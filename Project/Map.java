import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Reads and contains in memory the map of the game.
 *
 */
public class Map {

	private int playerGold = 0;

	/* Representation of the map */
	private char[][] map;

	/* Map name */
	private String mapName;

	/* Gold required for the human player to win */
	private int goldRequired;

	/**
	 * Default constructor, creates the default map "Very small Labyrinth of doom".
	 */
	public Map() {
		mapName = "Very small Labyrinth of Doom";
		goldRequired = 2;
		map = new char[][] {
				{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'E', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', 'E', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'G', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '#' },
				{ '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#',
						'#' } };
	}

	/**
	 * Constructor that accepts a map to read in from.4
	 *
	 * @param :
	 *            The filename of the map file.
	 * @throws IOException
	 */
	public Map(String fileName) throws IOException {
		readMap(fileName);
	}

	/**
	 * @return : Gold required to exit the current map.
	 */
	protected int getGoldRequired() {
		return goldRequired;
	}

	protected int getPlayerGold() {
		return playerGold;
	}

	protected void setPlayerGold(int gold) {
		playerGold = gold;

	}

	/**
	 * @return : The map as stored in memory.
	 */
	protected char[][] getMap() {
		return map;

	}

	/**
	 * @return : The name of the current map.
	 */
	protected String getMapName() {
		return mapName;
	}

	/**
	 * Reads the map from file.
	 *
	 * @param :
	 *            Name of the map's file.
	 * @throws IOException
	 */
	protected void readMap(String fileName) throws IOException {
		String line = null;
		FileReader fileReader = new FileReader(fileName);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		int lineCount = 0;
		int arrayX = 0;
		int arrayY = 0;

		// file reader iterates through file line by line
		while ((line = bufferedReader.readLine()) != null) {
			// name is found from line beginning with name
			if (line.substring(0, 4).equals("name")) {
				mapName = line.substring(5);
				// gold needed is found from line beginning with win
			} else if (line.substring(0, 3).equals("win")) {
				goldRequired = Integer.parseInt(line.substring(4));
				// finds length and width of 2d array
			} else {
				arrayX = line.length();
				lineCount++;
			}

		}
		arrayY = lineCount;

		// length and width of 2d array is used to create the array map is stored in
		int i = 0;
		char[][] map1 = new char[arrayY][arrayX];
		fileReader = new FileReader(fileName);
		bufferedReader = new BufferedReader(fileReader);
		// iterate through file again, adding each character to the new array
		while ((line = bufferedReader.readLine()) != null) {
			if (line.charAt(0) == '#') {
				for (int j = 0; j < arrayX; j++) {
					map1[i][j] = line.charAt(j);
				}
				i++;
			}
		}
		// use newly generated map
		map = map1;
		// close files
		bufferedReader.close();
	}

}
