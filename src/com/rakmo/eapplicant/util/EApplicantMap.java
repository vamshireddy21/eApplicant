package com.rakmo.eapplicant.util;

public class EApplicantMap {
	
	
	
	public static final String adminMailId = "xyz@gmail.com";
	public static final String adminPassword = "password";
	
	// 1 - TC 2- BC 3 - CC
	// 1- approved 2 - rejected 3 - progress
	//1 - student 2 - Admin  3- IStaff 4 -HStaff 5 - VPStaff 6- PStaff
	public static String getTypeOfCertificate(int type)
	{
		switch(type)
		{
		case 1: return "Transfer Certificate";
		case 2: return "Bonafide Certificate";
		case 3: return "Custodian Certificate";
		}
		return "NoMatch";
	}
	public static String getUserType(int type)
	{
		switch(type)
		{
		case 1: return "Student";
		case 2: return "Admin";
		case 3: return "IStaff";
		case 4: return "HStaff";
		case 5: return "Vice Prinicipal";
		case 6: return "Principal";
		}
		return "NoMatch";
	}
	
	public static String getStatus(int status)
	{
		switch(status)
		{
		case 1: return "Approved";
		case 2: return "Rejected";
		case 3: return "Progress";
		}
		return "NoMatch";
	}
}
