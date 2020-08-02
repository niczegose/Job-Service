package server;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Equation {
    //+,-,*,/
    // zlozone wyrazenie z uwzglednieniem kolejnosci wykonywania dzialan oraz nawiasami

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        boolean ok = false;
        String wyrazenie;
        String wynik = "";

        while (!ok) {
            System.out.println("Podaj wyrazenie arytmetyczne");
            wyrazenie = sc.nextLine();
            ok = true;
            try {
                isNawias(wyrazenie);
                wynik = obliczWyrazenie(wyrazenie);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Niepoprawne dane wejściowe, spróbuj jeszcze raz");
                ok = false;
            }
        }

        System.out.println("Wynik: " + wynik);
    }

    public static String obliczWyrazenie(String wyrazenie) {
        Pattern pattern = Pattern.compile("(\\(([^\\(]*?)\\))");
        String tmpWyrazenie = wyrazenie;
        Matcher matcher = pattern.matcher(tmpWyrazenie);
        while(matcher.find()){ //dopoki matcher bedzie znajdywal dopasowanie
            tmpWyrazenie = tmpWyrazenie.replace(matcher.group(1), obliczWyrazenie(matcher.group(2)));
            //System.out.println(dopasowanie);
        }
        matcher = pattern.matcher(tmpWyrazenie);
        if(matcher.find()){
            tmpWyrazenie=obliczWyrazenie(tmpWyrazenie);
        }
        return "" + oblicz(WyrazenieMultiFactory.stworzNaPodstawieLinii(tmpWyrazenie).getListaPunktow());
    }

    public static void isNawias(String wyrazenie) {
        int liczNawiasy = 0;
        for (int i = 0; i < wyrazenie.length(); i++) {
            if (wyrazenie.charAt(i) == '(') {
                liczNawiasy++;
            } else if (wyrazenie.charAt(i) == ')') {
                liczNawiasy--;
            }
            if (liczNawiasy < 0) {
                throw new IllegalArgumentException("źle dobrane nawiasy");
            }
        }
        if (liczNawiasy != 0) {
            throw new IllegalArgumentException("źle dobrane nawiasy");
        }
    }

    public static double oblicz(List<Point> list) {
        for (int i = 1; i < 3; i++) {
            obliczListeWgPriorytetu(list, i);
        }
        return list.get(0).getLiczba();
    }

    public static void obliczListeWgPriorytetu(List<Point> list, int priorytet) {
        Map<String, Operator> operators = new HashMap<>();
        operators.put("+", (a, b) -> a + b);
        operators.put("-", (a, b) -> a - b);
        operators.put("*", (a, b) -> a * b);
        operators.put("/", (a, b) -> a / b);

        int minPriority = priorytet;
        while (minPriority <= priorytet) {
            minPriority = 3;
            Point firstTemp;
            Point secondTemp;
            for (int i = 0; i < list.size(); i++) {
                firstTemp = list.get(i);
                if (firstTemp.getPriority() < minPriority) {
                    minPriority = firstTemp.getPriority();
                }
                if (firstTemp.getPriority() == priorytet) {
                    secondTemp = list.get(i + 1);
                    list.set(i, new Point(operators.get(firstTemp.getOperator()).calculate(firstTemp.getLiczba(), secondTemp.getLiczba()), secondTemp.getOperator()));
                    list.remove(i + 1);
                    break;
                }
            }
        }
    }
}
