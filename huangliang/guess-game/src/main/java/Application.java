import core.GameMessage;
import shell.Route;
import java.util.Scanner;

/**
 * Created by lianghuang on 7/23/16.
 */
public class Application {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Route route = new Route();

        showMenu();

        while (true) {
            GameMessage gameMessage = route.route(sc.nextLine());

            showResult(gameMessage);
        }


    }

    private static void showResult(GameMessage gameMessage) {

        if(gameMessage.getErrorMessage() != null) {
            System.out.println("Error: " + gameMessage.getErrorMessage());
        }

        switch (gameMessage.getStatusEnum()) {
            case noStart:
                System.out.println("Please input 1 to start a new game or input 2 quit the game:");
                break;
            case playing:
                if(gameMessage.getCalculateResult() != null) {
                    System.out.println("The guessing result is:" + gameMessage.getCalculateResult());
                }
                System.out.println("Your life value is:" + gameMessage.getLifeValue());
                System.out.println("Please input 4 bit of digits or input 2 quit the game:");
                break;
            case win:
                System.out.println("Congratulation, you got the right number!");
                System.out.println("Please input 1 to start a new game or input 2 quit the game:");
                break;
            case failure:
                System.out.println("Unfortunately, your life value is extinct! Please try to start a new game.");
                System.out.println("Please input 1 to start a new game or input 2 quit the game:");
                break;
            default:
                System.out.println("Please input 1 to start a new game or input 2 quit the game:");
                break;

        }
    }

    private static void showMenu() {
        System.out.println("Welcome to guess number game~");
        System.out.println("Please input 1 to start a new game or input 2 quit the game:");
    }

}
