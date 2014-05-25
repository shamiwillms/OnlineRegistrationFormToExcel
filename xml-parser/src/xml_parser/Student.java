/**
 * 
 */
package xml_parser;

/**
 * @author willms
 *
 */
public class Student {

	String firstName = "";
	String lastName = "";
	String grade = "";
	String gender = "";
	String birthdate = "";
	String allergiesMedical = "";
	String parentITAgreement = "";
	String parentITDiscussedWithChild = "";
	String studentITDiscussedWithParent = "";
	String parentPublishPermission = "";
	String studentPublishPermission = "";
	String lockerAgreement = "";
	String bringingOwnDevice = "";
	String studentDeviceAgreement = "";
	String parentDeviceAgreement = "";
	
	
	// IT permissions
	
	
	
	public void print() {
		System.out.println ("--------");
		System.out.println ("Student: " + firstName + ", " + lastName);
		System.out.println ("Grade: " + grade);
		System.out.println ("Gender: " + gender);
		System.out.println ("Birth Date: " + birthdate);
//		System.out.println ("Allergies and Medical Conditions: " + allergiesMedical);
		
		System.out.println ("Parent IT agreement? " + parentITAgreement);
		System.out.println ("Parent IT discussed with child? " + parentITDiscussedWithChild);
		System.out.println ("Student IT discussed with parent? " + studentITDiscussedWithParent);
		System.out.println ("Parent Electronic publishing? " + parentPublishPermission);
		System.out.println ("Student Electronic publishing? " + studentPublishPermission);
		System.out.println ("Bringing Own Device to school? " + bringingOwnDevice);
		System.out.println ("Student Agrees to Own Device Agreement? " + studentDeviceAgreement);
		System.out.println ("Parent Agrees to Own Device Agreement? " + parentDeviceAgreement);
		System.out.println ("Agree to locker terms? " + lockerAgreement);
		
		
	}
	
}
