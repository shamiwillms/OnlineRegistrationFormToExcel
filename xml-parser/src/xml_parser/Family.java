package xml_parser;

import java.util.LinkedList;
import java.util.List;

public class Family {

	boolean FOIP = false;
	
	String fathersFirstName = ""; 
	String fathersLastName = "";
	String mothersFirstName = ""; 
	String mothersLastName = "";
	
	String addressLine1 = "";
	String addressLine2 = "";
	String city = "";
	String province = "";
	String postalCode = "";
	
	String mothersEmail = "";
	String fathersEmail = "";
	
	String homePhone = "";
	String cellPhone = "";
	String workPhone = "";
	
	String bestContact = "";
	String schoolTaxes = "";
	String livesWith = "";
	String custody = "";
	String legalAccess = "";
	
	List<Student> students = new LinkedList<Student>();

	Volunteering volunteering = null;

	Directory directory = null;

	Extras extras = new Extras();

	Fees fees = null;

	InfoDisclosure consentOfInfoDisclosure;

	Integer societyFamilyMax = 0;

	Integer societyECSFee = 0;

	Integer societyGradeFee = 0;

	Integer activityFeeECS = 0;
	Integer activityFeeDiv1 = 0;
	Integer activityFeeDiv2 = 0;
	Integer activityFeeDiv3 = 0;

	Integer grade6CampoutFee = 0;
	Integer grade9GradFee = 0;
	Integer saltsFee = 0;

	
	
	public void print() {
		System.out.println("===============");
		System.out.println("Agreed to FOIP: " + FOIP);
		System.out.println("Father: " + fathersFirstName + " " + fathersLastName);
		System.out.println("Mother: " + mothersFirstName + " " + mothersLastName);
		System.out.print("Address: " + addressLine1); 
		if (!addressLine2.isEmpty()) System.out.print (", " + addressLine2);
		System.out.println(", " + city + ", " + province + ", " + postalCode);
		
		System.out.println("Mother's Email: " + mothersEmail); 
		System.out.println("Father's Email: " + fathersEmail); 
		
		System.out.println ("Best Contact Phone: " + homePhone);
/*		System.out.println ("Cell Phone: " + cellPhone);
		System.out.println ("Work Phone: " + workPhone);
		
		System.out.println ("Best way to contact: " + bestContact);
		System.out.println ("School taxes: " + schoolTaxes);
		System.out.println ("Student lives with: " + livesWith);
		System.out.println ("Student custody held by: " + custody);
		System.out.println ("Legal access to student: " + legalAccess);
*/	
		for (Student s : students) {
			s.print();
		}
		
		consentOfInfoDisclosure.print();
		directory.print();
		volunteering.print();
		extras.print();
		fees.print();
		
		System.out.println("\n===Constants ===");
		System.out.println("Society Family Max: "+ societyFamilyMax);
		System.out.println("ECS Society Fee: " + societyECSFee);
		System.out.println("Grade 1-9 Society Fee: " + societyGradeFee);
		
		System.out.println("ECS Activity Fee: " + activityFeeECS);
		System.out.println("Div 1 Activity Fee: " + activityFeeDiv1);
		System.out.println("Div 2 Activity Fee: " + activityFeeDiv2);
		System.out.println("Div 3 Activity Fee: " + activityFeeDiv3);
		
		System.out.println("Grade 6 Campout Fee: " + grade6CampoutFee);
		System.out.println("Grade 9 Graduation Fee: " + grade9GradFee);
		System.out.println("SALTS Fund Fee: " + saltsFee);
		
	}
	
	public String getTitle() {
		if (fathersLastName.isEmpty()) {
			return mothersLastName + ", " + mothersFirstName;
		}
		else if (mothersLastName.isEmpty()) {
			return fathersLastName + ", " + fathersFirstName;
		}
		return fathersLastName + ", " + fathersFirstName + " / " + mothersLastName + ", " + mothersFirstName;
	}

	public String getAddress() {
		
		StringBuilder s = new StringBuilder();
		s.append(addressLine1);
		
		if (!addressLine2.isEmpty()) {
			s.append(", " + addressLine2);
		}
		
		s.append("\n" + city + ", " + province + ", " + postalCode);

		return s.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((fathersFirstName == null) ? 0 : fathersFirstName.hashCode());
		result = prime * result
				+ ((fathersLastName == null) ? 0 : fathersLastName.hashCode());
		result = prime
				* result
				+ ((mothersFirstName == null) ? 0 : mothersFirstName.hashCode());
		result = prime * result
				+ ((mothersLastName == null) ? 0 : mothersLastName.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (fathersFirstName == null) {
			if (other.fathersFirstName != null)
				return false;
		} else if (!fathersFirstName.equals(other.fathersFirstName))
			return false;
		if (fathersLastName == null) {
			if (other.fathersLastName != null)
				return false;
		} else if (!fathersLastName.equals(other.fathersLastName))
			return false;
		if (mothersFirstName == null) {
			if (other.mothersFirstName != null)
				return false;
		} else if (!mothersFirstName.equals(other.mothersFirstName))
			return false;
		if (mothersLastName == null) {
			if (other.mothersLastName != null)
				return false;
		} else if (!mothersLastName.equals(other.mothersLastName))
			return false;
		return true;
	}
	
}
