import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.SocketAddress;

public class MultiChatThread extends Thread {
    private Socket client;
    private MultiChatServer server;
    private BufferedReader br;
    private BufferedWriter bw;

    public MultiChatThread(MultiChatServer _server, Socket _client)
            throws IOException {
        server = _server;
        client = _client;

        InputStreamReader isr = new InputStreamReader(client.getInputStream(),
                "EUC-KR");
        br = new BufferedReader(isr);

        OutputStreamWriter osw = new OutputStreamWriter(
                client.getOutputStream(), "EUC-KR");
        bw = new BufferedWriter(osw);
    }

    public void send(String chat) throws IOException {
        bw.write(chat);
        bw.newLine();
        bw.flush();
    }

    public SocketAddress getClientSocketAddress() {
        return client.getRemoteSocketAddress();
    }

    public void run() {
        try {
            while (true) {
                for (String str; (str = br.readLine()) != null;) {
                    System.out.println(str);
                    server.notifyChatReceived(client.getRemoteSocketAddress(),
                            str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
