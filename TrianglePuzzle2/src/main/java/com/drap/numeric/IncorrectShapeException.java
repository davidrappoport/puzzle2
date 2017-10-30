/**
 * 
 */
package com.drap.numeric;

/**
 * An exception if the shape of the numbers from the 
 * {@link com.drap.numeric.NumericShapeSource NumericShapeSource} does not match the expected shape
 * @author David Rappoport
 *
 */
public class IncorrectShapeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -547508837409557276L;

	/**
	 * 
	 * @param msg - The exception message 
	 */
	public IncorrectShapeException(String msg) {
		super(msg);
	}
}
