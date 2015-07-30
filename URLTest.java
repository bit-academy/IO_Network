import java.io.*;
import java.net.*;

public class URLTest {
    public static void main(String[] args) throws Exception {
        URL url1 = new URL("http://mindwing.kr");
        print(url1);

        URL url2 = new URL("http", "connec2u.com",
                "/2014/08/google-google-wallet.html");
        print(url2);

        URL url3 = new URL(url2, "/2014/07/thread-group-nest-arm.html");
        print(url3);

        URL url4 = new URL(url2, "/2014/07/wearable-athos.html");
        print(url4);

        URL url5 = new URL("ftp://myid:mypassword@ftp.mindwing.kr/myfile.txt");
        print(url5);
    }

    static void print(URL url) {
        System.out.println(url);
        System.out.println("Path : " + url.getPath());
        System.out.println("Host : " + url.getHost());
        System.out.println("Authority : " + url.getAuthority());
        System.out.println("UserInfo : " + url.getUserInfo());

        try {
            System.out.println(url.getContent());
        } catch (FileNotFoundException fnfe) {
            System.out.println("URL 정보에 의한 자원이 없습니다.");
        } catch (IOException ioe) {
            System.out.println(ioe.getClass() + ", " + ioe.getMessage());
        } finally {
            System.out.println();
        }
    }
}
