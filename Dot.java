
public class Dot {
    public int x;
    public int y;

    //Constructor
    Dot(int x, int y){
        this.x = x;
        this.y = y;
    }

    int getX(){ return this.x; };
    int getY(){ return this.y; };
    void editX(int input_x){ this.x += input_x; };
    void editY(int input_y){ this.y += input_y; };

}
