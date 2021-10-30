package ApiCalls;

// Imports
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CryptoCall {
    // Class variables
    public String name, coinUrl, baseUrl = "https://api.coingecko.com/api/v3/coins/";
    public int price;

    // Class methods
    public String getPrice(String coin) throws IOException, InterruptedException {
        this.name = coin;
        var url = baseUrl + coin;
        this.coinUrl = url;

        // Sending a GET request to get the price
        var request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        var client = HttpClient.newBuilder().build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString()).body();

        System.out.println(response);

        return response;
    }
}
