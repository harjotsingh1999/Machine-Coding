package SnakeLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;
    private ArrayList<Player> players;
    private int boardSize = 100;

    public Game(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, ArrayList<Player> players) {
        this.players = players;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public Game(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, ArrayList<Player> players,
            int boardSize) {
        this.players = players;
        this.snakes = snakes;
        this.ladders = ladders;
        this.boardSize = boardSize;
    }

    public void startGame() {
        System.out.println("Snakes= " + snakes);
        System.out.println("ladders= " + ladders);
        int rank = 1;
        while (rank < players.size()) {
            for (Player player : players) {
                if (player.getRank() == -1) {
                    boolean someoneWon = false;
                    int roll = new Random().nextInt(6) + 1;
                    someoneWon = handleDiceRoll(player, roll);
                    if (someoneWon) {
                        printWinning(player, rank);
                        player.setRank(rank);
                        rank += 1;
                    }
                }
            }
            if (players.size() == rank) {
                break;
            }
        }
    }

    private boolean handleDiceRoll(Player player, int roll) {
        int currentPos = player.getPosition();
        int newPos = currentPos + roll;
        if (newPos == boardSize) {
            player.setPosition(newPos);
            printMove(player, roll, currentPos, newPos);
            return true;
        } else if (snakes.containsKey(newPos)) {
            newPos = snakes.get(newPos);
            player.setPosition(newPos);
            printMoveSnakeEncountered(player, roll, currentPos, newPos);
        } else if (ladders.containsKey(newPos)) {
            newPos = ladders.get(newPos);
            player.setPosition(newPos);
            printMoveLadderClimbed(player, roll, currentPos, newPos);
            if (newPos == boardSize)
                return true;
        } else if (newPos < boardSize) {
            player.setPosition(newPos);
            printMove(player, roll, currentPos, newPos);
        }
        return false;
    }

    private void printMoveLadderClimbed(Player player, int roll, int currentPos, int newPos) {
        System.out.println(player.getName() + " rolled a " + roll + " and moved from " + currentPos + " to "
                + (currentPos + roll) + " climbed a ladder, up to " + newPos);
    }

    private void printMoveSnakeEncountered(Player player, int roll, int currentPos, int newPos) {
        System.out.println(player.getName() + " rolled a " + roll + " and moved from " + currentPos + " to "
                + (currentPos + roll) + " encountered a Snake, back to " + newPos);
    }

    private void printMove(Player player, int roll, int currentPos, int newPos) {
        System.out.println(player.getName() + " rolled a " + roll + " and moved from " + currentPos + " to " + newPos);
    }

    private void printWinning(Player player, int rank) {
        System.out.println(player.getName() + " wins the game with rank " + rank);
    }

}
