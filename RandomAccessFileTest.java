import java.io.*;

public class RandomAccessFileTest
{
    public static void main(String[] args) throws IOException
    {
        File imsiFile=File.createTempFile("imsi", ".tmp");
        imsiFile.deleteOnExit();
        FileOutputStream fos=new FileOutputStream(imsiFile);
        for (int i=0; i<=100; i++)
        {
            fos.write(i);
        }
        fos.close();

        RandomAccessFile raf=new RandomAccessFile(imsiFile, "rw");
        for (int i=100; i>=0; i-=10)
        {
            raf.seek(i);
            System.out.print(raf.readByte());
            System.out.print(" ");
        }
    }
}
