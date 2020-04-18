package model;

import controller.commander.ChangeCommand;
import controller.commander.ChangeMode;
import controller.commander.commands.AddShape;
import controller.commander.commands.RemoveShape;
import controller.commander.commands.UpdateShape;
import controller.commander.redo.RedoAddShape;
import controller.commander.redo.RedoRemoveShape;
import controller.commander.redo.RedoUpdateShape;
import controller.commander.undo.UndoAddShape;
import controller.commander.undo.UndoRemoveShape;
import controller.commander.undo.UndoUpdateShape;
import model.main.Application;
import model.shapes.Brush;
import model.shapes.Circle;
import model.shapes.Ellipse;
import model.shapes.Line;
import model.shapes.Rectangle;
import model.shapes.Square;
import model.shapes.Triangle;
import view.modes.CanvasMode;

public class Factory {

	/**
	 * overloading
	 * 
	 * @param canvasMode
	 * @return Shape
	 */
	public static Shape detectMode(CanvasMode canvasMode) {

		if (canvasMode == CanvasMode.BRUSH)
			return new Brush();

		if (canvasMode == CanvasMode.CIRCLE)
			return new Circle();

		if (canvasMode == CanvasMode.ELLIPSE)
			return new Ellipse();

		if (canvasMode == CanvasMode.SQUARE)
			return new Square();

		if (canvasMode == CanvasMode.RECTANGLE)
			return new Rectangle();

		if (canvasMode == CanvasMode.TRIANGLE)
			return new Triangle();

		if (canvasMode == CanvasMode.LINE)
			return new Line();

		return null;
	}

	/**
	 * overloading
	 * 
	 * @param changeMode
	 * @return ChangeCommand
	 */
	public static ChangeCommand detectMode(ChangeMode changeMode) {

		if (changeMode == ChangeMode.NO_CHANGE)
			return null;

		int LAST = Application.getController().getShapesArrayList().size() - 1;

		if (Application.getController().isOnUndo()) {
			// loop from ending index
			for (int i = LAST; i >= 0; i--) {
				if (Application.getController().getShapesArrayList().get(i).isVisible()) {
					LAST = i;
					break;
				}
			}
		} else if (Application.getController().isOnRedo()) {
			// loop from starting index
			for (int i = 0; i <= LAST; i++) {
				if (!Application.getController().getShapesArrayList().get(i).isVisible()) {
					LAST = i;
					break;
				}
			}
		}

		Shape shape = Application.getController().getShapesArrayList().get(LAST);
		System.out.println(shape);
		System.out.println(LAST);

		if (changeMode == ChangeMode.ADD) {
			if (Application.getController().isOnUndo())
				return new UndoAddShape(new AddShape(shape));

			else if (Application.getController().isOnRedo())
				return new RedoAddShape(new AddShape(shape));
		}

		else if (changeMode == ChangeMode.REMOVE) {
			if (Application.getController().isOnUndo())
				return new UndoRemoveShape(new RemoveShape(shape));

			else if (Application.getController().isOnRedo())
				return new RedoRemoveShape(new RemoveShape(shape));
		}

		else if (changeMode == ChangeMode.UPDATE) {
			if (Application.getController().isOnUndo())
				return new UndoUpdateShape(new UpdateShape(shape));

			else if (Application.getController().isOnRedo())
				return new RedoUpdateShape(new UpdateShape(shape));
		}
		return null;
	}

}
