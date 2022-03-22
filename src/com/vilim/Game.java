package com.vilim;

import java.awt.*;
import java.awt.image.BufferStrategy;

public class Game implements Runnable {
    public Display display;

    private int width;
    private int height;
    private String title;
    private Thread thread;
    private boolean running = false;

    private BufferStrategy bs;

    private Graphics g;
    private GameState gameState;

    // Input

    private KeyManager keyManager;
    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        this.keyManager = new KeyManager(this);
    }

    private void init() {
        display = new Display(title, width, height);

        //Enable access to keyboard
        display.getFrame().addKeyListener(keyManager);

        gameState = new GameState(this);
    }

    private void tick() {
        keyManager.tick();
        gameState.tick();
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        // Clear screen

        g.clearRect(0,0, width, height);

        // Here begins drawing

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, width, height);
        gameState.render(g);

        // End drawing here

        bs.show();
        g.dispose();
    }

    public void run() {
        init();
        int fps = 60;
        double timePerTick = 1_000_000_000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running) {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                ticks++;
                delta--;
            }
            if (timer >= 1_000_000_000) {
                ticks = 0;
                timer = 0;
            }
        }
        g = bs.getDrawGraphics();
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        g.drawString("You lost", 470 , 380);

        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.drawString("Press q for new game", 470, 450);
        bs.show();
        stop();
    }

    public synchronized void start() {
        if (running)
            return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running)
            return;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public Display getDisplay() {
        return display;
    }
}


