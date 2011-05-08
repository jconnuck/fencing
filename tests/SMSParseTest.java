import java.io.BufferedReader;
import java.io.InputStreamReader;

import mocks.*;

import org.junit.*;
import static org.junit.Assert.*;
import src.clj.final_project.model.*;
import final_project.control.SMSController;
import final_project.control.SMSParser;

public class SMSParseTest {

	private SMSParser _parser;
	private MockSMSController _control;
	private MockDataStore _store;
		
	public SMSParseTest () {
		_control = new MockSMSController();
		_parser = new SMSParser(_store, _control);
	}
	
	@Test
	public void test_parseOutput() {
		_parser.parseOutput("help fencer at strip 2 broke their leg!!!!!", "8132987766");
	
	}
}
