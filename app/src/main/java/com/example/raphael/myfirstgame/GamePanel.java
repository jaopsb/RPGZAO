package com.example.raphael.myfirstgame;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Raphael on 23/12/2015.
 */
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback{

    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;
    public static final int MOVESPEED = -1;
    private MainThread thread;
    private Background bg;
    private Player player;

    public GamePanel(Context context) {
        super(context);

        getHolder().addCallback(this);

        thread = new MainThread(getHolder(), this);

        setFocusable(true);
    }

    /* surfaceCreated
     * funcao que cria a tela do jogo, leitura do background (bg) e do jogador (player)
     *  */
    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        // leitura do background
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.bg));
        // leitura do jogador, dividido em (referencia da imagem, pixels horizontais lidos, pixels verticais lidos, nro de frames)
        player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.oboro), 77, 77, 1);
        // inicio da thread
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // funcao que para o jogo (a thread) de rodar
        boolean retry = true;
        while(retry){
            try{
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {e.printStackTrace();}
            retry = false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // acao ao toque na tela, caso toque embaixo o cara anda pra direita
        // problema: precisa tocar 3 vezes pra ele andar e nao da pra parar ele

        if(!player.getPlaying()){
            player.setPlaying(true);
        } else {
            if(event.getX() > (GamePanel.WIDTH/2) + 50) {
                player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.oborodireita), 100, 77, 8);
                player.setRight(true);
            } else {
                player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.oboroesquerda), 100, 77, 8);
                player.setLeft(true);
            }
        }

        /*if(event.getAction() == MotionEvent.EDGE_LEFT) {
            if(!player.getPlaying()){
                player.setPlaying(true);
                player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.oboroesquerda), 100, 77, 8);
                player.setLeft(true);
            } else {
                player = new Player(BitmapFactory.decodeResource(getResources(), R.drawable.oboroesquerda), 100, 77, 8);
                player.setLeft(true);
            }

            return true;
        }*/

        return super.onTouchEvent(event);
    }

    public void update(){
        // update da tela do jogo, de acordo com as imagens lidas anteriormente
        if(player.getPlaying()) {
            bg.update();
            player.update();
        }
    }

    @Override
    public void draw(Canvas canvas) {

        // desenho da tela de imagem utilizando Canvas (que nao sei o que eh), mas funciona
        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);
        if(canvas != null){
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            player.draw(canvas);
            canvas.restoreToCount(savedState);
        }
    }
}
