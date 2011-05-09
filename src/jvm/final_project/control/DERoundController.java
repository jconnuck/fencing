package final_project.control;

import final_project.model.CompleteResult;
import final_project.model.DERound;
import final_project.model.Result;

public class DERoundController {
	private DERound _deRound;

	public void addCompleteResult(CompleteResult result) throws DERound.NoSuchMatchException{
		_deRound.addCompleteResult(result);
	}

	public Result[] getMatches(){
		return _deRound.getMatches();
	}
}
