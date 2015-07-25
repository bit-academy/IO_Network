import java.io.*;
import java.net.*;

public class URLTest
{
    public static void main(String[] args) throws Exception
    {
        URL url1=new URL("http://www.handan.pe.kr");
        print(url1);

        URL url2=new URL("http", "www.handan.pe.kr", "/mypath/myresource/index.html");
        print(url2);

        URL url3=new URL(url2, "/newpath/newresource");
        print(url3);

        URL url4=new URL(url2, "anotherresource");
        print(url4);

        URL url5=new URL("ftp://myid:mypassword@ftp.handan.pe.kr/myfile.txt");
        print(url5);
    }

    static void print(URL url)
    {
        System.out.println(url);
        System.out.println("Path : "+url.getPath());
        System.out.println("Host : "+url.getHost());
        System.out.println("Authority : "+url.getAuthority());
        System.out.println("UserInfo : "+url.getUserInfo());

        try
        {
            System.out.println(url.getContent());
        } catch(FileNotFoundException fnfe)
        {
            System.out.println("URL 정보에 의한 자원이 없습니다.");
        } catch(IOException ioe)
        {
            System.out.println(ioe.getClass()+", "+ioe.getMessage());
        } finally
        {
            System.out.println();
        }
    }
}
