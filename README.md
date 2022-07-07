This is the implementation of the project in PADSOFT.
The author of this implementation is Karol Smółka

How to compile: Be in the path deliveryApp/src/main/java and run the bash script ./compileAll.sh
How to run: Be in the same path as above and run the bash script ./runMain.sh


The gui has been made with the swing library as showed in the class.

The admin (operator) credentials are username: admin password: admin
Via admin, drivers can be created and be assigned to trucks that are loaded from 
the given "fleet.txt" file. ALso via the sign in page, someone can create company users
in order to test the implementation of the app. 

How to test the app : 
1. Login to admin with username: admin, password: admin
2. Create a driver via the New driver page in admin
3. For the need of testing the app, a button on the admin (operator) has been made 
with the name create packages. This button will create packages in order to make more easy
the testing of the app.
4. Click assign truck to driver in order to have at least 1 driver assigned with at truck
5. Experiment with the whole app, sign up to a user and the login to him, login to the driver etc!

Invoices are created in pdf format in the /tmp/ folder.

Α .json file keeps all the users after the end of the application.


Note1: Through out my code, instead of doing System.out.println("..."), 
sometimes we do java.lang.System.out.println("..."). 
This is because, in the package engine, there exists a class System, and thus,
overwrites the System class of Java.
