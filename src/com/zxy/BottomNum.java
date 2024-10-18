package com.zxy;
//底层数字类
public class BottomNum {
    void newNum ()
    {//代码块
    for(int i=1;i<=GameUtil.MAP_W;i++){
        for(int j=1;j<=GameUtil.MAP_H;j++){
            if(GameUtil.DATA_BOTTOW[i][j]==-1){
                for(int k=i-1;k<=i+1;k++){
                    for(int l=j-1;l<=j+1;l++){
                        if(GameUtil.DATA_BOTTOW[k][l]>=0){
                            GameUtil.DATA_BOTTOW[k][l]++;
                        }
                    }

                }

            }
        }

    }


    }
}
