package newcoder.com;

import java.util.Arrays;

class Solution {
    public void gameOfLife(int[][] board) {
        int[][] copyBoard = deepCopy(board);
        for (int i = 0; i != board.length; i++) {
            for (int j = 0; j != board[i].length; j++) {
                int liveNum = getLiveNum(copyBoard, i ,j);
                if (board[i][j] == 1) {
                    if (liveNum < 2 || liveNum > 3) {
                        board[i][j] = 0;
                    }
                } else {
                    if (liveNum == 3) {
                        board[i][j] = 1;
                    }
                }
            }
        }
    }

    private int[][] deepCopy(int[][] board) {
        int[][] newBoard = new int[board.length][board[0].length];
        for (int i = 0; i != board.length; i++) {
            for (int j = 0; j != board[i].length; j++){
                newBoard[i][j] = board[i][j];
            }
        }
        return newBoard;
    }

    private int getLiveNum(int[][] board, int x, int y) {
        int liveNum = 0;
        int maxX = board.length;
        int maxY = board[0].length;
        //左边
        if (y - 1 >= 0) {
            liveNum += board[x][y - 1];
        }
        //右边
        if (y + 1 < maxY) {
            liveNum += board[x][y + 1];
        }
        //左上角
        if (x - 1 >= 0 && y - 1 >= 0) {
            liveNum +=board[x - 1][y - 1];
        }
        //正上方
        if (x - 1 >= 0 && y >= 0) {
            liveNum +=board[x - 1][y];
        }
        //右上方
        if (x - 1 >= 0 && y + 1 < maxY) {
            liveNum +=board[x - 1][y + 1];
        }
        //左下角
        if (x + 1 < maxX && y - 1 >= 0) {
            liveNum +=board[x + 1][y - 1];
        }
        //正下方
        if (x + 1 < maxX && y >= 0) {
            liveNum +=board[x + 1][y];
        }
        //右下方
        if (x + 1 < maxX && y + 1 < maxY) {
            liveNum +=board[x + 1][y + 1];
        }
        return liveNum;
    }

    public static void main(String[] arg) {
        int[][] board = {{0,1,0},{0,0,1},{1,1,1},{0,0,0}};
        Solution solution = new Solution();
        solution.gameOfLife(board);
        System.out.println(Arrays.deepToString(board));
    }
}


