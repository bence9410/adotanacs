package hu.beni.adotanacsadas.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Component;
import hu.beni.adotanacsadas.enums.MeetingTime;

@Component
public class FreeTimeGenerator {

    public LocalDate calcNextFarFriday() {
        LocalDate date = LocalDate.now();
        return isWednesdayOrThursday(date) ? nextFriday(nextFriday(date)) : nextFriday(date);

    }

    private boolean isWednesdayOrThursday(LocalDate localDate) {
        return localDate.getDayOfWeek().equals(DayOfWeek.WEDNESDAY)
                || localDate.getDayOfWeek().equals(DayOfWeek.THURSDAY);
    }

    private LocalDate nextFriday(LocalDate localDate) {
        return localDate.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
    }

    public Map<LocalDate, MeetingTime[]> next3Friday() {
        LocalDate localDate = calcNextFarFriday();
        Map<LocalDate, MeetingTime[]> map = new HashMap<>();
        map.put(localDate, MeetingTime.values());
        map.put(nextFriday(localDate), MeetingTime.values());
        map.put(nextFriday(nextFriday(localDate)), MeetingTime.values());
        return map;

    }

}
