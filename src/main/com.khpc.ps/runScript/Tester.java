package runScript;

public class Tester {
    public static void main(String args[]){
        Watcher demo = new Watcher("C:\\Users\\aaron\\Desktop");
        Thread demoT = new Thread(demo);
        demoT.start();
    }
}
