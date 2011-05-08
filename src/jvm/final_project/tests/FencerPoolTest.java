package final_project.tests;

import java.util.Collection;

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
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	@Test
	public void testGetSeeds() {
		Collection<FencerSeed> seedings = p.getSeeds();
		assertEquals(6, seedings.size());
	}

	public void testGetNextResult() {
		IncompleteResult temp = p.getNextResult();
		assertEquals(0, temp.getPlayer1());
		assertEquals(1, temp.getPlayer1());
	}

	//also does minor testing of createIncompleteResults()
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
		assertTrue(true);
	}
}