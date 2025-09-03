import java.util.Scanner;
import java.util.regex.Pattern;

public class PasswordCheckerConsole {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String password = "";

        // Main loop for continuous checking
        while (true) {
            System.out.println("------------------------------------");
            System.out.println("  Welcome to Strengthify: The password strength checker Tool ");
            System.out.println("------------------------------------");
            System.out.println("Current Password: " + getHiddenPassword(password));
            System.out.println("\nOptions:");
            System.out.println("1. Enter a new password");
            System.out.println("2. Check strength of current password");
            System.out.println("3. Show/Hide password");
            System.out.println("4. Exit");
            System.out.print("\nEnter your choice: ");

            String choice = scanner.nextLine();
            
            switch (choice) {
                case "1":
                    System.out.print("Please enter your new password: ");
                    password = scanner.nextLine();
                    break;
                case "2":
                    checkAndDisplayStrength(password);
                    break;
                case "3":
                    togglePasswordVisibility(password);
                    break;
                case "4":
                    System.out.println("Exiting. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void checkAndDisplayStrength(String password) {
        if (password.isEmpty()) {
            System.out.println("\nPlease enter a password first.");
            return;
        }
        
        System.out.println("\n--- Strength Report ---");
        int score = 0;
        
        // Check for length
        boolean hasLength = password.length() >= 8;
        if (hasLength) score += 20;
        System.out.println(hasLength ? "✅ At least 8 characters" : "❌ At least 8 characters");

        // Check for uppercase
        Pattern uppercasePattern = Pattern.compile(".*[A-Z].*");
        boolean hasUppercase = uppercasePattern.matcher(password).matches();
        if (hasUppercase) score += 20;
        System.out.println(hasUppercase ? "✅ Uppercase letter" : "❌ Uppercase letter");

        // Check for lowercase
        Pattern lowercasePattern = Pattern.compile(".*[a-z].*");
        boolean hasLowercase = lowercasePattern.matcher(password).matches();
        if (hasLowercase) score += 20;
        System.out.println(hasLowercase ? "✅ Lowercase letter" : "❌ Lowercase letter");

        // Check for numbers
        Pattern numberPattern = Pattern.compile(".*[0-9].*");
        boolean hasNumber = numberPattern.matcher(password).matches();
        if (hasNumber) score += 20;
        System.out.println(hasNumber ? "✅ Number" : "❌ Number");

        // Check for special characters
        Pattern specialCharPattern = Pattern.compile(".*[^a-zA-Z0-9].*");
        boolean hasSpecialChar = specialCharPattern.matcher(password).matches();
        if (hasSpecialChar) score += 20;
        System.out.println(hasSpecialChar ? "✅ Special character" : "❌ Special character");
        
        System.out.println("-------------------------");
        System.out.println("Total Score: " + score);
        System.out.print("Final Strength: ");

        if (score >= 80) {
            System.out.println("Strong! 💪");
        } else if (score >= 40) {
            System.out.println("Medium 🤔");
        } else {
            System.out.println("Weak 😞");
        }
    }

    private static String getHiddenPassword(String password) {
        return "*".repeat(password.length());
    }
    
    // Simple state management for "show/hide" functionality
    private static boolean passwordVisible = false;

    private static void togglePasswordVisibility(String password) {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            System.out.println("\nPassword is now visible: " + password);
        } else {
            System.out.println("\nPassword is now hidden: " + getHiddenPassword(password));
        }
    }
}