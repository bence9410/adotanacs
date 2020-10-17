package hu.beni.adotanacsadas.service;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hu.beni.adotanacsadas.entity.Booking;
import hu.beni.adotanacsadas.enums.MeetingTime;
import hu.beni.adotanacsadas.helper.FreeTimeGenerator;
import hu.beni.adotanacsadas.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

	private final BookingRepository bookingRepository;
	private final JavaMailSender mailSender;
	private final FreeTimeGenerator freeTimeGenerator;

	public void makeBooking(Booking booking) {
		boolean good = false;
		Map<LocalDate, MeetingTime[]> map = freeTimeGenerator.getFindAvailableTimes();

		MeetingTime[] times = map.get(booking.getMeetingDate());
		if (times == null) {
			throw new RuntimeException("Wrong meeting date.");
		}
		for (int i = 0; i < times.length; i++) {
			if (times[i].equals(booking.getMeetingTime())) {
				good = true;
			}

		}
		if (good == false) {
			throw new RuntimeException("Wrong meeting time.");
		}
		bookingRepository.save(booking);
		sendEmail(booking);

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
