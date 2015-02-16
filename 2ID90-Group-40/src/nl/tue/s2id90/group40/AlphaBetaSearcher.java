/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.tue.s2id90.group40;

import java.util.List;
import nl.tue.s2id90.game.GameState;
import org10x10.dam.game.Move;

/**
 *
 * @author s135578
 */
public class AlphaBetaSearcher {
    GameState state;
    private boolean stopped;
    
    public AlphaBetaSearcher() {
        this.stopped = false;
    }
    
    public void stop() {
        stopped = true;
    }
    
    int alphaBeta (GameNode node, int alpha, int beta) 
        throws AIStoppedException {
            if (stopped) {
                stopped = false;
                throw new AIStoppedException();
            }
        
        state = (GameState) node.getGameState();
        List <Move> moves = state.getMoves();
        for (Move move  : moves) {
            state.doMove(move);
            //...//recursive call
            state.undoMove(move);
        }
        // node.setBestMove(bestMove);
        //...//
        return 1;  
    }

    private static class AIStoppedException extends Exception {

        public AIStoppedException() {
            System.out.println("error code 6wow'denough9");
        }
    }
}
