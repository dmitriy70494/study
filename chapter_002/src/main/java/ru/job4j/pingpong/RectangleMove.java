package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        double stepX = 1;
        double stepY = 1;
        double limitX = 300;
        double limitY = 300;
        double positionX = this.rect.getX();
        double positionY = this.rect.getY();
        while (true) {
            this.rect.setX(positionX);
            this.rect.setY(positionY);
            if ((positionX += stepX) > limitX || positionX < 0) {
                stepX *= -1;
            }
            if ((positionY += stepY) > limitY || positionY < 0) {
                stepY *= -1;
            }
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}