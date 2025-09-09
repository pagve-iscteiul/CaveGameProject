package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Point2D;

public class Hammer extends RogueObject implements InteractableRogueObject{

	public Hammer(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public boolean interactable(Point2D p) {
		setPosition(p);
		return true;
	}

	@Override
	public int vida() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public boolean dropObject(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hero){
				Point2D p = o.getPosition();
				setPosition(p);
				return true;
			}
		}
		return false;
	}
	
	public boolean dropObject2(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Thief){
				Point2D p = o.getPosition();
				setPosition(p);
				return true;
			}
		}
		return false;
	}

	@Override
	public void DiminuiVida(int i) {
		// TODO Auto-generated method stub
		
	}
}
