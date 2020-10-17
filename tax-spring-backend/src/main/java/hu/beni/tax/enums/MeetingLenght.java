package hu.beni.tax.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingLenght {

	HALF_HOUR("30"), ONE_HOUR("60");

	private final String hu;

}
