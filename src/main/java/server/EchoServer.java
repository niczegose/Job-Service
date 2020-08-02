package server;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4444);
        System.out.println("Serwer start");
        while(true){
            Socket klient = ss.accept();
            System.out.println("Polaczyl sie klient: "+klient.getInetAddress().getHostAddress());

            BufferedReader in = new BufferedReader(new InputStreamReader(klient.getInputStream()));
            PrintWriter out = new PrintWriter(klient.getOutputStream(), true);

            String message = in.readLine();
            out.println(message.toUpperCase());

            out.close();
            in.close();
            klient.close();
        }
    }
}
