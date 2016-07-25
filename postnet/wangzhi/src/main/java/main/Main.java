package main;

import core.CoreServices;

import java.util.Scanner;

/**
 * Created by zbwang on 16/7/17.
 */
public class Main {

    //TODO: Insert a hyphen for the 9 digit zips Quit (check digit)You will need to print all output for the user. ???

    public static void main(String[] args) {
        CommandResponseFactory commandResponseFactory = new CommandResponseFactory();
        CoreServices coreServices = new CoreServices();
        CommandFactory commandFactory = new CommandFactory(commandResponseFactory, coreServices);
        Router router = new Router(commandFactory);

        while (true) {
            System.out.println(router.route(null));
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String input = scanner.nextLine();
                System.out.println(router.route(input));
            }
        }
    }

}
