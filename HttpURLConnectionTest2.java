import java.io.*;
import java.net.*;

public class HttpURLConnectionTest2
{
    public static void main(String[] args) throws IOException
    {
        HttpURLConnection.setFollowRedirects(false);

        URL url=new URL(args[0]);
        HttpURLConnection conn=(HttpURLConnection)url.openConnection();

        System.out.println("\n### Header[1]");
        System.out.println("HTTP Request-Method : "+conn.getRequestMethod());
        System.out.println("HTTP Status-Code : "+conn.getResponseCode());
        System.out.println("HTTP Response-Message : "+conn.getResponseMessage());

        System.out.println("\n### Header[2]");
        System.out.println("HTTP Response Header Fields : "+conn.getHeaderFields());

        System.out.println("\n### flowRedirects");
        System.out.println("HttpURLConnection.getInstanceFollowRedirects() : "+conn.getFollowRedirects());
        System.out.println("HttpURLConnection.getFollowRedirects() : "+conn.getInstanceFollowRedirects());

        System.out.println("\n### ³»¿ë");
        Reader reader=new InputStreamReader(conn.getInputStream());
        BufferedReader br=new BufferedReader(reader);

        for (String str; (str=br.readLine())!=null; )
        {
            System.out.println(str);
        }
    }
}
