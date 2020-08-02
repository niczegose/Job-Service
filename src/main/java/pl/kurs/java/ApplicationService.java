package pl.kurs.java;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationService {
//    2) Stworz klasę Zgłoszenie
//    klasa zawiera następujące pola
//- imię i nazwisko kandydata
//- jego oczekiwania finansowe
//- miasto skąd pochodzi
//- telefon kontaktowy
//- chętny do relokacji (true/false)
//- email z którego nadeszło zgłoszenie
//- posiadane doświadczenie
//- kolekcja posiadanych umiejętności
//    Stwórz listę Zgłoszeń a następnie
//- ile jest zgłoszeń dla każdego z miast?
//            - ile jest zgłoszeń dla każdego z email? (być może kandydat z jednego adresu email wysłał kilka aplikacji)
//            - która z umiejętnośc jest posiadana najczęściej?
//            - wykonaj wstępnej weryfikacji listy zgłoszeń, tzn: jeśli z tego samego adresu email przyszły zgłoszenia dwóch różnnych osób (różne imie i nazwisko) to znaczy że coś jest nie tak, wypisz wszystkie adresy email które sie nie zgadzają.
//            (możliwośc zdobycia 6 ptk, ostatnie jest za 2 punkty)

    public List<String> alreadyUsedEmail(List<Application> applicationList){
        if (applicationList == null || applicationList.isEmpty()) {
            return null;
        }

        return  applicationList.stream().filter(Objects::nonNull)
               .collect(Collectors.groupingBy(Application::getEmail,Collectors.toList())).values()
                .stream().filter(list-> list.size()>=2)
                .filter(ApplicationService::wrongEmails)
                .flatMap(List::stream)
                .map(ap-> ap.getEmail())
                .collect(Collectors.toSet()).stream()
                .collect(Collectors.toList());

    }

    private static boolean wrongEmails(List<Application> applications){
        for (int i = 0; i < applications.size()-1; i++) {
            Application first= applications.get(i);
            Application second=applications.get(i+1);
            if(!first.getName().equals(second.getName()) || !first.getSurname().equals(second.getSurname())){
                return true;
            }
        }
        return false;
    }

    public Map<String, Long> countApplicationsByCities(List<Application> applicationList) {
        if (applicationList == null || applicationList.isEmpty()) {
            return null;
        }
        return applicationList.stream().filter(Objects::nonNull)
                .filter(e-> e.getCity()!=null)
                .collect(Collectors.groupingBy(Application::getCity, Collectors.counting()));
    }

    public Map<String, Long> countApplicationsByEmails(List<Application> applicationList) {
        if (applicationList == null || applicationList.isEmpty()) {
            return null;
        }
        return applicationList.stream().filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Application::getEmail, Collectors.counting()));
    }
    public String mostPopularSkill(List<Application> applicationList){
        if (applicationList == null || applicationList.isEmpty()) {
            return null;
        }

        return applicationList.stream().filter(Objects::nonNull)
                .map(Application::getSkillSet)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }


}
