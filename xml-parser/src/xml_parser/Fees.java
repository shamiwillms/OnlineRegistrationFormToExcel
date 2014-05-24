/**
 * 
 */
package xml_parser;

/**
 * @author willms
 *
 */
public class Fees {
	
	Integer newFamily = null;
	Integer yearbook = null;
	Integer school = null;
	Integer gr6Campout = null;
	Integer gr9SALTS = null;
	Integer gr9Graduation = null;
	Integer society = null;
	Integer total = null;

	
	void print () {
		
		System.out.println("---------------");
		System.out.println("New Family Capital Fee: " + newFamily);
		System.out.println("Yearbook Fee: " + yearbook);
		System.out.println("School Fee: " + school);
		System.out.println("Grade 6 Campout Fee: " + gr6Campout);
		System.out.println("Grade 9 SALTS Travel Fee: " + gr9SALTS);
		System.out.println("Grade 9 Graduation Fee: " + gr9Graduation);
		System.out.println("Society Student Fee: " + society);
		System.out.println("--------");
		System.out.println("Total: " + total);
		
	}
}
