package com.vilim;

import java.awt.*;

public class GameState {

    private Player player1;
    private Player player2;
    private Ball ball;

    public GameState(Game game) {
        player1 = new Player(game, 25, 350);
        player2 = new Player(game, 1150, 350);
        ball = new Ball(game, game.getWidth() / 2, game.getHeight() / 2);
    }

    public void tick() {
        player1.tick();
        ball.tick();
    }

    public void render(Graphics g) {
        g.setColor(Color.WHITE);
        player1.render(g);
        player2.render(g);
        ball.render(g);
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }
}
