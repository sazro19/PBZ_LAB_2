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
    private List<Contract> contractList = new ArrayList<>();

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
        setContract();
        System.out.println("database created");
    }

    private void setAgent() throws SQLException {
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

    private void setOrganization() throws SQLException {
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

    private void setStaff() throws SQLException {
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

    private void setContract() throws SQLException {
        String id;
        String organizationID;
        String agentName;
        Date startDate;
        Date endDate;
        String sumCategory;
        String sumCase;
        String staffName;
        try {
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM contracts");
            while (resultSet.next()) {
                id = resultSet.getString(1);
                organizationID = resultSet.getString(2);
                agentName = resultSet.getString(3);
                startDate = resultSet.getDate(4);
                endDate = resultSet.getDate(5);
                sumCategory = resultSet.getString(6);
                sumCase = resultSet.getString(7);
                staffName = resultSet.getString(8);
                contractList.add(new Contract(id, organizationID, agentName, startDate, endDate, sumCategory,
                                              sumCase, staffName));
                System.out.println(contractList.get(0).getStartDate());
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void addAgent(Agent agent) throws SQLException {
        try {
            stmt.executeUpdate("INSERT INTO agent (agent_fullname, pasport_data)" +
                                  " VALUES ('" + agent.getFullName() + "', '" + agent.getPassportData() + "');");
            agentList.add(agent);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void addOrganization(Organization organization) throws SQLException {
        try {
            stmt.executeUpdate("INSERT INTO organizations (id, organization_fullname, organization_shortname, adress," +
                                  "bank_number, organization_specialty)" +
                                  " VALUES ('" + organization.getId() + "', '" + organization.getFullName() + "', '"
                                   + organization.getShortName() + "', '" + organization.getAddress() + "', '"
                                   + organization.getBankNumber() + "', '" + organization.getSpecialty() + "');");
            organizationList.add(organization);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void addStaff(Staff staff) throws SQLException {
        try {
            stmt.executeUpdate("INSERT INTO staff (full_name, age, risk_category)" +
                                  " VALUES ('" + staff.getFullName() + "', '" + staff.getAge() + "', '" +
                                   staff.getRiskCategory() + "');");
            staffList.add(staff);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void deleteAgent(Agent agent) throws SQLException {
        try {
            stmt.executeUpdate("DELETE FROM agent WHERE fullname = '" + agent.getFullName() + "' AND age = '" +
                                   agent.getPassportData() + "'");
            agentList.remove(agent);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void deleteOrganization(Organization organization) throws SQLException {
        try {
            stmt.executeUpdate("DELETE FROM organizations WHERE id = '" + organization.getId() +
                                  "' AND organization_fullname = '" + organization.getFullName() + "' AND " +
                                  " adress = '" + organization.getAddress() + "' AND bank_number = '" +
                                   organization.getBankNumber() + "' AND organization_specialty = '" +
                                   organization.getSpecialty() + "'");
            organizationList.remove(organization);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void deleteStaff(Staff staff) throws SQLException {
        try {
            stmt.executeUpdate("DELETE FROM staff WHERE full_name = '" + staff.getFullName() + "' AND age = '" +
                    staff.getAge() + "' AND risk_category = '" + staff.getRiskCategory() + "'");
            staffList.remove(staff);
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    private void editAgent(Agent agent, Agent newAgent) throws SQLException {
        try {
            stmt.executeUpdate("UPDATE agent SET agent_fullname = '" + newAgent.getFullName() + "', pasport_data = '" + newAgent.getPassportData() +
                                  "' WHERE agent_fullname = '" + agent.getFullName() + "' AND pasport_data = '" +
                                    agent.getPassportData() + "'");
            for(int i = 0; i < agentList.size(); i++){
                if(agentList.get(i).equals(agent)){
                    agentList.set(i, newAgent);
                    break;
                }
            }
        } catch (SQLException sqlEX){
            sqlEX.printStackTrace();
        }
    }

    private void editOrganization(Organization organization,
                                  Organization newOrganization) throws SQLException {
        try {
            stmt.executeUpdate("UPDATE organizations SET id = '" + newOrganization.getId() + "', " +
                                  "organization_fullname = '" + newOrganization.getFullName() + "', " +
                                  "organization_shortname = '" + newOrganization.getShortName() + "', " +
                                  "adress = '" + newOrganization.getAddress() + "', " +
                                  "bank_number = '" + newOrganization.getBankNumber() + "', " +
                                  "organization_specialty = '" + newOrganization.getSpecialty() + "' " +
                                  " WHERE id = '" + organization.getId() + "' AND " +
                                  "organization_fullname = '" + organization.getFullName() + "' AND " +
                                  "organization_shortname = '" + organization.getShortName() + "' AND " +
                                  "adress = '" + organization.getAddress() + "' AND " +
                                  "bank_number = '" + organization.getBankNumber() + "' AND "+
                                  "organization_specialty = '" + organization.getSpecialty() + "'");
            for(int i = 0; i < organizationList.size(); i++){
                if(organizationList.get(i).equals(organization)){
                    organizationList.set(i, newOrganization);
                    break;
                }
            }
        } catch (SQLException sqlEX){
            sqlEX.printStackTrace();
        }
    }

    private void editStaff(Staff staff, Staff newStaff) throws SQLException {
        try {
            stmt.executeUpdate("UPDATE staff SET full_name = '" + newStaff.getFullName() + "', " +
                                  "age = '" + newStaff.getAge() + "', " +
                                  "risk_category = '" + newStaff.getRiskCategory() + "'" +
                                  " WHERE full_name = '" + staff.getFullName() + "' AND " +
                                  "age = '" + staff.getAge() + "' AND " +
                                  "risk_category = '" + staff.getRiskCategory() + "'");
            for(int i = 0; i < staffList.size(); i++){
                if(staffList.get(i).equals(staff)){
                    staffList.set(i, newStaff);
                    System.out.println(staffList.get(i).getFullName());
                    break;
                }
            }
        } catch (SQLException sqlEX){
            sqlEX.printStackTrace();
        }
    }

    public List<Agent> getAgentList(){
        return agentList;
    }

    public List<Staff> getStaffList() { return staffList; }

    public List<Organization> getOrganizationList() { return organizationList; }

}
