import java.io.*;

public class FileStreamTest
{
    FileInputStream fis;
    byte[] buf;

    public FileStreamTest(String fileName) throws FileNotFoundException
    {
        fis=new FileInputStream(fileName);
        buf=new byte[1024];
    }

    public void readUntilEnd() throws iOException
    {
        FileOutputStream fos=null;

        try
        {
            fos=new FileOutputStream("복사본");

            for (int i; (i=fis.read(buf))!=-1; )
            {
                    System.out.write(buf, 0, i);
                    fos.write(buf, 0, i);
            }
        } catch(IOException ioe)
        {
            fis.close();
            fos.close();
        }
    }

    public static void main(String[] args) throws IOException
    {
        if (args.length<1)
        {
            System.out.println("사용법 : java FileTest1 파일이름");
            System.exit(1);
        }

        FileStreamTest test=new FileStreamTest(args[0]);
        test.readUntilEnd();
    }
}
