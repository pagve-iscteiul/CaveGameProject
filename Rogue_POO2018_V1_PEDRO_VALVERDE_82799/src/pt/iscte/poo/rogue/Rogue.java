package pt.iscte.poo.rogue;

import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import pt.iscte.poo.rogue.objects.Bat;
import pt.iscte.poo.rogue.objects.Black;
import pt.iscte.poo.rogue.objects.Door;
import pt.iscte.poo.rogue.objects.DoorClosed;
import pt.iscte.poo.rogue.objects.DoorOpen;
import pt.iscte.poo.rogue.objects.Floor;
import pt.iscte.poo.rogue.objects.Green;
import pt.iscte.poo.rogue.objects.Hammer;
import pt.iscte.poo.rogue.objects.Hero;
import pt.iscte.poo.rogue.objects.InteractableRogueObject;
import pt.iscte.poo.rogue.objects.Key;
import pt.iscte.poo.rogue.objects.MovingRogueObject;
import pt.iscte.poo.rogue.objects.Red;
import pt.iscte.poo.rogue.objects.RogueObject;
import pt.iscte.poo.rogue.objects.Skeleton;
import pt.iscte.poo.rogue.objects.Sword;
import pt.iscte.poo.rogue.objects.Thief;
import pt.iscte.poo.rogue.objects.Trap;
import pt.iscte.poo.rogue.objects.Wall;
import pt.iul.ista.poo.gui.ImageMatrixGUI;
import pt.iul.ista.poo.gui.ImageTile;
import pt.iul.ista.poo.observer.Observed;
import pt.iul.ista.poo.observer.Observer;
import pt.iul.ista.poo.utils.Direction;
import pt.iul.ista.poo.utils.Point2D;

// UML File -> Other -> 
public class Rogue implements Observer {

	private static final int WIDTH = 10;
	private static final int HEIGHT = 10;
	private Hero hero;
	private List<RogueObject> objects = new ArrayList<RogueObject>();
	//private List<RogueObject> objects2 = new ArrayList<RogueObject>();
	private List<List<RogueObject>> Rooms = new ArrayList<List<RogueObject>>();
	private List<Room> Room = new ArrayList<Room>();
	private Room Active_Room;
//	private List<RogueObject> ActualRoom = new ArrayList<RogueObject>();

	// TODO: Other variables
	
	// Singleton pattern attribute
	private static final Rogue INSTANCE = new Rogue();
	
	// Singleton pattern: private constructor
	private Rogue() {
		// Do not delete/change or move these three lines
		ImageMatrixGUI.setSize(WIDTH, HEIGHT + 1); // +1 because last row is the status bar
		ImageMatrixGUI.getInstance().registerObserver(this);
		ImageMatrixGUI.getInstance().setStatusMessage("Rogue Game Test"); // Try other message, keep track of the room number using this message
		
		// staticInit() should be replaced by initFromFile(FILENAME); after first phase with FILENAME = "config/map.txt"
//		staticInit(); 
		
		try {
			staticInit2();
			System.out.println("tamanho da lista" + Rooms.size());
		} catch (FileNotFoundException e){
			staticInit();
		}
		
	}

	// Singleton pattern inspector
	public static Rogue getInstance() {
		return INSTANCE;
	}
	
	// Initialization for 1st phase 
	private void staticInit() {
		hero = new Hero(new Point2D(1,1));
		objects.add(hero);
		for(int i = 1; i != WIDTH - 1; i++)
			for(int j = 1; j != HEIGHT - 1; j++)
				objects.add(new Floor(new Point2D(i,j)));
		RogueObject o = new Skeleton(new Point2D(8,1));
		objects.add(o);
		o = new Bat(new Point2D(1,8));
		objects.add(o);
		o = new Sword(new Point2D(8,8));
		objects.add(o);
		o = new Hammer(new Point2D(1,3));
		objects.add(o);
		o = new Trap(new Point2D(4,4));
		objects.add(o);
//		o = new DoorClosed(new Point2D(8,4));
//		objects.add(o);
//		o = new DoorOpen(new Point2D(7,4));
//		objects.add(o);
		o = new Thief(new Point2D(8,2));
		objects.add(o);
		o = new Key(new Point2D(1,2));
		objects.add(o);
		for(int x = 0; x != WIDTH; x++){
			o = new Wall(new Point2D(x,0));
			objects.add(o);
		}
		for(int y = 0; y != HEIGHT; y++){
			o = new Wall(new Point2D(y,HEIGHT - 1));
			objects.add(o);
		}
		for(int x1 = 0; x1 != HEIGHT; x1++){
			o = new Wall(new Point2D(0,x1));
			objects.add(o);
		}
		for(int y1 = 0; y1 != HEIGHT; y1++){
			o = new Wall(new Point2D(9,y1));
			objects.add(o);
		}
		getBlack();
//		getGreen();
//		getRed();
		hero.barraDeVida();
		addall();	
	}
		
	private void addall(){
		List<ImageTile> list = new ArrayList<ImageTile>();
		list.addAll(objects);
		
		ImageMatrixGUI.getInstance().addImages(list);
	}

	
	private void staticInit2() throws FileNotFoundException {
		hero = new Hero(new Point2D(1,1));
		ImageMatrixGUI.getInstance().addImage(hero);
		
		Scanner scanner = new Scanner(new File("config/file.txt"));
		
		List<RogueObject> objectsList = new ArrayList<>();
		
		int NumberOfRoom = 1;
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			line = line.toLowerCase();
			
			if(line.startsWith("room")) {
				if(NumberOfRoom > 1){
					Rooms.add(objectsList);
					objectsList = new ArrayList<RogueObject>();
				}
				NumberOfRoom++;
				
			} else {
				List<RogueObject> Objects = RogueObject.create(line);
				objectsList.addAll(Objects);
			}
		}
		
		Rooms.add(objectsList);
		
		scanner.close();
		
//		List<RogueObject> lista = Rooms.get(0);
		List<RogueObject> lista = Rooms.get(1);
//		List<RogueObject> lista = Rooms.get(2);
		for(RogueObject o : lista){
			ImageMatrixGUI.getInstance().addImage(o);
		}
		
//		Rooms.get(0).add(hero);
//		objects = Rooms.get(0);
		Rooms.get(1).add(hero);
		objects = Rooms.get(1);
//		Rooms.get(2).add(hero);
//		objects = Rooms.get(2);
		
		getBlack();
//		getGreen();
//		getRed();
		hero.barraDeVida();
		System.out.println();
	}
	
	// Barra Cor Preta
		public void getBlack(){
			for(int i = 0; i != WIDTH; i++){
				for(int j = 0; j != HEIGHT; j++){
					System.out.println(i);
					System.out.println(j);
					Black black = new Black(new Point2D(i,10));
					ImageMatrixGUI.getInstance().addImage(black);
				}
			}	
		}
	
	// Barra Cor Verde
		public void getGreen(){
			for(int i = 0; i != WIDTH; i++){
				for(int j = 0; j != HEIGHT; j++){
					System.out.println(i);
					System.out.println(j);
					Green green = new Green(new Point2D(i/2,10));
					ImageMatrixGUI.getInstance().addImage(green);
				}
			}
		}
	
	// Barra Cor Vermelha
		public void getRed(){
			for(int i = 0; i != WIDTH; i++){
				for(int j = 0; j != HEIGHT; j++){
					System.out.println(i);
					System.out.println(j);
					Red red = new Red(new Point2D(i/2,10));
					ImageMatrixGUI.getInstance().addImage(red);
				}
			}
		}
						       		
		//ImageMatrixGUI.getInstance().addImage(new Wall(3, 4));
		
		// TODO: Initialize other objects in the appropriate lists (use a Factory to make switch to file-based initialization easy)

	// Starting-up GUI
	public void go() {
		ImageMatrixGUI.getInstance().go();
		ImageMatrixGUI.getInstance().update();		
	}
	
	// Called everytime a key is pressed
	@Override
	public void update(Observed source) {
		int keyPressed = ImageMatrixGUI.getInstance().keyPressed();
		if(Direction.isDirection(keyPressed)){
			Direction d = Direction.directionFor(keyPressed);
			hero.move(d);
			moveObjects();
		}
		else {
			if(keyPressed == KeyEvent.VK_1 || keyPressed == KeyEvent.VK_2 || keyPressed == KeyEvent.VK_3){
				for(RogueObject o : objects){
					if(o instanceof Sword){
						if(HasSword()){
							((Sword) o).dropObject();
						}
					}
					if(o instanceof Hammer){
						if(HasHammer()){
							((Hammer) o).dropObject();	
						}
					}
					if(o instanceof Key){
						if(HasKey()){
							((Key) o).dropObject();
						}
					}
				}
			}
		}
		//TODO: what else?
		ImageMatrixGUI.getInstance().update();
		}
	
	public boolean HasSword(){
		for(RogueObject o : objects){
			if(o instanceof Hero){
				if(((Hero) o).TemObjetoSword()){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean HasHammer(){
		for(RogueObject o : objects){
			if(o instanceof Hero){
				if(((Hero) o).TemObjetoHammer()){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean HasKey(){
		for(RogueObject o : objects){
			if(o instanceof Hero){
				if(((Hero) o).TemObjetoKey()){
					return true;
				}
			}
		}
		return false;
	}
	
	public List<RogueObject> getObjects(){
		return this.objects;
	}
	
	public RogueObject getHero(){
		return hero;
	}
	
	public void moveObjects(){
		for(RogueObject o : objects){
			if(o instanceof MovingRogueObject){
				((MovingRogueObject) o).move();
			}
		}
	}
	
	public void removeObjects(RogueObject o){
		objects.remove(o);
		ImageMatrixGUI.getInstance().removeImage(o);
	}
				
	public void mudarDeSalas(int NumberOfRoom){
		objects.clear();
		objects = Rooms.get(NumberOfRoom);
		objects.add(hero);
		ImageMatrixGUI.getInstance().clearImages();
		getBlack();
		hero.barraDeVida();
		for(RogueObject l : objects){
			ImageMatrixGUI.getInstance().addImage(l);
		}
	}
	
//	public void showRoom(int New_Room){
//		Active_Room.hide();
//		Active_Room = Room.get(New_Room);
//		Active_Room.show();
//	}
//	
//	public void changeHeroPosition(int p){
//		Point2D p = positionOfDoor(p);
//		Hero.setPosition(p);
//	}
//	
//	public Point2D positionOfDoor(int p){
//		for(RogueObject x: objects){
//			if(x instanceof DoorOpen){
//				if(x.getNumber() == p)
//					return x.getPosition();
//			}
//		}
//	}
}		