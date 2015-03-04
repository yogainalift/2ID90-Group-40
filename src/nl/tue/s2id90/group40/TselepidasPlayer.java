package nl.tue.s2id90.group40;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import nl.tue.s2id90.draughts.DraughtsState;
import nl.tue.s2id90.draughts.player.DraughtsPlayer;
import org10x10.dam.game.Move;

/**
 * A simple draughts player that plays the first moves that comes to mind and
 * values all moves with value 0.
 *
 * @author huub
 */
public class TselepidasPlayer extends DraughtsPlayer {
    
    private static boolean stopped;
    private static long startTime;
    
    public TselepidasPlayer() {
        super(UninformedPlayer.class.getResource("resources/optimist.png"));
    }

    @Override
    /**
     * @return a random move *
     */
    public Move getMove(DraughtsState s) {
        List<Move> moves = s.getMoves();
        
        /**
         * If there is only one move, do it.
         */
        if (moves.size() == 1) {
            return moves.get(0);
        }
        
        Move best = null; // Storing the last best node of iterative deepening
        
        GameNode someNode = new GameNode(s);
        int i = 1;
        try {
            while (true) {
                System.out.println(i);
                startTime = System.currentTimeMillis();
                int score = alphaBeta(someNode, Integer.MIN_VALUE, Integer.MAX_VALUE, s.isWhiteToMove(), i++);
                stopped = false;
                best = someNode.getBestMove();
            }
        } catch (AIStoppedException ex) { }
        
        return best;
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
    
    @Override
    public Integer getValue() {
        return 0;
    }
    
    @Override
    public void stop() {
        this.stopped = true;
    }
}
