package pieces;
 
public abstract class Piece {
 
    boolean estBlanc;
    public Piece(boolean estBlanc) {
        this.estBlanc = estBlanc;
    }
    public boolean estBlanc() {
        return estBlanc;
    }
    public abstract void dessiner();
    public abstract boolean deplacementValide(int departLigne, int departColonne, int arriveeligne, int arriveColonne);
}