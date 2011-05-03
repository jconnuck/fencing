package tests;

import final_project.model.*;
import junit.framework.TestCase;
import org.junit.*;
import static org.junit.Assert.*;
import com.sun.source.tree.AssertTree;
import java.util.Collection;

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
		assertTrue(seedings.size() > 0);

	}

	public void testGetNextResult() {
		IncompleteResult temp = p.getNextResult();
		assertEquals(0, temp.getPlayer1());
		assertEquals(1, temp.getPlayer1());
	}

	//also does minor testing of createIncompleteResults() from FencerPool
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
		this.setUp();
		assertEquals(15, p.getIncompleteResults().size());
		p.addCompletedResult(new CompleteResult(new PlayerResult(0, 12), new PlayerResult(1, 12)));
		assertTrue(true);
	}
}