package ma.ensias.agent;

import java.util.HashMap;
import java.util.Map;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class ProducerAgent extends Agent {
	Map<String, Double> products;

	public ProducerAgent() {
		// TODO Auto-generated constructor stub
		products = new HashMap<String, Double>();
		products.put("CAMERA", 2500.0);
		products.put("LAPTOP", 6590.0);
		products.put("PRINTER", 2648.0);
	}
	@Override
	protected void setup() {
		System.out.println(this.getAID().getLocalName() + " created");
	}
	@Override
		public void addBehaviour(Behaviour b) {
			new CyclicBehaviour() {
				 @Override
				 public void action() {
				 ACLMessage message = receive();//Receipt the message
				 if(message != null) {
				 System.out.println(message.getSender().getLocalName() +
				 " => " +myAgent.getAID().getLocalName()+
				 ": "+message.getContent());
				 //Replay with a PROPOSITION
				 ACLMessage reply = message.createReply();
				 reply.setPerformative(ACLMessage.PROPOSE);
				 String productName = message.getContent();
				 Double productPrice = products.get(productName);
				 reply.setContent(productName+"\t Price : "+productPrice+" DHS");
				 send(reply);
				 }else {
					 block();
					 }
				 }
				};
		}
	public static void main(String[] args) {
		new ProducerAgent();
	}
}
