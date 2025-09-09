package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Point2D;

public class DoorOpen extends RogueObject implements InteractableRogueObject{

	private int NextRoom;
	private int n;
	private int n_Room_To;
	private int n_Door_To;
	
	public DoorOpen(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 2;
	}
	
	public int Room(){
		return NextRoom;
	}

	@Override
	public boolean interactable(Point2D p) {
		return false;
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
	
//	public void interact(RogueObject o){
//		Rogue.getInstance().showRoom(n_Room_To);
//		Rogue.getInstance().changeHeroPosition(n_Door_To);
//	}
