package data;

/**
 * Created by flysLi on 2019/1/7.
 */
public interface DataBaseDrop {
    void remove(String name);

    void createDataBase(String name);

    void updateDatabase(String name, String nname);
}
