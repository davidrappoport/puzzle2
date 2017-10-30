package com.drap.numeric;

import java.util.List;

/**
 * A source for {@link com.drap.numeric.NumericShape NumericShape} input data
 * @author David Rappoport
 *
 */
public interface NumericShapeSource {
	
	/**
	 * Returns the next row of iput data
	 * @return A {@link java.util.List List} of {@link java.lang.Long} values
	 * @throws SourceNotAccessibleException - If the source cannot be read for some reason
	 */
	public List<Long> getNextRow() throws SourceNotAccessibleException;
	
	/**
	 * 
	 * @return true if the source has another row of data available.
	 * It is recommended to call this method before calling getNextRow()
	 * @throws SourceNotAccessibleException if the source cannot be accessed
	 */
	public boolean hasNextRow() throws SourceNotAccessibleException;
}
