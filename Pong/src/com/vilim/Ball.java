package com.vilim;

import java.awt.*;

public class Ball extends Entity{

    private static int radius = 20;
    private double xMove;
    private double yMove;
    private Rectangle bound;

    public Ball(Game game, int x, int y) {
        super(game, x - radius / 2, y - radius / 2);
        this.game = game;

        bound = new Rectangle(x - radius / 2, y - radius / 2, radius, radius);

        xMove = 9;
        yMove = 5;
    }

    @Override
    public void move() {

        if(checkXCollision())  {
            xMove = -xMove;
        }

        if(checkYCollision()) {
            yMove = -yMove;
        }

        moveX();
        moveY();
    }

    private void moveX() {
        x += xMove;
        bound.x += xMove;
    }

    private void moveY() {
        y += yMove;
        bound.y += yMove;
        game.getGameState().getPlayer2().autoMove(yMove);
    }

    private boolean checkXCollision() {
        return bound.intersects(game.getGameState().getPlayer1().getRec()) ||
                bound.intersects(game.getGameState().getPlayer2().getRec());

    }

    private boolean checkYCollision() {
        return y + yMove <= 0 || y + yMove + radius >= game.getHeight();
    }

    @Override
    public void tick() {
        move();
        if(x <= 0 || x >= game.getWidth())
            game.setRunning(false);
    }

    @Override
    public void render(Graphics g) {
        g.fillOval(x, y, radius, radius);
    }
}
