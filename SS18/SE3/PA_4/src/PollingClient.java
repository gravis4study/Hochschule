public class PollingClient {
    static int count = 0;

    public static void main(String[] args) {
        PollingCaller caller = new PollingCaller("kongweiqi");


        while (!caller.isReturnend()) {
            counter();
            try {
                Thread.sleep(500);
            }catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("Ergebnis vom asynchronen Aufruf: ");
        String str = caller.getStr();
        System.out.println(str);
    }

    public static void counter(){
        System.out.println(count++);
    }

}
