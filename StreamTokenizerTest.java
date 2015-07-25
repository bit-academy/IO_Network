import java.io.*;

public class StreamTokenizerTest
{
    public static void main(String[] args) throws Exception
    {
        FileReader reader=new FileReader(args[0]);
        StreamTokenizer tokenizer=new StreamTokenizer(reader);

        while(tokenizer.nextToken() != tokenizer.TT_EOF)
        {
            switch(tokenizer.ttype)
            {
                case StreamTokenizer.TT_WORD:
                    System.out.print("TT_WORD : ");
                    System.out.println(tokenizer.sval);
                    break;

                case StreamTokenizer.TT_NUMBER:
                    System.out.print("TT_NUMBER : ");
                    System.out.println(tokenizer.nval);
            }
        }
    }
}
