import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                int s = Integer.parseInt(args[1]);
                int f = Integer.parseInt(args[0]);
                SplashScreen SS = new SplashScreen("Snake Game", s, f);
                SS.setVisible(true);
            }
        });

    }
}
