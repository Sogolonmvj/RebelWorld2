package com.vieira.sogolon.app.controller;

import com.vieira.sogolon.app.enums.Race;
import lombok.*;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rebel {
    Scanner scan;

    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    private String regex = "^[\\p{L} .'-]+$";

    @Getter
    private String name;

    @Getter
    private int Age;

    @Getter
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

                if (!isValid) {
                    System.out.println("#: Please, enter a valid name!");
                    System.out.print("#: ");
                    name = "";
                }

                if (name.isBlank() || name.isEmpty()) {
                    System.out.println("#: Please, fill in the name field!");
                    System.out.print("#: ");
                    name = "";
                }

                if (name.length() < acceptableNameLength) {
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
            }
            scan.nextLine();
        } while(age == 0);

        System.out.println(" ");
        return age;
    }

    private Race askRace() {
        int index = -1;

        System.out.println("################## What is your rebel race? #####################");

        for (Race race : Race.values()) {
            System.out.printf("%d - %s %n", race.ordinal(), race.name());
        }

        System.out.print("#: ");

        do {
            try {
                index = scan.nextInt();

                if (index < 0 || index >= Race.values().length) {
                    System.out.println("#: You entered an invalid index! Please, correct it!");
                    askRace();
                    return null;
                }

                for (Race race : Race.values()) {
                    if (index == race.ordinal()) {
                        return Race.valueOf(race.name());
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
