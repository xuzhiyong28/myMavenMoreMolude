package xzy.mock;

public class PersonService {
    private final PersonDao personDao;

    public PersonService(PersonDao personDao) {
        this.personDao = personDao;
    }

    public boolean update(int id, String name) {
        Person person = personDao.getPerson(id);
        if (person == null) {
            return false;
        }
        Person personUpdate = new Person(person.getId(), name);
        boolean result = personDao.update(personUpdate);
        return result;
    }
}
