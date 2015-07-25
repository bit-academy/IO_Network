import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class FileChannelTest2
{
    public static void main(String[] args) throws IOException
    {
        if (args.length<3)
        {
            System.out.println("사용법 : java FileChannelTest2 method번호 Source Target");
            System.out.println("method [1] - java.io");
            System.out.println("method [2] - java.nio.FileChannel");
            System.out.println("method [3] - java.nio.FileChannel.transferXX()");
            System.exit(1);
        }

        int method=Integer.parseInt(args[0]);
        double duration=0.0;
        FileInputStream source=new FileInputStream(args[1]);
        FileOutputStream target=new FileOutputStream(args[2]);

        if (method==1)
        {
            duration=copyIO(source, target)/1000.0;
        } else if (method==2)
        {
            duration=copyNIO(source, target)/1000.0;
        } else if (method==3)
        {
            duration=copyTransfer(source, target)/1000.0;
        } else
        {
            System.exit(1);
        }

        System.out.println("method "+method+" 로 파일복사 소요시간(초) : "+duration);
    }

    static long copyIO(FileInputStream source, FileOutputStream target) throws IOException
    {
        BufferedInputStream bis=new BufferedInputStream(source);
        BufferedOutputStream bos=new BufferedOutputStream(target);

        byte[] buf=new byte[1024000];
        long startTime=System.currentTimeMillis();

        for (int len; (len=bis.read(buf))!=-1; )
        {
            bos.write(buf, 0, len);
        }
        bos.close();

        return System.currentTimeMillis()-startTime;
    }

    static long copyNIO(FileInputStream source, FileOutputStream target) throws IOException
    {
        FileChannel input=source.getChannel();
        FileChannel output=target.getChannel();

        ByteBuffer buf=ByteBuffer.allocateDirect(1024000);
        long startTime=System.currentTimeMillis();

        for (int len; (len=input.read(buf))!=-1; )
        {
            buf.flip();
            output.write(buf);
            buf.clear();
        }
        output.close();

        return System.currentTimeMillis()-startTime;
    }

    static long copyTransfer(FileInputStream source, FileOutputStream target) throws IOException
    {
        FileChannel input=source.getChannel();
        FileChannel output=target.getChannel();

        long startTime=System.currentTimeMillis();

        input.transferTo(0, input.size(), output);
        output.close();

        return System.currentTimeMillis()-startTime;
    }
}
