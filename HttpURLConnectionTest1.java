import java.io.*;
import java.net.*;

public class HttpURLConnectionTest1
{
    public static void main(String[] args) throws IOException
    {
        Socket http=new Socket(args[0], Integer.parseInt(args[1]));
        PrintWriter pw=new PrintWriter(http.getOutputStream());
        Reader reader=new InputStreamReader(http.getInputStream());
        BufferedReader br=new BufferedReader(reader);

        pw.write("GET /index.html HTTP/1.0\n\n");
        pw.flush();

        for (String str; (str=br.readLine())!=null; )
        {
            System.out.println(str);
        }
    }
}
