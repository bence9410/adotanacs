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
import hu.beni.tax.helper.FreeTimeGenerator;
import hu.beni.tax.service.BookingService;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BookingController {

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
