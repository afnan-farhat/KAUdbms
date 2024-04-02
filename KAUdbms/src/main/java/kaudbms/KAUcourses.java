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

import java.io.PrintWriter;

public class KAUcourses {

    //Data feild
    private KAUcourse head;

    // Constructers
    public KAUcourses() {
    }

    public KAUcourses(KAUcourse head) {
        this.head = head;
    }

    //Methods (getter & setter)
    public KAUcourse getHead() {
        return head;
    }

    //  1 - the linkedlist of courses is empty
    public boolean isEmpty() {
        return head == null;
    }

    // 2 - Insert a new course to linked list (OVERLOADING)
    public void addCourse(String IDcourse, int grade) {
        head = addCourse(head, IDcourse, grade);
    }

    private KAUcourse addCourse(KAUcourse head, String IDcourse, int grade) {
        // If list is empty -> insert in start linked list 
        if (head == null) {
            head = new KAUcourse(IDcourse, grade, head);
            return head;
        } // otherway, traverse the place that you insert it
        else {
            // Traverse using help ptr
            KAUcourse helpPtr = head;

            while (helpPtr.getNext() != null) {
                // If you want to insert noed in middle
                if (helpPtr.getNext().getIDcourse().compareTo(IDcourse) > 0) {
                    break; // we found our spot and should break out of the while loop
                }

                helpPtr = helpPtr.getNext();
            }

            //Insert a new node in the last 
            KAUcourse newNode = new KAUcourse(IDcourse, grade, helpPtr.getNext());
            helpPtr.setNext(newNode);

        }
        // Return head
        return head;
    }

    // 3 - delete the course from linked list (OVERLOADING)
    public void deleteCourse(String IDcourse) {
        head = deleteCourse(head, IDcourse);
    }

    private KAUcourse deleteCourse(KAUcourse head, String IDcourse) {
        // if the list is not empty you can delete any node
        if (!isEmpty()) {
            // If you want to delete the first node ( move the head to next node) 
            if (head.getIDcourse().equals(IDcourse)) {
                head = head.getNext();
            } // ELSE, we must traverse to delete the node in middle or last the list
            else {
                // We need to traverse to find the data we want to delete using helpPtr
                KAUcourse helpPtr = head;
                // Traverse to correct deletion point
                while (helpPtr.getNext() != null) {
                    if (helpPtr.getNext().getIDcourse().equals(IDcourse)) {
                        helpPtr.setNext(helpPtr.getNext().getNext());
                        break; // this stip if you find the node to be delete in mid, then exit the loop and delete it
                    }
                    helpPtr = helpPtr.getNext();
                }
            }
            // return the possibly updated head of the list
            return head;
        }
        return head;
    }

    // 4 - Print all courses in linked list (OVERLOADING)
    public void printAllCourses(PrintWriter pen) {
        printAllCourses(head, pen);
    }

    private void printAllCourses(KAUcourse head, PrintWriter pen) {
        storList(); // to sorted data before print it

        // If list is empty -> print it
        if (head != null) {
            pen.println("\tAll courses saved in KAUdbms:");
            pen.println("\tCOURSE NAME         AVERAGE GRADE");

            // Traverse using help ptr
            KAUcourse helpPtr = head;
            while (helpPtr != null) {
                //Calculate the avrage frade to each course 
                int AvrageGrade = helpPtr.getGrade() / helpPtr.getNum();

                pen.println("\t" + helpPtr.getIDcourse() + "                " + (AvrageGrade));
                helpPtr = helpPtr.getNext();// tO traverse to next course
            }
        }
    }

    // 5 - Print one courses in linked list (OVERLOADING)
    public void printCourses(PrintWriter pen) {
        printCourses(head, pen);
    }

    private void printCourses(KAUcourse head, PrintWriter pen) {
        if (head == null) {
            pen.println("\t\tStudent has not taken any courses");
        } else {

            storList(); // to sorted data before print it

            // If list is empty -> print it
            // Traverse using help ptr
            KAUcourse helpPtr = head;
            while (helpPtr != null) {
                //print coursese with grade
                pen.printf("\t\t%s%9s%9s%5d%n", "Course ID:", helpPtr.getIDcourse(), "Grade:", helpPtr.getGrade());

                helpPtr = helpPtr.getNext();
            }
        }
    }

    // 6 - Sort all courses in linked list using course ID (OVERLOADING)
    public void storList() {
        sortList(head);

    }

    private void sortList(KAUcourse head) {
        // make 2 pointer to tranver
        KAUcourse helpPtr = head;
        KAUcourse compare;

        //Save the course ID
        String tempIDCourse;
        int tempGrade;
        int tempNum;

        // If list is empty -> not sorted
        if (head == null) {
            return;
        } // otherway, sort the data
        else {
            //To tranvers and sort it 
            while (helpPtr != null) {
                // to compare with first and second node
                compare = helpPtr.getNext();
                while (compare != null) {
                    // It the first node gratear than second, then save first node 
                    if (helpPtr.getIDcourse().compareToIgnoreCase(compare.getIDcourse()) > 0) {
                        tempIDCourse = helpPtr.getIDcourse();
                        tempGrade = helpPtr.getGrade();
                        tempNum = helpPtr.getNum();

                        helpPtr.setIDcourse(compare.getIDcourse());
                        helpPtr.setGrade(compare.getGrade());
                        helpPtr.setNum(compare.getNum());

                        compare.setIDcourse(tempIDCourse);
                        compare.setGrade(tempGrade);
                        compare.setNum(tempNum);
                    }
                    compare = compare.getNext();
                }
                helpPtr = helpPtr.getNext();
            }
        }
    }

    // 7 - Return the node address if you find it(OVERLOADING)
    public KAUcourse findNode(String courseID) {
        return findNode(head, courseID);
    }

    private KAUcourse findNode(KAUcourse head, String courseID) {
        // Traverse using help ptr
        KAUcourse helpPtr = head;
        while (helpPtr != null) {
            //If you find the node return address
            if (helpPtr.getIDcourse().equals(courseID)) {
                return helpPtr;
            }

            helpPtr = helpPtr.getNext();
        }
        return helpPtr; // return the address or null

    }

    //  8 - Calculate the number of node (OVERLOADING)
    public int NodeNumber() {
        return NodeNumber(head);
    }

    private int NodeNumber(KAUcourse head) {
        // Traverse using help ptr
        KAUcourse helpPtr = head;
        // Variable to counter 
        int number_counter = 0;

        while (helpPtr != null) {
            //Increase the counter and continue 
            ++number_counter;
            helpPtr = helpPtr.getNext();
        }
        return number_counter; // return number
    }

}
