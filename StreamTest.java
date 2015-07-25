import java.io.*;

public class StreamTest
{
    public static void main(String[] args) throws IOException
    {
        System.out.println(System.in);
        System.out.println(System.out);

        for (int input; (input=System.in.read())!=-1; )
        {
            if (input=='x')
            {
                System.exit(0);
            }

            System.out.print("ют╥б : ");

            if (input!=10 && input!=13)
            {
                System.out.write(input);
                System.out.print(", ");
            }

            System.out.println(input);
        }
    }
}
