import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class SplashScreen extends JFrame{
    StartMenu startmenu;
    GameBoard gb;

    //Constructor
    SplashScreen(String title, int s, int f){
        super(title);

        this.setSize(800, 600);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        startmenu = new StartMenu();
        gb = new GameBoard(s, f);

        getContentPane().add(startmenu);
        startmenu.StartButton.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                getContentPane().removeAll();
                getContentPane().add(gb);
                repaint();
                printAll(getGraphics());
                gb.play();
            }
        });

        this.setVisible(true);
    }
}
