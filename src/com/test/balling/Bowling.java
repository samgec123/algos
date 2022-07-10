package com.test.balling;

import java.io.IOException;

class Result {

    /*
     * Complete the 'isWinning' function below.
     *
     * The function is expected to return a STRING.
     * The function accepts following parameters:
     *  1. INTEGER n
     *  2. STRING config
     */

    public static int findWinningPos(String config, int player) {
        String nextMove = findNextMove(config);
        System.out.println("player" + player + " move:" + nextMove);
        char[] winPos = new char[config.length()];
        for (int i = 0; i < winPos.length; i++) {
            winPos[i] = 'X';
        }
        if (player == 2) {
            player = 1;
        } else {
            player = 2;
        }
        if (!nextMove.equals(String.valueOf(winPos))) {
            return findWinningPos(nextMove, player);
        } else {
            return player;
        }
      //  return player;
    }

    private static String findNextMove(String config) {
        //only one I or two consecutive II's
        int countOfI = 0;
        char[] chars = config.toCharArray();
        int winningPos = -1;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == 'I') {
                countOfI++;
            }
        }
        if (countOfI == 1) {
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == 'I') {
                    chars[i] = 'X';
                }
            }
            return String.valueOf(chars);
        } else if (countOfI == 2) {
            boolean twoMovePossible = false;
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] == 'I' && chars[i + 1] == 'I') {
                    twoMovePossible = true;
                    chars[i] = 'X';
                    chars[i + 1] = 'X';
                    break;
                }
            }

            if (!twoMovePossible) {
                for (int i = 0; i < chars.length - 1; i++) {
                    if (chars[i] == 'I') {
                        chars[i] = 'X';
                        break;
                    }
                }
            }

            return String.valueOf(chars);
        } else if (countOfI == 3) {
            if (config.contains("IXIXI") || config.contains("XIXIXI")) {
                for (int i = 0; i < chars.length - 1; i++) {
                    if (chars[i] == 'I') {
                        //consecutive I's not wining pos
                        chars[i] = 'X';
                        return String.valueOf(chars);
                    }
                }
            } else {
                for (int i = 0; i < chars.length - 1; i++) {
                    if (chars[i] == 'I' && chars[i + 1] == 'I') {
                        chars[i] = 'X';
                        break;
                    }
                }
                return String.valueOf(chars);
            }
        } else {
            boolean twoMovePossible = false;
            if (countOfI % 2 == 1) {
                //try to move 2

                for (int i = 0; i < chars.length - 1; i++) {
                    if (chars[i] == 'I' && chars[i + 1] == 'I') {
                        twoMovePossible = true;
                        chars[i] = 'X';
                        chars[i + 1] = 'X';
                        break;
                    }
                }
                return String.valueOf(chars);
            } else if (!twoMovePossible) {
                for (int i = 0; i < chars.length - 1; i++) {
                    if (chars[i] == 'I') {
                        chars[i] = 'X';
                        break;
                    }
                }
                return String.valueOf(chars);
            }
        }
        return config;
    }
}

public class Bowling {
    public static void main(String[] args) throws IOException {
        //     String result = Result.isWinning(3, "IXIXIXIIIXIIXIIX");

        int player = Result.findWinningPos("IIXXIIIIII", 1);
        System.out.println(player);
    }
}
