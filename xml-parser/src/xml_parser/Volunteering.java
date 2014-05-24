/**
 * 
 */
package xml_parser;

import java.util.List;

/**
 * @author willms
 *
 */
public class Volunteering {

	String alreadyVolunteering = "";
	String existingPosition = "";
	String interestedArea = "";
	List<String> eventOptions = null;
	List<String> monthlyOptions = null;
	String additionalOptions = "";
	String acknoledgeResponsibility = "";
	String societyAdminContactPlease = "";
	
	void print () {
		
		System.out.println("-------------");
		System.out.println("Already committed to volunteer? " + alreadyVolunteering);
		System.out.println("   Existing Position: " + existingPosition);
		System.out.println("Interested Area? " + interestedArea);

		System.out.println ("Monthly options selected:");
		if (monthlyOptions != null) {
			for (String option : monthlyOptions) {
				System.out.println ("   - " + option);
			}
		}

		System.out.println ("Event options selected:");
		if (eventOptions != null) {
			for (String option : eventOptions) {
				System.out.println ("   - " + option);
			}
		}
		
		System.out.println("Additional volunteer options: " + additionalOptions);
		System.out.println("Would like Society Admin to contact them? " + societyAdminContactPlease);
		System.out.println("Acknowledege volunteer responsibility? " + acknoledgeResponsibility);
		
	}
}
