package sample.database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String url = "jdbc:mysql://localhost:3306/second_lab?useUnicode=true&serverTimezone=UTC";
    private static final String user = "root";
    private static final String password = "sasha19062001";
    private static Connection con;
    private static Statement stmt;
    private List<Agent> agentList = new ArrayList<>();
    private List<Organization> organizationList = new ArrayList<>();
    private List<Staff> staffList= new ArrayList<>();

    private void setAgent() throws ClassNotFoundException, SQLException {
        String fullName;
        String passportData;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM agent");
            while (resultSet.next()){
                fullName = resultSet.getString(1);
                System.out.println(fullName);
                passportData = resultSet.getString(2);
                agentList.add(new Agent(fullName, passportData));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        System.out.println(agentList.get(0));
    }

    public Database() throws SQLException, ClassNotFoundException {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        setAgent();

    }

}
