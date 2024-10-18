package com.zxy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Gamewin extends JFrame {

    int wigth = 2 * GameUtil.OFFSET + GameUtil.MAP_W * GameUtil.SQUARE_LENGTH;
    int height = 4 * GameUtil.OFFSET + GameUtil.MAP_H * GameUtil.SQUARE_LENGTH;

    Image offScreenImage = null;

    boolean ifClear =false;

    Mapbottom mapbottom = new Mapbottom();
    MapTop mapTop = new MapTop();
    GameSelect gameSelect = new GameSelect();

    //是否开始,f未开始,t开始
    boolean begin=false;

    void launch(){
        GameUtil.START_TIME=System.currentTimeMillis();
        this.setVisible(true);
        if(GameUtil.stata==3){
            this.setSize(500,500);
        }else {
            this.setSize(wigth,height);
        }
        // this.setLocationRelativeTo(null);
        this.setTitle("我爱玩扫雷");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                ifClear=true;
            }
        });
        //鼠标事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                switch (GameUtil.stata){
                    case 0 :
                        if(e.getButton()==1){
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.LEFT = true;
                        }
                        if(e.getButton()==3) {
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            GameUtil.RIGHT = true;
                        }
                    case 1 :
                    case 2 :
                        if(e.getButton()==1){
                            if(e.getX()>GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2)
                                    && e.getX()<GameUtil.OFFSET + GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2) + GameUtil.SQUARE_LENGTH
                                    && e.getY()>GameUtil.OFFSET
                                    && e.getY()<GameUtil.OFFSET+GameUtil.SQUARE_LENGTH){
                                mapbottom.reGame();
                                mapTop.reGame();
                                GameUtil.FLAG_Num=0;
                                GameUtil.START_TIME=System.currentTimeMillis();
                                GameUtil.stata=0;
                            }
                        }
                        if(e.getButton()==2){
                            GameUtil.stata=3;
                            begin=true;
                        }
                        break;
                    case 3:
                        if(e.getButton()==1){
                            GameUtil.MOUSE_X = e.getX();
                            GameUtil.MOUSE_Y = e.getY();
                            begin = gameSelect.hard();
                        }

                        break;
                    default:

                }


            }
        });


        while (true){
            repaint();
            begin();
            try {
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    void begin(){
        if(begin){
            begin=false;
            gameSelect.hard(GameUtil.level);
            dispose();
            Gamewin gameWin = new Gamewin();
            GameUtil.START_TIME = System.currentTimeMillis();
            GameUtil.FLAG_Num=0;
            mapbottom.reGame();
            mapTop.reGame();
            gameWin.launch();
        }
    }

    @Override
    public void paint(Graphics g) {
        if (ifClear){
            g.clearRect(0,0,this.getWidth(),this.getHeight());
            ifClear=false;

        }
        if(GameUtil.stata==3){
            g.setColor(Color.white);
            g.fillRect(0,0,500,500);
            gameSelect.paintSelf(g);
        }else {


            offScreenImage = this.createImage(wigth, height);
            Graphics gImage = offScreenImage.getGraphics();
            //设置背景颜色
            gImage.setColor(Color.red);
            gImage.fillRect(0, 0, wigth, height);

            mapbottom.Painself(gImage);
            mapTop.Painself(gImage);
            g.drawImage(offScreenImage, 0, 0, null);
        }
    }

    public static void main(String[] args) {
        Gamewin gameWin = new Gamewin();
        gameWin.launch();
    }
}