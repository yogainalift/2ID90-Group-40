package nl.tue.s2id90.group40;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import nl.tue.s2id90.draughts.DraughtsState;
import org10x10.dam.game.Move;

/**
 *
 * @author s135578
 */
public class GameNode {

    Move bestMove;
    DraughtsState state;

    public GameNode(DraughtsState s) {
        this.state = s;
    }

    DraughtsState getGameState() {
        //Returns game state == moves available.
        return state;
    }

    void setBestMove(Move m) {
        bestMove = m;
        //Sets the best move found up till now.
    }

    Move getBestMove() {
        //Finds the best move via alphabeta algorithm.
        //FIRST FIND-TODO
        return bestMove;
    }

    //whites are more = +
    //blacks are more = -
    int simpleHeuristic() {
        int playerOne = 0;
        int playerTwo = 0;

        for (int i = 1; i < 50; i++) {
            if (state.getPiece(i) == 1) {
                playerOne += 10;
                if (inCenter(i)) {
                    playerOne += 1;
                }
                if (firstRow(i)) {
                    playerOne += 1;
                }
                if (lastRow(i)) {
                    playerOne += 2;
                }
            }
            //White queen
            if (state.getPiece(i) == 3) {
                playerOne += 25;
            }
            if (state.getPiece(i) == 2) {
                playerTwo += 10;
                if (inCenter(i)) {
                    playerTwo += 1;
                }
                if (firstRow(i)) {
                    playerTwo += 2;
                }
                if (lastRow(i)) {
                    playerTwo += 1;
                }
            }
            //Black queen
            if (state.getPiece(i) == 4) {
                playerTwo += 25;
            }
        }
        return playerOne - playerTwo;
    }

    //checks if piece is in center
    private boolean inCenter(int i) {
        return i > 15 && i < 21
                || i > 20 && i < 26
                || i > 25 && i < 31
                || i > 30 && i < 36;
    }

    private boolean firstRow(int i) {
        return i > 0 && i < 6;
    }

    private boolean lastRow(int i) {
        return i > 45 && i < 51;
    }

}
