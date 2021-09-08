package SnakeLadder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Game {

    private boolean isGameComplete = false;
    private HashMap<Integer, Integer> snakes;
    private HashMap<Integer, Integer> ladders;
    private ArrayList<Player> players;

    public Game(HashMap<Integer, Integer> snakes, HashMap<Integer, Integer> ladders, ArrayList<Player> players) {
        this.players = players;
        this.snakes = snakes;
        this.ladders = ladders;
    }

    public void startGame() {
        System.out.println("Snakes= " + snakes);
        System.out.println("ladders= " + ladders);
        System.out.println("Players= " + players);
        while (!isGameComplete) {
            for (Player player : players) {
                int roll = new Random().nextInt(6) + 1;
                isGameComplete = handleDiceRoll(player, roll);
                if (isGameComplete) {
                    printWinning(player);
                    break;
                }
            }
            if (isGameComplete) {
                break;
            }
        }
    }

    private boolean handleDiceRoll(Player player, int roll) {
        int currentPos = player.position;
        int newPos = currentPos + roll;
        if (newPos == 100) {
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
            if (newPos == 100)
                return true;
        } else if (newPos < 100) {
            player.setPosition(newPos);
            printMove(player, roll, currentPos, newPos);
        }
        return false;
    }

    private void printMoveLadderClimbed(Player player, int roll, int currentPos, int newPos) {
        System.out.println(player.name + " rolled a " + roll + " and moved from " + currentPos + " to "
                + (currentPos + roll) + " climbed a ladder, up to " + newPos);
    }

    private void printMoveSnakeEncountered(Player player, int roll, int currentPos, int newPos) {
        System.out.println(player.name + " rolled a " + roll + " and moved from " + currentPos + " to "
                + (currentPos + roll) + " encountered a Snake, back to " + newPos);
    }

    private void printMove(Player player, int roll, int currentPos, int newPos) {
        System.out.println(player.name + " rolled a " + roll + " and moved from " + currentPos + " to " + newPos);
    }

    private void printWinning(Player player) {
        System.out.println(player.name + " wins the game");
    }

}
