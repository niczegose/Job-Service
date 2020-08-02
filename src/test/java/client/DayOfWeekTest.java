package client;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class DayOfWeekTest {

    private DayOfWeek underTest;
    private DateProvider dateProvider;

    @Before
    public void setUp() {
        dateProvider = Mockito.mock(DateProvider.class);
        underTest = new DayOfWeek(dateProvider);
    }

    //FIRST - testy jednostkowe
    //F-FAST
    //I-Independent
    //R-Repeatable*
    //S-SelfValidating
    //T-Timely

    @Test
    public void shouldReturnTodayDay(){
        String expected = "poniedziałek";
        Mockito.when(dateProvider.now()).thenReturn(LocalDate.of(2020,07,06));
        assertEquals(expected, underTest.returnTodaysDayOfTheWeek());
        Mockito.verify(dateProvider,Mockito.times(1)).now();
    }



}