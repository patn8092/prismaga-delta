package delta.prismaga.level;

import java.awt.Color;
import java.awt.image.BufferedImage;

import delta.prismaga.main.FileUtils;
import delta.prismaga.main.Game;
import delta.prismaga.tile.Tile;

public class Level {
	
	private int[][] tiles;
	private BufferedImage background = FileUtils.loadAsImage("/background.png");
	private int w, h;
	
	public Level(BufferedImage levelImage) {
		loadLevel(levelImage);
		w = levelImage.getWidth();
		h = levelImage.getHeight();
	}

	public void loadLevel(BufferedImage levelImage) {
		tiles = new int[levelImage.getWidth()][levelImage.getHeight()];
		for(int y = 0; y < levelImage.getHeight(); y++) {
			for(int x = 0; x < levelImage.getWidth(); x++) {
				Color c = new Color(levelImage.getRGB(x,y));
				String h = String.format("%02x%02x%02x", c.getRed(), c.getGreen(), c.getBlue());	
				switch(h) {
				case "000000": //VOIDTILE
					tiles[x][y]	= 0;
					break;
				case "00ff00": //GRASS
					tiles[x][y] = 1;
					break;
				case "00db00":
					tiles[x][y] = 2;
					break;
				default:
					tiles[x][y] = 0;
					break;
				}
			}
		}
	}
	 
	public void tick() {
		int xo = Game.player.xOffset;
		int yo = Game.player.yOffset;
		
		int x0 = Math.max(xo / (Game.TILESIZE * Game.SCALE), 0);
		int y0 = Math.max(yo / (Game.TILESIZE * Game.SCALE), 0);
		int x1 = Math.min((xo + Game.WIDTH * Game.SCALE) / (Game.TILESIZE * Game.SCALE) + 1, w);
		int y1 = Math.min((yo + Game.HEIGHT * Game.SCALE) / (Game.TILESIZE * Game.SCALE) + 1, h);
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x,y).tick();
			}
		}	
	}
	
	public void render() {
		
		Game.g.drawImage(background, 0, 0, background.getWidth() * Game.SCALE, background.getHeight() * Game.SCALE, null);
		
		int xo = Game.player.xOffset;
		int yo = Game.player.yOffset;

		int x0 = Math.max(xo / (Game.TILESIZE * Game.SCALE), 0);
		int y0 = Math.max(yo / (Game.TILESIZE * Game.SCALE), 0);
		int x1 = Math.min((xo + Game.WIDTH * Game.SCALE) / (Game.TILESIZE * Game.SCALE) + 1, w);
		int y1 = Math.min((yo + Game.HEIGHT * Game.SCALE) / (Game.TILESIZE * Game.SCALE) + 1, h);
		
		for(int y = y0; y < y1; y++) {
			for(int x = x0; x < x1; x++) {
				getTile(x,y).render(x * Game.TILESIZE * Game.SCALE - xo, y * Game.TILESIZE * Game.SCALE - yo);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if( x < 0 || y < 0 || x >= w || y >= h) 
			return null;
		
		switch(tiles[x][y]) {
		case 0: return Tile.airTile;
		case 1: return Tile.grassPlatform;
		case 2: return Tile.dirtTile;
		default: return Tile.grassPlatform;
		}
	}
}
