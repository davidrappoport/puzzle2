package com.drap.numeric.triangle;

import java.util.ArrayList;
import java.util.List;

import com.drap.numeric.Algorithm;
import com.drap.numeric.IncorrectShapeException;
import com.drap.numeric.NumericShape;
import com.drap.numeric.NumericShapeSource;
import com.drap.numeric.SourceNotAccessibleException;
/**
 * An implementation of {@link com.drap.numeric.Algorithm Algorithm} that 
 * processes data in the shape of a triangle (where each successive line adds on additional row)
 * and calculates the value of the path (from top to bottom) in the triangle.
 * A path can be constructed by taking the value of either the left parent or the right parent.
 * @author David Rappoport
 *
 */
public abstract class TrianglePathAlgorithm implements Algorithm {

	private static final String FOR_SHAPE = " for shape: ";
	private static final String EXPECTED_MATCHING = "Expecting matching number of values to row number for row number:";
	/**
	 * Caches only the previous row state and adds the values from the current row
	 * @see com.drap.numeric.Algorithm#getResult(com.drap.numeric.NumericShapeSource)
	 */
	@Override
	public long getResult(NumericShapeSource source)
			throws SourceNotAccessibleException, IncorrectShapeException {
		if (source == null)
			throw new IllegalArgumentException(Algorithm.SOURCE_NULL);
		int currentRowNumber = 0;
		List<Long> previousRow = new ArrayList<Long>();
		previousRow.add((long)0);
		List<Long> row = null;
		
		//get values from source
		while (source.hasNextRow()) {
			currentRowNumber++;
			row = source.getNextRow();
			// expecting number of values to match row number
			if (row.size() != currentRowNumber)
				throw new IncorrectShapeException(EXPECTED_MATCHING
						+ currentRowNumber + FOR_SHAPE + getSupportedShape());
			for (int i = 0; i < currentRowNumber; i++) {
				if (i == 0)// first value, just one parent
					row.set(i, row.get(i)+previousRow.get(i));
				else if (i == currentRowNumber - 1) // last value, just one parent (left)
					row.set(i, row.get(i)+previousRow.get(i-1));
				else // choose larger of two parent values
					row.set(i, row.get(i)+select(previousRow.get(i), previousRow.get(i-1)));
			}
			previousRow = row;
		}
		//input data expected to have at least one row
		if (row == null) throw new IncorrectShapeException(Algorithm.ONE_ROW_EXPECTED );
		
		//iterate over last row and find highest value
		//values might be negative, so start with lowest possible value
		long result = previousRow.get(0);
		for (Long l : previousRow) {
			result = select(l, result);
		}

		return result;
	}
	
	/**
	 * Selects one value out of two based on the algorithm's operation
	 * @param val1 firstValue
	 * @param val2 secondValue
	 * @return a selected value
	 */
	public abstract long select(long val1, long val2);
	

	/**
	 * The shape supported by this algorithm
	 * @see com.drap.numeric.Algorithm#getSupportedShape()
	 */
	@Override
	public NumericShape getSupportedShape() {
		return NumericShape.TRIANGLE;
	}

	
}
