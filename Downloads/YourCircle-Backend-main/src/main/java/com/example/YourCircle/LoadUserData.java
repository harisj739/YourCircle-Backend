package com.example.YourCircle;

import java.sql.*;

public class LoadUserData {
    static final String DBURL = "jdbc:mysql://localhost:3306/mydb";  // database URL
    static final String USERID = "root";
    static final String PASSWORD = "$hazia@#Mehreen30739";

    public static void main(String[] args) {

        try (Connection conn = DriverManager.getConnection(DBURL, USERID, PASSWORD);) {

            PreparedStatement ps;
            ResultSet rs;
            int userId;
            int row_count;

            String sqlINSERT = "insert into User(password, firstName, lastName, email, city, state, country, zip, age) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
            String[] keycols = {"userId"};
            ps = conn.prepareStatement(sqlINSERT, keycols);

            ps.setString(1,  "password123");
            ps.setString(2, "Stevie");
            ps.setString(3, "Wonder");
            ps.setString(4, "stevie@gmail.com");
            ps.setString(5, "Santa Clara");
            ps.setString(6, "California");
            ps.setString(7, "United States");
            ps.setString(8, "95050");
            ps.setInt(9, 27);

            row_count = ps.executeUpdate();
            System.out.println("row inserted: " + row_count);

            rs = ps.getGeneratedKeys();
            if (rs.next()) {
                userId = rs.getInt(1);
                System.out.println("row inserted for user id: " + userId);
            }

            String sqlSELECT = "select userId, password, firstName, lastName, email, city, state, country, zip, age from User";
            ps = conn.prepareStatement(sqlSELECT);

            rs = ps.executeQuery();
            while (rs.next()) {
                userId = rs.getInt("userId");
                String password = rs.getString("password");
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String country = rs.getString("country");
                String zip = rs.getString("zip");
                int age = rs.getInt("age");
                System.out.println(password + firstName + lastName + email + city + state + country + zip + age);
            }
        } catch (SQLException e) {
            System.out.println("Error: SQLException " + e.getMessage());
        }
    }
}

