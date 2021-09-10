package com.vilim;

import java.awt.*;

public abstract class Entity {

    protected int x, y;
    protected Game game;

    public Entity(Game game, int x, int y) {
        this.x = x;
        this.y = y;
        this.game = game;
    }

    public abstract void move();

    public abstract void tick();

    public abstract void render(Graphics g);

}
