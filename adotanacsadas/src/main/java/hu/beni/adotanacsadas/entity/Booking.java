package hu.beni.adotanacsadas.entity;

import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @Future
    private LocalDate meetingDate;

    @NotNull
    @Size(min = 3, max = 30)
    private String name;

    @NotNull
    @Email(regexp = ".+@.+\\..+")
    private String email;

    @NotNull
    @Size(min = 3, max = 60)
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MeetingTime meetingTime;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MeetingLenght meetingLenght;

    @NotNull
    @Enumerated(EnumType.STRING)
    private MeetingType meetingType;

}
