import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;

public class SocketChannelTest1
{
    public static void main(String[] args) throws Exception
    {
        if (args.length<1)
        {
            System.out.println("���� : java SocketChannelTest1 ��������Ʈ��ȣ");
            System.exit(1);
        }

        int ����������Ʈ=Integer.parseInt(args[0]);
        SocketAddress addr=new InetSocketAddress("localhost", ����������Ʈ);
        SocketChannel socket=SocketChannel.open(addr);
//        socket.configureBlocking(false); // �� ������ �߰��ϸ� ����� �޶�����.

        System.out.println(socket);
        System.out.println(socket.isBlocking());

        ByteBuffer buf=ByteBuffer.allocate(100);
        buf.limit(10);
        int wrote=socket.write(buf);
        System.out.println("write : "+wrote);
        buf.clear();

        int read=0;
        while(true)
        {
            read=socket.read(buf);
            System.out.println("read : "+read);
            Thread.sleep(1000);
        }
    }
}