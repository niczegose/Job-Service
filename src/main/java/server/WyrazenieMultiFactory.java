package server;

import java.security.InvalidParameterException;
import java.util.Arrays;

public class WyrazenieMultiFactory {

    public static WyrazenieMulti stworzNaPodstawieLinii(String linia) throws IllegalArgumentException, NumberFormatException, InvalidParameterException, ArithmeticException {
        String[] tab =  waliduj(linia);
        return new WyrazenieMulti(tab);
    }

    private static String[] waliduj(String linia) {
        String[] tab = linia.split(" ");
        for (int i = 0; i < tab.length; i++) {
            if (i % 2 == 0) {
                double liczba = setLiczba(tab[i]);
                if (liczba == 0 && i != 0 && tab[i - 1].equals("/")) {
                    throw new ArithmeticException("Dzielenie przez 0");
                }
            } else {
                if (!isArithmetic(tab[i])) {
                    throw new IllegalArgumentException("Niepoprawny operator wyrazenia: " + tab[1]);
                }
            }
        }
        return tab;
    }

    private static double setLiczba(String liczba) {
        double wynik;
        try {
            wynik = Double.parseDouble(liczba);
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Niepoprawna liczba: " + liczba);
        }
        return wynik;
    }

    private static boolean isArithmetic(String str) {
        return Arrays.asList("+", "-", "*", "/").contains(str);
    }
}
