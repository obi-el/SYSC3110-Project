package project;
import java.util.*;

/**
 * Consumers are a type of user They are able to search for the top 'k'
 * documents based on some criteria The relevance of the documents based on if
 * the document's tag matches up their taste is calculated (payoff) Consumers
 * like documents returned by the search whose tag matches their taste They may
 * also follow users that liked documents they like
 * 
 * @author Obinna Elobi
 * @author Hasan Issa
 */

public class Consumer extends User implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9129451632214874893L;

	/**
	 * @param userTaste	the user's taste
	 * @param fs	the social network the user belongs to
	 */
	public Consumer(String userTaste, FileShare fs) {
		super(userTaste, fs);
	}

	/**
	 * goes through the search results and counts how many of them have have not
	 * already been liked i.e how much it pays, which sums up our version of a
	 * "payoff"
	 * 
	 * @param doc	list of documents returned by a given search
	 * @return the payoff for the consumer
	 */
	private int calculatePayoff(List<Document> doc) {
		int pays = 0;
		for (Document d : doc) {
			if (!(d.getLikers().contains(this)))
				pays++;
		}
		payoff += pays;
		return pays;
	}

	/**
	 * Searches the social network for the top k documents, calculates the
	 * payoff from the search results likes documents with similar tastes and
	 * follows users that liked them also
	 * 
	 * @param liked	document that consumer liked
	 */
	public void act(List<Document> liked) {
		messages.add("last search by cosumer " + userid + " paid " + calculatePayoff(liked));
		for (Document d : liked) {
			if (d == null)
				return;
			if (d.getDocumentTag().equals(userTaste)) {
				likeDocument(this, d);
				for (User user : d.getLikers())
					followUser(user);

			}
		}
	}
}