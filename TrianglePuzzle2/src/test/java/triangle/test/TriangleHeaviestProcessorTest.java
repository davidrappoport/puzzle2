package triangle.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.drap.numeric.IncorrectShapeException;
import com.drap.numeric.SourceNotAccessibleException;
import com.drap.numeric.triangle.TriangleProcessor;

public class TriangleHeaviestProcessorTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testProcessTriangle27() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(27, new TriangleProcessor().processTriangleFile("triangle27.txt"));
	}

	@Test
	public void testProcessTriangleLeftFile() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(56, new TriangleProcessor().processTriangleFile("triangle56Left.txt"));
	}

	@Test
	public void testProcessTriangleRightFile() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(56, new TriangleProcessor().processTriangleFile("triangle56Left.txt"));
	}
	
	@Test
	public void testProcessTriangleMiddleFile() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(56, new TriangleProcessor().processTriangleFile("triangle56Middle.txt"));
	}

	@Test
	public void testProcessTriangleIncorrectNumberOfValuesFile() throws SourceNotAccessibleException, IncorrectShapeException {
		exception.expect(IncorrectShapeException.class);
		new TriangleProcessor().processTriangleFile("triangleIncorrectNumberOfValues.txt");
	}
	
	@Test
	public void testProcessTriangleNoLines() throws SourceNotAccessibleException, IncorrectShapeException {
		exception.expect(IncorrectShapeException.class);
		new TriangleProcessor().processTriangleFile("triangleNoLines.txt");
	}
	
	@Test
	public void testProcessTriangleWrongDelimiter() throws SourceNotAccessibleException, IncorrectShapeException {
		exception.expect(IncorrectShapeException.class);
		new TriangleProcessor().processTriangleFile("triangleWrongDelimiter.txt");
	}
	
	@Test
	public void testProcessTriangle100rows() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(732506, new TriangleProcessor().processTriangleFile("triangle_test_100rows.txt"));
	}
}
