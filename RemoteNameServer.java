import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class RemoteNameServer extends UnicastRemoteObject implements RemoteNameInterface
{
    RemoteName remoteName;

    public RemoteNameServer() throws RemoteException
    {
        remoteName=new RemoteName();
        remoteName.setName("RemoteName 서버입니다.");
    }

    public RemoteName getRemoteName() throws RemoteException
    {
        System.out.println("Local 객체가 getRemoteName() 를 호출해왔습니다.");
        return remoteName;
    }

    public void putMe(RemoteNameInterface name) throws RemoteException
    {
        System.out.println(name.getRemoteName().getName());
    }

    public static void main(String[] args) throws java.io.IOException
    {
        RemoteNameServer server=new RemoteNameServer();
        LocateRegistry.createRegistry(1099);
        Naming.rebind("RemoteName_1", server);
    }
}
