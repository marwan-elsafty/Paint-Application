package controller.commander.redo;

import controller.commander.Change;
import controller.commander.ChangeCommand;

public class RedoAddShape implements ChangeCommand {

	Change change;

	public RedoAddShape(Change change) {
		this.change = change;
	}

	@Override
	public void execute() {
		this.change.redo();
	}
}
