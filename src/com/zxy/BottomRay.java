package com.zxy;
//底层地雷类
//初始化地雷

public class BottomRay {
    int [] rays = new int[GameUtil.RAY_MAX*2];//数组元素是地雷个数的两倍，一维数组存放坐标
    int x,y;
    //是否放置，可以放置为T，不可放置为F
    boolean isPlace = true;
    void newRay () {//代码块
        for(int i=0;i<GameUtil.RAY_MAX*2;i=i+2){
            x= (int) (Math.random()*GameUtil.MAP_W+1);//
            y = (int) (Math.random()*GameUtil.MAP_H+1);
            //重复地雷判断
            for(int j=0;j<i;j=j+2){
                if (x == rays[j]&&y==rays[j+1]){
                    i=i-2;//进行回退
                    isPlace =false;//T变为F
                    break;//跳出循环
                }
            }
            //将坐标放入数组
            if(isPlace){
                rays[i]=x;
                rays[i+1]=y;
            }
            isPlace = true;
        }
        for(int i=0;i<GameUtil.RAY_MAX*2;i=i+2){
            GameUtil.DATA_BOTTOW[rays[i]][rays[i+1]]=-1;

        }

    }

}
