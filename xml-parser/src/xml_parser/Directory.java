package xml_parser;

import java.util.List;

public class Directory {
	
	public String changesToDirectoryInfo = "";
	public String schoolDirectoryConsentForm = "";

	public void print() {

		System.out.println("Changes to Directory Info: " + changesToDirectoryInfo);
		System.out.println("School Directory Consent: " + schoolDirectoryConsentForm);

	}
}
