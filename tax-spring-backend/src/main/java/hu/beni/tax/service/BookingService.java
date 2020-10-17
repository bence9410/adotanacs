package hu.beni.tax.service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Optional;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.beni.tax.entity.Booking;
import hu.beni.tax.enums.MeetingTime;
import hu.beni.tax.exception.TaxException;
import hu.beni.tax.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BookingService {

	private final BookingRepository bookingRepository;

	private final JavaMailSender mailSender;

	private final FreeTimeService freeTimeGenerator;

	public void makeBooking(Booking booking) {
		MeetingTime[] meetingTimes = getMeetingTimesOnDateExceptionIfNotFound(booking.getMeetingDate());
		exceptionIfNotContains(meetingTimes, booking.getMeetingTime());

		bookingRepository.save(booking);
		sendEmail(booking);	
	}

	private MeetingTime[] getMeetingTimesOnDateExceptionIfNotFound(LocalDate meetingDate) {
		return Optional.ofNullable(freeTimeGenerator.findAvailableTimes().get(meetingDate))
				.orElseThrow(() -> new TaxException("Wrong meeting date."));
	}

	private void exceptionIfNotContains(MeetingTime[] meetingTimes, MeetingTime meetingTime) {
		if (!Arrays.asList(meetingTimes).contains(meetingTime)) {
			throw new TaxException("Wrong meeting time.");
		}
	}

	private void sendEmail(Booking booking) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("nembence1994@gmail.com");
		message.setTo("fenicser85@gmail.com", "nembence1994@gmail.com", "ennerzsebet@gmail.com");
		message.setSubject("Időpontfogalalás Németh Erzsébet adószakértőhöz.");
		message.setText("Szia Édesanyukám! \n\n" + "Foglaltak hozzád egy időpontot a következökkel: \n\n" + "Időpont: "
				+ booking.getMeetingDate() + " " + booking.getMeetingTime().getHu() + " "
				+ booking.getMeetingLenght().getHu() + " perc.\n" + "Név: " + booking.getName() + "\n" + "Email cím: "
				+ booking.getEmail() + "\nA találkozó módja: " + booking.getMeetingType().getHu()
				+ "\nA rövid leírás: \n" + booking.getDescription() + "\n\nSzeretettel, \nBence");
		mailSender.send(message);
	}
}
