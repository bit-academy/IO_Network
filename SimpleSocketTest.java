import java.io.*;
import java.net.*;

public class SimpleSocketTest {
    public static void main(String[] args) throws IOException {
        Socket connect = new Socket("127.0.0.1", 10000);
        System.out.println(connect);
        connect.close();
    }
}
