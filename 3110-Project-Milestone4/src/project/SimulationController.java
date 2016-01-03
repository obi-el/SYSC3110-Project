package project;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.util.*;
import javax.swing.*;

/**
 * RUN PROGRAM HERE Controller class for the gui
 * 
 * @author Elobi Obinna
 *
 * It observes the view class and whenever a button is pressed the
 * action event is sent here it performs the required functions of the
 * current user until the number of cycles inputted passes
 * 
 */
public class SimulationController implements Observer {
<<<<<<< HEAD
 private int Cycles;
 private int CurrentCycle = 0;
 private boolean prodstrat;
 private FileShare fs;
 private SimulationView sv;
 private int UndoCycles;
 private int UndoCurrentCycle = 0;
 private boolean Undoprodstrat;
 private FileShare Undofs;
 private SimulationView Undosv;

 public SimulationController() {
  fs = new FileShare();
  sv = new SimulationView();
  sv.addObserver(this);
 }

 /*
  * (non-Javadoc)
  * 
  * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
  */
 public void update(Observable arg0, Object arg1) {
  ActionEvent ae = (ActionEvent) arg1;
  String cmd = ae.getActionCommand();
  if (cmd.equals("Simulate")) {
   if (sv.canSimulate()) {
    sv.disableButton();
    int prod = Integer.valueOf(sv.getNoProds());
    int con = Integer.valueOf(sv.getNoCons());
    fs.createProdAndCon(prod, con);
    fs.setUserTaste();
    Cycles = Integer.valueOf(sv.getNoCycles());
    sv.view2();
   } else
    sv.showErrorDialog();
  }

  if (cmd.equals("step")) {
   UndoCycles=this.Cycles;
   UndoCurrentCycle=this.CurrentCycle;
   Undoprodstrat=this.prodstrat;
   Undofs=new FileShare(this.fs);
   
   if (sv.getSimUsSearchText().equals(""))sv.showErrorDialog();
   else {
    CurrentCycle++;
    if (CurrentCycle > Cycles) {
     JFrame frame = new JFrame();
     JOptionPane.showMessageDialog(frame, "Simulation Ended", "Done", JOptionPane.ERROR_MESSAGE);
    } else {
     sv.addtoActivityFeed("Cycle " + CurrentCycle);
     User us = fs.selectRandomUser();
     sv.setSimUsidText(us.getUserID());
     sv.setSimUsTasteText(us.getTaste());

     if (sv.getSelectedStrat().equals("SearchByLikes"))
      fs.setStrategy(new SearchByLikes());
     else if (sv.getSelectedStrat().equals("SearchByLikeSimilarity"))
      fs.setStrategy(new SearchByLikeSimilarity());
     else if (sv.getSelectedStrat().equals("SearchByFollowers"))
      fs.setStrategy(new SearchByFollowers());
     else if (sv.getSelectedStrat().equals("SearchByDistance"))
      fs.setStrategy(new SearchByDistance());
     else if (sv.getSelectedStrat().equals("SearchByFollowersSimilarity"))
      fs.setStrategy(new SearchByFollowersSimilarity());

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
      */
     if (us instanceof Producer) {
      sv.setSimUsTypeText("Producer");
      Producer p = (Producer) us;
      sv.disablestep();
      sv.producerStrategy();
      sv.enablestep();
      if (!(prodstrat)) {
       p.likeDifferentTaste(sv.getProdtaste(),
         fs.search(Integer.valueOf(sv.getSimUsSearchText()), p));
       p.messages.add("producer " + p.getUserID() + "payoff is now " + p.getPayoff());
       sv.addtoData(CurrentCycle, p.getPayoff());
       p.act();
       p.notify(sv);
      } else {
       p.likeSameTaste(fs.search(Integer.valueOf(sv.getSimUsSearchText()), p));
       p.messages.add("producer " + p.getUserID() + "payoff is now " + p.getPayoff());
       sv.addtoData(CurrentCycle, p.getPayoff());
       p.act();
       p.notify(sv);
      }

     } else if (us instanceof Consumer) {
      sv.setSimUsTypeText("Consumer");
      Consumer c = (Consumer) us;
      c.act(fs.search(Integer.valueOf(sv.getSimUsSearchText()), c));
      sv.addtoData(CurrentCycle, c.getPayoff());
      c.notify(sv);
     }
    }
   }
  }
  
  if (cmd.equals("undo")) 
  {
   this.Cycles=UndoCycles;
   this.CurrentCycle=UndoCurrentCycle;
   this.prodstrat=Undoprodstrat;
   this.fs=fs;
   this.sv.deleteListItem();
   this.sv.deleteListItem();
   this.sv.deleteListItem();
  }
  if (cmd.equals("save")){
	  try {
		SimFormat.save(new SimFormat(prodstrat,Cycles,CurrentCycle));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  if (cmd.equals("load")){
	  try {
		SimFormat sim = SimFormat.load();
		if(sim.currentCycles<CurrentCycle){
			for (int i = 0; i < sim.currentCycles; i++){
				   this.sv.deleteListItem();
				   this.sv.deleteListItem();
				   this.sv.deleteListItem();
			}
			   this.Cycles=sim.cycles;
			   this.CurrentCycle=sim.cycles;
			   this.prodstrat=sim.Prodstrat;
		}
		else {
			System.out.println("Error: Invalid State");
		}
	} catch (IOException | ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  if (cmd.equals("Done")) prodstrat = sv.getLikebysame();

  // endupdate//
 }

 public static void main(String args[]) { new SimulationController(); }
=======
	private int Cycles, UndoCycles;
	private int CurrentCycle,UndoCurrentCycle = 0;
	private boolean prodstrat,Undoprodstrat;
	private FileShare fs, Undofs;
	private SimulationView sv, Undosv;
	private Map <String, SearchStrategy> strategyMap = new HashMap<>();
	
	private static final String sim = "Simulate";
	private static final String step = "step";
	private static final String done = "Done";
	private static final String undo = "undo";
	private static final String empty = "";
	
	public SimulationController() {
		fs = new FileShare();
		sv = new SimulationView();
		sv.addObserver(this);
		strategyMap.put("SearchByLikes", new SearchByLikes());
		strategyMap.put("SearchByFollowers", new SearchByFollowers());
		strategyMap.put("SearchByDistance", new SearchByDistance());
		strategyMap.put("SearchByLikeSimilarity", new SearchByLikeSimilarity());
		strategyMap.put("SearchByFollowersSimilarity", new SearchByFollowersSimilarity());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	public void update(Observable arg0, Object arg1) {
		ActionEvent ae = (ActionEvent) arg1;
		String cmd = ae.getActionCommand();
		if (cmd.equals(sim)) {
			if (sv.canSimulate()) {
				sv.disableButton();
				int prod = Integer.valueOf(sv.getNoProds());
				int con = Integer.valueOf(sv.getNoCons());
				fs.createProdAndCon(prod, con);
				fs.setUserTaste();
				Cycles = Integer.valueOf(sv.getNoCycles());
				sv.view2();
			} else	sv.showErrorDialog();
		}

		if (cmd.equals(step)) {
			UndoCycles=this.Cycles;
			UndoCurrentCycle=this.CurrentCycle;
			Undoprodstrat=this.prodstrat;
			Undofs=new FileShare(this.fs);
			
			if (sv.getSimUsSearchText().equals(empty))sv.showErrorDialog();
			else {
				CurrentCycle++;
				if (CurrentCycle > Cycles) {
					JFrame frame = new JFrame();
					JOptionPane.showMessageDialog(frame, "Simulation Ended", "Done", JOptionPane.ERROR_MESSAGE);
				} else {
					sv.addtoActivityFeed("Cycle " + CurrentCycle);
					User us = fs.selectRandomUser();
					sv.setSimUsidText(us.getUserID());
					sv.setSimUsTasteText(us.getTaste());
					fs.setStrategy(strategyMap.get(sv.getSelectedStrat()));
					
					UserController uc = new UserController(us,sv,prodstrat,CurrentCycle);
					uc.userActs(us);
				}
			}
		}
		
		if (cmd.equals(undo)) 
		{
			this.Cycles=UndoCycles;
			this.CurrentCycle=UndoCurrentCycle;
			this.prodstrat=Undoprodstrat;
			this.fs=fs;
		}
		
		if (cmd.equals(done))	prodstrat = sv.getLikebysame();

		// endupdate//
	}
	
	public static void main(String args[]) { new SimulationController(); }
>>>>>>> origin/Milestone4
}