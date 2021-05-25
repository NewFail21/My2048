package com.example.my2048;

import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Random;

public class Board {


    private int[][] fields;
    private final int[] randomValues = new int[]{2, 2, 2, 2, 2, 2, 2, 4, 4, 4};
    private int size;

    public int getScore() {
        return score;
    }

    private int score = 0;
    public boolean isGameOver = false;
    Button buttonback1;


    public Board(int[][] fields) {
        this.fields = fields;
        size = fields.length;
    }

    public Board(int size){
        fields = new int[size][size];
        this.size = size;
    }

    public int getFieldValue(int x, int y){
        return fields[y][x];
    }

    public void generate(int count){
        ArrayList<Pair<Integer, Integer>> arrayList = new ArrayList<>();
        for(int i = 0; i < fields.length; i++){
            for(int j = 0; j < fields[i].length; j++){
                if (fields[i][j] == 0) arrayList.add(new Pair<>(i, j));
            }
        }
        if (arrayList.size() != 0){
            Random random = new Random();
            for (int i = 0; i < count; i++){
                Pair<Integer, Integer> randomPair = arrayList.get(random.nextInt(arrayList.size()));
                fields[randomPair.first][randomPair.second] = randomValues[random.nextInt(randomValues.length)];
            }
        }else {
            gameOver();
        }
    }

    private void gameOver() {
        for(int i = 0; i < fields.length; i++){
            for(int j = 0; j < fields[i].length; j++){
                score += fields[i][j];
            }
        }
        isGameOver = true;
    }

    private void calcLeft(){
        for(int i = 0; i < fields.length; i++){
            for(int j = fields[i].length - 1; j > 0; j--){
                if (fields[i][j] == fields[i][j - 1] || fields[i][j - 1] == 0){
                    fields[i][j - 1] += fields[i][j];
                    fields[i][j] = 0;
                }
            }
        }
    }

    private void calcRight(){
        for(int i = 0; i < fields.length; i++){
            for(int j = 0; j < fields[i].length - 1; j++){
                if (fields[i][j] == fields[i][j + 1] || fields[i][j + 1] == 0){
                    fields[i][j + 1] += fields[i][j];
                    fields[i][j] = 0;
                }
            }
        }
    }

    public void moveLeft(){
        for(int i = 0; i < fields.length; i++) calcLeft();
        generate(1);
    }

    public void moveRight(){
        for(int i = 0; i < fields.length; i++) calcRight();
        generate(1);
    }

    public void moveTop(){
        for(int i = 0; i < fields.length; i++) calcTop();
        generate(1);
    }

    private void calcTop() {
        for(int i = 0; i < size; i++){
            for(int j = size - 1; j > 0; j--){
                if (fields[j][i] == fields[j - 1][i] || fields[j - 1][i] == 0){
                    fields[j - 1][i] += fields[j][i];
                    fields[j][i] = 0;
                }
            }
        }
    }

    public void moveBottom(){
        for(int i = 0; i < fields.length; i++) calcBottom();
        generate(1);
    }

    private void calcBottom() {
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size - 1; j++){
                if (fields[j][i] == fields[j + 1][i] || fields[j + 1][i] == 0){
                    fields[j + 1][i] += fields[j][i];
                    fields[j][i] = 0;
                }
            }
        }
    }

    public int getSize() {
        return size;
    }
}
