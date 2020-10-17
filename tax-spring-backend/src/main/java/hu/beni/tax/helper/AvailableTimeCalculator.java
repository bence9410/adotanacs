package hu.beni.tax.helper;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import hu.beni.tax.entity.Booking;
import hu.beni.tax.enums.MeetingTime;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AvailableTimeCalculator {

	private final Map<LocalDate, MeetingTime[]> next3FridayWithTimes;

	private LocalDate meetingDate;

	private MeetingTime meetingTime;

	public void removeBooking(Booking booking) {
		meetingDate = booking.getMeetingDate();
		meetingTime = booking.getMeetingTime();
		Optional.ofNullable(next3FridayWithTimes.get(meetingDate)).ifPresent(this::removeTimeAndIfNoTimeLeftRemoveDate);
	}

	private void removeTimeAndIfNoTimeLeftRemoveDate(MeetingTime[] meetingTimes) {
		if (meetingTimes.length == 1) {
			next3FridayWithTimes.remove(meetingDate);
		} else {
			next3FridayWithTimes.put(meetingDate, removeTime(meetingTimes));
		}
	}

	private MeetingTime[] removeTime(MeetingTime[] meetingTimes) {
		return Stream.of(meetingTimes).filter(e -> !e.equals(meetingTime)).toArray(MeetingTime[]::new);
	}

	public Map<LocalDate, MeetingTime[]> getAvailableTimes() {
		return next3FridayWithTimes;
	}

}