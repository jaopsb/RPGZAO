package com.example.raphael.myfirstgame;

import android.graphics.Bitmap;

/**
 * Created by Raphael on 24/12/2015.
 */
public class Animation {
    private Bitmap[] frames;
    private int currentFrames;
    private long startTime;
    private long delay;
    private boolean playedOnce;

    public void setFrames (Bitmap[] frames){
        this.frames = frames;
        currentFrames = 0;
        startTime = System.nanoTime();
    }

    // delay de mudanca de frame
    public void setDelay (long d) { delay = d;}

    public void setFrame (int i) { currentFrames = i;}

    // funcao que atualiza a imagem de acordo com o passar de frames
    // aqui ela roda o vetor de imagens (sprites do player) a uma velocidade definida
    // e retorna ao frame inicial quando chega ao final. playedOnce significa que o vetor
    // foi rodado uma vez ja
    public void update() {
        long elapsed = (System.nanoTime() - startTime)/1000000;

        if(elapsed > delay){
            currentFrames++;
            startTime = System.nanoTime();
        }

        if(currentFrames == frames.length){
            currentFrames = 0;
            playedOnce = true;
        }
    }

    // funcao que retorna a imagem de acordo com o frame atual
    public Bitmap getImage() {
        return frames[currentFrames];
    }

    public int getFrame () {return currentFrames;}

    public boolean playedOnce () { return playedOnce;}
}
