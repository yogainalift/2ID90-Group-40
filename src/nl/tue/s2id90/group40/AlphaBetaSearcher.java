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

    public AlphaBetaSearcher() {
        this.stopped = false;
    }

    public void stop() {
        stopped = true;
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
            return 1; //the heuristic value of node
        }

        int v;
        Move bestMove;

        if (maxPlayer) {

            v = Integer.MIN_VALUE;
            List<Move> moves = state.getMoves();
            bestMove = moves.get(0);
            for (Move move : moves) {
                state.doMove(move);  // Check if state changes after doMove
                GameNode child = new GameNode(state);
                int alphaBeta = alphaBeta(child, alpha, beta, false, depth--);

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
        }
        else {

            v = Integer.MAX_VALUE;
            List<Move> moves = state.getMoves();
            bestMove = moves.get(0);
            for (Move move : moves) {
                state.doMove(move);  // Check if state changes after doMove
                GameNode child = new GameNode(state);
                int alphaBeta = alphaBeta(child, alpha, beta, true, depth--);

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
        node.setValue(v);
        return v;

    }

    private static class AIStoppedException extends Exception {

        public AIStoppedException() {
            System.out.println("error code 6wow'denough9");
        }
    }
}
