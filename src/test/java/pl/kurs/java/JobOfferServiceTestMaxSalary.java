package pl.kurs.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(Parameterized.class)
public class JobOfferServiceTestMaxSalary {
    private final List<JobOffer> jobOffers;
    //private JobOfferService underTest;
    private final JobOffer expected;

    public JobOfferServiceTestMaxSalary(List<JobOffer> jobOffers, JobOffer expected) {
        this.jobOffers = jobOffers;
        this.expected = expected;
    }

//    @Before
//    public void setUp() throws Exception {
//        underTest = new JobOfferService();
//    }

    @Test
    public void shouldReturnBestOffer() {
        assertEquals(expected, JobOfferService.maxSalary(jobOffers));
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<JobOffer> jobOffers1 = new ArrayList<>();
        JobOffer best1 =new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers1.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers1.add(best1);

        List<JobOffer> jobOffers2 = new ArrayList<>();
        JobOffer best2 =new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers2.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", "skill_2")));
        jobOffers2.add(null);
        jobOffers2.add(best2);

        List<JobOffer> jobOffers3 = new ArrayList<>();
        JobOffer best3 =new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        jobOffers3.add(new JobOffer("Warszawa", 10000, 2, Arrays.asList("skill_1", null)));
        jobOffers3.add(new JobOffer("Krakow", 10000, 2, null));
        jobOffers3.add(new JobOffer("Krakow", 0, 2, null));
        jobOffers3.add(null);
        jobOffers3.add(best3);

        return Arrays.asList(new Object[][]{
                {jobOffers1, best1},
                {null, null},
                {jobOffers2, best2},
                {jobOffers3, best3}
        });
    }

}