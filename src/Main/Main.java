package Main;

import Constant.Sprite;

import javax.swing.*;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Sprite sprite = new Sprite();
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("BOMBERMAN");

        window.add(new GamePanel());
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
