import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

public class GameBoard extends JPanel{

    // Included components
    private JPanel score_panel;
    private JLabel high_score_l;
    private JLabel score_l;
    private JLabel FPS_l;
    private JLabel speed_l;

    private JPanel gameover;
    private GridBagConstraints c;
    private JLabel gameover_l;
    private JLabel final_score_l;
    private JLabel new_high_score_l;
    private JLabel restart_l;

    private ImageIcon apple;
    private ImageIcon snake;

    private int high_score;
    private int speed;
    private Snake sk;
    private boolean is_pause;
    private boolean is_game_over;

    Thread game;
    Thread frame;

    private int SNAKE_RATE;
    private int FRAME_RATE;

    private boolean is_key_hit;

    //Constructor
    GameBoard(int s, int f){
        this.setLayout(null);
        this.setBorder(BorderFactory.createMatteBorder(40, 10, 10, 10, Color.gray));

        // Rate
        FRAME_RATE = f;
        switch (s){
            case 1:
                SNAKE_RATE = 5;
                break;
            case 2:
                SNAKE_RATE = 10;
                break;
            case 3:
                SNAKE_RATE = 15;
                break;
            case 4:
                SNAKE_RATE = 20;
                break;
            case 5:
                SNAKE_RATE = 25;
                break;
            case 6:
                SNAKE_RATE = 30;
                break;
            case 7:
                SNAKE_RATE = 35;
                break;
            case 8:
                SNAKE_RATE = 40;
                break;
            case 9:
                SNAKE_RATE = 45;
                break;
            case 10:
                SNAKE_RATE = 50;
                break;
        }

        // Layout

        // Image resize
        ImageIcon temp_imgicon = new ImageIcon(this.getClass().getResource("apple.png"));
        Image img = temp_imgicon.getImage();
        Image newimg = img.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        apple = new ImageIcon(newimg);
        temp_imgicon = new ImageIcon(this.getClass().getResource("snake.png"));
        img = temp_imgicon.getImage();
        newimg = img.getScaledInstance(10, 10, java.awt.Image.SCALE_SMOOTH);
        snake = new ImageIcon(newimg);

        // Top bar
        score_panel = new JPanel();
        score_panel.setSize(800, 30);
        score_panel.setLayout(new GridBagLayout());
        c = new GridBagConstraints();
        score_panel.setBackground(Color.lightGray);

        Font font = new Font("Cooper Black", Font.BOLD, 20);
        high_score_l = new JLabel("High Score: 0");
        score_l = new JLabel("Score: 0");
        FPS_l = new JLabel("FPS: " + Integer.toString(FRAME_RATE));
        speed_l = new JLabel("Speed: " + Integer.toString(s));

        high_score_l.setFont(font);
        score_l.setFont(font);
        FPS_l.setFont(font);
        speed_l.setFont(font);
        high_score_l.setForeground(Color.gray);
        score_l.setForeground(Color.gray);
        FPS_l.setForeground(Color.gray);
        speed_l.setForeground(Color.gray);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.6;
        c.gridx = 0;
        c.gridy = 0;
        score_panel.add(high_score_l, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.6;
        c.gridx = 1;
        c.gridy = 0;
        score_panel.add(score_l, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.6;
        c.gridx = 2;
        c.gridy = 0;
        score_panel.add(FPS_l, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.gridy = 0;
        score_panel.add(speed_l, c);

        // Game over panel
        gameover = new JPanel();
        gameover.setLayout(new GridBagLayout());
        gameover.setSize(400, 300);
        gameover.setLocation(200, 150);
        gameover.setBackground(new Color(0, 0, 0, 10));

        gameover_l = new JLabel("Game Over");
        gameover_l.setFont(new Font("Serif", Font.BOLD, 60));
        gameover_l.setForeground(Color.decode("#515151"));
        final_score_l = new JLabel();
        final_score_l.setFont(new Font("Serif", Font.BOLD, 40));
        final_score_l.setForeground(Color.decode("#515151"));
        new_high_score_l = new JLabel();
        new_high_score_l.setFont(new Font("Serif", Font.BOLD, 40));
        new_high_score_l.setForeground(Color.decode("#515151"));
        restart_l = new JLabel("To Restart - Press R");
        restart_l.setFont(new Font("Serif", Font.BOLD, 30));
        restart_l.setForeground(Color.decode("#515151"));

        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 1;
        gameover.add(gameover_l, c);

        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 2;
        gameover.add(final_score_l, c);

        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 2;
        gameover.add(new_high_score_l, c);

        c.fill = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 3;
        gameover.add(restart_l, c);

        this.add(gameover);
        this.add(score_panel);

        addKeyListener(new TAdapter());
        this.setBackground(Color.decode("#D3D3D3"));
        high_score = 0;
        this.init();
    }

    //Init
    public void init(){
        sk = new Snake();
        speed = 10;
        is_pause = false;
        is_game_over = false;

        gameover.setOpaque(false);
        gameover_l.setVisible(false);
        final_score_l.setVisible(false);
        new_high_score_l.setVisible(false);
        restart_l.setVisible(false);

        game = new Thread(){
            public void run() {
                while (true){
                    if (!is_pause) {
                        move_snake();
                        is_key_hit = false;
                    }
                    if (is_game_over){
                        break;
                    }

                    try{
                        Thread.sleep(1000 / SNAKE_RATE);
                    } catch (InterruptedException ex) { }

                }
            }
        };

        frame = new Thread(){
            public void run() {
                while (true){
                    if (!is_pause) {
                        repaint();
                    }
                    if (is_game_over){
                        break;
                    }
                    try{
                        Thread.sleep(1000 / FRAME_RATE);
                    } catch (InterruptedException ex) { }

                }
            }
        };
    }

    // Play
    public void play(){
        setFocusable(true);
        requestFocusInWindow();
        game.start();
        frame.start();
    }

    // Move snake to see if game over
    public void move_snake(){
        int score = sk.current_score;
        if (!sk.move(speed)) {
            playSound("gameover");
            is_game_over = true;
            gameover.setOpaque(true);
            gameover_l.setVisible(true);
            restart_l.setVisible(true);

            if (sk.current_score > high_score){
                high_score = sk.current_score;
                high_score_l.setText("High Score: " + Integer.toString(high_score));
                new_high_score_l.setText("New High Score: " + Integer.toString(high_score));
                new_high_score_l.setVisible(true);
            }
            else {
                final_score_l.setText("Score: " + Integer.toString(sk.current_score));
                final_score_l.setVisible(true);
            }
        }
        if (sk.current_score > score){
            playSound("eat");
        }
        score_l.setText("Score: " + Integer.toString(sk.current_score));
    }

    // Play sound
    public static void playSound(String type) {
        Thread sound = new Thread() {
            public void run() {
                try {
                    URL url;
                    if (type == "eat") {
                        url = this.getClass().getResource("eat.wav");
                    }
                    else{
                        url = this.getClass().getResource("gameover.wav");
                    }
                    Clip clip = AudioSystem.getClip();
                    AudioInputStream inputStream = AudioSystem.getAudioInputStream(url);
                    clip.open(inputStream);
                    clip.start();
                } catch (Exception e) {
                }
            }
        };
        sound.start();
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);    // Paint background

        // Draw Board


        // Draw Snake
        g.setColor(Color.BLUE);
        for (int i = 0; i < sk.dot_list.size(); i++){
            int x = sk.dot_list.get(i).getX();
            int y = sk.dot_list.get(i).getY();
            snake.paintIcon(this, g, x, y);
            //g.fillOval(x, y, 10, 10);
        }

        // Draw Food
        g.setColor(Color.red);
        apple.paintIcon(this, g, sk.Food.x, sk.Food.y);
    }

    private class TAdapter extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key){
                case KeyEvent.VK_LEFT:
                    if (!is_key_hit) {
                        if (sk.dir != Snake.direction.EAST) {
                            sk.dir = Snake.direction.WEST;
                        }
                        is_key_hit = true;
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!is_key_hit) {
                        if (sk.dir != Snake.direction.WEST) {
                            sk.dir = Snake.direction.EAST;
                        }
                        is_key_hit = true;
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!is_key_hit) {
                        if (sk.dir != Snake.direction.SOUTH) {
                            sk.dir = Snake.direction.NORTH;
                        }
                        is_key_hit = true;
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!is_key_hit) {
                        if (sk.dir != Snake.direction.NORTH) {
                            sk.dir = Snake.direction.SOUTH;
                        }
                        is_key_hit = true;
                    }
                    break;
                case KeyEvent.VK_SPACE:
                    if (is_pause){
                        is_pause = false;
                    }
                    else {
                        is_pause = true;
                    }
                    break;
                case KeyEvent.VK_R:
                    if (is_game_over){
                        init();
                        play();
                    }
                    else {
                        init();
                    }
                    break;
            }
        }
    }
}
