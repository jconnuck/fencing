package final_project.control;

import final_project.model.CompleteResult;
import final_project.model.DERound;

public class DERoundController {
	private DERound _deRound;

	public void addCompleteResult(CompleteResult result) throws DERound.NoSuchMatchException{
		_deRound.addCompleteResult(result);
	}
}
