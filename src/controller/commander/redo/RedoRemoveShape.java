package controller.commander.redo;

import controller.commander.Change;
import controller.commander.ChangeCommand;

public class RedoRemoveShape implements ChangeCommand {

	Change change;

	public RedoRemoveShape(Change change) {
		this.change = change;
	}

	@Override
	public void execute() {
		this.change.redo();
	}
}
