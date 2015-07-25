import java.io.*;
import java.net.*;

public class ChatServer implements Runnable
{
    ServerSocket server;

    public ChatServer() throws IOException
    {
    server=new ServerSocket(22500);
    }

    public void run()
    {
        try
        {
            Socket ����;
            InputStream �Է�1, �Է�2;
            OutputStream ���1, ���2;

            while(true)
            {
                System.out.println("ChatServer���� ������ ��ٸ��ϴ�.");
                ����=server.accept();
                System.out.println("ù ��° ���ӵǾ����ϴ�.\t"+����);
                �Է�1=����.getInputStream();
                ���1=����.getOutputStream();

                ����=server.accept();
                System.out.println("�� ��° ���ӵǾ����ϴ�.\t"+����);
                �Է�2=����.getInputStream();
                ���2=����.getOutputStream();

                new ChatDataExchanger(�Է�1, ���1, �Է�2, ���2);

                System.out.println("ä�ù��� ��������ϴ�. ----------\n");
            }
        } catch(Exception e)
        {
            System.err.println("ä���غ��� ���ܰ� �߻��߽��ϴ�.");
            e.printStackTrace();
        }
	}

	public static void main(String[] args)
	{
        try
        {
            ChatServer cs=new ChatServer();
            new Thread(cs).start();
        } catch(IOException ioe)
        {
            System.err.println("ChatServer�� ������ �� �����ϴ�.");
            ioe.printStackTrace();
        }
	}
}
