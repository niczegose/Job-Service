package pl.kurs.java;

import java.util.*;
import java.util.stream.Collectors;

public class ApplicationMatcher {
//    3)
//    Dla OfertPracy może przyjść wiele zgłoszeń, a jedno zgłoszenie może byc
//    dla wielu ofert pracy. Będąc rekruterem masz nielada gratkę aby ogarnąc 5 tyś CV na skrzynce pocztowej, dlatego zostałeś poproszony o napisanie prostego algorytmu który by dla każdej oferty wybrał by JEDNEGO (póki co) najlepszego kandydata.
//    Najlepsze dopasowanie będzie na podstawie ilości punktów oraz pewnych warunów "must have"
//    a) oferty z miasta X mogą brać pod uwagę tylko kandydatów z miasta X lub kandydatów z innego miasta jeśli kandydat bierze pod uwagę relokację.
//    b) oczekiwania kandydata nie mogą być wyższe niż stawka w ofercie pracy, chyba że kandydat posiada wszystkie wymagane umiejętności w swoim zgłoszeniu, wtedy możemy dopóścić limit 120% wynagrodzenia z ogłoszenia, ale nie więcej.
//            c) dodatkowe punkty przyznawane są za każdo razowe dopasowanie technologii
//    tzn: jeśli oferta wymaga: Java, Spring, Hibernate, Junit, a kandydat zna: Java, Spring, Hibernate to mamy 75% dopasowania.
//    napisz metodę public Zgloszenie znajdzNajlepsze(OfertaPracy oferta, List<Zgloszenie> zgloszenia) ktora dla podanej oferty znajdze najlepiej dopasowane zgloszenie lub zroci null jesli nie ma zadego dopasowanego zgloszenia*/

    public Application findBest(JobOffer offer, List<Application> applications) {
        if (applications == null || applications.isEmpty()) {
            return null;
        }
        List<Application> filtered =applications.stream()
                .filter(Objects::nonNull)
                .filter(a -> a.getCity() == null || a.getCity().equals(offer.getCity()) || a.isAcceptRelocation())
                .filter(a -> a.getFinancialExpectation() == offer.getSalary() || superEmployee(a, offer))
                .collect(Collectors.toList());



        Map<Application, Double> queuedApplications = new HashMap<>();

        for (Application app : filtered) {
            queuedApplications.put(app, setPoints(app, offer));
        }

        double maxPoints = queuedApplications.entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .get().getValue();

        return queuedApplications.entrySet().stream()
                .filter(e-> e.getValue()==maxPoints)
                .map(Map.Entry::getKey)
                .min(Comparator.comparingDouble(Application::getFinancialExpectation))
                .orElse(null);
    }

    private boolean superEmployee(Application application, JobOffer offer) {
        if (offer.getSkillSet() == null) {
            return true;
        }
        if (application.getSkillSet() == null) {
            return false;
        }
        return equalSkillsCounter(application, offer) == offer.getSkillSet().size();
    }

    private Double setPoints(Application application, JobOffer offer) {
        return (double) equalSkillsCounter(application, offer) / offer.getSkillSet().size();
    }

    private long equalSkillsCounter(Application app, JobOffer offer) {
        return offer.getSkillSet().stream().filter(s -> app.getSkillSet().contains(s)).count();
    }
}
