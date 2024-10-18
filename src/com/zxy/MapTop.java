package com.zxy;

import java.awt.*;

//顶层地图
public class MapTop {

    //格子位置
    int temp_x;
    int temp_y;
    //该方法用来重置游戏
    void reGame() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j <= GameUtil.MAP_H; j++) {
                GameUtil.DATA_TOP[i][j] = 0;
            }
        }
    }

    void logic() {
        temp_x = 0;
        temp_y = 0;
        if (GameUtil.MOUSE_X > GameUtil.OFFSET && GameUtil.MOUSE_Y > GameUtil.OFFSET * 3) {
            temp_x = (GameUtil.MOUSE_X - GameUtil.OFFSET) / GameUtil.SQUARE_LENGTH + 1;
            temp_y = (GameUtil.MOUSE_Y - GameUtil.OFFSET * 3) / GameUtil.SQUARE_LENGTH + 1;
        }
        if (temp_x >= 1 && temp_x <= GameUtil.MAP_W && temp_y >= 1 && temp_y <= GameUtil.MAP_H) {
            if (GameUtil.LEFT) {
                //覆盖则翻开
                if (GameUtil.DATA_TOP[temp_x][temp_y] == 0) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = -1;

                }
                spaceOpen(temp_x, temp_y);
                GameUtil.LEFT = false;
            }
            if (GameUtil.RIGHT) {
                //覆盖则插旗
                if (GameUtil.DATA_TOP[temp_x][temp_y] == 0) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 1;
                    GameUtil.FLAG_Num++;
                }
                //插旗取消
                else if (GameUtil.DATA_TOP[temp_x][temp_y] == 1) {
                    GameUtil.DATA_TOP[temp_x][temp_y] = 0;
                    GameUtil.FLAG_Num--;
                } else if (GameUtil.DATA_TOP[temp_x][temp_y] == -1) {
                    numOpen(temp_x, temp_y);
                }
                GameUtil.RIGHT = false;
            }
        }
        boom();
        victory();

    }

    //数字翻开
    void numOpen(int x, int y) {
        int count = 0;//统计
        if (GameUtil.DATA_BOTTOW[x][y] > 0) {
            for (int i = x - 1; i <= x + 1; i++) {
                for (int j = y - 1; j <= y + 1; j++) {
                    if (GameUtil.DATA_TOP[i][j] == 1) {
                        count++;
                    }
                }
            }
            if (count == GameUtil.DATA_BOTTOW[x][y]) {
                for (int i = x - 1; i <= x + 1; i++) {
                    for (int j = y - 1; j <= y + 1; j++) {
                        if (GameUtil.DATA_TOP[i][j] != 1&&GameUtil.DATA_BOTTOW[i][j] != -1) {
                            GameUtil.DATA_TOP[i][j] = -1;
                        }
                        //必须在雷区才能递归
                        if (i >= 1 && j >= 1 && i <= GameUtil.MAP_W && j <= GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }
                    }
                }

            }
        }

    }
    //胜利判定t为胜利f为不胜利
    boolean victory() {
        int count = 0;//统计未打开格子数
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j < GameUtil.MAP_H; j++) {
                if (GameUtil.DATA_TOP[i][j] != -1) {
                    count++;
                }

            }

        }
        if(count==GameUtil.RAY_MAX){
            GameUtil.stata=1;
            for (int i = 1; i <= GameUtil.MAP_W; i++) {
                for (int j = 1; j < GameUtil.MAP_H; j++) {
                    if(GameUtil.DATA_TOP[i][j]==0){
                        GameUtil.DATA_TOP[i][j]=1;
                    }

                }

            }
            return true;
        }
        return false;
    }

    //失败判定
    boolean boom() {
        if(GameUtil.FLAG_Num==GameUtil.RAY_MAX){
            for (int i = 1; i <= GameUtil.MAP_W; i++) {
                for (int j = 1; j < GameUtil.MAP_H; j++) {
                if(GameUtil.DATA_TOP[i][j]==0){
                    GameUtil.DATA_TOP[i][j]=-1;
                }
                }
            }
        }
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j < GameUtil.MAP_H; j++) {
                if (GameUtil.DATA_BOTTOW[i][j] == -1 && GameUtil.DATA_TOP[i][j] == -1) {
                    GameUtil.stata=2;
                    seeBomm();
                    return true;

                }
            }
        }
        return false;
    }

    //失败显示
    void seeBomm() {
        for (int i = 1; i <= GameUtil.MAP_W; i++) {
            for (int j = 1; j < GameUtil.MAP_H; j++) {
                //底层为雷底层没插旗帜
                if (GameUtil.DATA_BOTTOW[i][j] == -1 && GameUtil.DATA_TOP[i][j] != 1) {

                    GameUtil.DATA_TOP[i][j]=-1;
                }
                //底层不是雷顶层是旗显示插错旗
                if (GameUtil.DATA_BOTTOW[i][j] != -1 && GameUtil.DATA_TOP[i][j] == 1) {

                    GameUtil.DATA_TOP[i][j]=2;
                }
            }
        }
    }
    //打开空格
    void spaceOpen (int x,int y){
        if(GameUtil.DATA_BOTTOW[x][y]==0){
            for(int i=x-1;i<=x+1;i++){
                for(int j=y-1;j<=y+1;j++){
                    //覆盖才递归
                    if(GameUtil.DATA_TOP[i][j]!=-1){
                        if(GameUtil.DATA_TOP[i][j]==1){
                            GameUtil.FLAG_Num--;

                        }
                        GameUtil.DATA_TOP[i][j]=-1;
                        //必须在雷区才能递归
                        if(i>=1&&j>=1&&i<=GameUtil.MAP_W&&j<=GameUtil.MAP_H) {
                            spaceOpen(i, j);
                        }

                    }


                }
            }
        }
    }
    //
    void Painself(Graphics g){
        logic();//调用
        for(int i=1;i<=GameUtil.MAP_W;i++){
            for(int j=1 ;j<=GameUtil.MAP_H;j++){
                //顶层
                if(GameUtil.DATA_TOP[i][j]==0) {
                    g.drawImage(GameUtil.top,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
                //插旗
                if(GameUtil.DATA_TOP[i][j]==1) {
                    g.drawImage(GameUtil.flag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
                //差错旗
                if(GameUtil.DATA_TOP[i][j]==2) {
                    g.drawImage(GameUtil.noflag,
                            GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                }
            }
        }
    }

}
