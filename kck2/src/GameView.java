import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

import java.awt.*;
import java.util.Arrays;
import java.util.LinkedList;


public class GameView
{
	private final static String EMPTY_STRING = " ";
	private static LinkedList<Position> temp;


	private final static String BORDER_STRING = "▒";
	private final static String CAR_BODY_STRING ="▒";;
	private final static String CAR_HEAD_STRING =")";

	private final static String SNAKE_HEAD_STRING = "@";
	private final static String SNAKE_BODY_STRING = "o";
	private final static String FRUIT_STRING = "$";
	private final static String DYNAMITE_STRING = "#";

	private final static int GAME_SPEED_1 = 90;
	private final static int GAME_SPEED_2 = 75;
	private final static int GAME_SPEED_3 = 60;
	private final static int GAME_SPEED_4 = 45;
	private final static int GAME_SPEED_5 = 35;
	private final static int NEW_OBJECT_TIME_RATE = 6000;
	private final static int X_COORDINATE_OFFSET = 1;
	private final static int Y_COORDINATE_OFFSET = 2;
	private final static int OPTION_SPEED_1 = 32;
	private final static int OPTION_SPEED_2 = 34;
	private final static int OPTION_SPEED_3 = 36;
	private final static int OPTION_SPEED_4 = 38;
	private final static int OPTION_SPEED_5 = 40;
	private final static int QUIT_GAME      = 17;
	private final static int START_GAME   = 1;
	private final static int BACK_MAIN   = 2;

	private final static int RESTART_GAME = 15;
	private final static int MAIN_MENU    = 16;
	private final static int SnakeGame = 15;
	private final static int CarGame    = 16;
	private final static int Leader    = 17;

	private final static int QuiteMenu    = 18;
	private static SwingTerminal terminal;
	private static Screen screen;
	private GameState state;
	private Leaderboard leaderboard;
	private GameStateC stateC;
	private final int gameplay_height;
	private final int gameplay_width;
	private int selected_speed;
	protected GameView(int width, int height)
	{
		terminal = new SwingTerminal(width, height);

		gameplay_width  = width  - X_COORDINATE_OFFSET;
		gameplay_height = height - Y_COORDINATE_OFFSET;

		screen = new Screen(terminal);
		screen.setCursorPosition(null);
		screen.startScreen();
	}
	private void openMainMenu()
	{
		selected_speed = GAME_SPEED_1;

		renderMainMenu();
		refreshScreen();

		int selected_option = handleMainMenu();

		if(selected_option == START_GAME)
		{
			clearScreen();

			state = new GameState();
			startGame();
		}else if(selected_option==BACK_MAIN){
			clearScreen();
			openPickMenu();
		}
		else
		{
			exitGame();
		}
	}
	private int handleMainMenu()
	{
		Key k;

		while(true)
		{
			k = readKeyInput();

			if(k != null)
			{
				switch(k.getCharacter())
				{
					case '1':
						selected_speed = GAME_SPEED_1;
						highlighMainMenuSelectedOption(OPTION_SPEED_1);
						break;

					case '2':
						selected_speed = GAME_SPEED_2;
						highlighMainMenuSelectedOption(OPTION_SPEED_2);
						break;

					case '3':
						selected_speed = GAME_SPEED_3;
						highlighMainMenuSelectedOption(OPTION_SPEED_3);
						break;

					case '4':
						selected_speed = GAME_SPEED_4;
						highlighMainMenuSelectedOption(OPTION_SPEED_4);
						break;

					case '5':
						selected_speed = GAME_SPEED_5;
						highlighMainMenuSelectedOption(OPTION_SPEED_5);
						break;

					case 's':
						return START_GAME;
					case 'b':
						return BACK_MAIN;
					case 'q':
						return QUIT_GAME;

					default:
						break;
				}
			}

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	private void highlighMainMenuSelectedOption(int selected)
	{
		int y = 17;

		drawString(OPTION_SPEED_1, y, "1", Color.BLUE);
		drawString(OPTION_SPEED_2, y, "2", Color.BLUE);
		drawString(OPTION_SPEED_3, y, "3", Color.BLUE);
		drawString(OPTION_SPEED_4, y, "4", Color.BLUE);
		drawString(OPTION_SPEED_5, y, "5", Color.BLUE);

		if(selected == OPTION_SPEED_1)
		{
			drawString(OPTION_SPEED_1, y, "1", Color.WHITE);
		}
		else if(selected == OPTION_SPEED_2)
		{
			drawString(OPTION_SPEED_2, y, "2", Color.WHITE);
		}
		else if(selected == OPTION_SPEED_3)
		{
			drawString(OPTION_SPEED_3, y, "3", Color.WHITE);
		}
		else if(selected == OPTION_SPEED_4)
		{
			drawString(OPTION_SPEED_4, y, "4", Color.WHITE);
		}
		else if(selected == OPTION_SPEED_5)
		{
			drawString(OPTION_SPEED_5, y, "5", Color.WHITE);
		}
		refreshScreen();
	}
	private void renderMainMenu()
	{
		int x = 10;
		int y = 2;

		drawString(x, y, "##         ##         ##     ##          ########", Color.CYAN);
		drawString(x,++y,"##       ## ##       ##     ## ##              ##", Color.CYAN);
		drawString(x,++y," ##     ##   ##     ##     ##   ##            ##", Color.CYAN);
		drawString(x,++y," ##     ##   ##     ##     ##   ##           ## ", Color.CYAN);
		drawString(x,++y,"  ##   ##     ##   ##     #########      ####### ", Color.CYAN);
		drawString(x,++y,"  ##   ##     ##   ##     ##     ##        ## ", Color.CYAN);
		drawString(x,++y,"   ## ##       ## ##     ##       ##      ##", Color.CYAN);
		drawString(x,++y,"    ##          ##       ##        ##    ########", Color.CYAN);
		drawString(x,++y,"                                   ##                  ", Color.CYAN);
		drawString(x,++y,"                                  ##"                    , Color.CYAN);

		y += 2;	// 2 blank lines
		x = 22;

		drawString(x-2, y,   "##################################", Color.BLUE);
		drawString(x, ++y, "Naciśnij 'S' aby zacząć grę,", Color.BLUE);
		drawString(x, ++y, "'B' aby wrócić do menu głównego,", Color.BLUE);
		drawString(x, ++y, "'Q' aby wyjść.", Color.BLUE);

			// blank line

		drawString(x, ++y,  "Szybkość:", Color.BLUE);
		drawString(OPTION_SPEED_1, y,  "1", Color.WHITE);
		drawString(OPTION_SPEED_2, y,  "2", Color.BLUE);
		drawString(OPTION_SPEED_3, y, "3", Color.BLUE);
		drawString(OPTION_SPEED_4, y, "4", Color.BLUE);
		drawString(OPTION_SPEED_5, y, "5", Color.BLUE);
		drawString(x-2, ++y, "##################################", Color.BLUE);
	}
	private void openGameOverMenu()
	{


		leaderboard.addSS(Integer.toString(state.getScore()));
		leaderboard.Save(leaderboard.getscoresS(), "leadSnake.txt");
		clearGameObjects();

		renderGameOverMenu();
		refreshScreen();
		int selected_option = handleGameOverMenu();

		if(selected_option == RESTART_GAME)
		{
			clearScreen();

			state = new GameState();
			startGame();
		}
		else if(selected_option == MAIN_MENU)
		{
			clearScreen();

			openMainMenu();
		}
		else if(selected_option == QUIT_GAME)
		{
			exitGame();
		}
	}
	private int handleGameOverMenu()
	{
		int selected  = RESTART_GAME;

		Key k;

		while(true)
		{
			k = readKeyInput();

			if(k != null)
			{
				switch(k.getKind())
				{
					case ArrowDown:
						if(selected < QUIT_GAME)
							selected++;

						break;

					case ArrowUp:
						if(selected > RESTART_GAME)
							selected--;

						break;

					case Enter:
						return selected;

					default:
						break;
				}

				highlighGameOverMenuSelectedOption(selected);
			}

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	private void highlighGameOverMenuSelectedOption(int selected)
	{
		drawString(28, RESTART_GAME, "Zacznij od nowa", Color.BLUE);
		drawString(28, MAIN_MENU, "Powrót do menu gry", Color.BLUE);
		drawString(28, QUIT_GAME, "Wyjście", Color.BLUE);

		if(selected == RESTART_GAME)
		{
			drawString(28, RESTART_GAME, "Zacznij od nowa", Color.WHITE);
		}
		else if(selected == MAIN_MENU)
		{
			drawString(28, MAIN_MENU, "Powrót do menu gry", Color.WHITE);
		}
		else if(selected == QUIT_GAME)
		{
			drawString(28, QUIT_GAME, "Wyjście", Color.WHITE);
		}
		refreshScreen();
	}

	private void renderGameOverMenu()
	{
		int x = 20;
		int y = 2;

		drawString(x, y,  "#####    #######  ##### #####  ######", Color.CYAN);
		drawString(x, ++y,"##       ##   ##  ## ## ## ##  ##    ", Color.CYAN);
		drawString(x, ++y,"## ####  #######  ## ##### ##  ######", Color.CYAN);
		drawString(x, ++y,"##   ##  ##   ##  ##  ###  ##  ##    ", Color.CYAN);
		drawString(x, ++y,"#######  ##   ##  ##       ##  ######", Color.CYAN);

		y++;	// blank line

		drawString(x, ++y,"########  ###  ###  ######  ######### ", Color.CYAN);
		drawString(x, ++y,"##    ##  ###  ###  ###     ###  ###  ", Color.CYAN);
		drawString(x, ++y,"##    ##   ######   ######  ########  ", Color.CYAN);
		drawString(x, ++y,"##    ##    ####    ###     ###   ### ", Color.CYAN);
		drawString(x, ++y,"########     ##     ######  ###     ##", Color.CYAN);

		y++;	// blank line
		x = 28;

		drawString(x, ++y, "####################", Color.BLUE);
		drawString(x, ++y, "Zacznij od nowa", Color.WHITE);
		drawString(x, ++y, "Powrót do menu gry", Color.BLUE);
		drawString(x, ++y, "Wyjście", Color.BLUE);
		drawString(x, ++y, "####################", Color.BLUE);
	}

	private void openGameOverCMenu()
	{





		leaderboard.addCS(Integer.toString(stateC.getScore()));
		leaderboard.Save(leaderboard.getscoresC(), "leadCar.txt");
		clearGameObjectsC();
		renderGameOverCMenu();
		refreshScreen();

		int selected_option = handleGameOverCMenu();

		if(selected_option == RESTART_GAME)
		{
			clearScreen();

			stateC = new GameStateC();
			startGameCar();
		}
		else if(selected_option == MAIN_MENU)
		{
			clearScreen();

			openCarMenu();
		}
		else if(selected_option == QUIT_GAME)
		{
			exitGame();
		}
	}
	private int handleGameOverCMenu()
	{
		int selected  = RESTART_GAME;

		Key k;

		while(true)
		{
			k = readKeyInput();

			if(k != null)
			{
				switch(k.getKind())
				{
					case ArrowDown:
						if(selected < QUIT_GAME)
							selected++;

						break;

					case ArrowUp:
						if(selected > RESTART_GAME)
							selected--;

						break;

					case Enter:
						return selected;

					default:
						break;
				}

				highlighGameOverCMenuSelectedOption(selected);
			}

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	private void highlighGameOverCMenuSelectedOption(int selected)
	{
		drawString(28, RESTART_GAME, "Zacznij od nowa", Color.BLUE);
		drawString(28, MAIN_MENU, "Powrót do menu gry", Color.BLUE);
		drawString(28, QUIT_GAME, "Wyjście", Color.BLUE);

		if(selected == RESTART_GAME)
		{
			drawString(28, RESTART_GAME, "Zacznij od nowa", Color.WHITE);
		}
		else if(selected == MAIN_MENU)
		{
			drawString(28, MAIN_MENU, "Powrót do menu gry", Color.WHITE);
		}
		else if(selected == QUIT_GAME)
		{
			drawString(28, QUIT_GAME, "Wyjście", Color.WHITE);
		}
		refreshScreen();
	}

	private void renderGameOverCMenu()
	{
		int x = 20;
		int y = 2;

		drawString(x, y,  "#####    #######  ##### #####  ######", Color.CYAN);
		drawString(x, ++y,"##       ##   ##  ## ## ## ##  ##    ", Color.CYAN);
		drawString(x, ++y,"## ####  #######  ## ##### ##  ######", Color.CYAN);
		drawString(x, ++y,"##   ##  ##   ##  ##  ###  ##  ##    ", Color.CYAN);
		drawString(x, ++y,"#######  ##   ##  ##       ##  ######", Color.CYAN);

		y++;	// blank line

		drawString(x, ++y,"########  ###  ###  ######  ######### ", Color.CYAN);
		drawString(x, ++y,"##    ##  ###  ###  ###     ###  ###  ", Color.CYAN);
		drawString(x, ++y,"##    ##   ######   ######  ########  ", Color.CYAN);
		drawString(x, ++y,"##    ##    ####    ###     ###   ### ", Color.CYAN);
		drawString(x, ++y,"########     ##     ######  ###     ##", Color.CYAN);

		y++;	// blank line
		x = 28;

		drawString(x, ++y, "####################", Color.BLUE);
		drawString(x, ++y, "Zacznij od nowa", Color.WHITE);
		drawString(x, ++y, "Powrót do menu gry", Color.BLUE);
		drawString(x, ++y, "Wyjście", Color.BLUE);
		drawString(x, ++y, "####################", Color.BLUE);
	}

	protected void openPickMenu()
	{
		renderPickMenu();
		refreshScreen();
		leaderboard=new Leaderboard();
		leaderboard.ReadS("leadSnake.txt");
		leaderboard.ReadC("leadCar.txt");
		int selected_option = handlePickMenu();

		if(selected_option == SnakeGame)
		{
			clearScreen();
			openMainMenu();
		}
		else if(selected_option == CarGame)
		{
			clearScreen();

			openCarMenu();
		}
		else if(selected_option == Leader)
		{
			clearScreen();

			openLeaderBoard();
		}
		else if(selected_option == QuiteMenu)
		{
			exitGame();
		}
	}
	private int handlePickMenu()
	{
		int selected  = SnakeGame;

		Key k;

		while(true)
		{
			k = readKeyInput();

			if(k != null)
			{
				switch(k.getKind())
				{
					case ArrowDown:
						if(selected < QuiteMenu)
							selected++;

						break;

					case ArrowUp:
						if(selected > SnakeGame)
							selected--;

						break;

					case Enter:
						return selected;

					default:
						break;
				}

				highlighPickMenuSelectedOption(selected);
			}

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	private void highlighPickMenuSelectedOption(int selected)
	{
		drawString(28, SnakeGame, "Wąż", Color.BLUE);
		drawString(28, CarGame, "Samochody", Color.BLUE);
		drawString(28, Leader, "Rankingi", Color.BLUE);
		drawString(28, QuiteMenu, "Wyjście", Color.BLUE);

		if(selected == SnakeGame)
		{
			drawString(28, SnakeGame, "Wąż", Color.WHITE);
		}
		else if(selected == CarGame)
		{
			drawString(28, CarGame, "Samochody", Color.WHITE);
		}
		else if(selected == Leader)
		{
			drawString(28, Leader, "Rankingi", Color.WHITE);
		}
		else if(selected == QuiteMenu)
		{
			drawString(28, QuiteMenu, "Wyjście", Color.WHITE);
		}
		refreshScreen();
	}
	private void renderPickMenu(){
		int x = 20;
		int y = 12;

		drawString(x, y,  "Wybierz gre", Color.CYAN);

		y++;
		x = 28;

		drawString(x, ++y, "####################", Color.BLUE);
		drawString(x, ++y, "Wąż", Color.WHITE);
		drawString(x, ++y, "Samochody", Color.BLUE);
		drawString(x, ++y, "Rankingi", Color.BLUE);
		drawString(x, ++y, "Wyjście", Color.BLUE);
		drawString(x, ++y, "####################", Color.BLUE);


	}
	private void openCarMenu()
	{
		selected_speed = GAME_SPEED_1;

		renderCarMenu();

		// Make changes visible.
		refreshScreen();

		int selected_option = handleCarMenu();

		if(selected_option == START_GAME)
		{
			clearScreen();

			stateC = new GameStateC();
			startGameCar();
		} else if (selected_option==BACK_MAIN) {
			clearScreen();
			openPickMenu();

		} else
		{
			exitGame();
		}
	}
	private int handleCarMenu()
	{
		Key k;

		while(true)
		{
			k = readKeyInput();

			if(k != null)
			{
				switch(k.getCharacter())
				{
					case 's':
						return START_GAME;
					case 'b':
						return BACK_MAIN;
					case 'q':
						return QUIT_GAME;

					default:
						break;
				}
			}

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}


	private void renderCarMenu(){
		int x = 2;
		int y = 2;


		drawString(x, y, "                                                             ________           ", Color.CYAN);
		drawString(x,++y,"       ________                                             /   |    |          ", Color.CYAN);
		drawString(x,++y,"      /   |    |                   ________            ____/____|____|______   ", Color.CYAN);
		drawString(x,++y," ____/____|____|______            /   |    |          |   oo    o|    oo    |  ", Color.CYAN);
		drawString(x,++y,"|   oo    o|    oo    |      ____/____|____|______    |__o  o____|___o  o___|  ", Color.CYAN);
		drawString(x,++y,"|__o  o____|___o  o___|     |   oo    o|    oo    |       oo          oo       ", Color.CYAN);
		drawString(x,++y,"    oo          oo          |__o  o____|___o  o___|                            ", Color.CYAN);
		drawString(x,++y,"                                oo          oo                                 ", Color.CYAN);



		y += 2;	// 2 blank lines
		x = 22;

		drawString(x-2, y,   "##################################", Color.BLUE);
		drawString(x, ++y, "Naciśnij 'S' aby zacząć grę,", Color.BLUE);
		drawString(x, ++y, "'B' aby wrócić do menu głównego,", Color.BLUE);
		drawString(x, ++y, "'Q' aby wyjść.", Color.BLUE);

		y++;
		drawString(x-2, ++y, "##################################", Color.BLUE);


	}

	private void openLeaderBoard()
	{
		selected_speed = GAME_SPEED_1;

		renderLeaderBoard();

		// Make changes visible.
		refreshScreen();

		int selected_option = handleLeaderBoard();

		if (selected_option==BACK_MAIN) {
			clearScreen();
			openPickMenu();

		} else
		{
			exitGame();
		}
	}
	private int handleLeaderBoard()
	{
		Key k;

		while(true)
		{
			k = readKeyInput();

			if(k != null)
			{
				switch(k.getCharacter())
				{
					case 'b':
						return BACK_MAIN;
					case 'q':
						return QUIT_GAME;

					default:
						break;
				}
			}

			try
			{
				Thread.sleep(100);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}
		}
	}
	private Integer[] sort(Integer[] tab){
		int i,j,min_idx;
		Integer temp;
		for(i=0;i<tab.length-1;i++){
			min_idx=i;
			for(j=i+1;j<tab.length;j++){
				System.out.println(tab[min_idx]);
				System.out.println(tab[min_idx]);

				if(tab[j]<tab[min_idx]){
					min_idx=j;
				}
			}
			temp=tab[i];
			tab[i]=tab[min_idx];
			tab[min_idx]=temp;
		}
		return tab;
	}
	public void renderLeaderBoard()
	{

		int x = 3;
		int y = 2;
		Integer[] snakes= new Integer[leaderboard.getscoresS().size()];

		Integer[] carss= new Integer[leaderboard.getscoresC().size()];
		int pom=0;
		for(String s:leaderboard.getscoresC()){
			carss[pom]=Integer.parseInt(s);
			pom++;
		}
		Arrays.sort(carss);
		int rozc=carss.length;
		pom=0;
		for(String s:leaderboard.getscoresS()){
			snakes[pom]=Integer.parseInt(s);
			pom++;
		}
		Arrays.sort(snakes);
		int rozs=snakes.length;
		drawString(x, y, "Top 5 Snake", Color.CYAN);
		drawString(x,++y,"1."+snakes[rozs-1], Color.CYAN);
		drawString(x,++y,"2."+snakes[rozs-2], Color.CYAN);
		drawString(x,++y,"3."+snakes[rozs-3], Color.CYAN);
		drawString(x,++y,"4."+snakes[rozs-4], Color.CYAN);
		drawString(x,++y,"5."+snakes[rozs-5], Color.CYAN);
		drawString(x,++y,"", Color.CYAN);
		drawString(x,++y,"", Color.CYAN);

		x=30;
		y=2;

		drawString(x, y, " .-=========-.", Color.CYAN);
		drawString(x,++y, " \\'-=======-'/", Color.CYAN);
		drawString(x,++y," _|   .=.   |_", Color.CYAN);
		drawString(x,++y,"((|  {{1}}  |))", Color.CYAN);
		drawString(x,++y," \\|   /|\\   |/", Color.CYAN);
		drawString(x,++y,"  \\__ '`' __/", Color.CYAN);
		drawString(x,++y,"    _`) (`_", Color.CYAN);
		drawString(x,++y,"  _/_______\\_", Color.CYAN);
		drawString(x,++y," /___________\\", Color.CYAN);

		x=65;
		y=2;

		drawString(x, y, "Top 5 Cars", Color.CYAN);
		drawString(x,++y,"1."+carss[rozc-1], Color.CYAN);
		drawString(x,++y,"2."+carss[rozc-2], Color.CYAN);
		drawString(x,++y,"3."+carss[rozc-3], Color.CYAN);
		drawString(x,++y,"4."+carss[rozc-4], Color.CYAN);
		drawString(x,++y,"5."+carss[rozc-5], Color.CYAN);
		drawString(x,++y,"", Color.CYAN);
		drawString(x,++y,"", Color.CYAN);



		y += 2;	// 2 blank lines
		x = 22;

		drawString(x-2, y,   "##################################", Color.BLUE);
		drawString(x, ++y, "", Color.BLUE);
		drawString(x, ++y, "'B' aby wrócić do menu głównego,", Color.BLUE);
		drawString(x, ++y, "'Q' aby wyjść.", Color.BLUE);

		y++;
		drawString(x-2, ++y, "##################################", Color.BLUE);


	}
	private void startGame()
	{
		int counter = 0;

		drawWall();
		drawSnake();

		drawString(4, gameplay_height + 1, "SCORE: ", Color.CYAN);
		drawScore();
		while(state.isSnakeAlive())
		{
			if(counter % NEW_OBJECT_TIME_RATE == 0)
			{
				generateNewFruit();
				generateNewDynamite();
			}

			readKeyboard();
			if(counter % selected_speed == 0)
				updateGame();

			try
			{
				Thread.sleep(1);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}

			counter++;
		}
	}

	private void startGameCar()
	{

		drawWall();
		int counter = 0;
		drawString(4, gameplay_height + 1, "SCORE: ", Color.CYAN);
		drawCar();
		drawScoreC();
		refreshScreen();
		while(stateC.isCarAlive())
		{

			if(counter == 0 || counter % 10 == 0)
			{

				stateC.generateNewCar();
				for(LinkedList<Position> list: stateC.getOpponents()){
					drawOpponent(list);
				}



			}
			if (counter % 1 ==0){
				for(LinkedList<Position> list: stateC.getOpponents()){
					clearCar(list);

				}
				stateC.moveAll();
				for(LinkedList<Position> list: stateC.getOpponents()){

					drawOpponent(list);

				}
				drawScoreC();
			}
			readKeyboardC();

			if(counter % 2 == 0){
				updateGameC();}

			try
			{
				Thread.sleep(25);
			}
			catch (InterruptedException ie)
			{
				ie.printStackTrace();
			}

			counter++;
		}

	}
	private void updateGame()
	{
		Position tail = state.getSnakeTail();

		clearStringAt(tail.getX(), tail.getY());
		state.moveSnake();
		drawSnake();
		Position head = state.getSnakeHead();

		if(checkCollision())
		{
			highlightCrashPosition(head.getX(), head.getY());

			state.killSnake();

			openGameOverMenu();
		}
		else if(state.snakeAteFruit())
		{
			drawScore();

			generateNewFruit();
		}
		else if(state.snakeSteppedDynamite())
		{
			drawScore();
		}

		refreshScreen();
	}
	private void updateGameC()
{
	for(Position p : stateC.getCarBody())
	{
		clearStringAt(p.getX(), p.getY());
	}



	stateC.moveCar();
	drawCar();

	Position head = stateC.getCarHead();
	if(checkCollisionC())
	{
		highlightCrashPosition(head.getX(), head.getY());
		stateC.breakCar();

		openGameOverCMenu();
	}
	else if(stateC.car_collision())
	{
		stateC.breakCar();
		openGameOverCMenu();
	}


	refreshScreen();
}
	private void readKeyboard()
	{
		Key k = readKeyInput();

		if(k != null)
		{
			switch(k.getKind())
			{
				case ArrowUp:
					state.setDirection(Direction.UP);
					break;

				case ArrowDown:
					state.setDirection(Direction.DOWN);
					break;

				case ArrowLeft:
					state.setDirection(Direction.LEFT);
					break;

				case ArrowRight:
					state.setDirection(Direction.RIGHT);
					break;

				default:
					break;
			}
		}
	}
	private void readKeyboardC()
	{
		Key k = readKeyInput();

		if(k != null)
		{
			switch(k.getKind())
			{
				case ArrowUp:
					stateC.setDirection(Direction.UP);
					break;

				case ArrowDown:
					stateC.setDirection(Direction.DOWN);
					break;

				case ArrowLeft:
					stateC.setDirection(Direction.LEFT);
					break;

				case ArrowRight:
					stateC.setDirection(Direction.RIGHT);
					break;
				case Escape:
					exitGame();
				default:
					break;
			}
		}
	}

	private void drawWall()
	{
		for(int i = 0; i < gameplay_width; i++)
		{
			drawString(i, 0, BORDER_STRING, null);
			drawString(i, gameplay_height, BORDER_STRING, null);
		}

		for(int i = 0; i <= gameplay_height; i++)
		{
			drawString(0, i, BORDER_STRING, null);
			drawString(gameplay_width, i, BORDER_STRING, null);
		}
	}

	private boolean isWall(Position p)
	{
		if(p.getX() == 0 || p.getX() == gameplay_width ||
		   p.getY() == 0 || p.getY() == gameplay_height)
		{
			return true;
		}

		return false;
	}

	private boolean checkCollision()
	{
		Position head = state.getSnakeHead();

		if(isWall(head))
			return true;

		LinkedList<Position> body = state.getSnakeBody();
		if(body.subList(0, body.size()-1).contains(head))
			return true;

		return false;
	}

	private boolean checkCollisionC()
	{
		Position head = stateC.getCarHead();

		if(isWall(head))
			return true;

		return false;
	}

	private void drawString(int x, int y, String string, Terminal.Color fg_color)
	{
		screen.putString(x, y, string, fg_color, null);
	}

	private void clearStringAt(int x, int y)
	{
		drawString(x, y, EMPTY_STRING, null);
	}

	private void drawSnake()
	{
		Position head = state.getSnakeHead();

		for(Position p : state.getSnakeBody())
		{
			if(!p.equals(head))
			{
				drawString(p.getX(), p.getY(), SNAKE_BODY_STRING, Color.GREEN);
			}
			else
			{
				drawString(p.getX(), p.getY(), SNAKE_HEAD_STRING, Color.GREEN);
			}
		}
	}
	private void drawCar()
	{
		Position head = stateC.getCarHead();

		for(Position p : stateC.getCarBody())
		{
			if(!p.equals(head))
			{
				drawString(p.getX(), p.getY(), CAR_BODY_STRING, Color.GREEN);
			}
			else
			{
				drawString(p.getX(), p.getY(), CAR_HEAD_STRING, Color.GREEN);
			}
		}
	}
	private void drawOpponent(LinkedList<Position> posit)
	{
		Position head = posit.getFirst();

		for(Position p : posit)
		{
			if(p.getX()==0){
				drawString(p.getX(), p.getY(),BORDER_STRING , Color.WHITE);
			}
			else if(!p.equals(head))
			{
				drawString(p.getX(), p.getY(), CAR_BODY_STRING, Color.RED);
			}
			else
			{
				drawString(p.getX(), p.getY(), "(", Color.RED);
			}
		}
	}
	private void clearCar(LinkedList<Position> posit){
		for(Position p : posit)
		{
			if(p.getX()==0){
				drawString(p.getX(), p.getY(),BORDER_STRING , Color.WHITE);
			}
			else {
			clearStringAt(p.getX(), p.getY());
			}
		}
	}
	private void drawScore()
	{
		int s = state.getScore();
		drawString(10, gameplay_height + 1, Integer.toString(s), null);
	}
	private void drawScoreC()
	{
		int s = stateC.getScore();
		drawString(10, gameplay_height + 1, Integer.toString(s), null);
	}
	private void clearGameObjects()
	{
		for(Position p : state.getFruits())
		{
			clearStringAt(p.getX(), p.getY());
		}

		for(Position p : state.getDynamites())
		{
			clearStringAt(p.getX(), p.getY());
		}
	}
	private void clearGameObjectsC(){
		for(LinkedList<Position> lista: stateC.getOpponents()){
			for (Position p: lista){
				clearStringAt(p.getX(), p.getY());
			}
		}
		for (Position p: stateC.getCarBody()){
			clearStringAt(p.getX(), p.getY());
		}
	}

	private void generateNewFruit()
	{
		Position p = state.generateRandomObject(gameplay_width, gameplay_height);

		state.getFruits().add(new Position(p.getX(), p.getY()));
		drawString(p.getX(), p.getY(), FRUIT_STRING, Color.RED);
	}
	private void generateNewDynamite()
	{
		Position p = state.generateRandomObject(gameplay_width, gameplay_height);

		state.getDynamites().add(new Position(p.getX(), p.getY()));
		drawString(p.getX(), p.getY(), DYNAMITE_STRING, Color.YELLOW);
	}
	private void highlightCrashPosition(int x, int y)
	{
		drawString(x, y, "X", Color.RED);
	}
	private Key readKeyInput()
	{
		return terminal.readInput();
	}
	private void refreshScreen()
	{
		screen.refresh();
	}
	private void clearScreen()
	{
		screen.clear();
	}
	private void exitGame()
	{
		terminal.exitPrivateMode();
	}


}
