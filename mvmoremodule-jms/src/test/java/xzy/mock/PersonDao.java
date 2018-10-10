package xzy.mock;

public interface PersonDao {
    Person getPerson(int id);
    boolean update(Person person);
}