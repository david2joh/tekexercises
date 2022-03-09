package sdc;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class ClubMemberApp {
    public static void main(String[] args) throws FileNotFoundException {
        SoftwareDeveloperClub sdc = new SoftwareDeveloperClub("C:\\Users\\D.DW7-64\\Downloads\\TEK\\exercises\\members.txt");
 //       System.out.println(sdc);
        System.out.println("-- Part 1 of assignment--\n");
        System.out.println(sdc.streamToString()); //added for no other reason than to play with streams
        System.out.println("-------------------------\n");
    boolean completed = false;
    int choice = 0;
    Scanner kb = new Scanner(System.in);
    while(!completed) {
        System.out.printf("%nSelect from the following Menu%n1: Display Members%n2. Remove Member%n3. Add Member%n4. Quit%n");
        choice = Integer.parseInt(kb.nextLine()); // no I'm not checking for illegal format
        switch (choice) {
            case 1:
                System.out.println("-- All Hail the Great and Ancient Order of the SDC *snicker* --\n");
                System.out.println(sdc.streamToString()); //added for no other reason than to play with streams
                System.out.println("---------------------------------------------------------------\n");
                break;
            case 2:
                System.out.println("--  Select a member to Remove  --");
                int i = 1;
                for ( ; i<= sdc.size(); i++) {
                    System.out.println("Member #" + i);
                    System.out.println(sdc.get(i-1).toString());
                }
                int memNum = Integer.parseInt(kb.nextLine());
                if (memNum <1 || memNum > sdc.size()) {
                    System.out.println("**YOU** will be terminated with prejudice\n");
                    completed = true;
                    //well if they can't choose wisely it's off the bridge with them !
                }
                else {
                    System.out.println("Dalek sounds .. EXTERMINATE   #" + memNum);
                    sdc.remove(memNum - 1);
                }
                break;
            case 3:
                addMember(sdc,kb);
                break;
            case 4:
                completed = true;
                break;
            default:
                System.out.println("\nEnter a valid choice dummy !\n");
         }
        }


    } //main

    private static void addMember(SoftwareDeveloperClub sdc,Scanner kb) {
        System.out.println("Enter the new Member Name");
        String name = kb.nextLine();
        System.out.println("Enter the new Member City");
        String city = kb.nextLine();
        System.out.println("Enter the new Member State");
        String state = kb.nextLine();
        System.out.println("Enter the new Member Favorite Programming Language");
        String lang = kb.nextLine();
        ClubMember cm = new ClubMember(name,city,state,lang);
        sdc.add(cm);
    }

} //CLASS
