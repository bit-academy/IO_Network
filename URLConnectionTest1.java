import java.net.*;

public class URLConnectionTest1
{
    public static void main(String[] args) throws Exception
    {
        String[] urls={"file:/c:/config.sys", "www.handan.pe.kr/myfile.txt"
            , "gopher://gopher.yoyodyne.com", "http://www.javasoft.com"
            , "jar:file:/c:/jdk1.4/jre/lib/rt.jar!/"
            , "mailto://handan@hitel.net"};

        for (int i=0; i<urls.length; i++)
        {
            URL url=new URL(urls[i]);
            URLConnection conn=url.openConnection();
            System.out.println(conn);
        }
    }
}
