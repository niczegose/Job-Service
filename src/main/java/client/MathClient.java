package client;

import server.Expressions;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class MathClient {
    public static void main(String[] args) throws Exception {
        Scanner consoleIn = new Scanner(System.in);
        Socket conn = new Socket("localhost", 4444);

        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        PrintWriter out = new PrintWriter(conn.getOutputStream(), true);
        ObjectInputStream objectIn = new ObjectInputStream(conn.getInputStream());

        System.out.println("admin - A or  user - U?");
        String message = consoleIn.nextLine();
        out.println(message);

        String response;

        if (message.toUpperCase().equals("U")) {
            System.out.println("wpisz rownanie");
            message = consoleIn.nextLine();
            out.println(message);
            response = in.readLine();
            System.out.println("wynik: " + response);
        } else if (message.toUpperCase().equals("A")) {
            System.out.println("question list over time:");
            Expressions expressions = (Expressions) objectIn.readObject();
            expressions.allQuestions().forEach(System.out::println);
        }

        out.close();
        in.close();
        conn.close();
    }
}
