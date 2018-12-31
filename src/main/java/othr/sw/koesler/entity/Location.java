package othr.sw.koesler.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Location {


    int x_Coord;
    int y_Coord;

    public Location() {
    }

    public Location(int x, int y) {
        this.x_Coord = x;
        this.y_Coord = y;

    }

    public void setX_Coord(int x_Coord) {
        this.x_Coord = x_Coord;
    }

    public void setY_Coord(int y_Coord) {
        this.y_Coord = y_Coord;
    }

    public int getX_Coord() {
        return x_Coord;
    }

    public int getY_Coord() {
        return y_Coord;
    }
}
