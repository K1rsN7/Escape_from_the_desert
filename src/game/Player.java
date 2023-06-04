package game;

import java.util.Objects;

public class Player extends Character implements SystemConstants {
    protected String name_player; // Никнейм игрока
    private boolean game_over; // Флаг проигрыша
    private boolean exit_game; // Нахождение секретного выхода

    public void Delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    protected boolean GetGameOver() {
        return this.game_over;
    }

    protected boolean GetExitGame() {
        return exit_game;
    }


    protected String[][] ActUp(String[][] map) {
        if (this.height <= 0) { // Проверка на глупого пользователя :)
            System.out.println(SYSTEM_COLOR + "Error: The player cannot go beyond the boundaries" + DEFAULT_COLOR);
        } else {
            if (Objects.equals(map[this.height - 1][this.width], "T")) { // Проверка, не попал ли игрок на терминатора
                this.game_over = true;
                System.out.println(TERMINATOR_COLOR + name_terminator + ": You f#cking idiot, did you think you could get past me?" + DEFAULT_COLOR);
            } else {
                if (Objects.equals(map[this.height - 1][this.width], "#")) {
                    System.out.println(SYSTEM_COLOR + "Error: The player cannot pass through the barricade" + DEFAULT_COLOR);
                } else {
                    if (Objects.equals(map[this.height - 1][this.width], "0")) {
                        this.game_over = true;
                    }
                    map[this.height - 1][this.width] = "Y";
                    map[this.height][this.width] = null;
                    this.height--;
                    this.energy -= 10;
                }
            }
        }
        return map;
    }

    protected String[][] ActDown(String[][] map) {
        if (this.height >= 24) { // Проверка на глупого пользователя :)
            System.out.println(SYSTEM_COLOR + "Error: The player cannot go beyond the boundaries" + DEFAULT_COLOR);
        } else {
            if (Objects.equals(map[this.height + 1][this.width], "T")) { // Проверка, не попал ли игрок на терминатора
                this.game_over = true;
                System.out.println(TERMINATOR_COLOR + name_terminator + ": You f#cking idiot, did you think you could get past me?" + DEFAULT_COLOR);
            } else {
                if (Objects.equals(map[this.height + 1][this.width], "#")) { // Проверка, не пытается ли игрок пройти в стену
                    System.out.println(SYSTEM_COLOR + "Error: The player cannot pass through the barricade" + DEFAULT_COLOR);
                } else {
                    if (Objects.equals(map[this.height + 1][this.width], "0")) { // Проверка, не попал ли игрок в яму
                        this.game_over = true;
                    }
                    map[this.height + 1][this.width] = "Y";
                    map[this.height][this.width] = null;
                    this.height++;
                    this.energy -= 10;
                }
            }
        }
        return map;
    }

    protected String[][] ActLeft(String[][] map) {
        if (this.width <= 0) { // Проверка на глупого пользователя :)
            System.out.println(SYSTEM_COLOR + "Error: The player cannot go beyond the boundaries" + DEFAULT_COLOR);
        } else {
            if (Objects.equals(map[this.height][this.width - 1], "T")) { // Проверка, не попал ли игрок на терминатора
                this.game_over = true;
                System.out.println(TERMINATOR_COLOR + name_terminator + ": You f#cking idiot, did you think you could get past me?" + DEFAULT_COLOR);
            } else {
                if (Objects.equals(map[this.height][this.width - 1], "#")) { // Проверка, не пытается ли игрок пройти в стену
                    System.out.println(SYSTEM_COLOR + "Error: The player cannot pass through the barricade" + DEFAULT_COLOR);
                } else {
                    if (Objects.equals(map[this.height][this.width - 1], "0")) { // Проверка, не попал ли игрок в яму
                        this.game_over = true;
                    }
                    map[this.height][this.width - 1] = "Y";
                    map[this.height][this.width] = null;
                    this.width--;
                    this.energy -= 10;
                }
            }
        }
        return map;
    }

    protected String[][] ActRight(String[][] map) {
        if (this.width >= 24) { // Проверка на глупого пользователя :)
            System.out.println(SYSTEM_COLOR + "Error: The player cannot go beyond the boundaries" + DEFAULT_COLOR);
        } else {
            if (Objects.equals(map[this.height][this.width + 1], "T")) { // Проверка, не попал ли игрок на терминатора
                this.game_over = true;
                System.out.println(TERMINATOR_COLOR + name_terminator + ": You f#cking idiot, did you think you could get past me?" + DEFAULT_COLOR);
            } else {
                if (Objects.equals(map[this.height][this.width + 1], "#")) { // Проверка, не пытается ли игрок пройти в стену
                    System.out.println(SYSTEM_COLOR + "Error: The player cannot pass through the barricade" + DEFAULT_COLOR);
                } else {
                    if (Objects.equals(map[this.height][this.width + 1], "0")) { // Проверка, не попал ли игрок в яму
                        this.game_over = true;
                    }
                    map[this.height][this.width + 1] = "Y";
                    map[this.height][this.width] = null;
                    this.width++;
                    this.energy -= 10;
                }
            }
        }
        return map;
    }

    protected void ActSleep() {
        this.energy += 50;
    }

    protected String[][] ActAbility(String[][] map) {
        for (String[] strings : map) {
            for (String string : strings) {
                if (string == "Y") {
                    System.out.print(PLAYER_COLOR + string + DEFAULT_COLOR);
                } else if (string == "T") {
                    System.out.print(TERMINATOR_COLOR + string + DEFAULT_COLOR);
                } else if (string == "#") {
                    System.out.print(PIT_COLOR + string + DEFAULT_COLOR);
                } else if (string == "0") {
                    System.out.print(WALL_COLOR + string + DEFAULT_COLOR);
                } else {
                    System.out.print(VOID_COLOR + "." + DEFAULT_COLOR);
                }
                System.out.print("  ");
            }
            System.out.println();
        }
        return map;
    }

    protected String[][] Act(int act_player, String[][] map, int exit_height, int exit_width) {
        if (this.energy < 10) {
            System.out.println(PLAYER_COLOR + this.name_player + " is tired" + DEFAULT_COLOR);
            this.energy = 50;
        } else {
            switch (act_player) {
                case (1) -> // Действие: игрок движется вверх
                        map = ActUp(map);
                case (2) ->// Действие: игрок движется вниз
                        map = ActDown(map);
                case (3) ->// Действие: игрок движется влево
                        map = ActLeft(map);
                case (4) ->// Действие: игрок движется вправо
                        map = ActRight(map);
                case (5) ->// Действие: отдохнуть
                        ActSleep();
                default -> // Действие: игрок использует способность
                        map = ActAbility(map);
            }
        }
        Delay();
        System.out.printf(SYSTEM_COLOR + "Characteristics of the " + PLAYER_COLOR + this.name_player + SYSTEM_COLOR + " : position(%d:%d), energy(%d)\n" + DEFAULT_COLOR, this.width, this.height,
                this.energy);
        if (this.height == exit_height && this.width == exit_width) { // Проверка нахождения секретного выхода
            System.out.println();
            System.out.println(SYSTEM_COLOR + "System message: Bingo!!! You have found a secret exit!" + DEFAULT_COLOR);
            this.exit_game = true;
        }
        return map;
    }

}
