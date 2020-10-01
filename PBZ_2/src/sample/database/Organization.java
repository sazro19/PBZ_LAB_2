package sample.database;

public class Organization {
    private String id;
    private String fullName;
    private String shortName;
    private String address;
    private String bank_number;
    private String specialty;

    public Organization(String id, String fullName, String shortName,
                         String address, String bank_number, String specialty){
        this.id = id;
        this.fullName = fullName;
        this.shortName = shortName;
        this.address = address;
        this.bank_number = bank_number;
        this.specialty = specialty;
    }

    public String getId() {
        return this.id;
    }

    public String getFullName() {
        return this.fullName;
    }

    public String getShortName() {
        return this.shortName;
    }

    public String getAddress() {
        return this.address;
    }

    public String getBankNumber() {
        return this.bank_number;
    }

    public String getSpecialty() {
        return this.specialty;
    }
}
