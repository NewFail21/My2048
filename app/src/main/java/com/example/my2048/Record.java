package com.example.my2048;

public class Record {
    long id;
    int score;

    public Record(long id, int score) {
        this.id = id;
        this.score = score;
    }

    public Record(int score) {
        this(-1, score);
    }

    public long getId() {
        return id;
    }

    public int getScore() {
        return score;
    }
}
