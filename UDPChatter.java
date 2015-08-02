import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.HashSet;

public class UDPChatter extends Thread {
    HashSet<InetAddress> chatters = new HashSet<InetAddress>();
    DatagramSocket udpSocket;
    byte[] sendBuf = new byte[100];
    byte[] recvBuf = new byte[100];
    DatagramPacket sendPacket, recvPacket;

    public UDPChatter(String hostIP) throws SocketException,
            UnknownHostException {
        udpSocket = new DatagramSocket();
        sendPacket = new DatagramPacket(sendBuf, sendBuf.length,
                InetAddress.getByName(hostIP), 22501);
        recvPacket = new DatagramPacket(recvBuf, recvBuf.length);
    }

    public void run() {
        try {
            while (true) {
                udpSocket.receive(recvPacket);

                System.out.println(new String(recvBuf, 0, recvPacket
                        .getLength(), "UTF-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            udpSocket.close();
        }

    }

    private void sendChat(String chat) throws IOException {
        byte[] chatData = chat.getBytes("UTF-8");
        int chatLen = chatData.length < 100 ? chatData.length : 100;

        System.arraycopy(chatData, 0, sendBuf, 0, chatLen);

        sendPacket.setLength(chatLen);
        udpSocket.send(sendPacket);
    }

    public static void main(String[] args) throws IOException {
        if (args.length < 1) {
            System.out.println("사용법: java UDPChatter 서버주소");
            System.exit(1);
        }

        UDPChatter udpChatter = new UDPChatter(args[0]);
        udpChatter.start();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (String str; (str = br.readLine()) != null;) {
            udpChatter.sendChat(str);
        }
    }
}
