package ma.ensias.agent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

public class ConsumerAgent extends Agent {
	String productName = null;

	@Override
	protected void setup() {
		System.out.println(this.getAID().getLocalName() + " created");
		Object[] args = this.getArguments();
		if (args.length == 1) {
			productName = (String) args[0];
		}
		ParallelBehaviour parbehviour = new ParallelBehaviour();
		addBehaviour(parbehviour);
		var missionBehaviour = new OneShotBehaviour() {
			@Override
			public void action() {
				// TODO Auto-generated method stub
				System.out.println(myAgent.getAID().getLocalName() + " : my mission => buying a " + productName
						+ " for the consumer ");
				ACLMessage request = new ACLMessage(ACLMessage.REQUEST);
				AID receiver = new AID("ProducerAgent", AID.ISLOCALNAME);
				request.addReceiver(receiver);
				request.setContent(productName);

				System.out.println(request.getContent());
				send(request);
			}
		};
		parbehviour.addSubBehaviour(missionBehaviour);
		var lookingForSellerBehaviour = new TickerBehaviour(this, 2000) {
			@Override
			protected void onTick() {
				// TODO Auto-generated method stub
				System.out.println(myAgent.getAID().getLocalName() + " I'm looking for a Seler. tick = " + getTickCount());
				ACLMessage message  = receive();
				if(message!=null) {
					System.out.println(/*message.getSender().getLocalName()+ myAgent.getAID().getLocalName()+" : "+*/message.getContent());
				}
			}
		};
		parbehviour.addSubBehaviour(lookingForSellerBehaviour);
		var finishigBehav = new WakerBehaviour(this, 10000) {
			@Override
			protected void onWake() {
				// TODO Auto-generated method stub
				super.onWake();
				System.out.println(myAgent.getAID().getLocalName() + "Bye ....");
				myAgent.doDelete();
			}
		};
		parbehviour.addSubBehaviour(finishigBehav);
	}

	public static void main(String[] args) {
		new ConsumerAgent();
	}
}
