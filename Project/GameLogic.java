import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * Contains the main logic part of the game, as it processes.
 *
 */
public class GameLogic {

	private int xPos;
	private int yPos;
	private int xBot;
	private int yBot;
	private int turn = 0;
	private Map map;
	private HumanPlayer p1;
	private BotPlayer b1;

	// Generates a random location for a bot or player
	public void placePlayer(String botOrPlayer) {
		char tempMap[][] = map.getMap();
		int width = tempMap[0].length;
		int length = tempMap.length;
		Random rand = new Random();
		// 1 is deducted inside brackets and added outside to ensure 0 is not generated
		int n = rand.nextInt(width - 1) + 1;
		int m = rand.nextInt(length - 1) + 1;
		// string argument dictates if generated coordinates are used for bot or player
		if (botOrPlayer.equals("PLAYER")) {
			xPos = n;
			yPos = m;
		} else if (botOrPlayer.equals("BOT")) {
			xBot = n;
			yBot = m;
		}

	}
	

	/**
	 * Default constructor
	 * 
	 * @throws IOException
	 */

	public GameLogic() throws IOException {
		// reads in file or uses default map
		System.out.printf("input file path for map or leave blank for default map");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String mapPath = reader.readLine();

		if (mapPath.equals("")) {
			map = new Map();
		} else {
			System.out.println("map loaded");
			map = new Map(mapPath);
		}
		// generate new bot, player and map array
		char tempMap[][] = map.getMap();
		String command = "";
		p1 = new HumanPlayer();
		placePlayer("PLAYER");
		// if player generates in an invalid location generate a new spawn
		while (tempMap[yPos][xPos] == '#' | tempMap[yPos][xPos] == 'G') {
			placePlayer("PLAYER");
		}
		b1 = new BotPlayer();
		placePlayer("BOT");
		// if player generates in an invalid location generate a new spawn
		while (yPos == yBot && xPos == xBot | tempMap[yBot][xBot] == '#') {
			placePlayer("BOT");
		}
		// main game loop
		while (gameRunning() == true) {
			// every even turn is a player turn, each odd turn is a bot turn
			if (turn % 2 == 0) {
				command = p1.getInputFromConsole();
				//Dont miss a turn if an invalid command is entered
				while(command == "INVALID") {
					command = p1.getInputFromConsole();
				}
				turn++;
			} else {
				command = b1.getInputFromConsole();
				turn++;
			}
			// prints the result of command methods depending on input
			if (command.equals("HELLO")) {
				System.out.println(hello());
			} else if (command.equals("MOVE N")) {
				System.out.println(move('N'));
			} else if (command.equals("MOVE E")) {
				System.out.println(move('E'));
			} else if (command.equals("MOVE S")) {
				System.out.println(move('S'));
			} else if (command.equals("MOVE W")) {
				System.out.println(move('W'));
			} else if (command.equals("PICKUP")) {
				System.out.println(pickup());
			} else if (command.equals("LOOK")) {
				System.out.println(look());
			} else if (command.equals("QUIT")) {
				quitGame();
			}
			// win condition checked
			if (tempMap[yPos][xPos] == 'E' && (map.getGoldRequired() - map.getPlayerGold()) == 0) {
				System.out.println("                you have escaped the dugeon with all the gold");
				System.exit(0);
			}
		}
	}

	/**
	 * @return if the game is running.
	 */
	// checks fail condition
	protected boolean gameRunning() {
		// if the bot has caught the player the game is over, otherwise keep playing
		if (xBot == xPos && yBot == yPos) {
			System.out.println("\n              THE BOT HAS CAUGHT YOU, GAME OVER");
			return false;
		} else {
			return true;
		}
	}

	/**
	 * @return : Returns back gold player requires to exit the Dungeon.
	 */
	protected String hello() {
		return ("you need: " + (map.getGoldRequired() - map.getPlayerGold()));
	}

	/**
	 * Checks if movement is legal and updates player's location on the map.
	 *
	 * @param direction
	 *            : The direction of the movement.
	 * @return : Protocol if success or not.
	 */
	protected String move(char direction) {
		// finds length and width to get limits for movement
		char tempMap[][] = map.getMap();
		int width = tempMap[0].length;
		int length = tempMap.length;
		// checks if it is the player or bot turn to move
		if (turn % 2 == 0) {
			// Direction used to increment or decrement x/y coordinates, if the move is
			// invalid return fail otherwise SUCCESS
			if (direction == 'N') {
				if (tempMap[yBot - 1][xBot] == '#' | yBot == length + 1 | xBot == width + 1 | yBot == -1 | xBot == -1) {
					return ("BOT FAILED");
				} else {
					yBot--;
					return ("BOT MOVED");
				}
			} else if (direction == 'E') {
				if (tempMap[yBot][xBot + 1] == '#' | yBot == length + 1 | xBot == width + 1 | yBot == -1 | xBot == -1) {
					return ("BOT FAILED");
				} else {
					xBot++;
					return ("BOT MOVED");
				}
			} else if (direction == 'S') {
				if (tempMap[yBot + 1][xBot] == '#' | yBot == length + 1 | xBot == width + 1 | yBot == -1 | xBot == -1) {
					return ("BOT FAILED");
				} else {
					yBot++;
					return ("BOT MOVED");
				}
			} else if (direction == 'W') {
				if (tempMap[yBot][xBot - 1] == '#' | yBot == length + 1 | xBot == width + 1 | yBot == -1 | xBot == -1) {
					return ("BOT FAILED");
				} else {
					xBot--;
					return ("BOT MOVED");
				}
			}

		} else {
			// Player movement
			if (direction == 'N') {
				if (tempMap[yPos - 1][xPos] == '#' | yPos == length + 1 | xPos == width + 1 | yPos == -1 | xPos == -1) {
					return ("FAIL");
				} else {
					yPos--;
					return ("SUCCESS");
				}
			} else if (direction == 'E') {
				if (tempMap[yPos][xPos + 1] == '#' | yPos == length + 1 | xPos == width + 1 | yPos == -1 | xPos == -1) {
					return ("FAIL");
				} else {
					xPos++;
					return ("SUCCESS");
				}
			} else if (direction == 'S') {
				if (tempMap[yPos + 1][xPos] == '#' | yPos == length + 1 | xPos == width + 1 | yPos == -1 | xPos == -1) {
					return ("FAIL");
				} else {
					yPos++;
					return ("SUCCESS");
				}
			} else if (direction == 'W') {
				if (tempMap[yPos][xPos - 1] == '#' | yPos == length + 1 | xPos == width + 1 | yPos == -1 | xPos == -1) {
					return ("FAIL");
				} else {
					xPos--;
					return ("SUCCESS");
				}
			}
		}
		return null;
	}

	/**
	 * Converts the map from a 2D char array to a single string.
	 *
	 * @return : A String representation of the game map.
	 * @throws IOException
	 */
	protected String look() throws IOException {
		String area = "";
		char tempMap[][] = map.getMap();
		int width = tempMap[0].length;
		int length = tempMap.length;
		// player turn
		if (turn % 2 != 0) {
			// nested for loops iterate through a 5 by 5 array around the player
			for (int i = yPos - 2; i < yPos + 3; i++) {
				for (int j = xPos - 2; j < xPos + 3; j++) {
					// add player and bot to map
					if (j == xPos && i == yPos) {
						area += 'P';
					} else if (j == xBot && i == yBot) {
						area += 'B';
						// add blank space beyond walls
					} else if (j == width | i == length | i == -1 | j == -1) {
						area += ' ';
						// add character in map array
					} else {
						area += tempMap[i][j];
					}
				}
				area += '\n';
			}
			return area;
		} else {
			// bot turn operates same as player
			for (int i = yBot - 2; i < yBot + 3; i++) {
				for (int j = xBot - 2; j < xBot + 3; j++) {
					if (j == xPos && i == yPos) {
						area += 'P';
					} else if (j == xBot && i == yBot) {
						area += 'B';
					} else if (j == width | i == length | i == -1 | j == -1) {
						area += ' ';
					} else {
						area += tempMap[i][j];
					}
				}
				area += '\n';
			}
			// area is passed to BotPlayer class after it is generated
			b1.getNextAction(area);
			return ("THE BOT IS LOOKING FOR YOU");
		}
	}

	/**
	 * Processes the player's pickup command, updating the map and the player's gold
	 * amount.
	 *
	 * @return If the player successfully picked-up gold or not.
	 */
	protected String pickup() {
		char tempMap[][] = map.getMap();
		// if pickup is valid add one to gold and delete the gold from the map
		if (tempMap[yPos][xPos] == 'G') {
			map.setPlayerGold(map.getPlayerGold() + 1);
			tempMap[yPos][xPos] = '.';
			return ("SUCCESS Gold owned:" + map.getPlayerGold());
		} else {
			return ("FAIL");
		}
	}

	/**
	 * Quits the game, shutting down the application.
	 */
	protected void quitGame() {
		System.exit(0);
	}

	// game is generated in main method
	public static void main(String[] args) throws IOException {
		GameLogic game = new GameLogic();

	}
}