import java.io.*;
import java.net.*;

public class DatagramSender
{
    public static void main(String[] args) throws Exception
    {
        if (args.length<1)
        {
            System.out.println("사용법 : java DatagramSender 서버주소");
            System.exit(1);
        }

        byte[] buf=new byte[8];
        DatagramSocket ds=new DatagramSocket();
        DatagramPacket dp=new DatagramPacket(buf, buf.length
            , new InetSocketAddress(args[0], 22500));

        while(true)
        {
            setTime(buf);
            ds.send(dp);
            Thread.sleep(500);
        }
    }

    static void setTime(byte[] buf)
    {
        long time=System.currentTimeMillis();

        for (int i=0; i<8; i++)
        {
            long value=time>>(i*8);
            buf[i]=(byte)(value & 0xff);
        }
        System.out.println(time);
    }
}
