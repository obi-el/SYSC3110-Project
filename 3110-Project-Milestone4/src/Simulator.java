/**
 * Created by Home on 2015-10-17.
 */
import java.util.*;
	/**
	 * This class simulates the FileShare class
	 * 
	 * @author Craig Isesele
	 */
	public class Simulator {
	  /*  FileShare fs;
	    public Simulator()
	    {
	        fs = new FileShare();
	    }
	*/    
    
    /*
     * Makes user inputted number of producers and adds them to the social network
     */
    
    public static  void createNproducers(int n, FileShare Fil){
    	for(int i = 0; i < n ; i++){
    		Scanner no = new Scanner(System.in);
    		System.out.println("Type in taste for new producer: ");
    		String taste = no.next();
    		Producer Prod = new Producer(taste,Fil);
    		Fil.addUser(Prod);
    	}    	
    	
    }
    
    
    /*
     * Makes user inputted number of Consumers and adds them to the social network
     */
    
    public static void createNconsumers(int n, FileShare Fil){
    	for(int i = 0; i < n ; i++){
    		Scanner no = new Scanner(System.in);
    		System.out.println("Type in taste for new consumer: ");
    		String taste = no.next();
    		Consumer con = new Consumer(taste,Fil);
    		Fil.addUser(con);
    		//no.close();
    	}    	
    	
    }

    public static void main (String args[])
    {
    	FileShare fs = new FileShare();
    	Scanner scan = new Scanner(System.in);
    	System.out.println("Input number of Producers: ");
    	int prod = scan.nextInt();
    	createNproducers(prod,fs);
    	
    	System.out.println("Input number of Consumers: ");
    	int cons = scan.nextInt();
    	createNconsumers(cons,fs);
    	
    	System.out.println("Input number of Cycles of simulation: ");
    	int c = scan.nextInt();
        
    	Random ran = new Random();
        for(int n = 0; n<c;n++)
        {
            System.out.println("Simulation Cycle " + n + "\n");
            int index = ran.nextInt(fs.getUsers().size());
            Users u = fs.getUser(index);
            if (u instanceof Producer) ((Producer) u).act();
            else if (u instanceof Consumer) ((Consumer) u).act();
        }        
        scan.close();
    } 
}