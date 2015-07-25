import java.io.*;
import java.net.*;
import java.util.*;

public class URLConnectionTest2
{
    public static void main(String[] args) throws Exception
    {
        if (args.length<1)
        {
                System.out.println("���� : java URLConnectionTest2 <url>");
                System.exit(1);
        }

        URL url=new URL(args[0]);
        URLConnection con=url.openConnection();

        System.out.println("\n### URLConnection �� �⺻��");
        System.out.println("URLConnection.getDefaultAllowUserInteraction() : "+con.getDefaultAllowUserInteraction());
        System.out.println("URLConnection.getDefaultUseCaches() : "+con.getDefaultUseCaches());

        System.out.println("\n### URLConnection �� ���� ���ӿ� ����");
        System.out.println("con.getRequestProperties() : "+con.getRequestProperties());
        System.out.println("con.getAllowUserInteraction() : "+con.getAllowUserInteraction());
        System.out.println("con.getDoInput() : "+con.getDoInput());
        System.out.println("con.getDoOutput() : "+con.getDoOutput());
        System.out.println("con.getIfModifiedSince() : "+con.getIfModifiedSince());
        System.out.println("con.getUseCaches() : "+con.getUseCaches());

        con.connect();

        System.out.println("\n### URLConnection �ڿ��� ���� ����[1]");
        System.out.println("con.getContentEncoding() : "+con.getContentEncoding());
        System.out.println("con.getContentLength() : "+con.getContentLength());
        System.out.println("con.getContentType() : "+con.getContentType());
        System.out.println("con.getDate() : "+con.getDate());
        System.out.println("con.getExpiration() : "+con.getExpiration());
        System.out.println("con.getLastModified() : "+con.getLastModified());
        System.out.println("con.getURL() : "+con.getURL());

        System.out.println("\n### URLConnection �ڿ��� ���� ����[2]");
        System.out.println("con.getHeaderFields() : "+con.getHeaderFields());

        System.out.println("\n### URLConnection �ڿ��� ����");
        System.out.println("con.getContent() : "+con.getContent());
        System.out.println("con.getInputStream() : "+con.getInputStream());
    }
}
