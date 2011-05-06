package final_project.control;

import java.util.LinkedList;

public class StripController {
	private LinkedList<Integer> _availableStrips;
	private LinkedList<Integer> _occupiedStrips;
	
	public StripController() {
		_availableStrips = new LinkedList<Integer>();
		_occupiedStrips = new LinkedList<Integer>();
	}
	
	public boolean availableStrip(){
		return !_availableStrips.isEmpty();
	}
	
	public Integer checkOutStrip() throws IllegalStateException {
		if(_availableStrips.isEmpty())
			throw new IllegalStateException("No available strips.");
		Integer toCheckOut = _availableStrips.remove(0);
		_occupiedStrips.add(toCheckOut);
		return toCheckOut;
	}
	
	public void returnStrip(Integer toReturn) throws IllegalArgumentException{
		if(_occupiedStrips.remove(toReturn))
			_availableStrips.add(toReturn);
		else
			throw new IllegalArgumentException("That strip is not currently checked out");
	}
}
