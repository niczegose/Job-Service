package pl.kurs.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ApplicationServiceTestMostPopularSkill {
    private final List<Application> applicationList;
    private ApplicationService underTest;
    private final String expected;

    public ApplicationServiceTestMostPopularSkill(List<Application> applicationList, String expected) {
        this.applicationList = applicationList;
        this.expected = expected;
    }

    @Before
    public void setUp() throws Exception {
        underTest= new ApplicationService();
    }

    @Test
    public void shouldReturnBestSkill() {
        assertEquals(expected, underTest.mostPopularSkill(applicationList));
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<Application> applications = new ArrayList<>();
        applications.add(new Application("Bartek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications.add(new Application("Arek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications.add(new Application("Andrzej", "Portek", 2, "Krk", "1",true,"abcdef@1",1, Arrays.asList("skill_3","skill_8")));
        String used = "skill_3";

        List<Application> applications2 = new ArrayList<>();
        applications2.add(new Application("Bartek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications2.add(new Application("Arek", "Cyga", 2, "Krk", "1",true,"adabc@1",1, Arrays.asList("skill_3",null)));
        String used2 = "skill_3";

        List<Application> applications3 = new ArrayList<>();
        applications3.add(new Application("Bartek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications3.add(new Application("Arek", "Cyga", 2, "Krk", "1",true,"abc@1",1, null));
        applications3.add(new Application("Bartek", "NANA", 2, "Krk", "1",true,"abcd@1",1, Arrays.asList("skill_3","skill_1")));
        applications3.add(null);
        String used3 = "skill_3";

        return Arrays.asList(new Object[][]{
                {applications, used},
                {applications2, used2},
                {applications3, used3},
                {null, null}
        });
    }
}