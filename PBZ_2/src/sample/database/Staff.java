package sample.database;

public class Staff {
    private String fullName;
    private Integer age;
    private String riskCategory;

    public Staff(String fullName, Integer age, String riskCategory){
        this.fullName = fullName;
        this.age = age;
        this.riskCategory = riskCategory;
    }

    public String getFullName(){
        return this.fullName;
    }

    public Integer getAge(){
        return this.age;
    }

    public String getRiskCategory(){
        return this.riskCategory;
    }
}
