package pt.iscte.poo.rogue.test;

import java.io.FileNotFoundException;

import javax.swing.JOptionPane;

public class ScoresTest {
	
	public static void main(String[] args){
		
		int p = 256;
		try{
			
			HighScores h = new HighScores();
			
			boolean shouldInsert = h.isInsertable(p);
			
			if(shouldInsert){
				String name = JOptionPane.showInputDialog("Nome do jogador?");
				h.insert(name, p);
			}
			
			System.out.println(h);
			
			h.save();
			
		} catch(FileNotFoundException e){
			System.out.println("Não é possível gravar");
		}
	}
}
