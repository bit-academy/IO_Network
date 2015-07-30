import java.net.*;
import java.util.*;

public class NetworkInterfaceTest {
    public static void main(String[] args) throws Exception {
        Enumeration enums = NetworkInterface.getNetworkInterfaces();

        while (enums.hasMoreElements()) {
            System.out.println(enums.nextElement());
        }
    }
}