import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;

public class ChatDataExchanger {
    ExchangeThread readThread, writeThread;

    public ChatDataExchanger(InputStream inputA, OutputStream outputA,
            InputStream inputB, OutputStream outputB) throws IOException {
        readThread = new ExchangeThread(inputA, outputB);
        readThread.start();
        writeThread = new ExchangeThread(inputB, outputA);
        writeThread.start();
    }

    class ExchangeThread extends Thread {
        BufferedReader reader;
        PrintStream writer;

        ExchangeThread(InputStream input, OutputStream output)
                throws IOException {
            reader = new BufferedReader(new InputStreamReader(input));

            if (output == System.out) {
                writer = (PrintStream) output;
            } else {
                writer = new PrintStream(output, true);
            }
        }

        public void run() {
            try {
                while (true) {
                    for (String str; (str = reader.readLine()) != null;) {
                        if (writer == System.out) {
                            writer.print("[상대방] : ");
                        }
                        writer.println(str);
                    }
                }
            } catch (Exception e) {
                System.err.println("채팅데이터를 읽어오는 중 예외가 발생했습니다.");
                e.printStackTrace();
            }
        }
    }
}
