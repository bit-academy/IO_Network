import java.io.*;

public class ExternalizableTest implements Externalizable
{
    String name;
    int i;

    public ExternalizableTest()
    {
    }

    public ExternalizableTest(String name, int i)
    {
        this.name=name;
        this.i=i;
    }

    public void readExternal(ObjectInput stream) throws IOException
    {
        System.out.println("readExternal()");
        try
        {
            name=(String)stream.readObject();
        } catch(ClassNotFoundException cnfe)
        {
        }
    }

    public void writeExternal(ObjectOutput stream) throws IOException
    {
        System.out.println("writeExternal()");
        stream.writeObject(name);
    }

    public String toString()
    {
        return "이름은 "+name+" 이며, 값은 "+i;
    }

    public static void main(String[] args) throws Exception
    {
        ExternalizableTest ext1=new ExternalizableTest("테스트객체", 1000);

        FileOutputStream out=new FileOutputStream("_imsi");
        ObjectOutputStream oos=new ObjectOutputStream(out);
        oos.writeObject(ext1);

        FileInputStream in=new FileInputStream("_imsi");
        ObjectInputStream ois=new ObjectInputStream(in);
        ExternalizableTest ext2=(ExternalizableTest)ois.readObject();

        System.out.println("객체직렬화 대상 객체 : "+ext1);
        System.out.println("객체직렬화로 복원한 객체 : "+ext2);
    }
}
