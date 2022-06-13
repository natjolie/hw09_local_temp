package org.cis120;

import java.awt.*;
import javax.swing.*;

public class Screen implements Runnable {
    public void run() {
        JFrame frame = new JFrame("Game Play");
        JPanel panel = new JPanel();
        frame.add(panel);
        //score panel
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        JLabel status = new JLabel("Score: 3");
        status_panel.add(status);

        final GamePlay game = new GamePlay(status);
        frame.add(game, BorderLayout.CENTER);

        final JPanel buttons = new JPanel();
        frame.add(buttons, BorderLayout.NORTH);
        //play is set to do its action after the button is hit
        final JButton play = new JButton("Play");
        play.addActionListener(e -> game.setState(1));
        buttons.add(play);

        final JButton replay = new JButton("Replay");
        replay.addActionListener(e -> game.reset());
        buttons.add(replay);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        game.reset();

    }
}
