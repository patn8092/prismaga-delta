package delta.prismaga.mapobjects;

import java.awt.Graphics;

import delta.prismaga.main.Game;

public abstract class MapObject {
	
	//This class for extending to other objects
	
	protected Graphics g = Game.g;
	
	public abstract void tick();
	public abstract void render();
}
