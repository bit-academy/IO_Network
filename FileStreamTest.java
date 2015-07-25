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
            fos=new FileOutputStream("���纻");

            for (int i; (i=fis.read(buf))!=-1; )
            {
                    System.out.write(buf, 0, i);
                    fos.write(buf, 0, i);
            }
        } catch(IOException ioe)
        {
            System.out.println("��Ʈ���� �̻��� ���� : "+ioe.getMessage());
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
            System.out.println("���� : java FileTest1 �����̸�");
            System.exit(1);
        }

        try
        {
            FileStreamTest test=new FileStreamTest(args[0]);
            test.readUntilEnd();
        } catch(FileNotFoundException fnfe)
        {
                System.out.println(args[0]+" ������ ã�� �� �����ϴ�.");
        }
    }
}
