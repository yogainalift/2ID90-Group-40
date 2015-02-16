package nl.tue.s2id90.group40;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;
import nl.tue.s2id90.draughts.DraughtsState;
import org10x10.dam.game.Move;

/**
 *
 * @author s135578
 */
public class GameNode {

    GameNode node;
    Move bestMove;

    public GameNode(GameNode node) {
        this.node = node;
    }

    public GameNode() {
        this.state = new DraughtsState();
        this.bestMove = state.getMoves().get(0);
    }

    DraughtsState getGameState() {
        //Returns game state == moves available.
        state = new DraughtsState();
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
}
