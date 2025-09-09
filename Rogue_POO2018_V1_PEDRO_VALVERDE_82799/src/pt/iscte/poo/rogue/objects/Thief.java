package pt.iscte.poo.rogue.objects;

import java.util.List;

import pt.iscte.poo.rogue.Rogue;
import pt.iul.ista.poo.utils.Point2D;

public class Thief extends RogueObject implements InteractableRogueObject, MovingRogueObject{

	private int vida = 5;
	
	public Thief(Point2D position) {
		super(position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void move() {
		double r = Math.random()*2;
		int x = getPosition().getX();
		int y = getPosition().getY();
		RogueObject s = Rogue.getInstance().getHero();
		int x1 = s.getPosition().getX();
		int y1 = s.getPosition().getY();
		if(TemObjetoSword() || TemObjetoHammer() || TemObjetoKey() || TemObjetoSword() && TemObjetoHammer() ||
			TemObjetoSword() && TemObjetoKey() || TemObjetoHammer() && TemObjetoKey() ||
			TemObjetoSword() && TemObjetoHammer() && TemObjetoKey()){
			if((int)r == 1){
				if(x1 > x){
					x--;
				}
				if(x1 < x){
					x++;
				}
				
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
					
			} else {
				if(y1 > y){
					y--;
				}
				if(y1 < y){
					y++;
				}
				
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
			}
		} else {
			if((int)r == 1){
				if(x1 > x){
					x++;
				}
				if(x1 < x){
					x--;
				}
				
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
					
			} else {
				if(y1 > y){
					y++;
				}
				if(y1 < y){
					y--;
				}
				
				if(!interactable(new Point2D(x,y)))
				setPosition(new Point2D(x,y));
			}
		}
	}

	@Override
	public int getLayer() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean interactable(Point2D p) {
		double n = Math.random()*2;
		double r = Math.random()*3;
		double a = Math.random()*2;
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hero){
				if(o.getPosition().equals(p)){
					if((int)n == 1){
						if(((Hero) o).TemObjetoSword() && ((Hero) o).TemObjetoHammer() && ((Hero) o).TemObjetoKey()){
							if((int)r == 2){
								AgarraObjetoSword();
								return true;
							}
							if((int)r == 1){
								AgarraObjetoHammer();
								return true;
							}
							if((int)r == 0){
								AgarraObjetoKey();
								return true;
							}
						}
						if(((Hero) o).TemObjetoSword() && ((Hero) o).TemObjetoHammer()){
							if((int)a == 1){
								AgarraObjetoSword();
								return true;
							}
							if((int)a == 0){
								AgarraObjetoHammer();
								return true;
							}
						}
						if(((Hero) o).TemObjetoSword() && ((Hero) o).TemObjetoKey()){
							if((int)a == 1){
								AgarraObjetoSword();
								return true;
							}
							if((int)a == 0){
								AgarraObjetoKey();
								return true;
							}
						}
						if(((Hero) o).TemObjetoHammer() && ((Hero) o).TemObjetoKey()){
							if((int)a == 1){
								AgarraObjetoHammer();
								return true;
							}
							if((int)a == 0){
								AgarraObjetoKey();
								return true;
							}
						}
						if(((Hero) o).TemObjetoSword()){
							AgarraObjetoSword();
							return true;
						}
						if(((Hero) o).TemObjetoHammer()){
							AgarraObjetoHammer();
							return true;
						}
						if(((Hero) o).TemObjetoKey()){
							AgarraObjetoKey();
							return true;
						}
					} else {
						return true;
					}
				}	
			}
			if(o instanceof Skeleton || o instanceof Bat || o instanceof Wall){
				if(o.getPosition().equals(p)){
					return true;
				}
			}
			if(o instanceof Sword || o instanceof Hammer || o instanceof Key){
				if(o.getPosition().equals(p)){
					((InteractableRogueObject) o).interactable(new Point2D(8,10));
						return false;
				}
			}
		}
		return false;
	}
	
	public boolean TemObjetoSword(){
		Point2D x = new Point2D(8,10);
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
	
	public void AgarraObjetoSword(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Sword){
				((Sword) o).interactable(new Point2D(8,10));
			}
		}
	}

	public boolean TemObjetoHammer(){
		Point2D x = new Point2D(8,10);
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
	
	public void AgarraObjetoHammer(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hammer){
				((Hammer) o).interactable(new Point2D(8,10));
			}
		}
	}
	
	public boolean TemObjetoKey(){
		Point2D x = new Point2D(8,10);
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
	
	public void AgarraObjetoKey(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Key){
				((Key) o).interactable(new Point2D(8,10));
			}
		}
	}
	
	public void largaObjetos(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			for(RogueObject o1 : RO){
				for(RogueObject o2 : RO){
					if(o instanceof Sword){
						if(o1 instanceof Hammer){
							if(o2 instanceof Key){
								((Sword) o).dropObject2();
								((Hammer) o1).dropObject2();
								((Key) o2).dropObject2();
							}
						}
					}
				}
			}
		}
	}
	
	public void largaSwordHammer(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			for(RogueObject o1 : RO){
				if(o instanceof Sword){
					if(o1 instanceof Hammer){
						((Sword) o).dropObject2();
						((Hammer) o1).dropObject2();
					}
				}
			}
		}
	}
	
	public void largaSwordKey(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			for(RogueObject o1 : RO){
				if(o instanceof Sword){
					if(o1 instanceof Key){
						((Sword) o).dropObject2();
						((Key) o1).dropObject2();
					}
				}
			}
		}
	}
	
	public void largaHammerKey(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			for(RogueObject o1 : RO){
				if(o instanceof Hammer){
					if(o1 instanceof Key){
						((Hammer) o).dropObject2();
						((Key) o1).dropObject2();
					}
				}
			}
		}
	}
	
	public void largaSword(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Sword){
				((Sword) o).dropObject2();
			}
		}
	}
	
	public void largaHammer(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Hammer){
				((Hammer) o).dropObject2();
			}
		}
	}
	
	public void largaKey(){
		List<RogueObject> RO = Rogue.getInstance().getObjects();
		for(RogueObject o : RO){
			if(o instanceof Key){
				((Key) o).dropObject2();
			}
		}
	}

	@Override
	public int vida() {
		return vida;
	}

	@Override
	public void DiminuiVida(int i) {
		vida = vida - i;
		if(vida <= 0){
			if(TemObjetoSword() && TemObjetoHammer() && TemObjetoKey()){
				largaObjetos();
				Rogue.getInstance().removeObjects(this);
			}
			if(TemObjetoSword() && TemObjetoHammer() && !TemObjetoKey()){
				largaSwordHammer();
				Rogue.getInstance().removeObjects(this);
			}
			if(TemObjetoSword() && !TemObjetoHammer() && TemObjetoKey()){
				largaSwordKey();
				Rogue.getInstance().removeObjects(this);
			}
			if(!TemObjetoSword() && TemObjetoHammer() && TemObjetoKey()){
				largaHammerKey();
				Rogue.getInstance().removeObjects(this);
			}
			if(TemObjetoSword() && !TemObjetoHammer() && !TemObjetoKey()){
				largaSword();
				Rogue.getInstance().removeObjects(this);
			}
			if(!TemObjetoSword() && TemObjetoHammer() && !TemObjetoKey()){
				largaHammer();
				Rogue.getInstance().removeObjects(this);
			}
			if(!TemObjetoSword() && !TemObjetoHammer() && TemObjetoKey()){
				largaKey();
				Rogue.getInstance().removeObjects(this);
			}
			Rogue.getInstance().removeObjects(this);
		}
		System.out.println("vidaThief");
	}
}
