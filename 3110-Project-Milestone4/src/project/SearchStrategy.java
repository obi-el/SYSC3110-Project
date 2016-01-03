package project;
import java.util.*;

public abstract class SearchStrategy 
{
    /**
     * @author Hasan Issa
     * 
     * Search for a list of documents and rank them by a specific criteria
     * @param documents	list of documents to be sized down
     * @param restriction	size of expected list of documents
     * @return a list of documents with a size of 'restriction'
     */
    public List<Document> removeElems(List<Document> documents,int restriction)
    {
    	if(documents.size() <= restriction) return documents;
    	else 
    	for(int i = documents.size()-1; i>restriction;i--)
    	{
    		documents.remove(i);
    	}
    	return documents;
    }
    
	/**
	 * @param k value used to restrict number of documents
	 * @param user user that wants the documents returned
	 * @return top k documents that user searched for me
	 */
	public abstract List<Document> returnDocs(int k, User user);
}