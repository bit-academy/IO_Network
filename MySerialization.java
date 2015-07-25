import java.io.*;

class UnserializableClass
{
	int j;
}

public class MySerialization extends UnserializableClass implements Serializable
{
        int i;

        public MySerialization(int i)
        {
                this.i=i;
                j=i*2;
        }

        private void writeObject(java.io.ObjectOutputStream out)  throws IOException
        {
                System.out.println("writeObject");
                out.defaultWriteObject();
                out.writeInt(j);
        }

        private void readObject(ObjectInputStream in) throws IOException,

ClassNotFoundException
        {
                System.out.println("readObject");
                in.defaultReadObject();
                j=in.readInt();
        }

        public String toString()
        {
                return i+", "+j;
        }

        public static void main(String[] args) throws Exception
        {
                FileOutputStream fos=new FileOutputStream("_imsi");
                ObjectOutputStream oos=new ObjectOutputStream(fos);
                oos.writeObject(new MySerialization(1000));
                oos.close();

                FileInputStream fis=new FileInputStream("_imsi");
                ObjectInputStream ois=new ObjectInputStream(fis);
                MySerialization ser=(MySerialization)ois.readObject();
                ois.close();
                System.out.println("read : "+ser);
        }
}
