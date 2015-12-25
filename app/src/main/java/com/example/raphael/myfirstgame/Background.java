package com.example.raphael.myfirstgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

/**
 * Created by Raphael on 23/12/2015.
 */
public class Background {

    private Bitmap image;
    private int x, y, dx, dy;

    // funcao construcao do fundo
    // res sera a imagem do fundo
    public Background(Bitmap res){
        image = res;
        dx = GamePanel.MOVESPEED;
        dy = GamePanel.MOVESPEED;
    }

    // atualizacao do fundo de acordo com a velocidade do player
    public void update(){
        x += dx;
        y += dy;
        if(x < -GamePanel.WIDTH || x > GamePanel.WIDTH){
            x = 0;
        }
        if(y < -GamePanel.HEIGHT || y > GamePanel.HEIGHT){
            y = 0;
        }
    }

    // desenho do fundo por Canvas e modificado de acordo com a posicao do player (os ifs)
    public void draw(Canvas canvas){
        canvas.drawBitmap(image, x, y, null);
        if(x < 0){
            canvas.drawBitmap(image, x + GamePanel.WIDTH, y, null);
        }
        if(y < 0){
            canvas.drawBitmap(image, x, y + GamePanel.HEIGHT, null);
        }
    }
}
