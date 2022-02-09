package com.vieira.sogolon.app.model;

import com.vieira.sogolon.app.controller.Rebel;
import lombok.Cleanup;
import lombok.Data;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@Data
public class RebelTeam {
    private HashMap<Integer, Rebel> accepted;
    private int key = 0;

    public RebelTeam() {
        accepted = new HashMap<>();
    }

    public void newRebel(Rebel rebel) {
        accepted.put(key, rebel);
        System.out.println("Rebel Name: " + accepted.get(key).getName());
        System.out.println("Rebel Age: " + accepted.get(key).getAge());
        System.out.println("Rebel Race: " + accepted.get(key).getRace());
        key++;
    }

    public HashMap selectionSort(HashMap accepted, String sortBy) {

        for (int index = 0; index < accepted.size(); index++) {
            int greaterValueIndex = index;

            for (int secondIndex = index + 1; secondIndex < accepted.size(); secondIndex++) {

                Rebel value = (Rebel) accepted.get(greaterValueIndex);
                Rebel secondValue = (Rebel) accepted.get(secondIndex);

                switch(sortBy) {
                    case "Name":
                        try {
                            String tempValue = value.getName();
                            String tempSecondValue = secondValue.getName();

                            if (tempValue.compareTo(tempSecondValue) > 0) {
                                greaterValueIndex = secondIndex;
                            }
                        } catch (NullPointerException e) {
                            System.out.println("#: Oh, no! A problem has occurred!");
                        }
                        break;
                    case "Age":
                        try {
                            int tempValue = value.getAge();
                            int tempSecondValue = secondValue.getAge();

                            if (tempSecondValue < tempValue) {
                                greaterValueIndex = secondIndex;
                            }
                        } catch (NullPointerException e) {
                            System.out.println("#: Oh, no! A problem has occurred!");
                        }
                        break;
                    case "Race":
                        try {
                            if (value.getRace().toString().compareTo(secondValue.getRace().toString()) > 0) {
                                greaterValueIndex = secondIndex;
                            }
                        } catch (NullPointerException e) {
                            System.out.println("#: Oh, no! A problem has occurred!");
                        }
                        break;
                    default:
                        System.out.println("#: Oh, no! A problem has occurred!");
                        break;
                }

            }

            Rebel smallestValue = (Rebel) accepted.get(greaterValueIndex);
            accepted.put(greaterValueIndex, accepted.get(index));
            accepted.put(index, smallestValue);

        }

        return accepted;

    }

    public void printList(String sortBy) {

        try {
            @Cleanup PrintWriter writer = new PrintWriter("Accepted-rebels.txt", "UTF-8");
            writer.printf("List of accepted rebels sorted by %s: %n", sortBy);
            writer.println("");

            for (Rebel rebel : accepted.values()) {
                writer.println(rebel);
            }

        } catch (FileNotFoundException e) {
            System.out.println("#: File not found!");
        } catch (UnsupportedEncodingException e) {
            System.out.println("#: Encoding type not supported!");
        }

    }

    public void printListOnScreen(String orderBy) {

        System.out.printf("#: List of accepted rebels sorted by %s: %n", orderBy);
        System.out.println("");

        for (Rebel rebel : accepted.values()) {
            System.out.printf("#: %s %n", rebel);
        }

    }

}
