package pt.iscte.poo.rogue.objects;

import pt.iul.ista.poo.utils.Point2D;

public interface InteractableRogueObject {
	public int getLayer();
	public boolean interactable(Point2D p);
	public int vida();
	public void DiminuiVida(int i);
}
