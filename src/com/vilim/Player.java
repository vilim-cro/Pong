package com.vilim;

import java.awt.*;

public class Player extends Entity{

    private final int DEFAULT_WIDTH = 25, DEFAULT_HEIGHT = 100;
    private static final int DEFAULT_SPEED = 5;
    private int move;
    private Rectangle rec;

    public Player(Game game, int x, int y) {
        super(game, x, y);
        this.game = game;
        this.rec = new Rectangle(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void move() {
        if(game.getKeyManager().up) {
            move = -DEFAULT_SPEED;
        } else if(game.getKeyManager().down)
            move = DEFAULT_SPEED;
        if(!((y + move - 1 >= game.getHeight() - DEFAULT_HEIGHT) || (y + move + 1 <= 0))) {
            y += move;
            rec.y += move;
        }
        move = 0;
    }

    @Override
    public void tick() {
        move();
    }

    @Override
    public void render(Graphics g) {
        g.fillRect(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    public Rectangle getRec() {
        return rec;
    }

    public void autoMove(double move) {
        y += move;
        rec.y += move;
    }
}
