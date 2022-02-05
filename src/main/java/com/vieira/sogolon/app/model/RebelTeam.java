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
    private int key;

    public RebelTeam() {
        accepted = new HashMap<>();
    }

    public void newRebel(Rebel rebel) {
        key++;
        accepted.put(key, rebel);
    }

    public HashMap orderByAge(HashMap accepted) {
        HashMap<Integer, Rebel> byAge;
        byAge = selectionSort(accepted);
        return byAge;
    }

    public HashMap orderByName(HashMap accepted) {
        HashMap<Integer, Rebel> byName;
        byName = selectionSort(accepted);
        return byName;
    }

    public HashMap orderByRace(HashMap accepted) {
        HashMap<Integer, Rebel> byRace;
        byRace = selectionSort(accepted);
        return byRace;
    }


    public HashMap selectionSort(HashMap accepted) {

        System.out.println("Before ordering: " + accepted);

        for (int index = 0; index < accepted.size(); index++) {
            int greaterValueIndex = index;

            for (int secondIndex = index + 1; secondIndex < accepted.size(); secondIndex++) {
                int value = (int) accepted.get(greaterValueIndex);
                int secondValue = (int) accepted.get(secondIndex);

                if(secondValue < value) {
                    greaterValueIndex = secondIndex;
                }
            }

            int smallestValue = (int) accepted.get(greaterValueIndex);
            accepted.put(greaterValueIndex, accepted.get(index));
            accepted.put(index, smallestValue);

        }

        System.out.println("After ordering: " + accepted);

        return accepted;

    }

    public void printList() {

        try {
            @Cleanup PrintWriter writer = new PrintWriter("Accepted-rebels.txt", "UTF-8");
            writer.println("List of accepted rebels: ");
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
}
