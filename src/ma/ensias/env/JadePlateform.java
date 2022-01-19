package ma.ensias.env;

import jade.core.ProfileImpl;
import jade.util.ExtendedProperties;
import jade.util.leap.Properties;
import jade.wrapper.ContainerController;

public class JadePlateform {
	private ContainerController mainContainer;
	private jade.core.Runtime runtime = null;

	public JadePlateform() {
		runtime = jade.core.Runtime .instance(); // singleton instance of the JADE Runtime
		Properties p = new ExtendedProperties();// for some properties
		//p.setProperty("gui", "true"); // –gui especially
		ProfileImpl profile = new ProfileImpl(p);
		mainContainer = runtime.createMainContainer(profile);
	}

	public ContainerController createAgentContainer(String name, String host) {
		ProfileImpl profile = new ProfileImpl(false);
// This is not a main-container.
		profile.setParameter(ProfileImpl.MAIN_HOST, host);
// Set the container name.
		profile.setParameter(ProfileImpl.CONTAINER_NAME, name);
// Associated with the main-container started on host
		return runtime.createAgentContainer(profile);
	}
	
	public static void main(String[] args) {
		new JadePlateform();
	}
}