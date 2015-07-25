import java.net.*;

public class URLCODECTest
{
    public static void main(String[] args) throws Exception
    {
        System.out.println(URLEncoder.encode("자바 2 SDK 1.4 멀린"));
        System.out.println(URLEncoder.encode("자바 2 SDK 1.4 멀린", "UTF-8"));
        System.out.println(URLDecoder.decode("%C0%DA%B9%D9+2+SDK+1.4+%B8%D6%B8%B0", "EUC-KR"));
    }
}
