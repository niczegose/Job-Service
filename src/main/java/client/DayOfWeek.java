package client;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@RequiredArgsConstructor
public class DayOfWeek {

    private final DateProvider dateProvider;

    public String returnTodaysDayOfTheWeek(){
        Locale polish = new Locale("pl", "PL");
        return dateProvider.now().getDayOfWeek().getDisplayName(TextStyle.FULL, polish);
    }
}
