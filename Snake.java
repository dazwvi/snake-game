import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Snake {
    public enum direction { NORTH, SOUTH, EAST, WEST };

    public direction dir;
    public int current_score;
    public List<Dot> dot_list = new ArrayList<Dot>();
    public Dot Food;

    Snake(){
        current_score = 0;

        //Random initialization
        Random rand = new Random();
        int StartDirection = rand.nextInt(3);
        int StartX = rand.nextInt(30)*10 + 100;
        int StartY = rand.nextInt(20)*10 + 100;

        //Initialization
        dir = direction.values()[StartDirection];
        Dot dot1 = new Dot(StartX, StartY);
        Dot dot2 = new Dot(StartX, StartY);
        Dot dot3 = new Dot(StartX, StartY);
        switch (dir){
            case NORTH:
                dot2.editY(10);
                dot3.editY(20);
                dot_list.add(dot1);
                dot_list.add(dot2);
                dot_list.add(dot3);
                break;
            case SOUTH:
                dot2.editY(-10);
                dot3.editY(-20);
                dot_list.add(dot1);
                dot_list.add(dot2);
                dot_list.add(dot3);
                break;
            case WEST:
                dot2.editX(10);
                dot3.editX(20);
                dot_list.add(dot1);
                dot_list.add(dot2);
                dot_list.add(dot3);
                break;
            case EAST:
                dot2.editX(-10);
                dot3.editX(-20);
                dot_list.add(dot1);
                dot_list.add(dot2);
                dot_list.add(dot3);
                break;
            default: break;
        }
        Food = new Dot(0, 0);
        generate_food();

    }

    public boolean move(int speed){
        int headerX = dot_list.get(0).getX();
        int headerY = dot_list.get(0).getY();
        Dot newdot = new Dot(headerX, headerY);

        switch (dir){
            case NORTH:
                newdot.editY(-speed);
                break;
            case SOUTH:
                newdot.editY(speed);
                break;
            case WEST:
                newdot.editX(-speed);
                break;
            case EAST:
                newdot.editX(speed);
                break;
        }

        if (is_collision(newdot)){
            return false;
        }
        else{
            if (!is_food(newdot)) {
                dot_list.remove(dot_list.size() - 1);
            }
            dot_list.add(0, newdot);
            return true;
        }

    }

    public boolean is_collision(Dot dot){
        int x = dot.getX();
        int y = dot.getY();

        if ((x > 789) || (y > 569) || (x < 10) || (y < 40)){
            return true;
        }
        for (int i = 0; i < dot_list.size(); i++){
            if ((x == dot_list.get(i).getX()) && (y == dot_list.get(i).getY())){
                return true;
            }
        }

        return false;
    }

    public boolean is_food(Dot dot){
        int x = dot.getX();
        int y = dot.getY();

        if ((x == Food.getX()) && (y == Food.getY())){
            generate_food();
            current_score++;
            return true;
        }
        else {
            return false;
        }
    }

    public void generate_food(){
        Random rand = new Random();
        Food.x = rand.nextInt(70)*10 + 10;
        Food.y = rand.nextInt(50)*10 + 40;
        for (int i = 0; i < dot_list.size(); i++){
            if ((Food.x == dot_list.get(i).getX()) && (Food.y == dot_list.get(i).getY())){
                generate_food();
            }
        }
        return;
    }
}
