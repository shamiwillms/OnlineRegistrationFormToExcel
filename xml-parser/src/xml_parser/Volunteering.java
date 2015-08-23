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
	List<String> availableTimes = null;
	String otherTalents = "";
	String additionalOptions = "";
	String acknoledgeResponsibility = "";
	
	void print () {
		
		System.out.println("-------------");
		System.out.println("Already committed to volunteer? " + alreadyVolunteering);
		System.out.println("   Existing Position: " + existingPosition);
		System.out.println("Interested Area? " + interestedArea);

		System.out.println ("Monthly options selected:");
		if (availableTimes != null) {
			for (String time : availableTimes) {
				System.out.println ("   - " + time);
			}
		}

		
		System.out.println("Additional volunteer options: " + additionalOptions);
		System.out.println("Other talents " + otherTalents);
		System.out.println("Acknowledege volunteer responsibility? " + acknoledgeResponsibility);
		
	}
}
