package project;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Hasan Issa
 * @author Craig Isesele 
 * ranks documents by checking if the poster of a given
 *         document is the searcher's follower or someone the searcher follows
 */
public class SearchByDistance extends SearchStrategy implements java.io.Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6490120851058686740L;

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.SearchStrategy#returnDocs(int, project.User)
	 */
	@Override
	public List<Document> returnDocs(int k, User user) {

		List<Document> returnedDocuments = new ArrayList<Document>();
		Set<User> friends = new HashSet<>();
		for (Document d : user.getFileShare().getDocuments()) {
			friends.addAll(user.getFollowers());
			friends.addAll(user.getFollowing());
			for (User u : friends) {
				if (d.getPoster() == u) {
					returnedDocuments.add(d);
					if (returnedDocuments.size() == k)
						return returnedDocuments;
				}
			}
		}
		return returnedDocuments;
	}

}
