import java.io.IOException;

public class PollingCaller extends Thread {
    private boolean returnend = false;
    private String str;

    public PollingCaller(String str) {
        this.str = str;
        this.start();
    }

    public void run() {
        System.out.println(" entfernter Aufruf läuft u. wartet auf Taste ");
        str = this.wandleKlein(str);

        try {
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        returnend = true;
    }

    public boolean isReturnend() {
        return returnend;
    }

    public String wandleKlein(String str) {

        return str.toUpperCase();
    }

    public String getStr() {
        return str;
    }
}
