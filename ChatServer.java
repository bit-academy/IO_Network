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
            Socket 접속;
            InputStream 입력1, 입력2;
            OutputStream 출력1, 출력2;

            while(true)
            {
                System.out.println("ChatServer에서 접속을 기다립니다.");
                접속=server.accept();
                System.out.println("첫 번째 접속되었습니다.\t"+접속);
                입력1=접속.getInputStream();
                출력1=접속.getOutputStream();

                접속=server.accept();
                System.out.println("두 번째 접속되었습니다.\t"+접속);
                입력2=접속.getInputStream();
                출력2=접속.getOutputStream();

                new ChatDataExchanger(입력1, 출력1, 입력2, 출력2);

                System.out.println("채팅방을 만들었습니다. ----------\n");
            }
        } catch(Exception e)
        {
            System.err.println("채팅준비중 예외가 발생했습니다.");
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
            System.err.println("ChatServer를 실행할 수 없습니다.");
            ioe.printStackTrace();
        }
	}
}
