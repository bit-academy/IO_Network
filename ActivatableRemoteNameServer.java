import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
import java.rmi.activation.*;

public class ActivatableRemoteNameServer
    extends Activatable implements RemoteNameInterface
{
    RemoteName remoteName;

    public ActivatableRemoteNameServer(ActivationID id, MarshalledObject data)
        throws RemoteException
    {
        super(id, 0);
        remoteName=new RemoteName();
        remoteName.setName("RemoteName �����Դϴ�.");
    }

    public RemoteName getRemoteName() throws RemoteException
    {
        System.out.println("Local ��ü�� getRemoteName() �� ȣ���ؿԽ��ϴ�.");
        return remoteName;
    }

    public static void main(String[] args) throws Exception
    {
        System.setSecurityManager(new RMISecurityManager());

        Properties props = new Properties();
        props.put("java.security.policy",
           "c:/myJava/rmid.policy");

        ActivationGroupDesc remoteNameGroup =
            new ActivationGroupDesc(props, null);

        ActivationGroupID agi =
           ActivationGroup.getSystem().registerGroup(remoteNameGroup);

        ActivationDesc desc = new ActivationDesc(agi,
            "ActivatableRemoteNameServer", "file:/c:/myJava/book/", null);

        RemoteNameInterface rni=(RemoteNameInterface)Activatable.register(desc);
        System.out.println("RMI Daemon �� ����ϰ� Stub�� ������ϴ�.");

        LocateRegistry.getRegistry("localhost");
        Naming.rebind("RemoteName_1", rni);
    }
}
