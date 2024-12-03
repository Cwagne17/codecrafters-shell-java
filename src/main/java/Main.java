import java.util.Scanner;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;

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
    }

    static String[] PATH;

    public static void main(String[] args) throws Exception {
        PATH = System.getenv("PATH").split(":");

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("$ ");
            String input = scanner.nextLine();

            String[] splitCommand = input.split(" ", 2);
            String command = splitCommand[0];
            String agruments = splitCommand.length > 1 ? splitCommand[1] : "";


            // Handle built-in commands
            if (command.startsWith(Commands.EXIT.getCommand())) {
                exit(agruments);
                continue;
            } else if (command.startsWith(Commands.ECHO.getCommand())) {
                System.out.println(agruments);
                continue;
            } else if (command.startsWith(Commands.TYPE.getCommand())) {
                type(agruments);
                continue;
            }

            // Handle commands that may be in the PATH
            String commandPath = getCommandPath(command);
            if (commandPath != null) {
                executeCommand(commandPath, agruments);
                continue;
            }  

            // Default to command not found
            System.out.printf("%s: command not found\n", input);
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
        String commandPath = getCommandPath(args);
        if (commandPath != null) {
            System.out.printf("%s is %s\n", args, commandPath);
        }

        System.out.printf("%s: not found\n", args);
    }

    private static void executeCommand(String commandPath, String arguments) {
        try {
            ProcessBuilder pb = new ProcessBuilder(commandPath, arguments);
            Process p = pb.start();

            // To wait for the process to finish:
            p.waitFor();

            // Print the output of the command
            java.util.Scanner s = new java.util.Scanner(p.getInputStream()).useDelimiter("\\A");
            while (s.hasNext()) {
                System.out.println(s.next());
            }
            s.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String getCommandPath(String command) {
        for (String path: PATH) {
            String commandPath = path + "/" + command;
            if (new File(commandPath).exists()) {
                return commandPath;
            }
        }

        // Couldn't find the command in the PATH
        return null;
    }
}
