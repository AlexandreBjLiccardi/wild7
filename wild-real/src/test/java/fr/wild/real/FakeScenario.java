
package fr.wild.real;

import fr.wild.orchestra.WildClass;
import fr.wild.orchestra.WildObject;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 *
 */
public class FakeScenario extends WildClass implements Callable<Object>{

	private int time = 10;

	public FakeScenario(WildObject wild, String configxml) throws Exception {
		WILD_initialize_WildClass(wild);
	}

	public FakeScenario(WildObject wild, Map configmap) throws Exception {
		WILD_initialize_WildClass(wild);
		time = Integer.valueOf( (String)((Map)configmap.get("fake.sleep")).get("value") );
	}

	@Override
	public Object call() throws Exception {
		Thread.sleep(time*1000);
		System.out.println("Execution");
		return null;
	}

}
