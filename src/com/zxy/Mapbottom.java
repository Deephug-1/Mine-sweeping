package com.zxy;

//底层地图
//创建游戏组件
import java.awt.*;

public class Mapbottom {
    //创建一个地雷类
    BottomRay bottomRay = new BottomRay();
    BottomNum bottomNum = new BottomNum();
    {
        bottomRay.newRay();
        bottomNum.newNum();
    }
    //该方法用来重置游戏
    void reGame(){
        for(int i=1;i<=GameUtil.MAP_W;i++){
            for(int j=1;j<=GameUtil.MAP_H;j++){
                GameUtil.DATA_BOTTOW[i][j]=0;
            }
        }
        bottomRay.newRay();;
        bottomNum.newNum();
    }
    void Painself(Graphics g){
        g.setColor(Color.red);

            for(int i=0;i<=GameUtil.MAP_W;i++){
                g.drawLine(GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH,
                        3*GameUtil.SQUARE_LENGTH,
                        GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH,
                        3*GameUtil.OFFSET+GameUtil.MAP_H*GameUtil.SQUARE_LENGTH);

            }
            for(int i=0;i<=GameUtil.MAP_H;i++){
                g.drawLine(GameUtil.OFFSET,
                        3*GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH,
                        GameUtil.OFFSET+GameUtil.MAP_W*GameUtil.SQUARE_LENGTH,
                        3*GameUtil.OFFSET+i*GameUtil.SQUARE_LENGTH);
            }
            for(int i=1;i<=GameUtil.MAP_W;i++){
                for(int j=1 ;j<=GameUtil.MAP_H;j++){
                    //雷
                    if(GameUtil.DATA_BOTTOW[i][j]==-1) {
                        g.drawImage(GameUtil.lei,
                                GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                                GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1,
                                GameUtil.SQUARE_LENGTH - 2,
                                GameUtil.SQUARE_LENGTH - 2,
                                null);
                    }
                    //数字
                    if(GameUtil.DATA_BOTTOW[i][j]>=0) {
                        g.drawImage(GameUtil.images[GameUtil.DATA_BOTTOW[i][j]],
                                GameUtil.OFFSET + (i - 1) * GameUtil.SQUARE_LENGTH + 1,
                                GameUtil.OFFSET * 3 + (j - 1) * GameUtil.SQUARE_LENGTH + 1 ,
                                GameUtil.SQUARE_LENGTH - 2,
                                GameUtil.SQUARE_LENGTH - 2,
                                null);
                    }
                }
            }
            //绘制数字，剩余雷数,倒计时
        GameUtil.drawWord(g,""+(GameUtil.RAY_MAX-GameUtil.FLAG_Num),
                GameUtil.OFFSET,2*GameUtil.OFFSET,30,Color.blue);
        GameUtil.drawWord(g,""+(GameUtil.END_TIME-GameUtil.START_TIME)/1000,GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W-1),
                2*GameUtil.OFFSET,30,Color.blue);
            switch (GameUtil.stata){
                case 0:
                    GameUtil.END_TIME =System.currentTimeMillis();
                    g.drawImage(GameUtil.face,GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
                            GameUtil.OFFSET,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                    break;
                case 1:
                    g.drawImage(GameUtil.win,GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
                            GameUtil.OFFSET,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                    break;
                case 2:
                    g.drawImage(GameUtil.over,GameUtil.OFFSET+GameUtil.SQUARE_LENGTH*(GameUtil.MAP_W/2),
                            GameUtil.OFFSET,
                            GameUtil.SQUARE_LENGTH - 2,
                            GameUtil.SQUARE_LENGTH - 2,
                            null);
                    break;
                default:
            }
        }
    }

