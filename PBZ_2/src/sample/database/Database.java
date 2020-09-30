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
    private List<Staff> staffList = new ArrayList<>();

    public Database() throws SQLException, ClassNotFoundException {
        try {
            con = DriverManager.getConnection(url, user, password);
            stmt = con.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        setAgent();
        setOrganization();
        setStaff();
        System.out.println("database created");
    }

    private void setAgent() throws ClassNotFoundException, SQLException {
        String fullName;
        String passportData;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM agent");
            while (resultSet.next()) {
                fullName = resultSet.getString(1);
                passportData = resultSet.getString(2);
                agentList.add(new Agent(fullName, passportData));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void setOrganization() throws ClassNotFoundException, SQLException {
        String id;
        String fullName;
        String shortName;
        String address;
        String bank_number;
        String specialty;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM organizations");
            while (resultSet.next()) {
                id = resultSet.getString(1);
                fullName = resultSet.getString(2);
                shortName = resultSet.getString(3);
                address = resultSet.getString(4);
                bank_number = resultSet.getString(5);
                specialty = resultSet.getString(6);
                organizationList.add(new Organization(id, fullName, shortName, address, bank_number, specialty));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void setStaff() throws ClassNotFoundException, SQLException {
        String fullName;
        Integer age;
        String riskCategory;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM staff");
            while (resultSet.next()) {
                fullName = resultSet.getString(1);
                age = resultSet.getInt(2);
                riskCategory = resultSet.getString(3);
                staffList.add(new Staff(fullName, age, riskCategory));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void addAgent(Agent agent) throws ClassNotFoundException, SQLException {
        try {
            stmt.executeUpdate("INSERT INTO agent (agent_fullname, pasport_data)" +
                                  " VALUES ('" + agent.getFullName() + "', '" + agent.getPassportData() + "');");
            agentList.add(agent);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void addOrganization(Organization organization) throws ClassNotFoundException, SQLException {
        try {
            stmt.executeUpdate("INSERT INTO organizations (id, organization_fullname, organization_shortname, adress," +
                                  "bank_number, organization_specialty)" +
                                  " VALUES ('" + organization.getId() + "', '" + organization.getFullName() + "', '"
                                   + organization.getShortName() + "', '" + organization.getAddress() + "', '"
                                   + organization.getBank_number() + "', '" + organization.getSpecialty() + "');");
            organizationList.add(organization);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void addStaff(Staff staff) throws ClassNotFoundException, SQLException {
        try {
            stmt.executeUpdate("INSERT INTO staff (full_name, age, risk_category)" +
                                  " VALUES ('" + staff.getFullName() + "', '" + staff.getAge() + "', '" +
                                   staff.getRiskCategory() + "');");
            staffList.add(staff);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void deleteStaff(Staff staff) throws ClassNotFoundException, SQLException {
        try {
            stmt.executeUpdate("DELETE FROM staff WHERE full_name = '" + staff.getFullName() + "' AND age = '" +
                                   staff.getAge() + "' AND risk_category = '" + staff.getRiskCategory() + "'");
            staffList.add(staff);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
}
