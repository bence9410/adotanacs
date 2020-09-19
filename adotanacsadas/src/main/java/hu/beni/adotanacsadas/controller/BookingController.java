package hu.beni.adotanacsadas.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import hu.beni.adotanacsadas.entity.Booking;
import hu.beni.adotanacsadas.enums.MeetingTime;
import hu.beni.adotanacsadas.helper.FreeTimeGenerator;
import hu.beni.adotanacsadas.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final FreeTimeGenerator freeTimeGenerator;

    @PostMapping("/book")
    public void save(@RequestBody Booking booking) {
        bookingRepository.save(booking);
    }

    @GetMapping("/free-times")
    public Map<LocalDate, MeetingTime[]> findAvailableTimes() {
        Map<LocalDate, MeetingTime[]> mapNext3Friday = freeTimeGenerator.next3Friday();

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
