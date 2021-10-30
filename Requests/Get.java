package Requests;

// Imports
import java.io.IOException;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;

public class Get {
    // Class variables
    public String url;

    public Get(String url) {
        this.url = url;
    }

    // Class methods
    public String response() throws IOException {
        // Sending a GET request to get the price
        InputStream response = new URL(url).openStream(); //getting input stream
        try (Scanner scanner = new Scanner(response)) {
            return scanner.useDelimiter("\\A").next();
        } catch(Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
