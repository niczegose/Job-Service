package client;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UrlServiceTest {

    UrlService underTest;

    @Before
    public void setUp() {
        underTest = new UrlService();
    }

    @Test
    public void countInUrlHTTPs() {
        assertEquals(31,underTest.countInUrl("https://stackoverflow.com/questions/238547/how-do-you-programmatically-download-a-webpage-in-java", "body"));
    }

    @Test
    public void countInUrlHTTP() {
        assertEquals(12,underTest.countInUrl("http://ancugbrowar.pl", "ancug"));
    }

    @Test
    public void countInUrl() {
        assertEquals(0,underTest.countInUrl("http://ancugbrowar.p", "ancug"));
    }

    @Test
    public void shouldReturnZero() {
        assertEquals(0,underTest.countInUrl("http://ancugbrowar.pl", null));
        assertEquals(0,underTest.countInUrl(null, null));
        assertEquals(0,underTest.countInUrl(null, "ancug"));
        assertEquals(0,underTest.countInUrl("ancugbrowar.pl", "ancug"));
        assertEquals(0,underTest.countInUrl("kilo", "ancug"));
    }

}