package com.zhaipz.study.datastructure.service.throughtwork;

public class UAV {
    String name;
    int x;
    int y;
    int z;
    boolean isAlive = false;

    public UAV(){
        this.x = 0;
        this.y = 0;
        this.z = 0;
        this.name = "";
    }

    public UAV(String x,String y,String z){
        this.x = Integer.parseInt(x);
        this.y = Integer.parseInt(y);
        this.z = Integer.parseInt(z);
    }

    public UAV(int x,int y,int z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void changeLocation(String x,String y,String z){
        this.x += Integer.parseInt(x);
        this.y += Integer.parseInt(y);
        this.z += Integer.parseInt(z);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }
}
