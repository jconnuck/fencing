package final_project.control;

import java.util.*;

import final_project.model.CompleteResult;
import final_project.model.DERound;
import final_project.model.store.IDataStore;
import final_project.model.Result;

public class DERoundController {
	private DERound _deRound;

	public void addCompleteResult(CompleteResult result) throws DERound.NoSuchMatchException{
		_deRound.addCompleteResult(result);
	}

	public Result[] getMatches(){
		return _deRound.getMatches();
	}

	public DERoundController(IDataStore dataStore, StripController stripController, List<Integer> players, double cut){
		_deRound = new DERound(dataStore, stripController, players, cut);
		_deRound.setupRound();
	}
}
