package pt.iscte.poo.rogue;

import java.util.List;

import pt.iscte.poo.rogue.objects.RogueObject;
import pt.iul.ista.poo.utils.Point2D;

public class Room extends RogueObject{

	public Room(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 0;
	}

//	public void hide(){
//		List<RogueObject> RO = Rogue.getInstance().getObjects();
//		for(RogueObject x: RO){
//			img.removeImage(x);
//		}
//	}
//
//	public void show() {
//		// TODO Auto-generated method stub
//		
//	}
}
