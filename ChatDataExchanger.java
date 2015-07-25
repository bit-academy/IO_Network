import java.io.*;
import java.net.*;

public class ChatDataExchanger
{
    ExchangeThread readThread, writeThread;

    public ChatDataExchanger(InputStream 입력a, OutputStream 출력a
        , InputStream 입력b, OutputStream 출력b) throws IOException
    {
        readThread=new ExchangeThread(입력a, 출력b);
        readThread.start();
        writeThread=new ExchangeThread(입력b, 출력a);
        writeThread.start();
    }

    class ExchangeThread extends Thread
    {
        BufferedReader reader;
        PrintStream writer;

        ExchangeThread(InputStream 입력, OutputStream 출력) throws IOException
        {
            reader=new BufferedReader(new InputStreamReader(입력));

            if (출력==System.out)
            {
                writer=(PrintStream)출력;
            } else
            {
                writer=new PrintStream(출력, true);
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
                            writer.print("[상대방] : ");
                        }
                        writer.println(str);
                    }
                }
            } catch(Exception e)
            {
                System.err.println("채팅데이터를 읽어오는 중 예외가 발생했습니다.");
                e.printStackTrace();
            }
        }
    }
}
