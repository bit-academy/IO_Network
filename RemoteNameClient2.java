import java.rmi.*;

public class RemoteNameClient2
{
    public static void main(String[] args) throws Exception
    {
        System.setSecurityManager(new RMISecurityManager());

        RemoteNameInterface remote = (RemoteNameInterface)Naming.lookup("RemoteName_1");

        RemoteName name=remote.getRemoteName();
        System.out.println(name.getName());

        System.out.println(name);
        System.out.println(name.getClass());
    }
}
