package project;

public class UserController {

	private SimulationView sv;
	private User u;
	private boolean prodstrat;
	int CurrentCycle;
	public UserController(User u,SimulationView sv, boolean producerstrategy,int cycle) 
	{ 
		CurrentCycle = cycle;
		prodstrat = producerstrategy;
		this.sv = sv;
		this.u = u;
	}
	
	/**
	 * @param sv SimulationView with activity feed to be updated
	 * @param u	user being monitored
	 */
	protected void update(SimulationView sv, User u)
	{
		for (String str :u.messages) { sv.addtoActivityFeed(str); }
		u.clearMessages();
	}
	
	/**
	 * if producer, open the producerstrategy jdialog which is
	 * set to modal( so it waits for you to press done before
	 * resuming execution) to choose like strategy. if false get
	 * the taste inputted in the textfield and also the search
	 * strategy selected and searches using those, if true
	 * search with own taste All users also follows everyone
	 * that liked document they are about to like
	 * 
	 * updates the Jchart
	 * 
	 * @param u	user
	 */
	protected void userActs(User u)
	{
		if (u instanceof Producer) {
			sv.setSimUsTypeText("Producer");
			Producer p = (Producer) u;
			sv.disablestep();
			sv.producerStrategy();
			sv.enablestep();
			if (!(prodstrat)) {
				p.likeDifferentTaste(sv.getProdtaste(),
						p.getFileShare().search(Integer.valueOf(sv.getSimUsSearchText()), p));
				p.payoffMessage();
				sv.addtoData(CurrentCycle, p.getPayoff());
				p.act();
				update(sv,p);
			} else {
				p.likeSameTaste(p.getFileShare().search(Integer.valueOf(sv.getSimUsSearchText()), p));
				p.payoffMessage();
				sv.addtoData(CurrentCycle, p.getPayoff());
				p.act();
				update(sv,p);
			}
		} else if (u instanceof Consumer) {
			sv.setSimUsTypeText("Consumer");
			Consumer c = (Consumer) u;
			c.act(c.getFileShare().search(Integer.valueOf(sv.getSimUsSearchText()), c));
			sv.addtoData(CurrentCycle, c.getPayoff());
			update(sv,c);
		}
	}
}