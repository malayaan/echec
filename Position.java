package pieces;
 
public class Position {
    private int departLigne;
    private int departColonne;
    private int arriveeligne;
    private int arriveeColonne;
 
 
    public Position(int departLigne, int departColonne, int arriveeligne, int arriveeColonne) {
        this.departLigne = departLigne;
        this.departColonne = departColonne;
        this.arriveeligne = arriveeligne;
        this.arriveeColonne = arriveeColonne;
    }
 
    public int getdepartLigne() {
        return departLigne;
    }
 
    public int getdepartColonne() {
        return departColonne;
    }
     
    public int getarriveeligne() {
        return arriveeligne;
    }
 
    public int getarriveeColonne() {
        return arriveeColonne;
    }
 
 
    public void setdepartLigne(int departLigne) {
        this.departLigne = departLigne;
    }
 
    public void setdepartColonne(int departColonne) {
        this.departColonne = departColonne;
    }
     
    public void setarriveeligne(int arriveeligne) {
        this.arriveeligne = arriveeligne;
    }
 
    public void setarriveeColonne(int arriveeColonne) {
        this.arriveeColonne = arriveeColonne;
    }
 
    public boolean equals(Position position) {
        return position.getdepartLigne() == this.getdepartLigne()
                && position.getdepartColonne() == this.getdepartColonne()
                && position.getarriveeligne() == this.getarriveeligne()
                && position.getarriveeColonne() == this.getdepartColonne();
    }
}