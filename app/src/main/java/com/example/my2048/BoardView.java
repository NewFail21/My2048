package com.example.my2048;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Pair;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.TreeMap;

public class BoardView {
    private Board board;
    private Paint boardPaint, fieldPaint, textPaint;
    public static final int MARGIN = 10;
    public boolean isGameOver = false;
    public TreeMap<Integer, Bitmap> bitmaps;

    public BoardView(Board board, TreeMap<Integer, Bitmap>  bitmaps) {
        this.board = board;
        this.bitmaps = bitmaps;

        boardPaint = new Paint();
        boardPaint.setColor(Color.LTGRAY);

        fieldPaint = new Paint();
        fieldPaint.setColor(Color.GRAY);

        textPaint = new Paint();
        textPaint.setStyle(Paint.Style.FILL);
    }

    public int getScore(){
        return board.getScore();
    }

    public void draw(Canvas canvas){
        if (board.isGameOver) {
            isGameOver = true;
            return;
        }

        int fieldSize = (Math.min(canvas.getHeight(), canvas.getWidth()) - MARGIN * 2) / board.getSize();
        textPaint.setTextSize(fieldSize / 2);
        canvas.drawRect(new Rect(0, 0, canvas.getWidth(), canvas.getHeight()), boardPaint);
        for (int i = 0; i < board.getSize(); i++){
            for(int j = 0; j < board.getSize(); j++){
                int x = MARGIN + j * fieldSize;
                int y = MARGIN + i * fieldSize;
                canvas.drawRect(new Rect(
                        x, y, x + fieldSize, y + fieldSize), fieldPaint);
                Bitmap bitmap = getBitmapByNumber(board.getFieldValue(j, i));
                canvas.drawBitmap(bitmap,
                        new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                        new Rect(x, y, x + fieldSize,  y + fieldSize), textPaint);

            }
        }
    }

    public Bitmap getBitmapByNumber(int number){
        return bitmaps.get(number);
    }
}
