package pl.kurs.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ApplicationServiceTestCountApplicationsByEmails {
    private final List<Application> applicationList;
    private ApplicationService underTest;
    private final Map<String,Long> expected;

    public ApplicationServiceTestCountApplicationsByEmails(List<Application> applicationList, Map<String,Long> expected) {
        this.applicationList = applicationList;
        this.expected = expected;
    }

    @Before
    public void setUp() throws Exception {
        underTest= new ApplicationService();
    }

    @Test
    public void shouldReturnMapOfTotalAppsByEmails() {
        assertEquals(expected, underTest.countApplicationsByEmails(applicationList));
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<Application> applications = new ArrayList<>();
        applications.add(new Application("Bartek", "Cyga", 2, "Krakow", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications.add(new Application("Arek", "Cyga", 2, "Krakow", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        Map<String,Long> used = new HashMap<>();
        used.put("abc@1",2L);

        List<Application> applications2 = new ArrayList<>();
        applications2.add(new Application("Bartek", "Cyga", 2, "Krakow", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications2.add(null);
        Map<String,Long> used2 = new HashMap<>();
        used2.put("abc@1",1L);

        List<Application> applications3 = new ArrayList<>();
        applications3.add(new Application("Bartek", "Cyga", 2, "Krakow", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications3.add(new Application("Arek", "Cyga", 2, "Warszawa", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications3.add(new Application("Bartek", "NANA", 2, "Krakow", "1",true,"abcd@1",1, Arrays.asList("skill_3","skill_5")));
        applications3.add(new Application("Arek", "Bana", 2, null, "1",true,"abcd@1",1, Arrays.asList("skill_3","skill_5")));
        Map<String,Long> used3 = new HashMap<>();
        used3.put("abc@1",2L);
        used3.put("abcd@1",2L);

        return Arrays.asList(new Object[][]{
                {applications, used},
                {applications2, used2},
                {applications3, used3},
                {null, null}
        });
    }
}