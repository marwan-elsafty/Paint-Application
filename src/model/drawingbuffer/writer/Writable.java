package model.drawingbuffer.writer;

import java.io.IOException;

public interface Writable {

	public void writeInFile(String filPath) throws IOException;
}
