import java.net.*;
import java.util.*;

public class NetworkInterfaceTest
{
    public static void main(String[] args) throws Exception
    {
        Enumeration enum=NetworkInterface.getNetworkInterfaces();
        while(enum.hasMoreElements())
        {
            System.out.println(enum.nextElement());
        }
    }
}