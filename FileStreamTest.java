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

    public void readUntilEnd()
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
            System.out.println("스트림에 이상이 있음 : "+ioe.getMessage());
        } finally
        {
            try
            {
                    fis.close();
            } catch(IOException ioe2)
            {
            }

            try
            {
                    fos.close();
            } catch(IOException ioe2)
            {
            }
        }
    }

    public static void main(String[] args)
    {
        if (args.length<1)
        {
            System.out.println("사용법 : java FileTest1 파일이름");
            System.exit(1);
        }

        try
        {
            FileStreamTest test=new FileStreamTest(args[0]);
            test.readUntilEnd();
        } catch(FileNotFoundException fnfe)
        {
                System.out.println(args[0]+" 파일을 찾을 수 없습니다.");
        }
    }
}
