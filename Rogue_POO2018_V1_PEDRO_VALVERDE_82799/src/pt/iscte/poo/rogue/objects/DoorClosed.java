package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Point2D;

public class DoorClosed extends RogueObject implements InteractableRogueObject{
	
	private int NextRoom;
	
	public DoorClosed(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}
	
	public int Room(){
		return NextRoom;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 3;
	}

	@Override
	public boolean interactable(Point2D p) {
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
