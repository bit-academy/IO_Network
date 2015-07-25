import java.rmi.*;

public interface RemoteNameInterface extends Remote
{
    public RemoteName getRemoteName() throws RemoteException;
}
