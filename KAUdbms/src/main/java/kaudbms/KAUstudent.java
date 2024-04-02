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

public class KAUstudent {

    //Data feild
    private int ID;
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private int phone;
    private int level = 1;
    private KAUcourses courses = new KAUcourses();
    private double GPA;
    private KAUstudent left;
    private KAUstudent right;

//     Constructers
    public KAUstudent() {
    }

    public KAUstudent(int ID, String firstName, String lastName, String email, int age, int phone) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phone = phone;
    }

    public KAUstudent(int ID, String firstName, String lastName, String email, int age, int phone, int level, KAUcourses courses, double GPA, KAUstudent left, KAUstudent right) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.level = level;
        this.courses = courses;
        this.GPA = GPA;
        this.left = left;
        this.right = right;
    }

    //Methods (getter & setter)
    public int getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public int getAge() {
        return age;
    }

    public int getPhone() {
        return phone;
    }

    public int getLevel() {
        return level;
    }

    public KAUcourses getCourses() {
        return courses;
    }

    public double getGPA() {
        return GPA;
    }

    public KAUstudent getLeft() {
        return left;
    }

    public KAUstudent getRight() {
        return right;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setCourses(KAUcourses courses) {
        this.courses = courses;
    }

    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    public void setLeft(KAUstudent left) {
        this.left = left;
    }

    public void setRight(KAUstudent rigth) {
        this.right = rigth;
    }

}
