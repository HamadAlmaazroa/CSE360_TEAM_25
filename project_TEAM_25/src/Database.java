
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


/** Database class that stores all information about a player
 *  This class add has the score of each player and his/her name.
 *
 * @authors Salman Alyahya, Latifah Alhulelah, Abdullah Aljarrah, Hamad Almazroa, Rashed Almeqbali
 * @TeamNumber	25
 * @version April 15, 2016.
 */

public class Database {
	
	/** an empty constructor of the database class
	 * 
	 */
	public Database()
	{
		
	}
	
	/**
	 * recoredScore - record the score in a folder named scoresDatabase.txt
	 * @param winningPlayer - the player object of the won player
	 */
	public void recoredScore(Player winningPlayer)
	{
		try(FileWriter fw = new FileWriter("ScoreDatabase\\scoresDatabase.txt", true);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.println(winningPlayer.getName()+", "+ winningPlayer.getScore());
			} catch (IOException e) {    
			}
	}
	
	/**
	 * getScoreList - getScoreList function to get the list of the score from a file named scoresDatabase.txt
	 * @param winningPlayer - the player object of the won player
	 */
	public List getScoreList()
	{
		 // This reference one line at a time
        String line = null;
        List<String> scoreList = new ArrayList<String>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader("ScoreDatabase\\scoresDatabase.txt");

            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	scoreList.add(line);
            }   

            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		"scoresDatabase.txt" + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + "scoresDatabase.txt" + "'");                  
        }
		return scoreList;
	}
	
	/**
	 * resetScores - resetScores function to delete the list of the score from a file named scoresDatabase.txt
	 */
	public void resetScores()
	{
		try(FileWriter fw = new FileWriter("ScoreDatabase\\scoresDatabase.txt", false);
			    BufferedWriter bw = new BufferedWriter(fw);
			    PrintWriter out = new PrintWriter(bw))
			{
			    out.print("");
			} catch (IOException e) {    
			}
		
    }
}
