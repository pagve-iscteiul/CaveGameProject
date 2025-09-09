package pt.iscte.poo.rogue.objects;

import java.util.ArrayList;
import java.util.List;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

/**
 * 
 * @author pedro
 * 
 */
public abstract class RogueObject implements ImageTile {

	/**
	 * position Position
	 */
	private Point2D position;
	
	/**
	 * x coordinate 
	 */
	private int x;
	
	/**
	 * y coordinate 
	 */
	private int y;

	/** Creates a new RogueObject using a position
	 *  
	 *  @param position position Position
	 */
	public RogueObject(Point2D position) {
		this.position = position;
	}
	
	/** Creates a new RogueObject using two coordinates
	 * 
	 * @param x x coordinate
	 * @param y y coordinate
	 */
	public RogueObject(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * @return position Position
	 */
	@Override
	public Point2D getPosition() {
		return position;
	}
	
	/**
	 * @return x coordinate
	 */
	public int getX(){
		return x;
	}
	
	/**
	 * @return y coordinate
	 */
	public int getY(){
		return y;
	}
	
	/**
	 * @param newPosition
	 */
	protected void setPosition(Point2D newPosition) {
		this.position = newPosition; 
	}
	
	/**
	 * @param x
	 */
	protected void setX(int x){
		this.x = x;
	}
	
	/**
	 * @param y
	 */
	protected void setY(int y){
		this.y = y;
	}

	/** 
	 * 
	 * @return a string with the name of the RogueObject
	 */
	@Override
	public String getName() {
		return getClass().getSimpleName();
	}

	/**
	 * @param line Each line of the file
	 * @return a list with created RogueObjects
	 */
	public static List<RogueObject> create(String line) {
		System.out.println(line);
		List<RogueObject> createdObjects = new ArrayList<>();
		String[] tokens = line.split(" ");
		String type = tokens[0];
		
		String xiStr = "";
		String xfStr = "";
			
		if(tokens[1].contains(":")){
			xiStr = tokens[1].substring(0, tokens[1].indexOf(":"));
			xfStr = tokens[1].substring(tokens[1].indexOf(":") + 1, tokens[1].indexOf(","));
		} else {
			xiStr = tokens[1].substring(0, tokens[1].indexOf(","));
			xfStr = xiStr;
		}
			
		String yiStr = "";
		String yfStr = "";
			
		if(tokens[2].contains(":")){
			yiStr = tokens[2].substring(0, tokens[2].indexOf(":"));
			yfStr = tokens[2].substring(tokens[2].indexOf(":") + 1);
		} else {
			yiStr = tokens[2];
			yfStr = yiStr;
		}
			
		int xi = Integer.parseInt(xiStr);
		int xf = Integer.parseInt(xfStr);
		int yi = Integer.parseInt(yiStr);
		int yf = Integer.parseInt(yfStr);
			
		for(int x = xi; x <= xf; x++){
			for(int y = yi; y <= yf; y++){
					
				if("wall".equals(type)){
					createdObjects.add(new Wall(new Point2D(x,y)));
				} else if("floor".equals(type)){
					createdObjects.add(new Floor(new Point2D(x,y)));
				} else if("bat".equals(type)){
					createdObjects.add(new Bat(new Point2D(x,y)));
				} else if("skeleton".equals(type)){
					createdObjects.add(new Skeleton(new Point2D(x,y)));
				} else if("sword".equals(type)){
					createdObjects.add(new Sword(new Point2D(x,y)));
				} else if("trap".equals(type)){
					createdObjects.add(new Trap(new Point2D(x,y)));
				} else if("hammer".equals(type)){
					createdObjects.add(new Hammer(new Point2D(x,y)));
				} else if("thief".equals(type)){
					createdObjects.add(new Thief(new Point2D(x,y)));
				} else if("key".equals(type)){
					createdObjects.add(new Key(new Point2D(x,y)));
				} else {
					if("door".equals(type)){
						if(tokens.length <= 8){
							createdObjects.add(new DoorOpen(new Point2D(x,y)));
						} else {
							createdObjects.add(new DoorClosed(new Point2D(x,y)));
						}
					}
				}
			}
		}
		return createdObjects;
	}
}