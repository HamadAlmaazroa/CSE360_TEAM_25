/** Description of Player
 *  This class add has the score of each player and his/her name.
 *
 * @authors Salman Alyahya, Latifah Alhulelah, Abdullah Aljarrah, Hamad Almazroa, Rashed Almeqbali
 * @TeamNumber	25
 * @version April 15, 2016.
 */

public class Player {
	
	/** name: The name of player.
	 *  score: The player's score so far. 
	 *  */
	private String name;
	private int score;
	
	/** This is the constructor of the Player class
	 * it initialize name to an empty String and score to 0 at the beginning. 
	 *  */
	public Player()
	{
		name = "";
		score = 0;
	}
	
	/** This is the constructor of the Player class
	 * it initialize name to the name given and score to 0 at the beginning. 
	 * 
	 * @param name		The name of the player
	 *  */
	public Player(String name)
	{
		this.name = name;
		score = 0;
	}
	
	/** This method increment the player's score by the given number
	 * 
	 * @param addedScore		The score to be added the player
	 *  */
	public void updateScore(int addedScore)
	{
		score += addedScore;
	}
	
	/** This method return the player's score .
	 * 
	 * @return score		The player's score
	 *  */
	public int getScore()
	{
		return score;
	}
	
	/** This method set the player's name with the given name
	 * 
	 * @param name		The player's name
	 *  */
	public void setName(String name)
	{
		this.name = name;
	}
	
	/** This method return the player's name .
	 * 
	 * @return name		The player's name
	 *  */
	public String getName()
	{
		return name;
	}
}
