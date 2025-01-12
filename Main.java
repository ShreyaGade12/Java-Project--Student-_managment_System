package com.acc.util;
import java.sql.*;
import java.util.Scanner;

import com.acc.db.DBConnection;
import com.acc.model.Student;

public class Main {

    public static void main(String[] args) {

        Connection con = DBConnection.getDBConnection();

        if (con != null) {
            System.out.println("Connected to the database successfully!");
            System.out.println();
            System.out.println("************* Welcome To Student Database Management System ********** ");
            Scanner scanner = new Scanner(System.in);

            while (true) {

                System.out.println("\nSelect an option:");
                System.out.println("1. Fetch Student Data");
                System.out.println("2. Insert New Student");
                System.out.println("3. Update Student");
                System.out.println("4. Delete Student");
                System.out.println("5. Exit");

                int option = scanner.nextInt();

                switch (option) {
                    case 1:
                        fetchStudentData(con);
                        break;

                    case 2:
                        insertStudent(con, scanner);
                        break;

                    case 3:
                        updateStudent(con, scanner);
                        break;

                    case 4:
                        deleteStudent(con, scanner);
                        break;

                    case 5:
                        System.out.println("Exited Successfully !");
                        scanner.close();
                        try {
                            con.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        return;

                    default:
                        System.out.println("Invalid option. Please try again.");
                }
            }

        } else {
            System.out.println("Failed to connect to the database.");
        }
    }

    public static void fetchStudentData(Connection con) {
        String selectQuery = "SELECT * FROM student";
        try (PreparedStatement selectStmt = con.prepareStatement(selectQuery);
             ResultSet rs = selectStmt.executeQuery()) {
            while (rs.next()) {
                int stuid = rs.getInt("stuid");
                String studName = rs.getString("studName");
                int studage = rs.getInt("studage");
                String deptName = rs.getString("deptName");
                double marks = rs.getDouble("marks");

                Student student = new Student(stuid, studName, studage, deptName, marks);
                System.out.println(student);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


   
    
    public static void insertStudent(Connection con, Scanner scanner) {
        try {
            String insertQuery = "INSERT INTO student (stuid, studName, studage, deptName, marks) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = con.prepareStatement(insertQuery);

            System.out.print("Enter Student ID: ");
            int stuid = scanner.nextInt();
            System.out.print("Enter Student Name: ");
            String studName = scanner.next();
            System.out.print("Enter Student Age: ");
            int studage = scanner.nextInt();
            System.out.print("Enter Department Name: ");
            String deptName = scanner.next();
            System.out.print("Enter Marks: ");
            double marks = scanner.nextDouble();

            pstmt.setInt(1, stuid);
            pstmt.setString(2, studName);
            pstmt.setInt(3, studage);
            pstmt.setString(4, deptName);
            pstmt.setDouble(5, marks);

            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("A new student was inserted successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateStudent(Connection con, Scanner scanner) {
        try {
            System.out.print("Enter Student ID to update: ");
            int stuid = scanner.nextInt();
            System.out.print("Enter new Name for Student ID " + stuid + ": ");
            String studName = scanner.next();
            System.out.print("Enter new Age: ");
            int studage = scanner.nextInt();
            System.out.print("Enter new Department: ");
            String deptName = scanner.next();
            System.out.print("Enter new Marks: ");
            double marks = scanner.nextDouble();

            String updateQuery = "UPDATE student SET studName = ?, studage = ?, deptName = ?, marks = ? WHERE stuid = ?";
            PreparedStatement pstmt = con.prepareStatement(updateQuery);

            pstmt.setString(1, studName);
            pstmt.setInt(2, studage);
            pstmt.setString(3, deptName);
            pstmt.setDouble(4, marks);
            pstmt.setInt(5, stuid);

            int rowsUpdated = pstmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Student record updated successfully!");
            } else {
                System.out.println("No student found with ID " + stuid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(Connection con, Scanner scanner) {
        try {
            System.out.print("Enter Student ID to delete: ");
            int stuid = scanner.nextInt();

            String deleteQuery = "DELETE FROM student WHERE stuid = ?";
            PreparedStatement pstmt = con.prepareStatement(deleteQuery);
            pstmt.setInt(1, stuid);

            int rowsDeleted = pstmt.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Student record deleted successfully!");
            } else {
                System.out.println("No student found with ID " + stuid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
