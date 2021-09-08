package SnakeLadder;

import java.util.*;

public class InputDriver {

    public static void main(String[] args) {

        Scanner read = new Scanner(System.in);

        int snakeCount = read.nextInt();
        HashMap<Integer, Integer> snakes = new HashMap<>();
        for (int i = 0; i < snakeCount; i++) {
            int head = read.nextInt();
            int tail = read.nextInt();
            snakes.put(head, tail);
        }
        int ladderCount = read.nextInt();
        HashMap<Integer, Integer> ladders = new HashMap<>();
        for (int i = 0; i < ladderCount; i++) {
            int start = read.nextInt();
            int end = read.nextInt();
            ladders.put(start, end);
        }

        int playerCount = read.nextInt();
        ArrayList<Player> players = new ArrayList<>();
        for (int i = 0; i < playerCount; i++) {
            String playerName = read.next();
            players.add(new Player(playerName));
        }
        read.close();

        Game game = new Game(snakes, ladders, players);
        game.startGame();
    }
}
