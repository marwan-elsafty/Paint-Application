package controller.commander.undo;

import controller.commander.Change;
import controller.commander.ChangeCommand;

public class UndoAddShape implements ChangeCommand {

	Change change;

	public UndoAddShape(Change change) {
		this.change = change;
	}

	@Override
	public void execute() {
		this.change.undo();
	}
}
