import java.io.*;
import java.nio.*;
import java.nio.channels.*;

public class FileChannelTest1
{
    public static void main(String[] args) throws Exception
    {
        RandomAccessFile raf=new RandomAccessFile("test.file", "rw");
        FileChannel fc=raf.getChannel();
        String str="123멀린456";
        byte[] bytes=str.getBytes();
        char[] chars=str.toCharArray();
        System.out.println("bytes 배열의 길이 : "+bytes.length);
        System.out.println("chars 배열의 길이 : "+chars.length);

        ByteBuffer bBuf1=ByteBuffer.allocate(40);
        CharBuffer cBuf1=bBuf1.asCharBuffer();
        bBuf1.put(bytes);
        bBuf1.flip();
        System.out.println(bBuf1);

        bBuf1.limit(bBuf1.limit()+chars.length*2);
        System.out.println(bBuf1);

        cBuf1.position(bytes.length/2);
        cBuf1.put(str.toCharArray());
        System.out.println("cBuf1 : pos="+cBuf1.position()+", lim="+cBuf1.limit()+", cap="+cBuf1.capacity());

        fc.write(bBuf1);

        ByteBuffer bBuf2=ByteBuffer.allocate(40);
        CharBuffer cBuf2=bBuf2.asCharBuffer();
        bBuf2.limit(bBuf1.limit());

        fc.position(0);
        fc.read(bBuf2);
        System.out.println(bBuf2);
        cBuf2.position(5).limit(13);
        System.out.println(cBuf2);
    }
}
