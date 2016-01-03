package project;
/**
 * @author Craig Isesele
 */

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses
({
	//SimulatorTest.class
	SimulationViewTest.class,
	SearchStrategyTest.class,
	UnitTest.class
	})
public class AllTests{}