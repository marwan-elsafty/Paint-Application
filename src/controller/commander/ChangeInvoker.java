package controller.commander;

import java.awt.Toolkit;
import java.util.ArrayList;

public class ChangeInvoker {

	private static int changeCount = 0;
	private static ArrayList<ChangeMode> changeModeHistory = new ArrayList<ChangeMode>();

	ChangeCommand changeCommand;

	public ChangeInvoker(ChangeCommand changeCommand) {
		this.changeCommand = changeCommand;
	}

	public void invoke() {
		if (this.changeCommand == null)
			return;

		int LAST = ChangeInvoker.getChangeModeHistory().size() - 1;
		if (changeModeHistory.get(LAST) == ChangeMode.NO_CHANGE)
			return;

		// change is limited to 20 steps
		if (ChangeInvoker.getChangeModeHistory().size() > 20 || this.changeCommand == null) {
			Toolkit.getDefaultToolkit().beep();

		} else {
			this.changeCommand.execute();
			ChangeInvoker.changeCount++;
		}
	}

	public static int getChangeCount() {
		return ChangeInvoker.changeCount;
	}

	public static void resetChangeCount() {
		ChangeInvoker.changeCount = 0;
	}

	public static ArrayList<ChangeMode> getChangeModeHistory() {
		return changeModeHistory;
	}

	public static void resetChangeModeHistory() {
		changeModeHistory.clear();
	}
}
