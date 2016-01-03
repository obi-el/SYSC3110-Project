package project;
import static org.junit.Assert.*;
import org.junit.*;
/**
 * @author Craig Isesele
 */
public class SearchStrategyTest {

	FileShare fileshare = null;
	FileShare fileshare1 = null;
	FileShare fileshare2 = null;
	@Before
	public void setUp() throws Exception {
		fileshare = new FileShare();
		fileshare1 = new FileShare(new SearchByLikes());
		fileshare2 = new FileShare(new SearchByLikeSimilarity());
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void defaultStrategyTest() {
		assertEquals(fileshare.getStrategy(),fileshare1.getStrategy());
		assertNotEquals(fileshare.getStrategy(),fileshare2.getStrategy());
	}
}
