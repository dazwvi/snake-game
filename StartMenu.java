import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class StartMenu extends JPanel{
    public JButton StartButton;
    private ImageIcon play;
    private ImageIcon background;

    //Constructor
    StartMenu(){
        this.setLayout(new FlowLayout(FlowLayout.RIGHT));

        // Image resize
        ImageIcon temp_imgicon = new ImageIcon(this.getClass().getResource("background.jpg"));
        Image img = temp_imgicon.getImage();
        Image newimg = img.getScaledInstance(800, 600, java.awt.Image.SCALE_SMOOTH);
        background = new ImageIcon(newimg);

        temp_imgicon = new ImageIcon(this.getClass().getResource("play.png"));
        img = temp_imgicon.getImage();
        newimg = img.getScaledInstance(110, 80, java.awt.Image.SCALE_SMOOTH);
        play = new ImageIcon(newimg);

        StartButton = new JButton(play);
        this.add(StartButton);
    }

    @Override
    public void paintComponent(Graphics g){
        background.paintIcon(this, g, 0, 0);
    }
}
