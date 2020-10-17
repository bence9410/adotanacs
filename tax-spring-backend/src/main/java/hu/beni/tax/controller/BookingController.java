package hu.beni.tax.controller;

import java.time.LocalDate;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hu.beni.tax.entity.Booking;
import hu.beni.tax.enums.MeetingTime;
import hu.beni.tax.service.BookingService;
import hu.beni.tax.service.FreeTimeService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class BookingController {

	private final FreeTimeService freeTimeGenerator;

	private final BookingService bookingService;

	@PostMapping("/book")
	public void save(@Valid @RequestBody Booking booking) {
		bookingService.makeBooking(booking);
	}

	@GetMapping("/free-times")
	public Map<LocalDate, MeetingTime[]> findAvailableTimes() {
		return freeTimeGenerator.findAvailableTimes();
	}
}
