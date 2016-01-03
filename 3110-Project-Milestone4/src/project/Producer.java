package project;
import java.util.*;

/**
 * Producers produce documents that match their taste Producers typically like
 * their own documents, and others that match their taste They can also follow
 * users that like them Doing this helps to make documents they post more
 * popular
 * 
 * @author Obinna Elobi
 */
public class Producer extends User implements java.io.Serializable{

	private ArrayList<Document> posted = new ArrayList<Document>(); 
	int nofollwr = 0; // no of followers from past simulation cycle

	/**
	 * @param userTaste	the user's taste
	 * @param fs	the social network the user belongs to
	 */
	public Producer(String userTaste, FileShare fs) 
	{
		super(userTaste, fs);
	}

	/**
	 * makes a new document and posts it to the social network, he then likes it
	 * and it is added to an arraylist of documents he made. He then searches
	 * the social network for the top k posts, k is randomly generated between 0
	 * - 11 (11 exclusive) He then looks through the result from the search and
	 * likes the documents with similar tastes to his and also follows users
	 * that liked those same documents. payoff is calculated and posted
	 * 
	 * @param docs	list of documents returned from search
	 */
	public void likeSameTaste(List<Document> docs) {
		for (Document d : docs) {
			if (d == null)
				return;
			if (d.getDocumentTag().equals(userTaste)) {
				likeDocument(this, d);
				for (User user : d.getLikers())
					followUser(user);
			}
		}
	}

	/**
	 * @param taste	producer's new taste
	 * @param docs	list of documents returned by search
	 */
	public void likeDifferentTaste(String taste, List<Document> docs) {
		for (Document d : docs) {
			if (d == null)
				return;
			if (d.getDocumentTag().equals(taste)) {
				likeDocument(this, d);
				for (User user : d.getLikers())
					followUser(user);
			}
		}
	}

	protected void payoffMessage()
	{
		this.messages.add("producer " + getUserID() + "payoff is now " + getPayoff());
	}
	
	/**
	 * 
	 */
	public void act() {
		// int pays = 0;
		// int nofollwr = 0;
		Document po = new Document(userTaste, this);
		fs.addDocument(po);
		likeDocument(this, po);
		posted.add(po);
		payoff = 0;
	}
}