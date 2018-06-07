package ru.job4j.pingpong;

import javafx.scene.shape.Rectangle;

import java.sql.SQLOutput;

public class RectangleMove implements Runnable {
    private final Rectangle rect;
    double limitX;

    public RectangleMove(Rectangle rect, int limit) {
        this.rect = rect;
        limitX = limit;
    }

    @Override
    public void run() {
        double stepX = 1;
        double stepY = 1;
        double limitY = limitX;
        double positionX = this.rect.getX();
        double positionY = this.rect.getY();
        while (!Thread.currentThread().isInterrupted()) {
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
                Thread.currentThread().sleep(50);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
                break;
            }
        }
    }
}