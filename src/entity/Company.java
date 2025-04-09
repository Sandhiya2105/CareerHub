package entity;

import java.util.List;

public class Company {
    private int companyId;
    private String companyName;
    private String location;

    public Company() {}

    public Company(int companyId, String companyName, String location) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.location = location;
    }

    // Getters & Setters
    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    // Method Stubs (to be implemented in DAO layer)
    public void postJob(String jobTitle, String jobDescription, String jobLocation, double salary, String jobType) {
        System.out.println("Post job called (Handled in DAO Layer).");
    }

    public List<JobListing> getJobs() {
        System.out.println("Retrieve company jobs (Handled in DAO Layer).");
        return null;
    }
}

