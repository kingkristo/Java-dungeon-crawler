import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Runs the game with a human player and contains code needed to read inputs.
 *
 */
public class HumanPlayer implements Player {

	/**
	 * 
	 * Reads player's input from the console.
	 * <p>
	 * return : A string containing the input the player entered.
	 * 
	 * @throws IOException
	 */
	public String getInputFromConsole() throws IOException {
		// buffered reader gets command from user
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String commandIn = reader.readLine();
		// exits if no command is given
		if (commandIn == null) {
			System.exit(0);
		}
		// passes command from user to getNextAction
		return getNextAction(commandIn);

	}

	/**
	 * Processes the command. It should return a reply in form of a String, as the
	 * protocol dictates. Otherwise it should return the string "Invalid".
	 *
	 * @param command
	 *            : Input entered by the user.
	 * @return : Processed output or Invalid if the @param command is wrong.
	 */
	public String getNextAction(String command) {
		// checks command against valid commands
		if (command.equals("MOVE N") || command.equals("MOVE E") || command.equals("MOVE S") || command.equals("MOVE W")
				|| command.equals("LOOK") || command.equals("PICKUP") || command.equals("QUIT")
				|| command.equals("HELLO")) {
			return command;
		} else {
			// returns invalid and a message to user if an invalid command is entered
			System.out.println("invalid command, try again");
			return ("INVALID");
		}
	}
}