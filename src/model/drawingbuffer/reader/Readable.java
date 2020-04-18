package model.drawingbuffer.reader;

import java.io.IOException;

public interface Readable {

	public void readFromFile(String filePath) throws IOException;
}
