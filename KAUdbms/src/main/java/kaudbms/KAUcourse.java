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

public class KAUcourse {

    //Data feild
    private String IDcourse;
    private int grade;
    private int num ;
    private KAUcourse next;

    // Constructers
    public KAUcourse() {
    }

    public KAUcourse(String IDcourse, int grade, int num) {
        this.IDcourse = IDcourse;
        this.grade = grade;
        this.num = num;
        this.next = null;
    }
    
    
    public KAUcourse(String IDcourse, int grade, KAUcourse next) {
        this.IDcourse = IDcourse;
        this.grade = grade;
        this.next = next;
    }


    public KAUcourse(String IDcourse, int grade, int num, KAUcourse next) {
        this.IDcourse = IDcourse;
        this.grade = grade;
        this.num = num;
        this.next = next;
    }

    //Methods (getter & setter)
    public String getIDcourse() {
        return IDcourse;
    }

    public int getGrade() {
        return grade;
    }

    public int getNum() {
        return num;
    }

    public KAUcourse getNext() {
        return next;
    }

    public void setIDcourse(String IDcourse) {
        this.IDcourse = IDcourse;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setNext(KAUcourse next) {
        this.next = next;
    }

}
