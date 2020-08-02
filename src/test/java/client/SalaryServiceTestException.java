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

import static org.junit.Assert.assertEquals;

public class SalaryServiceTestException {

    private DateProvider dateProvider;
    private SalaryService underTest;

    @Before
    public void setUp() {
        dateProvider = Mockito.mock(DateProvider.class);
        underTest = new SalaryService(dateProvider);
    }

    @Test(expected = ClassCastException.class)
    public void shouldReturnSalary() {
        List<String> nonTasks = new ArrayList<>();
        nonTasks.add("ojoj");
        Mockito.when(dateProvider.now()).thenReturn(LocalDate.of(2020,7,10));
        //underTest.calculateSalary(nonTasks);
    }

}