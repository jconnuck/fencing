import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import final_project.model.*;

/**
 * @author Josh
 *
 */
public class DERoundTest {

	DERound round1;
	DERound round2;
	DERound round3;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		ArrayList<Integer> seeding1 = new ArrayList<Integer>();
		seeding1.add(80);
		seeding1.add(70);
		seeding1.add(50);
		seeding1.add(90);
		seeding1.add(40);
		seeding1.add(39);
		seeding1.add(60);
		seeding1.add(20);		
		round1 = new DERound(seeding1);
		
		ArrayList<Integer> seeding2 = new ArrayList<Integer>();
		seeding2.add(10);
		seeding2.add(15);
		seeding2.add(17);
		seeding2.add(42);
		seeding2.add(73);
		round2 = new DERound(seeding2);
		
		ArrayList<Integer> seeding3 = new ArrayList<Integer>(seeding1);
		seeding3.add(29);
		round3 = new DERound(seeding3);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		ArrayList<Integer> seeding1 = new ArrayList<Integer>();
		seeding1.add(80);
		seeding1.add(70);
		seeding1.add(50);
		seeding1.add(90);
		seeding1.add(40);
		seeding1.add(39);
		seeding1.add(60);
		seeding1.add(20);		
		round1 = new DERound(seeding1);
		
		ArrayList<Integer> seeding2 = new ArrayList<Integer>();
		seeding2.add(10);
		seeding2.add(15);
		seeding2.add(17);
		seeding2.add(42);
		seeding2.add(73);
		round2 = new DERound(seeding2);
		
		ArrayList<Integer> seeding3 = new ArrayList<Integer>(seeding1);
		seeding3.add(29);
		round3 = new DERound(seeding3);
	}

	/**
	 * Test method for {@link final_project.model.DERound#setupRound()}.
	 */
	@Test
	public void testSetupRound() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link final_project.model.DERound#makeCut()}.
	 */
	@Test
	public void testMakeCut() {
		round1.setCut(0);
		round1.makeCut();
		assertEquals(8, round1.getSeeding());
		
		round1.setCut(15);
		round1.makeCut();
		assertEquals(7, round1.getSeeding());
		
		round1.setCut(20);
		round1.makeCut();
		assertEquals(7, round1.getSeeding());
		
		round1.setCut(25);
		round1.makeCut();
		assertEquals(6, round1.getSeeding());
		
		round1.setCut(30);
		round1.makeCut();
		assertEquals(6, round1.getSeeding());
		
		round2.setCut(15);
		round2.makeCut();
		assertEquals(5, round2.getSeeding());
		
		round2.setCut(20);
		round2.makeCut();
		assertEquals(4, round2.getSeeding());
		
		round2.setCut(25);
		round2.makeCut();
		assertEquals(4, round2.getSeeding());
		
		round2.setCut(30);
		round2.makeCut();
		assertEquals(4, round2.getSeeding());
		
		round3.setCut(15);
		round3.makeCut();
		assertEquals(8, round3.getSeeding());
		
		round3.setCut(20);
		round3.makeCut();
		assertEquals(7, round3.getSeeding());
		
		round3.setCut(25);
		round3.makeCut();
		assertEquals(7, round3.getSeeding());
		
		round3.setCut(30);
		round3.makeCut();
		assertEquals(6, round3.getSeeding());
		
	}

	/**
	 * Test method for {@link final_project.model.DERound#populateBracket()}.
	 */
	@Test
	public void testPopulateBracket() {
		round1.populateBracket();
		
	}

	/**
	 * Test method for {@link final_project.model.DERound#switchSeedsForCompetitors()}.
	 */
	@Test
	public void testSwitchSeedsForCompetitors() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link final_project.model.DERound#getCurrentBracketSize()}.
	 */
	@Test
	public void testGetCurrentBracketSize() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link final_project.model.DERound#getNextMatch()}.
	 */
	@Test
	public void testGetNextMatch() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link final_project.model.DERound#addCompleteResult(final_project.model.CompleteResult)}.
	 */
	@Test
	public void testAddCompleteResult() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link final_project.model.DERound#getTopNPlayers(int)}.
	 */
	@Test
	public void testGetTopNPlayers() {
		fail("Not yet implemented");
	}

}
