package blocking.socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import logic.Calculator;

public class Server {

    private static final int TIMEOUT = 5000;
    private static final int PORT = 5000;

    private final int port;

    public Server(int port) {
        this.port = port;
    }

    public void run() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket socket = serverSocket.accept();
                socket.setSoTimeout(TIMEOUT);
                new Thread(() -> handleSocket(socket)).start();
            }
        }
    }

    private void handleSocket(Socket socket) {
        try (socket;
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            while (true) {
                String input;
                try {
                    input = in.readLine();
                } catch (SocketTimeoutException e) {
                    System.out.println("Timeout!!!");
                    break;
                }

                if (input.equals("exit")) {
                    out.write("good bye!\n");
                    out.flush();
                    break;
                }

                String output;
                try {
                    int k = Integer.parseInt(input);
                    long fib = Calculator.fib(k);
                    output = String.valueOf(fib);
                } catch (NumberFormatException e) {
                    output = "Error format: " + input + "\n";
                }

                out.write(output + "\n");
                out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new Server(PORT).run();
    }
}
