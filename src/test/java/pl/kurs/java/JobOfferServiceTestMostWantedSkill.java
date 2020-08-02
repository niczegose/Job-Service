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
public class JobOfferServiceTestMostWantedSkill {
    private final List<JobOffer> jobOffers;
    //private JobOfferService underTest;
    private final String expected;

    public JobOfferServiceTestMostWantedSkill(List<JobOffer> jobOffers, String expected) {
        this.jobOffers = jobOffers;
        this.expected = expected;
    }

//    @Before
//    public void setUp() throws Exception {
//        underTest = new JobOfferService();
//    }

    @Test
    public void shouldReturnAvgRequiredExperience() {
        assertEquals(expected, JobOfferService.mostWantedSkill(jobOffers));
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<JobOffer> jobOffers1 = new ArrayList<>();
        JobOffer best1 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_5", "skill_3"));
        jobOffers1.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_3", "skill_2")));
        jobOffers1.add(best1);
        String most1 = "skill_3";

        List<JobOffer> jobOffers2 = new ArrayList<>();
        JobOffer best2 = new JobOffer("Warszawa", 50000, 0, Arrays.asList("skill_5", "skill_5"));
        jobOffers2.add(new JobOffer("Krakow", 10000, 2, Arrays.asList("skill_8", "skill_5")));
        //jobOffers2.add(null);
        //jobOffers2.add(new JobOffer(null, 1520, 3, Arrays.asList("skill_5", "skill_2")));
        jobOffers2.add(new JobOffer("Krakow", 1520, 3, Arrays.asList("skill_5", "skill_9")));
        jobOffers2.add(best2);
        String most2 = "skill_5";

        List<JobOffer> jobOffers3 = new ArrayList<>();
        JobOffer best3 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers3.add(new JobOffer("Warszawa", 10000, 3, Arrays.asList("skill_5", "skill_7")));
        jobOffers3.add(new JobOffer("Krakow", 10000, 2, null));
        jobOffers3.add(new JobOffer(null, 0, 2, null));
        jobOffers3.add(null);
        jobOffers3.add(best3);
        String most3 = "skill_5";

        return Arrays.asList(new Object[][]{
                {jobOffers1, most1},
                {jobOffers2, most2},
                {jobOffers3, most3},
                {null, null}
        });
    }

}