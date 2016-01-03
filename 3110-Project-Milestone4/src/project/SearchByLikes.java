package project;
import java.util.*;


/**
 * @author Hasan Issa
 * @author Craig Isesele
 * The documents are ranked by the number of likes they have
 */
public class SearchByLikes extends SearchStrategy implements java.io.Serializable{
	/** (non-Javadoc)
	 * @see project.SearchStrategy#returnDocs(int, project.User)
	 */
	@Override
	public List<Document> returnDocs(int k, User user) {
		List<Document> highestRanked = user.getFileShare().getDocuments();
		Collections.sort(highestRanked);
		System.out.println(highestRanked.size());
		return removeElems(highestRanked, k);
	}
}