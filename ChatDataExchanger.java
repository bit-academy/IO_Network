import java.io.*;
import java.net.*;

public class ChatDataExchanger
{
    ExchangeThread readThread, writeThread;

    public ChatDataExchanger(InputStream �Է�a, OutputStream ���a
        , InputStream �Է�b, OutputStream ���b) throws IOException
    {
        readThread=new ExchangeThread(�Է�a, ���b);
        readThread.start();
        writeThread=new ExchangeThread(�Է�b, ���a);
        writeThread.start();
    }

    class ExchangeThread extends Thread
    {
        BufferedReader reader;
        PrintStream writer;

        ExchangeThread(InputStream �Է�, OutputStream ���) throws IOException
        {
            reader=new BufferedReader(new InputStreamReader(�Է�));

            if (���==System.out)
            {
                writer=(PrintStream)���;
            } else
            {
                writer=new PrintStream(���, true);
            }
        }

        public void run()
        {
            try
            {
                while(true)
                {
                    for (String str; (str=reader.readLine())!=null; )
                    {
                        if (writer==System.out)
                        {
                            writer.print("[����] : ");
                        }
                        writer.println(str);
                    }
                }
            } catch(Exception e)
            {
                System.err.println("ä�õ����͸� �о���� �� ���ܰ� �߻��߽��ϴ�.");
                e.printStackTrace();
            }
        }
    }
}
