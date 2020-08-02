package pl.kurs.java;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class ApplicationServiceTestAlreadyUsedEmail {
    private final List<Application> applicationList;
    private ApplicationService underTest;
    private final List<String> expected;

    public ApplicationServiceTestAlreadyUsedEmail(List<Application> applicationList, List<String> expected) {
        this.applicationList = applicationList;
        this.expected = expected;
    }

    @Before
    public void setUp() throws Exception {
        underTest= new ApplicationService();
    }

    @Test
    public void shouldReturnWrongUsedEmails() {
        assertEquals(expected, underTest.alreadyUsedEmail(applicationList));
    }

    @Parameterized.Parameters
    public static Collection data() {
        List<Application> applications = new ArrayList<>();
        applications.add(new Application("Bartek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications.add(new Application("Arek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        List<String> used = Collections.singletonList("abc@1");

        List<Application> applications2 = new ArrayList<>();
        applications2.add(new Application("Bartek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications2.add(new Application("Arek", "Cyga", 2, "Krk", "1",true,"adabc@1",1, Arrays.asList("skill_3","skill_5")));
        List<String> used2 = Collections.emptyList();

        List<Application> applications3 = new ArrayList<>();
        applications3.add(new Application("Bartek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications3.add(new Application("Arek", "Cyga", 2, "Krk", "1",true,"abc@1",1, Arrays.asList("skill_3","skill_5")));
        applications3.add(new Application("Bartek", "NANA", 2, "Krk", "1",true,"abcd@1",1, Arrays.asList("skill_3","skill_5")));
        applications3.add(new Application("Arek", "Bana", 2, "Krk", "1",true,"abcd@1",1, Arrays.asList("skill_3","skill_5")));
        List<String> used3 = Arrays.asList("abc@1","abcd@1");

        return Arrays.asList(new Object[][]{
                {applications, used},
                {applications2, used2},
                {applications3, used3},
                {null, null}
        });
    }
}