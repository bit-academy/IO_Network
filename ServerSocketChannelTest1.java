import java.io.*;
import java.net.*;
import java.util.*;
import java.nio.*;
import java.nio.channels.*;

public class ServerSocketChannelTest1 implements Runnable
{
    Selector selector;

    public ServerSocketChannelTest1() throws IOException
    {
        selector=Selector.open();
        ServerSocketChannel server1=getServerSocketChannel(5555);
        ServerSocketChannel server2=getServerSocketChannel(7777);

        int validOps=server1.validOps();
        System.out.print("ServerSocketChannel.validOps() : "+validOps);
        System.out.println(", "+(validOps==SelectionKey.OP_ACCEPT));

        server1.register(selector, SelectionKey.OP_ACCEPT);
        server2.register(selector, SelectionKey.OP_ACCEPT);
    }

    ServerSocketChannel getServerSocketChannel(int port) throws IOException
    {
        ServerSocketChannel server=ServerSocketChannel.open();
        System.out.println(server);

        ServerSocket socket=server.socket();
        SocketAddress addr=new InetSocketAddress(port);
        socket.bind(addr);
        System.out.println(server);

        server.configureBlocking(false);

        return server;
    }

    public void run()
    {
        int socketOps=SelectionKey.OP_CONNECT | SelectionKey.OP_READ
            | SelectionKey.OP_WRITE;
        ByteBuffer buf=null;

        while(true)
        {
            try
            {
                selector.select();
            } catch(IOException ioe)
            {
                ioe.printStackTrace();
            }

            Set selectedKeys=selector.selectedKeys();
            Iterator iter=selectedKeys.iterator();

            while(iter.hasNext())
            {
                try
                {
                    SelectionKey selected=(SelectionKey)iter.next();
                    iter.remove();
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
                        int validOps=socketChannel.validOps();
                        System.out.print("SocketChannel.validOps() : "+validOps);
                        System.out.println(", "+(validOps==socketOps));

                        buf=ByteBuffer.allocate(10);
                        socketChannel.register(selector, socketOps, buf);
                    } else
                    {
                        SocketChannel socketChannel=(SocketChannel)channel;
                        buf=(ByteBuffer)selected.attachment();

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
                            int read=socketChannel.read(buf);
                            System.out.println(" # socket read : "+read);
                            buf.clear();
                        }

                        if (selected.isWritable())
                        {
                            int wrote=socketChannel.write(buf);
                            System.out.println(" # socket write : "+wrote);
                            buf.clear();
                        }
                    }
       			} catch(IOException ioe)
	        	{
                    ioe.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        ServerSocketChannelTest1 test=new ServerSocketChannelTest1();
        new Thread(test).start();
    }
}
