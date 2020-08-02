package pl.kurs.java;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

public class JobOfferService {
//    public static void main(String[] args) {
        /*w
        * Stworz Klase OfertaPracy
klasa zawiera następujące pola:
- miasto
- oferowana pensja
- oczekiwane minimalne doświadczenie
- kolekcja oczekiwanych umiejętności (stringi)
Stworz listę OfertPracy a następnie
- wypisz ofertę która oferuje największą pesję
- ile jest ofert z warszawy
- z jakich miast pochodzą oferty?
- jakie jest średnie minimalne doświadzenie
- która z oczekiwanych umiejętności jest najpopularniejsza?
(możliwośc zdobycia 6 punktów)
* */
//
//        List<JobOffer> jobOffers = new ArrayList<>();
//        jobOffers.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", "skill_2")));
//        jobOffers.add(new JobOffer("Warszawa", 10000, 5, Arrays.asList("skill_3", "skill_5")));
//        jobOffers.add(new JobOffer("Krakow", 50000, 1, Arrays.asList("skill_1", "skill_5")));
//        jobOffers.add(new JobOffer("Krakow", 10000, 8, Arrays.asList("skill_6", "skill_2")));
//        jobOffers.add(new JobOffer("Krakow", 100000, 3, Arrays.asList("skill_8", "skill_2")));
//        jobOffers.add(new JobOffer("Radom", 10000, 1, Arrays.asList("skill_1", "skill_10")));
//        jobOffers.add(new JobOffer("Radom", 50000, 4, Arrays.asList("skill_4", "skill_2", "skill_10")));
//        jobOffers.add(new JobOffer("Radom", 300000, 6, Arrays.asList("skill_4", "skill_7")));
//        jobOffers.add(new JobOffer("Radom", 10000, 2, Arrays.asList("skill_3", "skill_7")));
//        jobOffers.add(new JobOffer("Wroclaw", 43500, 1, Arrays.asList("skill_1", "skill_6")));
//        jobOffers.add(new JobOffer("Wroclaw", 25000, 1, Arrays.asList("skill_7", "skill_4", "skill_10")));
//
//        System.out.println("Largest salary 1:" + maxSalary(jobOffers));
//        System.out.println("Largest salary 2:" + maxSalary(null));
//        System.out.println("Largest salary 3:" + maxSalary(Collections.EMPTY_LIST));
//        System.out.println("Largest salary 4:" + maxSalary(Arrays.asList(jobOffers.get(0), null, jobOffers.get(1))));
//        System.out.println("Largest salary 5:" + maxSalary(Arrays.asList(null, null)));
//        System.out.println("Offers in Warsow: " + countCityOffers(jobOffers, "Warszawa"));
//        System.out.println("cities: " + citiesList(jobOffers));
//        System.out.println("Avg experience: " + avgExperience(jobOffers));
//        System.out.println("Most wanted skill is: " + mostWantedSkill(jobOffers));
//    }

    public static JobOffer maxSalary(List<JobOffer> jobOffers) {
        if (jobOffers == null || jobOffers.isEmpty()) {
            return null;
        }
        return jobOffers.stream().filter(Objects::nonNull).max(Comparator.comparingInt(JobOffer::getSalary)).orElse(null);
    }

    public static long countCityOffers(List<JobOffer> jobOffers, String city){
        if (jobOffers == null || jobOffers.isEmpty()) {
            return 0;
        }
        return jobOffers.stream().filter(Objects::nonNull).filter(f-> f.getCity().toUpperCase().equals(city.toUpperCase())).count();
    }

    public static Set<String> citiesList (List<JobOffer> jobOffers){
        if (jobOffers == null || jobOffers.isEmpty()) {
            return null;
        }
        return jobOffers.stream().filter(Objects::nonNull).map(JobOffer::getCity).filter(Objects::nonNull).
                collect(Collectors.toSet());
    }

    public static double avgExperience (List<JobOffer> jobOffers){
        if (jobOffers == null || jobOffers.isEmpty()) {
            return 0;
        }
        return jobOffers.stream().filter(Objects::nonNull).collect(Collectors.averagingDouble(JobOffer::getExperience));
    }

    public static String mostWantedSkill (List<JobOffer> jobOffers){
        if (jobOffers == null || jobOffers.isEmpty()) {
            return null;
        }

        return jobOffers.stream()
                .filter(Objects::nonNull)
                .map(jobOffer -> jobOffer.getSkillSet())
                .filter(Objects::nonNull)//{[s1, s2],[s1, s3],[s1,s2,s3]}
                .flatMap(skills -> skills.stream())
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(JobOfferService::lCase, Collectors.counting()))
                .entrySet().stream()
                //.max((e1,e2) -> (int) Long.max(e1.getValue(),e2.getValue()))
                .max(Map.Entry.comparingByValue())
                .orElse(null)
                .getKey();

    }

    private static String lCase(String toLower){
        return toLower.toLowerCase();
    }

}

@Data
@AllArgsConstructor
class JobOffer {
    private String city;
    private int salary;
    private int experience;
    private List<String> skillSet;

    public String getCity() {
        return city;
    }

    public int getSalary() {
        return salary;
    }

    public int getExperience() {
        return experience;
    }

    public List<String> getSkillSet() {
        return skillSet;
    }
}