import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.ArrayList;

public class MultiChatServer {
    private static ArrayList<MultiChatThread> clientList = new ArrayList<MultiChatThread>();

    public static void main(String[] args) throws Exception {
        MultiChatServer server = new MultiChatServer();
        ServerSocket serverSocket = new ServerSocket(22501);

        while (true) {
            System.out.println("ChatServer 에서 connection 을 기다립니다.");
            Socket connection = serverSocket.accept();

            System.out.println("connection 이 되었습니다.\t" + connection);

            MultiChatThread thread = new MultiChatThread(server, connection);
            clientList.add(thread);
            thread.start();
        }
    }

    synchronized public void notifyChatReceived(SocketAddress socketAddress,
            String chat) {
        chat = "[" + socketAddress + "] " + chat;

        for (MultiChatThread client : clientList) {
            if (client.getClientSocketAddress().equals(socketAddress)) {
                continue;
            }

            try {
                client.send(chat);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
