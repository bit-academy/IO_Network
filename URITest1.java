import java.net.*;

public class URITest1
{
    public static void main(String[] args) throws Exception
    {
        URI uri=new URI(args[0]);
        System.out.println("Original --- "+uri);
        printURI(uri);
        URI normalizedURI=uri.normalize();
        System.out.println("Normalized --- "+normalizedURI);
        printURI(normalizedURI);

        System.out.println(uri.parseServerAuthority());
        System.out.println(uri.toASCIIString());
        System.out.println(normalizedURI.toASCIIString());
    }

    static void printURI(URI uri)
    {
        System.out.println("getAuthority() : "+uri.getAuthority());
        System.out.println("getFragment() : "+uri.getFragment());
        System.out.println("getHost() : "+uri.getHost());
        System.out.println("getPath() : "+uri.getPath());
        System.out.println("getPort() : "+uri.getPort());
        System.out.println("getQuery() : "+uri.getQuery());
        System.out.println("getRawAuthority() : "+uri.getRawAuthority());
        System.out.println("getRawUserInfo() : "+uri.getRawUserInfo());
        System.out.println("getRawSchemeSpecificPart() : "+uri.getRawSchemeSpecificPart());
        System.out.println("getRawFragment() : "+uri.getRawFragment());
        System.out.println("getRawPath() : "+uri.getRawPath());
        System.out.println("getRawQuery() : "+uri.getRawQuery());
        System.out.println(" ###\n");
    }
}
