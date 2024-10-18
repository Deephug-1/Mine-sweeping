package com.zxy;

import sun.net.www.content.text.Generic;

import java.awt.*;

//工具类
//工具方法
//存放静态方法
public class GameUtil {
    static int RAY_MAX =100;//定义地雷个数

    static int MAP_W = 36;//地图的宽
    static int MAP_H = 17;//地图的高
    static int OFFSET = 45;//雷区偏移量
    static int SQUARE_LENGTH = 50;//格子的边长
    //鼠标相关
    //坐标
    static int MOUSE_X;
    static int MOUSE_Y;
    //状态
    static boolean LEFT=false;
    static boolean RIGHT=false;
    //游戏状态
    static int stata= 3;
    static  int FLAG_Num =0;
    //倒计时
    static long START_TIME;
    static long END_TIME;
    //游戏难度
    static int level;
    //底层元素
    //-1雷 0 空 1-8为数字
    static int [][] DATA_BOTTOW = new int[MAP_W+2][MAP_H+2];
    //顶层元素 -1无覆盖 0有覆盖 1插旗 2无插旗
    static int [][] DATA_TOP= new int[MAP_W+2][MAP_H+2];

    static Image lei =Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片1/R-a.png");//
    static Image flag =Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片1/R-d.png");//旗帜
    static Image top =Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片1/R-e.png");//顶
    static Image noflag =Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片1/R-f.png");//插错旗帜
    static Image face =Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片2/R-g.png");//
    static Image over =Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片1/R-c.png");//失败
    static Image win =Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片1/R-b.png");//胜利

    static Image[] images= new Image[9];
    static {
        for(int i=1;i<=8;i++){
            images[i]= Toolkit.getDefaultToolkit().getImage("imgs/扫雷图片1/R-"+i+".png");
        }
    }
    static void drawWord(Graphics g,String srt,int x,int y,int size,Color color){
        g.setColor(color);
        g.setFont(new Font("仿宋",Font.BOLD,size));
        g.drawString(srt,x,y);
    }
}
