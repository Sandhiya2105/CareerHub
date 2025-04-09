package exception;

import java.util.Scanner;

public class InvalidSalaryException extends Exception {
    public InvalidSalaryException(String message) {
        super(message);
    }

    public static class SalaryCalculator {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            double total = 0;
            int count = 0;

            System.out.print("Enter number of job listings: ");
            int n = scanner.nextInt();

            for (int i = 1; i <= n; i++) {
                System.out.print("Enter salary for job " + i + ": ");
                double salary = scanner.nextDouble();
                try {
                    if (salary < 0) {
                        throw new InvalidSalaryException("Negative salary found for job " + i);
                    }
                    total += salary;
                    count++;
                } catch (InvalidSalaryException e) {
                    System.err.println(e.getMessage());
                }
            }

            if (count > 0) {
                System.out.println("Average Salary: " + (total / count));
            } else {
                System.out.println("No valid salaries to calculate average.");
            }
        }
    }
}

