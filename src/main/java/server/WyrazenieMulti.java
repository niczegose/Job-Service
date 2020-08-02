package server;

import java.util.ArrayList;
import java.util.List;

public class WyrazenieMulti {

    private final List<Point> listaPunktow;

    public WyrazenieMulti(String[] tab) {
        listaPunktow = new ArrayList<>();
        for (int i = 0; i < tab.length; i++) {
            if (i % 2 == 0) {
                String operator;
                if (i == tab.length - 1) {
                    operator = "";
                } else {
                    operator = tab[i + 1];
                }
                listaPunktow.add(new Point(Double.parseDouble(tab[i]), operator));
            }
        }
    }

    public List<Point> getListaPunktow() {
        return listaPunktow;
    }
}

class Point {
    private final double liczba;
    private final String operator;
    private final int priority;

    public Point(double liczba, String operator) {
        this.liczba = liczba;
        this.operator = operator;
        this.priority=setPriority(operator);
    }

    private int setPriority(String operator) {
        if (operator.equals("*") || operator.equals("/")){
            return  1;
        }
        else if (operator.equals("+") || operator.equals("-")){
            return 2;
        }
        return 3;
    }

    public double getLiczba() {
        return liczba;
    }

    public String getOperator() {
        return operator;
    }

    public int getPriority() {
        return priority;
    }

}