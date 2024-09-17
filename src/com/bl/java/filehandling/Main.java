package com.bl.java.filehandling;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    static boolean checkFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    static String getValidFileName() {
        Scanner scanner = new Scanner(System.in);
        String fileName = null;

        while (fileName == null) {
            try {
                System.out.print("Enter the name of the file: ");
                fileName = scanner.nextLine();
                if (fileName.isEmpty()) {
                    throw new IOException("File name cannot be empty. Please enter a valid file name.");
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                fileName = null;  // Reset the file name to prompt the user again
            }
        }
        return fileName;
    }

    // Overwrite function: Writes new content and replaces the old content
    static void fileOverwrite(String fileName, String content) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);  // Default behavior is to overwrite
            fileWriter.write(content);
            fileWriter.close();  // Ensure the file is closed after writing
            System.out.println("File overwritten successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Append function: Adds new content to the end of the file
    static void fileAppend(String fileName, String content) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);  // true indicates append mode
            fileWriter.append(content);
            fileWriter.close();  // Ensure the file is closed after writing
            System.out.println("Content appended successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void fileRead(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            int data = fileReader.read();
            if (data == -1) {
                System.out.println("The file is empty.");
            } else {
                while (data != -1) {
                    System.out.print((char) data);
                    data = fileReader.read();
                }
                System.out.println("\nFile read successfully.");
            }
            fileReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error: The file '" + fileName + "' does not exist.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    static void fileDelete(String fileName) {
        File file = new File(fileName);
        if (checkFileExists(fileName)) {
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        } else {
            System.out.println("File does not exist.");
        }
    }

    // New Create File Functionality
    static void fileCreate() {
        Scanner scanner = new Scanner(System.in);
        String fileName = getValidFileName();

        File file = new File(fileName);
        if (checkFileExists(fileName)) {
            System.out.println("Error: The file '" + fileName + "' already exists.");
        } else {
            try {
                if (file.createNewFile()) {
                    System.out.println("File '" + fileName + "' created successfully.");

                    // Ask user if they want to add content
                    System.out.print("Do you want to add content to the file? (yes/no): ");
                    String response = scanner.nextLine().trim().toLowerCase();

                    if (response.equals("yes")) {
                        System.out.println("Enter the content of the file:");
                        String content = scanner.nextLine();
                        fileOverwrite(fileName, content);
                    } else {
                        System.out.println("File will remain empty.");
                    }
                } else {
                    System.out.println("Error: File could not be created.");
                }
            } catch (IOException e) {
                System.out.println("An error occurred while creating the file: " + e.getMessage());
            }
        }
    }

    static void fileWriteOperation(String fileName) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to: ");
        System.out.println("1. Overwrite the file");
        System.out.println("2. Append to the file");
        System.out.print("Enter your choice (1/2): ");
        int writeChoice = scanner.nextInt();
        scanner.nextLine();  // Consume the newline character

        System.out.println("Enter the content of the file:");
        String content = scanner.nextLine();

        switch (writeChoice) {
            case 1:
                fileOverwrite(fileName, content);
                break;
            case 2:
                fileAppend(fileName, content);
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    static void fileOperations(int choice) {

        switch (choice) {
            case 1:
                fileCreate();  // Create File is now the first option
                break;

            case 2:
                String fileName = getValidFileName();
                fileRead(fileName);
                break;

            case 3:
                String fileToWrite = getValidFileName();
                fileWriteOperation(fileToWrite);  // Added overwrite and append options
                break;

            case 4:
                String fileToDelete = getValidFileName();
                fileDelete(fileToDelete);
                break;

            case 5:
                System.out.println("Exiting the file operation.");
                break;

            default:
                System.out.println("Invalid choice. Please select a valid option.");
                break;
        }
    }

    static void menu2(int choice) {
        Scanner sc = new Scanner(System.in);

        switch (choice) {
            case 1:
                System.out.println(" -- Welcome to File Operation -- ");
                System.out.println(" ------------------------------- ");
                System.out.println(" --       1. Create File      -- ");
                System.out.println(" --       2. Read File        -- ");
                System.out.println(" --       3. Write File       -- ");
                System.out.println(" --       4. Delete File      -- ");
                System.out.println(" --       5. Exit             -- ");
                System.out.println();
                System.out.print("Enter choice: ");
                int choice1 = sc.nextInt();
                sc.nextLine(); // Consumes the newline character
                fileOperations(choice1);
                break;

            case 2:
                System.out.println("\nExiting Successfully!");
                break;

            default:
                System.out.println("Invalid choice. Please select a valid option.");
                break;
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.println();
            System.out.println(" -- Welcome to File Operation -- ");
            System.out.println(" ------------------------------- ");
            System.out.println(" -- 1. Start File Operation --   ");
            System.out.println(" -- 2. Exit File Operation --   ");
            System.out.println();
            System.out.print("Enter your choice: ");
            int choice = in.nextInt();
            in.nextLine();  // Consumes the newline character
            System.out.println();

            if (choice == 2) {
                System.out.println("Exiting the program.");
                break;  // Exit the loop to stop the program
            }

            menu2(choice);
        }

        in.close(); // Close the Scanner resource
    }
}
