package com.example.crypto;

import javafx.application.Platform;
import javafx.scene.chart.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

//using this tutorial
//https://levelup.gitconnected.com/realtime-charts-with-javafx-ed33c46b9c8d

//intended to be used as  view for every coin, so that different trends can be
//monitored at the same time

public class RTPriceChart {
    final CategoryAxis xAxis = new CategoryAxis();//time axis
    final NumberAxis yAxis = new NumberAxis();//price of currency
    final LineChart<String, Number> lineChart;
    final int WINDOW_SIZE = 43200;//half a day if plotting every second
    private Coin coin;
    RTPriceChart(Coin coin){
        this.coin=coin;

        xAxis.setLabel("Time/s");
        xAxis.setAnimated(true);
        yAxis.setLabel("Value($)");
        yAxis.setAnimated(true);

        lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle(coin.getCoinName());
        lineChart.setAnimated(true);

        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Data Series");
        // add series to chart
        lineChart.getData().add(series);

        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        ScheduledExecutorService scheduledExecutorService;
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        scheduledExecutorService.scheduleAtFixedRate(() -> {
            Double currentPrice=this.coin.getPrice();

            // Update the chart
            Platform.runLater(() -> {
                // get current time
                Date now = new Date();
                // put random number with current time
                series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), currentPrice));
                if (series.getData().size() > WINDOW_SIZE)
                    series.getData().remove(0);
            });
        }, 0, 1, TimeUnit.SECONDS);
    }
}
//Need to add this method to the main method calling this class
//@Override
//public void stop() throws Exception {
//        super.stop();
//        scheduledExecutorService.shutdownNow();
//        }