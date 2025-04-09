package exception;

import java.io.File;

public class FileUploadException extends Exception {
    public FileUploadException(String message) {
        super(message);
    }

    public static class ResumeUploader {
        public static void main(String[] args) {
            String filePath = "resumes/sample.pdf";

            try {
                File file = new File(filePath);
                if (!file.exists()) {
                    throw new FileUploadException("Resume file not found.");
                }

                if (!file.getName().endsWith(".pdf")) {
                    throw new FileUploadException("Unsupported file format. Only .pdf allowed.");
                }

                if (file.length() > 2 * 1024 * 1024) {
                    throw new FileUploadException("File size exceeded. Max size: 2MB");
                }

                System.out.println("Resume uploaded successfully.");
            } catch (FileUploadException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}

