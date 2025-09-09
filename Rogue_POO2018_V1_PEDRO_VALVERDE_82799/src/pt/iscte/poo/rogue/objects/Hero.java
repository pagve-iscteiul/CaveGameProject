
package pt.iscte.poo.rogue.objects;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

public class Hero extends RogueObject implements InteractableRogueObject{ // TODO: implements?
	
	private int vida = 10;
	private static final int MAX_VIDA = 10;
	private static final int Y_BARRA = 10;
	private int n = 0;
	private int r = 0;
	private int numberOfMoves;
	private List<RogueObject> inventario = new ArrayList<RogueObject>();
	private List<ImageTile> vidaImagens = new ArrayList<ImageTile>();
	
	public Hero(Point2D position) {
		super(position);
	}

	// TODO: Override ?

	public void move(Direction d) {
		Point2D nextP = getPosition().plus(d.asVector());
//		List<Interactable> ObjectsAt = img.getInstance().
//		for(Interactable i : objects){
//			i.interact(this);
		if(!TemObjetoHammer() && !Trapped() && !interactable(nextP)){
			n = 0;
			setPosition(nextP);
			numberOfMoves++;
		}
		if(TemObjetoHammer() && Trapped() || !TemObjetoHammer() && Trapped()){
			n++;
			numberOfMoves++;
		}
		if(TemObjetoHammer() && !Trapped() && !interactable(nextP)){
			n = 0;
			if(r%2 == 0){
				setPosition(nextP);
				System.out.println(0);
				n = n + 2;
				r = 1;
				numberOfMoves++;
			} else {
				System.out.println(1);
				n = n + 2;
				r = 0;
				numberOfMoves++;
			}
		}
	}
				
/*
		}*/
		//if(...) { //TODO: When does it move?
		// }
		// TODO: What does it do to objects in that position?
	
	public void barraDeVida(){
	
		ImageMatrixGUI.getInstance().removeImages(vidaImagens);
		vidaImagens.clear();
		
		for(int i = 0; i < MAX_VIDA; i += 2){
			if(i + 1 < vida)
				vidaImagens.add(new QuadradoCor(Cor.Red, new Point2D(i/2, Y_BARRA)));
			else if(i + 1 == vida)
				vidaImagens.add(new QuadradoCor(Cor.RedGreen, new Point2D(i/2, Y_BARRA)));
			else
				vidaImagens.add(new QuadradoCor(Cor.Green, new Point2D(i/2, Y_BARRA)));
		}
	
		
//		inventario.add(new Sword(new Point2D(8,Y_BARRA)));
//		inventario.add(new Key(new Point2D(9,Y_BARRA)));
//		
//		assert inventario.size() < 4; // So em modo debug
//		
//		List<ImageTile> imagensInventário = new ArrayList<ImageTile>(inventario);
//		ImageMatrixGUI.getInstance().addImages(imagensInventário);
//			
		ImageMatrixGUI.getInstance().addImages(vidaImagens);
		ImageMatrixGUI.getInstance().update();
	}
			
	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 10;
	}
		

	@Override
	public boolean interactable(Point2D p) {
		List<RogueObject> RO = Rogue.getInstance().getObjects();	
		for(RogueObject o : RO){
			if(o instanceof Skeleton || o instanceof Bat || o instanceof Thief){
				if(o.getPosition().equals(p)){
					if(TemObjetoSword() && TemObjetoHammer()){
						System.out.println(7);
						((InteractableRogueObject) o).DiminuiVida(7);
						return true;
					}
					if(TemObjetoSword()){
						System.out.println(2);
						((InteractableRogueObject) o).DiminuiVida(2);
						return true;
					}
					if(TemObjetoHammer()){
						System.out.println(5);
						((InteractableRogueObject) o).DiminuiVida(5);
						return true;
					} else {
						System.out.println(1);
						((InteractableRogueObject) o).DiminuiVida(1);
						return true;
					}
				}
			}
			if(o instanceof Wall){
				if(o.getPosition().equals(p)){
					return true;
				}
			}
			if(o instanceof Sword || o instanceof Hammer || o instanceof Key){
				if(o.getPosition().equals(p) && r == 0){
					((InteractableRogueObject) o).interactable(new Point2D(9,10));
					return false;
				}
			}
			if(o instanceof Trap){
				if(o.getPosition().equals(p)){
					return false;
				}
			}
			if(o instanceof DoorOpen){
				if(o.getPosition().equals(p)){
					System.out.println("chegou");
					Rogue.getInstance().mudarDeSalas(((DoorOpen) o).Room());
					return false;
				}
			}
			if(o instanceof DoorClosed){
				if(o.getPosition().equals(p)){
					if(TemObjetoKey()){
						System.out.println("chegou");
						Rogue.getInstance().mudarDeSalas(((DoorClosed) o).Room());
						return false;
					} else {
						return true;
					}
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
	
	public boolean TemObjetoSword(){
		Point2D x = new Point2D(9,10);
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Sword){
				if(o.getPosition().equals(x)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean TemObjetoHammer(){
		Point2D x = new Point2D(9,10);
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hammer){
				if(o.getPosition().equals(x)){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean TemObjetoKey(){
		Point2D x = new Point2D(9,10);
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Key){
				if(o.getPosition().equals(x)){
					return true;
				}
			}
		}
		return false;
	}
				
	@Override
	public void DiminuiVida(int i) {
		vida = vida - i;
		barraDeVida();
		if(vida <= 0){
			Rogue.getInstance().removeObjects(this);
			ImageMatrixGUI.getInstance().setStatusMessage("Game Over");
			System.out.println("Numero de movimentos do herói  " + numberOfMoves);
		}
		System.out.println("vidaHero");
	}
	
	public boolean Trapped(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Trap){
				if(o.getPosition().equals(this.getPosition()) && n < 4){
					System.out.println("Trapped");
					return true;
				}
			}
		}
		return false;
	}					
}