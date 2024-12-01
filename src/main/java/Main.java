import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("$ ");
            String input = scanner.nextLine();
            System.out.printf("%s: command not found\n", input);
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
