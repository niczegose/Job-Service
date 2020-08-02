package pl.kurs.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JobOfferServiceTestCountCityOffers {
    private final List<JobOffer> jobOffers;
    //private JobOfferService underTest;
    private final long expected;
    private final String city;

    public JobOfferServiceTestCountCityOffers(List<JobOffer> jobOffers, String city, long expected) {
        this.jobOffers = jobOffers;
        this.expected = expected;
        this.city = city;
    }

//    @Before
//    public void setUp() throws Exception {
//        underTest = new JobOfferService();
//    }

    @Test
    public void shouldReturnSumInCity() {
        assertEquals(expected, JobOfferService.countCityOffers(jobOffers, city));
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<JobOffer> jobOffers1 = new ArrayList<>();
        JobOffer best1 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers1.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers1.add(best1);
        String city1 = "Warszawa";

        List<JobOffer> jobOffers2 = new ArrayList<>();
        JobOffer best2 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers2.add(new JobOffer("Krakow", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers2.add(null);
        jobOffers2.add(best2);
        String city2 = "Warszawa";

        List<JobOffer> jobOffers3 = new ArrayList<>();
        JobOffer best3 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers3.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", null)));
        jobOffers3.add(new JobOffer("Krakow", 10000, 2, null));
        jobOffers3.add(new JobOffer("Warszawa", 0, 2, null));
        jobOffers3.add(null);
        jobOffers3.add(best3);
        String city3 = "Warszawa";

        return Arrays.asList(new Object[][]{
                {jobOffers1, city1, 2},
                {null, city1, 0},
                {jobOffers2, city2, 1},
                {jobOffers3, city3, 3}
        });
    }

}