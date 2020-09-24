package hu.beni.adotanacsadas.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hu.beni.adotanacsadas.entity.Booking;
import hu.beni.adotanacsadas.repository.BookingRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookingService {

    private final BookingRepository bookingRepository;
    private final JavaMailSender mailSender;

    public void makeBooking(Booking booking) {

        bookingRepository.save(booking);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("nembence1994@gmail.com");
        message.setTo("fenicser85@gmail.com", "nembence1994@gmail.com");
        message.setSubject("Időpontfogalalás Németh Erzsébet adószakértőhöz.");
        message.setText("Szia Édesanyukám! \n\n" + "Foglaltak hozzád egy időpontot a következökkel: \n\n" + "Időpont: "
                + booking.getMeetingDate() + " " + booking.getMeetingTime().getHu() + " "
                + booking.getMeetingLenght().getHu() + " perc.\n" + "Név: " + booking.getName() + "\n" + "Email cím: "
                + booking.getEmail() + "\nA találkozó módja: " + booking.getMeetingType().getHu()
                + "\nA rövid leírás: \n" + booking.getDescription() + "\n\nSzeretettel, \nBence");
        mailSender.send(message);

    }
}
