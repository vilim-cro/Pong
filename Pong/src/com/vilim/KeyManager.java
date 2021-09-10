package com.vilim;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private boolean[] keys;
    public boolean up, down, q;
    private Game game;

    public KeyManager(Game game) {
        keys = new boolean[256];
        this.game = game;
    }

    public void tick() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        q = keys[KeyEvent.VK_Q];
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;
        if(e.getKeyCode() == KeyEvent.VK_Q) {
            game.getDisplay().getFrame().setVisible(false);
            game.getDisplay().getFrame().dispose();
            game = new Game("Pong" , 1200, 800);
            game.start();
        }
    }
}
