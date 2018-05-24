package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

import java.sql.SQLOutput;

public class RectangleMove implements Runnable {
    private final Rectangle rect;

    public RectangleMove(Rectangle rect) {
        this.rect = rect;
    }

    @Override
    public void run() {
        boolean interrapted = true;
        double stepX = 1;
        double stepY = 1;
        double limitX = 300;
        double limitY = 300;
        double positionX = this.rect.getX();
        double positionY = this.rect.getY();
        while (interrapted) {
            try {
                this.rect.setX(positionX);
                this.rect.setY(positionY);
                positionX += stepX;
                positionY += stepY;
                if (positionX > limitX || positionX < 0) {
                    stepX *= -1;
                }
                if (positionY > limitY || positionY < 0) {
                    stepY *= -1;
                }
                Thread.currentThread().join(50);
            } catch (InterruptedException e) {
                interrapted = false;
            }
        }
    }
}