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
    int value;
    DraughtsState state;

    public GameNode(DraughtsState s) {
        this.state = s;
        this.value = 0;
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
        int whitePieces=0;
        int blackPieces=0;
        for (Integer i : state.getPieces()) {
            if (i == 1) {
                whitePieces++;
            }
            else if (i == 2) {
                blackPieces++;
            }
        }
        return whitePieces-blackPieces;
    }

    void setValue(int v) {
        value = v;
    }
}
