import java.io.*;

public class ReadCharacter
{
    public static void main(String[] args) throws IOException
    {
        InputStreamReader reader=new InputStreamReader(System.in);

        for (int i; (i=reader.read())!=-1; )
        {
            System.out.print((char)i);
        }
    }
}
