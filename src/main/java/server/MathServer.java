package server;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.ToString;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MathServer {
    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(4444);
        System.out.println("Serwer start");

        List<SimpleQuestion> questionList = Collections.synchronizedList(new ArrayList<>());

        while (true) {
            new LonelyClient(ss.accept(), questionList).start();
        }
    }
}

class LonelyClient extends Thread {

    private Socket klient;
    private BufferedReader in;
    private PrintWriter out;
    private List<SimpleQuestion> questionList;
    private ObjectOutputStream objectOut;

    public LonelyClient(Socket klient, List<SimpleQuestion> questionList) throws IOException {
        this.klient = klient;
        this.questionList = questionList;
        in = new BufferedReader(new InputStreamReader(klient.getInputStream()));
        out = new PrintWriter(klient.getOutputStream(), true);
        objectOut =new ObjectOutputStream(klient.getOutputStream());
    }

    @SneakyThrows
    @Override
    public void run() {
        System.out.println("Polaczyl sie klient: " + klient.getInetAddress().getHostAddress());

        String message = in.readLine();
        //System.out.println(message);
        if (message.toUpperCase().equals("U")) {
            message = in.readLine();
            questionList.add(new SimpleQuestion(message, LocalDateTime.now()));
            try {
                Equation.isNawias(message);
                out.println(Equation.obliczWyrazenie(message));

            } catch (Exception e) {
                //System.out.println(e.getMessage());
                out.println("Niepoprawne dane wejściowe, spróbuj jeszcze raz");
            }
        } else if (message.toUpperCase().equals("A")) {
            //questionList.forEach(out::println);
            objectOut.writeObject(Expressions.findAllQuestions(questionList));
        }

        //out.println(message.toUpperCase());

        out.close();
        in.close();
        klient.close();
    }
}


