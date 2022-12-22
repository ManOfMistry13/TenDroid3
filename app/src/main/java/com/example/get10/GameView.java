package com.example.get10;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;



import tendroid.model.TenGame;
/**
 *
 * @author eleph
 */


public class GameView extends SurfaceView implements SurfaceHolder.Callback {

    TheApplication app;
    Paint paint = new Paint();
    int canvasWidth;
    int canvasHeight;
    int cellSize;
    int somme;


    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        app = new TheApplication();
        this.getApp(context);
        somme = app.score;

    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        app = new TheApplication();
        this.getApp(context);
        somme = app.score;
    }

    public GameView(Context context) {
        super(context);
        getHolder().addCallback(this);
        app = new TheApplication();
        this.getApp(context);
        somme = app.score;


    }

    public void surfaceCreated(SurfaceHolder holder) {
        //rien à faire
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        canvasWidth = Math.min(width,height);
        canvasHeight = height;
        cellSize = canvasWidth / 5;
        reDraw();

    }

    public void surfaceDestroyed(SurfaceHolder arg0) {
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        TenGame theGame = app.getGame();
        int x = (int) event.getX() * (theGame.nbCol()) / canvasWidth;
        int y = (int) event.getY() * (theGame.nbLig()) / canvasWidth;
        int action = event.getAction();

        switch (action){
            case MotionEvent.ACTION_UP:{
                ((PlayActivity)this.getContext()).WindowAlert();
                if(app.s) {
                    Score(x, y);
                    theGame.transition(new Position(x, y));
                    ((PlayActivity) this.getContext()).setScore(app.score);
                    reDraw();
                }
                return true;
            }

            case MotionEvent.ACTION_DOWN:{
                reDraw();
                return true;
            }
            default:
                return false;
        }
    }

    @SuppressLint("WrongCall")
    void reDraw() {
        Canvas c = getHolder().lockCanvas();
        if (c != null) {
            this.onDraw(c);
            getHolder().unlockCanvasAndPost(c);
        }
    }


    @Override
    public void onDraw(Canvas canvas) {

        TenGame theGame = app.getGame();
        paint.reset();
        canvas.drawColor(Color.BLACK);

        //les traits de séparation
        //canvas.drawColor(Color.GRAY);
        paint.setColor(Color.WHITE);
        for (int x = 0; x <= canvasWidth-cellSize; x += cellSize) {
            canvas.drawLine(x, 0, x, canvasWidth, paint);
        }

        //paint.setColor(Color.RED);
        for (int y = 0; y <= canvasHeight; y += cellSize) {
            canvas.drawLine(0,y, canvasHeight,y,paint);
        }

        //Changement des case en couleur
        String[] tab={"#a4c639", "#a6ab66", "#a29088", "#9874a7", "#8657c5", "#6638e2","#0009ff", "#693651", "#442e32", "#292317", "#0009ff"};

        for(int i=0; i<theGame.nbCol();i++){
            for(int j=0;j<theGame.nbLig();j++){
                Position p = new Position(i,j);
                paint.setColor(Color.parseColor(tab[theGame.get(p)]));
                canvas.drawRect(p.getCol()*cellSize,p.getLig()*cellSize,p.getCol()*cellSize+cellSize,p.getLig()*cellSize+cellSize,paint);

            }
        }

        //Les chiffres en couleur
        paint.setTextSize(cellSize*0.7f);
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);
        for (int x = 0; x < theGame.nbCol(); x++) {
            for (int y = 0; y < theGame.nbLig(); y++) {
                paint.setColor(Color.BLACK);
                canvas.drawText(Integer.toString(theGame.get(new Position(x, y))),
                        (x * cellSize) + cellSize/2 - (cellSize * 0.7f/4),        //(x * cellSize) + cellSize / 2 - 25,
                         (cellSize*0.7f + y *cellSize), paint);    //(cellSize + y * cellSize) - cellSize / 2 + 50, paint);


                if (theGame.getSelectedGroup() != null) {
                    paint.setColor(Color.RED);
                    for (int i = 0; i < theGame.getSelectedGroup().size(); i++) {
                        paint.setColor(Color.WHITE);
                        //score=score+
                        canvas.drawText(Integer.toString(theGame.get(theGame.getSelectedGroup().get(i))),
                                (theGame.getSelectedGroup().get(i).getCol() * cellSize) + cellSize/2-(cellSize*0.7f/4), //(theGame.getSelectedGroup().get(i).getCol() * cellSize) + cellSize / 2 - 25,
                                (theGame.getSelectedGroup().get(i).getLig()*cellSize)+cellSize*0.7f, paint);       //(cellSize + theGame.getSelectedGroup().get(i).getLig() * cellSize) - cellSize / 2 + 50, paint);
                    }
                }

            }

        }

    }

    public final void getApp (Context context){
        app = (TheApplication) (context.getApplicationContext());
    }

    public void Score(int x, int y){
        TenGame theGame = app.getGame();
        Position p = new Position(x, y);
        if(theGame.getSelectedGroup()==null){
            return;
        }
        if(theGame.getSelectedGroup().contains(p)){
           app.score++;
        }

    }



}