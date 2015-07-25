import java.rmi.*;

public class RemoteNameClient
{
    public static void main(String[] args) throws Exception
    {
        RemoteNameInterface remote=(RemoteNameInterface)Naming.lookup("rmi://localhost/RemoteName_1");

        RemoteName name=remote.getRemoteName();
        System.out.println(name.getName());

        System.out.println(name);
        System.out.println(name.getClass());
    }
}