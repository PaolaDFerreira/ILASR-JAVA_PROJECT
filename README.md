# ILASR PROJECT:
ILASR (Innovate, learn and Share recipes) was a project made during Programming Language subject (2021), in the IT course. With the objective of creating a functional code, using what was learned in class about object orientation, programming in java language and uniting what had been studied about SQL, the project was built using MySql Workbench and Eclipse IDEs.

# PROGRAMMING LANGUAGES AND TECHNOLOGIES USED:
A simple project made using Java, in the Eclipse IDE, with a database created in MySQL. Through a property file, using the .properties extension, together with the JDBC mechanism, knowledge in object orientation and inheritance in java, a connection was established between the code made in java and the database present in MySQL.
Thus, through methods and functions, it was possible to change, delete and add registration data for an account and recipe, for example.
The project has a series of functions displayed in an IDE terminal, which can be chosen by typing a number, such as '1' for "create an account". 


# MAKING ILASR WORK:
For ILASR to work, after downloading the ILASR_Project folder, some steps must be followed. Note that the ILASR project was made in eclipse and mysql, so it might work differently in another IDE:

1. Inside the ILASR folder, create a file called “db.properties”. This file will contain the username, password and dburl of your mysql connection.

2. In the file, you can copy and paste the following commands:

     User = (MYSQL USERNAME THAT WILL BE USED)

      Password = (MYSQL CONNECTION PASSWORD)

     Dburl = jdbc:mysql://(HOSTNAME):(PORT)/ilasr

      useSSL=false

3. after logging into the mysql connection, in a new file, copy, paste and execute the commands from the file "sql Commands.txt".

# VISUAL PART OF THE PROJECT:

In this project, there are 3 menus. You can perform actions such as: 

- creating an account
- logging into the acount
- leave the project
- Delete current account
- Go to recipe menu
- Update account
- show current account details
- Access a list of accounts, seeing only what has been allowed, such as a username and a brief summary of the account
- Search for an account by id number
- Post a recipe
- Access a list of recipes
- Delete a recipe belonging to the account in which the user is logged in
- Search for a recipe
- Update a recipe belonging to the account in which the user is logged in

# TAKEAWAYS:
Facing a challenge and learning something completely new to me, the ILASR project helped me to learn about Object Orientation, improve my programming logic and learn more about Java and SQL languages.
