package controller.commander;

public interface Change {
	
	/**
	 * limited to 20 steps
	 * 
	 * consider these actions in undo & re-do:
	 * addShape() - removeShape() - updateShape()
	 */
	public void undo();

	public void redo();

}
