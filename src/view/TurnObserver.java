package view;

public interface TurnObserver {

	void doNewTurnThings();
	
	void turnPartIsThis(boolean thisTurnPart);
}
