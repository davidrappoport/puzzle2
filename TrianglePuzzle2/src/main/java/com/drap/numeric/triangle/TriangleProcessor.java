package com.drap.numeric.triangle;

import com.drap.numeric.Algorithm;
import com.drap.numeric.AlgorithmProvider;
import com.drap.numeric.IncorrectShapeException;
import com.drap.numeric.NumericShape;
import com.drap.numeric.NumericShapeFileSource;
import com.drap.numeric.NumericShapeOperation;
import com.drap.numeric.SourceNotAccessibleException;

/**
 * TriangleProcessor
 * <p>
 * Process an input file containing integers in triangle form and calculate the
 * heaviest path
 * 
 * @author David Rappoport
 * @since 2017-10-24
 *
 */
public class TriangleProcessor {

	public static void main(String[] args) throws SourceNotAccessibleException, IncorrectShapeException {
		if (args.length == 0 || args[0] == null || args[0].trim() == "") {
			throw new IllegalArgumentException("Expecting file name argument");
		}

		long result = new TriangleProcessor().processTriangleFile(args[0]);
		System.out.println("The heaviest path is: " + result);
	}

	/**
	 * Takes a file containing numbers in a triangle shape (as described in the
	 * requirements) and returns the heaviest path along the way
	 * 
	 * @param fileName
	 *            <p>
	 *            Assumptions:
	 *            <p>
	 *            1: File contains at least one line with a number
	 *            <p>
	 *            2: First line has one number only
	 *            <p>
	 *            3: Every subsequent line has one additional number
	 *            <p>
	 *            4: Values are separated by whitespace
	 *            <p>
	 *            5: Values are integers
	 * @return heaviestWeight long
	 *         <p>
	 *         The heaviest weight is the sum of the values of the nodes along a
	 *         given path from the top of the triangle to the bottom
	 *         <p>
	 * 		(using long, as the addition of an unspecified number of integers may
	 *         result in a long value)
	 * @throws SourceNotAccessibleException if the data source cannot be accessed 
	 * @throws IncorrectShapeException if the data is not in the expected triangle shape
	 */
	public long processTriangleFile(String fileName) throws SourceNotAccessibleException, IncorrectShapeException {
		Algorithm algorithm = AlgorithmProvider.getAlgorithm(NumericShape.TRIANGLE, NumericShapeOperation.HEAVIEST_PATH);
		NumericShapeFileSource source = new NumericShapeFileSource(fileName);
		return algorithm.getResult(source);
	}
	
	/**
	 * Takes a file containing numbers in a triangle shape (as described in the
	 * requirements) and returns the lightest path along the way
	 * 
	 * @param fileName
	 *            <p>
	 *            Assumptions:
	 *            <p>
	 *            1: File contains at least one line with a number
	 *            <p>
	 *            2: First line has one number only
	 *            <p>
	 *            3: Every subsequent line has one additional number
	 *            <p>
	 *            4: Values are separated by whitespace
	 *            <p>
	 *            5: Values are integers
	 * @return lightest path long
	 *         <p>
	 *         The lightest weight is the sum of the values of the nodes along a
	 *         given path from the top of the triangle to the bottom
	 *         <p>
	 * 		(using long, as the addition of an unspecified number of integers may
	 *         result in a long value)
	 * @throws SourceNotAccessibleException if the data source cannot be accessed 
	 * @throws IncorrectShapeException if the data is not in the expected triangle shape
	 */
	public long processTriangleFileLightestPath(String fileName) throws SourceNotAccessibleException, IncorrectShapeException {
		Algorithm algorithm = AlgorithmProvider.getAlgorithm(NumericShape.TRIANGLE, NumericShapeOperation.LIGHTEST_PATH);
		NumericShapeFileSource source = new NumericShapeFileSource(fileName);
		return algorithm.getResult(source);
	}

	
}
