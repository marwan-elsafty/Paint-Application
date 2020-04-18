package controller.commander.redo;

import controller.commander.Change;
import controller.commander.ChangeCommand;

public class RedoUpdateShape implements ChangeCommand {

	Change change;

	public RedoUpdateShape(Change change) {
		this.change = change;
	}

	@Override
	public void execute() {
		this.change.redo();
	}
}
