package com.mygdx.game;

import com.badlogic.gdx.math.Rectangle;

import java.awt.Point;

public class Dynamite extends Rectangle {

    Duck duck;

    public Dynamite(Duck thrower) {
        duck = thrower;
    }

    public boolean shouldExplode() {
        if ((getY() > Globals.RESOLUTION_HEIGHT - 192 && duck.type == DuckType.PLAYER) ||
                (getY() < 192 && duck.type == DuckType.ENEMY)) {
            return true;
        } else {
            return false;
        }
    }

    //Should return point of explosion
    //TODO: MIGHT BE WRONG TYPE OF POINT
    public Point explode() {

        return new Point();
    }
}
