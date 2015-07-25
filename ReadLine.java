import java.io.*;

public class ReadLine
{
    public static void main(String[] args) throws IOException
    {
        File imsiFile=File.createTempFile("imsi", ".tmp");
        imsiFile.deleteOnExit();

        FileWriter fw=new FileWriter(imsiFile);
        BufferedWriter bw=new BufferedWriter(fw);

        for (int i=0; i<args.length; i++)
        {
            bw.write(args[i]);
            bw.newLine();
        }

        bw.close();
        System.out.println(imsiFile+" 에 썼습니다.");

        FileReader fr=new FileReader(imsiFile);
        BufferedReader br=new BufferedReader(fr);

        System.out.println(imsiFile+" 에서 읽어 들입니다.");
        for (String readLine; (readLine=br.readLine())!=null; )
        {
            System.out.println(readLine);
        }
        br.close();
    }
}
