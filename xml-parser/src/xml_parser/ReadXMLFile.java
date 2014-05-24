/**
 * 
 */
package xml_parser;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import jxl.write.WriteException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ReadXMLFile {

	public static void main(String argv[]) {

		List<Family> families = new LinkedList<Family>();
		
		try {

			File fXmlFile = new File(
	//				"/Users/willms/Documents/workspace/xml-parser/Tricky.xml");	
		"/Users/willms/Documents/workspace/xml-parser/Export_2014-05-24.xml");	
//					"/Users/willms/Documents/workspace/xml-parser/Export_2013-10-13.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(fXmlFile);

			// optional, but recommended
			// read this -
			// http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getDocumentElement().getChildNodes();

			System.out.println("----------------------------");

			for (int temp = 0; temp < nList.getLength(); temp++) {

				Node tmpNode = nList.item(temp);
				
				// continue to next node if not a response
				if (!tmpNode.getNodeName().equals("response")) continue;
				
				
				Family data = new Family();  // create data for response



				NodeList children = tmpNode.getChildNodes();
				for (int i = 0; i < children.getLength(); i++) {

					Node nNode = children.item(i);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {

						Element eElement = (Element) nNode;
						System.out.println("name:" + eElement.getNodeName()
								+ " value: " + eElement.getNodeValue());

						if (eElement.getNodeName() == "fieldset") {

							parseData(data, eElement);
						}
					}
				}
				data.print();
				
				families.add(data);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		Collections.sort(families, new Comparator<Family>() {

			@Override
			public int compare(Family o1, Family o2) {
				String s1 = o1.fathersLastName + o1.fathersFirstName;
				if (o1.fathersLastName.isEmpty()) {
					s1 = o1.mothersLastName + o1.mothersFirstName;
				}
				String s2 = o2.fathersLastName + o2.fathersFirstName;
				if (o2.fathersLastName.isEmpty()) {
					s2 = o2.mothersLastName + o2.mothersFirstName;
				}	
				
				return s1.compareToIgnoreCase(s2);
			}
		});
		
		//PdfPrinter.printOneFamilyPerPage(families);
		
		WriteExcel excel = new WriteExcel (families);
		try {
			excel.setOutputFile("finances.xls");
			excel.writeFinances();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			excel.setOutputFile("volunteerOutcomes.xls");
			excel.writeVolunteerOutcomes();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		try {
			excel.setOutputFile("schoolDirectory.xls");
			excel.writeSchoolDirectory();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Print families to PDF and cleaned up spreadsheet
		
		
	}

	private static void parseData(Family data, Element e) {

		// if FOIP
		if (idMatches(e, "tfa_9356123593406")) {
			data.FOIP = getYesNoChoiceBoolean(e, "tfa_9356123593254");
		} 
		else if (idMatches(e, "tfa_9356123593175")) {
			data.fathersFirstName = getSingleValueString(e, "tfa_9356123593408");
			data.fathersLastName = getSingleValueString(e, "tfa_9356123593410");
			System.out.println ("FAMILY: " + data.fathersFirstName + " " + data.fathersLastName);
			
			data.mothersFirstName = getSingleValueString(e, "tfa_9356123593415");
			data.mothersLastName = getSingleValueString(e, "tfa_9356123593416");
	
			data.addressLine1 = getSingleValueString(e, "tfa_9356123593123");
			data.addressLine2 = getSingleValueString(e, "tfa_9356123593130");
			data.city = getSingleValueString(e, "tfa_9356123593126");
			data.province = getChoiceString(e, "tfa_9356123593105");
			data.postalCode = getSingleValueString(e, "tfa_9356123593128");
			
			// get list of email addresses
			data.mothersEmail = getSingleValueString(e, "tfa_9356123593653");
			data.fathersEmail = getSingleValueString(e, "tfa_9356123593132");
			
			data.homePhone = getSingleValueString(e, "tfa_9356123593135"); // best contact phone number
/*			data.cellPhone = getSingleValueString(e, "tfa_9356123593250");
			data.workPhone = getSingleValueString(e, "tfa_9356123593139");

			data.bestContact = getSingleValueString(e, "tfa_9356123593141");
			data.schoolTaxes = getChoiceString(e, "tfa_9356123593142");
			data.livesWith = getSingleValueString(e, "tfa_9356123593171");
			data.custody = getSingleValueString(e, "tfa_9356123593172");
			data.legalAccess = getSingleValueString(e, "tfa_9356123593173");
	*/		
		
		}
		else if (idMatches(e, "tfa_9356123593174")) {
			
			// get constants for fees
			data.societyFamilyMax = Integer.parseInt(getSingleValueString(e, "tfa_9356123593284"));
			data.societyECSFee = Integer.parseInt(getSingleValueString(e, "tfa_9356123593595"));
			data.societyGradeFee = Integer.parseInt(getSingleValueString(e, "tfa_9356123593294"));
			
			data.activityFeeECS = Integer.parseInt(getSingleValueString(e, "tfa_9356123593295"));
			data.activityFeeDiv1 = Integer.parseInt(getSingleValueString(e, "tfa_9356123593285"));
			data.activityFeeDiv2 = Integer.parseInt(getSingleValueString(e, "tfa_9356123593286"));
			data.activityFeeDiv3 = Integer.parseInt(getSingleValueString(e, "tfa_9356123593287"));
			
			data.grade6CampoutFee = Integer.parseInt(getSingleValueString(e, "tfa_9356123593288"));
			data.grade9GradFee = Integer.parseInt(getSingleValueString(e, "tfa_9356123593291"));
			data.saltsFee = Integer.parseInt(getSingleValueString(e, "tfa_9356123593296"));
			
			/*
			TODO for finance
			- separate billing column (YES/NO)
			- extra fees
			*/
			
			
			NodeList studentNodes = e.getChildNodes();

			for (int i=0; i< studentNodes.getLength(); i++) {
				if (studentNodes.item(i).getNodeName() == "fieldset") {
					try {
						data.students.add(parseStudent((Element) studentNodes.item(i)));
					}
					catch (InvalidStudentException ex) {
						System.out.println ("Invalid student entered: " + data.fathersFirstName + " " + data.fathersLastName);
					}
				}
			}
			
		}
		else if (idMatches(e, "tfa_9356123593324")) {
			data.volunteering = parseVolunteering (e);	
		}
		else if (idMatches(e, "tfa_9356123593448")) {
			
			data.extras.parentCovenant = getChoiceString(e, "tfa_9356123593455");
			
		}
		else if (idMatches(e, "tfa_9356123593651")) {
			
			data.consentOfInfoDisclosure = parseConsentOfInfoDisclosure(e);
			
		}
		else if (idMatches(e, "tfa_9356123593459")) {
			data.extras.commitToMenno = getChoiceString(e, "tfa_9356123593469");
		}
		else if (idMatches(e, "tfa_9356123593274")) {
			
			data.extras.newFamily = getChoiceString(e, "tfa_9356123593275").isEmpty()? false : true;
			data.extras.numberOfYearbooks = Integer.valueOf(getChoiceString(e, "tfa_9356123593299"));
			data.extras.childAtCCS = getChoiceString(e, "tfa_9356123593541");
			data.extras.infoIsTrue = getChoiceString(e, "tfa_9356123593266");
			data.extras.contactAboutBilling = getChoiceString(e, "tfa_9356123593647");
			
			data.fees = parseFees(e);
		}
		else {
			System.out.println("did not match: " + e.getAttribute("id"));
		}
	}


	// get list of values from child field items
	private static List<String> getListOfFieldValues(Element e, String id) {
		NodeList items = findId(e, id).getElementsByTagName("value");
		
		List<String> values = new LinkedList<String>();
		
		for (int i=0; i< items.getLength(); i++) {
			values.add(items.item(i).getTextContent());
		}
		
		return values;
	}

	// check if id matches for given element (uses normalized id if it exists)
	private static boolean idMatches(Element e, String id) {
		return e.getAttribute("id").equals(id);
	}

	// get single value string for a given id
	private static String getSingleValueString(Element e, String id) {

		NodeList items = findId(e, id).getElementsByTagName("value");

		 // if there was not choice items found, return false
		 if (items.getLength() == 0) {
			System.out.println ("Log: Did not find choice for id: " + id);
			return "";
		}
		
		return items.item(0).getTextContent();
	}

	// Get Yes/No choice and return as boolean value
	private static Boolean getYesNoChoiceBoolean(Element e, String id) {
		 NodeList items = findId(e, id).getElementsByTagName("choice");
	
		 // if there was not choice items found, return false
		 if (items.item(0) == null) {
			System.out.println ("Log: Did not find choice for id: " + id);
			return false;
		}
		String choice = ((Element) ((Element) items.item(0))
				.getElementsByTagName("label").item(0)).getTextContent();
		
		if (choice.equals("Yes")) {
			return true;
		} else if (choice.equals("No")) {
			return false;
		}
		throw new UnknownError("Expected Yes/No choice but found: '" + choice
				+ "'.");
	}
	
	// Get choice string value
	private static String getChoiceString(Element e, String id) {
		 NodeList items = findId(e, id).getElementsByTagName("choice");
			
		 // if there was not choice items found, return false
		 if (items.getLength() == 0) {
			System.out.println ("Log: Did not find choice for id: " + id);
			return "";
		}
		return ((Element) ((Element) items.item(0))
				.getElementsByTagName("label").item(0)).getTextContent();
		
		
	//	return ((Element) ((Element) findId(e, id).getElementsByTagName("choice")
	//			.item(0)).getElementsByTagName("label").item(0))
	//			.getTextContent();
	}

	private static List<String> getChoiceList(Element e, String id) {
		NodeList items = findId(e, id).getElementsByTagName("choice");

		// if there was not choice items found, return null
		if (items.getLength() == 0) {
			System.out.println ("Log: Did not find choice for id: " + id);
			return null;
		}
		
		List<String> choices = new LinkedList<String>() ;
		
		for (int i=0 ; i<items.getLength(); i++) {
			choices.add(((Element) ((Element) items.item(i))
					.getElementsByTagName("label").item(0)).getTextContent());
			
		}
		
		return choices;
	}

	public static void printTree(Node doc) {
		if (doc == null) {
			System.out.println("Nothing to print!!");
			return;
		}
		try {
			System.out.println(doc.getNodeName() + "  " + doc.getNodeValue());
			NamedNodeMap cl = doc.getAttributes();
			for (int i = 0; i < cl.getLength(); i++) {
				Node node = cl.item(i);
				System.out.println("\t" + node.getNodeName() + " ->"
						+ node.getNodeValue());
			}
			NodeList nl = doc.getChildNodes();
			for (int i = 0; i < nl.getLength(); i++) {
				Node node = nl.item(i);
				printTree(node);
			}
		} catch (Throwable e) {
			System.out.println("Cannot print!! " + e.getMessage());
		}
	}

	private static Element findId(Element e, String id) {

		if (e.hasAttribute("normalized")) {
			if (e.getAttribute("normalized").equals(id)) {
				return e;
			}
		}
		else if (e.getAttribute("id").equals(id)) {
			return e;
		}

		NodeList elements = e.getElementsByTagName("*");
		for (int i = 0; i < elements.getLength(); i++) {
			Element tmp = findId((Element) elements.item(i), id);
			if (tmp != null) {
				return tmp;
			}
		}
		return null;
	}
	
	private static Student parseStudent(Element e) throws InvalidStudentException { 
		Student s = new Student();
		
		s.firstName = getSingleValueString(e, "tfa_9356123593182");
		s.lastName = getSingleValueString(e, "tfa_9356123593187");
		
		if ((s.firstName == "") && (s.lastName == "")) {
			throw new InvalidStudentException();
		}
		
		s.grade = getChoiceString(e, "tfa_9356123593189");
		s.gender = getChoiceString(e, "tfa_9356123593220");
		s.birthdate = getSingleValueString(e, "tfa_9356123593248");
		
		// palliser IT agreement
		s.parentITAgreement = getChoiceString(e, "tfa_9356123593499");
		s.parentITDiscussedWithChild = getChoiceString(e, "tfa_9356123593503");
		s.studentITDiscussedWithParent = getChoiceString(e, "tfa_9356123593511");
		s.parentPublishPermission = getChoiceString(e, "tfa_9356123593524");
		s.studentPublishPermission = getChoiceString(e, "tfa_9356123593528");
		
		// electronic advice
		s.bringingOwnDevice = getChoiceString(e, "tfa_9356123593596");
		s.studentDeviceAgreement = getChoiceString(e, "tfa_9356123593626");
		s.parentDeviceAgreement = getChoiceString(e, "tfa_9356123593635");

		// locker agreement
		s.lockerAgreement = getChoiceString(e, "tfa_9356123593233");
		
		return s;
	}

	private static Volunteering parseVolunteering(Element e) {
		Volunteering v = new Volunteering();
		
		v.alreadyVolunteering = getChoiceString(e, "tfa_9356123593327");
		v.existingPosition = getChoiceString(e, "tfa_9356123593333");
		v.interestedArea = getChoiceString(e, "tfa_9356123593335");
		v.monthlyOptions = getChoiceList(e, "tfa_9356123593371");
		v.eventOptions = getChoiceList(e, "tfa_9356123593381");
		v.additionalOptions = getSingleValueString(e, "tfa_9356123593400");
		v.societyAdminContactPlease = getChoiceString(e, "tfa_9356123593643").isEmpty() ? "No" : "Yes";
		v.acknoledgeResponsibility = getChoiceString(e, "tfa_9356123593476");
		
		return v;
	}


	private static InfoDisclosure parseConsentOfInfoDisclosure(Element e) {
	
		InfoDisclosure info = new InfoDisclosure();
		
		info.copywriteRelease = getChoiceString(e, "tfa_9356123593561");
		info.schoolCouncilInfoDisclosure = getChoiceString(e, "tfa_9356123593566");
		info.mediaConsentForm = getChoiceString(e, "tfa_9356123593570");
		info.internetInfoDisclosure = getChoiceList(e, "tfa_9356123593574");
		info.differentConsentCriteria = getSingleValueString(e, "tfa_9356123593650");
		
		
		
		return info;
	}
	
	private static Fees parseFees(Element e) {
		Fees f = new Fees();
		
		f.newFamily = Integer.valueOf(getSingleValueString(e, "tfa_9356123593311"));
		f.yearbook = Integer.valueOf(getSingleValueString(e, "tfa_9356123593318"));
		f.school = Integer.valueOf(getSingleValueString(e, "tfa_9356123593320"));
		f.gr6Campout = Integer.valueOf(getSingleValueString(e, "tfa_9356123593312"));
		f.gr9SALTS = Integer.valueOf(getSingleValueString(e, "tfa_9356123593314"));
		f.gr9Graduation = Integer.valueOf(getSingleValueString(e, "tfa_9356123593313"));
		f.society = Integer.valueOf(getSingleValueString(e, "tfa_9356123593315"));
		f.total = Integer.valueOf(getSingleValueString(e, "tfa_9356123593319"));
		
		return f;
	}
}
