import java.io.*;

public class PipeStreamTest
{
    public static void main(String[] args) throws Exception
    {
        PipedOutputStream pos=new PipedOutputStream();
        PipedInputStream pis=new PipedInputStream(pos);

        for (int i; (i=System.in.read())!=-1; )
        {
            pos.write(i);
        }

        for (int i; (i=pis.read())!=-1; )
        {
            System.out.write(i);
        }
    }
}