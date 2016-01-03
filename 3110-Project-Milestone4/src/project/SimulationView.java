package project;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import org.jfree.data.xy.*;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;

/**
 * @author Hasan Issa
 * @author Obinna Elobi
 */
public class SimulationView extends Observable implements ActionListener {
	private String prodtaste;
	private static final String sametaste = "Like By Same Taste";
	private static final String differenttaste = "Like Different Taste";
	private static final String done = "Done";
	private static final String empty = "";
	private static final String undo = "undo";
	private boolean Likebysame;
	private String[] searchStrategies = { "SearchByLikes", "SearchByFollowers", "SearchByDistance",
			"SearchByLikeSimilarity", "SearchByFollowersSimilarity" };
	private JButton simbutton, stepthroughbutton, undoButton, saveButton, loadButton;
	private JTextField numProdText, prodText, numConsText, consText, numCycleText, cycletext;
	private JTextField taste;
	private JTextField usIDText, usTypeText, usTasteText, ussearchText;
	private JScrollPane scroll;
	private DefaultListModel<String> listModel;
	private JList list;
	private ChartPanel cp;
	private XYSeriesCollection ds;;
	private XYSeries series;
	private JFreeChart chart;
	private static final int rowno = 2;
	private JPanel ActivityFeed, Stepthru;
	private JDialog fr;
	private JComboBox Strategies;
	private JFrame j;

	/*
	 * First Window to enter the Number of USERS and cycles for the simulation
	 */

	public SimulationView() {
		JFrame f = new JFrame("Simulation Data");
		f.setSize(400, 300);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout grid1 = new GridLayout(4, 2);
		f.setLayout(grid1);
		
		simbutton = new JButton("Simulate");
		simbutton.addActionListener(this);

		numProdText = new JTextField();
		numProdText.setEditable(false);
		numProdText.setText("Number of Producers");

		prodText = new JTextField();
		prodText.setEditable(true);

		numConsText = new JTextField();
		numConsText.setEditable(false);
		numConsText.setText("Number of Consumers");

		consText = new JTextField();
		consText.setEditable(true);

		numCycleText = new JTextField();
		numCycleText.setEditable(false);
		numCycleText.setText("Number of Cycles");

		cycletext = new JTextField();
		cycletext.setEditable(true);

		f.add(numProdText);
		f.add(prodText);
		f.add(numConsText);
		f.add(consText);
		f.add(numCycleText);
		f.add(cycletext);

		f.add(simbutton);

		f.setVisible(true);
	}

	/*
	 * This Window opens after simulate is pressed, it displays information
	 * needed to walk through simulation
	 */

	public void view2() {
		j = new JFrame("Simulation");
		j.setSize(1000, 400);

		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout grid = new GridLayout();
		j.setLayout(grid);
		JLabel lab = new JLabel("Activity Feed");
		lab.setHorizontalAlignment(JLabel.CENTER);
		listModel = new DefaultListModel<String>();
		list = new JList(listModel);
		series = new XYSeries("Data");
		listModel.addElement("Simulation Started");

		scroll = new JScrollPane(list);

		// Setting up chart for payoff
		ds = new XYSeriesCollection();
		ds.addSeries(series);

		chart = ChartFactory.createXYLineChart("Payoff Chart", "Cycle", "Payoff", ds, PlotOrientation.VERTICAL, true,
				true, false);

		cp = new ChartPanel(chart);
		cp.setVisible(true);
		chart.setBorderVisible(true);

		// Setting Up Activity Feed on Gui
		ActivityFeed = new JPanel();
		ActivityFeed.setLayout(new BorderLayout());
		ActivityFeed.add(lab, BorderLayout.PAGE_START);
		ActivityFeed.add(scroll, BorderLayout.CENTER);

		// Setting up GUI elements to step through Simulation

		Stepthru = new JPanel();
		JPanel search = new JPanel();
		search.setLayout(new GridLayout(4, 2));
		Stepthru.setLayout(new BorderLayout());
		JLabel usID = new JLabel("Current User ID:");
		usIDText = new JTextField();
		usIDText.setEditable(false);
		JLabel usType = new JLabel("Current User Type:");
		usTypeText = new JTextField();
		usTypeText.setEditable(false);
		JLabel usTaste = new JLabel("Current User Taste:");
		usTasteText = new JTextField();
		usTasteText.setEditable(false);
		JLabel ussearch = new JLabel("Number of Documents to Search For:");
		ussearchText = new JTextField();
		ussearchText.setEditable(true);
		Strategies = new JComboBox(searchStrategies);
		Strategies.setActionCommand("strategy");
		Strategies.addActionListener(this);
		JLabel CBlabel = new JLabel("Select Search Strategy");
		undoButton = new JButton(undo);
		undoButton.setActionCommand(undo);
		undoButton.addActionListener(this);
		saveButton = new JButton("Save State");
		saveButton.setActionCommand("save");
		saveButton.addActionListener(this);
		loadButton = new JButton("Load State");
		loadButton.setActionCommand("load");
		loadButton.addActionListener(this);
		search.add(ussearch);
		search.add(ussearchText);
		search.add(usID);
		search.add(usIDText);
		search.add(usType);
		search.add(usTypeText);
		search.add(usTaste);
		search.add(usTasteText);

		JPanel last = new JPanel();
		last.setLayout(new GridLayout(3, 0));
		last.add(CBlabel);
		last.add(Strategies);
		stepthroughbutton = new JButton("Step Through");
		stepthroughbutton.setActionCommand("step");
		stepthroughbutton.addActionListener(this);
		last.add(stepthroughbutton);

		Stepthru.add(search, BorderLayout.CENTER);
		Stepthru.add(last, BorderLayout.PAGE_END);

		j.add(ActivityFeed);

		j.add(cp);
		j.add(Stepthru);

		last.add(undoButton);
		last.add(saveButton);
		last.add(loadButton);

		j.pack();

		j.setVisible(true);
	}

	/**
	 * window to let user decide producer strategy for liking
	 */
	public void producerStrategy() {
<<<<<<< HEAD
		JRadioButton rad = new JRadioButton("Like By Same Taste", true);
		JRadioButton rad2 = new JRadioButton("Like Different Taste", false);
=======
		JRadioButton rad = new JRadioButton(sametaste, true);
		JRadioButton rad2 = new JRadioButton(differenttaste, false);
>>>>>>> origin/Milestone4
		rad.addActionListener(this);
		rad2.addActionListener(this);
		taste = new JTextField();
		JButton ok = new JButton(done);
		ok.addActionListener(this);
		fr = new JDialog(j, "Producer Like Strategy", Dialog.ModalityType.APPLICATION_MODAL);
		fr.setSize(1000, 100);
		fr.setModal(true);
		ButtonGroup group = new ButtonGroup();
		group.add(rad);
		group.add(rad2);

		GridLayout grid = new GridLayout(3, 0);
		fr.getContentPane().setLayout(grid);
		fr.getContentPane().add(rad);
		fr.getContentPane().add(rad2);
		fr.getContentPane().add(taste);
		fr.getContentPane().add(ok);
		fr.pack();
		fr.setVisible(true);
	}

	/**
	 * @return the producer's taste
	 */
	public String getProdtaste() {	return prodtaste;	}

	/**
	 * @param cycle		current iteration
	 * @param payoff	user's payoff
	 */
	public void addtoData(double cycle, double payoff) {	series.add(cycle, payoff);	}

	/**
	 * @return true if liking by same taste
	 */
	public boolean getLikebysame() {	return Likebysame;	}

	/**
	 * @param element	user activity adds user activity to the activity feed
	 */
	public void addtoActivityFeed(String element) {	listModel.addElement(element);	}

	/**
	 * check if all values are inputted for simulation to be possible
	 * 
	 * @return true if all of the textfields for the producer,consumer and cycle aren't empty
	 */
	public boolean canSimulate() {
		if (prodText.getText().equals(empty) | 
				consText.getText().equals(empty) | cycletext.getText().equals(empty))return false;
		return true;
	}

	/**
	 * Set UserID in GUI
	 * @param s	user id 
	 */
	public void setSimUsidText(int s) {
		String str = "" + s;
		usIDText.setText(str);
	}

	/**
	 * @param s	user's taste
	 */
	public void setSimUsTasteText(String s) {	usTasteText.setText(s);	}

	/**
	 * @param s	type of user
	 */
	public void setSimUsTypeText(String s) { usTypeText.setText(s);	}

	/**
	 * Get k value to search for documents in GUI from user
	 * 
	 * @return value of maximum amount of documents user wants to search for
	 */
	public String getSimUsSearchText() { return ussearchText.getText();	}

	/**
	 * @return the number of producers inputted in gui by user
	 */
	public String getNoProds() { return prodText.getText();	}

	/**
	 * @return number of consumers inputted in gui by user
	 */
	public String getNoCons() { return consText.getText();	}

	/**
	 * @return number of cycles inputted in gui by user
	 */
	public String getNoCycles() { return cycletext.getText();	}

	/**
	 * @return selected strategy from jcombobox
	 */
	public String getSelectedStrat() { return (String) Strategies.getSelectedItem();	}

	/**
	 * disables simulate button
	 */
	public void disableButton() { simbutton.setEnabled(false);	}

	/**
	 * disable stepthrough
	 */
	public void disablestep() { stepthroughbutton.setEnabled(false);	}

	/**
	 * enable step through
	 */
	public void enablestep() { stepthroughbutton.setEnabled(true);	}

	/**
	 * Shows error dialog if any required parameters are missing
	 */
	public void deleteListItem() { 
		if(listModel.getSize()>1){
			listModel.remove(listModel.getSize()-1);
		}
	}
	public void showErrorDialog() {
		JOptionPane.showMessageDialog(fr, "Enter number of Documents to search for", "Input Error",
				JOptionPane.ERROR_MESSAGE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String cmd = e.getActionCommand();

<<<<<<< HEAD
		if (cmd.equals("Like By Same Taste"))			taste.setEditable(false);
		else if (cmd.equals("Like Different Taste"))	taste.setEditable(true);
		else if (cmd.equals("Done") && taste.isEditable() == true) 
		{
			if (taste.getText().equals(""))	showErrorDialog();
=======
		if (cmd.equals(sametaste))			taste.setEditable(false);
		else if (cmd.equals(differenttaste))	taste.setEditable(true);
		else if (cmd.equals(done) && taste.isEditable() == true) 
		{
			if (taste.getText().equals(empty))	showErrorDialog();
>>>>>>> origin/Milestone4
			else {
				Likebysame = false;
				
				setChanged();
				notifyObservers(e);
				prodtaste = taste.getText();
				fr.setVisible(false);
				fr.dispose();
			}
		} else if (cmd.equals(done) && taste.isEditable() == false) {
			Likebysame = true;

			setChanged();
			notifyObservers(e);
			fr.setVisible(false);
			fr.dispose();
		} else {
			setChanged();
			notifyObservers(e);
		}
	}
}