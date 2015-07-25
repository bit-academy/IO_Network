import java.util.*;
import java.nio.charset.*;

public class CharsetTest1
{
    public static void main(String[] args) throws Exception
    {
        System.out.println(System.getProperty("java.vm.vendor"));
        System.out.println(System.getProperty("java.vm.version"));

        Map map=Charset.availableCharsets();
        Set keys=map.keySet();
        Iterator iter=keys.iterator();
        while(iter.hasNext())
        {
            System.out.println(iter.next());
        }
    }
}