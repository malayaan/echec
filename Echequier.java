package pieces;
 
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
 
import pieces.Piece;
import pieces.Position;
 
 
public class Echequier {
 
    private Boolean Jeu;
    private Piece[][] echequier = new Piece[8][8];
    Scanner user_input = new Scanner(System.in);
    private static int departLigne, departColonne, arriveeligne, arriveColonne;
    private static Boolean Tourdujoueur;
    // Définit sur true si movement est invalide. Demande de nouveau l'utilisateur dans move ()
    // méthode.
    private static Boolean MouvementIncorrect = false;
    // Contient une chaîne avec l'entrée de l'utilisateur pour les instructions de déplacement
    String mouvement;
 
 
 
    public Echequier() {
 
        initialisation_de_la_grille(echequier);
        Jeu = true;
 
    }
 
 
    public Boolean getJeu() {
        return this.Jeu;
    }
 
 
    private static void initialisation_de_la_grille(Piece[][] echequier) {
        // un échiquier avec une matrice 8x8 de pièces
        // les lignes [0] et [1] sont noires
        // les lignes [6] et [7] sont blanches
 
        for (int ligne = 0; ligne < echequier.length; ligne++) {
            for (int colonne = 0; colonne < echequier[ligne].length; colonne++) {
                if (ligne == 0) {
                    switch (colonne) {
                    case 0:echequier[ligne][colonne] = new Tour(false);break;
                    case 1:echequier[ligne][colonne] = new Cavalier(false);break;
                    case 2:echequier[ligne][colonne] = new Fou(false);break;
                    case 3:echequier[ligne][colonne] = new Reine(false);break;
                    case 4:echequier[ligne][colonne] = new Roi(false);break;
                    case 5:echequier[ligne][colonne] = new Fou(false);break;
                    case 6:echequier[ligne][colonne] = new Cavalier(false);break;
                    case 7:echequier[ligne][colonne] = new Tour(false);break;
                    }
                } else if (ligne == 1) {
                    echequier[ligne][colonne] = new Pion(false);
                } else if (ligne == 6) {
                    echequier[ligne][colonne] = new Pion(true);
                } else if (ligne == 7) {
                    switch (colonne) {
                    case 0:echequier[ligne][colonne] = new Tour(true);break;
                    case 1:echequier[ligne][colonne] = new Cavalier(true);break;
                    case 2:echequier[ligne][colonne] = new Fou(true);break;
                    case 3:echequier[ligne][colonne] = new Reine(true);break;
                    case 4:echequier[ligne][colonne] = new Roi(true);break;
                    case 5:echequier[ligne][colonne] = new Fou(true);break;
                    case 6:echequier[ligne][colonne] = new Cavalier(true);break;
                    case 7:echequier[ligne][colonne] = new Tour(true);break;
                    }
                } else {
                    echequier[ligne][colonne] = null;
                }
            }
        }
 
        // Affecte aléatoirement qui commence en premier (noir ou blanc)
        Random rand = new Random();
        Tourdujoueur = rand.nextBoolean();
 
    }
 
 
    public void grille() {
         
        System.out.println("\ta\tb\tc\td\te\tf\tg\th");
        System.out.println("------------------------------------------------------------------");
        for (int ligne = 0; ligne < echequier.length; ligne++) {
            System.out.print(8 - ligne + "|\t");
            for (int colonne = 0; colonne < echequier[ligne].length; colonne++) {
                if (echequier[ligne][colonne] != null) {
                    echequier[ligne][colonne].dessiner();
                    System.out.print("\t");
 
                }
                else {
                    System.out.print("\t");
                         
                }
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------------");
    }
 
 
    private boolean mouvementValide(boolean Echec) {
 
        // On verifie que le chemin ne sort pas de l'échequier
        if (departLigne < 0 || departLigne > 7 || departColonne < 0 || departColonne > 7 || arriveeligne < 0 || arriveeligne > 7 || arriveeligne < 0 || arriveColonne > 7) {
            System.out.println("Le mouvement est en dehors de l'echequier");
            return false;
        }
 
        // On vérifie que l'origine n'est pas null
        if (echequier[departLigne][departColonne] == null) {
            System.err.println("L'origine est vide");
            return false;
        }
 
        // On verifie que c'est bien le tour du joueur pour qu'il joue
        if ((echequier[departLigne][departColonne].estBlanc && !Tourdujoueur) || (!echequier[departLigne][departColonne].estBlanc && Tourdujoueur)) {
            if(Echec == false) 
            System.err.println("Ce n'est pas votre tour");
            return false;
        }
 
        // On verifie que les déplacement spécifique de la pièces sont bien respectés
        if (!echequier[departLigne][departColonne].deplacementValide(departLigne, departColonne, arriveeligne,arriveColonne)) {
            if(Echec == false) 
                System.err.println("Cette pièce ne bouge pas comme ça");
            return false;
        }
 
        // cette déclaration arrête la déclaration pour vérifier si le blanc atterit
        // blanc de l'exécution estBlanc() sur un espace null
        if (echequier[arriveeligne][arriveColonne] == null) {          
            //Chemin pas possible si une piece veut fauter une auter piece SAUF POUR LE CAVALIER
            if(!(echequier[departLigne][departColonne] instanceof Cavalier)){
                int deplaceX = 0, deplaceY = 0;
                if(departLigne < arriveeligne) deplaceX = 1; else if(departLigne > arriveeligne)deplaceX = -1;
                if(departColonne < arriveColonne) deplaceY = 1; else if(departColonne > arriveColonne) deplaceY = -1;
 
                int i = departLigne, j = departColonne;
                while(i != arriveeligne || j != arriveColonne){
                    if(i != arriveeligne) i+= deplaceX;
                    if(j != arriveColonne) j+= deplaceY;
 
                    if(echequier[i][j] != null){
                        System.err.println("Cette pieces ne peut pas sauter un pion");
                        return false;
                    }
                }
            }
            return true;
        }
         
        if(echequier[departLigne][departColonne] instanceof Pion && (arriveeligne == arriveColonne) && this.echequier[arriveeligne][arriveColonne] == null ){
            System.out.println("Ne peut pas ce deplacer en diagonale");
            return false;
        }
 
        // On verifie que un pion Blanc ne veut pas se poser sur un autre pion Blanc
        if (echequier[departLigne][departColonne].estBlanc && echequier[arriveeligne][arriveColonne].estBlanc) {
            System.err.println("Les blancs ne peuvent pas atterrir sur le blanc");
            return false;
        }
 
        // On verifie que un pion Noir ne veut pas se poser sur un autre pion Noir
        if (!echequier[departLigne][departColonne].estBlanc && !echequier[arriveeligne][arriveColonne].estBlanc) {
            System.err.println("Le noir ne peut pas atterrir sur le noir");
            return false;
        }
         
        return true;
    }
 
    public boolean ECHEC(){
        //Piece ROI;
        ArrayList<Position> P = new ArrayList<Position>();
        boolean joueur_adverse = Tourdujoueur;
        boolean joueur_courant = !Tourdujoueur;
        //On recherche la position du ROI
        for(int ligne=0; ligne<8; ligne++){
            for(int colonne=0; colonne<8; colonne++){
                if(this.echequier[ligne][colonne] != null  && this.echequier[ligne][colonne].estBlanc == Tourdujoueur && this.echequier[ligne][colonne] instanceof Roi)
                {
                    //ROI = this.echequier[ligne][colonne];
                    arriveeligne = ligne;
                    arriveColonne = colonne;
                     
                }
            }
        }
        for(int ligne=0; ligne<8; ligne++){
            for(int colonne=0; colonne<8; colonne++){
                    if(this.echequier[ligne][colonne] != null && this.echequier[ligne][colonne].estBlanc == !Tourdujoueur)
                    {
                        departLigne = ligne;
                        departColonne = colonne;
                         
                        if(mouvementValide(true))
                            P.add(new Position(departLigne,departColonne,arriveeligne,arriveColonne));
                            System.out.println(departLigne+""+departColonne);
                    }      
            }
        }
        if(P.isEmpty())
            return false;
        else
            return true;
    }
 
 
    public void deplacer() {
        if (MouvementIncorrect) {
            System.err.println("Le mouvement n'est pas valide. Veuillez réessayer:");
            MouvementIncorrect = false;
        }
 
        else if (Tourdujoueur) {
            System.out.println( "\nLe joueur BLANC doit jouer\n");
        } else {
            System.out.println("\nLe joueur NOIR doit jouer\n");
        }
 
        mouvement = user_input.nextLine();
 
 
        // convertir en minuscules
        String lowerCase = mouvement.toLowerCase();
        // analyse la chaîne de déplacement en composants
        String[] components = lowerCase.split(" ");
 
        // si vous supposez que le déplacement est "e1 à e5" alors
        // composants [0] .chartAt (0) = 'e'
        // composants [0] .charAt (1) = '1'
 
        // utilisez des chars dans les composants pour définir les coordonnées du tableau
        // déplace l'origine et déplace la destination
 
        departLigne = 7 - (components[0].charAt(1) - '1');
        departColonne = components[0].charAt(0) - 'a';
        arriveeligne = 7 - (components[2].charAt(1) - '1');
        arriveColonne = components[2].charAt(0) - 'a';
 
        if (mouvementValide(false)) {
            // mettre une pièce dans la destination
            echequier[arriveeligne][arriveColonne] = echequier[departLigne][departColonne];
            // vide l'origine du mouvement
            echequier[departLigne][departColonne] = null;
            if(ECHEC() == true)
            {System.out.println("\n ECHEC !\n");}
            Tourdujoueur = !Tourdujoueur;
             
                 
        }
        else {
            MouvementIncorrect = true;
            mouvementValide(false);
            if(ECHEC() == true)
            {System.out.println("\n ECHEC !\n");}
             
 
        }
 
    }
 
}