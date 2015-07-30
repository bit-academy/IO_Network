import java.io.*;
import java.net.*;

public class SimpleServerSocketTest {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(10000);
        System.out.println(server);

        Socket accepted = server.accept();
        System.out.println(accepted);
        accepted.close();
    }
}
