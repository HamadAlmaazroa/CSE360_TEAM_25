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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import java.net.URL;

public class GameInterface extends JFrame {

	/** String reference for the Cards in the main frame
	 *  */
    private final static String titlePagePanelRef = "titlePagePanel";
    private final static String namePageRef = "namePage";
    private final static String gameScoresPageRef = "gameScoresPage";
    private final static String gamePlayPageRef = "gamePlayPage";
    private final static String instructionsRef = "instructionsPage";

    /** String reference for the Cards in the gamePlay JPanel
	 *  */
    private final static String throwDiceRef = "throwDice";
    private final static String guessingRef = "guessing";
    private final static String optionsBoredButtonRef = "optionsBoredButton";
    private final static String optionsBoredRef = "optionsBored";

    private JPanel pagesCards, titlePagePanel, namePage, gameScoresPage, gameplayPage, instructionsPage;
    private JLabel winnerLabel, gamePagePlayer1Score, gamePagePlayer2Score, currentTurnLabel,helpRangeText,helpEvenOddText;
    private JLabel diceImg1, diceImg2, diceImg3, diceImg4, diceImg5, diceImg6;
    private JButton playAnother,  throwDiceB;
    private Player player1, player2, currentFirstPlayer, currentSecondPlayer;
    private String rangeM , evenOddM;
    private boolean playerTroTurn, challengingMode;
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
        challengingMode = false;
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
        instructionsPage();
        
        //Create the panel that contains the "cards".
        pagesCards.add(titlePagePanel,titlePagePanelRef);
        pagesCards.add(namePage,namePageRef);
        pagesCards.add(gameScoresPage,gameScoresPageRef);
        pagesCards.add(gameplayPage,gamePlayPageRef);
        pagesCards.add(instructionsPage,instructionsRef);
        
        frame.add(pagesCards);
        frame.setPreferredSize(new Dimension(800, 600));
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
    	URL url = GameInterface.class.getResource(fileName);
    	JLabel temp = new JLabel();
    	temp = new JLabel();
        ImageIcon imageIcon2 = new ImageIcon(url); // load the image to a imageIcon
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
        
    	titleJLabel = createLabel("Dice Game", 60, 200, 100, 500, 100, Color.black);
   	    titlePagePanel.add(titleJLabel);
        
   	    dieImgJLabel = createImageLabel("DiesImg.png",60, 59, 485, 120, 64, 64);
        titlePagePanel.add(dieImgJLabel);
        
        dieImgJLabel2 = createImageLabel("dice.png", 280, 227,  515, 290, 300, 300);
        titlePagePanel.add(dieImgJLabel2);
        
        titleToScoresB = new JButton("View Scores");
        titleToScoresB.setPreferredSize(new Dimension(220, 50));
        titleToScoresB.setFont(new Font("Serif", Font.PLAIN, 30));
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
        titlePlayB.setPreferredSize(new Dimension(200, 50));
        titlePlayB.setFont(new Font("Serif", Font.PLAIN, 30));
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
        titleButtonPanel.setBounds( 230, 230, 250, 250 );
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
         	
         	 JLabel tempLabel1 = new JLabel(str);
 			 tempLabel1.setFont(new Font("", Font.PLAIN, 24));
         	 temp.add(tempLabel1);
         	 JLabel tempLabel2 = new JLabel(str2);
 			 tempLabel2.setFont(new Font("", Font.PLAIN, 24));
         	 temp.add(tempLabel2);
         }
         return temp;
    }
    
    /** The method for the initiation of the score page and its buttons, and labels.
   	 *  */
    private void scoresPage()
    {
        JPanel scoreList, scoreButtonPanel;
        JLabel scoresTitleLabel;
        JButton backFromScoresB, resetScoresB;
        Database dataBase = new Database();		

        gameScoresPage = new JPanel(new BorderLayout());
  	    scoresTitleLabel = new JLabel("Game Scores", SwingConstants.CENTER);
  	    scoresTitleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
        gameScoresPage.add(scoresTitleLabel, BorderLayout.PAGE_START);
        
        //filling the panel with the scores
        scoreList = fillScoresList();
        gameScoresPage.add(scoreList,BorderLayout.CENTER);
       
        backFromScoresB = new JButton("Back to title page");
        backFromScoresB.setPreferredSize(new Dimension(250, 40));
        backFromScoresB.setFont(new Font("SansSerif", Font.PLAIN, 25));
        backFromScoresB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) (pagesCards.getLayout());
                cl.show(pagesCards, titlePagePanelRef);
            }
        });
        
        resetScoresB = new JButton("Reset Scores");
        resetScoresB.setPreferredSize(new Dimension(200, 40));
        resetScoresB.setFont(new Font("SansSerif", Font.PLAIN, 25));
        resetScoresB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	dataBase.resetScores();
                CardLayout cl = (CardLayout) (pagesCards.getLayout());
                cl.show(pagesCards, titlePagePanelRef);
            }
        });
        
        scoreButtonPanel = new JPanel();
        scoreButtonPanel.add(backFromScoresB);
        scoreButtonPanel.add(resetScoresB);
        gameScoresPage.add(scoreButtonPanel,BorderLayout.PAGE_END);
    }

    /** The method for the initiation of the name page and its buttons, and labels.
   	 *  */
    private void instructionsPage()
    {
    	JLabel instructionsTitleLabel, instructionsText1, instructionsText2, instructionsText3, instructionsHeadPoints1, instructionsHeadPoints2, instructionsHeadPoints3 ;
    	JButton instructionsBack;
    	JPanel instructionsButtons, textPanel;
    	
    	instructionsPage = new JPanel(new BorderLayout());
    			
    	instructionsTitleLabel = new JLabel("Game Instructions", SwingConstants.CENTER);
    	instructionsTitleLabel.setFont(new Font("Serif", Font.PLAIN, 50));
    	instructionsPage.add(instructionsTitleLabel, BorderLayout.PAGE_START);
    	
    	textPanel = new JPanel(null);
        instructionsPage.add(textPanel,BorderLayout.CENTER);
        
        instructionsHeadPoints1 = createLabel("Normal Mode:",23, 5, 0, 500, 100, Color.magenta);
        textPanel.add(instructionsHeadPoints1);
        
        instructionsText1 = createLabel("<html>First, Player 1 throw the dice, and Player 2 try to guess the total number of the thrown dice.<br>"
        		+ "If the guessed number is the same, Player 2 gets 15 points.<br>"
        		+ "If the gueesed number is with in 5 numbers, Player 2 gets 4 points.<br>"
        		+ "If the gueesed number is with in 10 numbers, Player 2 gets 2 points.<br>"
        		+ "If the gueesed number is with in more than 10 numbers, Player 1 gets 3 points.<br>"
        		+ "After the turn ends, Player 1 and Player 2 switch role.</html>",18, 5, 30, 800, 200, Color.black);
        textPanel.add(instructionsText1);
        
        instructionsHeadPoints2 = createLabel("Challenge Mode:",23, 5, 165, 500, 100, Color.magenta);
        textPanel.add(instructionsHeadPoints2);
        
        instructionsText2 = createLabel("<html>Challenge Mode is the same as Normal Mode<br>"
        		+ "However, The player guessing the total of the dice will lose the same number of point<br>"
        		+ "as the highest number of matched dice<br>"
        		+ "For Example, if the dice were 5,5,4,2,1,5 Then the player guessing will lose 3 points.</html>",18, 5, 175, 800, 200, Color.black);
        textPanel.add(instructionsText2);
        
        instructionsHeadPoints3 = createLabel("Option Board:",23, 5, 290, 500, 100, Color.magenta);
        textPanel.add(instructionsHeadPoints3);
        
        instructionsText3 = createLabel("<html>Using the Option Board has a Penalty<br>"
        		+ "When Range is used, The guessing Player either get 5 point for the correct total, or 0 points.<br>"
        		+ "When Even Odd is used, The guessing Player will only get half of the points.</html>",18, 5, 295, 800, 200, Color.black);
        textPanel.add(instructionsText3);

        instructionsBack = new JButton("Back to name page");
        instructionsBack.setPreferredSize(new Dimension(280, 40));
        instructionsBack.setFont(new Font("SansSerif", Font.PLAIN, 25));
        instructionsBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) (pagesCards.getLayout());
                cl.show(pagesCards, namePageRef);
            }
        });
        
        instructionsButtons = new JPanel();
        instructionsButtons.add(instructionsBack);
        instructionsPage.add(instructionsButtons,BorderLayout.PAGE_END);
        
    }
    
    /** The method for the initiation of the name page and its buttons, and labels.
   	 *  */
    private void namePage()
    {
        JPanel nameButtonPanel;
        JLabel namePageJLabel, player1NameLabel, player2NameLabel;
        JButton nameBackToTitleB, nametoGameB, instructionsButton;
        JRadioButton normalRadio, challengeRadio;
        JTextField player1NameText, player2NameText; 
    	
    	namePage = new JPanel(null);
        
    	namePageJLabel = createLabel("Name of Players",70, 180, 0, 500, 100, Color.black);
        namePage.add(namePageJLabel); 
        
    	player1NameLabel = createLabel("Player 1 name:",35,20, 130, 300, 64, Color.black);
        namePage.add(player1NameLabel); 
        
        player2NameLabel = createLabel("Player 2 name:",35,20, 200, 300, 64, Color.black);
        namePage.add(player2NameLabel);
        
        player1NameText = new JTextField();
        player1NameText.setFont(new Font("SansSerif", Font.PLAIN, 25));
        player1NameText.setBounds( 250, 150, 300, 40 );
        namePage.add(player1NameText); 

        player2NameText = new JTextField();
        player2NameText.setFont(new Font("SansSerif", Font.PLAIN, 25));
        player2NameText.setBounds( 250, 220, 300, 40 );
        namePage.add(player2NameText);

        normalRadio = new JRadioButton("Normal Mode", true);
        normalRadio.setMnemonic(KeyEvent.VK_C);
        normalRadio.setBounds(200, 300, 200, 50);
        normalRadio.setFont(new Font("SansSerif.bold", Font.PLAIN, 22));
        normalRadio.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {         
               challengingMode = false;
            }           
         });
        
        challengeRadio = new JRadioButton("Challenge Mode");
        challengeRadio.setMnemonic(KeyEvent.VK_M);
        challengeRadio.setBounds(400, 300, 250, 50);
        challengeRadio.setFont(new Font("SansSerif.bold", Font.PLAIN, 22));
        challengeRadio.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {             
            	challengingMode = true;
            }           
         });

       //Group the radio buttons.
         ButtonGroup group = new ButtonGroup();
         group.add(normalRadio);
         group.add(challengeRadio);
         
         namePage.add(normalRadio);
         namePage.add(challengeRadio);

        nameBackToTitleB = new JButton("Back to title page");
        nameBackToTitleB.setPreferredSize(new Dimension(300, 50));
        nameBackToTitleB.setFont(new Font("Serif", Font.PLAIN, 30));
        nameBackToTitleB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                cl.show(pagesCards, titlePagePanelRef);
            }
        });
        
        instructionsButton = new JButton("Game Instructions");
        instructionsButton.setPreferredSize(new Dimension(280, 50));
        instructionsButton.setFont(new Font("Serif", Font.PLAIN, 30));
        instructionsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                cl.show(pagesCards, instructionsRef);  
            }
        });
        
        
        nametoGameB = new JButton("Continue");
        nametoGameB.setPreferredSize(new Dimension(220, 50));
        nametoGameB.setFont(new Font("Serif", Font.PLAIN, 30));
        nametoGameB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	initGame();								// Initializing the values for the game.
            	player1.setName(player1NameText.getText());
            	player2.setName(player2NameText.getText());
            	update();								// updating the names of the players before starting the game.
                pagesCards.add(gameplayPage,gamePlayPageRef);
                CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                cl.show(pagesCards, gamePlayPageRef);             
            }
        });
        
        nameButtonPanel = new JPanel();
        nameButtonPanel.add(nametoGameB);
        nameButtonPanel.add(nameBackToTitleB);
        nameButtonPanel.add(instructionsButton);
        nameButtonPanel.setBounds( 120, 400, 550, 350 );
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
        JLabel dieImgJLabel3, lastValue, lastScoreP1, lastScoreP2;
        JButton optionBoardButton, optionBBack, evenOddButton, rangeButton, makeGuessB ;
        JComboBox<Integer> comboCount ;
        final JPanel currentGameCards = new JPanel(new CardLayout()), optionBoardCards = new JPanel(new CardLayout());;
        GameEngine engine = new GameEngine();
        
       	gameplayPage = new JPanel(null);
       	
       	dieImgJLabel3 = createImageLabel("dice_shaker.png",280, 230,  500, -18, 280, 230);
        gameplayPage.add(dieImgJLabel3);
        
        diceImg1 = new JLabel();
        diceImg2 = new JLabel();
        diceImg3 = new JLabel();
        diceImg4 = new JLabel();  
        diceImg5 = new JLabel();
        diceImg6 = new JLabel();
        
        gamePagePlayer1Score = createLabel("Player 1 Score: "+player1.getScore(),25, 0, 0, 300, 65, Color.black);
        gameplayPage.add(gamePagePlayer1Score); 
        
        gamePagePlayer2Score = createLabel("Player 2 Score: "+player2.getScore(),25, 0, 40, 300, 65, Color.black);
        gameplayPage.add(gamePagePlayer2Score);
        
        currentTurnLabel = createLabel("Player 1 Turn ("+player1.getName()+")",25, 0, 80, 480, 65, Color.red);
        gameplayPage.add(currentTurnLabel);

        lastValue = createLabel("",25, 0, 120, 400, 65, Color.black);
        gameplayPage.add(lastValue);
        
        lastScoreP1 = createLabel("",25, 0, 160, 400, 65, Color.blue);
        gameplayPage.add(lastScoreP1);
        
        lastScoreP2 = createLabel("",25, 0, 200, 400, 65, Color.blue);
        gameplayPage.add(lastScoreP2);
        
        winnerLabel = createLabel("", 40, 310, 260, 350, 65 , Color.magenta);
        gameplayPage.add(winnerLabel);
       	
        helpRangeText = createLabel("",25,500, 300, 300, 65, Color.blue);
        gameplayPage.add(helpRangeText);
        
        helpEvenOddText = createLabel("",25, 500, 340, 300, 65, Color.blue);
        gameplayPage.add(helpEvenOddText);
        
        
        currentGameTPanel = new JPanel(null);
        currentGameGPanel = new JPanel(null);
        optionBoredPanel= new JPanel(null);
        optionBoredBPanel= new JPanel();
    	
    	Integer numbers[] = new Integer[31]; // the list number to be guessed from 6 since each dice is 1 min to 36
    	for(int i=0; i<31; i++)
    		numbers[i] = (i+6);

    	comboCount = new JComboBox<Integer>(numbers);
    	comboCount.setFont(new Font("SansSerif", Font.PLAIN, 25));
    	comboCount.setBounds( 70, 110, 60, 40 );
      	currentGameGPanel.add(comboCount);
      	
    	playAnother = new JButton("Play Another Game");
    	playAnother.setFont(new Font("SansSerif", Font.PLAIN, 25));
		playAnother.setBounds( 270, 325, 300, 40 );
		playAnother.setVisible(false);
		gameplayPage.add(playAnother);
		playAnother.addActionListener(new ActionListener() {
                  @Override
                  public void actionPerformed(ActionEvent ae) {
                  	lastValue.setText("");
                  	lastScoreP1.setText("");
                  	lastScoreP2.setText("");
                  	CardLayout cl = (CardLayout) (pagesCards.getLayout());//get cards
                    cl.show(pagesCards, namePageRef);            	
              }
          });
		 	
        optionBoardButton = new JButton("Options Board");
        //optionBoardButton.setPreferredSize(new Dimension(350, 40));
        optionBoardButton.setFont(new Font("SansSerif", Font.PLAIN, 25));
        optionBoardButton.setBounds(30, 40, 250, 40);
        optionBoardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	  CardLayout cl = (CardLayout) (optionBoardCards.getLayout());//get cards
                  cl.show(optionBoardCards, optionsBoredButtonRef);          	
            }
        });

        optionBBack = new JButton("Back");
        optionBBack.setPreferredSize(new Dimension(150, 40));
        optionBBack.setFont(new Font("SansSerif", Font.PLAIN, 25));
        optionBBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	  CardLayout cl = (CardLayout) (optionBoardCards.getLayout());//get cards
                  cl.show(optionBoardCards, optionsBoredRef);           	
            }
        });
        
        evenOddButton = new JButton("Even Or Odd");
        evenOddButton.setPreferredSize(new Dimension(220, 40));
        evenOddButton.setFont(new Font("SansSerif", Font.PLAIN, 25));
        evenOddButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	evenOddM = engine.getEvenOdd();		
            	update();
            }
        });
        
        rangeButton = new JButton("Range");
        rangeButton.setPreferredSize(new Dimension(180, 40));
        rangeButton.setFont(new Font("SansSerif", Font.PLAIN, 25));
        rangeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            	rangeM = engine.getRange();
            	update();
            }
        });
        
        throwDiceB = new JButton("throw the dices");
        throwDiceB.setPreferredSize(new Dimension(250, 40));
        throwDiceB.setFont(new Font("SansSerif", Font.PLAIN, 25));
        throwDiceB.setBounds( 50, 180, 220, 40 );
    	throwDiceB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                	engine.throwDices();	
                	optionBoardButton.setVisible(true);
                	playerTroTurn = false;
                	lastValue.setText("");
                	lastScoreP1.setText("");
                  	lastScoreP2.setText("");
                	if(currentFirstPlayer == player1)
                		currentPlayer = 2;
                	else
                		currentPlayer = 1;
                	update();

                	dieImgJLabel3.setVisible(false);
                	diceImg1.setVisible(false);
                    diceImg1 = createImageLabel("red-dice-hi.png",100, 100,  450, 10, 100, 100);
                    gameplayPage.add(diceImg1);
                    
                    diceImg2.setVisible(false);
                    diceImg2 = createImageLabel("red-dice-hi.png",100, 100,  560, 10, 100, 100);
                    gameplayPage.add(diceImg2);
                    
                    diceImg3.setVisible(false);
                    diceImg3 = createImageLabel("red-dice-hi.png",100, 100,  670, 10, 100, 100);
                    gameplayPage.add(diceImg3);
                    
                    diceImg4.setVisible(false);
                    diceImg4 = createImageLabel("red-dice-hi.png",100, 100,  450, 115, 100, 100);
                    gameplayPage.add(diceImg4);
                    
                    diceImg5.setVisible(false);
                    diceImg5 = createImageLabel("red-dice-hi.png",100, 100,  560, 115,  100, 100);
                    gameplayPage.add(diceImg5);
                    
                    diceImg6.setVisible(false);
                    diceImg6 = createImageLabel("red-dice-hi.png",100, 100,  670, 115,  100, 100);
                    gameplayPage.add(diceImg6);
                    
                    pagesCards.add(gameplayPage,gamePlayPageRef);
                    CardLayout c2 = (CardLayout) (pagesCards.getLayout());//get cards
                    c2.show(pagesCards, gamePlayPageRef);

            	    CardLayout cl = (CardLayout) (currentGameCards.getLayout());//get cards
                    cl.show(currentGameCards, guessingRef);
            	
            }
        });
    	
    	makeGuessB = new JButton("make a guess");
    	makeGuessB.setFont(new Font("SansSerif", Font.PLAIN, 25));
    	makeGuessB.setBounds( 50, 180, 220, 40 );
    	makeGuessB.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                	optionBoardButton.setVisible(false);
                		playerTroTurn = true;
                		lastValue.setText("The total of the last dices was: "+engine.getTotalDices());
                		engine.updateGuessedNum((Integer)comboCount.getSelectedItem());
                		int player2CurrentScore = engine.getScore();
                		int player1CurrentScore = engine.getP2Score();
                		currentSecondPlayer.updateScore(player2CurrentScore);
                		currentFirstPlayer.updateScore(player1CurrentScore);
                	
                		if(challengingMode)
                			currentSecondPlayer.updateScore((-1)*engine.getMachedDicesNum());
                		
                		if(currentSecondPlayer == player1 && !challengingMode)
                		{
	                		lastScoreP1.setText("Player 1 got "+ player2CurrentScore +" points.");
	                      	lastScoreP2.setText("Player 2 got "+ player1CurrentScore +" points.");
                		}
                		else if(currentSecondPlayer == player2 && !challengingMode)
                		{
                			lastScoreP1.setText("Player 1 got "+ player1CurrentScore +" points.");
	                      	lastScoreP2.setText("Player 2 got "+ player2CurrentScore +" points.");
                		}
                		else if(currentSecondPlayer == player1 && challengingMode)
                		{
	                		lastScoreP1.setText("Player 1 got "+ player2CurrentScore +" points, and lost "+ engine.getMachedDicesNum() +" pints.");
	                      	lastScoreP2.setText("Player 2 got "+ player1CurrentScore +" points.");
                		}
                		else if(currentSecondPlayer == player2 && challengingMode)
                		{
                			lastScoreP1.setText("Player 1 got "+ player1CurrentScore +" points.");
	                      	lastScoreP2.setText("Player 2 got "+ player2CurrentScore +" points, and lost "+ engine.getMachedDicesNum() +" pints.");
                		}
                		
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
	                	
						dieImgJLabel3.setVisible(false);
						diceImg1.setVisible(false);
						diceImg1 = createImageLabel("die_face_"+engine.getDice1()+".png",100, 100,  450, 10, 100, 100);
						gameplayPage.add(diceImg1);
						
						diceImg2.setVisible(false);
						diceImg2 = createImageLabel("die_face_"+engine.getDice2()+".png",100, 100,  560, 10, 100, 100);
						gameplayPage.add(diceImg2);
						
						diceImg3.setVisible(false);
						diceImg3 = createImageLabel("die_face_"+engine.getDice3()+".png",100, 100,  670, 10, 100, 100);
						gameplayPage.add(diceImg3);
						
						diceImg4.setVisible(false);
						diceImg4 = createImageLabel("die_face_"+engine.getDice4()+".png",100, 100,  450, 115, 100, 100);
						gameplayPage.add(diceImg4);
						
						diceImg5.setVisible(false);
						diceImg5 = createImageLabel("die_face_"+engine.getDice5()+".png",100, 100,  560, 115, 100, 100);
						gameplayPage.add(diceImg5);
						
						diceImg6.setVisible(false);
						diceImg6 = createImageLabel("die_face_"+engine.getDice6()+".png",100, 100,  670, 115, 100, 100);
						gameplayPage.add(diceImg6);
						
						pagesCards.add(gameplayPage,gamePlayPageRef);
						CardLayout c3 = (CardLayout) (pagesCards.getLayout());//get cards
						c3.show(pagesCards, gamePlayPageRef);
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
        currentGameCards.setBounds(0, 300, 300, 300);
        
        optionBoardCards.add(optionBoredPanel,optionsBoredRef);
        optionBoardCards.add(optionBoredBPanel,optionsBoredButtonRef);   
        optionBoardCards.setBounds(450, 400, 350, 300);

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
