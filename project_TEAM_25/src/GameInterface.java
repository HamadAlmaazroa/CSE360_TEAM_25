/** Description of GameInterface
 *  This class create the GUI Interface and add call the other classes to make the game work
 *
 * @authors Salman Alyahya, Latifah Alhulelah, Abdullah Aljarrah, Hamad Almazroa, Rashed Almeqbali
 * @TeamNumber	25
 * @version April 15, 2016.
 */

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerListModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class GameInterface extends JFrame {

	/** String reference for the Cards in the main frame
	 *  */
    private final static String titlePagePanelRef = "titlePagePanel";
    private final static String namePageRef = "namePage";
    private final static String gameScoresPageRef = "gameScoresPage";
    private final static String gamePlayPageRef = "gamePlayPage";
    
    /** String reference for the Cards in the gamePlay JPanel
	 *  */
    private final static String throwDiceRef = "throwDice";
    private final static String guessingRef = "guessing";
    private final static String optionsBoredButtonRef = "optionsBoredButton";
    private final static String optionsBoredRef = "optionsBored";


    private JPanel pagesCards, titlePagePanel, namePage, gameScoresPage, gameplayPage;
    private JLabel winnerLabel, gamePagePlayer1Score, gamePagePlayer2Score, currentTurnLabel,helpRangeText,helpEvenOddText;
    private JButton playAnother,  throwDiceB;
    private Player player1, player2, currentFirstPlayer, currentSecondPlayer;
    private String rangeM , evenOddM;
    private boolean playerTroTurn;
    private int currentPlayer;
        
    /** The constructor of the class it create a new JPanel for the cards layout of the main frame.
     * 	it also create new Player objects for each player
     * 	it calls the initiation method for creating the GUI.
   	 *  */
    public GameInterface() 
    {
    	pagesCards = new JPanel(new CardLayout());
        player1=new Player();
        player2=new Player();
        initComponents();
    }
    
    /** The method for the initiation and the creating the main frame of the GUI, 
     * then calls each method to create the cards of the pages of the game
   	 *  */
    private void initComponents() {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        titlePage();        
        namePage();
        scoresPage();     
        gamePlayPage();

        //Create the panel that contains the "cards".
        pagesCards.add(titlePagePanel,titlePagePanelRef);
        pagesCards.add(namePage,namePageRef);
        pagesCards.add(gameScoresPage,gameScoresPageRef);
        pagesCards.add(gameplayPage,gamePlayPageRef);

        
        frame.add(pagesCards);
        frame.setPreferredSize(new Dimension(350, 300));
        frame.pack();
        frame.setTitle( "Dice Game" );
        frame.setVisible(true);
        
    }
    
    /** The method create a JLabel from the given parameter and return it
     * 
     * @param text		the text to be put in the JLabel
     * @param fontSize	The size of the font
     * @param x			The x location on the label
     * @param y			The y location on the label
     * @param width		The width of the label
     * @param height	The height of the label
     * @return temp		THe JLabel to be created
   	 *  */
    private JLabel createLabel(String text,int fontSize,int x, int y, int width, int height, Color color)
    {
    	JLabel temp = new JLabel(text);
       	temp.setFont(new Font("Serif", Font.PLAIN, fontSize));
       	temp.setBounds(x, y, width, height);
       temp.setForeground(color);
       return temp;
    }
    
    /** The method create a JLabel for the images from the given parameter and return it
     * 
     * @param fileName	the fileName of the image
     * @param widthScale The width that the image is to be scaled to
     * @param heightScale The height that the image is to be scaled to
     * @param x			The x location on the label
     * @param y			The y location on the label
     * @param width		The width of the label
     * @param height	The height of the label
     * @return temp		THe JLabel to be created
   	 *  */
    private JLabel createImageLabel(String fileName,int widthScale, int heightScale, int x, int y, int width, int height)
    {
    	JLabel temp = new JLabel();
    	temp = new JLabel();
        ImageIcon imageIcon2 = new ImageIcon(fileName); // load the image to a imageIcon
        Image image2 = imageIcon2.getImage(); // transform it 
        Image newimg2 = image2.getScaledInstance(widthScale, heightScale,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
        imageIcon2 = new ImageIcon(newimg2);  // transform it back
        temp.setIcon( imageIcon2 );
        temp.setBounds( x, y, width, height );
        validate();
       return temp;
    }
    
    /** The method for the initiation of the first page (title page) and its buttons, its images and labels.
   	 *  */
    private void titlePage()
    {
        JPanel titleButtonPanel;
        JLabel titleJLabel, dieImgJLabel, dieImgJLabel2;
        JButton titleToScoresB, titlePlayB;

    	titlePagePanel = new JPanel(null);
        
    	titleJLabel = createLabel("Dice Game", 29, 70, 40, 150, 64, Color.black);
   	    titlePagePanel.add(titleJLabel);
        
   	    dieImgJLabel = createImageLabel("Images//DiesImg.png",30, 29, 210, 40, 64, 64);
        titlePagePanel.add(dieImgJLabel);
        
        dieImgJLabel2 = createImageLabel("Images//dice.png", 138, 112,  202, 140, 132, 112);
        titlePagePanel.add(dieImgJLabel2);
        
        titleToScoresB = new JButton("View Scores");
        titleToScoresB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	scoresPage();	//calling the scorePage
                pagesCards.add(gameScoresPage,gameScoresPageRef);	//repainting the page
                CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                cl.show(pagesCards, gameScoresPageRef);	//go to the referenced card
            }
        });

        titlePlayB = new JButton("Play Game");
        titlePlayB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                cl.show(pagesCards, namePageRef);
            }
        });
        
        titleButtonPanel = new JPanel();
        titleButtonPanel.add(titlePlayB);
        titleButtonPanel.add(titleToScoresB);
        titleButtonPanel.setBounds( 70, 120, 200, 100 );
        titlePagePanel.add(titleButtonPanel, BorderLayout.SOUTH);
    }
    
    /** The method for the filling the panel of the list of the scores that is saved in the database
     * 
     * @return temp 		The list of the scores.
   	 *  */
    private JPanel fillScoresList()
    {
        Database printingScores = new Database();		
    	List list = printingScores.getScoreList();		// getting the List of the core from the dataBase
        int numberOfData = 10;							// number of data per column
    	JPanel temp = new JPanel(new GridLayout(numberOfData,2));
         String str = "",str2 = "";
         
         for(int i = 1; i<= numberOfData; i++)
         {
         	if(i <= list.size())
         	{
         		str = i+") " + list.get(i-1);
         	}
         	else
         		str = i+") ";
         	if((i + numberOfData) <= list.size())
             	str2 = (i + numberOfData) + ") " + list.get((i + numberOfData) - 1);
             else
            	 str2 = (i + numberOfData) + ")";
         	 temp.add(new JLabel(str));
         	 temp.add(new JLabel(str2));
         }
         return temp;
    }
    
    /** The method for the initiation of the score page and its buttons, and labels.
   	 *  */
    private void scoresPage()
    {
        JPanel scoreList, scoreButtonPanel;
        JLabel scoresTitleLabel;
        JButton backFromScoresB;

        gameScoresPage = new JPanel(new BorderLayout());
  	    scoresTitleLabel = new JLabel("Game Scores", SwingConstants.CENTER);
  	    scoresTitleLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        gameScoresPage.add(scoresTitleLabel, BorderLayout.PAGE_START);
        
        //filling the panel with the scores
        scoreList = fillScoresList();
        gameScoresPage.add(scoreList,BorderLayout.CENTER);
       
        backFromScoresB = new JButton("Back to title page");
        backFromScoresB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) (pagesCards.getLayout());
                cl.show(pagesCards, titlePagePanelRef);
            }
        });
        
        scoreButtonPanel = new JPanel();
        scoreButtonPanel.add(backFromScoresB);
        gameScoresPage.add(scoreButtonPanel,BorderLayout.PAGE_END);
    }

    /** The method for the initiation of the name page and its buttons, and labels.
   	 *  */
    private void namePage()
    {
        JPanel nameButtonPanel;
        JLabel namePageJLabel, player1NameLabel, player2NameLabel;
        JButton nameBackToTitleB, nametoGameB;
        JTextField player1NameText, player2NameText; 
    	
    	namePage = new JPanel(null);
        
    	namePageJLabel = createLabel("Name of Players",24, 100, 0, 350, 65, Color.black);
        namePage.add(namePageJLabel); 
        
    	player1NameLabel = createLabel("Player 1 name:",15,20, 40, 150, 64, Color.black);
        namePage.add(player1NameLabel); 
        
        player2NameLabel = createLabel("Player 2 name:",15,20, 80, 150, 64, Color.black);
        namePage.add(player2NameLabel);
        
        player1NameText = new JTextField();
        player1NameText.setBounds( 110, 65, 150, 20 );
        namePage.add(player1NameText); 

        player2NameText = new JTextField();
        player2NameText.setBounds( 110, 105, 150, 20 );
        namePage.add(player2NameText);
        
        nameBackToTitleB = new JButton("Back to title page");
        nameBackToTitleB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                cl.show(pagesCards, titlePagePanelRef);
            }
        });
        
        nametoGameB = new JButton("continue");
        nametoGameB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	initGame();								// Initializing the values for the game.
            	player1.setName(player1NameText.getText());
            	player2.setName(player2NameText.getText());
            	update();								// updating the names of the players before starting the game
                CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                cl.show(pagesCards, gamePlayPageRef);             
            }
        });
        
        nameButtonPanel = new JPanel();
        nameButtonPanel.add(nametoGameB);
        nameButtonPanel.add(nameBackToTitleB);
        nameButtonPanel.setBounds( 10, 165, 330, 350 );
        namePage.add(nameButtonPanel, BorderLayout.SOUTH);
    }
    
    /** The method for the initializing the values for the game.
   	 *  */
    private void initGame()
    {
    	player1=new Player();
        player2=new Player();
        currentFirstPlayer = player1;
        currentSecondPlayer = player2;
        rangeM = "";
        evenOddM = "";
        playerTroTurn = true;
        currentPlayer = 1;
    	playAnother.setVisible(false);
    	winnerLabel.setText("");
    	throwDiceB.setVisible(true);
    }
    
    /** The method for updating the labels of the game.
   	 *  */
    private void update()
    {
    	gamePagePlayer1Score.setText("Player 1 Score: "+player1.getScore());
    	gamePagePlayer2Score.setText("Player 2 Score: "+player2.getScore());
    	if(playerTroTurn)
    		currentTurnLabel.setText("Player "+currentPlayer+" Turn ("+currentFirstPlayer.getName()+")");
    	else
    		currentTurnLabel.setText("Player "+currentPlayer+" Turn ("+currentSecondPlayer.getName()+")");
    	helpRangeText.setText(rangeM);
    	helpEvenOddText.setText(evenOddM);
    }
    
    /** The method for the initiation of the name page and its buttons, and labels.
     * 	and the buttons ActionListener for navigating throw the game
   	 *  */
    private void gamePlayPage()
    {
        JPanel currentGameTPanel,currentGameGPanel, optionBoredPanel,optionBoredBPanel;
        JLabel dieImgJLabel3, lastValue ;
        JButton optionBoardButton, optionBBack, evenOddButton, rangeButton, makeGuessB ;
        JComboBox<Integer> comboCount ;
        final JPanel currentGameCards = new JPanel(new CardLayout()), optionBoardCards = new JPanel(new CardLayout());;
        GameEngine engine = new GameEngine();
        
       	gameplayPage = new JPanel(null);
       	
       	dieImgJLabel3 = createImageLabel("Images//dice_shaker.png",164, 135,  170, -18, 164, 135);
        gameplayPage.add(dieImgJLabel3);
       	
        gamePagePlayer1Score = createLabel("Player 1 Score: "+player1.getScore(),18, 0, 0, 200, 65, Color.black);
        gameplayPage.add(gamePagePlayer1Score); 
        
        gamePagePlayer2Score = createLabel("Player 2 Score: "+player2.getScore(),18, 0, 25, 200, 65, Color.black);
        gameplayPage.add(gamePagePlayer2Score);
        
        currentTurnLabel = createLabel("Player 1 Turn ("+player1.getName()+")",18, 0, 50, 350, 65, Color.red);
        gameplayPage.add(currentTurnLabel);

        lastValue = createLabel("",18, 0, 70, 350, 65, Color.black);
        gameplayPage.add(lastValue);
        
        winnerLabel = createLabel("",20,0, 90, 350, 65 , Color.magenta);
        gameplayPage.add(winnerLabel);
       	
        helpRangeText = createLabel("",15,190, 80, 200, 65, Color.blue);
        gameplayPage.add(helpRangeText);
        
        helpEvenOddText = createLabel("",15, 190, 100, 200, 65, Color.blue);
        gameplayPage.add(helpEvenOddText);
        
        
        currentGameTPanel = new JPanel(null);
        currentGameGPanel = new JPanel(null);
        optionBoredPanel= new JPanel(null);
        optionBoredBPanel= new JPanel();
    	
    	Integer numbers[] = new Integer[31]; // the list number to be guessed from 6 since each dice is 1 min to 36
    	for(int i=0; i<31; i++)
    		numbers[i] = (i+6);

    	comboCount = new JComboBox<Integer>(numbers);
    	comboCount.setBounds( 23, 30, 60, 25 );
      	currentGameGPanel.add(comboCount);
      	
    	playAnother = new JButton("Play Another Game");
		playAnother.setBounds( 10, 150, 150, 25 );
		playAnother.setVisible(false);
		gameplayPage.add(playAnother);
		playAnother.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent ae) {
                  	lastValue.setText("");
                  	CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                    cl.show(pagesCards, namePageRef);            	
              }
          });
		 	
        optionBoardButton = new JButton("Options Board");
        optionBoardButton.setBounds(15, 40, 115, 25);
        optionBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	  CardLayout cl = (CardLayout) (optionBoardCards.getLayout());//get cards
                  cl.show(optionBoardCards, optionsBoredButtonRef);          	
            }
        });

        optionBBack = new JButton("Back");
        optionBBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	  CardLayout cl = (CardLayout) (optionBoardCards.getLayout());//get cards
                  cl.show(optionBoardCards, optionsBoredRef);           	
            }
        });
        
        evenOddButton = new JButton("Even Or Odd");
        evenOddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	evenOddM = engine.getEvenOdd();		
            	update();
            }
        });
        
        rangeButton = new JButton("Range");
        rangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	rangeM = engine.getRange();
            	update();
            }
        });
        
        throwDiceB = new JButton("throw the dices");
        throwDiceB.setBounds( 10, 65, 135, 25 );
    	throwDiceB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                	engine.throwDices();	
                	optionBoardButton.setVisible(true);
                	playerTroTurn = false;
                	lastValue.setText("");
                	if(currentFirstPlayer == player1)
                		currentPlayer = 2;
                	else
                		currentPlayer = 1;
                	update();              	
            	  CardLayout cl = (CardLayout) (currentGameCards.getLayout());//get cards
                  cl.show(currentGameCards, guessingRef);
            	
            }
        });
    	
    	makeGuessB = new JButton("make a guess");
    	makeGuessB.setBounds( 23, 65, 115, 25 );
    	makeGuessB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                	optionBoardButton.setVisible(false);
                		playerTroTurn = true;
                		lastValue.setText("last total of the dices was: "+engine.getTotalDices());
                		engine.updateGuessedNum((Integer)comboCount.getSelectedItem());
                		currentSecondPlayer.updateScore(engine.getScore());
                		currentFirstPlayer.updateScore(engine.getP2Score());
                	if(currentFirstPlayer == player1)
                	{
                		currentPlayer = 2;
                		currentFirstPlayer = player2;
                		currentSecondPlayer = player1;
                	}
                	else
                	{
                		currentPlayer = 1;
                		currentFirstPlayer = player1;
                		currentSecondPlayer = player2;
                	}
                	rangeM = "";
                	evenOddM = "";
                	update();
                	if(engine.gameEnded(player1, player2))
                	{
                		winnerLabel.setText(engine.getWinner());
                  	  	winnerLabel.setVisible(true);
                		playAnother.setVisible(true);
                		throwDiceB.setVisible(false);
                	}
            	  CardLayout cl = (CardLayout) (currentGameCards.getLayout());//get cards
                  cl.show(currentGameCards, throwDiceRef);
                  CardLayout c2 = (CardLayout) (optionBoardCards.getLayout());//get cards
                  c2.show(optionBoardCards, optionsBoredRef);
            	
            }
        });
 	
        optionBoredPanel.add(optionBoardButton);
        optionBoredBPanel.add(rangeButton);
        optionBoredBPanel.add(evenOddButton);
        optionBoredBPanel.add(optionBBack);

        currentGameTPanel.add(throwDiceB);
        currentGameGPanel.add(makeGuessB);

        currentGameCards.add(currentGameTPanel,throwDiceRef);
        currentGameCards.add(currentGameGPanel,guessingRef);
        currentGameCards.setBounds(0, 150, 175, 100);
        
        optionBoardCards.add(optionBoredPanel,optionsBoredRef);
        optionBoardCards.add(optionBoredBPanel,optionsBoredButtonRef);   
        optionBoardCards.setBounds(190, 150, 140, 200);

        gameplayPage.add(currentGameCards);
        gameplayPage.add(optionBoardCards);

        
    }
    /**	The main method to run the game.
      * 
     **/
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater(new Runnable() 
        {
            @Override
            public void run() 
            {
                new GameInterface();
            }
        });
    }
}