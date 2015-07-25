import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class DatagramChannelSender
{
    public static void main(String[] args) throws Exception
    {
        if (args.length<1)
        {
            System.out.println("���� : java DatagramChannelSender �����ּ�");
            System.exit(1);
        }

        ByteBuffer byteBuf=ByteBuffer.allocate(8);
//        byteBuf.order(ByteOrder.LITTLE_ENDIAN);
        LongBuffer longBuf=byteBuf.asLongBuffer();

        DatagramChannel dc=DatagramChannel.open();
        DatagramSocket socket=dc.socket();
        System.out.println(socket.isBound());
        dc.connect(new InetSocketAddress(args[0], 22500));
        System.out.println(socket.isBound());

        while(true)
        {
            long time=System.currentTimeMillis();
            System.out.println(time);
            longBuf.put(0, time);
            dc.write(byteBuf);
            Thread.sleep(500);
            byteBuf.clear();
        }
    }
}
