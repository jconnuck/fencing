package final_project.control;

public class TournamentRunner implements Runnable {
	SMSController _smsController;

	public TournamentRunner(SMSController cont){
		_smsController = cont;
	}
	@Override
	public void run() {
		//public void returnResults(int refID, int winnerID, int winnerScore, int loserID, int loserScore) {
	/*	_smsController.returnResults(0, 1, 5, 2, 2);
		System.out.println("running runner");
		_smsController.returnResults(0, 0, 5, 3, 2);
		_smsController.returnResults(0, 1, 5, 0, 2);
		_smsController.returnResults(0, 2, 5, 3, 2);
		_smsController.returnResults(0, 0, 5, 2, 2);
		_smsController.returnResults(0, 1, 5, 3, 2);*/
	}

}
