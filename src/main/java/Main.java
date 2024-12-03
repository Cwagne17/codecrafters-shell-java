import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        String[] commands = {
            "exit",
            "echo",
            "type"
        };


        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("$ ");
            String input = scanner.nextLine();

            // Exit the program if the user types "exit"
            if (input.equals("exit 0")) {
                System.exit(0);
            } else if (input.startsWith("echo")) {
                System.out.println(input.substring(5));
            } else if (input.startsWith("type")) {
                String command = input.substring(5);
                if (Arrays.asList(commands).contains(command)) {
                    System.out.printf("%s is a shell builtin\n", command);
                } else {
                    System.out.printf("%s: not found\n", command);
                }
            } else {
                System.out.printf("%s: command not found\n", input);
            }
        } while (true);
    }

    // private static String readInput(scanner: Scanner) {
    //     System.out.print("$ ");
    //     String input = scanner.nextLine();

    //     return input;
    // }

    // static void evaluateInput(input: String) {
        
    // }

    // void printOutput(command: String) {
    //     System.out.printf("%s: command not found\n", command);
    // }
}
