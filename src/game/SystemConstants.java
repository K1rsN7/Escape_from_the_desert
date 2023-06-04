package game;

public interface SystemConstants {
    String DEFAULT_COLOR = "\033[0;97m"; // Вернуть текст к стандартному ярко белому
    String VOID_COLOR = "\033[1;90m"; // Цвет пустых мест на карте
    String TERMINATOR_COLOR = "\033[1;91m"; // Цвет текста терминатора
    String PLAYER_COLOR = "\033[1;92m"; // Цвет текста игрока
    String LOGO_COLOR = "\033[1;96m"; // Цвет логотипа игры
    String SYSTEM_COLOR = "\033[1;94m"; // Цвет системных сообщений игры
    String PIT_COLOR = "\033[0;35m";  // Цвет стенок на карте
    String WALL_COLOR = "\033[0;36m"; // Цвет ям на карте
    String name_terminator = "Arnold";
}
