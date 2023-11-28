package Game;

import javax.swing.*;

public class TicTacToe {
    private static final char[] m_board = {'1', '2', '3', '4', '5', '6', '7', '8', '9'};
    private final static char[] m_playerChars = {'X', 'O'};
    private static char m_current;
    private enum Player { X, O }

    public TicTacToe() {
        gameLoop();
    }

    private static void gameLoop() {
        String choice = JOptionPane.showInputDialog("Choose X or O");
        m_current =  Character.toUpperCase(choice.charAt(0));

        while(true) {
            if(hasWon(Player.X)) {
                JOptionPane.showMessageDialog(null, "X has won.");

                System.exit(0);
            }
            else if(hasWon(Player.O)) {
                JOptionPane.showMessageDialog(null, "O has won.");

                System.exit(0);
            }

            int index = Integer.parseInt(inputChar()) - 1;
            if(isFree(index)) {
                m_board[index] = m_current;
            }
            else {
                while(!isFree(index)) {
                    JOptionPane.showMessageDialog(null, "Invalid choice.");

                    index = Integer.parseInt(inputChar()) - 1;
                }

                m_board[index] = m_current;
            }

            m_current = (m_current == 'X') ? 'O' : 'X';
        }
    }

    private static boolean isFree(int index) {
        return m_board[index] != 'X' && m_board[index] != 'O';
    }

    private static String inputChar() {
        return JOptionPane.showInputDialog("| " + m_board[0] + " | " + m_board[1] + " | " + m_board[2] +" |" + "\n"
                                         + "| " + m_board[3] + " | " + m_board[4] + " | " + m_board[5] +" |" + "\n"
                                         + "| " + m_board[6] + " | " + m_board[7] + " | " + m_board[8] +" |" + "\n"
                                         + "Input position where you want to put " + m_current + ":");
    }

    private static boolean checkLine(int firstIndex, int secondIndex, int thirdIndex , Player player) {
        char playerChar = m_playerChars[player.ordinal()];

        return m_board[firstIndex] == playerChar && m_board[secondIndex] == playerChar && m_board[thirdIndex] == playerChar;
    }

    private static boolean hasWon(Player player) {
        for(int i = 0; i < 9; i += 3) {
            if(checkLine(i, i + 1, i + 2, player)) {
                return true;
            }
        }

        for(int i = 0; i < 3; i++) {
            if(checkLine(i, i + 3, i + 6, player)) {
                return true;
            }
        }

        return checkLine(0, 4, 8, player) || checkLine(2, 4, 6, player);
    }
}
