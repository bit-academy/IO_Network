import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;

public class RemoteNameServer2 extends UnicastRemoteObject implements RemoteNameInterface
{
    RemoteName remoteName;

    public RemoteNameServer2() throws RemoteException
    {
        remoteName=new RemoteName();
        remoteName.setName("RemoteName �����Դϴ�.");
    }

    public RemoteName getRemoteName() throws RemoteException
    {
        System.out.println("Local ��ü�� getRemoteName() �� ȣ���ؿԽ��ϴ�.");
        return remoteName;
    }

    public static void main(String[] args) throws java.io.IOException
    {
        RemoteNameServer2 server=new RemoteNameServer2();
        LocateRegistry.getRegistry("localhost", 1099);
        Naming.rebind("RemoteName_1", server);
    }
}
