package project;

import static org.junit.Assert.*;
import org.junit.*;

public class SimulationViewTest {

	SimulationView one = null;
	@Before
	public void setUp() throws Exception {
		one = new SimulationView();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() {
		one.view2();
		one.producerStrategy();
		one.addtoData(5.0, 7);
		one.addtoData(8, 9);
	    one.addtoData(10, 13);
		one.addtoData(1, 12);
		one.addtoActivityFeed("sheryl");
	}

}
