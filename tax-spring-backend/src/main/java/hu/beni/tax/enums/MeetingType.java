package hu.beni.tax.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MeetingType {

	PERSONAL("személyes"), SKYPE("skype");

	private final String hu;
	
}
