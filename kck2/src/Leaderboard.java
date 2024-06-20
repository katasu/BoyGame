import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Leaderboard {
    private static LinkedList<String> carsScore;
    private static LinkedList<String> snakeScore;
    public LinkedList<String> getscoresC(){
        return carsScore;
    }
    public LinkedList<String> getscoresS(){
        return snakeScore;
    }

    public void addCS(String x){
        carsScore.add(x);
    }

    public void addSS(String x){
        snakeScore.add(x);
    }
    public void Save(LinkedList<String> list, String name){
        String newLine = System.getProperty("line.separator");
        try {
            FileWriter myWriter = new FileWriter(name);
            String toPrint="";
            for(String x:list){
                toPrint+=x;
                toPrint+=newLine;
            }
            myWriter.write(toPrint);
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public  void ReadC(String name) {
        carsScore = new LinkedList<String>();
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                carsScore.add(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
    public  void ReadS(String name) {
        snakeScore = new LinkedList<String>();
        try {
            File myObj = new File(name);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                snakeScore.add(data);
            }
            myReader.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}