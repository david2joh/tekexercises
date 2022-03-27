package jpa.mainrunner;

import jpa.entitymodels.Course;
import jpa.service.*;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SMSRunner {
    static CourseService cs = smsGetCourseService.getCourseService();
    static StudentService ss = smsGetStudentService.getStudentService();

    public static void main(String[] args) {
        SessionFactory factory = smsGetSessionFactory.getFactory();   //Initializing this here
        if (factory == null) {  //Something went terribly wrong
            System.out.println("\n*** Could not connect to Database : Please try again Later ***");
            return;
        }

        boolean completed = false;
        Scanner kb = new Scanner(System.in);
        Integer choice = 0;
        Integer subchoice = 0;
        Integer courseChoice = 0;
        String sEmail = null;
        while (!completed) {
            displayMainMenu();
            choice = tryParseInt(kb, "Please enter a valid number 1 or 2");
            if (choice == 0) break;
            switch (choice) {
                case 1:
                    sEmail = null;
                    sEmail = enterCredentials(kb);
                    if (sEmail == null) {
                        System.out.println(" Please Enter a Valid Email and Password ");
                        break;
                    }
                    List<Course> sCourses = ss.getStudentCourses(sEmail);
                    emitStudentCourses(sCourses);
                    subchoice = tryParseInt(kb, "Please enter a valid number 1 or 2");
                    if (subchoice == 0) break;
                    switch (subchoice) {
                        case 1:
                            while(!completed) {
                                List<Course> courses = cs.getAllCourses();
                                courseChoice = emitCoursesandGetRequest(kb, courses);
                                System.out.println("Chose course ID = " + courseChoice);
                                if (courseChoice == 0) {
                                    break;
                                } else if (courseChoice == -1) {
                                    completed = true;
                                } else {
                                    ss.registerStudentToCourse(sEmail, courseChoice);
                                    sCourses = ss.getStudentCourses(sEmail);
                                    emitStudentCourses(sCourses);
                                }
                            }
                            break;
                        case 2:
                            completed = true;
                            break;
                        default:
                            System.out.println("Please enter a valid number 1 or 2");
                            break;
                    }
                    break;
                case 2:

                    completed = true;
                    break;
                default:
                    System.out.println("Please enter a valid number 1 or 2");
                    break;
            }
        }
        System.out.println("--- GoodBye !");
        factory.close();
    }


    private static void displayMainMenu() {
        System.out.printf("Are you a(n)%n 1. Student%n 2. Quit%n%nPlease enter 1 or 2%n");
    }

    private static String enterCredentials(Scanner kb) {
        boolean result = false;
        String sEmail = null;
        System.out.println("Enter Your Email");
        sEmail = kb.nextLine().trim();
        if ((sEmail == null) || (sEmail.isEmpty()) || (sEmail.isBlank())) {
            return null;
        }
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(sEmail);
        if (!matcher.matches()) {
            return null;
        } //bad email
        System.out.println("Enter Your Password");
        String sPass = null;
        sPass = kb.nextLine().trim();
        if ((sPass == null) || (sPass.isEmpty()) || (sPass.isBlank())) {
            return null;
        }
        try {
            result = ss.validateStudent(sEmail, sPass);
            if (!result) {
                sEmail = null;
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            sEmail = null;
        }
        return sEmail;
    }

    private static void emitStudentCourses(List<Course> sCourses) {
        System.out.printf("My Classes:%n%-5s %-35s %-35s%n", "ID", "Course Name", "Instructor Name");
        if (!(sCourses == null)) {
            for (Course course : sCourses) {
                System.out.printf("%-5d %-35s %-35s%n", course.getCId(), course.getCName(), course.getCInstructorName());
            }
            System.out.printf("%n%n 1.  Register to Class%n 2.  Logout%n");
        }
    }

    private static Integer emitCoursesandGetRequest(Scanner kb, List<Course> courses) {
        int courseId = 0;
        System.out.printf("All Classes:%n%-5s %-35s %-35s%n", "ID", "Course Name", "Instructor Name");
        List<Integer> availCourses;
        if (!(courses == null)) {
            availCourses = new ArrayList<>();
            for (Course course : courses) {
                System.out.printf("%-5d %-35s %-35s%n", course.getCId(), course.getCName(), course.getCInstructorName());
                availCourses.add(course.getCId());
            }
            System.out.printf("%n%n Which Course?  (-1 to Logout) :");
            courseId = tryParseInt(kb, "Please enter a valid course Id Number");
            if (courseId == -1) {return courseId;}
            boolean found = false;
            //make sure the entered courseId matches a courseId available
            for (int i = 0; i < availCourses.size(); i++) {
                if (availCourses.get(i) == courseId) {
                    found = true;
                    return courseId;
                }
            }
            if (!found) courseId = 0;
        }
        return courseId;
    }


    public static Integer tryParseInt(Scanner kb, String msg) {
        try {
            return Integer.parseInt(kb.nextLine());
        } catch (NumberFormatException e) {
            System.out.printf("%n%s%n",msg);
            return 0;
        }
    }


}  //CLASS

