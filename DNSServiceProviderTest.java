import java.io.*;
import java.util.*;
import javax.naming.*;
import javax.naming.directory.*;

public class DNSServiceProviderTest {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.dns.DnsContextFactory");
        env.put(Context.PROVIDER_URL, "dns://202.30.143.11");
        DirContext ctx = new InitialDirContext(env);

        System.out.println(" === ������ ������ ��������.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (String str; (str = br.readLine()) != null;) {
            Attributes attrs = ctx.getAttributes(str);
            if (attrs == null) {
                System.out.println("��ϵ� ������ �����ϴ�.");
                continue;
            }

            for (NamingEnumeration enums = attrs.getAll(); enums.hasMore();) {
                System.out.println(enums.next());
            }
            System.out.println("\n === ������ ������ ��������.");
        }
    }
}