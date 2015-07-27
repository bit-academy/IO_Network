import java.io.*;

public class DataStreamTest
{
    File imsiFile;

    public DataStreamTest() throws IOException
    {
            imsiFile=File.createTempFile("imsi", ".tmp");
            imsiFile.deleteOnExit();
    }

    public void writeData(int x, int y) throws IOException
    {
        FileOutputStream fos=new FileOutputStream(imsiFile);
        DataOutputStream dos=new DataOutputStream(fos);

        dos.writeInt(x);
        dos.writeInt(y);
        dos.writeInt(x*y);
        dos.close();
    }

    public void readData() throws IOException
    {
        FileInputStream fis=new FileInputStream(imsiFile);
        DataInputStream dis=new DataInputStream(fis);

        System.out.print(dis.readInt());
        System.out.print(" * ");
        System.out.print(dis.readInt());
        System.out.print(" = ");
        System.out.print(dis.readInt());

        dis.close();
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length<2)
        {
            System.out.println("사용법: java DataStreamTest x y");
            System.exit(1);
        }

        int x=Integer.parseInt(args[0]);
        int y=Integer.parseInt(args[1]);
        DataStreamTest dst=new DataStreamTest();
        dst.writeData(x, y);
        dst.readData();
    }
}
