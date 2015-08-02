import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class MultiChatClient {
    public static void main(String[] args) throws Exception {
        if (args.length < 1) {
            System.out.println("사용법: java ChatClent 서버주소");
            System.exit(1);
        }

        Socket socket = new Socket(args[0], 22501);
        InputStream input = socket.getInputStream();
        OutputStream output = socket.getOutputStream();
        new MultiChatDataExchanger(System.in, System.out, input, output);
    }
}
