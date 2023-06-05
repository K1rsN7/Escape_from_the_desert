package game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game implements SystemConstants {
    public static void Start_game() {
        // Все переменные, необходимые для функционирования игры
        String[][] map;
        int count = 0; // Количество ходов для логов победителей
        Random random = new Random();
        int exit_height;
        int exit_width;
        boolean exit_game = false;
        boolean game_over;
        // Создание карты, заполнение её препятствиями, генерация секретного выхода, помещение на карту персонажей
        map = new String[25][25];
        for (int y = 0; y < 25; y++) {
            for (int x = 0; x < 25; x++) {
                map[y][x] = null;
            }
        }
        map[0][0] = "Y";
        for (int k = 0; k <= 100; k++) {
            while (true) {
                int y = random.nextInt(25 - 1);
                int x = random.nextInt(25 - 1);
                if (map[y][x] == null) {
                    map[y][x] = "#";
                    break;
                }
            }
        }
        for (int k = 0; k <= 100; k++) {
            while (true) {
                int y = random.nextInt(25 - 1);
                int x = random.nextInt(25 - 1);
                if (map[y][x] == null) {
                    map[y][x] = "0";
                    break;
                }
            }
        }
        do {
            exit_height = random.nextInt(24) + 1;
            exit_width = random.nextInt(24) + 1;
        } while (map[exit_height][exit_width] != null);
        Player player = new Player();
        player.energy = 100;
        String name_input; // Временная переменная для ввода никнейма игрока
        // Вступительная часть для игрока
        System.out.println(LOGO_COLOR + """
                 _       __     __                             __           __  __      \s
                | |     / /__  / /________  ____ ___  ___     / /_____     / /_/ /_  ___\s
                | | /| / / _ \\/ / ___/ __ \\/ __ `__ \\/ _ \\   / __/ __ \\   / __/ __ \\/ _ \\
                | |/ |/ /  __/ / /__/ /_/ / / / / / /  __/  / /_/ /_/ /  / /_/ / / /  __/
                |__/|__/\\___/_/\\___/\\____/_/ /_/ /_/\\___/   \\__/\\____/   \\__/_/ /_/\\___/\s
                                                                                        \s
                         ______                              ____                  \s
                        / ____/_____________ _____  ___     / __/________  ____ ___\s
                       / __/ / ___/ ___/ __ `/ __ \\/ _ \\   / /_/ ___/ __ \\/ __ `__ \\
                      / /___(__  ) /__/ /_/ / /_/ /  __/  / __/ /  / /_/ / / / / / /
                     /_____/____/\\___/\\__,_/ .___/\\___/  /_/ /_/   \\____/_/ /_/ /_/\s
                                          /_/                                      \s
                               __  __            ____                      __\s
                              / /_/ /_  ___     / __ \\___  ________  _____/ /_
                             / __/ __ \\/ _ \\   / / / / _ \\/ ___/ _ \\/ ___/ __/
                            / /_/ / / /  __/  / /_/ /  __(__  )  __/ /  / /_ \s
                            \\__/_/ /_/\\___/  /_____/\\___/____/\\___/_/   \\__/ \s
                            
                            """ + SYSTEM_COLOR);
        player.Delay();
        player.Delay();
        System.out.println("The goal of the game: Find a secret exit among the desert!");
        player.Delay();
        player.Delay();
        System.out.println("To make it not easy for you to do this, obstacles and a terminator have been added to the game :)");
        player.Delay();
        player.Delay();
        System.out.println(TERMINATOR_COLOR + "Terminator : Exactly noticed the doc!");
        player.Delay();
        player.Delay();
        Scanner sc = new Scanner(System.in);
        // Создание никнейма для игрока
        System.out.print(SYSTEM_COLOR + "Enter the desired username: " + PLAYER_COLOR);
        name_input = sc.nextLine();
        if (!name_input.isEmpty()) {
            player.name_player = name_input;
        } else {
            player.name_player = "Player";
        }
        player.Delay();
        System.out.println(SYSTEM_COLOR + "System message: A " + PLAYER_COLOR + player.name_player + SYSTEM_COLOR + " has been created" + DEFAULT_COLOR);
        player.Delay();
        Terminator terminator = new Terminator();
        System.out.println(SYSTEM_COLOR + "System message: A terminator has been created" + DEFAULT_COLOR);
        player.Delay();
        map = terminator.CreateTerminator(map);
        System.out.println(TERMINATOR_COLOR + "Terminator: My name is " + name_terminator +
                ". I have risen from the ashes and am ready to kill you!" + DEFAULT_COLOR);
        player.Delay();
        player.Delay();
        int act;
        System.out.print(SYSTEM_COLOR + "Notation on the map:\n" +
                PLAYER_COLOR + "   Y" + SYSTEM_COLOR + " - Player (" + PLAYER_COLOR + "You" + SYSTEM_COLOR + ")\n" +
                TERMINATOR_COLOR + "   T" + SYSTEM_COLOR + " - Terminator, you can't fall for him!\n" +
                PIT_COLOR + "   #" + SYSTEM_COLOR + " - Walls, you can't pass through them, but not for the terminator!\n" +
                WALL_COLOR + "   0" + SYSTEM_COLOR + " - Pits, falling into them you die!\n\n");
        player.Delay();
        player.Delay();
        player.Delay();
        player.Delay();
        player.Act(6, map, exit_height, exit_width);
        player.Delay();
        // Алгоритм игры
        while (!exit_game) {
            while (true) {
                System.out.println(SYSTEM_COLOR + """
                        Action:\s
                        1 - Upward movement
                        2 - Downward movement
                        3 - Left movement
                        4 - Right movement
                        5 - Restore energy
                        6 - Open the map
                        7 - Exit game""" + DEFAULT_COLOR);
                System.out.print(PLAYER_COLOR + player.name_player + " action: ");
                try {
                    act = sc.nextInt();
                } catch (InputMismatchException e) {
                    act = 0;
                    sc.next();
                }
                if ((act > 0) && (act < 8)) {
                    break;
                } else {
                    System.out.println(SYSTEM_COLOR + "System error: there is no such action" + DEFAULT_COLOR);
                }
            }
            System.out.println();
            if (act == 7) {
                System.out.println(TERMINATOR_COLOR + name_terminator + ": Seriously? Did you give up so quickly?" + DEFAULT_COLOR);
                player.Delay();
                System.out.println(SYSTEM_COLOR + "System message: Thank you for playing this game. It was very nice to play with you :)" + DEFAULT_COLOR);
                break;
            } else {
                map = terminator.ActT(map);
                map = terminator.ActT(map);
                player.Delay();
                game_over = player.GetGameOver() || terminator.GetGameOver();
                if (game_over) {
                    System.out.println(SYSTEM_COLOR + "System message:" + TERMINATOR_COLOR + " You're dead!!! X_X GAME OVER!!!" + DEFAULT_COLOR);
                }
                map = player.Act(act, map, exit_height, exit_width);
                count += 1;
            }
            game_over = player.GetGameOver() || terminator.GetGameOver();
            exit_game = player.GetExitGame();
            if (exit_game) {
                try {
                    FileWriter fw = new FileWriter("log_list_of_winners.txt", true);
                    fw.write(PLAYER_COLOR + player.name_player + DEFAULT_COLOR + ": " + count + " moves" + "\n");
                    fw.close();
                } catch (IOException e) {
                    System.out.println(SYSTEM_COLOR + "Error: " + e.getMessage() + DEFAULT_COLOR);
                }

            }
            if (game_over) {
                System.out.println(SYSTEM_COLOR + "System message:" + TERMINATOR_COLOR + " You're dead!!! X_X GAME OVER!!!" + DEFAULT_COLOR);
                exit_game = true;
            }
        }
    }
}
