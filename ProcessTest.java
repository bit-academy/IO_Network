import java.io.*;

public class ProcessTest
{
    public static void main(String[] args) throws IOException
    {
        if (args.length<1)
        {
            System.out.println("사용법 : java ProcessTest 프로그램이름");
            System.exit(1);
        }

        Runtime rt=Runtime.getRuntime();
        Process p=rt.exec(args);
        InputStream is=p.getInputStream();
        BufferedReader br=new BufferedReader(new InputStreamReader(is));

        System.out.println(args[0]+" 프로세스로부터의 출력");
        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        for (String str; (str=br.readLine())!=null; )
        {
            System.out.println(str);
        }

        System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
        System.out.println("프로세스 종료코드 : "+p.exitValue());
    }
}
