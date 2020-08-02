package client;

import java.time.LocalDate;

public class DateProvider {
    public LocalDate now(){
        return LocalDate.now();
    }
    public LocalDate setDate(int year, int month, int day){
        return LocalDate.of(year,month,day);
    }
}
