package final_project.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import final_project.tests.mocks.*;

import org.junit.*;
import static org.junit.Assert.*;
import final_project.model.*;
import final_project.control.*;
import final_project.control.*;

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
