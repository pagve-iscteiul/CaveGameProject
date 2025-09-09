package pt.iscte.poo.rogue.objects;

import pt.iul.ista.poo.utils.Point2D;

public class Floor extends RogueObject implements InteractableRogueObject{

	public Floor(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean interactable(Point2D p) {
		// TODO Auto-generated method stub
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
