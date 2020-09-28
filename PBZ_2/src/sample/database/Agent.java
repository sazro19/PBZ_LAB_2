package sample.database;

public class Agent {
    private String fullName;
    private String passportData;

    public Agent(String fullName, String pasportData) {
        this.fullName = fullName;
        this.passportData = pasportData;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getPassportData() {
        return this.passportData;
    }
}
