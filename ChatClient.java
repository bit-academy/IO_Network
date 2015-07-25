import java.io.*;
import java.net.*;

public class ChatClient
{
    public ChatClient(String server) throws Exception
    {
        Socket socket=new Socket(server, 22500);
        InputStream �Է�=socket.getInputStream();
        OutputStream ���=socket.getOutputStream();
        new ChatDataExchanger(System.in, System.out, �Է�, ���);
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length<1)
        {
            System.out.println("���� : java ChatClent �����ּ�");
            System.exit(1);
        }

        ChatClient client=new ChatClient(args[0]);
    }
}
