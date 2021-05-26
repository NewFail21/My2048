package com.example.my2048;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;

import java.util.TreeMap;

public class GameView extends View {
    public Board board;
    private BoardView boardView;

    private float downPosX, downPosY, upPosX, upPosY;
    public static final float MIN_SWIPE_DIST = 100;

    private Paint paint;
    Bitmap bitmap;

    public interface OnGameOver{
        public void onGameOver(int score);
    }

    public void setOnGameOver(OnGameOver onGameOver) {
        this.onGameOver = onGameOver;
    }

    public OnGameOver onGameOver = new OnGameOver() {
        @Override
        public void onGameOver(int score) {

        }
    };

    public GameView(Context context, Board board) {
        super(context);
        this.board = board;
        this.board.generate(1);

        TreeMap<Integer, Bitmap> bitmapTreeMap = new TreeMap<>();
        bitmapTreeMap.put(0, BitmapFactory.decodeResource(getResources(), R.drawable.num0));
        bitmapTreeMap.put(2, BitmapFactory.decodeResource(getResources(), R.drawable.num2));
        bitmapTreeMap.put(4, BitmapFactory.decodeResource(getResources(), R.drawable.num4));
        bitmapTreeMap.put(8, BitmapFactory.decodeResource(getResources(), R.drawable.num8));
        bitmapTreeMap.put(16, BitmapFactory.decodeResource(getResources(), R.drawable.num16));
        bitmapTreeMap.put(32, BitmapFactory.decodeResource(getResources(), R.drawable.num32));
        bitmapTreeMap.put(64, BitmapFactory.decodeResource(getResources(), R.drawable.num64));
        bitmapTreeMap.put(128, BitmapFactory.decodeResource(getResources(), R.drawable.num128));
        bitmapTreeMap.put(256, BitmapFactory.decodeResource(getResources(), R.drawable.num256));
        bitmapTreeMap.put(512, BitmapFactory.decodeResource(getResources(), R.drawable.num512));
        bitmapTreeMap.put(1024, BitmapFactory.decodeResource(getResources(), R.drawable.num1024));
        bitmapTreeMap.put(2048, BitmapFactory.decodeResource(getResources(), R.drawable.num2048));

        boardView = new BoardView(board, bitmapTreeMap);
        paint = new Paint();
        bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.background);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (board.isGameOver){
            onGameOver.onGameOver(board.getScore());
        }
        canvas.drawBitmap(bitmap,
                new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight()),
                new Rect(0, 0, getWidth(), getHeight()), paint);
        boardView.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (MotionEvent.ACTION_DOWN == event.getAction()){
            downPosX = event.getX();
            downPosY = event.getY();
        }
        if (MotionEvent.ACTION_UP == event.getAction()){
            upPosX = event.getX();
            upPosY = event.getY();

            float deltaX = downPosX - upPosX;
            float deltaY = downPosY - upPosY;

            if (Math.abs(deltaX) > MIN_SWIPE_DIST || Math.abs(deltaY) > MIN_SWIPE_DIST){
                if (Math.abs(deltaX) > Math.abs(deltaY)){
                    if (deltaX > 0){
                        //left
                        board.moveLeft();
                    }else{
                        //right
                        board.moveRight();
                    }
                }else{
                    if (deltaY > 0){
                        // up
                        board.moveTop();
                    }else{
                        //down
                        board.moveBottom();
                    }
                }
            }


        }
        invalidate();
        return true;
    }
}
