/**
 * 
 */
package com.drap.numeric;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * An implementation of {@link com.drap.numeric.NumericShapeSource
 * NumericShapeSource} that sources the input numbers from a file. Note: This
 * implementation is not thread safe and should be used by a single thread only.
 * An instance of this class can only be used once to iterate over the lines of
 * the input file and return the values for each line.
 * 
 * @author David Rappoport
 */
public class NumericShapeFileSource implements NumericShapeSource {

	private String fileName;
	private InputStream inputStream;
	private Scanner scanner;
	private boolean scannerClosed;

	/**
	 * 
	 * @param fileName
	 *            the name of the file to query
	 * 
	 */
	public NumericShapeFileSource(String fileName) {
		this.fileName = fileName;
	}

	/**
	 * @return InputStream a stream for this file
	 * @throws IOException
	 */
	private InputStream getStream() throws SourceNotAccessibleException {
		if (inputStream == null) {
			// to be able to read the file from within the .jar file, we
			// use classloader.getResourceAsStream
			ClassLoader classLoader = getClass().getClassLoader();
			inputStream = classLoader.getResourceAsStream(fileName);
			if (inputStream == null)
				throw new SourceNotAccessibleException();
		}
		return inputStream;
	}

	/**
	 * Used internally
	 * 
	 * @return {@link java.util.Scanner Scanner}
	 * @throws SourceNotAccessibleException
	 */
	private Scanner getScanner() throws SourceNotAccessibleException {
		if (scanner == null) {
			scanner = new Scanner(getStream());
		}
		return scanner;
	}

	/**
	 * 
	 * @see com.drap.numeric.NumericShapeSource#hasNextRow()
	 */
	@Override
	public boolean hasNextRow() throws SourceNotAccessibleException {
		if (scannerClosed)
			return false;
		return getScanner().hasNextLine();
	}

	/**
	 * Returns the a list of long values for the next row in the file.
	 * 
	 * @see com.drap.numeric.NumericShapeSource#getNextRow()
	 */
	@Override
	public List<Long> getNextRow() throws SourceNotAccessibleException {
		if (scannerClosed || !getScanner().hasNextLine())
			return null;

		// use the same scanner for each successive line
		Scanner scanner = getScanner();
		String line = scanner.nextLine();

		// use a different scanner to parse the line, as the original scanner would
		// parse the entire file using nextLong()
		Scanner lineScanner = new Scanner(line);
		List<Long> row = new ArrayList<Long>();
		while (lineScanner.hasNextLong()) {
			row.add(lineScanner.nextLong());
		}
		lineScanner.close();

		// do this here, as the user is unlikely to do it on his side
		if (!scanner.hasNextLine()) {
			scanner.close();
			scannerClosed = true;
		}
		return row;
	}

	/**
	 * Close the scanner if it hasn't been closed yet in getNextRow().
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		if (scanner != null) {
			scanner.close();
			scanner = null;
		}
	}

}
