package hu.beni.adotanacsadas.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingTime {
    PM2_30("14:30"), PM4_00("16:00"), PM5_30("17:30");

    private final String hu;
}
