import java.io.*;
import java.net.*;

public class ChatServer implements Runnable {
    ServerSocket server;

    public ChatServer() throws IOException {
        server = new ServerSocket(22500);
    }

    public void run() {
        try {
            Socket connection;
            InputStream input1, input2;
            OutputStream output1, output2;

            while (true) {
                System.out.println("ChatServer에서 connection을 기다립니다.");
                connection = server.accept();
                System.out.println("첫 번째 connection되었습니다.\t" + connection);
                input1 = connection.getInputStream();
                output1 = connection.getOutputStream();

                connection = server.accept();
                System.out.println("두 번째 connection되었습니다.\t" + connection);
                input2 = connection.getInputStream();
                output2 = connection.getOutputStream();

                new ChatDataExchanger(input1, output1, input2, output2);

                System.out.println("채팅방을 만들었습니다. ----------\n");
            }
        } catch (Exception e) {
            System.err.println("채팅준비중 예외가 발생했습니다.");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try {
            ChatServer cs = new ChatServer();
            new Thread(cs).start();
        } catch (IOException ioe) {
            System.err.println("ChatServer를 실행할 수 없습니다.");
            ioe.printStackTrace();
        }
    }
}
