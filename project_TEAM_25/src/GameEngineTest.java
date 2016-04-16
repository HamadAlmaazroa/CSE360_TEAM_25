/** Description of GameEngineTest
 *  This class has a test cases to test every aspect of the GameEngine class
 *
 * @authors Salman Alyahya, Latifah Alhulelah, Abdullah Aljarrah, Hamad Almazroa, Rashed Almeqbali
 * @TeamNumber	25
 * @version April 15, 2016.
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class GameEngineTest {

	@Test
	public void testGameCalculator() {
		// testing if the class will be created.
		GameEngine engine =  new GameEngine();
		assertNotNull(engine);
	}

	@Test
	public void testThrowDices() {
		// testing that the total is correct
		GameEngine engine =  new GameEngine();
		engine.throwDices();
		int total = engine.getDice1()+engine.getDice2()+engine.getDice3()+engine.getDice4()+engine.getDice5()+engine.getDice6();
		assertEquals(engine.getTotalDices(),total);
		
		//testing if the values will change to if the total is random
		engine.throwDices();
		int total2 = engine.getDice1()+engine.getDice2()+engine.getDice3()+engine.getDice4()+engine.getDice5()+engine.getDice6();
		assertEquals(engine.getTotalDices(),total2);
		
		//comparing the two totals, however, there is a small probability that both total are the same.
		assertNotEquals(total, total2);	
	}

	@Test
	public void testGetScore() {
		//testing getScore and that the use of evenOdd help will deduct points
		GameEngine engine =  new GameEngine();
		engine.throwDices();
		engine.updateGuessedNum(15);
		int score1 = engine.getScore();
		engine.getEvenOdd();
		int score2 = engine.getScore();
		assertNotEquals(score1, score2);
		
		//testing getScore and that the use of getRange help will deduct points
		GameEngine engine2 =  new GameEngine();
		engine2.throwDices();
		engine2.updateGuessedNum(engine2.getTotalDices());
		int score3 = engine2.getScore();
		engine2.getRange();
		int score4 = engine2.getScore();
		assertNotEquals(score3, score4);	
		
		//testing that if the guessed number is right the score = 15
		GameEngine engine3 =  new GameEngine();
		engine3.throwDices();
		engine3.updateGuessedNum(engine3.getTotalDices());
		assertEquals(15, engine3.getScore());
		
		//testing that if the guessed number is within 5 numbers the score = 4
		GameEngine engine4 =  new GameEngine();
		engine4.throwDices();
		engine4.updateGuessedNum(engine4.getTotalDices()+5);
		assertEquals(4, engine4.getScore());
		
		//testing that if the guessed number is within 10 numbers the score = 2
		GameEngine engine5 =  new GameEngine();
		engine5.throwDices();
		engine5.updateGuessedNum(engine5.getTotalDices()+10);
		assertEquals(2, engine5.getScore());
		
		//testing that if the guessed number is more than 10 numbers away the score = 0
		GameEngine engine6 =  new GameEngine();
		engine6.throwDices();
		engine6.updateGuessedNum(engine6.getTotalDices()+11);
		assertEquals(0, engine6.getScore());
		
		//testing that if the guessed number is within 5 numbers the score = 4, and if getEvenOdd is used it will be half that
		GameEngine engine7 =  new GameEngine();
		engine7.throwDices();
		engine7.updateGuessedNum(engine7.getTotalDices()+5);
		int score5 = engine7.getScore();
		assertEquals(4, score5);
		engine7.getEvenOdd();
		int score6 = engine7.getScore();
		assertEquals(2, score6);
		assertNotEquals(score5, score6);
	}

	@Test
	public void testGetP2Score() {
		//testing that if the guessed number is more than 10 numbers away the score = 0
		// and the other player's score will be 3
		GameEngine engine =  new GameEngine();
		engine.throwDices();
		engine.updateGuessedNum(engine.getTotalDices()+11);
		assertEquals(0, engine.getScore());
		assertEquals(3, engine.getP2Score());
	}


}
