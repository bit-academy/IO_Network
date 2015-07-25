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
                System.out.println("파일이 존재하지 않습니다. "+file);
                System.exit(1);
        }

        System.out.println("절대경로 : "+file.getAbsolutePath());
        System.out.println("공식경로 : "+file.getCanonicalPath());
        System.out.println("파일크기 : "+file.length());
        System.out.println("만든날짜 : "+new java.util.Date(file.lastModified()));
        System.out.println("네트워크경로 : "+file.toURL());

        if (file.isDirectory())
        {
            File[] list=file.listFiles(this);

            System.out.println("\n디렉토리 안의 내용 ---");
            for (int i=0; i<list.length; i++)
            {
                System.out.print(list[i].getName());
                System.out.print("\t");
                System.out.println(list[i].length());
            }
        }

        System.out.println("\n파일시스템");
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
            System.out.println("사용법 : java FileTest 파일이름");
            System.exit(1);
        }
        FileTest test=new FileTest(args[0]);
        test.test();
    }
}
