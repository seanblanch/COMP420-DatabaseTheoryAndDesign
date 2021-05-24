//Sean Blanchard
//4/6/2021
//COMP420 - FINAL PROJECT

package com.company;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class CRUDFunctions {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Insert a new person\n"
                + "2. Purchase tickets\n"
                + "3. Change feeding Schedule\n"
                + "4. Delete an Employee\n"
                + "5. Views\n"
                + "6. Triggers\n"
                + "7. Close Program Gracefully");
        String input = scan.nextLine();
        while(!input.equals("7"))
        {
            switch(input) {
                case "1":
                    insertPerson();
                    break;
                case "2":
                    purchaseTicket();
                    break;
                case "3":
                    feedingSchedule();
                case "4":
                    deleteEmployee();
                    break;
                case "5":
                    views();
                    break;
                case "6":
                    triggers();
                    break;
                case "7":
                    break;
                default:
                    System.out.println("not a valid entry");
                    break;
            }
            System.out.println("1. Insert a new person\n"
                    + "2. Purchase tickets\n"
                    + "3. Change feeding Schedule\n"
                    + "4. Delete an Employee\n"
                    + "5. Views\n"
                    + "6. Triggers\n"
                    + "7. Close Program Gracefully");
            input = scan.nextLine();
        }
        System.out.println("You have closed the program.");

    }

    private static void report(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Intput akas title: ");
        String sql = scan.nextLine();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String uid="root";
            String pwd="comp420";
            con = DriverManager.getConnection("jdbc:mysql:///imdb", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");
            Statement statement = con.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT title_id FROM imdb.akas WHERE title = '" + sql + "';");
            System.out.println("title_id: corresponding with the title " + sql );
            while (resultSet.next()){
                String title = resultSet.getString("title_id");

                System.out.printf(title + "\n");
            }
        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void update() {

        Scanner scan = new Scanner(System.in);
        System.out.println("Enter UPDATE SQL code: (UPDATE table_name\n" +
                "SET column1 = value1, column2 = value2, ...\n" +
                "WHERE condition;)\n" +
                "UPDATE Customers\n" +
                "SET ContactName = 'Alfred Schmidt', City= 'Frankfurt'\n" +
                "WHERE CustomerID = 1;");
        String sql = scan.nextLine();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String uid="root";
            String pwd="comp420";
            con = DriverManager.getConnection("jdbc:mysql:///imdb", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void delete() {
        Scanner scan = new Scanner(System.in);
//        System.out.println("Enter Person ID: ");
//        String ID = scan.nextLine();
        System.out.println("Enter DELETE SQL code: (DELETE FROM table_name WHERE condition;)(DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';)");
        String sql = scan.nextLine();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String uid="root";
            String pwd="comp420";
            con = DriverManager.getConnection("jdbc:mysql:///imdb", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");
            Statement statement = con.createStatement();
            statement.executeUpdate(sql);
        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }

    }

    private static void search() {
        Scanner scan = new Scanner(System.in);
        //System.out.println("Enter First and Last Name to Search: ");
        //String Search = scan.nextLine();
        System.out.println("Enter SELECT SQL code: (SELECT column1, column2, ...\n" +
                                                    "FROM table_name;) (SELECT CustomerName, City FROM Customers;)");
        String sql = scan.nextLine();
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String uid="root";
            String pwd="comp420";
            con = DriverManager.getConnection("jdbc:mysql:///imdb", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");
            Statement statement = con.createStatement();
            //Result set get the result of the SQL query
            //ResultSet resultSet = statement.executeQuery("select * from people where name like '%"+Search+"%'");
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                String ID = resultSet.getString("title_id");
                String Name = resultSet.getString("title");
                String DOB = resultSet.getString("region");
                String Death = resultSet.getString("language");
                System.out.println(ID+"\t"+Name+"\t"+DOB+"\t"+Death+"\t");
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }

    }

    private static void insertPerson() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the person's information!");
        System.out.println("Digit Personal Key Number(Less than 9999999999): ");
        int id = scan.nextInt();
        //System.out.println("First Name: ");
        String rName = scan.nextLine();
        System.out.println("First Name: ");
        String fName = scan.nextLine();
        System.out.println("Last Name: ");
        String lName = scan.nextLine();
        System.out.println("Street: ");
        String street = scan.nextLine();
        System.out.println("City: ");
        String city = scan.nextLine();
        System.out.println("State: ");
        String state = scan.nextLine();
        System.out.println("Zip: ");
        String zip = scan.nextLine();
        System.out.println("Email: ");
        String email = scan.nextLine();
        System.out.println("Phone: ");
        String phone = scan.nextLine();
        System.out.println("Date of Birth (ex. 1993-08-08):");
        String dob = scan.nextLine();

        System.out.printf("Is this an employee? (y/n): ");
        String employee = scan.nextLine();

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");
            Statement statement = con.createStatement();
            //statement.executeUpdate("insert into people(person_id, name, born, died) values('"+ID+"','"+Name+"','"+DOB+"','"+Death+"')");
            statement.executeUpdate("insert into person(person_ID, person_FN, person_LN, person_street, person_city," +
                    " person_state, person_zip, person_email, person_phone, person_DOB)" +
                    " values('"+id+"','"+fName+"','"+lName+"','"+street+"','"+city+"','"+state+"','"+zip+"','"+email+"','"+phone+"','"+dob+"')");
            //statement.executeUpdate(insert into animal(animal_ID) values(2));
            //statement.executeUpdate(sql);
        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }

    }


    //Ticket prices are 15 dollars per entrance
    private static void purchaseTicket() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the information to make a ticket purchase!");
        System.out.println("First and Last Name: ");
        String fName = scan.nextLine();
        System.out.println("Visitors Digit Personal Key Number(Less than 9999999999): ");
        int id = scan.nextInt();
        //System.out.println("First Name: ");
        String rName = scan.nextLine();
        System.out.println("Membership ID(if applicable): ");
        String memberID = scan.nextLine();
        System.out.println("How many tickets?: ");
        int ticket = scan.nextInt();

        int ticketOverallPrice = ticket * 15;

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement2 = con.createStatement();
            statement2.executeUpdate("insert into visitor(Visitor_ID, Visitor_Membership, Person_ID) values('"+id+"','"+memberID+"','"+id+"')");

            Statement statement = con.createStatement();
            statement.executeUpdate("insert into admission(visitor_ID, admission_cost, Admission_name, admission_duration," +
                    " admission_date)" +
                    " values('"+id+"','"+ticketOverallPrice+"','"+fName+"','All Day',NOW())");

            System.out.println("That will be " + ticketOverallPrice + " Dollars.");
        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }

    }

    private static void feedingSchedule() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the information to change feeding schedule!");
        System.out.println("Species ID Number: ");
        int id = scan.nextInt();
        //System.out.println("First Name: ");
        String needed1 = scan.nextLine();
        System.out.println("Food Type: ");
        String foodType = scan.nextLine();
        System.out.println(foodType);
        System.out.println("Food Amount: ");
        int foodAmount = scan.nextInt();
        String needed2 = scan.nextLine();
        System.out.println(foodAmount);
        System.out.println("Feeding Time: ");
        String foodTime = scan.nextLine();
        System.out.println(foodTime);

        char ch = '"';


        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();
            statement.executeUpdate("update feeding set food_type = " + ch + foodType + ch + ", food_amount = " + foodAmount + ", food_time = " + ch + foodTime + ch + " where species_id = " + id + ";");

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }

    }

    private static void deleteEmployee() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Please enter the information to delete an employee!");
        System.out.println("Employee ID: ");
        int employeeId = scan.nextInt();
        //System.out.println("First Name: ");
        String needed1 = scan.nextLine();
        System.out.println("Person ID: ");
        int personId = scan.nextInt();
        String needed2 = scan.nextLine();

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();
            Statement statement2 = con.createStatement();
            //statement.executeUpdate("insert into people(person_id, name, born, died) values('"+ID+"','"+Name+"','"+DOB+"','"+Death+"')");
            statement.executeUpdate("delete from employee where emp_id = " + employeeId + ";");
            statement2.executeUpdate("delete from person where person_id = " + personId + ";");
            System.out.printf("Employee Sucessfully Deleted.");

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }

    }


    private static void views() {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. Find each duty given in the past year\n"
                + "2. Number of employees in the database\n"
                + "3. Number of animals in the database\n"
                + "4. Which species eats what food\n"
                + "5. How many tickets were sold the day\n" +
                "prior\n"
                + "6. Returns people’s names in the database\n" +
                "that start with ‘A’\n"
                + "7. The number of species in the database\n"
                + "8. List the name of all the species in the\n" +
                "database\n"
                + "9. Returns the exhibit hours for bears\n"
                + "10. Returns the exhibit location for bears\n"
                + "11. Returns the day of the last\n" +
                "administered animal in the database\n"
                + "12. A given person’s ID number will\n" +
                "return their first and last name\n"
                + "13. Close out of views\n");
        String input = scan.nextLine();
        while(!input.equals("13"))
        {
            switch(input) {
                case "1":
                    view1();
                    break;
                case "2":
                    view2();
                    break;
                case "3":
                    view3();
                    break;
                case "4":
                    view4();
                    break;
                case "5":
                    view5();
                    break;
                case "6":
                    view6();
                    break;
                case "7":
                    view7();
                    break;
                case "8":
                    view8();
                    break;
                case "9":
                    view9();
                    break;
                case "10":
                    view10();
                    break;
                case "11":
                    view11();
                    break;
                case "12":
                    view12();
                    break;
                case "13":
                    break;
                default:
                    System.out.println("not a valid entry");
                    break;
            }
            System.out.println("1. Find each duty given in the past year\n"
                    + "2. Number of employees in the database\n"
                    + "3. Number of animals in the database\n"
                    + "4. Which species eats what food\n"
                    + "5. How many tickets were sold the day\n" +
                    "prior\n"
                    + "6. Returns people’s names in the database\n" +
                    "that start with ‘A’\n"
                    + "7. The number of species in the database\n"
                    + "8. List the name of all the species in the\n" +
                    "database\n"
                    + "9. Returns the exhibit hours for bears\n"
                    + "10. Returns the exhibit location for bears\n"
                    + "11. Returns the day of the last\n" +
                    "administered animal in the database\n"
                    + "12. A given person’s ID number will\n" +
                    "return their first and last name\n"
                    + "13. Back to main menu\n");
            input = scan.nextLine();
        }
        System.out.println("You have exited the views menu.");

    }

    private static void view1() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select duty_name from duty inner join assignment on assignment.duty_id = duty.duty_id where assignment_begin_date between date_sub(now(), interval 1 year) and now();";

            ResultSet result = statement.executeQuery(sql);

            while (result.next())
            {
                System.out.println("Each duty given in the past year: " + result.getString("duty_name"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view2() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select count(emp_id) from employee";

            ResultSet result = statement.executeQuery(sql);

            result.next();

            int count = result.getInt(1);

            System.out.println("The number of employees in database: " + count);

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view3() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select count(animal_id) from animal;";

            ResultSet result = statement.executeQuery(sql);

            result.next();

            int count = result.getInt(1);

            System.out.println("The number of employees in database: " + count);

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view4() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select species_name, food_type from species inner join feeding on species.species_id = feeding.species_id;";

            ResultSet result = statement.executeQuery(sql);

            while (result.next())
            {
                System.out.println("Species: " + result.getString("species_name"));
                System.out.println("Eats: " + result.getString("food_type"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view5() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select count(*) from admission where admission_date = date_sub('2021-05-23', interval 1 day);";

            ResultSet result = statement.executeQuery(sql);

            result.next();

            int count = result.getInt(1);

            System.out.println("The number of tickets sold the day prior: " + count);

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view6() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select person_fn, person_ln from person where person_fn like 'R%';";

            ResultSet result = statement.executeQuery(sql);

            while (result.next())
            {
                System.out.print("Returns people's name that start with 'R': " + result.getString("person_fn") + " ");
                System.out.println(result.getString("person_ln"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view7() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select count(species_id) from species;";

            ResultSet result = statement.executeQuery(sql);

            result.next();

            int count = result.getInt(1);

            System.out.println("The number of species at the Zoo: " + count);

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view8() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select species_name from species;";

            ResultSet result = statement.executeQuery(sql);

            System.out.println("Name of all the species in the database: ");

            while (result.next())
            {
                System.out.println(result.getString("species_name"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view9() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select exhibit_hours from exhibit inner join species on species.species_id = exhibit.species_id where species_name = 'Mammal';";

            ResultSet result = statement.executeQuery(sql);

            System.out.println("Exhibit hours for Bears: ");

            while (result.next())
            {
                System.out.println(result.getString("exhibit_hours"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view10() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select exhibit_location from exhibit inner join species on species.species_id = exhibit.species_id where species_name = 'Mammal';";

            ResultSet result = statement.executeQuery(sql);

            System.out.println("Exhibit location for Bears: ");

            while (result.next())
            {
                System.out.println(result.getString("exhibit_location"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view11() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select max(animal_admitdate), animal_name from animal where animal_admitdate = (select max(animal_admitdate) from animal) group by animal_name;";

            ResultSet result = statement.executeQuery(sql);

            System.out.println("Last administered animal date: ");

            while (result.next())
            {
                int count = result.getInt(1);
                System.out.println("date: " + count);
                System.out.println(result.getString("animal_name"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void view12() {

        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid="seanblan_root";
            String pwd="M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();

            String sql = "select person_fn, person_ln from person inner join employee on person.person_id = employee.person_id where emp_id = '77298';";

            ResultSet result = statement.executeQuery(sql);

            System.out.println("Returns employee first and last name with id '77298': ");

            while (result.next())
            {
                System.out.print(result.getString("person_fn") + " ");
                System.out.println(result.getString("person_ln"));
            }

        }
        catch (Exception e){
            System.err.println("Exception:" + e.getMessage());
        }
        finally {
            try {
                if (con != null){
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            }
            catch (SQLException e){
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }


    private static void triggers() {

        Scanner scan = new Scanner(System.in);
        System.out.println("1. Before a ticket purchase can be made, the database will check if the purchaser is over 18 y/o.\n"
                + "2. Before adding a new person to the database, it will check to make sure there are no duplicate entries\n"
                + "3. After deleting an employee, they too will be deleted from the Persons table.\n"
                + "4. Back to main menu\n");
        String input = scan.nextLine();
        while(!input.equals("4"))
        {
            switch(input) {
                case "1":
                    trigger1();
                    break;
                case "2":
                    trigger2();
                    break;
                case "3":
                    trigger3();
                case "4":
                    break;
                default:
                    System.out.println("not a valid entry");
                    break;
            }
            System.out.println("1. Before a ticket purchase can be made, the database will check if the purchaser is over 18 y/o.\n"
                    + "2. Before adding a new person to the database, it will check to make sure there are no duplicate entries\n"
                    + "3. After deleting an employee, they too will be deleted from the Persons table.\n"
                    + "4. Back to main menu\n");
            input = scan.nextLine();
        }
        System.out.println("You have exited the triggers menu.");

    }

    private static void trigger1() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid = "seanblan_root";
            String pwd = "M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();
            //statement.executeUpdate("insert into people(person_id, name, born, died) values('"+ID+"','"+Name+"','"+DOB+"','"+Death+"')");
            statement.execute("create trigger old_enough before insert on admission for each row\n" +
                    "begin\n" +
                    "declare is_of_age int;\n" +
                    "select datediff(now(), person_dob)/365 into is_of_age from person inner join visitor on visitor.person_id = person.person_id inner join admission\n" +
                    "on admission.visitor_id = visitor.visitor_id where visitor_id = new.visitor_id;\n" +
                    "if is_of_age >= 18 then insert into admission\n" +
                    "values(new.ticket_id, new.visitor_id, new.admission_cost, new.admission_name, new.admission_duration, new.admission_date);\n" +
                    "end if;\n" +
                    "end;");

        } catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            } catch (SQLException e) {
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void trigger2() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid = "seanblan_root";
            String pwd = "M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();
            //statement.executeUpdate("insert into people(person_id, name, born, died) values('"+ID+"','"+Name+"','"+DOB+"','"+Death+"')");
            statement.execute("create trigger no_dupes before insert on person for each row\n" +
                    "begin\n" +
                    "declare person_count int;\n" +
                    "select count(*) into person_count from person where person_id = new.person_id;\n" +
                    "if person_count = 0 then insert into person\n" +
                    "values (new.person_id, new.person_fn, new.person_ln, new.person_street, new.person_city, new.person_state, new.person_zip, new.person_email, new.person_phone, new.person_dob);\n" +
                    "end if;\n" +
                    "end;");

        } catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            } catch (SQLException e) {
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }

    private static void trigger3() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String jdbcURL = "jdbc:mysql://%s:%d/%s";
            String uid = "seanblan_root";
            String pwd = "M@gnum15";
            con = buildConnection(jdbcURL, "seanblanch.cikeys.com", 3306, "seanblan_comp_420_spring_2021", uid, pwd);
            if (!con.isClosed())
                System.out.println("Connected to MySQL server.");

            Statement statement = con.createStatement();
            //statement.executeUpdate("insert into people(person_id, name, born, died) values('"+ID+"','"+Name+"','"+DOB+"','"+Death+"')");
            statement.execute("create trigger after_delete after delete on employee for each row\n" +
                    "begin\n" +
                    "delete from person where person_id = old.person_id;\n" +
                    "end;");

        } catch (Exception e) {
            System.err.println("Exception:" + e.getMessage());
        } finally {
            try {
                if (con != null) {
                    con.close();
                    System.out.println("Disconnected from MySQL server");
                }
            } catch (SQLException e) {
                System.err.println("Exception:" + e.getMessage());
            }
        }
    }



    public static Connection buildConnection(String jdbcURL, String host, int port, String schema, String user, String password) throws SQLException {
        return DriverManager.getConnection(String.format(jdbcURL, host, port, schema), user, password);
    }

}

