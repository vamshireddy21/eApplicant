package com.rakmo.eapplicant.types;

public enum Gender {
	MALE,FEMALE;
	
	int getValue()
	{
		if(this.equals(Gender.MALE))
			return 1;
		else
			return 0;
	}
}
