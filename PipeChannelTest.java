import java.nio.*;
import java.nio.channels.*;

public class PipeChannelTest
{
    public static void main(String[] args) throws Exception
    {
        Pipe pipe=Pipe.open();
        Pipe.SinkChannel sink=pipe.sink();
        Pipe.SourceChannel source=pipe.source();

        DataSender sender=new DataSender(sink);
        DataReceiver receiver=new DataReceiver(source);

        System.out.println("Pipe.SinkChannel 의 validOps() : "+sink.validOps());
        System.out.println("Pipe.SourceChannel 의 validOps() : "+source.validOps());

        sender.start();
        receiver.start();
    }

    static class DataSender extends Thread
    {
        Pipe.SinkChannel sink;

        DataSender(Pipe.SinkChannel sink)
        {
            this.sink=sink;
        }

        public void run()
        {
            ByteBuffer byteBuf=ByteBuffer.allocate(8);
            LongBuffer longBuf=byteBuf.asLongBuffer();

            while(true)
            {
                long time=System.currentTimeMillis();
                longBuf.put(0, time);

                try
                {
                    System.out.println("데이터 보냄");
                    sink.write(byteBuf);
                    Thread.sleep(500);
                } catch(Exception e)
                {
                    e.printStackTrace();
                } finally
                {
                    byteBuf.clear();
                }
            }
        }
    }

    static class DataReceiver extends Thread
    {
        Pipe.SourceChannel source;

        DataReceiver(Pipe.SourceChannel source)
        {
            this.source=source;
        }

        public void run()
        {
            ByteBuffer byteBuf=ByteBuffer.allocate(8);
            LongBuffer longBuf=byteBuf.asLongBuffer();

            while(true)
            {
                try
                {
                    source.read(byteBuf);
                    System.out.print("데이터 받음 : ");
                    System.out.println(longBuf.get(0));
                } catch(Exception e)
                {
                    e.printStackTrace();
                }

                byteBuf.clear();
            }
        }
    }
}
