import ApiCalls.CryptoCall;

public class Main {
    public static void main(String[] args) {
        CryptoCall call = new CryptoCall();

        try {
            call.getPrice("bitcoin");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
