package pl.kurs.java;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class JobOfferServiceTestAvgExperience {
    private final List<JobOffer> jobOffers;
    //private JobOfferService underTest;
    private final double expected;

    public JobOfferServiceTestAvgExperience(List<JobOffer> jobOffers, double expected) {
        this.jobOffers = jobOffers;
        this.expected = expected;
    }

//    @Before
//    public void setUp() throws Exception {
//        underTest = new JobOfferService();
//    }

    @Test
    public void shouldReturnAvgRequiredExperience() {
        assertEquals(expected, JobOfferService.avgExperience(jobOffers),0.0001);
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<JobOffer> jobOffers1 = new ArrayList<>();
        JobOffer best1 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers1.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers1.add(best1);
        double avg1 = 3.5;

        List<JobOffer> jobOffers2 = new ArrayList<>();
        JobOffer best2 = new JobOffer("Warszawa", 50000, 0, Arrays.asList("skill_3", "skill_5"));
        jobOffers2.add(new JobOffer("Krakow", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers2.add(null);
        jobOffers2.add(best2);
        double avg2 = 1.0;

        List<JobOffer> jobOffers3 = new ArrayList<>();
        JobOffer best3 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers3.add(new JobOffer("Warszawa", 10000, 3, Arrays.asList("skill_1", null)));
        jobOffers3.add(new JobOffer("Krakow", 10000, 2, null));
        jobOffers3.add(new JobOffer(null, 0, 2, null));
        jobOffers3.add(null);
        jobOffers3.add(best3);
        double avg3 = 3.0;

        return Arrays.asList(new Object[][]{
                {jobOffers1, avg1},
                {null, 0.0},
                {jobOffers2, avg2},
                {jobOffers3, avg3}
        });
    }

}