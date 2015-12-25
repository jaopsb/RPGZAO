package com.example.raphael.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

/**
 * Created by Raphael on 24/12/2015.
 */
public class Player extends GameObject{
    private Bitmap spritesheet;
    private int score;
    private double dya;
    private double dxa;
    private boolean up;
    private boolean down;
    private boolean right;
    private boolean left;
    private boolean playing;
    private Animation animation = new Animation();
    private long startTime;

    // funcao de identificacao do player
    // res sera a imagem do player (no caso, a sprite)
    // w sera o tamanho da imagem horizontalmente
    // h sera o tamanho da imagem verticalmente
    // numFrames sera a quantidade de imagens nos sprite do player
    public Player(Bitmap res, int w, int h, int numFrames){
        x = GamePanel.WIDTH/2;
        y = GamePanel.HEIGHT/2;
        score = 0;
        height = h;
        width = w;

        Bitmap image[] = new Bitmap[numFrames];
        spritesheet = res;

        for (int i = 0; i < image.length; i++){
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(100);
        startTime = System.nanoTime();
    }

    // funcoes que setam a direcao de andar
    public void setUp(boolean b){ up = b; }

    public void setRight(boolean b) { right = b; }

    public void setLeft(boolean b) { left = b; }

    // funcao de atualizacao de acordo com o comando pelo touch event
    // por enquanto so tem de ir pra direita, porem implementar pros outros lados
    // nao sera dificil se entender
    public void update(){
        long elapsed = (System.nanoTime() - startTime)/1000000;
        if(elapsed > 100){
            score++;
            startTime = System.nanoTime();
        }

        animation.update();

        if(up){
            dy = (int) (dya -= 1.1);
        } else {
            dy = (int) (dya += 1.1);
        }

        if(down){
            dy = (int) (dya += 1.1);
        }

        if(right){
            dx = (int) (dxa -= 1.1);
        }

        if(left){
            dx = (int) (dxa += 1.1);
        }

        if(dy > 14) dy = 14;
        if(dy < -14) dy = -14;
    }

    public void draw (Canvas canvas) {
        canvas.drawBitmap(animation.getImage(), x, y, null);
    }

    public int getScore() { return score; }

    public boolean getPlaying () { return playing; }

    public void setPlaying (boolean b) { playing = b; }
    public void resetDYA() { dya = 0;}
    public void resetScore() { score = 0;}
}
