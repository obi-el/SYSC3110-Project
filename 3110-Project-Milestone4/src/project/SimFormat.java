package project;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

public class SimFormat implements java.io.Serializable{
	private static final long serialVersionUID = -3285954589745793699L;
	public boolean Prodstrat;
	public int cycles;
	public int currentCycles;
	public SimFormat(){
		Prodstrat = false;
		cycles = 0;
		currentCycles = 0;
	}
	public SimFormat (boolean p, int c, int cc){
		Prodstrat = p;
		cycles = c;
		currentCycles = cc;
	}
	@SuppressWarnings("resource")
	public static void save(SimFormat data) throws IOException{
			File SelectedFile = new File("state.dat");
			if(!SelectedFile.exists()){
				SelectedFile.createNewFile();
			}
			FileOutputStream out = new FileOutputStream(SelectedFile);
			ObjectOutputStream stream = new ObjectOutputStream (out);
			stream.writeObject (data);
			System.out.println("State saved in " + SelectedFile.getPath());
	}
	public static SimFormat load () throws IOException, ClassNotFoundException{
		JFileChooser fc = new JFileChooser();
			File file = new File("state.dat");
			if(file.exists()){
			FileInputStream in = new FileInputStream(file);
			ObjectInputStream stream = new ObjectInputStream (in);
			System.out.println("State loaded from " + file.getPath());
			return (SimFormat) stream.readObject();
			}
			else{
				System.out.println("State not found");
				return null;
			}

	}
}
