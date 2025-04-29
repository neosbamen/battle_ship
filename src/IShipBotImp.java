import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IShipBotImp extends Ship implements NavalSystem{
    List<Ship> shipBotList=new ArrayList<>();
    public IShipBotImp(){

        for (int z=0;z<81;z++){

            shipBotList.add(new Ship());
            shipBotList.get(z).setEmptyPlace('.');
            shipBotList.get(z).setPlaced('.');
            shipBotList.get(z).setSuccessfulShot('.');
            shipBotList.get(z).setWrongShot('.');
        }
    }
    @Override
    public void emptyPainting() {

    }
    @Override
    public void placementPainting() {
        int newLine = 0;

        for (int i = 0; i < shipBotList.size(); i++) {
            newLine ++;
            boolean placedShip=shipBotList.get(i).getPlaced()=='#';
            if (placedShip) {
                System.out.print(shipBotList.get(i).getPlaced() + "  ");

            } else  {
                System.out.print(shipBotList.get(i).getEmptyPlace() + "  ");

            }
            if (newLine % 9 == 0) {
                System.out.println();
            }
        }


    }
    @Override
    public boolean placementBoardShip(int[] place) {

        Random random=new Random();
        place[0]=random.nextInt(1,81);
        int initialP=place[0];
       place[1]=random.nextInt(initialP,82);
        int lastP=place[1];
        int num=initialP;
        int initialCount=initialP;
        int howBigIs=0;
        while (initialCount<lastP){
            initialCount++;
            howBigIs++;
        }

        boolean multiploLimit=false;
        boolean positionV= howBigIs>=9;

        while (num<=lastP) {
            if (!shipBotList.get(num - 1).getPlaced().equals('#')&& positionV) {
                num+=9;
            } else if (!shipBotList.get(num - 1).getPlaced().equals('#')&&!positionV) {
                num ++;
            } else {
                initialP=random.nextInt(1,81);
                lastP=random.nextInt(initialP,82);
            }
        }

        if (positionV){
            while (initialP<=81&&initialP<=lastP){
                shipBotList.get(initialP-1).setPlaced('#');
                initialP+=9;

            }

        } else {
            while (initialP<=lastP&&!multiploLimit) {
                shipBotList.get(initialP-1).setPlaced('#');
                multiploLimit= initialP%9==0;
                initialP++;
            }
        }
        return true;
    }
    @Override
    public void boardShipShot(int shot) {



        boolean existShip = shipBotList.get(shot - 1).getPlaced()==('#');

        if (existShip) {
            shipBotList.get(shot-1).setSuccessfulShot('O');
            shipBotList.get(shot-1).setWrongShot('.');
        }else {
            shipBotList.get(shot-1).setWrongShot('X');
            shipBotList.get(shot-1).setSuccessfulShot('.');
        }

        int newLine = 0;

        for (int i = 0; i < shipBotList.size(); i++) {
            newLine ++;

            if (shipBotList.get(i).getWrongShot()=='X') {
                System.out.print(shipBotList.get(i).getWrongShot() + "  ");
                shipBotList.get(i).setWrongShot('.');
            } else if (shipBotList.get(i).getSuccessfulShot()=='O') {
                System.out.print(shipBotList.get(i).getSuccessfulShot() + "  ");

            } else {
                System.out.print(shipBotList.get(i).getEmptyPlace() + "  ");

            }
            if (newLine % 9 == 0) {
                System.out.println();
            }
        }
    }

    @Override
    public boolean gameOverNotification() {

        int placedCounter=0;
        int shotCounter=0;

        for (int x=0;x<shipBotList.size();x++){
            if (shipBotList.get(x).getPlaced().equals('#')) {
                placedCounter++;
            }}

            for (int y=0;y<shipBotList.size();y++){
                if (shipBotList.get(y).getSuccessfulShot().equals('O')) {
                    shotCounter++;
                }
            }
        return placedCounter==shotCounter;
    }
}