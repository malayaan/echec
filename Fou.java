package pieces;
import pieces.Piece;
 
 
public class Fou extends Piece {
 
    public Fou(boolean estBlanc) {
        super(estBlanc);
    }
 
 
    public void dessiner() {
        if (super.estBlanc()) {
            System.out.print("\u2657");
        } else {
            System.out.print("\u265D");
        }
    }
 
    private static Boolean chemin(int departLigne, int departColonne, int arriveeligne,int arriveColonne) {
        return ((Math.abs(departLigne - arriveeligne) == Math.abs(departColonne
                -  arriveColonne)));
    }
 
     
    public boolean deplacementValide(int departLigne, int departColonne, int arriveeligne,int arriveColonne) {
        return chemin(departLigne, departColonne, arriveeligne, arriveColonne);
    }
 
     
    public int ValeurRelative() {
        return 3;
    }
 
}