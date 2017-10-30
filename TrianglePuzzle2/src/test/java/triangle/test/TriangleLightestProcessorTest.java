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

public class TriangleLightestProcessorTest {

	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testProcessTriangle27() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(18, new TriangleProcessor().processTriangleFileLightestPath("triangle27.txt"));
	}

	@Test
	public void testProcessTriangleLeftFile() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(41, new TriangleProcessor().processTriangleFileLightestPath("triangle56Left.txt"));
	}

	@Test
	public void testProcessTriangleRightFile() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(41, new TriangleProcessor().processTriangleFileLightestPath("triangle56Left.txt"));
	}
	
	@Test
	public void testProcessTriangleMiddleFile() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(41, new TriangleProcessor().processTriangleFileLightestPath("triangle56Middle.txt"));
	}

	@Test
	public void testProcessTriangleIncorrectNumberOfValuesFile() throws SourceNotAccessibleException, IncorrectShapeException {
		exception.expect(IncorrectShapeException.class);
		new TriangleProcessor().processTriangleFileLightestPath("triangleIncorrectNumberOfValues.txt");
	}
	
	@Test
	public void testProcessTriangleNoLines() throws SourceNotAccessibleException, IncorrectShapeException {
		exception.expect(IncorrectShapeException.class);
		new TriangleProcessor().processTriangleFileLightestPath("triangleNoLines.txt");
	}
	
	@Test
	public void testProcessTriangleWrongDelimiter() throws SourceNotAccessibleException, IncorrectShapeException {
		exception.expect(IncorrectShapeException.class);
		new TriangleProcessor().processTriangleFileLightestPath("triangleWrongDelimiter.txt");
	}
	
	@Test
	public void testProcessTriangle100rows() throws SourceNotAccessibleException, IncorrectShapeException {
		assertEquals(272473, new TriangleProcessor().processTriangleFileLightestPath("triangle_test_100rows.txt"));
	}
}
