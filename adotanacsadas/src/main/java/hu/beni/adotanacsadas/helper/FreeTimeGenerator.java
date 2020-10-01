package hu.beni.adotanacsadas.helper;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import hu.beni.adotanacsadas.entity.Booking;
import hu.beni.adotanacsadas.enums.MeetingTime;
import hu.beni.adotanacsadas.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class FreeTimeGenerator {

    private final BookingRepository bookingRepository;

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
        Map<LocalDate, MeetingTime[]> map = new TreeMap<>();
        map.put(localDate, MeetingTime.values());
        map.put(nextFriday(localDate), MeetingTime.values());
        map.put(nextFriday(nextFriday(localDate)), MeetingTime.values());
        return map;

    }

    public Map<LocalDate, MeetingTime[]> getFindAvailableTimes() {
        Map<LocalDate, MeetingTime[]> mapNext3Friday = next3Friday();

        for (Booking booking : bookingRepository.findAll()) {
            MeetingTime[] meetingDate = mapNext3Friday.get(booking.getMeetingDate());

            if (meetingDate != null) {

                if (meetingDate.length == 1) {
                    mapNext3Friday.remove(booking.getMeetingDate());
                } else {
                    mapNext3Friday.put(booking.getMeetingDate(), Stream.of(meetingDate)
                            .filter(e -> !e.equals(booking.getMeetingTime())).toArray(MeetingTime[]::new));
                }
            }
        }
        return mapNext3Friday;
    }

}
