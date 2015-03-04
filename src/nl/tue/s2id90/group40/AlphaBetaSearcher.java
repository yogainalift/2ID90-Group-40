/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nl.tue.s2id90.group40;

import java.util.List;
import nl.tue.s2id90.draughts.DraughtsState;
import org10x10.dam.game.Move;

/**
 *
 * @author s135578
 */
public class AlphaBetaSearcher {

    private boolean stopped;
    private static long start;

    public AlphaBetaSearcher() {
        this.stopped = false;
        this.start = System.currentTimeMillis();
    }

    public void stop() {
        stopped = true;
    }
    
    public long getTime(){
        return start;
    }
    
    int alphaBeta(GameNode node, int alpha, int beta, boolean maxPlayer, int depth)
            throws AIStoppedException {
        //To be able to stop alpha-beta function.
        DraughtsState state = node.getGameState(); //they 
        if (stopped) {
            stopped = false;
            throw new AIStoppedException();
        }
        if (depth == 0 || state.isEndState()) {
            return node.simpleHeuristic(); //the heuristic value of node
        }
        if (System.currentTimeMillis()-getTime()>3000){
            stopped = true;
        }
        
        int v;
        Move bestMove = null;
        
        if (maxPlayer) {
            
            v = Integer.MIN_VALUE;
            List<Move> moves = state.getMoves();
            if (moves != null) bestMove = moves.get(0);
            for (Move move : moves) {
                state.doMove(move);  // Check if state changes after doMove
                GameNode child = new GameNode(state);
                int alphaBeta = alphaBeta(child, alpha, beta, false, depth - 1);
                
                if (v < alphaBeta) {
                    bestMove = move;
                    v = alphaBeta;
                }
                if (v > alpha) {
                    alpha = v;
                }
                state.undoMove(move);
                if (beta <= alpha) {
                    break;
                } 
            }
        } else {

            v = Integer.MAX_VALUE;
            List<Move> moves = state.getMoves();
            if (moves != null) bestMove = moves.get(0);
            for (Move move : moves) {
                state.doMove(move);  // Check if state changes after doMove
                GameNode child = new GameNode(state);
                int alphaBeta = alphaBeta(child, alpha, beta, true, depth - 1);
                
                if (v > alphaBeta) {
                    bestMove = move;
                    v = alphaBeta;
                }
                if (v < alpha) {
                    alpha = v;
                }
                state.undoMove(move);
                if (beta <= alpha) {
                    break;
                } 
            }
            
        }
        node.setBestMove(bestMove);
        return v;
    }

    public static class AIStoppedException extends Exception {

        public AIStoppedException() {
            System.out.println("Your time is out.");
        }
    }
}
