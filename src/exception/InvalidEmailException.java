package exception;

import java.util.Scanner;

public class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }

    public static class EmailValidationProgram {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter email: ");
            String email = scanner.nextLine();

            try {
                if (!email.contains("@") || !email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$")) {
                    throw new InvalidEmailException("Invalid email format!");
                }
                System.out.println("Email is valid. Proceeding with registration...");
            } catch (InvalidEmailException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

