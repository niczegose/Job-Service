package pl.kurs.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JobOfferServiceTestCitiesList {
    private final List<JobOffer> jobOffers;
    //private JobOfferService underTest;
    private final Set<String> expected;

    public JobOfferServiceTestCitiesList(List<JobOffer> jobOffers,  Set<String> expected) {
        this.jobOffers = jobOffers;
        this.expected = expected;
    }

//    @Before
//    public void setUp() throws Exception {
//        underTest = new JobOfferService();
//    }

    @Test
    public void shouldReturnListOfCitiesWithOffers() {
        assertEquals(expected, JobOfferService.citiesList(jobOffers));
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<JobOffer> jobOffers1 = new ArrayList<>();
        JobOffer best1 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers1.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers1.add(best1);
        Set<String> city1 = new HashSet<>();
        city1.add("Warszawa");

        List<JobOffer> jobOffers2 = new ArrayList<>();
        JobOffer best2 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers2.add(new JobOffer("Krakow", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers2.add(null);
        jobOffers2.add(best2);
        Set<String> city2 = new HashSet<>();
        city2.add("Warszawa");
        city2.add("Krakow");

        //NULL miasto -> czyli zdalnie, więc co???
        List<JobOffer> jobOffers3 = new ArrayList<>();
        JobOffer best3 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers3.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", null)));
        jobOffers3.add(new JobOffer("Krakow", 10000, 2, null));
        jobOffers3.add(new JobOffer(null, 0, 2, null));
        jobOffers3.add(null);
        jobOffers3.add(best3);
        Set<String> city3 = new HashSet<>();
        city3.add("Warszawa");
        city3.add("Krakow");

        return Arrays.asList(new Object[][]{
                {jobOffers1, city1},
                {null, null},
                {jobOffers2, city2},
                {jobOffers3, city3}
        });
    }

}