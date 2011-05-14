package final_project.tests;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import final_project.tests.mocks.*;

import org.junit.*;
import static org.junit.Assert.*;
import final_project.model.*;
import final_project.model.store.IDataStore;
import final_project.model.store.DataStore;
import final_project.control.*;
import final_project.control.*;

public class SMSParseTest {

	private SMSParser _parser;
	private MockSMSController _control;
	private IDataStore store;

	public SMSParseTest () {
		_control = new MockSMSController();
		
        store = new DataStore();
        store.runTransaction(new Runnable() {
                public void run() {
                    store.putData(store.createPlayer("1234567891", "Jon", "Leavitt",
                                                     "","Fencer",1));
                    store.putData(store.createPlayer("1234561291", "William", "Zimrin",
                                                     "","Fencer",1));
                    store.putData(store.createPlayer("1235467892", "Josh", "Grill",
                                                     "","Fencer",1));
                    store.putData(store.createPlayer("5432167893", "John", "Connuck",
                                                     "","Fencer",1));
                    store.putData(store.createSpectator("8132987766", "Miranda", "Steele",
                                                        "","Spectator"));
                    for(int x = 0; x < 10; x++)
                    	store.putData(store.createPlayer("1231234123", "random", "player" + x , "", "Fencer", 1));
                    for(int refNum = 0; refNum < 1; refNum++)
                    	store.putData(store.createReferee("3155690308", "ref", ((Integer) refNum).toString(), null, "Referee").setReffing(false));

                }
            });
		
		//_parser = new SMSParser(store, _control);
	}

	@Test
	public void test_parseOutput() {
		//_parser.parseOutput("help fencer at strip 2 broke their leg!!!!!", "8132987766");
		_parser.parseOutput("result 4 beat 0 5 to 4", "3155690308");

	}
}
