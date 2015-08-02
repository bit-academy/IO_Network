import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;

public class DNSServiceProviderTest {
    public static void main(String[] args) throws Exception {
        Hashtable env = new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
                "com.sun.jndi.dns.DnsContextFactory");
        env.put(Context.PROVIDER_URL, "dns://202.30.143.11");
        DirContext ctx = new InitialDirContext(env);

        System.out.println(" === 도메인 네임을 넣으세요.");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (String str; (str = br.readLine()) != null;) {
            Attributes attrs = ctx.getAttributes(str);
            if (attrs == null) {
                System.out.println("등록된 정보가 없습니다.");
                continue;
            }

            for (NamingEnumeration enums = attrs.getAll(); enums.hasMore();) {
                System.out.println(enums.next());
            }
            System.out.println("\n === 도메인 네임을 넣으세요.");
        }
    }
}