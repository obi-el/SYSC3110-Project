package project;
import java.util.*;
import java.util.Map.Entry;

/**
 * A user can be of type producer or consumer
 * The user can like documents that match the user's taste
 * A list of users who a user follows or who follow a user is also maintained
 * Users are given randomly generated IDs for identification
 * This removes the need for user name input
 * @author Obinna Elobi
 */

public abstract class User {
    protected int userid;
    protected String userTaste;
    protected List<User> following, followers;
    protected int payoff = 0;
    protected Random rn = new Random();   // to generate random int
    protected FileShare fs;   
    private int idMax = 1000;
    private SearchStrategy strategy;
	protected ArrayList<String> messages;
	
    /**
     * Initialize user and add them to a social network
     * @param userTaste		the user's taste
     * @param fs	the social network the user belongs to
     */
    public User(String userTaste, FileShare fs){
    	setSearchStrategy(fs.getStrategy());
        this.userid = rn.nextInt(idMax);
        this.userTaste = userTaste;
        following = new ArrayList<User>();
        followers = new ArrayList<User>();
        this.fs = fs;
        messages = new ArrayList<String>();
    }
<<<<<<< HEAD

    protected void notify(SimulationView sv)
    {
		for (String str :this.messages) { sv.addtoActivityFeed(str); }
		this.clearMessages();
    }
=======
>>>>>>> origin/Milestone4

    /**
     * @param strategy	search strategy to be implemented
     */
    public void setSearchStrategy (SearchStrategy strategy){ this.strategy = strategy; }
        
    /**
     * @return	the social network that the user belongs to
     */
    public FileShare getFileShare(){ return fs; }
    
	/**
	 * @return	the list of users the current user follows
	 */
	public List<User> getFollowing(){ return following; }

	/**
	 * @param following	the list of users that the current user follows
	 */
	public void setFollowing(List<User> following) { this.following = following; }
	
    /**
     * @return the list of users that follow the current user
     */
    public List<User> getFollowers() { return followers; }

	/**
	 * @param followers	the list of users that follow the current user
	 */
	public void setFollowers(List<User> followers) { this.followers = followers; }
	
    /**
     * whenever a user is followed it adds you to their followers arraylist if you're not already on there and adds
     * them to your following arraylist
     * @param user user that current user intends to follow
     * 
     */
    protected void followUser(User user) {
        if(!(following.contains(user))){
            this.following.add(user);
            user.followers.add(user);
            System.out.println(userid + "followed " + user.getUserID() + "\n");
            messages.add(userid + "followed " + user.getUserID());
        }
        else return;
    }

    /**
     * @return the current user's taste
     */
    public String getTaste(){ return userTaste; }
    
    /**
     * @param taste	the user's new taste
     */
    public void setTaste(String taste){ userTaste = taste; }

    /**
     * @return the user's id
     */
    public int getUserID() { return userid; }
		
	/*
	 * checks if you already liked the document, if not you are added to the arraylist
	 * of likers and the original posters'(the producer who posted this) payoff is increased 
	 * by one
	 */
    
    /**
     * @param user	the current user
     * @param d	document that the user wants to like
     */
    protected void likeDocument(User user, Document d) {
        if(d.getLikers().contains(user)) return;
        else {
            d.getPoster().payoff++;  // producer payoff increments everytime consumer likes document 
            d.addLikers(user);
            System.out.println( user.getUserID() + " liked doc:" + d.getDocumentTag() + " ID:" + d.getDocumentID() + "\n");
            messages.add(user.getUserID() + " liked doc: " + d.getDocumentTag() + " ID:" + d.getDocumentID());
        }
    }

    public void clearMessages(){ messages.clear(); }
    
    /**
     * @return the user's payoff
     */
    public int getPayoff(){ return payoff; }

	/**
	 * @return the number of followers a user has
	 */
	public int getNumberOfFollowers() { return followers.size(); }
}