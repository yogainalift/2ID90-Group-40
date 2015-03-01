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

    AlphaBetaSearcher abs;
    
    public TselepidasPlayer() {
        super(UninformedPlayer.class.getResource("resources/optimist.png"));
    }

    @Override
    /**
     * @return a random move *
     */
    public Move getMove(DraughtsState s) {
        List<Move> moves = s.getMoves();
        
        //init a gamenode and getWhiteSize or getBlackSize is possible.
        
        if (moves.size() == 1) {
            return moves.get(0);
        }
        Move best = null;
        
        GameNode someNode = new GameNode(s);
        abs = new AlphaBetaSearcher();
        int i = 1;
        try {
            while (true) {
                int score = abs.alphaBeta(someNode, Integer.MIN_VALUE, Integer.MAX_VALUE, s.isWhiteToMove(), i);
                best = someNode.getBestMove();
                System.out.println(i);
                i++;
            }
        } catch (AlphaBetaSearcher.AIStoppedException ex) {
            //Logger.getLogger(TselepidasPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return best;
    }

    @Override
    public Integer getValue() {
        return 0;
    }
}
