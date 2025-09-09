package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Point2D;

public class LifeBar extends RogueObject{

	private static int vida = 6;
	
	public LifeBar(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public static void Bar(){
		for(int i = 0; i != 10; i++){
			if(i < vida){
				Rogue.getInstance().getGreen();
				Rogue.getInstance().getRed();
			} else {
				Rogue.getInstance().getBlack();
			}
		}
	}
	
	public void GreenBar(){
		int i = 1;
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hero){
				if(vida > 0){
					((Hero) o).DiminuiVida(i);
					vida--;
				}
			}
		}
	}			
}


