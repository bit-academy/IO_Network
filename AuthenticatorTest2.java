import java.io.*;
import java.net.*;

public class AuthenticatorTest2
{
    HttpURLConnection conn;
    static String id;
    static char[] password;

    public AuthenticatorTest2(String urlString) throws IOException
    {
        URL url=new URL(urlString);
        conn=(HttpURLConnection)url.openConnection();
    }

    void printContent() throws IOException
    {
        Reader reader=new InputStreamReader(conn.getInputStream());
        BufferedReader br=new BufferedReader(reader);

        for (String str; (str=br.readLine())!=null; )
        {
            System.out.println(str);
        }
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length<3)
        {
            System.out.println("사용법 : java AuthenticatorTest2 url id password");
            System.exit(1);
        }

        id=args[1];
        password=args[2].toCharArray();

        Authenticator.setDefault(new MyAuthenticator());
        AuthenticatorTest2 auth=new AuthenticatorTest2(args[0]);
        auth.printContent();
    }

    static class MyAuthenticator extends Authenticator
    {
        public PasswordAuthentication getPasswordAuthentication()
        {
            System.out.println(" ### 다음과 같은 정보로 PasswordAuthentication 이 요구되었습니다.");
            System.out.println("Host : "+getRequestingHost());
            System.out.println("Port : "+getRequestingPort());
            System.out.println("Prompt : "+getRequestingPrompt());
            System.out.println("Protocol : "+getRequestingProtocol());
            System.out.println("Scheme : "+getRequestingScheme());
            System.out.println("Site : "+getRequestingSite());

            return new PasswordAuthentication(id, password);
        }
    }
}
