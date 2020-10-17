package hu.beni.tax.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.stereotype.Component;

import hu.beni.tax.enums.MeetingTime;
import hu.beni.tax.helper.AvailableTimeCalculator;
import hu.beni.tax.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class FreeTimeService {

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

	public Map<LocalDate, MeetingTime[]> next3FridayWithTimes() {
		LocalDate localDate = calcNextFarFriday();
		Map<LocalDate, MeetingTime[]> map = new TreeMap<>();
		map.put(localDate, MeetingTime.values());
		map.put(nextFriday(localDate), MeetingTime.values());
		map.put(nextFriday(nextFriday(localDate)), MeetingTime.values());
		return map;

	}

	public Map<LocalDate, MeetingTime[]> findAvailableTimes() {
		AvailableTimeCalculator availableTimeCalculator = new AvailableTimeCalculator(next3FridayWithTimes());
		bookingRepository.findAll().stream().forEach(availableTimeCalculator::removeBooking);
		return availableTimeCalculator.getAvailableTimes();
	}

}
