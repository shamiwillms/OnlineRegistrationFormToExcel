OnlineRegistrationFormToExcel
=============================

This is a simple program (mostly hacked together) that will read the xml from a Form Assmebly online form (using the xml output).

http://www.formassembly.com/

This takes the xml, reads it into memory and then outputs as excel spreadsheets for various purposes.

To run, just import project into Eclipse and run as a java application.  The xml file is hardcoded into the program.


Debugging
To add missing id's go to the form assembly website, configure the form and click on the "Publish" tab.  At the bottom of the page is "Show how to prefill form data dynamically".  This will show you all of the tfa_* field names to use.  It really helps speed up parsing the file for new fields.