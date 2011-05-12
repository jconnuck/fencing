package final_project.control;

import java.util.LinkedList;
import java.util.List;

public class StripController {
	private boolean _grid;
	private int _gridX;
	private int _gridY;
	private LinkedList<Integer> _availableStrips;
	private LinkedList<Integer> _occupiedStrips;

	public StripController() {
		_availableStrips = new LinkedList<Integer>();
		_occupiedStrips = new LinkedList<Integer>();
	}

	public StripController(int numStrips) {
		_availableStrips = new LinkedList<Integer>();
		_occupiedStrips = new LinkedList<Integer>();
		for(int s = 0; s < numStrips; s++)
			_availableStrips.add(s);
	}

	public void setUpStrips(int x, int y, boolean grid) {
		for(int i = 1; i <= x * y; i++){
			_availableStrips.add(i);
		}
		this._grid = grid;
	}

	public boolean availableStrip(){
		return !_availableStrips.isEmpty();
	}

	public Integer checkOutStrip() throws IllegalStateException {
		if(_availableStrips.isEmpty())
			throw new IllegalStateException("No available strips.");
		Integer toCheckOut = _availableStrips.remove(0);
		_occupiedStrips.add(toCheckOut);
		System.out.println("To check out in checkOutStrip: " + toCheckOut);
		return toCheckOut;
	}

	public void returnStrip(Integer toReturn) throws IllegalArgumentException{
		if(_occupiedStrips.remove(toReturn))
			_availableStrips.add(toReturn);
		else
			throw new IllegalArgumentException("That strip is not currently checked out");
	}

	/**
	 * Returns a List of the free strips that are adjacent to the strip whose number is the argument.
	 * @param toCheck The number of the strip that is to be checked for available neighbors.
	 * @return List<Integer> A list of the of the available strips adjacent to toCheck.
	 * @throws IllegalArgumentException If there is no such strip whose number is toCheck.
	 */
	public List<Integer> getAdjacentFreeStrips(Integer toCheck) throws IllegalArgumentException{
		if(toCheck <= 0 || toCheck > (_gridY * _gridX))
			throw new IllegalArgumentException("No such strip");

		if(!_grid)
			return new LinkedList<Integer>();
		Integer left;
		Integer right;
		Integer up;
		Integer down;
		if(toCheck % _gridX == 1)
			left = -1;
		else
			left = toCheck -1;

		if(toCheck % _gridX == 0)
			right = -1;
		else
			right = toCheck +1;

		if(toCheck % _gridY == 1)
			up = -1;
		else
			up = toCheck - _gridX;

		if(toCheck % _gridY ==0)
			down = -1;
		else down = toCheck + _gridX;

		LinkedList<Integer> toReturn = new LinkedList<Integer>();
		if(left != -1 && _availableStrips.contains(left))
			toReturn.add(left);

		if(right != -1 && _availableStrips.contains(right))
			toReturn.add(right);

		if(up != -1 && _availableStrips.contains(up))
			toReturn.add(up);

		if(down != -1 && _availableStrips.contains(down))
			toReturn.add(down);
		return toReturn;
	}
}
