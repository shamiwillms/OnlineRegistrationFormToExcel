package xml_parser;

import java.util.LinkedList;
import java.util.List;

public class InfoDisclosure {
	
	public String copywriteRelease = "";
	public String schoolCouncilInfoDisclosure = "";
	public String mediaConsentForm = "";
	public List<String> internetInfoDisclosure = new LinkedList<String>();
	public String differentConsentCriteria = "";

	public void print() {

		System.out.println("Copywrite Release: " + copywriteRelease);
		System.out.println("School Council Information Disclosure Consent: " + schoolCouncilInfoDisclosure);
		System.out.println("Media Consent Form: " + mediaConsentForm);
		System.out.println("Internet/Website Information Disclosure Consent: " + internetInfoDisclosure.size());
		for (String s : internetInfoDisclosure) {
			System.out.println("  -- " + s);
		}
		
		System.out.println("Different Consent Criteria from Parent: " + differentConsentCriteria);

	}
}
