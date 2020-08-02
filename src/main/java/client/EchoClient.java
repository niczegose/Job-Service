package client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    public static void main(String[] args) throws Exception {
        Scanner consoleIn= new Scanner(System.in);

        Socket conn = new Socket("localhost", 4444);
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
        System.out.println("wpisz cos");
        String message = consoleIn.nextLine();

        out.println(message);
        String response = in.readLine();

        System.out.println("response: "+response);
        out.close();
        in.close();
        conn.close();
    }
}
/*
MathServer
- umiec rozwaizac dowolnego typu rownania np: ((2 + 2) * 2) - 5
- obsluga bledow: invalid format
- gromadzi historie wykonanych zapytan
- *laczy sie jakis "admin" ktory dostaje historie wykonaych dzialanian (data/dzialanie)
MathClient
- przymuje input z consoli od klienta
 */
