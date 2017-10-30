package com.drap.numeric.triangle;

import com.drap.numeric.NumericShapeOperation;
/**
 * An implementation of {@link com.drap.numeric.Algorithm Algorithm} that 
 * processes data in the shape of a triangle (where each successive line adds on additional row)
 * and calculates the heaviest path (from top to bottom) in the triangle.
 * A path can be constructed by taking the value of either the left parent or the right parent.
 * @author David Rappoport
 *
 */
public class TriangleHeaviestPathAlgorithm extends TrianglePathAlgorithm {

	/**
	 * The operation supported by this algorithm
	 * @see com.drap.numeric.Algorithm#getSupportedOperation()
	 */
	@Override
	public NumericShapeOperation getSupportedOperation() {
		return NumericShapeOperation.HEAVIEST_PATH;
	}

	/**
	 * 
	 * @see com.drap.numeric.triangle.TrianglePathAlgorithm#select(long, long)
	 */
	@Override
	public long select(long val1, long val2) {
		return val1 > val2? val1: val2;
	}

}
