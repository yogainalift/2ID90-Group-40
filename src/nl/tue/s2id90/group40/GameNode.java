package nl.tue.s2id90.group40;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import nl.tue.s2id90.draughts.DraughtsState;
import org10x10.dam.game.Move;

/**
 *
 * @author s135578
 */
public class GameNode {

    Move bestMove;
    int value;
    DraughtsState state;

    public GameNode(DraughtsState s) {
        this.state = s;
        this.value = 0;
        this.bestMove = state.getMoves().get(0);
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
    int getPiecesSize() {
        int[] counter = new int[]{0, 0};
        for (Integer i : state.getPieces()) {
            if (i == 1) {
                counter[0] += 1;
            }
            else if (i == 2) {
                counter[1] += 1;
            }
        }
        return counter[0] - counter[1];
    }

    void setValue(int v) {
        value = v;
    }
}
