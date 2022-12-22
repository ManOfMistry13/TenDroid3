package com.example.get10;

import java.util.ArrayList;
import java.util.ListIterator;


public class Model  {

    ArrayList<Position> xys;
    public Model()  {
        xys = new ArrayList<Position>();
    }
    public void add(int x, int y) {
        xys.add(new Position(x,y));
    }
    private ListIterator<Position> getAll() {

        return xys.listIterator();
    }

    void reset(){
        xys.clear();
    }


}
