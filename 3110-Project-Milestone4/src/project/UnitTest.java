package project;
import java.util.Random;
import junit.framework.TestCase;

public class UnitTest extends TestCase
{
	private FileShare fx = null;
	private Consumer Kent,Cole,Jenny,Marrie;
	private Document Pop,Rock = null;
	private Producer Thunder, Speed = null;
	//private Simulator Sim = null;
	int n = 0;
	Random ran = null;
	
	protected void setUp() throws Exception{
		fx = new FileShare();
		Kent = new Consumer("Jazz", fx);
		Cole = new Consumer("Rock", fx);
		Jenny = new Consumer("Rock", fx);
		Marrie = new Consumer("Pop", fx);
		Speed = new Producer("Rock", fx);
		Thunder = new Producer("Pop", fx);
		Rock = new Document("Rock", Speed);
		Pop = new Document("Pop", Thunder);
		fx.addUser(Thunder);
		fx.addUser(Speed);
		fx.addUser(Marrie);
		fx.addUser(Jenny);
		fx.addUser(Cole);
		fx.addUser(Kent);
		n=2;
		//Sim = new Simulator();
	}
	
	/**
	 * test if producers can create documents 
	 */
	public void testActV(){
		Speed.act();
		Thunder.act();
		assertEquals("Did not Create the Document Rock", Speed.getTaste(), "Rock");
		assertEquals("Did not Create the Document Pop", Thunder.getTaste(), "Pop");
	}
	
	/**
	 * test if users can follow other users 
	 */
	public void testAddFollowerV(){
		Kent.followUser(Cole);
		assertEquals("Could not Follow User Cole", 1, Cole.followers.size());
	}
	

	/**
	 *test if multiple users can follow a user 
	 */
	public void test2AddFollowerV(){
		Kent.followUser(Cole);
		Marrie.followUser(Cole);
		assertEquals("Kent and Marrie are not following Cole",2,Cole.followers.size());
	}
	
	/**
	 * test if a user can like a document 
	 */
	public void testLikeDocumentV(){
		Jenny.likeDocument(Jenny, Rock);
		assertEquals("Like attempt failed", 1, Rock.getNumberOfLikes());
	}
	
	
	/**
	 * test if multiple users liked a user's document 
	 */
	public void test2LikeDocumentV(){
		Jenny.likeDocument(Jenny, Rock);
		Kent.likeDocument(Kent, Rock);
		assertEquals("Like attempt failed", 2, Rock.getNumberOfLikes());
	}
	
	public void tearDown() throws Exception {}
	
	/**
	 * test if the social network works without accessing the actual simulation 
	 */
	public void testConsole() {
		fx.createProdAndCon(n,n);
		Random ran = new Random();
        	for(int n = 0; n<10;n++)
        	{
	            System.out.println("Simulation Cycle " + n + "\n");
	            int index = ran.nextInt(fx.getUsers().size());
	            User u = fx.getUser(index);
	            System.out.println("Index: " + u);
        	}
	}
}