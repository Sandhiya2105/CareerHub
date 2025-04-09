package exception;

import java.time.LocalDateTime;

public class ApplicationDeadlineException extends Exception {
    public ApplicationDeadlineException(String message) {
        super(message);
    }

    public static class ApplicationDeadlineChecker {
        public static void main(String[] args) {
            LocalDateTime deadline = LocalDateTime.of(2025, 4, 1, 23, 59);
            LocalDateTime current = LocalDateTime.now();

            try {
                if (current.isAfter(deadline)) {
                    throw new ApplicationDeadlineException("Application deadline has passed!");
                }
                System.out.println("Application submitted on time.");
            } catch (ApplicationDeadlineException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

