import javax.swing.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.time.Duration;
import java.time.Instant;
import java.util.LinkedList;
import javax.swing.JFrame;

public class Danke extends JFrame implements Runnable{
    public static Danke window = null;
    public boolean isRunning;
    public int currentState;
    private static Leaderboard leaderboard;
    private Snake snake;
    public Scene currentScene;
    public KL keyListener = new KL();
    public ML mouseListener = new ML();
    public static LinkedList<String> getSnSc(){
        return leaderboard.getscoresS();
    }
    public static LinkedList<String> getCrSc(){
        return leaderboard.getscoresC();
    }

    public Danke(int width, int height, String title) {
        this.setSize(width, height);
        this.setTitle(title);
        this.setResizable(false);
        this.setVisible(true);
        this.setDefaultCloseOperation(3);
        this.addKeyListener(this.keyListener);
        this.addMouseListener(this.mouseListener);
        this.addMouseMotionListener(this.mouseListener);
        this.isRunning = true;
        this.changeState(0,0,false,0);
        leaderboard=new Leaderboard();
        leaderboard.ReadS("leadSnake.txt");
        leaderboard.ReadC("leadCar.txt");

    }

    public static Danke getWindow() {
        if (window == null) {
            window = new Danke(480, 960, "Snake");
        }

        return window;
    }

    public void close() {
        this.isRunning = false;
    }

    public void changeState(int newState, int score, boolean chck, int game) {
        if(chck==true){
            if(game==1){
                leaderboard.addSS(Integer.toString(score));
                leaderboard.Save(leaderboard.getscoresS(), "leadSnake.txt");
                System.out.println(score);
                System.out.println(leaderboard.getscoresS());}
            else if (game==2) {
                leaderboard.addCS(Integer.toString(score));
                leaderboard.Save(leaderboard.getscoresC(), "leadCar.txt");
                System.out.println(score);
                System.out.println(leaderboard.getscoresC());

            }
        }

        this.currentState = newState;
        switch (this.currentState) {
            case 0:
                this.currentScene = new MenuScene(this.keyListener, this.mouseListener);
                break;
            case 1:
                this.currentScene = new GameSceneT(this.keyListener);
                break;
            case 2:
                this.currentScene = new SnakeMScene(this.keyListener, this.mouseListener);
                break;
            case 3:
                this.currentScene = new GameSceneC(this.keyListener);
                break;
            case 4:
                this.currentScene = new LeadMenu(this.keyListener, this.mouseListener);
                break;
            case 5:
                this.currentScene = new GameOverSnake(this.keyListener, this.mouseListener);
                break;
            case 6:
                this.currentScene = new GameOverC(this.keyListener, this.mouseListener);
                break;
            default:
                System.out.println("Unknown scene.");
                this.currentScene = null;
        }

    }

    public void update(double dt) {
        Image dbImage = this.createImage(this.getWidth(), this.getHeight());
        Graphics dbg = dbImage.getGraphics();
        this.draw(dbg);
        this.getGraphics().drawImage(dbImage, 0, 0, this);
        this.currentScene.update(dt);
    }

    public void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        this.currentScene.draw(g);
    }

    public void run() {
        Instant lastFrameTime = Instant.now();

        try {
            while(this.isRunning) {
                Instant time = Instant.now();
                double deltaTime = (double)Duration.between(lastFrameTime, time).toNanos() * 1.0E-9;
                lastFrameTime = Instant.now();
                double deltaWanted = 0.0167;
                this.update(deltaWanted);
                long msToSleep = (long)((deltaWanted - deltaTime) * 1000.0);
                if (msToSleep > 0L) {
                    Thread.sleep(msToSleep);
                }
            }
        } catch (Exception var9) {
            var9.printStackTrace();
        }

        this.dispose();
    }

}