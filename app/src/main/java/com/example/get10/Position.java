package com.example.get10;


public class Position extends tendroid.model.Position {

    int x, y;
    public Position(int x, int y) {
        super(x,y);
    }


    public Integer getX() {
        return x;
    }
    public Integer getY() {
        return y;
    }
}
