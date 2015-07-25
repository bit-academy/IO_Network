import java.io.*;

public class FileTest implements FileFilter
{
    File file;

    public FileTest(String fileName)
    {
            file=new File(fileName);
    }

    public void test() throws Exception
    {
        if (!file.exists())
        {
                System.out.println("������ �������� �ʽ��ϴ�. "+file);
                System.exit(1);
        }

        System.out.println("������ : "+file.getAbsolutePath());
        System.out.println("���İ�� : "+file.getCanonicalPath());
        System.out.println("����ũ�� : "+file.length());
        System.out.println("���糯¥ : "+new java.util.Date(file.lastModified()));
        System.out.println("��Ʈ��ũ��� : "+file.toURL());

        if (file.isDirectory())
        {
            File[] list=file.listFiles(this);

            System.out.println("\n���丮 ���� ���� ---");
            for (int i=0; i<list.length; i++)
            {
                System.out.print(list[i].getName());
                System.out.print("\t");
                System.out.println(list[i].length());
            }
        }

        System.out.println("\n���Ͻý���");
        File[] roots=File.listRoots();
        for (int i=0; i<roots.length; i++)
        {
            System.out.println(roots[i].getPath());
        }
    }

    public boolean accept(File path)
    {
        return path.isFile();
    }

    public static void main(String[] args) throws Exception
    {
        if (args.length<1)
        {
            System.out.println("���� : java FileTest �����̸�");
            System.exit(1);
        }
        FileTest test=new FileTest(args[0]);
        test.test();
    }
}
