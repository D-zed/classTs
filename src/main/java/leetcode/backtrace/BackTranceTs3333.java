package leetcode.backtrace;

import org.junit.Test;

public class BackTranceTs3333 {
//四皇后问题  https://blog.csdn.net/oracle1158/article/details/32703619
    //n皇后问题  目前以四皇后为例子
    //四皇后问题的规则任何两个皇后在棋盘上不可以在一条置线上和斜线上

    //皇后个数
    int count = 4;
    //棋盘
    int [][] board=new int[count][count];
    //位置
    int [] positionArr=new int[count];

    @Test
    public void test1() {

       setChess(0);

    }

    private void setChess(int row) {

        if (row>count-1){
            for (int[] ints : board) {
                for (int anInt : ints) {
                    System.out.print(anInt);
                }
                System.out.println();
            }
            System.out.println();
        }else {
            for (int i = 0; i < count; i++) {
                //预下棋
                positionArr[row]=i;
                if (checkPosition(row)){
                    //真正下棋
                    board[row][i]=1;
                    //继续下下一行
                    setChess(row+1);
                    //退出来的时候滞空当前行
                    board[row][i]=0;
                }
            }
        }
    }

    private boolean checkPosition(int row) {
        for (int i = 0; i < row; i++) {
            if (positionArr[row]==positionArr[i]|| (row-i)==Math.abs(positionArr[row]-positionArr[i])){
                return false;
            }
        }
        return true;
    }


}
