package org.example;

import java.sql.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    String url = "jdbc:postgresql://localhost/users";
    String user = "postgres";
    String password = "ntsoa";

    String query = "SELECT * FROM users WHERE name ILIKE ? LIMIT ? ";

    public void getUserByPagination(String word, Integer limit, Integer offset){
        try(Connection conn = DriverManager.getConnection(url, user, password);
        PreparedStatement pst = conn.prepareStatement(query)){
            pst.setString(1,"%" + word +"%" );
            pst.setInt(2,limit);


            ResultSet rs = pst.executeQuery();

            while (rs.next()){
                String name = rs.getString("name");
                String firstname = rs.getString("firstname");
                String email = rs.getString("email");
                System.out.println(name + "|" + firstname + "|" + email);
            }


        }catch(SQLException e){
            throw new RuntimeException(e);

        }

    }
    public static void main(String[] args) {
        Main test = new Main();
        test.getUserByPagination("al",2,2);
    }
}