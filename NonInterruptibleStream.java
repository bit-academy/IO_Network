import java.io.*;
import java.net.*;

public class NonInterruptibleStream extends Thread {
    static ServerSocket server;
    static Socket socket;
    static InputStream is;

    public void run() {
        try {
            server = new ServerSocket(80);
            System.out.println("accept() 중...");
            socket = server.accept();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("accept() 해서 Socket 을 얻었음.");

        try {
            InputStream is = socket.getInputStream();

            for (int i = 0; (i = is.read()) != -1;) {
                // sleep(1);
                System.out.write(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Thread thread = new NonInterruptibleStream();
        thread.start();
        System.out.println("스레드에 interrupt() 를 예약합니다.");
        thread.interrupt();
        Thread.sleep(1000);
    }
}