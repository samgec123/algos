package com.test;

public class TowerOfHanoi {
    public static void move(int disk, char startTower, char intermediateTower, char finalTower) {
        if (disk == 1) {
            System.out.println(startTower + " -> " + finalTower);
        } else {
            move(disk - 1, startTower, finalTower, intermediateTower);
            System.out.println(startTower + " -> " + finalTower);
            move(disk - 1, intermediateTower, startTower, finalTower);
        }
    }

    public static void main(String... args) {
        move(20, 'A', 'B', 'C');
    }
}
