package Main;

import dao.DataBaseManager;
import entity.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class MainModule {
    static Scanner sc = new Scanner(System.in);
    static DataBaseManager db = new DataBaseManager();

    public static void main(String[] args) {
        db.initializeDatabase();
        boolean running = true;

        while (running) {
            System.out.println("\n=== CareerHub Job Board ===");
            System.out.println("1. View All Job Listings");
            System.out.println("2. Create Applicant Profile");
            System.out.println("3. Submit Job Application");
            System.out.println("4. Post a Job (Company)");
            System.out.println("5. Search Jobs by Salary Range");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> viewJobs();
                case 2 -> createApplicant();
                case 3 -> applyJob();
                case 4 -> postJob();
                case 5 -> searchSalaryRange();
                case 0 -> {
                    System.out.println("Exiting...");
                    running = false;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // 1. View all job listings
    private static void viewJobs() {
        List<JobListing> jobs = db.getJobListings();
        System.out.println("\n--- Job Listings ---");
        for (JobListing job : jobs) {
            System.out.printf("ID: %d | Title: %s | CompanyID: %d | Salary: %.2f%n",
                    job.getJobId(), job.getJobTitle(), job.getCompanyId(), job.getSalary());
        }
    }

    // 2. Create applicant profile
    private static void createApplicant() {
        try {
            System.out.print("Enter Applicant ID: ");
            int id = sc.nextInt(); sc.nextLine();
            System.out.print("First Name: ");
            String fname = sc.nextLine();
            System.out.print("Last Name: ");
            String lname = sc.nextLine();
            System.out.print("Email: ");
            String email = sc.nextLine();
            System.out.print("Phone: ");
            String phone = sc.nextLine();
            System.out.print("Resume (text): ");
            String resume = sc.nextLine();

            Applicant applicant = new Applicant(id, fname, lname, email, phone, resume);
            db.insertApplicant(applicant);
        } catch (Exception e) {
            System.err.println("Failed to create profile: " + e.getMessage());
        }
    }

    // 3. Apply to a job
    private static void applyJob() {
        try {
            System.out.print("Enter Application ID: ");
            int appId = sc.nextInt(); sc.nextLine();
            System.out.print("Applicant ID: ");
            int applicantId = sc.nextInt(); sc.nextLine();
            System.out.print("Job ID: ");
            int jobId = sc.nextInt(); sc.nextLine();
            System.out.print("Cover Letter: ");
            String cover = sc.nextLine();

            JobApplication application = new JobApplication(
                    appId, jobId, applicantId, LocalDateTime.now(), cover);
            db.insertJobApplication(application);
        } catch (Exception e) {
            System.err.println("Failed to apply: " + e.getMessage());
        }
    }

    // 4. Company posts a job
    private static void postJob() {
        try {
            System.out.print("Enter Job ID: ");
            int jobId = sc.nextInt(); sc.nextLine();
            System.out.print("Company ID: ");
            int companyId = sc.nextInt(); sc.nextLine();
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Description: ");
            String desc = sc.nextLine();
            System.out.print("Location: ");
            String location = sc.nextLine();
            System.out.print("Salary: ");
            double salary = sc.nextDouble(); sc.nextLine();
            System.out.print("Job Type: ");
            String type = sc.nextLine();

            JobListing job = new JobListing(
                    jobId, companyId, title, desc, location, salary, type, LocalDateTime.now());
            db.insertJobListing(job);
        } catch (Exception e) {
            System.err.println("Failed to post job: " + e.getMessage());
        }
    }

    // 5. Salary range query
    private static void searchSalaryRange() {
        try {
            System.out.print("Min Salary: ");
            double min = sc.nextDouble();
            System.out.print("Max Salary: ");
            double max = sc.nextDouble();

            List<JobListing> jobs = db.getJobListings();
            System.out.println("\n--- Jobs in Salary Range ---");
            for (JobListing job : jobs) {
                if (job.getSalary() >= min && job.getSalary() <= max) {
                    System.out.printf("ID: %d | %s | Salary: %.2f%n",
                            job.getJobId(), job.getJobTitle(), job.getSalary());
                }
            }
        } catch (Exception e) {
            System.err.println("Error during search: " + e.getMessage());
        }
    }
}

