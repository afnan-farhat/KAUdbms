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

public class KAUdbmsBST {

    //Data feild
    private KAUstudent root;

    // Constructers
    public KAUdbmsBST() {
    }

    public KAUdbmsBST(KAUstudent root) {
        this.root = root;
    }

    //Methods
    //1 - check the node is empty
    public boolean isEmpty() {
        return root == null;
    }

    // OVERLOEADING METHODS
    //2 - Search on node by ID -> return the address of node
    public KAUstudent findNode(int IDstudent) {
        return findNode(root, IDstudent);
    }

    private KAUstudent findNode(KAUstudent p, int IDstudent) {
        // Tree is empty
        if (p == null) {
            return null;
        } // Tree is not empty 
        else {
            // if the data we are searching for is found at p (at the current root)
            if (IDstudent == p.getID()) {
                return p;
            } else if (IDstudent < p.getID()) {
                return findNode(p.getLeft(), IDstudent); // left side
            } else {
                return findNode(p.getRight(), IDstudent); // rigth side
            }
        }
    }

    //3 - Search on node by name -> return the address of node
    public KAUstudent findNodeName(String fname, String lname) {
        return findNodeName(root, fname, lname);
    }

    private KAUstudent findNodeName(KAUstudent p, String fname, String lname) {
        // Tree is not empty 
        if (p != null) {
            //Check on last name
            if (lname.equals(p.getLastName())) { // equal -> check on first name
                if (fname.compareTo(p.getFirstName()) > 0) {
                    return findNodeName(p.getRight(), fname, lname); // the student greater than (rigth)
                } else if (fname.compareTo(p.getFirstName()) < 0) {
                    return findNodeName(p.getLeft(), fname, lname);// the student less than (left)
                } else {
                    return p; // find the student
                }
            }// not equal -> recursion 
            else {
                if (lname.compareTo(p.getLastName()) > 0) {
                    return findNodeName(p.getRight(), fname, lname);// the student greater than (rigth)
                } else if (lname.compareTo(p.getLastName()) < 0) {
                    return findNodeName(p.getLeft(), fname, lname);// the student less than (left)
                }
            }
        }
        return p; // return null (Base case)
    }

    //4 -  insert a new student
    public void insert(int ID, String firstName, String lastName, String email, int age, int phone) {
        KAUstudent newNode = new KAUstudent(ID, firstName, lastName, email, age, phone);
        root = insert(root, newNode);
    }

    private KAUstudent insert(KAUstudent p, KAUstudent newNode) {
        // IF tree is empty -> insert in begginig
        if (p == null) {
            return newNode;
        } // ELSE, we have a tree. Insert to the right or the left
        else {
            // Insert to the RIGHT of root (the value greater than root value)
            if (newNode.getID() > p.getID()) {
                KAUstudent temp = insert(p.getRight(), newNode);

                // Updated root of right subtree
                p.setRight(temp);
            } // Insert to the LEFT of root(the value smaller than root value)
            else if (newNode.getID() < p.getID()) {
                KAUstudent temp = insert(p.getLeft(), newNode);

                // Updated root of left subtree
                p.setLeft(temp);
            }
        }
        // Return root of updated tree
        return p;
    }

    // 5 - delete node to student 
    public void delete(String firstName, String lastName) {
        root = delete(root, firstName, lastName);
    }

    private KAUstudent delete(KAUstudent p, String firstName, String lastName) {
        //private KAUstudent delete(KAUstudent p, String firstName, String lastName) {

        KAUstudent node2delete;
        KAUstudent newnode2delete, node2save, parent;

        // To save all data to children of node to be delete and replace them
        int saveID, saveAge, savePhone, saveLevel;
        String saveFname, saveLname, saveEmail;
        KAUcourses saveCourses;
        double saveGPA;

        // Step 1: Search on the node we want to delete
        node2delete = findNodeName(p, firstName, lastName);
        // If node is not found >> cannot delete it.
        if (node2delete == null) {
            return root;
        }

        // Step 2: Find the parent of the node we want to delete
        parent = parent(p, node2delete);

        // Step 3: Check the children of node to delete it 
        // A :  node2delete is a leaf node (no any children)
        if (isLeaf(node2delete)) {
            // if parent is null >> node2delete is the ONLY one node in the tree
            if (parent == null) {
                return null; // we return null as the updated root of the tree
            }
            // Delete node if it is a left child
            if (lastName.compareToIgnoreCase(parent.getLastName()) < 0) {
                parent.setLeft(null);
            } // Delete node if it is a right child
            else {
                parent.setRight(null);

            }
            // Finally, update to new root
            return p;
        }
        // B : node2delete has only a left child
        if (hasOnlyLeftChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getLeft();
            }

            // If node2delete is not the root,
            // Check the child in left OR the right.
            // IF it is the left child.
            if (lastName.compareToIgnoreCase(parent.getLastName()) < 0) {
                parent.setLeft(parent.getLeft().getLeft());
            } // ELSE it is the right child.
            else {
                parent.setRight(parent.getRight().getLeft());
            }

            // Finally, update to new root
            return p;
        }

        // C :  node2delete has only a right child
        if (hasOnlyRightChild(node2delete)) {
            // if node2delete is the root
            if (parent == null) {
                return node2delete.getRight();
            }

            // If node2delete is not the root,
            // Check the child in left OR the right.
            // IF it is the left child.

            if (lastName.compareToIgnoreCase(parent.getLastName()) < 0) {
                parent.setLeft(parent.getLeft().getRight());
            } // ELSE it is the right child.
            else {
                parent.setRight(parent.getRight().getRight());
            }

            // Finally, update to new root
            return p;
        }

        // D :  node2delete has TWO children.
        // Using find min value in most right side
        newnode2delete = minNode(node2delete.getRight());

        // Save temporarily for all data from this node
        saveID = newnode2delete.getID();
        saveFname = newnode2delete.getFirstName();
        saveLname = newnode2delete.getLastName();
        saveEmail = newnode2delete.getEmail();
        saveAge = newnode2delete.getAge();
        savePhone = newnode2delete.getPhone();
        saveLevel = newnode2delete.getLevel();
        saveCourses = newnode2delete.getCourses();
        saveGPA = newnode2delete.getGPA();

        // Now, we recursively call our delete method, we just copied the data from
        p = delete(p, saveFname, saveLname);

        // Replace the node that I want to delete it with saved values
        node2delete.setID(saveID);
        node2delete.setFirstName(saveFname);
        node2delete.setLastName(saveLname);
        node2delete.setEmail(saveEmail);
        node2delete.setAge(saveAge);
        node2delete.setPhone(savePhone);
        node2delete.setLevel(saveLevel);
        node2delete.setCourses(saveCourses);
        node2delete.setGPA(saveGPA);

        // Finally, update to new root
        return p;
    }

    // 6 - Print for rocorder
    public void printRecorders(String fname, String lastName, KAUcourse courseInfo, PrintWriter pen) {
        printRecorders(root, fname, lastName, courseInfo, pen);
    }

    private void printRecorders(KAUstudent p, String firstName, String lastName, KAUcourse courseInfo, PrintWriter pen) {
        if (p != null) {
            // If you found the student >> print 
            if (p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {

                pen.println("	Student Record for ID " + p.getID());
                pen.printf("\t%-13s%s%n", "First Name:", p.getFirstName());
                pen.printf("\t%-13s%s%n", "Last Name:", p.getLastName());
                //print tap(\t) + (Email: + (13 space after Email:) %-13s ) + ( p.getEmail() + (35 space after p.getEmail()) %-35s) +
                pen.printf("\t%-13s%-35s%-5s%2s%n", "Email:", p.getEmail(), "Phone:  ", p.getPhone());

                //Print the level -> If the level equal 1 print (st)
                if (p.getLevel() == 1) {
                    pen.printf("\t%-13s%-35s%-5s%2s%n", "Age:", p.getAge(), "Level:  ", p.getLevel() + "st Year");
                } // Otherway, print (nd)
                else {
                    pen.printf("\t%-13s%-35s%-5s%2s%n", "Age:", p.getAge(), "Level:  ", p.getLevel() + "nd Year");
                }

                // IF GPA = 0, print N/A
                if (p.getGPA() == 0.0) {
                    pen.printf("\t%-13s%s%n", "GPA:", "N/A");
                } //Else, print GPA 
                else {
                    pen.printf("\t%-13s%.2f%n", "GPA:", p.getGPA());
                }

                // Print to recorders courses 
                pen.println("\tCourse Record:");
                p.getCourses().printCourses(pen);

                return; // Exit from method (finish printRecorder)

            } // Otherway, continue searching
            //Search in left side
            printRecorders(p.getLeft(), firstName, lastName, courseInfo, pen);

            //Search in right side
            printRecorders(p.getRight(), firstName, lastName, courseInfo, pen);

        }
    }

    //7 - Print ALL rocorders
    public void printAllRecorders(KAUcourse courseInfo, PrintWriter pen) {
        printAllRecorders(root, courseInfo, pen);
    }

    private void printAllRecorders(KAUstudent p, KAUcourse courseInfo, PrintWriter pen) {
        //The BST is not emoty -> print the data
        if (p != null) {
            //Print first smaller data 
            printAllRecorders(p.getLeft(), courseInfo, pen);

            // Change the number of level according number of courses
            if (p.getCourses() != null) {
                if (p.getCourses().NodeNumber() >= 0 && p.getCourses().NodeNumber() < 10) {
                    p.setLevel(1);
                } else if (p.getCourses().NodeNumber() >= 10 && p.getCourses().NodeNumber() < 20) {
                    p.setLevel(2);
                } else if (p.getCourses().NodeNumber() >= 20 && p.getCourses().NodeNumber() < 30) {
                    p.setLevel(3);
                } else if (p.getCourses().NodeNumber() >= 30 && p.getCourses().NodeNumber() < 40) {
                    p.setLevel(4);
                }
            }

            //Print the root data  -> If the level equal 1 print (st)
            if (p.getLevel() == 1) {
                pen.printf("\t%-15d%-25s%-8s%-14s%5.2f\n", p.getID(), p.getFirstName() + " " + p.getLastName(),
                        p.getAge(), p.getLevel() + "st Year", p.getGPA());
            } // Otherway, print (nd)
            else {
                pen.printf("\t%-15d%-25s%-8s%-14s%5.2f\n", p.getID(), p.getFirstName() + " " + p.getLastName(),
                        p.getAge(), p.getLevel() + "nd Year", p.getGPA());
            }

            //Print first bigger data.
            printAllRecorders(p.getRight(), courseInfo, pen);

        }
    }

    // 8 - Finde MinNode most left 
    public KAUstudent minNode(KAUstudent root) {
        if (root == null) {
            return null;
        } else {
            //Most lefi value return it
            if (root.getLeft() == null) {
                return root;
            } else {
                return minNode(root.getLeft());
            }
        }
    }

    // 9 - Find parent to node 
    public KAUstudent parent(KAUstudent p) {
        return parent(root, p);
    }

    private KAUstudent parent(KAUstudent root, KAUstudent p) {
        // BST is empty or the node that you want to delete i s a root -> NULL cases
        if (root == null || root == p) {
            return null; // because there is on parent
        }
        // If root is the actual parent of node p
        if (root.getLeft() == p || root.getRight() == p) {
            return root; // because root is the parent of p
        }
        // Search on parent in the left side of root
        if (p.getID() < root.getID()) {
            return parent(root.getLeft(), p);
        } // Search on parent in the right side of root
        else if (p.getID() > root.getID()) {
            return parent(root.getRight(), p);
        } // Other cases
        else {
            return null;
        }
    }

    // 10 - Find the node that no have any children 
    public boolean isLeaf(KAUstudent p) {
        return (p.getLeft() == null && p.getRight() == null);
    }

    // 11 - Find the node that has one children in (left side)
    public boolean hasOnlyLeftChild(KAUstudent p) {
        return (p.getLeft() != null && p.getRight() == null);
    }

    // 12 - Find the node that has one children in (right side)
    public boolean hasOnlyRightChild(KAUstudent p) {
        return (p.getLeft() == null && p.getRight() != null);
    }

}
