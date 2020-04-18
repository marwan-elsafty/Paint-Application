package model.drawingbuffer;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

import model.drawingbuffer.reader.ReadFromJSON;
import model.drawingbuffer.reader.ReadFromXML;
import model.drawingbuffer.writer.WriteInJSON;

public final class DrawingBuffer {

	private static File fileDirectory;

//	private model.drawingbuffer.writer.Writable strategyWritable;
	private model.drawingbuffer.reader.Readable strategyReadable;

	private String extension;
	private File file;
	private JFileChooser fileChooser;

	public DrawingBuffer() {

	}

	public void save(String filePath) {
		// fileChooser.showSaveDialog(null);
		// fileChooser.setDialogTitle("Save");

		// if (file != null) {
		// extension = fileChooser.getFileExtension();
		// System.out.println(extension);
		// switch (extension) {
		// case "json":
		try {
			new WriteInJSON().writeInFile(filePath);
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		// break;
		//
		// case "xml":
		// try {
		// new WriteInXML().writeInFile(null);
		// } catch (IOException exception) {
		// exception.printStackTrace();
		// }
		// break;
		// }
		// }
	}

	public void load() {
		fileChooser.setDialogTitle("Open");
		fileChooser.showSaveDialog(null);

		if (file != null) {
			extension = getFileExtension(file.getName());

			if (extension.equals("json"))
				strategyReadable = new ReadFromJSON();
			else
				strategyReadable = new ReadFromXML();

			try {
				strategyReadable.readFromFile(null);
			} catch (IOException exception) {
				exception.printStackTrace();
			}
		}
	}

	private String getFileExtension(String extension) {
		return extension.substring(extension.lastIndexOf(".") + 1);
	}

	public static File getFileDirectory() {
		return DrawingBuffer.fileDirectory;
	}

	public static void setFileDirectory(File fileDirectory) {
		DrawingBuffer.fileDirectory = fileDirectory;
	}

	public static boolean hasFileDirectory() {
		if (DrawingBuffer.fileDirectory == null)
			return false;
		return true;
	}
}
