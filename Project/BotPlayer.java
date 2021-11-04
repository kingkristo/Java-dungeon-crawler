import java.io.IOException;
import java.util.Random;

public class BotPlayer implements Player {
	private int counter = 0;
	private String nextMove = "";
	
	//bot decides on its move
	public String getInputFromConsole() throws IOException {
		//Bot alternates between Looking and moving on its turn via a counter
		String command;
		if (counter % 2 == 0) {
			command = "LOOK";
			counter++;
		} else {
			command = nextMove;
			counter++;
		}
		return command;
	}
	
	//area found via look is passed here from Gamelogic so Bot can decide where to move
	public String getNextAction(String area) {
		//if player is in the area bot follows them
		if (area.contains("P")) {
			//area bot can see is passed into an array
			char botArea[][] = new char[5][5];
			int counter = 0;
			int playerX = 0;
			int playerY = 0;
			for (int i = 0; i < 5; i++) {
				for (int j = 0; j < 5; j++) {
					botArea[i][j] = area.charAt(counter);
					if (area.charAt(counter) == 'P') {
						playerX = j;
						playerY = i;
					}
					counter++;
				}
			}
			//Bot direction is decides based on the players location relative to the centre of the array
			if (playerX > 2 && playerX != 3) {
				nextMove = "MOVE W";
			} else if (playerX < 2) {
				nextMove = "MOVE E";
			} else if (playerY > 2 && playerX != 3) {
				nextMove = "MOVE S";
			} else if (playerY < 2) {
				nextMove = "MOVE N";
			}
		//if player cannot be seen the bot moves randomly 
		} else {
			//element from array of possible moves is randomly chosen
			String possibleMoves[] = { "MOVE N", "MOVE S", "MOVE E", "MOVE W" };
			Random rand = new Random();
			nextMove = possibleMoves[rand.nextInt(4)];
		}
		return (null);
	}
}
