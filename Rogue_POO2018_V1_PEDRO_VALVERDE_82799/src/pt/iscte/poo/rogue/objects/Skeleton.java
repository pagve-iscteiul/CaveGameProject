package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Skeleton extends RogueObject implements InteractableRogueObject, MovingRogueObject{

	private int vida = 5;
	private int r = 0;
	
	public Skeleton(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}
	
	public void move(Direction d) {
		Point2D nextP = getPosition().plus(d.asVector());
		if(!interactable(nextP))
			setPosition(nextP);
	}
	
	@Override
	public void move() {
		double n = Math.random() * 2;
		int x = getPosition().getX();
		int y = getPosition().getY();
		RogueObject s = Rogue.getInstance().getHero();
		int x1 = s.getPosition().getX();
		int y1 = s.getPosition().getY();
		if(r%2 == 0){
			if((int)n == 1){
				if(x1 > x){
					x++;
				}
				if(x1 < x){
					x--;
				}
					
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
				System.out.println(0);
				r = 1;
						
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
				System.out.println(0);
				r = 1;
			}
		} else {
			System.out.println(1);
			r = 0;
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
					((InteractableRogueObject) o).DiminuiVida(1);
					return true;
				}
			}
			if(o instanceof Bat || o instanceof Wall || o instanceof Thief){
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
		System.out.println("vidaSkeleton");
	}
}