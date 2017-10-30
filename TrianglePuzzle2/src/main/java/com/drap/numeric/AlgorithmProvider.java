package com.drap.numeric;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import com.drap.numeric.triangle.TriangleHeaviestPathAlgorithm;
import com.drap.numeric.triangle.TriangleLightestPathAlgorithm;

/**
 * Returns a matching {@link com.drap.numeric.Algorithm Algorithm} for a given
 * {@link com.drap.numeric.NumericShape NumericShape} and
 * {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
 * 
 * @author David Rappoport
 *
 */
public class AlgorithmProvider {

	private static final String AND_SHAPE = " and shape: ";
	private static final String NO_ALGORITHM_HAS_BEEN_IMPLEMENTED_FOR_OPERATION = "No algorithm has been implemented for operation: ";

	/**
	 * Private constructor to prevent instantiation
	 */
	private AlgorithmProvider() {

	}
	
	/**
	 * Initialize the provider with a static map
	 */
	private static Map<ShapeOperationKey, Class<? extends Algorithm>> shapeOperationToAlgoMap;
	static {
		shapeOperationToAlgoMap = new HashMap<ShapeOperationKey, Class<? extends Algorithm>>();
		registerAlgo(NumericShape.TRIANGLE, NumericShapeOperation.HEAVIEST_PATH, TriangleHeaviestPathAlgorithm.class);
		registerAlgo(NumericShape.TRIANGLE, NumericShapeOperation.LIGHTEST_PATH, TriangleLightestPathAlgorithm.class);
	}
	
	/**
	 * Allows registering an {@link com.drap.numeric.Algorithm Algorithm} for a given
	 *  {@link com.drap.numeric.NumericShape NumericShape} and 
	 * {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
	 * @param shape {@link com.drap.numeric.NumericShape NumericShape}
	 * @param operation {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
	 * @param clazz {@link java.lang.Class class} class must be of type {@link com.drap.numeric.Algorithm Algorithm}
	 */
	public static void registerAlgo(NumericShape shape, NumericShapeOperation operation,
			Class<? extends Algorithm> clazz) {
		shapeOperationToAlgoMap.put(new AlgorithmProvider.ShapeOperationKey(shape, operation), clazz);
	}
	
	/**
	 * Returns a matching algorithm {@link com.drap.numeric.Algorithm Algorithm}
	 * for a given {@link com.drap.numeric.NumericShape NumericShape}
	 * and {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
	 * @param shape {@link com.drap.numeric.NumericShape NumericShape}
	 * @param operation {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
	 * @return {@link com.drap.numeric.Algorithm Algorithm}
	 */
	public static Algorithm getAlgorithm(NumericShape shape, NumericShapeOperation operation) {
		ShapeOperationKey key = new ShapeOperationKey(shape, operation);
		if (shapeOperationToAlgoMap.containsKey(key))
			try {
				//create a new instance of this algorithm (in case the algorithm needs to maintain state)
				return shapeOperationToAlgoMap.get(key).newInstance();
			} catch (InstantiationException | IllegalAccessException e) {
				throw new UnsupportedOperationException(
						NO_ALGORITHM_HAS_BEEN_IMPLEMENTED_FOR_OPERATION + operation + AND_SHAPE + shape);
			}
		else
			throw new UnsupportedOperationException(
					NO_ALGORITHM_HAS_BEEN_IMPLEMENTED_FOR_OPERATION + operation + AND_SHAPE + shape);
	}

	/**
	 * Inner class to form single key from {@link com.drap.numeric.NumericShape NumericShape}
	 * and {@link com.drap.numeric.NumericShapeOperation NumericShapeOperation}
	 * We could have also used the Apache multikeymap
	 * @author David Rappoport
	 *
	 */
	private static class ShapeOperationKey {

		private NumericShape shape;
		private NumericShapeOperation operation;

		private ShapeOperationKey(NumericShape shape, NumericShapeOperation operation) {
			if (shape == null || operation == null)
				throw new NullPointerException();
			this.shape = shape;
			this.operation = operation;
		}
		
		/**
		 * Returns true if both shape and operation are equal
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		public boolean equals(Object o) {
			ShapeOperationKey shapeOperation = (ShapeOperationKey) o;
			if (shapeOperation.shape.equals(this.shape) && shapeOperation.operation.equals(this.operation))
				return true;
			return false;
		}
		
		/**
		 * Gets a single hash code for two objects
		 * @see java.lang.Object#hashCode()
		 */
		public int hashCode() {
			return Objects.hash(shape, operation);
		}

	}

}
