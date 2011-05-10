package final_project.model;

import java.util.*;

import final_project.model.store.*;

import final_project.control.StripController;

public class DERound implements IRound {
    private List<Integer> _seeding;  /*!< The seeding of fencers based on pool results. */
    private double _cut;         /*!< The percentage of the fencers to be cut before the round starts */
    private Result[] _matches;  /*!< Array of all bouts in this DE round. */
    private int _bracketSize;    /*!< The bracket size (How many slots there are in the first round of DEs) (must be a power of 2) */
    private static int POINTS_TO_WIN;
    private StripController _stripController;
    private Map<IncompleteResult, Integer> _stripsInUse; //Correlates currently fencing bouts with the strips that they are using
    private Map<IncompleteResult, Integer> _refsInUse; //Correlates currently fencing bouts with the referees that they are using
    private IDataStore _dataStore;

    //WHY DO WE NEED THIS CONSTRUCTOR?
    /*public DERound(IDataStore store, StripController sc) {
        this(store,sc,new ArrayList<Integer>());
    }*/

    public DERound(IDataStore store, StripController sc, List<Integer> seeding, double cut) {
    	this._cut = cut;
        _dataStore = store;
    	_seeding = seeding;
        POINTS_TO_WIN = 15;
        _stripController = sc;
        _stripsInUse = new HashMap<IncompleteResult, Integer>();
        _refsInUse = new HashMap<IncompleteResult, Integer>();
    }

    public List<Integer> getSeeding() {
        return _seeding;
    }
    
    public void setDataStore(IDataStore ds) {
    	_dataStore = ds;
    }
    
    public void setStripController(StripController sc) {
    	_stripController = sc;
    }
    
    public void setSeeding(ArrayList<Integer> seeding) {
        _seeding = seeding;
    }

    public Result[] getMatches() {
    	return _matches;
    }

    public void setPointsToWin(int newPTW) {
        POINTS_TO_WIN = newPTW;
    }

    /**
     * Sets up the DE round by cutting the bottom _cut percentage of the competitors and filling the bracket with the remaining competitors.
     */
    public void setupRound() {
        makeCut();
        calcBracketSize();
        populateBracket();
        startFirstMatch();
    }

    /**
     * Cuts the bottom _cut percentage of fencers from _seeding.
     */
    public void makeCut() {
    	int newEnd = (int) Math.ceil(_seeding.size() * (1.0 - (_cut/100.0)));
        if(newEnd == _seeding.size()) {
        	return;
        }
        _seeding = _seeding.subList(0, newEnd);
    }

    /**
     * Calculates the proper bracket size using the number of fencers in the round.
     */
    public void calcBracketSize() throws IllegalArgumentException{
        if(_seeding.size() < 2)
            throw new IllegalArgumentException("Attempted to build a bracket for fewer than 2 competitors.");
        _bracketSize = (int) Math.pow(2,Math.ceil(Math.log(_seeding.size())/Math.log(2)));
        _matches = new Result[_bracketSize -1];
    }

    /**
     * Populates the bracket with all of the seed number for the first round.
     * @throws IllegalStateException
     */
    public void populateBracket() throws IllegalStateException{
        if(_matches == null){
            throw new IllegalStateException("_matches not yet instantiated.");
        }
        populateBracketHelper(0, 2, 1, false);
        Arrays.fill(_matches, 0, computeRoundHead(_bracketSize /2), null);
        switchSeedsForCompetitors();
    }

    /**
     * Recursively populates _matches with IncompleteResults with players whose IDs are their position(1-indexed) in _seeding.
     * @param index The index in _matches whose IncompleteResult is to be created.
     * @param currentBracketSize The number of fencers in the current round(round that index is in).
     * @param currentSeed The seed of the higher seeded competitor in the IncompleteResult to be created.
     * @param bottomSwap True if the bottom and top children must be swapped (to ensure that on the bottom half of the bracket the higher seeds are lower on the table).
     */
    private void populateBracketHelper(int index, int currentBracketSize, int currentSeed, boolean bottomSwap){
        if(index < 0)
            throw new IllegalArgumentException("Index cannot be negative.");
        if(index >= _matches.length)
            return;
        _matches[index] = new IncompleteResult(currentSeed,
                                               currentBracketSize - currentSeed + 1,
                                               POINTS_TO_WIN);
        if(bottomSwap) {
        	populateBracketHelper(2 * index + 1,
                    			  currentBracketSize * 2,
                    			  currentBracketSize - currentSeed + 1,
                    			  false);
        	populateBracketHelper(2 * index + 2,
                    			  currentBracketSize * 2,
                    			  currentSeed,
                    			  true);
        }
        else {
        	populateBracketHelper(2 * index + 1,
                              	  currentBracketSize * 2,
                              	  currentSeed,
                              	  false);
        	populateBracketHelper(2 * index + 2,
                              	  currentBracketSize * 2,
                              	  currentBracketSize - currentSeed + 1,
                              	  true);
        }
        
    }

    /**
     * Helper function for populateBracket() that takes the values that represent the seeding of the competitors
     * in the current IncompleteResults and replaces them with the int id of the actual competitor of that seed.
     */
    private void switchSeedsForCompetitors(){
    	IncompleteResult temp;
    	IncompleteResult next;
        for(int i = _matches.length - (_bracketSize /2); i < _matches.length; i++){
            temp = (IncompleteResult) _matches[i];
            next = (IncompleteResult) _matches[getNextMatchIndex(i)];
            if(temp.getPlayer1() > _seeding.size()) { // If player2 got a bye
                _matches[i] = new CompleteResult(new PlayerResult(_seeding.get(temp.getPlayer2() -1), 0),  // Sets player2 to the winner of the match
                                                 new PlayerResult(-1, 0));
                if(next == null) // If the next round has not yet been initialized
                	_matches[getNextMatchIndex(i)] = new IncompleteResult(_seeding.get(temp.getPlayer2() -1),
                														  -1,
                														  POINTS_TO_WIN);
                else if(next.getPlayer2() == -1) // If the next bout has already been initialized because opponent also got a bye
                	_matches[getNextMatchIndex(i)] = new IncompleteResult(next.getPlayer1(),
                														  _seeding.get(temp.getPlayer2() -1),
                														  POINTS_TO_WIN);
                else if(next.getPlayer1() == -1)  // This should  never happen because of the way this function works, but better safe than sorry.
                	_matches[getNextMatchIndex(i)] = new IncompleteResult(_seeding.get(temp.getPlayer2() -1),
                														  next.getPlayer2(),
                														  POINTS_TO_WIN);
            }
            else if(temp.getPlayer2() > _seeding.size()) { // If player1 got a bye
                _matches[i] = new CompleteResult(new PlayerResult(_seeding.get(temp.getPlayer1() -1), 0),  // Sets player1 to the winner of the match
                                                 new PlayerResult(-1, 0));
                if(next == null)  // If the next bout has not yet been initialized
                	_matches[getNextMatchIndex(i)] = new IncompleteResult(_seeding.get(temp.getPlayer1() -1),
                														  -1,
                														  POINTS_TO_WIN);
                else if(next.getPlayer2() == -1) // If the next bout has already been initialized because opponent also got a bye
                	_matches[getNextMatchIndex(i)] = new IncompleteResult(next.getPlayer1(),
							  											  _seeding.get(temp.getPlayer1() -1),
							  											  POINTS_TO_WIN);
                else if(next.getPlayer1() == -1)  // This should  never happen because of the way this function works, but better safe than sorry.
                	_matches[getNextMatchIndex(i)] = new IncompleteResult(_seeding.get(temp.getPlayer1() -1),
							  											  next.getPlayer2(),
							  											  POINTS_TO_WIN);
            }
            else {
                _matches[i] = new IncompleteResult(_seeding.get(temp.getPlayer1() -1),
                                                   _seeding.get(temp.getPlayer2() -1),
                                                   POINTS_TO_WIN);
            }
        }
    }

    /**
     * @param index The index in _matches of the current match.
     * @return int the index of the match that the winner of the index match will continue on to.
     */
    private int getNextMatchIndex(int index) {
        return (index+(index%2)-2)/2;
    }

    public void setCut(double newCut){
        _cut = newCut;
    }

    public double getCut() {
        return _cut;
    }

    private void startFirstMatch() {
    	if(_stripController.availableStrip()) {
    		int strip = _stripController.checkOutStrip();
    		int ref = _dataStore.getNextReferee();
            IncompleteResult firstMatch = (IncompleteResult) _matches[computeRoundHead(_bracketSize /2)];
    		_stripsInUse.put(firstMatch, strip);
            _refsInUse.put(firstMatch, ref);
            // Notify fencers and ref
            System.out.println("YO:" + firstMatch.getPlayer1() + " and " + firstMatch.getPlayer2() + " are fencing on strip: " + strip + " with referee: " + ref);
    	}
    	// Not exactly sure what to do if there is no strip (or referee??) to use for the first match.
    }
    
    /**
     * Returns the next match to be played if there is one.  Returns null if there is not.
     * @return IncompleteResult that represents the next match to be fenced.
     */
    private IncompleteResult getNextMatch() {
    	int currentRoundSize = _bracketSize /2;   // currentRoundSize is the number of matches in the current round.
    	int currentRoundHead = computeRoundHead(currentRoundSize);
    	int i = currentRoundHead;
    	while(true){
    		if(_matches[i] == null){
    			return null;
    		}
    		else if(_matches[i] instanceof IncompleteResult  &&  // If the current match is an IncompleteResult and
    				_stripsInUse.get(_matches[i]) == null) {     // the match is not currently being fenced.
    			if(_matches[i].getPlayer1() == -1 ||      // If either of the players is not yet known.
    			   _matches[i].getPlayer2() == -1) {
    				return null;
    			}
    			else
    				return (IncompleteResult) _matches[i];
    		}
    		if(currentRoundSize == 1)  // If method got to final.  This should only happen if all matches in the round have been completed.
    			return null;
    		if(i >= (currentRoundHead + currentRoundSize)) {  // If i is at the end of the round
    			currentRoundSize /= 2;
    			currentRoundHead = computeRoundHead(currentRoundSize);
    			i = currentRoundHead;
    		}
    		else
    			i++;
    	}
    }

    /**
     * Computes the index in _matches that is the first element in _matches for the round in the DE that has roundSize
     * Results in it.
     * @param roundSize The number of Results in the round that the method finds the head of.
     * @return int the index of the head of the round that has roundSize Results in it in _matches.
     */
    private int computeRoundHead(int roundSize) {
    	int curRoundSize = 1;
    	int toReturn =0;
    	while(curRoundSize < roundSize) {
    		toReturn += curRoundSize;
    		curRoundSize *= 2;
    	}
    	return toReturn;
    }

    /**
     * Adds its argument as a completed match of the DE
     * @param newResult The completed result to be added.
     * @throws NoSuchMatchException If there is no such bout in the DE bracket.
     */
    public void addCompleteResult(CompleteResult newResult) throws NoSuchMatchException {
        Result tempResult;
        for(int i = _matches.length -1; i >= 0; i--) {
            tempResult = _matches[i];
            if(tempResult != null) {
	            if(tempResult.getPlayer1() == newResult.getPlayer1() &&
	               tempResult.getPlayer2() == newResult.getPlayer2()
	               ||
	               tempResult.getPlayer1() == newResult.getPlayer2() &&
	               tempResult.getPlayer2() == newResult.getPlayer1()) {
	            	if(tempResult instanceof CompleteResult) {
	                    throw new NoSuchMatchException("This bout has already been completed");
	                }
	                _matches[i] = newResult;
	                if(i == 0) {
	                	// maybe announce winner of the event to all spectators and fencers
	                	return;
	                }
	                IncompleteResult nextResult = (IncompleteResult) _matches[getNextMatchIndex(i)];
	                if(nextResult == null) {
	                    nextResult = new IncompleteResult(newResult.getWinner(), -1, POINTS_TO_WIN);
	                }
	                else if(nextResult.getPlayer2() == -1 ) {
	                    nextResult.setPlayer2(newResult.getWinner());
	                }
	                else if(nextResult.getPlayer1() == -1) {
	                    // Should never actually happen because only player2 should be set to -1
	                    nextResult.setPlayer1(newResult.getWinner());
	                }
                    IncompleteResult justFinished = (IncompleteResult) tempResult;
                    _stripController.returnStrip(_stripsInUse.get(justFinished));
                    returnRef(_refsInUse.get(justFinished));
                    _refsInUse.remove(justFinished);
                    _stripsInUse.remove(justFinished);
		            boolean hasNextBout = true;
		            while(hasNextBout){
		            	hasNextBout = advanceRound((IncompleteResult) tempResult); // safe cast because of check above that throws exception
		            }
	            }

            }
        }
        throw new NoSuchMatchException("No match was found that corresponded to the CompleteResult you attempted to add");
    }

    private void returnRef(int id) {
        final int fid = id;
        _dataStore.runTransaction(new Runnable(){
                public void run(){
                    _dataStore.putData(_dataStore.getReferee(fid).setReffing(false));
                }
            });
    }

    private boolean advanceRound(IncompleteResult justFinished) {
        IncompleteResult nextMatch = getNextMatch();
        if(nextMatch == null) {
            return false;
        }

        int ref = _dataStore.getNextReferee();

        if (ref != -1 && _stripController.availableStrip()) {
            int strip = _stripController.checkOutStrip();
            _stripsInUse.put(nextMatch, strip);
            _refsInUse.put(nextMatch, ref);
            // Send notification to referee and fencers to start the match
            return true;
        } else {
            if (ref!=-1)
                returnRef(ref);
            return false;
        }
    }

    //TODO: why are we getting this warning?
    public class NoSuchMatchException extends Exception {
        public NoSuchMatchException(String message) {
            super(message);
        }
    }
}