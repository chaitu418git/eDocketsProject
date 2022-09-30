package eDockets.cucumber.customtype;

import java.util.Map;

import eDockets.cucumber.domainobjects.LoginDetails;
import io.cucumber.java.DataTableType;

public class CustomDataTableType {
	@DataTableType
	public LoginDetails loginDetailsEntry(Map<String,String> entry)
	{
		return new LoginDetails(entry.get("username"), 
				entry.get("password"));
	}
}
