import java.util.Scanner;
import java.util.Arrays;
import java.io.File;

public class Main {
    enum Commands {
        EXIT("exit"),
        ECHO("echo"),
        TYPE("type");

        private final String command;

        Commands(String command) {
            this.command = command;
        }

        public String getCommand() {
            return this.command;
        }

        public String getCommandArgs(String input) {
            return input.substring(this.command.length() + 1);
        }
    }

    static String[] PATH;

    public static void main(String[] args) throws Exception {
        PATH = System.getenv("PATH").split(":");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("$ ");
            String input = scanner.nextLine();

            

            // Exit the program if the user types "exit"
            if (input.startsWith(Commands.EXIT.getCommand())) {
                exit(Commands.EXIT.getCommandArgs(input));
            } else if (input.startsWith(Commands.ECHO.getCommand())) {
                System.out.println(Commands.ECHO.getCommandArgs(input));
            } else if (input.startsWith(Commands.TYPE.getCommand())) {
                type(Commands.TYPE.getCommandArgs(input));
            } else {
                System.out.printf("%s: command not found\n", input);
            }
        } while (true);
    }

    private static void exit(String args) {
        if (!args.equals(Integer.toString(0))) {
            System.out.println("exit: Unrecognized argument");
            return;
        }
        System.exit(0);
    }

    private static void type(String args) {
        String[] commands = {
            Commands.EXIT.getCommand(),
            Commands.ECHO.getCommand(),
            Commands.TYPE.getCommand()
        };

        // Check if the command is a shell builtin
        if (Arrays.asList(commands).contains(args)) {
            System.out.printf("%s is a shell builtin\n", args);
            return;
        }

        // Check if the command is in the PATH
        for (String path: PATH) {
            String commandPath = path + "/" + args;
            if (new File(commandPath).exists()) {
                System.out.printf("%s is %s\n", args, commandPath);
                return;
            }
        }

        System.out.printf("%s: not found\n", args);
    }
}
