import java.net.*;

public class InetAddressTest
{
    public static void main(String[] args) throws Exception
    {
        InetAddress addr=(Inet6Address)InetAddress.getByName("FE80::200C:417A");
        System.out.println(addr.isLinkLocalAddress());
        System.out.println(addr.isSiteLocalAddress());
        System.out.println(addr.isLoopbackAddress());

        InetAddress addr4=InetAddress.getByName("192.18.97.39");
        System.out.println(addr4.getHostName());

        Inet6Address addr6=(Inet6Address)InetAddress.getByName("::192.18.97.39");
        System.out.println(addr6.isIPv4CompatibleAddress());
    }
}
