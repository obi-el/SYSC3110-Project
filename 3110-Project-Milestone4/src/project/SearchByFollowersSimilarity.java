package project;
import java.util.*;
import java.util.Map.Entry;

/**
 * @author Hasan Issa  
 * 
 * @author Craig Isesele 
 * ranks documents by checking if the poster follows similar
 *         people the searcher follows
 */
public class SearchByFollowersSimilarity extends SearchStrategy {

	/*
	 * (non-Javadoc)
	 * 
	 * @see project.SearchStrategy#returnDocs(int, project.User)
	 */
	@Override
	public List<Document> returnDocs(int k, User user) {
		Map<User, Integer> numOfSimilarFollowersMap = new HashMap<>();
		List<Document> returnedDocuments = new ArrayList<>();
		for (User allUser : user.getFileShare().getUsers()) {
			int counter = 0;
			for (User followingUser : allUser.getFollowing()) {
				for (User myUserFollowing : user.getFollowing()) {
					if (myUserFollowing == allUser)
						counter++;
				}
			}
			numOfSimilarFollowersMap.put(allUser, counter);
		}

		List<Entry<User, Integer>> sortedList = new ArrayList<>(numOfSimilarFollowersMap.entrySet());
		sortedList.sort(new Comparator<Entry<User, Integer>>() {
			@Override
			public int compare(Entry<User, Integer> o1, Entry<User, Integer> o2) {
				if (o1.getValue() == o2.getValue())
					return 0;
				else if (o1.getValue() > o2.getValue())
					return 1;
				else
					return -1;
			}

		});

		Iterator<Entry<User, Integer>> itr = sortedList.iterator();
		while (itr.hasNext()) {
			User user1 = itr.next().getKey();
			for (Document d : user.getFileShare().getDocuments()) {
				if (d.getPoster() == user1) {
					k--;
					returnedDocuments.add(d);
					if (k == 0)
						return returnedDocuments;
				}
			}
		}
		return returnedDocuments;
	}
}