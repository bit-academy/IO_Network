import java.io.*;
import java.util.*;

public class ObserverTest extends Observable
{
    public static void main(String[] args) throws Exception
    {
        ObserverTest oo=new ObserverTest();
        oo.addObserver(new MyObserver());

        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        String str;
        System.out.println("입력하세요...");

        while((str=br.readLine())!=null)
        {
            oo.setChanged();
            oo.notifyObservers(str);
        }
    }

    public String toString()
    {
        return "ObserverTest";
    }
}

class MyObserver implements Observer
{
    public void update(Observable o, Object arg)
    {
        System.out.println(o+" 에서 메시지가 왔음 : "+arg);
    }
}
