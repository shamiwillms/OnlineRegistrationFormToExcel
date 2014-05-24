package xml_parser;
import java.io.File;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Formula;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class WriteExcel {

  private WritableCellFormat timesBoldUnderline;
  private WritableCellFormat times;
  private String outputFile;
  private List<Family> families;
  
public WriteExcel(List<Family> p_families) {
	families = p_families; 
}

public void setOutputFile(String outputFile) {
  this.outputFile = outputFile;
  }

  public void writeGrades() throws IOException, WriteException {
    File file = new File(outputFile);
    WorkbookSettings wbSettings = new WorkbookSettings();

    wbSettings.setLocale(new Locale("en", "EN"));

    WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Students", 0);
    WritableSheet excelSheet = workbook.getSheet(0);
    createLabel(excelSheet);
    
    
    // write each student to the spreadsheet
    int row = 1;
    for (Family f: families) {
    	for (Student s: f.students) {
    	      addLabel(excelSheet, 0, row, s.firstName + " " + s.lastName);
    	      addLabel(excelSheet, 1, row, s.grade);
    	      row++; // increment row count
    	}
    }
    
    

    workbook.write();
    workbook.close();
  }

  private void createLabel(WritableSheet sheet)
      throws WriteException {
    // Lets create a times font
    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
    // Define the cell format
    times = new WritableCellFormat(times10pt);
    // Lets automatically wrap the cells
    times.setWrap(true);

    // create create a bold font with underlines
    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
        UnderlineStyle.SINGLE);
    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
    // Lets automatically wrap the cells
    timesBoldUnderline.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBoldUnderline);
    cv.setAutosize(true);

    // Write a few headers
    addCaption(sheet, 0, 0, "Student");
    addCaption(sheet, 1, 0, "Grade");
    

  }

  private void createContent(WritableSheet sheet) throws WriteException,
      RowsExceededException {
    // Write a few number
    for (int i = 1; i < 10; i++) {
      // First column
      addNumber(sheet, 0, i, i + 10);
      // Second column
      addNumber(sheet, 1, i, i * i);
    }
    // Lets calculate the sum of it
    StringBuffer buf = new StringBuffer();
    buf.append("SUM(A2:A10)");
    Formula f = new Formula(0, 10, buf.toString());
    sheet.addCell(f);
    buf = new StringBuffer();
    buf.append("SUM(B2:B10)");
    f = new Formula(1, 10, buf.toString());
    sheet.addCell(f);

    // now a bit of text
    for (int i = 12; i < 20; i++) {
      // First column
      addLabel(sheet, 0, i, "Boring text " + i);
      // Second column
      addLabel(sheet, 1, i, "Another text");
    }
  }

  private void addCaption(WritableSheet sheet, int column, int row, String s)
      throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, timesBoldUnderline);
    sheet.addCell(label);
  }

  private void addNumber(WritableSheet sheet, int column, int row,
      Integer integer) throws WriteException, RowsExceededException {
    Number number;
    number = new Number(column, row, integer, times);
    sheet.addCell(number);
  }

  private void addLabel(WritableSheet sheet, int column, int row, String s)
      throws WriteException, RowsExceededException {
    Label label;
    label = new Label(column, row, s, times);
    sheet.addCell(label);
  }

  	// write finances spreadsheet for Jennifer 
	public void writeFinances() throws IOException, WriteException {
		File file = new File(outputFile);
		WorkbookSettings wbSettings = new WorkbookSettings();

		wbSettings.setLocale(new Locale("en", "EN"));

		WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
		workbook.createSheet("Financial Information", 0);
		WritableSheet excelSheet = workbook.getSheet(0);
		createFinanceLabels(excelSheet);
		
		// write each student to the spreadsheet
		int row = 1;
		for (Family f : families) {
			writeParentNames(excelSheet, row, f, 0);
			addLabel(excelSheet, 1, row, f.fathersEmail);
			addLabel(excelSheet, 2, row, f.mothersEmail);
			
			
			addLabel(excelSheet, 7, row, "-$200");
			addLabel(excelSheet, 8, row, f.fees.newFamily.equals(0) ? "" : "$" + f.fees.newFamily.toString());

			addLabel(excelSheet, 13, row, f.extras.numberOfYearbooks.toString());
			addLabel(excelSheet, 14, row, "$" + f.fees.yearbook.toString());
			
			addLabel(excelSheet, 18, row, f.extras.contactAboutBilling);
			
			addLabel(excelSheet, 19, row, "$" + f.fees.total.toString());
			
			
			
			
			
			// store total amount of student fees
			Integer studentFeeTotal = 0;
			
			int firstRow = row; 
			
			for (Student s : f.students) {
				addLabel(excelSheet, 3, row, s.firstName);
				addLabel(excelSheet, 4, row, s.grade);
				
				Integer fee = 0;
				if (s.grade.equals("K")) {
					fee = f.societyECSFee;
					addLabel(excelSheet, 9, row, "$" + f.activityFeeECS.toString());
				}
				else {
					fee = f.societyGradeFee;
					
					switch (Integer.parseInt(s.grade)){
					case 1:
					case 2:
					case 3:
						addLabel(excelSheet, 10, row, "$" + f.activityFeeDiv1.toString());
						break;
					case 4:
					case 5:
					case 6:
						addLabel(excelSheet, 11, row, "$" + f.activityFeeDiv2.toString());
						break;
					case 7:
					case 8:
					case 9:
						addLabel(excelSheet, 12, row, "$" + f.activityFeeDiv3.toString());
						break;
					default:
						throw new UnexpectedException("should never get here - unknown grade");
					}
					
					
					switch (Integer.parseInt(s.grade)) {
					case 6:
						addLabel(excelSheet, 15, row, "$" + f.grade6CampoutFee.toString());
						break;
					case 9:
						addLabel(excelSheet, 16, row, "$" + f.grade9GradFee.toString());
						addLabel(excelSheet, 17, row, "$" + f.saltsFee.toString());
						break;
					}
						
				}

				addLabel(excelSheet, 5, row, "$" + fee.toString());
				
				studentFeeTotal += fee;
				
				row++; // increment row count
			}

			// display the discount the family gets with the society family max
			if (f.fees.society.equals(f.societyFamilyMax)) {
				addLabel(excelSheet, 6, firstRow, "$" + new Integer(f.societyFamilyMax - studentFeeTotal).toString());
			}
			
			
		}

		workbook.write();
		workbook.close();
	}

	  private void createFinanceLabels(WritableSheet sheet)
	      throws WriteException {
	    // Lets create a times font
	    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
	    // Define the cell format
	    times = new WritableCellFormat(times10pt);
	    // Lets automatically wrap the cells
	    times.setWrap(true);

	    // create create a bold font with underlines
	    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
	        UnderlineStyle.SINGLE);
	    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
	    // Lets automatically wrap the cells
	    timesBoldUnderline.setWrap(true);

	    CellView cv = new CellView();
	    cv.setFormat(times);
	    cv.setFormat(timesBoldUnderline);
	    cv.setAutosize(true);

	    // Write a few headers
	    addCaption(sheet, 0, 0, "Family");
	    addCaption(sheet, 1, 0, "Father's Email");
	    addCaption(sheet, 2, 0, "Mother's Email");
	    addCaption(sheet, 3, 0, "Student First Name");
	    addCaption(sheet, 4, 0, "Grade");
	    addCaption(sheet, 5, 0, "Society Fee");
	    addCaption(sheet, 6, 0, "Family Maximum Discount");
	    addCaption(sheet, 7, 0, "Less Deposit Fee");
	    addCaption(sheet, 8, 0, "New Family Capital Fee");
	    addCaption(sheet, 9, 0, "ECS Activity Fee");
	    addCaption(sheet, 10, 0, "Gr 1-3 Activity Fee");
	    addCaption(sheet, 11, 0, "Gr 4-6 Activity Fee");
	    addCaption(sheet, 12, 0, "Gr 7-9 Activity Fee");
	    addCaption(sheet, 13, 0, "# Yearbook");
	    addCaption(sheet, 14, 0, "Yearbook Fee");
	    addCaption(sheet, 15, 0, "Gr 6 Campout");
	    addCaption(sheet, 16, 0, "Gr 9 Grad");
	    addCaption(sheet, 17, 0, "Gr 9 SALTS");
	    addCaption(sheet, 18, 0, "Contact Family?");
	    addCaption(sheet, 19, 0, "Total");

	  }
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  
	  

	  	// write volunteer outcomes spreadsheet for Ann 
		public void writeVolunteerOutcomes() throws IOException, WriteException {
			File file = new File(outputFile);
			WorkbookSettings wbSettings = new WorkbookSettings();

			wbSettings.setLocale(new Locale("en", "EN"));

			WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
			workbook.createSheet("Financial Information", 0);
			WritableSheet excelSheet = workbook.getSheet(0);
			createVolunteerOutcomesLabels(excelSheet);
			
			// write each family to the spreadsheet
			int row = 1;
			for (Family f : families) {
				int i = 0;
				i = writeParentNames(excelSheet, row, f, i);
				addLabel(excelSheet, i++, row, f.homePhone);
				addLabel(excelSheet, i++, row, f.fathersEmail);
				addLabel(excelSheet, i++, row, f.mothersEmail);
				
				if (f.volunteering.alreadyVolunteering.equals("Yes")) {
					addLabel(excelSheet, i++, row, f.volunteering.existingPosition);
				}
				else if (f.volunteering.eventOptions != null) {
					for (String option : f.volunteering.eventOptions) {
						addLabel(excelSheet, i++, row, option);

					}
				}
				else {
					for (String option : f.volunteering.monthlyOptions) {
						addLabel(excelSheet, i++, row, option);
					}
				}
				
				i = 7;
				addLabel(excelSheet, i++, row, f.volunteering.additionalOptions);
				addLabel(excelSheet, i++, row, f.volunteering.societyAdminContactPlease);
				


				row++; // increment row count
			}

			workbook.write();
			workbook.close();
		}

		
		
		  private void createVolunteerOutcomesLabels(WritableSheet sheet)
		      throws WriteException {
		    // Lets create a times font
		    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
		    // Define the cell format
		    times = new WritableCellFormat(times10pt);
		    // Lets automatically wrap the cells
		    times.setWrap(true);

		    // create create a bold font with underlines
		    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
		        UnderlineStyle.SINGLE);
		    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
		    // Lets automatically wrap the cells
		    timesBoldUnderline.setWrap(true);

		    CellView cv = new CellView();
		    cv.setFormat(times);
		    cv.setFormat(timesBoldUnderline);
		    cv.setAutosize(true);

		    // Write a few headers
		    int i = 0;
		    addCaption(sheet, i++, 0, "Family");
		    addCaption(sheet, i++, 0, "Home Phone");
		    addCaption(sheet, i++, 0, "Father's Email");
		    addCaption(sheet, i++, 0, "Mother's Email");
		    addCaption(sheet, i++, 0, "Selection #1");
		    addCaption(sheet, i++, 0, "Selection #2");
		    addCaption(sheet, i++, 0, "Selection #3");
		    addCaption(sheet, i++, 0, "Comments");
		    addCaption(sheet, i++, 0, "SA to Contact?");

		  }

		  

		  	// write school directory spreadsheet for Ann 
			public void writeSchoolDirectory() throws IOException, WriteException {
				File file = new File(outputFile);
				WorkbookSettings wbSettings = new WorkbookSettings();

				wbSettings.setLocale(new Locale("en", "EN"));

				WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
				workbook.createSheet("Financial Information", 0);
				WritableSheet excelSheet = workbook.getSheet(0);
				createSchoolDirectoryLabels(excelSheet);
				
				// write each family to the spreadsheet
				int row = 1;
				for (Family f : families) {
					int i = 0;
					i = writeParentNames(excelSheet, row, f, i);
					addLabel(excelSheet, i++, row, f.homePhone);
					addLabel(excelSheet, i++, row, f.fathersEmail);
					addLabel(excelSheet, i++, row, f.mothersEmail);
					
					if (f.volunteering.alreadyVolunteering.equals("Yes")) {
						addLabel(excelSheet, i++, row, f.volunteering.existingPosition);
					}
					else if (f.volunteering.eventOptions != null) {
						for (String option : f.volunteering.eventOptions) {
							addLabel(excelSheet, i++, row, option);

						}
					}
					else {
						for (String option : f.volunteering.monthlyOptions) {
							addLabel(excelSheet, i++, row, option);
						}
					}
					
					i = 7;
					addLabel(excelSheet, i++, row, f.volunteering.additionalOptions);
					addLabel(excelSheet, i++, row, f.volunteering.societyAdminContactPlease);
					


					row++; // increment row count
				}

				workbook.write();
				workbook.close();
			}

			/**
			 * @param excelSheet
			 * @param row
			 * @param f
			 * @param i
			 * @return
			 * @throws WriteException
			 * @throws RowsExceededException
			 */
			private int writeParentNames(WritableSheet excelSheet, int row,
					Family f, int i) throws WriteException,
					RowsExceededException {
				if (f.fathersLastName != null && f.fathersLastName.equals(f.mothersLastName)) {
					addLabel(excelSheet, i++, row, f.fathersLastName + ", " + f.fathersFirstName + " & " + f.mothersFirstName);
				}
				else {
					addLabel(excelSheet, i++, row, f.fathersLastName + ", " + f.fathersFirstName + "\n" + f.mothersLastName + ", " + f.mothersFirstName);
				}
				return i;
			}

			
			
			  private void createSchoolDirectoryLabels(WritableSheet sheet)
			      throws WriteException {
			    // Lets create a times font
			    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
			    // Define the cell format
			    times = new WritableCellFormat(times10pt);
			    // Lets automatically wrap the cells
			    times.setWrap(true);

			    // create create a bold font with underlines
			    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
			        UnderlineStyle.SINGLE);
			    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
			    // Lets automatically wrap the cells
			    timesBoldUnderline.setWrap(true);

			    CellView cv = new CellView();
			    cv.setFormat(times);
			    cv.setFormat(timesBoldUnderline);
			    cv.setAutosize(true);

			    // Write a few headers
			    int i = 0;
			    addCaption(sheet, i++, 0, "Family");
			    addCaption(sheet, i++, 0, "Home Phone");
			    addCaption(sheet, i++, 0, "Father's Email");
			    addCaption(sheet, i++, 0, "Mother's Email");
			    addCaption(sheet, i++, 0, "Selection #1");
			    addCaption(sheet, i++, 0, "Selection #2");
			    addCaption(sheet, i++, 0, "Selection #3");
			    addCaption(sheet, i++, 0, "Comments");
			    addCaption(sheet, i++, 0, "SA to Contact?");

			  }

		  
} 