package final_project.tests;

import java.util.Collection;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;

import final_project.model.*;

public class FencerPoolTest extends TestCase {

	private FencerPool p;

	protected void setUp() throws Exception {
		super.setUp();
		p = new FencerPool();
		for(int i = 0; i < 6; i++)
			p.addPlayer(i);
		p.createIncompleteResults();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetSeeds() {
		Collection<FencerSeed> seedings = p.getSeeds();
		assertEquals(6, seedings.size());
	}

	@Test
	public void testGetNextResult() {
		IncompleteResult temp = p.getNextResult();
		assertEquals(0, temp.getPlayer1());
//		System.out.println(p.getIncompleteResults().size());
//		for(IncompleteResult ir : p.getIncompleteResults())
//			System.out.println(ir.getPlayer1() + " , " +  ir.getPlayer2());

		assertEquals(1, temp.getPlayer2());
		assertEquals(3, p.getIncompleteResults().get(1).getPlayer1());
		assertEquals(4, p.getIncompleteResults().get(1).getPlayer2());
		assertEquals(15, p.getIncompleteResults().size());
	}

	@Test
	public void testgetSeeds(){

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(0, 0),
				new PlayerResult(1, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(3, 0),
				new PlayerResult(4, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(1, 0),
				new PlayerResult(2, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(4, 0),
				new PlayerResult(5, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(2, 0),
				new PlayerResult(0, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(5, 0),
				new PlayerResult(3, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(1, 0),
				new PlayerResult(4, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(0, 0),
				new PlayerResult(3, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(4, 0),
				new PlayerResult(2, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(0, 0),
				new PlayerResult(5, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(3, 0),
				new PlayerResult(1, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(2, 5),
				new PlayerResult(5, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(4, 0),
				new PlayerResult(0, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(2, 0),
				new PlayerResult(3, 0)));

		p.addCompletedResult(new CompleteResult(
				new PlayerResult(5, 0),
				new PlayerResult(1, 0)));
		List<FencerSeed> seeding = p.getSeeds();
		assertEquals(p.numPlayers(), seeding.size());
	}

	@Test
	public void testisDone(){
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(0, 0),
				new PlayerResult(1, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(3, 0),
				new PlayerResult(4, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(1, 0),
				new PlayerResult(2, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(4, 0),
				new PlayerResult(5, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(2, 0),
				new PlayerResult(0, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(5, 0),
				new PlayerResult(3, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(1, 0),
				new PlayerResult(4, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(0, 0),
				new PlayerResult(3, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(4, 0),
				new PlayerResult(2, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(0, 0),
				new PlayerResult(5, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(3, 0),
				new PlayerResult(1, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(2, 5),
				new PlayerResult(5, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(4, 0),
				new PlayerResult(0, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(2, 0),
				new PlayerResult(3, 0)));
		assertTrue(!p.isDone());
		p.addCompletedResult(new CompleteResult(
				new PlayerResult(5, 0),
				new PlayerResult(1, 0)));
		assertTrue(p.isDone());
	}

	//also tests createIncompleteResults()
	@Test
	public void testAddCompletedResult() {
		try{
			p.addCompletedResult(new CompleteResult(
									new PlayerResult(0, 0),
									new PlayerResult(0, 0)));
			fail();
		}catch(IllegalArgumentException e){
			assertTrue(true);
		}

		for(int i = 6; i < 10; i++)
			p.addPlayer(i);
		try{
			p.createIncompleteResults();
			fail();
		}catch(IllegalStateException e){
			assertTrue(true);
		}
		try {
			this.setUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(15, p.getIncompleteResults().size());
		p.addCompletedResult(new CompleteResult(new PlayerResult(0, 12), new PlayerResult(1, 12)));
		p.addCompletedResult(new CompleteResult(new PlayerResult(3, 12), new PlayerResult(4, 13)));
		p.addCompletedResult(new CompleteResult(new PlayerResult(1, 12), new PlayerResult(2, 12)));
		assertTrue(true);
	}
}