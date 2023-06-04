package game;

import java.util.Objects;

public class Terminator extends Character implements SystemConstants {
    private boolean game_over;

    protected boolean GetGameOver() {
        return this.game_over;
    }

    private void Delay() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ignored) {
        }
    }

    private void PhraseBoundaries() {
        int phrase = random.nextInt(2);
        switch (phrase) {
            case (0) ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": What kind of f#cker stitched me?!" + DEFAULT_COLOR);
            default ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": Some mistake has occurred, I am correcting the error." + DEFAULT_COLOR);
        }
    }

    private void PhraseMurder() {
        int phrase = random.nextInt(2);
        switch (phrase) {
            case (0) ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": I told you I'd come back for you!" + DEFAULT_COLOR);
            default ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": Got the son of a b#tch! Time to go to Hell!" + DEFAULT_COLOR);
        }
    }

    private void PhraseWall() {
        int phrase = random.nextInt(2);
        switch (phrase) {
            case (0) ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": Is this a wall? You would have built it out of f#cking paper!" + DEFAULT_COLOR);
            default ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": There are no barriers for me, this barricade will not stop me!" + DEFAULT_COLOR);
        }
    }

    private void PhrasePit() {
        int phrase = random.nextInt(2);
        switch (phrase) {
            case (0) ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": F#ck! What kind of dog was digging these damn holes?!" + DEFAULT_COLOR);
            default ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": F#cking pit, f#cking people dug up!" + DEFAULT_COLOR);
        }
    }

    protected String[][] ActUp(String[][] map) {
        if (this.height <= 0) { // Проверка, не пытается ли терминатор уйти за карту
            PhraseBoundaries();
        } else {
            if (Objects.equals(map[this.height - 1][this.width], "Y")) { // Проверка, попался ли терминатор на игрока
                this.game_over = true;
                PhraseMurder();
            } else {
                if (Objects.equals(map[this.height - 1][this.width], "#")) { // Проверка, попался ли терминатор на стену
                    PhraseWall();
                    map[this.height - 1][this.width] = "T";
                } else {
                    if (Objects.equals(map[this.height - 1][this.width], "0")) {
                        PhrasePit();
                    } else {
                        map[this.height - 1][this.width] = "T";
                    }

                }
                map[this.height][this.width] = null;
                this.height--;
            }
        }
        return map;
    }

    protected String[][] ActDown(String[][] map) {
        if (this.height >= 24) { // Проверка, не пытается ли терминатор уйти за карту
            PhraseBoundaries();
        } else {
            if (Objects.equals(map[this.height + 1][this.width], "Y")) { // Проверка, попался ли терминатор на игрока
                this.game_over = true;
                PhraseMurder();
            } else {
                if (Objects.equals(map[this.height + 1][this.width], "#")) { // Проверка, попался ли терминатор на стену
                    PhraseWall();
                    map[this.height + 1][this.width] = "T";
                } else {
                    if (Objects.equals(map[this.height + 1][this.width], "0")) {
                        PhrasePit();
                    } else {
                        map[this.height + 1][this.width] = "T";
                    }
                }
                map[this.height][this.width] = null;
                this.height++;
            }
        }
        return map;
    }

    protected String[][] ActLeft(String[][] map) {
        if (this.width <= 0) { // Проверка, не пытается ли терминатор уйти за карту
            PhraseBoundaries();
        } else {
            if (Objects.equals(map[this.height][this.width - 1], "Y")) { // Проверка, попался ли терминатор на игрока
                this.game_over = true;
                PhraseMurder();
            } else {
                if (Objects.equals(map[this.height][this.width - 1], "#")) { // Проверка, попался ли терминатор на стену
                    PhraseWall();
                    map[this.height][this.width - 1] = "T";

                } else {
                    if (Objects.equals(map[this.height][this.width - 1], "0")) {
                        PhrasePit();
                    } else {
                        map[this.height][this.width - 1] = "T";
                    }
                }
                map[this.height][this.width] = null;
                this.width--;
            }
        }
        return map;
    }

    protected String[][] ActRight(String[][] map) {
        if (this.width >= 24) { // Проверка, не пытается ли терминатор уйти за карту
            PhraseBoundaries();
        } else {
            if (Objects.equals(map[this.height][this.width + 1], "Y")) { // Проверка, попался ли терминатор на игрока
                this.game_over = true;
                PhraseMurder();
            } else {
                if (Objects.equals(map[this.height][this.width + 1], "#")) { // Проверка, попался ли терминатор на стену
                    PhraseWall();
                    map[this.height][this.width + 1] = "T";

                } else {
                    if (Objects.equals(map[this.height][this.width + 1], "0")) {
                        PhrasePit();
                    } else {
                        map[this.height][this.width + 1] = "T";
                    }
                }
                map[this.height][this.width] = null;
                this.width++;
            }
        }
        return map;
    }

    protected void ActSleep() {
        int phrase = random.nextInt(2);
        switch (phrase) {
            case (0) ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": Time to clean your weapons!" + DEFAULT_COLOR);
            default ->
                    System.out.println(TERMINATOR_COLOR + name_terminator + ": F#ck walking, where's my cigarette?" + DEFAULT_COLOR);
        }
    }

    protected String[][] Scan(String[][] map) {
        boolean flag = false;
        for (int y = this.height - 3; y <= this.height + 3; y++) {
            for (int x = this.width - 3; x <= this.height + 3; x++) {
                if (y < map.length && y >= 0 && x >= 0 && x < map[0].length) {
                    if (Objects.equals(map[y][x], "Y")) {
                        flag = true;
                    }
                }
            }
        }
        if (flag) {
            System.out.println(TERMINATOR_COLOR + name_terminator + ": The scan was successful. There you are, you son of a b#tch!" + DEFAULT_COLOR);
            Delay();
            System.out.println(TERMINATOR_COLOR + name_terminator + ": Time to taste the bullets of my legendary Winchester! Hasta la vista, baby!" + DEFAULT_COLOR);
            this.game_over = true;
        } else {
            map[this.height][this.width] = null;
            System.out.println(SYSTEM_COLOR + "System message: " + TERMINATOR_COLOR + name_terminator + SYSTEM_COLOR + " called his motorcycle and moved!" + DEFAULT_COLOR);
            while (true) {
                int y = random.nextInt(map.length);
                int x = random.nextInt(map[0].length);
                if (!Objects.equals(map[y][x], "Y")) {
                    this.height = y;
                    this.width = x;
                    map[this.height][this.width] = "T";
                    break;
                }
            }
            for (int y = this.height - 2; y <= this.height + 2; y++) {
                for (int x = this.width - 2; x <= this.height + 2; x++) {
                    if (y < map.length && y >= 0 && x >= 0 && x < map[0].length) {
                        if (Objects.equals(map[y][x], "Y")) {
                            flag = true;
                        }
                    }
                }
            }
            if (flag) {
                System.out.println(TERMINATOR_COLOR + name_terminator + ": The scan was successful. There you are, you son of a b#tch!" + DEFAULT_COLOR);
                Delay();
                System.out.println(TERMINATOR_COLOR + name_terminator + ": Time to taste the bullets of my legendary Winchester! Hasta la vista, baby!" + DEFAULT_COLOR);
                this.game_over = true;
            }
        }
        return map;
    }

    protected String[][] ActAbility(String[][] map) {
        System.out.println(TERMINATOR_COLOR + name_terminator + ": Terminators are a transitional stage. It's half-human, half-machine." + DEFAULT_COLOR);
        Delay();
        System.out.println(TERMINATOR_COLOR + name_terminator + ": I'm starting to scan the area." + DEFAULT_COLOR);
        Delay();
        map = Scan(map);
        return map;
    }

    public String[][] CreateTerminator(String[][] map) {
        while (true) {
            this.height = random.nextInt(map.length - 3) + 3;
            this.width = random.nextInt(map[0].length - 3) + 3;
            if (map[this.height][this.width] == null) {
                map[this.height][this.width] = "T";
                break;
            }
        }
        return map;
    }

    protected String[][] ActT(String[][] map) {
        int act_terminator = random.nextInt(6) + 1;
        switch (act_terminator) {
            case (1) ->// Действие: терминатор движется вверх
                    map = ActUp(map);
            case (2) ->// Действие: терминатор движется вниз
                    map = ActDown(map);
            case (3) ->// Действие: терминатор движется влево
                    map = ActLeft(map);
            case (4) ->// Действие: терминатор движется вправо
                    map = ActRight(map);
            case (5) ->// Действие: отдохнуть
                    ActSleep();
            default -> // Действие: терминатор использует способность
                    map = ActAbility(map);
        }
        Delay();
        if (!this.game_over) {
            System.out.printf(SYSTEM_COLOR + "Characteristics of the " + TERMINATOR_COLOR + name_terminator + SYSTEM_COLOR + ": position(%d,%d), energy(infinite)\n" + DEFAULT_COLOR,
                    this.width, this.height);
        }
        Delay();
        return map;
    }

}
