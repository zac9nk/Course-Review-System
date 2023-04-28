import java.sql.SQLException;
import java.util.*;

import static java.lang.System.exit;

public class Main {

     static ReviewMngr manager;
    public static void main(String args[]) throws SQLException {
            Scanner myObj = new Scanner(System.in);
            manager = new ReviewMngr();
            System.out.println("Welcome to course program! are you a new student or " +
                    "do you already have an account?");
    menu:   while(true){
            System.out.println("Say n for new and e for existing");
            String choice = myObj.nextLine();
            if (choice.equals("n")) {
                System.out.println("Username:");
                String username = myObj.nextLine();
                System.out.println("Password:");
                String password = myObj.nextLine();
                System.out.println("Confirm Password:");
                String pw = myObj.nextLine();
                if (password.equals(pw)) {
                    Student student = new Student(username, password);
                    try {
                        manager.register(student);
                        System.out.println("Registered and Logged In!");
                    }
                    catch(NoSuchElementException e){
                        System.out.println("Username must be unique");
                        continue;
                    }
                } else {
                    System.out.println("error, try again, wrong password");
                    continue;
                }
            } else if (choice.equals("e")) {
                System.out.println("Username:");
                String username = myObj.nextLine();
                System.out.println("Password:");
                String password = myObj.nextLine();
                Student student = new Student(username, password);
                try{
                    manager.login(student);
                    System.out.println("Logged In!");
                }
                catch(NoSuchElementException e){
                    System.out.println("Incorrect username or password");
                    continue;
                }
            }
      choices:      while (true) {
                System.out.println("press 1 to exit, press 2 to select a class for reviewing, 3 for reading review, 4 to return to main menu");
                try {
                    Integer number = myObj.nextInt();
                    switch (number) {
                        case 1:
                            System.exit(0);
                            break;
                        case 2:
                            System.out.println("what is course department?");
                            myObj.nextLine();
                            String d = myObj.nextLine();
                            System.out.println("what is course number?");
                            String num = myObj.nextLine();
                            manager.chooseCourse(new Course(d,Integer.parseInt(num)));
                            System.out.println("Enter rating(1-5):");
                            String rate = myObj.nextLine();
                            System.out.println("Add comment:");
                            String msg = myObj.nextLine();
                            try {
                                manager.rate(msg, Integer.parseInt(rate));
                                System.out.println("Done!");
                            }
                            catch (NoSuchElementException e) {
                                System.out.println("You have already written a review for this Course");
                            }
                            continue;
                        case 3:
                            System.out.println("what is course department?");
                            myObj.nextLine();
                            String dep = myObj.nextLine();
                            System.out.println("what is course number?");
                            String id = myObj.nextLine();
                            manager.chooseCourse(new Course(dep,Integer.parseInt(id)));
                            printReviews(manager.output());
                            System.out.println("Average Rating: " + manager.Average());
                            continue;
                        case 4:
                            break choices;
                        default:
                            System.out.println("wrong number");
                            break;
                    }
                    break choices;
                } catch (InputMismatchException e) {
                    System.out.println("wrong inputt");
                    break choices;
                }
            }
        }
    }
    public void checker(String choice){
        if(choice.equals("n")){

        }

    }
    public static void printReviews(List<Review> l) throws  SQLException{
        int i = 1;
        for(Review r : l){
            System.out.println(i + ": " + r.getCourse().getDepartment() + " " + r.getCourse().getNumber() + " | "
            + r.getStudent().getUser() + " | " + r.getRating() + " | " + r.getMessage());
            i++;
        }
    }
}
