package delta.prismaga.mapobjects;

import java.util.ArrayList;

public class MapObjectManager {
	
	/** MapObjectManager.java
	 *  Manages objects on map, including the player, items, enemies and other entities.
	 */
	
	private static ArrayList<MapObject> mapObjects;
	
	public static void addMapObject(MapObject o) {
		mapObjects.add(o);
	}
	
	public static void removeMapObject(MapObject o) {
		mapObjects.remove(o);
	}
	
	public void tick() {
		for(MapObject o : mapObjects) {
			o.tick();
		}
	}
	
	public void render() {
		for(MapObject o : mapObjects) {
			o.render();
		}
	}
	
	public static ArrayList<MapObject> getMapObjects() {
		return mapObjects;
	}
}
