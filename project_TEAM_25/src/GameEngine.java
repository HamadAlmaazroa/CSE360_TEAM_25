/** GameEngine class that is used to control the flow of the game
 *  This class add has the score of each player and his/her name.
 *
 * @authors Salman Alyahya, Latifah Alhulelah, Abdullah Aljarrah, Hamad Almazroa, Rashed Almeqbali
 * @TeamNumber	25
 * @version April 15, 2016.
 */

public class GameEngine {

	private int dice1, dice2, dice3, dice4, dice5, dice6, guessedNum, guessRange, totalDices, score, otherScoreP2;
	private boolean rangeUsed, evenOddUsed;
	private String winner = "";

	/** This is the constructor of the GameEngine class
	 * it initializes:  
	 * 	guessRange, score, guessedNum, otherScoreP2 and totalDices to zero
	 * rangeUsed and evenOddUsed to false
	 *  */
	
	public GameEngine()
	{
		guessRange = 0;
		score = 0;
		guessedNum = 0;
		otherScoreP2 = 0;
		totalDices = 0;
		rangeUsed = false;
		evenOddUsed = false;
	}
	
	/**
	 * throughDices - that generate a random number ( 6>= number <=36)
	 * 
	 */
	public void throwDices()
	{
		 dice1 = (int)(6*Math.random()+1);
		 dice2 = (int)(6*Math.random()+1);
		 dice3 = (int)(6*Math.random()+1);
		 dice4 = (int)(6*Math.random()+1);
		 dice5 = (int)(6*Math.random()+1);
		 dice6 = (int)(6*Math.random()+1);
		totalDices = dice1 + dice2 + dice3 + dice4 + dice5 + dice6;
	}

	/**
	 * getDice1 - that is used to return dice 1
	 * @return dice1
	 * 
	 */
	public int getDice1()
	{
		return dice1;
	}
	
	/**
	 * getDice2 - that is used to return dice 2
	 * @return dice2
	 * 
	 */
	public int getDice2()
	{
		return dice2;
	}
	/**
	 * getDice3 - that is used to return dice 3
	 * @return dice3
	 * 
	 */
	public int getDice3()
	{
		return dice3;
	}
	
	/**
	 * getDice4 - that is used to return dice 4
	 * @return dice4
	 * 
	 */
	public int getDice4()
	{
		return dice4;
	}
	
	/**
	 * getDice5 - that is used to return dice 5
	 * @return dice5
	 * 
	 */
	public int getDice5()
	{
		return dice5;
	}
	
	/**
	 * getDice6 - that is used to return dice 6
	 * @return dice6
	 * 
	 */
	public int getDice6()
	{
		return dice6;
	}
	
	/**
	 * getTotalDices - return the summation of all dices
	 * @return totalDices
	 */
	public int getTotalDices()
	{
		return totalDices;
	}
	
	/**
	 * updateRangeUsed - to indicate that a range option is used
	 * 
	 */
	private void updateRangeUsed()
	{
		rangeUsed = true;;
	}
	
	/**
	 * getRange - to generate a range if the range option is used
	 * 
	 */
	public String getRange()
	{
		updateRangeUsed();
		int rangeL = (int)(Math.random() * 6);
		int rangeR = (int)(Math.random() * 6);
		return "It ranges from "+ ((totalDices-rangeL)-1) +" to "+ ((totalDices+rangeR)+1)+".";
	}
	
	/**
	 * updateEvenOddUsed - to indicate that a Even and Odd option is used
	 * 
	 */
	private void updateEvenOddUsed()
	{
		evenOddUsed = true;;
	}
	
	/**
	 * getEvenOdd - to tell the player wether the number is even or odd
	 * 
	 */
	public String getEvenOdd()
	{
		updateEvenOddUsed();
		String help = "The number is even.";
		if(totalDices % 2 != 0)
			help = "The number is odd.";
		return help;
	}
	
	
	/**
	 * getScore - is used to call scoreCalculation function and return the result
	 * @return score
	 */
	public int getScore()
	{
		scoreCalculation();
		return score;
	}
	
	/**
	 * getP2Score - that calls resetVaruables function and return the score
	 * @return returnedScore
	 */
	public int getP2Score()
	{
		int returnedScore = otherScoreP2;
		resetVaruables();
		return returnedScore;
	}
	
	/**
	 * updateGuessedNum - is used to update the guessed Number

	 */
	public void updateGuessedNum(int number)
	{
		guessedNum = number;
	}
	
	/**
	 * resetVaruables - used to reset all variables to their initials
	 * 
	 */
	private void resetVaruables()
	{
		guessRange = 0;
		score = 0;
		guessedNum = 0;
		otherScoreP2 = 0;
		totalDices = 0;
		rangeUsed = false;
		evenOddUsed = false;
	}
	
	/**
	 * scoreCalculation - used to score all calculation 
	 */
	private void scoreCalculation()
	{		
		guessRange = Math.abs(guessedNum - totalDices); 
		
		if(guessRange >= 0 && guessRange <= 10)
		{
			if(rangeUsed)
			{
				if(guessRange == 0)
					score = 5;
			}
			else
			{
				if(guessRange == 0)
					score = 15;
				else if(guessRange >= 1 && guessRange <= 5)
					score = 4;
				else if(guessRange >= 6 && guessRange <= 10)
					score = 2;
			}
			if(evenOddUsed)
				score = score / 2;
		}
		else
		{
			score = 0;
			otherScoreP2 = 3;
		}
	}

	/**
	 * gameEnded - that indicates if the game is ended(one player won)
	 * @param player1
	 * @param player2
	 * @return gameEnded
	 */
	public boolean gameEnded(Player player1, Player player2)
	{
		boolean gameEnded = false;
		Database database = new Database();

		if(player1.getScore() >= 30)
		{
			gameEnded = true;
			winner = "Player 1 WON!";
			database.recoredScore(player1);
		}
		else if(player2.getScore() >= 30)
		{
			gameEnded = true;
			winner = "Player 2 WON!";
			database.recoredScore(player2);
		}
		else if(Math.abs(player1.getScore()-player2.getScore()) >= 20)
		{
			gameEnded = true;
			if(player1.getScore()>player2.getScore())
			{
				winner = "Player 1 WON!";
				database.recoredScore(player1);
			}
			else
			{
				winner = "Player 2 WON!";
				database.recoredScore(player2);
			}
		}
		
		return gameEnded;
	}
	
	/**
	 * getWinner - returns a the won player 
	 * @return winner
	 */
	public String getWinner()
	{
		return winner;
	}

	/**
	 * getMachedDicesNum - returns the number of matched dices 
	 * @return winner
	 */
	public int getMachedDicesNum()
	{
		int machedDices = 0;
		int currentCount = 0;
		int diceArray[] = {dice1,dice2,dice3,dice4,dice5,dice6};
		
		for(int dice = 1; dice <= 6; dice++)
		{
			for(int diceInner = 1; diceInner <= 6; diceInner++)
			{
				if(diceArray[dice-1] == diceArray[diceInner-1])
					currentCount++;
			}
			if(currentCount > machedDices)
				machedDices = currentCount;
			currentCount = 0;
		}
		
		return machedDices;
	}
}
