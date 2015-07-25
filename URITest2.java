import java.net.*;

public class URITest2
{
    public static void main(String[] args) throws Exception
    {
        URI uri=new URI("http://handan.pe.kr/dir1/dir2/");

        URI anotherURI=new URI("http://handan.pe.kr/dir1/dir2/newDir2/aa.txt");

        URI resolve1=uri.resolve("sub1/index1.html");
        URI resolve2=uri.resolve("../sub2/index2.html");
        URI resolve3=uri.resolve("/sub3/index3.html");

        URI relatedURI=uri.relativize(anotherURI);

        System.out.println(uri);
        System.out.println("resolve(\"sub1/index1.html\") : "+resolve1);
        System.out.println("resolve(\"../sub2/index2.html\") : "+resolve2);
        System.out.println("resolve(\"/sub3/index3.html\") : "+resolve3);
        System.out.println("\nhttp://handan.pe.kr/dir1/dir2/");
        System.out.println("http://handan.pe.kr/dir1/dir2/newDir2/aa.txt");
        System.out.println("related URI ==> "+relatedURI);
    }
}