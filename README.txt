Author: Nauman Aman

Email: nauman.aman@ieee.org

# Breif introduction to the project:

This a fully functional application for payroll report accomplished as a result of wave SE coding challenge. This application was orignally using NodeJS on the backend; however, I have redesigned and developed it into a Java (Sprint Boot) backend application over the weekend.


# Major steps overview:

-Step A: Downloading and Installing H2 Database

-Step B: Download and Install Java and Tomcat

-Step C: Make sure you have unzipped/unbundled the source file 

-Step D: Open browser to Firefox/Chrome to enjoy the app.


# Step A: Downloading and Installing H2 Database

If you have windows 64 bit, you can use the following link: https://h2database.com/h2-setup-2019-10-14.exe or select your appropriate OS/version: https://www.h2database.com/html/main.html .

Once H2 has been installed and running, we have to do following steps:

1) Open H2 Web console.

2) Use default credentials (use default settings wherever possible):
*user=sa
*password=    ... leave blank

3) Create in memory DB called: payrollDB (JDBC URL: jdbc:h2:mem:payrollDB)


4) Leave the table creations to JPA / Hibernate (i.e. code will take care of creating the DDLs).



# Step B: Download and Install Java and Tomcat

1) Use the following link to download and install Java (prefer the msi link): https://adoptopenjdk.net/

2) Choose the latest Java version and JVM type.

3) Following instructions to install Java on your machine.

4) Copy the Apache Tomcat binaries (zip) from https://tomcat.apache.org/download-90.cgi, or directly from following link: https://downloads.apache.org/tomcat/tomcat-9/v9.0.44/bin/apache-tomcat-9.0.44-windows-x64.zip

5) Open your prefered IDE and use maven plugin to build WAR file: payroll.war.



# Step C: Make sure you have unzipped/unbundled the source file 

1) Make sure you have unzipped/unbundled WAR file to Tomcat installation location on your filesystem. Example: Place payroll.war file to C:\Tools\Tomcat\webapps

2) Unzipped/unbundled the folder wave (angular frontend components) to same location as step 1. Example: Place wave foldder to C:\Tools\Tomcat\webapps

2) Open up cmd / command line, and go to the Tomcat's bin directory: cd C:\Tools\Tomcat\bin

3) Make sure there exists a file called startup.bat, and run following command on cmd: startup.bat



# Step D: Open browser to Firefox/Chrome to enjoy the app.

1) Open prefered browser FireFox.

2) Make sure you have internet as some of the CDN Repositories are online, such as angular-ui.github.io/bootstrap/ui-bootstrap-tpls-0.11.0.js (all of them are listed at waveDashboard.html).

3) Copy, paste and enter the following link to address bar: http://localhost:8080/wave/

4) Upload 'sample.csv' file, 'confirm' on the dialouge box and test out the app! :)




# Major Design Overview:

Using AngularJS on the frontend. Java on the backend, Spring Boot framework, and H2 relational database.

- \\payroll\src\main\resources\application.properties - contains Spring's JPA / Hibernate and H2 db connection information for the app

- \\payroll\src\main\java - contains the java source file (REST Calls: /metrics, /deploy, /generate, and /generate/metaData)

- \\waveApp\public\ - contains index.html and waveDashboard.html that are the main html files - waveDashboard.html shows manage.html which is at \\waveApp\public\manage.html (manage.html is where the bulk of html work is)

- \\waveApp\public\partials\ - contains partials html pages such as error.html, deploy.html (to send csv data to db), and reportView.html (shows the payroll report for particular reportID).

- \\waveApp\public\partials\js\ - contains the angular components. ctrl.js is the main one that contains the 'app controller' and major bulk of functions. srv.js contains factory services like dataStructureServices, dialougeServices, dataServices - that's used to send/rcv data to backend.




# What's I'm proud of in this implementation?

I'm mostly proud of the time I took to make it look and feel as production ready as possible. I have tried my best to finish all the requirements to the letter and spirit, and in fact I went above and beyond the basic requirments such as charts and meta data display like avg pay person and many more features.

Also, I believe I gave equal attention to the front-end as much as energy I devoted to the back-end and database aspect of the coding assignment.

Some things I took extra care on: 

- pay periods that deal with leap year;

- processing date of the report;

- chart data to visualize payroll analysis;

- meta data for each reportID;

- welcome 'pop-up' that senses the user is new and pops up info/welcome/tips screen; and if user is not new, then display 'regular' app;

- drag-and-drop of csv file and only accepting csv file extensions;

- error msgs pop-ups in case of an unexpected situation to let user know what's happeneing;

- organization of the data presentation and UI layout;

- deducing of 'report id' row from csv file even if there are blank spaces after the report id row (i.e. last row is not the report id row in csv file - like '\\waveApp\public\samples\sample.csv' file);

- and many more other small/big things.




# All the requirements set by SE coding challenge and it's implementation status:

- Implemented:    Your app must accept (via a form) a comma separated file with the schema described in the previous section.

- Implemented:    Your app must parse the given file, and store the timekeeping information in a relational database for archival reasons.

- Implemented:    After upload, your application should display a payroll report. This report should also be accessible to the user without them having to upload a file first.

- Implemented:    If an attempt is made to upload two files with the same report id, the second upload should fail with an error message indicating that this is not allowed.

The payroll report should be structured as follows:

- Implemented:    There should be 3 columns in the report: Employee Id, Pay Period, Amount Paid

- Implemented:    A Pay Period is a date interval that is roughly biweekly. Each month has two pay periods; the first half is from the 1st to the 15th inclusive, and the second half is from the 16th to the end of the month, inclusive.

- Implemented:    Each employee should have a single row in the report for each pay period that they have recorded hours worked. The Amount Paid should be reported as the sum of the hours worked in that pay period multiplied by the hourly rate for their job group.

- Implemented:    If an employee was not paid in a specific pay period, there should not be a row for that employee + pay period combination in the report.

- Implemented:    The report should be sorted in some sensical order (e.g. sorted by employee id and then pay period start.)

- Implemented:    The report should be based on all of the data across all of the uploaded time reports, for all time.
