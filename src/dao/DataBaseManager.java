package dao;

import entity.*;
import util.DBConnUtil;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataBaseManager {

    private Connection getConnection() throws SQLException {
        return DBConnUtil.getConnection();
    }

    public void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Companies (" +
                    "CompanyID INT PRIMARY KEY, " +
                    "CompanyName VARCHAR(100), " +
                    "Location VARCHAR(100))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Applicants (" +
                    "ApplicantID INT PRIMARY KEY, " +
                    "FirstName VARCHAR(50), " +
                    "LastName VARCHAR(50), " +
                    "Email VARCHAR(100), " +
                    "Phone VARCHAR(15), " +
                    "Resume TEXT)");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Jobs (" +
                    "JobID INT PRIMARY KEY, " +
                    "CompanyID INT, " +
                    "JobTitle VARCHAR(100), " +
                    "JobDescription TEXT, " +
                    "JobLocation VARCHAR(100), " +
                    "Salary DECIMAL(10,2), " +
                    "JobType VARCHAR(50), " +
                    "PostedDate DATETIME, " +
                    "FOREIGN KEY (CompanyID) REFERENCES Companies(CompanyID))");

            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS Applications (" +
                    "ApplicationID INT PRIMARY KEY, " +
                    "JobID INT, " +
                    "ApplicantID INT, " +
                    "ApplicationDate DATETIME, " +
                    "CoverLetter TEXT, " +
                    "FOREIGN KEY (JobID) REFERENCES Jobs(JobID), " +
                    "FOREIGN KEY (ApplicantID) REFERENCES Applicants(ApplicantID))");

            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCompany(Company company) {
        String sql = "INSERT INTO Companies VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, company.getCompanyId());
            ps.setString(2, company.getCompanyName());
            ps.setString(3, company.getLocation());
            ps.executeUpdate();
            System.out.println("Company inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertApplicant(Applicant applicant) {
        String sql = "INSERT INTO Applicants VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, applicant.getApplicantId());
            ps.setString(2, applicant.getFirstName());
            ps.setString(3, applicant.getLastName());
            ps.setString(4, applicant.getEmail());
            ps.setString(5, applicant.getPhone());
            ps.setString(6, applicant.getResume());
            ps.executeUpdate();
            System.out.println("Applicant inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertJobListing(JobListing job) {
        String sql = "INSERT INTO Jobs VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, job.getJobId());
            ps.setInt(2, job.getCompanyId());
            ps.setString(3, job.getJobTitle());
            ps.setString(4, job.getJobDescription());
            ps.setString(5, job.getJobLocation());
            ps.setDouble(6, job.getSalary());
            ps.setString(7, job.getJobType());
            ps.setTimestamp(8, Timestamp.valueOf(job.getPostedDate()));
            ps.executeUpdate();
            System.out.println("Job inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertJobApplication(JobApplication application) {
        String sql = "INSERT INTO Applications VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, application.getApplicationId());
            ps.setInt(2, application.getJobId());
            ps.setInt(3, application.getApplicantId());
            ps.setTimestamp(4, Timestamp.valueOf(application.getApplicationDate()));
            ps.setString(5, application.getCoverLetter());
            ps.executeUpdate();
            System.out.println("Application inserted.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<JobListing> getJobListings() {
        List<JobListing> list = new ArrayList<>();
        String sql = "SELECT * FROM Jobs";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                JobListing job = new JobListing(
                        rs.getInt("JobID"),
                        rs.getInt("CompanyID"),
                        rs.getString("JobTitle"),
                        rs.getString("JobDescription"),
                        rs.getString("JobLocation"),
                        rs.getDouble("Salary"),
                        rs.getString("JobType"),
                        rs.getTimestamp("PostedDate").toLocalDateTime()
                );
                list.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Company> getCompanies() {
        List<Company> list = new ArrayList<>();
        String sql = "SELECT * FROM Companies";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Company company = new Company(
                        rs.getInt("CompanyID"),
                        rs.getString("CompanyName"),
                        rs.getString("Location")
                );
                list.add(company);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<Applicant> getApplicants() {
        List<Applicant> list = new ArrayList<>();
        String sql = "SELECT * FROM Applicants";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Applicant app = new Applicant(
                        rs.getInt("ApplicantID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("Email"),
                        rs.getString("Phone"),
                        rs.getString("Resume")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<JobApplication> getApplicationsForJob(int jobID) {
        List<JobApplication> list = new ArrayList<>();
        String sql = "SELECT * FROM Applications WHERE JobID = ?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, jobID);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                JobApplication app = new JobApplication(
                        rs.getInt("ApplicationID"),
                        rs.getInt("JobID"),
                        rs.getInt("ApplicantID"),
                        rs.getTimestamp("ApplicationDate").toLocalDateTime(),
                        rs.getString("CoverLetter")
                );
                list.add(app);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}

