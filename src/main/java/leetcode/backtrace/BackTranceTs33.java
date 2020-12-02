package leetcode.backtrace;

import org.junit.Test;

/**
 * 即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 *
 * @author dzd
 */
public class BackTranceTs33 {
    //n皇后问题  目前以四皇后为例子

    //皇后数量
    int count=8;

    int [] arrayRow=new int[count];

    int [][] chessBoard = new int[count][count];


    @Test
    public void test1(){

        //首先下第零行
        setChess(0);
    }

    private void setChess(int row) {

        //如果此时下到了最后一行说明此时都是满足的，则输出此时的棋盘
        if (row==count){
            printCurrentBoard();
        }else {
            //否则代表不满足的情况，需要对此进行校验
            for (int i = 0; i < count; i++) {
                //预记录当前行皇后此时的位置
                arrayRow[row]=i;
               boolean flag= isOk(row);
               if (flag){
                   //如果可以的话下棋
                   chessBoard[row][i]=1;
                   //前往下一行
                   setChess(row+1);
                   //退出这一行 重置当前行
                   chessBoard[row][i]=0;
               }
            }
        }

    }

    /**
     * 校验此时的皇后的位置是否可以
     * @return
     */
    private boolean isOk(int row) {

        for (int i = 0; i <row; i++) {
            if (arrayRow[i]==arrayRow[row]||Math.abs(i-row)==Math.abs(arrayRow[i]-arrayRow[row])){
                return false;
            }
        }

        return true;
    }

    void printCurrentBoard(){
        for (int[] ints : chessBoard) {
            for (int anInt : ints) {
                System.out.print(anInt);
            }
            System.out.println();
        }
        System.out.println();
    }

}