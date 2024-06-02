# JFS6WDE_CAPSTONE_MINIPROJECT-1

Its an Employee Management Application developed in java with Springboot framework.

DATA in EmployeManagementApplication folder :-

1.pom.xml -> This file holds all the dependency of this project.
2.src/main/java -> This folder holds the main java programs.

--------Inside src/main/java there are 5 important packages.------

1.Base Package -> Contain Main SpringBoot run application class.

2.Controller package -> Contain 2 controllers.
- Employee Controller - for employee application endpoints.
- Swagger Controller - for swagger api endpoints.

3.Entities package -> Contain all entity files
- Employee - This file holds the main data variables of this      application.

4.Repository package -> Contains Repository class of this applications.

5.Service package -> Contains all the bussiness logic of this application.

------Inside src/main/resources there is 1 file and 1 folder------

1.template folder -> It contains 4 thymleaf templates.
addemployee.html - page add an employee.
index - page to show all employees that are added.
updateemployee - page to update employee data.
viewemployee - page to view a specific employee data.

2.application properties -> this file is very important for the configuration of the database.

----Inside src/test/java there is package for testing purpose.---

EmployeeControllerTest -> contains all the test cases of this application.

------------------------------------------------------------------

--------------STEPS TO RUN AND USE THE APPLICATION---------------

Step 1 -> Configure the application.properties according to the database you want to use.

I am using MYSQL H2 console.
To open H2 consoele run the application and use endpoint ->
localhost:port_number/h2-console
By default port number  - 8080

my h2 console endpoint -> localhost:8080/h2-console

You can also use your local database just configure the application.properties accordingly

Step 2 -> Go to the base package and run the main springboot application class.

if the console shows "Employee Management Application is running" that means your application is running completely fine otherwise there is some issue that you need to resolve first.
After running the application check use the endpoint to navigate.

for example -> localhost:8080/index for index page

Step 3 -> Inside index page there is a button name "Add Employee"

click on Add employee button to add an employee details this button will navigate you to localhost:8080/addEmployee
After submiting employee details you will be nevigated towad index page where you can see the added employees.

Step 4 -> With the employee details on the index page you can see the Action cloumn with 3 buttons

view -  to view that employee details.
update -  to update that employee details.
delete - to delete that employee from the list.

------------------------------------------------------------------
Note :- You can use Swagger api to check if the end points working properly or not.

-------------------How to use Swagger Api--------------------

After running application ->

Go to -> localhost:8080/swagger-ui.html

after nevigating to swagger api many methods are given for crud operation

For example :

saveEmployee -> to save an employee data.
deleteEmployee by id->  to delete an employee data by id.
updateEmployee by id -> to update employee data by id.
getEmployeeById -> to get employee data of specific id.
getAllEmployee -> to see all the saved employees.

