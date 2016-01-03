package project;
import java.util.*;

/**
 * This class is used to create documents which have randomly generated IDs and
 * specific tags Documents are posted by producers (a type of user) The tag of a
 * document is the taste of the producer that posted it The document also
 * maintains a list of users that like it
 * 
 * @author Craig Isesele
 * @author Obinna Elobi
 * @author Hasan Issa
 */
public class Document implements Comparable<Document>, java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4686949229978132455L;
	private ArrayList<User> Likers;
	private int DocumentID;
	private String documentTag;
	private Random rn = new Random();
	private User poster;
	private static int maxNoOfDocs = 1000;

	public Document(String documentTag, User poster) {
		this.DocumentID = rn.nextInt(maxNoOfDocs);
		this.documentTag = documentTag;
		Likers = new ArrayList<User>();
		this.poster = poster;
	}

	/**
	 * @return number of people who like a particular document
	 */
	public int getNumberOfLikes() {
		return Likers.size();
	}

	/**
	 * @param user
	 *            a given user that supposedly likes the current document
	 * @return true if user was successfully added to list of those who like a
	 *         given document
	 */
	public boolean addLikers(User user) {
		return Likers.add(user);
	}

	/**
	 * @return the Document's tag
	 */
	public String getDocumentTag() {
		return documentTag;
	}

	/**
	 * @return the amount of people who like a given document
	 */
	public ArrayList<User> getLikers() {
		return Likers;
	}

	/**
	 * @return the Document's ID
	 */
	public int getDocumentID() {
		return DocumentID;
	}

	/**
	 * @return the poster
	 */
	public User getPoster() {
		return poster;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Document document) {
		Integer native_likes = (Integer) getNumberOfLikes();
		return native_likes.compareTo(document.getNumberOfLikes());
	}
}