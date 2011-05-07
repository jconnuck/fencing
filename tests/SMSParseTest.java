import java.io.BufferedReader;
import java.io.InputStreamReader;

import mocks.MockDataStore;

import org.junit.*;
import static org.junit.Assert.*;
import src.clj.final_project.model.*;
import final_project.control.SMSController;
import final_project.control.SMSParser;

public class SMSParseTest {

	private SMSController _control;
	private MockDataStore _store;
	
	
	public SMSParseTest () {
		_control = new SMSController();
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void test_subscribeUser() {
		_parser.parseOutput("follow Miranda Steele", "8132987766");
	
	
	}
}
