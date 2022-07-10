package com.test.tictactoe;

import java.util.*;

/**
 * @author Sameer Bhalerao
 */
public class TicTacToe {
    private static class Move {
        char[][] move;
        Move parent;
        Move[] children;
        int winCount = 0;

        int rank = 0;

        public Move(Move parent) {
            this.move = new char[3][3];
            if (parent == null) {
                this.move[0][0] = '*';
                this.move[0][1] = '*';
                this.move[0][2] = '*';
                this.move[1][0] = '*';
                this.move[1][1] = '*';
                this.move[1][2] = '*';
                this.move[2][0] = '*';
                this.move[2][1] = '*';
                this.move[2][2] = '*';
            } else {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        this.move[i][j] = parent.move[i][j];
                    }
                }
            }
            this.parent = parent;
            this.winCount = 0;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Move) {
                Move compareMove = (Move) obj;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if (move[i][j] != compareMove.move[i][j]) {
                            return false;
                        }
                    }
                }
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i < 3; i++) {
                stringBuffer.append(move[i][0] + " " + move[i][1] + " " + move[i][2]);
                stringBuffer.append("\n");
            }
            return stringBuffer.toString();
        }
    }

    private Move root;

    public TicTacToe() {
        root = new Move(null);
    }

    private Move searchNode(Move root, Move move) {
        Stack<Move> moves = new Stack<>();
        if (root != null && root.rank <= move.rank) {
            if (move.equals(root)) {
                return root;
            }
            moves.push(root);
            while (moves.peek() != null) {
                Move[] nextMoves = moves.pop().children;
                if (nextMoves != null) for (Move nextMove : nextMoves) {
                    if (nextMove != null && nextMove.rank <= move.rank) {
                        if (move.equals(nextMove)) {
                            return nextMove;
                        }
                        moves.push(nextMove);
                    }
                }
            }
        }
        return null;
    }

    private void buildAllMoves(Move move, int player) {
        if (move == null) {
            throw new RuntimeException("Root is not initialized");
        }
        if (!nextMoveAvailable(move) || isAlreadyWinningMove(move, 1)) {
            return;
        }
        int nextPlayer = 1;
        if (player == -1 || player == 2) {
            nextPlayer = 1;
        } else {
            nextPlayer = 2;
        }
        Move[] nextMoves = findNextMoves(move, player);
        move.children = nextMoves;
        for (Move tmpMove : nextMoves) {
            tmpMove.rank = move.rank + 1;
            buildAllMoves(tmpMove, nextPlayer);
            if (isAlreadyWinningMove(tmpMove, 2) && player == 2) {
                while (tmpMove.parent != null) {
                    tmpMove.parent.winCount = tmpMove.parent.winCount + 1;
                    tmpMove = tmpMove.parent;
                }
                return;
            }
        }
    }

    private boolean nextMoveAvailable(Move root) {
        if (root.move[0][0] == '*' || root.move[0][1] == '*' || root.move[0][2] == '*' || root.move[1][0] == '*' || root.move[1][1] == '*' || root.move[1][2] == '*' || root.move[2][0] == '*' || root.move[2][1] == '*' || root.move[2][2] == '*') {
            return true;
        }
        return false;
    }

    private Move[] findNextMoves(Move move, int player) {
        //next move character
        List<Move> moveList = new ArrayList<>();
        char ch = 'O';
        if (player == 2) {
            ch = 'X';
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Move tmpMove = new Move(move);
                if (tmpMove.move[i][j] == '*') {
                    tmpMove.move[i][j] = ch;
                    moveList.add(tmpMove);
                }
            }
        }
        return moveList.toArray(new Move[moveList.size()]);
    }

    private boolean isWinning(Move move, int player) {
        boolean isWinInNextMove = false;
        char charToBeChecked = 'O';
        if (player == 2) {
            charToBeChecked = 'X';
        }

        if ((move.move[0][0] == charToBeChecked && move.move[0][1] == charToBeChecked && move.move[0][2] == '*') || (move.move[0][0] == charToBeChecked && move.move[0][2] == charToBeChecked && move.move[0][1] == '*') || (move.move[0][1] == charToBeChecked && move.move[0][2] == charToBeChecked && move.move[0][0] == '*')) {
            isWinInNextMove = true;
        }//check second row
        else if ((move.move[1][0] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[1][2] == '*') || (move.move[1][0] == charToBeChecked && move.move[1][2] == charToBeChecked && move.move[1][1] == '*') || (move.move[1][1] == charToBeChecked && move.move[1][2] == charToBeChecked && move.move[1][0] == '*')) {
            isWinInNextMove = true;
        } else if ((move.move[2][0] == charToBeChecked && move.move[2][1] == charToBeChecked && move.move[2][2] == '*') || (move.move[2][0] == charToBeChecked && move.move[2][2] == charToBeChecked && move.move[2][1] == '*') || (move.move[2][1] == charToBeChecked && move.move[2][2] == charToBeChecked && move.move[2][0] == '*')) {
            isWinInNextMove = true;
        } else if ((move.move[0][0] == charToBeChecked && move.move[1][0] == charToBeChecked && move.move[2][0] == '*') || (move.move[1][0] == charToBeChecked && move.move[2][0] == charToBeChecked && move.move[0][0] == '*') || (move.move[2][0] == charToBeChecked && move.move[0][0] == charToBeChecked && move.move[1][0] == '*')) {
            isWinInNextMove = true;
        } else if ((move.move[0][1] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[2][1] == '*') || (move.move[1][1] == charToBeChecked && move.move[2][1] == charToBeChecked && move.move[0][1] == '*') || (move.move[2][1] == charToBeChecked && move.move[0][1] == charToBeChecked && move.move[1][1] == '*')) {
            isWinInNextMove = true;
        } else if ((move.move[0][2] == charToBeChecked && move.move[1][2] == charToBeChecked && move.move[2][2] == '*') || (move.move[1][2] == charToBeChecked && move.move[2][2] == charToBeChecked && move.move[0][2] == '*') || (move.move[2][2] == charToBeChecked && move.move[0][2] == charToBeChecked && move.move[1][2] == '*')) {
            isWinInNextMove = true;
        } else if ((move.move[0][0] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[2][2] == '*') || (move.move[1][1] == charToBeChecked && move.move[2][2] == charToBeChecked && move.move[0][0] == '*') || (move.move[2][2] == charToBeChecked && move.move[0][0] == charToBeChecked && move.move[1][1] == '*')) {
            isWinInNextMove = true;
        } else if ((move.move[0][2] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[2][0] == '*') || (move.move[1][1] == charToBeChecked && move.move[2][0] == charToBeChecked && move.move[0][2] == '*') || (move.move[2][0] == charToBeChecked && move.move[0][2] == charToBeChecked && move.move[1][1] == '*')) {
            isWinInNextMove = true;
        }
        return isWinInNextMove;
    }

    private boolean isAlreadyWinningMove(Move move, int player) {
        boolean isWinInNextMove = false;
        char charToBeChecked = 'O';
        if (player == 2) {
            charToBeChecked = 'X';
        }

        if (move.move[0][0] == charToBeChecked && move.move[0][1] == charToBeChecked && move.move[0][2] == charToBeChecked) {
            isWinInNextMove = true;
        }//check second row
        else if (move.move[1][0] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[1][2] == charToBeChecked) {
            isWinInNextMove = true;
        } else if (move.move[2][0] == charToBeChecked && move.move[2][1] == charToBeChecked && move.move[2][2] == charToBeChecked) {
            isWinInNextMove = true;
        } else if (move.move[0][0] == charToBeChecked && move.move[1][0] == charToBeChecked && move.move[2][0] == charToBeChecked) {
            isWinInNextMove = true;
        } else if (move.move[0][1] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[2][1] == charToBeChecked) {
            isWinInNextMove = true;
        } else if (move.move[0][2] == charToBeChecked && move.move[1][2] == charToBeChecked && move.move[2][2] == charToBeChecked) {
            isWinInNextMove = true;
        } else if (move.move[0][0] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[2][2] == charToBeChecked) {
            isWinInNextMove = true;
        } else if (move.move[0][2] == charToBeChecked && move.move[1][1] == charToBeChecked && move.move[2][0] == charToBeChecked) {
            isWinInNextMove = true;
        }
        return isWinInNextMove;
    }


    public static void main(String[] args) {
        TicTacToe ticTacToe = new TicTacToe();
        ticTacToe.buildAllMovesTree();
        Scanner scanner = new Scanner(System.in);
        Move move = new Move(null);
        boolean run = true;
        System.out.println("Before First Move");
        System.out.println(ticTacToe.root);
        while (run) {
            Move compMove = ticTacToe.computerMakeMove(move, 2);
            if (compMove == null) {
                return;
            } else {
                System.out.println(compMove);
            }
            move = compMove;
            if (!ticTacToe.nextMoveAvailable(move)) {
                System.out.println("Draw!! No one wins");
                return;
            }
            System.out.println("Please put coordinate of move in (x,y)");
            boolean correctInFormat = false;
            while (!correctInFormat) {
                String x = scanner.next();
                int cor1 = 0;
                int cor2 = 0;
                if (x.contains(",")) {
                    int separator = x.indexOf(",");
                    try {
                        cor1 = Integer.valueOf(x.substring(0, separator));
                        cor2 = Integer.valueOf(x.substring(2, x.length()));
                    } catch (NumberFormatException e) {
                        System.out.println("Please put moves in x,y format");
                        continue;
                    }
                } else {
                    System.out.println("Please put moves in x,y format");
                    continue;
                }
                move.move[cor1 - 1][cor2 - 1] = 'O';
                System.out.println(move);
                correctInFormat = true;
                if (ticTacToe.isAlreadyWinningMove(move, 1)) {
                    System.out.println("You win");
                    return;
                }
            }
        }
        System.out.println(ticTacToe.root);
    }

    private Move computerMakeMove(Move move, int player) {
        //for current move, find all the
        Move[] nextMoves = findNextMoves(move, player);
        Queue<Move> queue = new PriorityQueue<>(nextMoves.length, (o1, o2) -> o1.winCount <= o2.winCount ? 1 : -1);
        for (Move nextMove : nextMoves) {
            nextMove.rank = findRank(nextMove);
            Move myMove = searchNode(root, nextMove);
            if (myMove != null) {
                queue.add(myMove);
            }
        }
        Move computerMove = queue.poll();
        if (isAlreadyWinningMove(computerMove, player)) {
            //computer wins
            System.out.println(computerMove);
            System.out.println("Computer wins");
            return null;
        } else if (isWinning(move, 1)) {
            //block 1 from win
            computerMove = move;
            int[] pos = returnWinPosition(computerMove, 1);
            computerMove.move[pos[0]][pos[1]] = 'X';
            if (isAlreadyWinningMove(computerMove, player)) {
                //computer wins
                System.out.println(computerMove);
                System.out.println("Computer wins");
                return null;
            }
        }
        return computerMove;
    }

    private int findRank(Move nextMove) {
        int rank = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (nextMove.move[i][j] != '*') {
                    rank++;
                }
            }
        }
        return rank;
    }

    private int[] returnWinPosition(Move move, int player) {
        int[] winPos = new int[2];
        char ch = 'X';
        if (player == 1) {
            ch = 'O';
        }
        if (move.move[0][0] == ch && move.move[0][1] == ch && move.move[0][2] == '*') {
            winPos[0] = 0;
            winPos[1] = 2;
        }
        if (move.move[0][0] == ch && move.move[0][2] == ch && move.move[0][1] == '*') {
            winPos[0] = 0;
            winPos[1] = 1;
        }
        if (move.move[0][2] == ch && move.move[0][1] == ch && move.move[0][0] == '*') {
            winPos[0] = 0;
            winPos[1] = 0;
        }
        if (move.move[1][0] == ch && move.move[1][1] == ch && move.move[1][2] == '*') {
            winPos[0] = 1;
            winPos[1] = 2;
        }
        if (move.move[1][1] == ch && move.move[1][2] == ch && move.move[1][0] == '*') {
            winPos[0] = 1;
            winPos[1] = 0;
        }
        if (move.move[1][2] == ch && move.move[1][0] == ch && move.move[1][1] == '*') {
            winPos[0] = 1;
            winPos[1] = 1;
        }
        if (move.move[2][0] == ch && move.move[2][1] == ch && move.move[2][2] == '*') {
            winPos[0] = 2;
            winPos[1] = 2;
        }
        if (move.move[2][1] == ch && move.move[2][2] == ch && move.move[2][0] == '*') {
            winPos[0] = 2;
            winPos[1] = 0;
        }
        if (move.move[2][2] == ch && move.move[2][0] == ch && move.move[2][1] == '*') {
            winPos[0] = 2;
            winPos[1] = 1;
        }

        //columns now
        if (move.move[0][0] == ch && move.move[1][0] == ch && move.move[2][0] == '*') {
            winPos[0] = 2;
            winPos[1] = 0;
        }
        if (move.move[1][0] == ch && move.move[2][0] == ch && move.move[0][0] == '*') {
            winPos[0] = 0;
            winPos[1] = 0;
        }
        if (move.move[2][0] == ch && move.move[0][0] == ch && move.move[1][0] == '*') {
            winPos[0] = 1;
            winPos[1] = 0;
        }
        if (move.move[0][1] == ch && move.move[1][1] == ch && move.move[2][1] == '*') {
            winPos[0] = 2;
            winPos[1] = 1;
        }
        if (move.move[1][1] == ch && move.move[2][1] == ch && move.move[0][1] == '*') {
            winPos[0] = 0;
            winPos[1] = 1;
        }
        if (move.move[2][1] == ch && move.move[0][1] == ch && move.move[1][1] == '*') {
            winPos[0] = 1;
            winPos[1] = 1;
        }

        if (move.move[0][2] == ch && move.move[1][2] == ch && move.move[2][2] == '*') {
            winPos[0] = 2;
            winPos[1] = 2;
        }
        if (move.move[1][2] == ch && move.move[2][2] == ch && move.move[0][2] == '*') {
            winPos[0] = 0;
            winPos[1] = 2;
        }
        if (move.move[2][2] == ch && move.move[0][2] == ch && move.move[1][2] == '*') {
            winPos[0] = 1;
            winPos[1] = 2;
        }

        if (move.move[0][0] == ch && move.move[1][1] == ch && move.move[2][2] == '*') {
            winPos[0] = 2;
            winPos[1] = 2;
        }
        if (move.move[1][1] == ch && move.move[2][2] == ch && move.move[0][0] == '*') {
            winPos[0] = 0;
            winPos[1] = 0;
        }
        if (move.move[2][2] == ch && move.move[0][0] == ch && move.move[1][1] == '*') {
            winPos[0] = 1;
            winPos[1] = 1;
        }

        if (move.move[0][2] == ch && move.move[1][1] == ch && move.move[2][0] == '*') {
            winPos[0] = 2;
            winPos[1] = 0;
        }
        if (move.move[1][1] == ch && move.move[2][0] == ch && move.move[0][2] == '*') {
            winPos[0] = 0;
            winPos[1] = 2;
        }
        if (move.move[2][0] == ch && move.move[0][2] == ch && move.move[1][1] == '*') {
            winPos[0] = 1;
            winPos[1] = 1;
        }
        return winPos;
    }

    private void buildAllMovesTree() {
        buildAllMoves(this.root, 2);
    }
}
