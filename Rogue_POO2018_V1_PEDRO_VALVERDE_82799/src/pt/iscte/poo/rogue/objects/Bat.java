package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Bat extends RogueObject implements InteractableRogueObject, MovingRogueObject{
	
	private int vida = 3;
	
	public Bat(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}
	
	public void move(Direction d){
		Point2D nextP = getPosition().plus(d.asVector());
		if(!interactable(nextP))
			setPosition(nextP);
	}
	
	@Override
	public void move() {
		double r = Math.random() * 2;
		double r1 = Math.random() * 2;
		int x = getPosition().getX();
		int y = getPosition().getY();
		RogueObject s = Rogue.getInstance().getHero();
		int x1 = s.getPosition().getX();
		int y1 = s.getPosition().getY();
		int i = 0;
		int j = 0;
		if ((int)r == 1) { // em direção ao heroi
			if((int)r1 == 1){
				if(x1 > x){
					x++;
				}
				if(x1 < x){
					x--;
				}
				
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
				
			}
			else {
				if(y1 > y){
					y++;
				}
				if(y1 < y){
					y--;
				}
				
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
				}
			
		} else { // se for aleatorio
			if((int)r == 1){
				if((int)r1 == 1){
					i = -1;
				} else {
					i = 1;
				}
					
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
				
			} else {
				if((int)r1 == 1){
					j = -1;
				} else {
					j = 1;
				}
					
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
				}
			}
	}

	// TODO: What does it do to objects in that position?
	

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public boolean interactable(Point2D p) {
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hero){
				if(o.getPosition().equals(p)){
					System.out.println(1);
					((InteractableRogueObject) o).DiminuiVida(1);
					return true;
				}
			}
			if(o instanceof Skeleton || o instanceof Wall || o instanceof Thief || o instanceof DoorOpen || o instanceof DoorClosed){
				if(o.getPosition().equals(p)){
					return true;	
				}
			}	
		}
		return false;
	}

	@Override
	public int vida() {
		// TODO Auto-generated method stub
		return vida;
	}

	@Override
	public void DiminuiVida(int i) {
		vida = vida - i;
		if(vida <= 0){
			Rogue.getInstance().removeObjects(this);
		}
		System.out.println("vidaBat");
	}
}
				