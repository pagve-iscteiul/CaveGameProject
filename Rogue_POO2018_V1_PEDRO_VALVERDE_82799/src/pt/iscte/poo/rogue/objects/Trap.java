package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Point2D;

public class Trap extends RogueObject implements InteractableRogueObject{

	public Trap(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean interactable(Point2D p) {
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hero){
				if(o.getPosition().equals(p)){
					return false;
				}
			}
		}	
		return true;
	}

	@Override
	public int vida() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void DiminuiVida(int i) {
		// TODO Auto-generated method stub
		
	}
}
