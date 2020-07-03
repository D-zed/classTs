package backtrace;

import org.junit.Test;

public class BackTranceTs3 {
//四皇后问题  https://blog.csdn.net/oracle1158/article/details/32703619
    //n皇后问题  目前以四皇后为例子

    //皇后数
    final int count=4;

    //记录当前行的皇后的位置 i是行 value 是列
    int[] rowColArr = new int[count];

    //初始化棋盘 0为空位 1为皇后位
    int[][] chessboard = new int[count][count];

    @Test
    public void test1() {

        setChessboard(0);

    }

    /**
     * 给皇后选择位置
     *
     * @param row row
     */
    void setChessboard(int row) {
        //四行都放上皇后之后就可以打印输出  当到底的时候代表不需要回溯 此时的位置就是可以的位置
        if (row == rowColArr.length) {
            for (int m = 0; m < rowColArr.length; m++) {
                for (int j = 0; j < rowColArr.length; j++) {
                    System.out.print(chessboard[m][j]);
                }
                System.out.println();
            }
            System.out.println();
            return;
        }
        for (int i = 0; i < rowColArr.length; i++) {
            //预判皇后位
            rowColArr[row] = i;
            //判断皇后位是否满足条件
            if (isOk(row)) {
                //选择
                chessboard[row][i] = 1;
                //递归
                setChessboard(row + 1);
                //撤销 也就是回溯点
                chessboard[row][i] = 0;
            }
        }
    }

    /**
     * 判断皇后是否可以在此行的此位置
     *
     * @return boolean
     */
    boolean isOk(int row) {
        for (int i = 0; i < row; i++) {
            //当两行的皇后在同一位置   或者   两行皇后的行差和列查相同的时候则 不符合要求
            if (rowColArr[i] == rowColArr[row] || Math.abs(rowColArr[i] - rowColArr[row]) == row - i) {
                return false;
            }
        }
        return true;
    }
}
