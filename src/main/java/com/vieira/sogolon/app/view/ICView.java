package com.vieira.sogolon.app.view;

import com.vieira.sogolon.app.controller.Rebel;
import com.vieira.sogolon.app.enums.Order;
import com.vieira.sogolon.app.model.RebelTeam;
import lombok.Getter;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class ICView {
    Scanner scan;

    @Getter
    private String joinTeam;

    @Getter
    private Rebel rebel;

    @Getter
    private RebelTeam team;

    @Getter
    private int index = 0;

    public ICView() {
        this.scan = new Scanner(System.in);
        this.team = new RebelTeam();
    }

    public void welcome() {
        System.out.println("###########################################################");
        System.out.println("# Hello rebel! I am the administrator of the rebel world! #");
        System.out.println("###########################################################");
        this.addRebel();
    }

    public void addRebel() {
        boolean addMore = true;

        do {

            index = joinUs();

            switch (index) {
                case 1:
                    this.rebel = new Rebel();
                    rebel.askData();
                    newRebel();
                    break;
                case 2:
                    System.out.println("#: Ok! See you!");
                    System.out.println("#: Closing the application...");
                    addMore = false;
                    break;
                case 3:
                    Order orderBy = chooseOrder();
                    switchOrder(orderBy);
                default:
                    System.out.println("#: Closing the application...");
                    addMore = false;
                    break;
            }

        } while (addMore);

    }

    private int joinUs() {
        System.out.println("#: Hello, can I help you?");
        System.out.println("#################################################################################################");
        System.out.println("###: 1 - Yes, add a rebel # 2 - No, close the application # 3 - Yes, print the list of rebels ###");
        System.out.print("#: ");

        try {
            index = scan.nextInt();

            if (index != 1 && index != 2 && index != 3) {
                System.out.println("#: Enter a valid option, please!");
                joinUs();
            }

        } catch (InputMismatchException e) {
            System.out.println("#: Must be a valid option!");
            index = 0;
        }

        return index;
    }

    private void newRebel() {
        joinTeam = String.valueOf(getRandomBoolean());

        switch (joinTeam) {
            case "true":
                team.newRebel(rebel);
                System.out.println("#: Rebel added to the team successfully!");
//                team.printList();
                break;
            case "false":
                System.out.println("#: Unfortunately, you are not able to join us!");
                break;
            default:
                System.out.println("#: I am undecided!");
                break;
        }
    }

    private Order chooseOrder() {
        int index = -1;

        System.out.println("#: Do you want to print the rebels list by: ");

        for (Order order : Order.values()) {
            System.out.printf("%d - %s %n", order.ordinal(), order.name());
        }

        System.out.print("#: ");

        do {
            try {
                index = scan.nextInt();

                if (index < 0 || index >= Order.values().length) {
                    System.out.println("#: You entered an invalid index! Please, correct it!");
                    chooseOrder();
                    return null;
                }

                for (Order order : Order.values()) {
                    if (index == order.ordinal()) {
                        return Order.valueOf(order.name());
                    }
                }

            } catch (InputMismatchException e) {
                System.out.println("#: Invalid index! Please, fill in this field correctly!");
                System.out.print("#: ");
            }

            scan.nextLine();

        } while (index < 0);

        System.out.println(" ");

        return null;
    }

    private void switchOrder(Order orderBy) {

        switch (orderBy) {
            case AGE:
                team.selectionSort(team.getAccepted(), "Age");
                team.printList("age");
                team.printListOnScreen("age");
                break;
            case NAME:
                team.selectionSort(team.getAccepted(), "Name");
                team.printList("name");
                team.printListOnScreen("name");
                break;
            case RACE:
                team.selectionSort(team.getAccepted(), "Race");
                team.printList("race");
                team.printListOnScreen("race");
                break;
            default:
                System.out.println("#: Invalid option!");
                chooseOrder();
                break;
        }

    }

    private boolean getRandomBoolean() {
        Random random = new Random();
        return random.nextBoolean();
    }

}
