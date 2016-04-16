/** Description of PlayerTest
 *  This class has a test cases to test every aspect of the Player class
 *
 * @authors Salman Alyahya, Latifah Alhulelah, Abdullah Aljarrah, Hamad Almazroa, Rashed Almeqbali
 * @TeamNumber	25
 * @version April 15, 2016.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testPlayer() 
	{
		// testing if the class will be created, and the player name will not be initialized. 
		Player player = new Player();
		assertNotNull(player);
		assertEquals("",player.getName());
	}

	@Test
	public void testPlayerString() 
	{
		// testing if the class will be created, and the player name will be initialized. 
		Player player = new Player("Alex");
		assertNotNull(player);
		assertEquals("Alex",player.getName());
	}

	@Test
	public void testUpdateScore() 
	{
		// testing if the core will be updated and added
		Player player = new Player();
		player.updateScore(2);
		assertEquals(2,player.getScore());
		player.updateScore(1);
		player.updateScore(2);
		player.updateScore(4);
		assertEquals(9,player.getScore());
	}

	@Test
	public void testGetScore() 
	{
		//testing the getScore function
		Player player = new Player();
		player.updateScore(5);
		assertEquals(5,player.getScore());
	}

	@Test
	public void testSetName() 
	{
		// testing setName with both constructors.
		Player player = new Player();
		assertEquals("",player.getName());
		player.setName("Max");
		assertEquals("Max",player.getName());
		Player player2 = new Player("Sam");
		player2.setName("Corey");
		assertEquals("Corey",player2.getName());
	}

	@Test
	public void testGetName() 
	{
		// testing getName function
		Player player = new Player();
		player.setName("Max");
		assertEquals("Max",player.getName());
	}

}
