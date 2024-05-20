/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tictactoe;

import java.awt.*;
import java.awt.event.*;

public class Tictactoe extends Frame implements ActionListener {
    private Button[][] gridButtons;
    private Label statusLabel;
    private boolean xTurn;

    public Tictactoe() {
        setTitle("Tic Tac Toe");
        setSize(300, 300);
        setLayout(new BorderLayout());

        Panel gridPanel = new Panel(new GridLayout(3, 3));
        gridButtons = new Button[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridButtons[i][j] = new Button("");
                gridButtons[i][j].setFont(new Font("Arial", Font.BOLD, 48));
                gridButtons[i][j].addActionListener(this);
                gridPanel.add(gridButtons[i][j]);
            }
        }

        statusLabel = new Label("Player X's turn");
        statusLabel.setAlignment(Label.CENTER);

        Button newGameButton = new Button("New Game");
        newGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resetGame();
            }
        });

        add(gridPanel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.NORTH);
        add(newGameButton, BorderLayout.SOUTH);

        xTurn = true;
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        Button button = (Button) e.getSource();
        if (!button.getLabel().equals("")) return;
        
        if (xTurn) {
            button.setLabel("X");
            statusLabel.setText("Player O's turn");
        } else {
            button.setLabel("O");
            statusLabel.setText("Player X's turn");
        }
        xTurn = !xTurn;
        
        if (checkWinner()) {
            String winner = xTurn ? "O" : "X";
            statusLabel.setText("Player " + winner + " wins!");
            disableGrid();
        } else if (isGridFull()) {
            statusLabel.setText("It's a draw!");
            disableGrid();
        }
    }

    private boolean checkWinner() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (!gridButtons[i][0].getLabel().equals("") &&
                gridButtons[i][0].getLabel().equals(gridButtons[i][1].getLabel()) &&
                gridButtons[i][0].getLabel().equals(gridButtons[i][2].getLabel())) {
                return true;
            }
        }
        // Check columns
        for (int i = 0; i < 3; i++) {
            if (!gridButtons[0][i].getLabel().equals("") &&
                gridButtons[0][i].getLabel().equals(gridButtons[1][i].getLabel()) &&
                gridButtons[0][i].getLabel().equals(gridButtons[2][i].getLabel())) {
                return true;
            }
        }
        // Check diagonals
        if (!gridButtons[0][0].getLabel().equals("") &&
            gridButtons[0][0].getLabel().equals(gridButtons[1][1].getLabel()) &&
            gridButtons[0][0].getLabel().equals(gridButtons[2][2].getLabel())) {
            return true;
        }
        if (!gridButtons[0][2].getLabel().equals("") &&
            gridButtons[0][2].getLabel().equals(gridButtons[1][1].getLabel()) &&
            gridButtons[0][2].getLabel().equals(gridButtons[2][0].getLabel())) {
            return true;
        }
        return false;
    }

    private boolean isGridFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (gridButtons[i][j].getLabel().equals("")) {
                    return false;
                }
            }
        }
        return true;
    }

    private void disableGrid() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridButtons[i][j].setEnabled(false);
            }
        }
    }

    private void resetGame() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gridButtons[i][j].setLabel("");
                gridButtons[i][j].setEnabled(true);
            }
        }
        statusLabel.setText("Player X's turn");
        xTurn = true;
    }

    public static void main(String[] args) {
        new Tictactoe();
    }
}

