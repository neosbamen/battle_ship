import java.util.ArrayList;
import java.util.List;
public class IShipImp extends Ship implements NavalSystem {
    List<Ship> shipList = new ArrayList<>();

    public IShipImp() {

        for (int z = 0; z < 81; z++) {
            shipList.add(new Ship('.', '.', '.', '.'));
        }
    }

    @Override
    public void emptyPainting() {
        List<String> emptyList = new ArrayList<>();
        String count = "01,02,03,04,05,06,07,08,09,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64,65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81";
        String[] numbers = count.split(",");
        for (int z = 0; z < 81; z++) {
            emptyList.add(numbers[z]);
        }
        int newLine = 0;

        for (int i = 0; i < emptyList.size(); i++) {
            newLine += 1;
            System.out.print(emptyList.get(i) + " ");
            if (newLine % 9 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public boolean placementBoardShip(int[] place) {//aqui puede estar un error: se cuelan espacios en blanco desde la entrada en main

        try {
            int howBigIs = 0;
            int initialP = place[0]; //que pasa si el num que representa la primera posicion es mas grande que el num que respresenta la ultima
            int lastP = place[1];
            int initialCount = initialP;

            if (initialP>lastP){
                System.out.println("Introduce las posiciones de tu barco en orden ascendente");
                System.out.println("Vuelve a intentarlo");
                return false;
            }

            while (initialCount < lastP) {
                initialCount++;
                howBigIs++;
            }
            boolean isShipVertical = howBigIs >= 9; //Cuando sea true la logica se comportará entendiendo que el barco pretende ser vertical
            boolean multiploLimit = false;
            int num = initialP;

            while (num <= lastP) {
                if (!shipList.get(num - 1).getPlaced().equals('#') && isShipVertical) {
                    num += 9;
                } else if (!shipList.get(num - 1).getPlaced().equals('#') && !isShipVertical) {
                    num++;
                } else {
                    System.out.println("Uno o mas posiciones coinciden con una anterior");
                    return false;
                }
            }

            if (isShipVertical) {
                System.out.println("Este barco tiene orientacion vertical y ha sido ubicado en: ");
                while (initialP <= 81 && initialP <= lastP) {
                    shipList.get(initialP - 1).setPlaced('#'); //
                   // int[] barcoPosicionado = new int[];
                    System.out.println("Posicion " + initialP);
                    initialP += 9;
                }

            } else {
                System.out.println("Este barco tiene orientacion horizontal y ha sido ubicado en: ");
                while (initialP <= lastP && !multiploLimit) {
                    shipList.get(initialP - 1).setPlaced('#');
                    multiploLimit = initialP % 9 == 0;
                    System.out.println("Posicion " + initialP);
                    initialP++;
                }
            }

            return true;
        } catch (Exception e) {

            System.out.println("Una de las posiciones de tu barco excede los limites");

        }
        return false;
    }

    public void boardShipShot(int shot) {

        boolean existShip = shipList.get(shot - 1).getPlaced() == ('#');

        if (existShip) {
            shipList.get(shot - 1).setSuccessfulShot('O');
            //shipList.get(shot - 1).setWrongShot('.');
        } else {
            shipList.get(shot - 1).setWrongShot('X');
            //shipList.get(shot - 1).setSuccessfulShot('.');
        }

        int newLine = 0;

        for (int i = 0; i < shipList.size(); i++) {
            newLine++;

            if (shipList.get(i).getWrongShot() == 'X') {
                System.out.print(shipList.get(i).getWrongShot() + "  ");
                shipList.get(i).setWrongShot('.');
            } else if (shipList.get(i).getSuccessfulShot() == 'O') {
                System.out.print(shipList.get(i).getSuccessfulShot() + "  ");

            } else {
                System.out.print(shipList.get(i).getEmptyPlace() + "  ");

            }
            if (newLine % 9 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public boolean gameOverNotification() {//que hace esta linea?

        int placedCounter = 0;
        int shotCounter = 0;

        for (int x = 0; x < shipList.size(); x++) {
            if (shipList.get(x).getPlaced().equals('#')) {
                placedCounter++;
            }
        }

        for (int y = 0; y < shipList.size(); y++) {
            if (shipList.get(y).getSuccessfulShot().equals('O')) {
                shotCounter++;
            }
        }
        return placedCounter==shotCounter;
}


    @Override
    public void placementPainting() {
        int newLine = 0;


        for (int i = 0; i < shipList.size(); i++) {
            newLine ++;
            boolean placedShip=shipList.get(i).getPlaced()=='#';
            if (placedShip) {
                System.out.print(shipList.get(i).getPlaced() + "  ");

            } else  {
                System.out.print(shipList.get(i).getEmptyPlace() + "  ");

            }
            if (newLine % 9 == 0) {
                System.out.println();
            }
        }

    }

}
