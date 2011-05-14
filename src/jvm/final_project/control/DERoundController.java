package final_project.control;

import java.util.*;

import javax.swing.JPanel;

import final_project.model.CompleteResult;
import final_project.model.DERound;
import final_project.model.store.IDataStore;
import final_project.model.Result;

public class DERoundController {
	private DERound _deRound;
	private TournamentController _tournamentController;
    private Collection<DERoundObserver> observers;

	public void addCompleteResult(CompleteResult result) throws DERound.NoSuchMatchException{
		_deRound.addCompleteResult(result);
        for (DERoundObserver obs : observers) {
            obs.bracketUpdated();
        }
	}

	public Result[] getMatches(){
		return _deRound.getMatches();
	}

	public DERoundController(IDataStore dataStore, StripController stripController, List<Integer> players, double cut, TournamentController tc){
		_tournamentController = tc;
		_deRound = new DERound(dataStore, stripController, players, cut, _tournamentController);
		_deRound.setupRound();
        observers = new LinkedList<DERoundObserver>();
	}

    public void addDEObserver(DERoundObserver obs) {
        observers.add(obs);
    }

    public void removeDEObserver(DERoundObserver obs) {
        observers.remove(obs);
    }
}
