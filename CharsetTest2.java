import java.io.*;
import java.net.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.channels.*;
import java.util.*;

public class CharsetTest2 implements Runnable
{
    ByteBuffer mesgBuf, readBuf;
    Selector selector;
    CharsetDecoder decoder;

    public CharsetTest2(String mesg) throws Exception
    {
        Charset charset=Charset.forName("UTF-8");
        CharsetEncoder encoder=charset.newEncoder();
        decoder=charset.newDecoder();
        mesgBuf=encoder.encode(CharBuffer.wrap(mesg));
        readBuf=ByteBuffer.allocate(50);

        selector=Selector.open();
        ServerSocketChannel serverChannel=ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        serverChannel.socket().bind(new InetSocketAddress(80));
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);
    }

    public void run()
    {
        int socketOps=SelectionKey.OP_CONNECT | SelectionKey.OP_READ
            | SelectionKey.OP_WRITE;

        while(true)
        {
            try
            {
                selector.select();
            } catch(IOException ioe)
            {
                ioe.printStackTrace();
            }
            Iterator seletectedKeys=selector.selectedKeys().iterator();

            while(seletectedKeys.hasNext())
            {
                try
                {
                    SelectionKey selected=(SelectionKey)seletectedKeys.next();
                    seletectedKeys.remove();
                    SelectableChannel channel=selected.channel();
                    if (channel instanceof ServerSocketChannel)
                    {
                        ServerSocketChannel serverChannel=(ServerSocketChannel)channel;
                        SocketChannel socketChannel=serverChannel.accept();
                        if (socketChannel==null)
                        {
                            System.out.println(" # null server socket");
                            continue;
                        }

                        System.out.println(" # socket accepted : "+socketChannel);

                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector, socketOps);
                    } else
                    {
                        SocketChannel socketChannel=(SocketChannel)channel;
                        if (selected.isConnectable())
                        {
                            System.out.println(" # socket connected");
                            if (socketChannel.isConnectionPending())
                            {
                                System.out.println(" # Connection is pending");
                                socketChannel.finishConnect();
                            }
                        }

                        if (selected.isReadable())
                        {
                            int read=socketChannel.read(readBuf);
                            readBuf.flip();
                            System.out.print("\n  # socket read : "+read);
                            System.out.println(", "+decoder.decode(readBuf));
                            readBuf.clear();
                        }

                        if (selected.isWritable())
                        {
                            int wrote=socketChannel.write(mesgBuf);
                            System.out.println("\n # socket write : "+wrote);
                            mesgBuf.clear();
                        }
                    }
       			} catch(IOException ioe)
	        	{
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length<1)
        {
            System.out.println("사용법 : java CharsetTest2 메시지");
            System.exit(1);
        }

        CharsetTest2 ct2=new CharsetTest2(args[0]);
        new Thread(ct2).start();
    }
}
