import java.io.IOException;

//interface used for inheritance between Bot and Player
public interface Player {
	public String getInputFromConsole() throws IOException;

	public String getNextAction(String command);

}
