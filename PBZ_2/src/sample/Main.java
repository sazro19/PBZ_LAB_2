package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.database.Database;

import java.sql.*;
import java.util.Properties;

public class Main extends Application {
    private static final String url = "jdbc:mysql://localhost:3306/second_lab?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "sasha19062001";
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    private Properties p;


    @Override
    public void start(Stage primaryStage) throws Exception{
//        String query = "SELECT * FROM agent";
////        p = new Properties();
////        p.setProperty("user", "root");
////        p.setProperty("password", "sasha19062001");
////        p.setProperty("useUnicode", "true");
////        p.setProperty("characterEncoding", "cp1251");
////        try {
////            // opening database connection to MySQL server
////            con = DriverManager.getConnection(url, user, password);
////
////            // getting Statement object to execute query
////            stmt = con.createStatement();
////
////            // executing SELECT query
////            rs = stmt.executeQuery(query);
////
////            while (rs.next()) {
////                String fullName = rs.getString(1);
////                String data = rs.getString(2);
////                System.out.println(fullName+" "+data);
////            }
////
////
////        } catch (SQLException sqlEx) {
////            sqlEx.printStackTrace();
////        } finally {
////            //close connection ,stmt and resultset here
////            try { con.close(); } catch(SQLException se) { /*can't do anything */ }
////            try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
////            try { rs.close(); } catch(SQLException se) { /*can't do anything */ }
////        }
        Parent root = FXMLLoader.load(getClass().getResource("view/sample.fxml"));
        primaryStage.setScene(new Scene(root, 600, 350));
        primaryStage.show();

    }




    public static void main(String[] args) {
        launch(args);
    }
}
