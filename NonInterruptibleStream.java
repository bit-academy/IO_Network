import java.io.*;
import java.net.*;

public class NonInterruptibleStream extends Thread
{
    static ServerSocket server;
    static Socket socket;
    static InputStream is;

    public void run()
    {
        try
        {
            server=new ServerSocket(80);
            System.out.println("accept() ��...");
            socket=server.accept();
        } catch(Exception e)
        {
            e.printStackTrace();
        }
        System.out.println("accept() �ؼ� Socket �� �����.");

        try
        {
            InputStream is=socket.getInputStream();
            while(true)
            {
                System.out.write(is.read());
            }
        } catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception
    {
        Thread thread=new NonInterruptibleStream();
        thread.start();
        System.out.println("�����忡 interrupt() �� �����մϴ�.");
        thread.interrupt();
        Thread.sleep(1000);
    }
}