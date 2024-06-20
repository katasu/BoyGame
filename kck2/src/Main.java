import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    private static Leaderboard leaderboard;
    private final static int LARGE_WIDTH  = 80;
    private final static int LARGE_HEIGHT = 23;
    public static void main(String[] args) throws IOException{
        String filePath = "mode.txt";
        String mod = getFirstWordFromFile(filePath);
        System.out.println(mod);
        if(mod.equals("Graph")) {
            new Music();
            Music.soundtrackcar();
            Danke window = Danke.getWindow();
            Thread thread = new Thread(window);
            thread.start();
        } else if (mod.equals("Text")) {

                new Music();
                Music.soundtrackcar();
                GameView snake_game = new GameView(LARGE_WIDTH, LARGE_HEIGHT);
                snake_game.openPickMenu();
        }

    }
    public static String getFirstWordFromFile(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line = reader.readLine();
        reader.close();

        if (line != null) {
            String[] words = line.split("\\s+");
            return words[0];
        } else {
            return "";
        }
    }
}
