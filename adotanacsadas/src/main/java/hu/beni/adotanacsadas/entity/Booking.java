package hu.beni.adotanacsadas.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import hu.beni.adotanacsadas.enums.MeetingLenght;
import hu.beni.adotanacsadas.enums.MeetingTime;
import hu.beni.adotanacsadas.enums.MeetingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    @GeneratedValue
    private Long id;

    private LocalDate meetingDate;

    @Enumerated(EnumType.STRING)
    private MeetingTime meetingTime;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

    private String description;

    @Enumerated(EnumType.STRING)
    private MeetingLenght meetingLenght;
}
