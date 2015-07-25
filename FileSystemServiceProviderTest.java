import java.io.*;
import java.util.*;
import javax.naming.*;

public class FileSystemServiceProviderTest
{
    public static void main(String[] args) throws Exception
    {
        Hashtable env=new Hashtable();
        env.put(Context.INITIAL_CONTEXT_FACTORY,
            "com.sun.jndi.fscontext.RefFSContextFactory");
        env.put(Context.PROVIDER_URL,"file:C:\\");
        Context ctx=new InitialContext(env);
        Context subCtx=ctx.createSubcontext("subDirectory");

        NamingEnumeration enum=ctx.list(".");
        while(enum.hasMore())
        {
            System.out.println(enum.next());
        }

        Object obj=ctx.lookup(args[0]);
        System.out.println("\n\n-----\n"+args[0]+" --> "+obj.getClass());
    }
}