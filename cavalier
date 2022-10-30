package pieces;
 
 
 
public class Cavalier extends Piece {
 
    public Cavalier(boolean estBlanc) {
        super(estBlanc);
            }
 
     
    public void dessiner() {
        if (estBlanc){
            System.out.print("\u2658");
        }
        else{
            System.out.print("\u265E");
        }      
    }
     
    private static Boolean chemin(int departLigne, int departColonne,int arriveeligne, int arriveColonne){
        return ((Math.abs(departLigne - arriveeligne) == 2 && Math.abs(departColonne- arriveColonne) == 1)
                || (Math.abs(departLigne - arriveeligne) == 1 && Math.abs(departColonne- arriveColonne) == 2));
    }
 
     
    public boolean deplacementValide(int departLigne, int departColonne, int arriveeligne, int arriveColonne) {
        return chemin(departLigne, departColonne, arriveeligne, arriveColonne);
    }
 
 
}