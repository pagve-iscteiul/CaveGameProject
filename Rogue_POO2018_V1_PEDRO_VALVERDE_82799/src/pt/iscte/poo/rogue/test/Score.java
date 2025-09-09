package pt.iscte.poo.rogue.test;

public class Score implements Comparable<Score>{

	private String name;
	private int points;
	
	public Score(String name, int points) {
		this.name = name;
		this.points = points;
	}

	public String getName(){
		return name;
	}
	
	public int getPoints() {
		return points;
	}

	@Override
	public String toString(){
		return "Score[name=" + name + ", points=" + points + "]";
	}
	
	@Override
	public int compareTo(Score s) {
//		if(getPoints() > s.getPoints())
//			return 1;
//		if(getPoints() < s.getPoints())
//			return -1;
//		return 0;
		// Decrescente
		return s.getPoints() - getPoints();
	}

	
}
