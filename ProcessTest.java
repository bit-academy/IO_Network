import java.io.*;

public class ProcessTest
{
    public static void main(String[] args) throws IOException
    {
        if (args.length<1)
        {
            System.out.println("���� : java ProcessTest ���α׷��̸�");
            System.exit(1);
        }

        Runtime rt=Runtime.getRuntime();
        Process p=rt.exec(args);
        InputStream is=p.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is));

        System.out.println(args[0]+" ���μ����κ����� ���");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for (String str; (str=br.readLine())!=null; )
        {
            System.out.println(str);
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("���μ��� �����ڵ� : "+p.exitValue());
    }
}
