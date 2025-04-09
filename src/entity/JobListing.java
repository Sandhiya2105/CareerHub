package entity;

import java.time.LocalDateTime;
import java.util.List;

public class JobListing {
    private int jobId;
    private int companyId;
    private String jobTitle;
    private String jobDescription;
    private String jobLocation;
    private double salary;
    private String jobType;
    private LocalDateTime postedDate;

    public JobListing() {}

    public JobListing(int jobId, int companyId, String jobTitle, String jobDescription,
                      String jobLocation, double salary, String jobType, LocalDateTime postedDate) {
        this.jobId = jobId;
        this.companyId = companyId;
        this.jobTitle = jobTitle;
        this.jobDescription = jobDescription;
        this.jobLocation = jobLocation;
        this.salary = salary;
        this.jobType = jobType;
        this.postedDate = postedDate;
    }
    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public int getCompanyId() { return companyId; }
    public void setCompanyId(int companyId) { this.companyId = companyId; }

    public String getJobTitle() { return jobTitle; }
    public void setJobTitle(String jobTitle) { this.jobTitle = jobTitle; }

    public String getJobDescription() { return jobDescription; }
    public void setJobDescription(String jobDescription) { this.jobDescription = jobDescription; }

    public String getJobLocation() { return jobLocation; }
    public void setJobLocation(String jobLocation) { this.jobLocation = jobLocation; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public String getJobType() { return jobType; }
    public void setJobType(String jobType) { this.jobType = jobType; }

    public LocalDateTime getPostedDate() { return postedDate; }
    public void setPostedDate(LocalDateTime postedDate) { this.postedDate = postedDate; }

    public void apply(int applicantID, String coverLetter) {
        System.out.println("Applicant " + applicantID + " applied to job " + jobId + " (Handled in DAO).");
    }

    public List<Applicant> getApplicants() {
        System.out.println("Returning applicants for job (Handled in DAO).");
        return null;
    }
}

