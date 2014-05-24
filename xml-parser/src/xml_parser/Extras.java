/**
 * 
 */
package xml_parser;

/**
 * @author willms
 *
 */
public class Extras {

	String parentCovenant = "";
	String commitToMenno = "";
	Boolean newFamily = null;
	Integer numberOfYearbooks = null;
	String childAtCCS = "";
	String infoIsTrue = "";
	String contactAboutBilling = "";
	
	
	
	
	public void print() {
		

		System.out.println("Parental Covenant: " + parentCovenant);
		System.out.println("Signed commitement to Menno: " + commitToMenno);
		System.out.println("New Family at Menno: " + newFamily);
		System.out.println("Number of Yearbooks: " + numberOfYearbooks);
		System.out.println("Have child at CCS? " + childAtCCS);
		System.out.println("Please contact me about Billing: " + contactAboutBilling);
		System.out.println("Info is entered and true? " + infoIsTrue);
		
		
		
	}

}
