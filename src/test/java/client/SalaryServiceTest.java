package client;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mockito;

import java.math.BigInteger;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SalaryServiceTest {

    private DateProvider dateProvider;
    private SalaryService underTest;
    private final BigInteger expected;
    private final List<Task> taskList;
    private final LocalDate providedDate;

    public SalaryServiceTest(BigInteger expected, List<Task> taskList,LocalDate providedDate) {
        this.expected = expected;
        this.taskList = taskList;
        this.providedDate = providedDate;
    }

    @Before
    public void setUp() {
        dateProvider = Mockito.mock(DateProvider.class);
        underTest = new SalaryService(dateProvider);
    }

    @Test
    public void shouldReturnSalary() {
        Mockito.when(dateProvider.now()).thenReturn(providedDate);
        assertEquals(expected, underTest.calculateSalary(taskList));
    }

//    @Test(expected = ClassCastException.class)
//    public void shouldReturnException() {
//        Mockito.when(dateProvider.now()).thenReturn(providedDate);
//        underTest.calculateSalary(taskList);
//    }

    @Parameterized.Parameters
    public static Collection data() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("1", BigInteger.valueOf(10), LocalDate.of(2020,7,10)));
        tasks.add(new Task("2", BigInteger.valueOf(10), LocalDate.of(2020,7,11)));
        tasks.add(null);
        List<String> nonTasks = new ArrayList<>();
        nonTasks.add("ojoj");

        return Arrays.asList(new Object[][]{
                {BigInteger.valueOf(25), tasks, LocalDate.of(2020,7,10)},
                {BigInteger.valueOf(35), tasks, LocalDate.of(2020,7,4)},
                {BigInteger.valueOf(0), null, LocalDate.of(2020,7,10)},
                {BigInteger.valueOf(0), null, LocalDate.of(2020,7,4)},
                //{BigInteger.valueOf(0), nonTasks, LocalDate.of(2020,7,10)}
        });
    }

}