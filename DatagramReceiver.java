import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class DatagramReceiver {
    public static void main(String[] args) throws Exception {
        DatagramSocket ds = new DatagramSocket(22500);
        byte[] buf = new byte[8];
        DatagramPacket dp = new DatagramPacket(buf, buf.length);

        while (true) {
            ds.receive(dp);
            printTime(buf);
        }
    }

    static void printTime(byte[] buf) {
        long imsi, time = 0;
        for (int i = 0; i < 8; i++) {
            imsi = buf[i] & 0xff;
            imsi <<= (i * 8);
            time |= imsi;
        }
        System.out.println(time);
    }
}
