package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

import javax.persistence.Entity;

@Entity
public class Location extends GeneratedIdEntity {

    private static long id_count = 1;

    int x_Coord;
    int y_Coord;

    public Location() {
    }

    public Location(int x, int y) {
        this.x_Coord = x;
        this.y_Coord = y;
        super.id = id_count;
        id_count++;

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
