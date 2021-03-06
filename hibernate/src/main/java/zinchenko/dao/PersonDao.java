package zinchenko.dao;

import zinchenko.domain.MultiId;
import zinchenko.domain.Person;
import zinchenko.domain.Profession;

import java.util.Date;
import java.util.List;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public interface PersonDao {

    public List<Person> findAll();

    public Person find(MultiId multiId);

    public List<Person> find(List<MultiId> multiIds);

    public MultiId save(Person person);

    public void delete(Person person);

    public List<Person> findByProfession(Profession profession);

    public List<Person> findYoungerThen(Date birthDate);

    public List<Person> findYoungerOrEqualThen(Date birthDate);

    public List<Person> findOlderThen(Date birthDate);

    public List<Person> findOlderOrEqualThen(Date birthDate);

    public List<Person> findByProfessionName(String professionName);

}
