import java.util.Scanner;
import org.junit.*;
import static org.junit.Assert.*;

public class ScannerDelimTest {

	private Scanner _s;

	@Test
	public void test_pipeDelim() {
		_s = new Scanner ("19|4412312345|Hi there|2004-01-20 16:06:40|44771234567|0");
		_s.useDelimiter("\\|");

		assertTrue(_s.hasNextInt());
		int nextI = _s.nextInt();
		assertEquals(19, nextI);

		assertTrue(_s.hasNext());
		String next = _s.next();
		assertTrue(next.equals("4412312345"));

		assertTrue(_s.hasNext());
		next = _s.next();
		assertTrue(next.equals("Hi there"));

	}

	public static void main(String args[]) {
		org.junit.runner.JUnitCore.main("ScannerDelimTest");
	}
}
