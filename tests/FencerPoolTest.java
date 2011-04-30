package tests;

import final_project.model.*;
import junit.framework.TestCase;

public class FencerPoolTest extends TestCase {

	private FencerPool p;
	
	protected void setUp() throws Exception {
		super.setUp();
		p = new FencerPool();
		for(int i = 0; i < 10; i++)
			p.addPlayer(i);	
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testGetSeeds() {
		
	}

	public void testGetNextResult() {
		
	}

	public void testAddCompletedResult() {
		try{
			p.addCompletedResult(new Result(
									new PlayerResult(0, 0),
									new PlayerResult(0, 0),
									1));
			fail();
		}catch(IllegalArgumentException e){
			
		}
		
	}
}
