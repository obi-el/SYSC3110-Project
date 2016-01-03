package project;
import java.util.*;
import java.util.Map.Entry;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * @author Craig Isesele
 * @author Hasan Issa
 */
public class FileShare implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1864614334583755924L;
	private List<User> users;
	private List<Document> documents;
	private SearchStrategy strategy;

	/**
	 * By default FileShare uses the SearchByLikes strategy
	 * 
	 * @see SearchByLikess
	 */
	public FileShare() {
		this(new SearchByLikes());
	}

	/**
	 * Copy Constructor
	 * @param fs	Social Network instance to be copied
	 */
	public FileShare(FileShare fs) {
		this.users = new ArrayList<>(fs.users);
		this.documents = new ArrayList<>(fs.documents);
		this.strategy = fs.strategy;

	}

	/**
	 * @param strategy
	 *            the starting strategy the social network is to use
	 */
	public FileShare(SearchStrategy strategy) {
		users = new ArrayList<>();
		documents = new ArrayList<>();
		this.strategy = strategy;
	}

	/**
	 * @param strategy
	 *            strategy that fileshare will implement
	 */
	public void setStrategy(SearchStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * @return the current search strategy of the social network
	 */
	public SearchStrategy getStrategy() {	return strategy;	}

	/**
	 * @param k		value used to restrict number of returned documents
	 * @param u		user searching for documents
	 * @return the top k documents
	 */
	public List<Document> search(int k, User u) {	return strategy.returnDocs(k, u);	}

	/**
	 * @param user		user that wants to join social network
	 */
	public void addUser(User user) {	users.add(user);	}

	/**
	 * @return a random user from the list of users
	 */
	public User selectRandomUser() {
		Collections.shuffle(users);
		return users.get(0);
	}

	/**
	 * @param n		number of producers to be generated
	 * @return an arraylist of n producers
	 */
	private ArrayList<User> createNproducers(int n) {
		ArrayList<User> newusers = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Producer Prod = new Producer(null, this);
			newusers.add(Prod);
		}
		return newusers;
	}

	/**
	 * @param n		number of consumers to be generated
	 * @return return an arraylist of n producers
	 */
	private ArrayList<User> createNconsumers(int n) {
		ArrayList<User> newusers = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Consumer con = new Consumer(null, this);
			newusers.add(con);
		}
		return newusers;
	}

	protected void createProdAndCon(int prod, int con) {
		users.addAll(createNproducers(prod));
		users.addAll(createNconsumers(con));
	}

	protected boolean checkIfConsumer(User u) {
		if (u instanceof Consumer)
			return true;
		return false;
	}

	protected boolean checkIfProducer(User u) {
		if (u instanceof Producer)
			return true;
		return false;
	}

	/**
	 * @return the list of users
	 */
	protected List<User> getUsers() {
		return users;
	}

	/**
	 * @param i
	 *            index of specific user
	 * @return user at index i
	 */
	protected User getUser(int i) { return users.get(i); }

	/**
	 * @param id
	 *            id of a specific user
	 * @return user if supplied id matches his
	 */
	protected User getUserFromID(int id) {
		for (User u : users) {
			if (u.getUserID() == id)
				return u;
		}
		return null;
	}
	
	protected void setUserTaste()
	{
		JFrame frame;
		String s;
		for (User u : this.getUsers()) {
			if (this.checkIfProducer(u)) 
			{
				frame = new JFrame();
				s = (String) JOptionPane.showInputDialog(frame, "Input Producer Taste");
				u.setTaste(s);
			}
			else if (this.checkIfConsumer(u)) 
			{
				frame = new JFrame();
				s = (String) JOptionPane.showInputDialog(frame, "Input Consumer Taste");
				u.setTaste(s);
			}
		}

	}

	/**
	 * @param document
	 *            document to be added to list of documents in social network
	 */
	public void addDocument(Document document) {
		documents.add(document);
	}

	/**
	 * @return the list of documents
	 */
	public List<Document> getDocuments() {
		return documents;
	}
}