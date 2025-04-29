public class Ship {

    private Character placed;
    private Character successfulShot;
    private Character wrongShot;
    private Character emptyPlace;

    public Ship() {
    }

    public Ship(char placed, char successfulShot, char wrongShot, char emptyPlace) {

        this.placed=placed; this. successfulShot= successfulShot; this.wrongShot=wrongShot; this.emptyPlace=emptyPlace;

    }

    public Character getPlaced() {
        return placed;
    }

    public void setPlaced(Character placed) {
        this.placed = placed;
    }

    public Character getSuccessfulShot() {
        return successfulShot;
    }

    public void setSuccessfulShot(Character successfulShot) {
        this.successfulShot = successfulShot;
    }

    public Character getWrongShot() {
        return wrongShot;
    }

    public void setWrongShot(Character wrongShot) {
        this.wrongShot = wrongShot;
    }

    public Character getEmptyPlace() {
        return emptyPlace;
    }

    public void setEmptyPlace(Character emptyPlace) {
        this.emptyPlace = emptyPlace;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "placed=" + placed +
                ", successfulShot=" + successfulShot +
                ", wrongShot=" + wrongShot +
                ", emptyPlace=" + emptyPlace +
                '}';
    }
}
