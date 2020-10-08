package sample.database;

import java.sql.Date;

public class Contract {
    private String id;
    private String organizationId;
    private String agentName;
    private Date startDate;
    private Date endDate;
    private String sumCategory;
    private String sumCase;
    private String staffName;

    public Contract(String id, String organizationID, String agentName, Date startDate, Date endDate,
                    String sum_category, String sum_case, String staffName){
        this.id = id;
        this.organizationId = organizationID;
        this.agentName = agentName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.sumCategory = sum_category;
        this.sumCase = sum_case;
        this.staffName = staffName;
    }

    public String getId() {
        return id;
    }

    public String getOrganizationId() {
        return organizationId;
    }

    public String getAgentName() {
        return agentName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public String getSumCategory() {
        return sumCategory;
    }

    public String getSumCase() {
        return sumCase;
    }

    public String getStaffName() {
        return staffName;
    }



}
