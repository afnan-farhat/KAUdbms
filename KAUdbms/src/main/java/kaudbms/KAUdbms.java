/*
Name: Afnan Tariq Farhat
ID: 2105973
Section: B0T
Assignment: 3 
Course: CPCS204
Date: Feb/2/2023
Email: ahfarhat@stu.kau.edu.sa
 */
package kaudbms;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class KAUdbms {

    //  Create Objectes to access others classes 
    static KAUdbmsBST myTree = new KAUdbmsBST(); // KAUdbmsBST class >> other methods 
//    static KAUstudent studentInfo = new KAUstudent(); // KAUstudent class >> getter & setter >> getter courses >> access methods 
    static KAUcourse courseInfo = new KAUcourse();// KAUcourse clas >> getter & setter
    static KAUcourses courses = new KAUcourses();// KAUcourses clas >> other methods 

    public static void main(String[] args) throws FileNotFoundException {
        //Create files to read & write it 
        File inFile = new File("KAUdbms.in.txt");
        File outFile = new File("KAUdbms.out.txt");

        Scanner input = new Scanner(inFile);
        PrintWriter pen = new PrintWriter(outFile);

        // if the file does nit exist -> close program
        if (!inFile.exists()) {
            System.out.println("The input file does not exists");
            System.exit(0);
        }
        //-------------------------------------------------------------------------------------------

        // Save the number of command in variable
        int NoOfCommands = input.nextInt();
        String command;

        int i = 1;
        while (i <= NoOfCommands) { // from 1 to 49 (49 commands)
            command = input.next();
            if (command.equalsIgnoreCase("NEWSTUDENT")) {
                pen.println("NEWSTUDENT Command");

                // Save all values in variables 
                int ID = input.nextInt();
                String firstName = input.next();
                String lastName = input.next();
                String email = input.next();
                int age = input.nextInt();
                int phone = input.nextInt();

                // Add a new student to tree.
                myTree.insert(ID, firstName, lastName, email, age, phone);
                pen.println("\t" + firstName + " " + lastName + " (ID " + ID + ") has been inserted as a new student in KAUdbms.");

            } else if (command.equalsIgnoreCase("SEARCHNAME")) {
                pen.println("SEARCHNAME Command");
                // Save all values in variables 
                String firstName = input.next();
                String lastName = input.next();

                //Search on student in tree
                // If student not found -> error
                if (myTree.findNodeName(firstName, lastName) == null) {
                    pen.println("\t" + firstName + " " + lastName + " was not found in FCITbook.");
                }

            } else if (command.equalsIgnoreCase("SEARCHID")) {
                pen.println("SEARCHID Command");

                // Save the value in variable
                int ID = input.nextInt();

                //Search on ID student in tree
                // If student not found -> error
                if (myTree.findNode(ID) == null) {
                    pen.println("\tID " + ID + " was not found in FCITbook.");
                }

            } else if (command.equalsIgnoreCase("ADDCOURSE")) {
                pen.println("ADDCOURSE Command");

                // Save all values in variables 
                String ID = input.next();
                String IDcourse = input.next();
                int grade = input.nextInt();

                // Convert string to integer to using findNode method -> parameter = int
                int ID_int = Integer.parseInt(ID);

                //Search on ID student in tree
                // If student not found -> error
                if (myTree.findNode(ID_int) == null) {
                    pen.println("\tERROR: cannot add course. Student ID # " + ID + " was not found in KAUdbms.");
                } //Otherway, add the course for this student 
                else {
                    AddCourses(IDcourse, grade, ID_int, myTree, myTree.findNode(ID_int), courses, pen);
                }
            } else if (command.equalsIgnoreCase("DELETE")) {
                pen.println("DELETE Command");

                // Save all values in variables 
                String firstName = input.next();
                String lastName = input.next();

                //Search on student in tree
                // If student not found -> error
                if (myTree.findNodeName(firstName, lastName) == null) {
                    pen.println("\tCannot Perform DELETE Command:");
                    pen.println("\t\tStudent (" + firstName + " " + lastName + ") was not found in KAUdbms.");
                } // Found the student that I want to delete it
                else {
                    myTree.delete(firstName, lastName);
                    pen.println("\tStudent (" + firstName + " " + lastName + ") has been removed from KAUdbms.");
                }

            } else if (command.equalsIgnoreCase("PRINTRECORD")) {
                pen.println("PRINTRECORD Command");

                // Save all values in variables 
                String firstName = input.next();
                String lastName = input.next();

                //Search on student in tree
                // If student not found -> error
                if (myTree.findNodeName(firstName, lastName) == null) {
                    pen.println("\tCannot Perform PRINTRECORD Command:");
                    pen.println("\t\tStudent (" + firstName + " " + lastName + ") was not found in KAUdbms.");
                } //Otherway, print recorder by student name
                else {
                    // With correct GPA & LEVEL & COURSES
                    myTree.printRecorders(firstName, lastName, courseInfo, pen);
                }

            } else if (command.equalsIgnoreCase("PRINTALLRECORDS")) {
                pen.println("PRINTALLRECORDS Command");

                // If the tree is empty -> display error message
                if (myTree.isEmpty()) {
                    pen.println("\tCannot Perform PRINTALLRECORDS Command:");
                    pen.println("\t\tThere are currently no student records saved in KAUdbms.");
                } // Otherway, print all recorder of students 
                else {
                    pen.println("\tAll records saved in KAUdbms:");
                    pen.println("\tSTUDENT ID     NAME                     AGE     YEAR/LEVEL     GPA");
                    // With correct GPA & LEVEL & COURSES
                    myTree.printAllRecorders(courseInfo, pen);
                }

            } else if (command.equalsIgnoreCase("PRINTALLCOURSES")) {
                pen.println("PRINTALLCOURSES Command");

                courses.printAllCourses(pen);
//                studentInfo.getCourses().printAllCourses(pen);

            }
            i++; // COUNTER
        }

        //Close read & write operation
        input.close();

        pen.close();
    }

    public static void AddCourses(String IDcourse, int grade, int IDstudent, KAUdbmsBST myTree, KAUstudent studentInfo, KAUcourses courses, PrintWriter pen) {

        if (studentInfo.getCourses().findNode(IDcourse) == null) {
            studentInfo.getCourses().addCourse(IDcourse, grade);
            pen.println("\t" + IDcourse + " (Grade: " + grade + ") has been added to the record of Student ID " + IDstudent + ".");

            if (studentInfo.getCourses().NodeNumber() >= 0 && studentInfo.getCourses().NodeNumber() < 10) {
                studentInfo.setLevel(1);
            } else if (studentInfo.getCourses().NodeNumber() >= 10 && studentInfo.getCourses().NodeNumber() < 20) {
                studentInfo.setLevel(2);
            } else if (studentInfo.getCourses().NodeNumber() >= 20 && studentInfo.getCourses().NodeNumber() < 30) {
                studentInfo.setLevel(3);
            } else if (studentInfo.getCourses().NodeNumber() >= 30 && studentInfo.getCourses().NodeNumber() < 40) {
                studentInfo.setLevel(4);
            }

        } else {
            studentInfo.getCourses().findNode(IDcourse).setGrade(grade);
            pen.println("\t" + IDcourse + ": grade has been changed/updated, to a " + grade + ", for Student ID " + IDstudent + ".");
        }

        // Calculate GPA using method in BST class.,
        double subTotal = 0;
        int hour = 0;

        KAUcourse helpPtr = studentInfo.getCourses().getHead();
        while (helpPtr != null) {
            int pts = 3;

            if (helpPtr.getGrade() >= 90 && helpPtr.getGrade() <= 100) {
                pts *= 5;
            } else if (helpPtr.getGrade() >= 80 && helpPtr.getGrade() <= 89) {
                pts *= 4;
            } else if (helpPtr.getGrade() >= 70 && helpPtr.getGrade() <= 79) {
                pts *= 3;
            } else if (helpPtr.getGrade() >= 60 && helpPtr.getGrade() <= 69) {
                pts *= 2;
            } else {
                pts *= 0;
            }

            subTotal += pts;
            hour += 3;
//                while (j < numberCourses) {

//                    j++;
//                }
            helpPtr = helpPtr.getNext();

        }
        // Calculate GPA in KAUdbms class
        double GPA = subTotal / hour;
        studentInfo.setGPA(GPA);

        if (courses.findNode(IDcourse) == null) {
            courses.addCourse(IDcourse, grade);
            courses.findNode(IDcourse).setNum(1);

        } else {
            courses.findNode(IDcourse).setGrade(courses.findNode(IDcourse).getGrade() + grade);
            courses.findNode(IDcourse).setNum(courses.findNode(IDcourse).getNum() + 1);

        }

    }
}
