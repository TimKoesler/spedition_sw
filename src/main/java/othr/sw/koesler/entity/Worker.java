package othr.sw.koesler.entity;

import othr.sw.koesler.entity.util.GeneratedIdEntity;

public class Worker extends GeneratedIdEntity {

    public static long id_count = 0;

    public Worker() {
        super.id = this.id_count;
        id_count++;
    }
}
