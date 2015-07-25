import java.net.*;
import java.nio.*;
import java.nio.channels.*;

public class DatagramChannelReceiver
{
    public static void main(String[] args) throws Exception
    {
        DatagramChannel dc=DatagramChannel.open();
        DatagramSocket socket=dc.socket();
        socket.bind(new InetSocketAddress(22500));
        ByteBuffer byteBuf=ByteBuffer.allocate(8);
//        byteBuf.order(ByteOrder.LITTLE_ENDIAN);
        LongBuffer longBuf=byteBuf.asLongBuffer();

        while(true)
        {
            dc.receive(byteBuf);
            System.out.println(longBuf.get(0));
            byteBuf.clear();
        }
    }
}
