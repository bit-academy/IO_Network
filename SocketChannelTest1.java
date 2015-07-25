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
            System.out.println("사용법 : java SocketChannelTest1 접속할포트번호");
            System.exit(1);
        }

        int 서버소켓포트=Integer.parseInt(args[0]);
        SocketAddress addr=new InetSocketAddress("localhost", 서버소켓포트);
        SocketChannel socket=SocketChannel.open(addr);
//        socket.configureBlocking(false); // 이 라인을 추가하면 결과가 달라진다.

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