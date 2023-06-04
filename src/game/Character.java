package game;

import java.util.Random;

public abstract class Character {
    protected final Random random = new Random();
    protected int height;  // Высота по координатам
    protected int width;// Ширина по координатам
    protected int energy; // Энергия

    protected abstract String[][] ActUp(String[][] map); // Метод движения вверх

    protected abstract String[][] ActDown(String[][] map); // Метод движения вниз

    protected abstract String[][] ActLeft(String[][] map); // Метод движения влево

    protected abstract String[][] ActRight(String[][] map); // Метод движения вправо

    protected abstract void ActSleep(); // Метод отдохнуть

    protected abstract String[][] ActAbility(String[][] map); // Метод использования способности
}
