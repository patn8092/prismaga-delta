package delta.prismaga.main;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import delta.prismaga.level.Level;
import delta.prismaga.mapobjects.Player;

public class Game extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	
	private static final String TITLE = "Prismaga-Delta";
	
	public static final int WIDTH = 320,
							HEIGHT = 240,
							SCALE = 2,
							TILESIZE = 16;		
	
	private static Dimension SIZE = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
	
	private static boolean running = false;
	
	private static JFrame frame;
	private static Thread thread;
	private static Input input;
	
	private static BufferStrategy bs;
	public static Graphics g;
		
	public static Player player;
	
	public static Level level;
	
	private void init() {		
		createBufferStrategy(2);
		
		input = new Input();
		addKeyListener(input);
		addMouseListener(input);
		
		player = new Player();
		
		level = new Level(FileUtils.loadAsImage("/level.png"));
		
		debug(0,"Game successfully started");
	}
	
	private synchronized void start() {
		if(running)
			return;
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop() {
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		init(); //Initializes game settings and data;
		
		//Timer variables
		long lastTime = System.nanoTime();
		long now;
		final double amountOfTicks = 60D;
		double delta = 0;
		double ns = 1000000000 / amountOfTicks;
		
		while(running) {
			now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			
			if(delta >= 1) {
				tick();
				render();
				delta--;
			}
		}
		stop();
	}
	
	public void tick() {
		player.tick();
		level.tick();
	}
	
	public void render() {
		bs = this.getBufferStrategy();
		g = bs.getDrawGraphics();
		//RENDER

		g.fillRect(0, 0, WIDTH * SCALE, HEIGHT * SCALE);		
		
		level.render();
		player.render();
		
		//END RENDER
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
		game.setPreferredSize(SIZE);
		
		//Frame Properties
		frame = new JFrame(TITLE);
		frame.setSize(SIZE);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setLayout(new BorderLayout());
		frame.setResizable(false);
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		
		frame.setVisible(true);
		
		//Game Start
		game.start();
	}
	
	public static void debug(int l, String msg) {
		switch(l){
		case 0: System.out.println("[INFO]: " + msg); break;
		case 1:	System.out.println("[WARNING]: " + msg); break;
		case 2: System.out.println("[SEVERE]:" + msg); System.exit(1);;
		default: System.out.println("[INFO]: " + msg); break;
		}
	}
	
	public static Input getInput() {
		return input;
	}
}
