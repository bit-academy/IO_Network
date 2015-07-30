import java.io.*;
import java.net.*;

public class ChatClient {
    public ChatClient(String server) throws Exception {
        Socket socket = new Socket(server, 22500);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        new ChatDataExchanger(System.in, System.out, input, output);
    }

    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("사용법 : java ChatClent 서버주소");
            System.exit(1);
        }

        ChatClient client = new ChatClient(args[0]);
    }
}
