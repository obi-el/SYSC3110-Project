package project;
import java.util.*;

/**
 * @author Hasan Issa 
 * @author Craig Isesele
 * ranks documents by checking if the poster likes similar documents the searcher likes
 */
public class SearchByLikeSimilarity extends SearchStrategy {
	/* (non-Javadoc)
	 * @see project.SearchStrategy#returnDocs(int, project.User)
	 */
	@Override
	public List<Document> returnDocs(int k, User user) {
		// get all the documents that my user likes
		Set<Document> userLikedDocuments = new HashSet<Document>();
		Set<User> similarLikers = new HashSet<User>();
		Set<Document> unlikedDocuments = new HashSet<>();
		for (Document d : user.getFileShare().getDocuments()) {
			for (User liker : d.getLikers()) {
				if (liker == user) {
					userLikedDocuments.add(d);
				}
			}
		}
		// get users that like the same documents
		for (Document d : user.getFileShare().getDocuments()){ similarLikers.addAll(d.getLikers()); }

		// get all the documents that similarLikers liked that my user didnt
		// like

		for (Document d : user.getFileShare().getDocuments()) {
			for (User liker : similarLikers) {
				for (User documentLiker : d.getLikers()) {
					if (documentLiker == liker && !(userLikedDocuments.contains(d))) 
						unlikedDocuments.add(d);
				}
			}
		}

		ArrayList<Document> listToReturn = new ArrayList<Document>();
		Iterator<Document> itr = unlikedDocuments.iterator();
		for (int i = 0; i < k; i++) {
			if (itr.hasNext()) listToReturn.add(itr.next());
			else return listToReturn;
		}
		return listToReturn;
	}
}