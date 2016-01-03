package project;
import java.util.*;
/**
 * @author Hasan Issa
 * @author Craig ISesele 
 * rank the documents by the number of followers the
 * poster has
 */
public class SearchByFollowers extends SearchStrategy implements java.io.Serializable{
	/*
	 * (non-Javadoc)
	 * 
	 * @see project.SearchStrategy#returnDocs(int, project.User)
	 */
	@Override
	public List<Document> returnDocs(int k, User user) {
		List<Document> highestRanked = user.getFileShare().getDocuments();
		final Comparator<Document> followers = new Comparator<Document>() {
			public int compare(Document d1, Document d2) {
				Integer numberOfFollowers = (Integer) d1.getPoster().getNumberOfFollowers();
				return numberOfFollowers.compareTo(d2.getPoster().getNumberOfFollowers());
			}
		};
		Collections.sort(highestRanked, followers);
		return removeElems(highestRanked, k);
	}
}