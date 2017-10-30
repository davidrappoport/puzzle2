package com.drap.numeric;

/**
 * An algorithm that can process a given {@link com.drap.numeric.NumericShape NumericShape} using 
 * a specified {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
 * and produce a long result
 * @author David Rappoport
 
 */
public interface Algorithm {
	
	public String ONE_ROW_EXPECTED = "Expected at least one row of input data";
	public String SOURCE_NULL = "NumbericShapeSource cannot be null";

	/**
	 * Gets integer values from the specified source, and applies a {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
	 *  to a  {@link com.drap.numeric.NumericShape NumericShape}
	 * @param source {@link com.drap.numeric.NumericShapeSource NumericShapeSource}
	 * @return long - The result of the operation
	 * @throws SourceNotAccessibleException if the data cannot be access from source
	 * @throws IncorrectShapeException if the data does not conform to the expected shape
	 */
	public long getResult(NumericShapeSource source) throws SourceNotAccessibleException, IncorrectShapeException;
	
	/**
	 * @return the type of {@link com.drap.numeric.NumericShape NumericShape} supported by this algorithm
	 */
	public NumericShape getSupportedShape();
	
	/**
	 * @return the type of {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation} supported by this algorithm
	 */
	public NumericShapeOperation getSupportedOperation();
	
}
