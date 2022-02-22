package blocking.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static final int PORT = 5000;

    public static void main(String[] args) throws IOException {
        try (Scanner scanner = new Scanner(System.in)) {
            try (Socket socket = new Socket("localhost", PORT);
                 BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {
                while (true) {
                    System.out.println("Print your message:");
                    final String message = scanner.nextLine();

                    out.write(message + '\n');
                    out.flush();

                    final String s = in.readLine();
                    System.out.println("Answer: " + s);

                    if (message.equals("exit")) {
                        break;
                    }
                }
            }
        }
    }
}
