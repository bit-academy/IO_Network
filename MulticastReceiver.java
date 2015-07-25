import java.io.*;
import java.net.*;

public class MulticastReceiver
{
	public static void main(String[] args) throws Exception
	{
		MulticastSocket socket=new MulticastSocket(22500);
		socket.joinGroup(InetAddress.getByName(args[0]));
		byte[] buf=new byte[8];
		DatagramPacket dp=new DatagramPacket(buf, buf.length);

		while(true)
		{
			socket.receive(dp);
			printTime(buf);
		}
	}

        static void printTime(byte[] buf)
        {
                long imsi, time=0;
                for (int i=0; i<8; i++)
                {
                        imsi=buf[i] & 0xff;
                        imsi<<=(i*8);
                        time|=imsi;
                }
                System.out.println(time);
        }
}
