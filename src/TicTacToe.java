import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
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
                    checkForWin(list, frame);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            });
            frame.add(buttons[i]);
        }

        frame.setLayout(new GridLayout(3, 3));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 900);
        frame.setVisible(true);
    }

    public void checkForWin(List<Integer> list, JFrame frame) throws InterruptedException {
        if ((list.get(0) == list.get(1) && list.get(1) == list.get(2)) ||
                (list.get(3) == list.get(4) && list.get(4) == list.get(5)) ||
                list.get(6) == list.get(7) && list.get(7) == list.get(8) ||
                list.get(0) == list.get(3) && list.get(3) == list.get(6) ||
                list.get(1) == list.get(4) && list.get(4) == list.get(7) ||
                list.get(2) == list.get(5) && list.get(5) == list.get(8) ||
                list.get(0) == list.get(4) && list.get(4) == list.get(8) ||
                list.get(2) == list.get(4) && list.get(4) == list.get(6)) {
            TimeUnit.SECONDS.sleep(2);
            frame.setVisible(false);
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        }
    }
}
