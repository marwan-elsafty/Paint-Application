package controller.commander.undo;

import controller.commander.Change;
import controller.commander.ChangeCommand;

public class UndoRemoveShape implements ChangeCommand {

	Change change;

	public UndoRemoveShape(Change change) {
		this.change = change;
	}

	@Override
	public void execute() {
		this.change.undo();
	}
}
