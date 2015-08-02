import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketAddress;
import java.util.HashSet;

public class UDPChatHost extends Thread {
    HashSet<SocketAddress> chatters = new HashSet<SocketAddress>();
    DatagramSocket ds;
    byte[] sendBuf = new byte[120];
    byte[] recvBuf = new byte[100];
    DatagramPacket sendPacket = new DatagramPacket(sendBuf, sendBuf.length);
    DatagramPacket recvPacket = new DatagramPacket(recvBuf, recvBuf.length);

    public void run() {
        try {
            ds = new DatagramSocket(22501);

            while (true) {
                ds.receive(recvPacket);

                chatters.add(recvPacket.getSocketAddress());
                castChat(recvPacket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            ds.close();
        }
    }

    private void castChat(DatagramPacket recvPacket) throws IOException {
        SocketAddress senderAddr = recvPacket.getSocketAddress();
        String chatData = new String(recvBuf, 0, recvPacket.getLength(),
                "UTF-8");

        for (SocketAddress chatter : chatters) {
            if (chatter.equals(senderAddr)) {
                continue;
            }

            String castData = "[" + senderAddr + "] " + chatData;
            byte[] castDataBuf = castData.getBytes("UTF-8");

            sendPacket.setData(castDataBuf);
            sendPacket.setSocketAddress(chatter);
            ds.send(sendPacket);
        }
    }

    public static void main(String[] args) {
        new UDPChatHost().start();
    }
}
