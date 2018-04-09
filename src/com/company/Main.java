package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

public class Main extends JFrame implements ActionListener{

    private int counter = 0;
    int[] tellWhichButtons = new int[3];
    private List<JButton> buttons = new ArrayList<>();

    public Main(){
        setTitle("Kółko i krzyżyk");
        setSize(300, 300);
        GridLayout gridLayout = new GridLayout(3, 3);
        setLayout(gridLayout);

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton("");
            button.addActionListener(this);
            buttons.add(button);
            add(button);
        }

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Main();
            }
        }
        );
    }



    public void actionPerformed(ActionEvent e){
        JButton clickedButton = (JButton) e.getSource();
        if(counter%2 == 0) {
            clickedButton.setText("X");
        }else
            clickedButton.setText("O");

        clickedButton.setEnabled(false);
        counter++;
        if(isWinner()) {
            color();
            for(JButton b : buttons){
                b.setEnabled(false);
            }
            JOptionPane.showMessageDialog(null,"Wygrał: " + clickedButton.getText(), "Koniec gry!",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private boolean isWinner(){
        return (isWinner(0,1,2)||
        isWinner(3,4,5)||
        isWinner(6,7,8)||
        isWinner(0,3,6)||
        isWinner(1,4,7)||
        isWinner(2,5,8)||
        isWinner(0,4,8)||
        isWinner(6,4,2)
        );
    }

    private boolean isWinner(int i, int j, int k){
        boolean a = buttons.get(i).getText().equals(buttons.get(j).getText()) &&
                buttons.get(j).getText().equals(buttons.get(k).getText()) &&
                !buttons.get(i).getText().equals("");
        if(a){
            tellWhichButtons[0] = i;
            tellWhichButtons[1] = j;
            tellWhichButtons[2] = k;
        }
        return a;
    }

    public void color(){
        Color col = new Color(0,230,0);
        if(isWinner()) {
            for(Integer v : tellWhichButtons){
                buttons.get(v).setBackground(col);
            }
        }
    }

}
