package com.vieira.sogolon.app.controller;

import com.vieira.sogolon.app.enums.Race;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@NoArgsConstructor
public class Rebel {
    Scanner scan;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private String regex = "^[\\p{L} .'-]+$";

    @Getter
    @NotNull(message = "Name cannot be null!")
    private String name;

    @Getter
    @NotNull(message = "Age cannot be null!")
    private int Age;

    @Getter
    @NotNull(message = "Race cannot be null!")
    private Race race;

    public void askData() {
        this.scan = new Scanner(System.in);

        this.name = this.askName();
        this.Age = this.askAge();
        this.race = this.askRace();
    }

    private String askName() {
        String name;
        int acceptableNameLength = 2;

        System.out.println("################## What is your name? #####################");
        System.out.print("#: ");

        do {
            try {
                name = scan.next();

                boolean isValid = validateEnteredLetters(name);

                if (!isValid || name.isBlank() || name.isEmpty() || name.length() < acceptableNameLength) {
                    System.out.println("#: Please, enter a valid name!");
                    System.out.print("#: ");
                    name = "";
                }

            } catch (InputMismatchException e) {
                System.out.println("#: Write your name, please!");
                System.out.print("#: ");
                name = "";
            }

            scan.nextLine();

        } while (name.equals(""));

        System.out.println(" ");
        return name;
    }

    private int askAge() {
        int age = 0;

        System.out.println("################## How old are you? #####################");
        System.out.print("#: ");

        do {
            try {
                age = scan.nextInt();
                if (age < 0 || age > 150) {
                    System.out.println("#: Please, enter a valid age!");
                    System.out.print("#: ");
                    age = 0;
                }
            } catch (InputMismatchException e) {
                System.out.println("#: Invalid age!");
                System.out.print("#: ");
            }
            scan.nextLine();
        } while(age == 0);

        System.out.println(" ");
        return age;
    }

    private Race askRace() {
        int index;

        System.out.println("################## What is your rebel race? #####################");

        for (Race race : Race.values()) {
            System.out.printf("%d - %s %n", race.ordinal(), race.name());
        }

        System.out.print("#: ");

        do {
            try {
                index = scan.nextInt();

                if (index >= 0 && index <= Race.values().length) {

                    for (Race race : Race.values()) {
                        if (index == race.ordinal()) {
                            return Race.valueOf(race.name());
                        }
                    }

                }

                System.out.println("#: You entered an invalid index! Please, correct it!");
                System.out.print("#: ");

            } catch (InputMismatchException e) {
                System.out.println("#: Invalid index! Please, fill in this field correctly!");
                System.out.print("#: ");
            }

            index = -1;

            scan.nextLine();

        } while (index < 0);

        System.out.println(" ");

        return null;
    }

    private boolean validateEnteredLetters(String name) {

        try {

            Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(name);
            return matcher.matches();

        } catch (NullPointerException e) {

            System.out.println("#: This is not a valid name!");
            return false;

        }

    }

    @Override
    public String toString() {
        return "Rebel Name: " + this.name + " - " + "Rebel Age: " + this.Age + " - " + "Rebel Race: " + this.race;
    }

}
