import jvm.final_project.control;

import static org.junit.Assert.*;
import org.junit.*;
import java.awt.Point;

public class calcPoolsTest {
	@Test
	public void testCalcPools() {
		Point p;
		p = calcPools(26, 6);
		AssertEquals(p.x, 1);
		AssertEquals(p.y, 4);

		p = calcPools(28, 5);
		AssertEquals(p.x, 4);
		AssertEquals(p.y, 2);

		p = calcPools(12, 5);
		AssertEquals(p.x, 0);
		AssertEquals(p.y, 3);

		p = calcPools(120, 5);
		AssertEquals(p.x, 24);
		AssertEquals(p.y, 0);

		p = calcPools(124, 5);
		AssertEquals(p.x, 24);
		AssertEquals(p.y, 1);

		p = calcPools(5, 4);
		AssertEquals(p.x, -1);
		AssertEquals(p.y, -1);

		p = calcPools(6, 4);
		AssertEquals(p.x, -1);
		AssertEquals(p.y, -1);

		p = calcPools(10, 6);
		AssertEquals(p.x, -1);
		AssertEquals(p.y, -1);

		p = calcPools(36, 7);
		AssertEquals(p.x, -1);
		AssertEquals(p.y, -1);

		p = calcPools(18, 7);
		AssertEquals(p.x, -1);
		AssertEquals(p.y, -1);

		p = calcPools(11, 7);
		AssertEquals(p.x, -1);
		AssertEquals(p.y, -1);

		try {
			p = calcPools(0, 6);
			fail();
		}
		catch(IllegalArgumentException ex) {}

		try {
			p = calcPools(0, 0);
			fail();
		}
		catch(IllegalArgumentException ex) {}

		try {
			p = calcPools(4, 0);
			fail();
		}
		catch(IllegalArgumentException ex) {}

		try {
			p = calcPools(-1, 0);
			fail();
		}
		catch(IllegalArgumentException ex) {}
		
		try {
			p = calcPools(-5, -5);
			fail();
		}
		catch(IllegalArgumentException ex) {}
		
		try {
			p = calcPools(0, -4);
			fail();
		}
		catch(IllegalArgumentException ex) {}
		
		try {
			p = calcPools(6, 8);
			fail();
		}
		catch(IllegalArgumentException ex) {}

		try {
			p = calcPools(4, 6);
			fail();
		}
		catch(IllegalArgumentException ex) {}
	}


}