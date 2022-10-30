package pieces; 
import java.util.Scanner;
import pieces.Piece;
import pieces.Echequier;
 
public class Test {
    public static void main(String[] args) {
        Scanner user_input = new Scanner(System.in);
        Echequier echequier = new Echequier();
        System.out.println("Voulez vous demarrer une nouvelle partie ? (OUI)\n Charger une partie existante ?(CHARGER)");
        String rep = user_input.nextLine();
        
        if(rep.equals("OUI")){
            while (echequier.getJeu()) {
                echequier.grille();
                echequier.deplacer();
            }
        }
    }
}