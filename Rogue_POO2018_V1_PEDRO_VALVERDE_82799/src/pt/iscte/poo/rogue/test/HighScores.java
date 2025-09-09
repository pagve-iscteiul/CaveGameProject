package pt.iscte.poo.rogue.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class HighScores {

	public static final int MAX_SCORES = 10;
	
	private static final String SCORES_FILE = "pontuacoes.txt";
	
	private List<Score> scores = new ArrayList<Score>();
	
	public HighScores() throws FileNotFoundException{
		Scanner s = new Scanner(new File(SCORES_FILE));
		while(s.hasNextLine()){
			String[] tokens = s.nextLine().split(" ");
			if(tokens.length == 2){
				scores.add(new Score(tokens[0], Integer.parseInt(tokens[1])));
			}
		}
		s.close();
		Collections.sort(scores);
	}
	
	public boolean isInsertable(int p) {
		if(scores.size() < MAX_SCORES)
			return true;
		if(scores.get(scores.size() - 1).getPoints() < p)
			return true;
		return false;
	}

	public void insert(String name, int points) {
		assert isInsertable(points);
		
		scores.add(new Score(name, points));
		
		Collections.sort(scores);
		
		if(scores.size() > MAX_SCORES)
			scores.remove(scores.size() - 1);
	}
	
	@Override
	public String toString(){
		String s = "";
		String nl = System.getProperty("line.separator");
		for(Score x: scores)
			s += x + nl;
		return s;
	}

	public void save() throws FileNotFoundException{
		PrintWriter p = new PrintWriter(new File(SCORES_FILE));
		for(Score x: scores)
			p.println(x.getName() + " " + x.getPoints());
		p.close();
	}
	
		
	}
