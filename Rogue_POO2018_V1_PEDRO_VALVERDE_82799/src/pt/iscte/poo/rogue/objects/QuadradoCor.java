package pt.iscte.poo.rogue.objects;

import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Point2D;

public class QuadradoCor implements ImageTile{

	private Cor cor;
	private Point2D position;
	
	public QuadradoCor(Cor cor, Point2D position) {
		this.cor = cor;
		this.position = position;
	}

	@Override
	public String getName() {
		return cor.name();
	}

	@Override
	public Point2D getPosition() {
		return position;
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

}
