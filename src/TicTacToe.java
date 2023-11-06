import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class TicTacToe {
    TicTacToe() {
        List<Integer> list = new ArrayList<>(9);
        final boolean[] turn = {true};
        for (int i = 0; i < 9; i++)
            list.add(i + 3);

        JFrame frame = new JFrame();
        JButton[] buttons = new JButton[9];

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setFont(new Font("Comic San", Font.BOLD, 200));
            buttons[i].setBackground(Color.BLACK);
            buttons[i].setForeground(Color.WHITE);
            int finalI = i;
            buttons[i].addActionListener(e -> {
                list.set(finalI, turn[0] ? 1 : 2);
                if (turn[0]) {
                    buttons[finalI].setText("X");
                } else {
                    buttons[finalI].setText("O");
                }
                turn[0] = !turn[0];
                buttons[finalI].setEnabled(false);
                try {
                    checkForWin(list, frame, turn[0]);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
            frame.add(buttons[i]);
        }

        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1080, 1080);
        frame.setVisible(true);
    }

    public void checkForWin(List<Integer> list, JFrame frame, boolean x) throws InterruptedException {
        int counter = 0;
        for(Integer num : list){
            if (num == 1 || num == 2)
                counter++;
        }

        if ((Objects.equals(list.get(0), list.get(1)) && Objects.equals(list.get(1), list.get(2))) ||
                (Objects.equals(list.get(3), list.get(4)) && Objects.equals(list.get(4), list.get(5))) ||
                Objects.equals(list.get(6), list.get(7)) && Objects.equals(list.get(7), list.get(8)) ||
                Objects.equals(list.get(0), list.get(3)) && Objects.equals(list.get(3), list.get(6)) ||
                Objects.equals(list.get(1), list.get(4)) && Objects.equals(list.get(4), list.get(7)) ||
                Objects.equals(list.get(2), list.get(5)) && Objects.equals(list.get(5), list.get(8)) ||
                Objects.equals(list.get(0), list.get(4)) && Objects.equals(list.get(4), list.get(8)) ||
                Objects.equals(list.get(2), list.get(4)) && Objects.equals(list.get(4), list.get(6))) {
            frame.setVisible(false);
            finishScreen(x);
        }
        else if(counter == 9){
            draw();
        }
    }
    public static void finishScreen(boolean x){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        JLabel label = new JLabel();
        label.setFont(new Font("Comic San", Font.BOLD, 200));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        if(!x)
        label.setText("X Won!");
        else label.setText("O Won!");
        ImageIcon icon = new ImageIcon("wow.jpg");
        label.setIcon(icon);
        frame.add(label);
        frame.setVisible(true);
    }
    public static void draw(){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1920, 1080);
        JLabel label = new JLabel();
        label.setFont(new Font("Comic San", Font.BOLD, 200));
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setText("It's a draw");
        ImageIcon icon = new ImageIcon("wow.jpg");
        label.setIcon(icon);
        frame.add(label);
        frame.setVisible(true);
    }
}
