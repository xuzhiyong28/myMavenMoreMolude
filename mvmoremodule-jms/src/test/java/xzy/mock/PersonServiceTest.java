package xzy.mock;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class PersonServiceTest {
    private PersonDao personDao;
    private PersonService personService;

    @Before
    public void setUp(){
        personDao = Mockito.mock(PersonDao.class);
        Mockito.when(personDao.getPerson(1)).thenReturn(new Person(1,"Person1"));
        Mockito.when(personDao.update(Mockito.isA(Person.class))).thenReturn(true);
        personService = new PersonService(personDao);
    }

    @Test
    public void testUpdate(){
        boolean result = personService.update(1,"new name");
        Assert.assertTrue("true", result);
        Mockito.verify(personDao,Mockito.times(1)).getPerson(Mockito.eq(1));
    }
}
