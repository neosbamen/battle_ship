import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random randomNum=new Random();
        IShipImp iShipImpHuman = new IShipImp();
        IShipBotImp iShipBotImp=new IShipBotImp();
        List<Integer>totalAttacks=new ArrayList<>();

        System.out.println("Bienvenido a Ship Naval War");
        boolean stillPlaying = true;
        int humanShipOrientationPositioning;

        do {
            System.out.println("1. Ingresar Barco\n" +
                    "2. Iniciar ataque\n" +
                    "3. Revisar tus posiciones\n" +
                            "4. Salir"
                     );
            int chosenOption = scanner.nextInt();
            scanner.nextLine();

                switch (chosenOption) {

                    case 1:

                            iShipImpHuman.emptyPainting();
                            System.out.println("Ingresa la ubicacion inicial y final en la que posicionar tu barco, separadas por comas.");
                            String inputPlace= scanner.nextLine();
                            String[] inputSplit = inputPlace.split(",");
                            int[] positionByInt = new int[inputSplit.length];
                            /*int num=0;
                            int iterator=0;*/
                            for (int y = 0; y <positionByInt.length; y++) {
                                //iterator++;
                                positionByInt[y] = Integer.parseInt(inputSplit[y]);
                            }
                            boolean repeat=iShipImpHuman.placementBoardShip(positionByInt);
                            if (repeat){

                                int initial=0;
                                int lastOne=0;
                                int[]numbers={initial,lastOne};

                                iShipBotImp.placementBoardShip(numbers);
                                }
                            break;

                    case 2:
                        iShipImpHuman.emptyPainting();
                        System.out.println("Ingresa en donde quieres lanzar tu ataque ");
                        int inputAttack = scanner.nextInt();
                        int attackNum=randomNum.nextInt(1,82);
                        try {

                            System.out.println("Mapa rival");
                            iShipBotImp.boardShipShot(inputAttack);
                            System.out.println();
                            if (totalAttacks.stream().noneMatch(e->e.equals(attackNum))){
                                System.out.println("Mapa tuyo");
                                iShipImpHuman.boardShipShot(attackNum);
                                totalAttacks.add(attackNum);
                            }else {
                                int alternativeAttack=randomNum.nextInt(1,82);
                                iShipImpHuman.boardShipShot(alternativeAttack);
                                totalAttacks.add(alternativeAttack);
                            }

                            if (iShipBotImp.gameOverNotification() ){
                                System.out.println("Ganaste");
                                stillPlaying = false;
                            }
                            if (iShipImpHuman.gameOverNotification()){
                                System.out.println("Game Over");
                                stillPlaying = false;
                            }

                        } catch (Exception e) {
                            if (inputAttack < 1 || inputAttack > 81)
                                System.out.println("La posicion de ataquen no puede ser menor que 1 y mayor a 81" + e.getMessage());
                        }
                        break;
                    case 3:
                        iShipImpHuman.placementPainting();
                        System.out.println();
                        iShipBotImp.placementPainting();
                        break;
                    case 4:
                        System.out.println("Dejando Juego. Todo  reiniciara");
                        stillPlaying = false;

                        break;
                }

    }while (stillPlaying);

}}