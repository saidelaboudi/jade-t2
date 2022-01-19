package ma.ensias.agent;

import jade.wrapper.AgentController;
import jade.wrapper.ContainerController;
import jade.wrapper.StaleProxyException;
import ma.ensias.env.JadePlateform;

public class Consumer {

	public Consumer() {
		JadePlateform myPlatform = new JadePlateform();
		ContainerController consumerContainer = myPlatform.createAgentContainer("ConsumerContainer", "localhost");
		try {
			// deploy a consumer agent on the consumer container
			AgentController consumerAgentController = consumerContainer.createNewAgent("ConsumerAgent",
					ConsumerAgent.class.getName(), new Object[] {"LAPTOP"});
			consumerAgentController.start();
		} catch (StaleProxyException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		new Consumer();
	}
}
