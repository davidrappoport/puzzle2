package com.drap.numeric;

/**
 * If the source cannot be accessed for some reason
 * Use this exception instead of IOException in case the source is 
 * a different type (such as a database or another stream)
 * @author David Rappoport
 *
 */
public class SourceNotAccessibleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7071644975508459161L;

}
