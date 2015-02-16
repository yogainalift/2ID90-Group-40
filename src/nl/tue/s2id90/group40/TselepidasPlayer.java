package nl.tue.s2id90.group40;

import java.util.List;
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

    public TselepidasPlayer() {
        super(UninformedPlayer.class.getResource("resources/optimist.png"));
    }

    @Override
    /**
     * @return a random move *
     */
    public Move getMove(DraughtsState s) {
        List<Move> moves = s.getMoves();
        System.out.println(moves);
        s.doMove(moves.get(0));
        //moves = s.getMoves();
        System.out.println(moves);
        System.out.println(s);
        return moves.get(0);
    }

    @Override
    public Integer getValue() {
        return 0;
    }
}