import javax.sound.sampled.*;

public class PortTest
{
    public static void main(String[] args) throws Exception
    {
        printPort(Port.Info.COMPACT_DISC);
        printPort(Port.Info.HEADPHONE);
        printPort(Port.Info.LINE_IN);
        printPort(Port.Info.LINE_OUT);
        printPort(Port.Info.MICROPHONE);
        printPort(Port.Info.SPEAKER);
    }

    static void printPort(Port.Info info) throws Exception
    {
        System.out.println(info);
        System.out.println(info.isSource() ? "SourceDataLine" : "TargetDataLine");

        try
        {
            Port port=(Port)AudioSystem.getLine(info);
            System.out.println(port);
        } catch(Exception e)
        {
            System.out.println(" ## Exception : "+e.getMessage());
        }
        System.out.println();
    }
}
