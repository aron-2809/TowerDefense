package junit_test;

import static org.junit.Assert.*;
import main.Controller;

import org.junit.Test;

public class TestController {
	/**
	 * THis method tests the instance of controller
	 */
	@Test
	public void testGetInstance()
	{
		Controller instance1 = Controller.getInstance();
		Controller instance2 = Controller.getInstance();
		assertSame(instance1, instance2);
	}

}
