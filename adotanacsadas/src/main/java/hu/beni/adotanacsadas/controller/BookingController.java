package hu.beni.adotanacsadas.controller;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Stream;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import hu.beni.adotanacsadas.entity.Booking;
import hu.beni.adotanacsadas.enums.MeetingTime;
import hu.beni.adotanacsadas.helper.FreeTimeGenerator;
import hu.beni.adotanacsadas.repository.BookingRepository;
import hu.beni.adotanacsadas.service.BookingService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BookingController {

    private final BookingRepository bookingRepository;
    private final FreeTimeGenerator freeTimeGenerator;
    private final BookingService bookingService;

    @PostMapping("/book")
    public void save(@Valid @RequestBody Booking booking) {
        bookingService.makeBooking(booking);
    }

    @GetMapping("/free-times")
    public Map<LocalDate, MeetingTime[]> findAvailableTimes() {
        Map<LocalDate, MeetingTime[]> mapNext3Friday = freeTimeGenerator.getFindAvailableTimes();

        return mapNext3Friday;
    }
}
