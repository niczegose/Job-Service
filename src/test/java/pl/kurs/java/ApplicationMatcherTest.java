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
public class ApplicationMatcherTest {
    private final List<Application> applicationList;
    private ApplicationMatcher underTest;
    private final Application expected;
    private final JobOffer testFor;

    public ApplicationMatcherTest(JobOffer testFor, List<Application> applicationList, Application expected) {
        this.applicationList = applicationList;
        this.expected = expected;
        this.testFor = testFor;
    }

    @Before
    public void setUp() {
        underTest = new ApplicationMatcher();
    }

    @Test
    public void shouldReturnBestCandidate() {
        assertEquals(expected, underTest.findBest(testFor, applicationList));
    }

    @Parameterized.Parameters
    public static Collection data() {
        JobOffer offer = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        List<Application> applications = new ArrayList<>();
        Application best = new Application("Bartek", "Cyga", 45000, "Krk", "1", true, "abc@1", 1, Arrays.asList("skill_3", "skill_5"));
        applications.add(best);
        applications.add(new Application("Bartek", "Cyga", 60000, null, "1", true, "abc@1", 1, Arrays.asList("skill_3", "skill_5")));
        applications.add(new Application("Arek", "Cyga", 55000, "Krk", "1", true, "abc@1", 1, Arrays.asList("skill_3", "skill_5")));
        applications.add(new Application("Andrzej", "Portek", 45000, "Krk", "1", true, "abcdef@1", 1, Arrays.asList("skill_3", "skill_8")));

        JobOffer offer2 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5"));
        List<Application> applications2 = new ArrayList<>();
        Application best2 = new Application("Bartek", "Cyga", 55000, "Krk", "1", true, "abc@1", 1, Arrays.asList("skill_3", null, "skill_5"));
        applications2.add(best2);
        applications2.add(new Application("Bartek", "Cyga", 55000, "Krk", "1", true, "abc@1", 1, Arrays.asList("skill_3", "skill_5")));
        applications2.add(new Application("Bartek", "Cyga", 40000, "Krk", "1", true, "abc@1", 1, Arrays.asList("skill_3", "skill_4")));
        applications2.add(new Application("Arek", "Cyga", 45000, "Krk", "1", true, "adabc@1", 1, Arrays.asList("skill_3", null)));

        JobOffer offer3 = new JobOffer("Warszawa", 50000, 5, Arrays.asList("skill_3", "skill_5", "skill_8"));
        List<Application> applications3 = new ArrayList<>();
        Application best3 = new Application("Bartek", "Cyga", 55000, "Krk", "1", true, "abc@1", 1, Arrays.asList("skill_3", "skill_5", "skill_8"));
        applications3.add(best3);
        applications3.add(new Application("Arek", "Cyga", 40000, "Krk", "1", false, "abc@1", 1, null));
        applications3.add(new Application("Bartek", "NANA", 45000, "Warszawa", "1", true, "abcd@1", 1, Arrays.asList("skill_3", "skill_1", "skill_5")));
        applications3.add(null);

        return Arrays.asList(new Object[][]{
                {offer, applications, best},
                {offer2, applications2, best2},
                {offer3, applications3, best3},
                {offer, null, null}
        });
    }
}
