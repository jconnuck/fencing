

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.junit.*;
import static org.junit.Assert.*;

import final_project.control.SMSParser;

public class SMSParseTest {

	private SMSParser _parser;
	
	@Before
	public void setUp() {
		_parser = new SMSParser(null, null);
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test_subscribeUser() {
		_parser.parseOutput("follow Miranda Steele", "8132987766");
	
	
	}
}
