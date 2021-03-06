package zinchenko.dao.impl;

import org.hibernate.SessionFactory;
import zinchenko.dao.PersonDao;
import zinchenko.domain.MultiId;
import zinchenko.domain.Person;
import zinchenko.domain.Profession;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * User: zinchenko
 * Date: 12.01.14
 */
public class PersonHibernateHqlDao implements PersonDao {

    private SessionFactory sessionFactory;

    @Override
    public List<Person> findAll() {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person").list();
    }

    @Override
    public Person find(MultiId multiId) {
        return (Person) sessionFactory.getCurrentSession()
                .createQuery("from Person as person where person.id = :id")
                .setParameter("id", multiId).uniqueResult();
    }

    @Override
    public List<Person> find(List<MultiId> multiIds) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person p where p.id = (:ids)")
                .setParameterList("ids", multiIds)
                .list();
    }

    @Override
    public MultiId save(Person person) {
        return (MultiId) sessionFactory.getCurrentSession().save(person);
    }

    @Override
    public void delete(Person person) {
        sessionFactory.getCurrentSession().delete(person);
    }

    @Override
    public List<Person> findByProfession(Profession profession) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person as person " +
                        "where person.profession = :profession")
                .setParameter("profession", profession)
                .list();
    }

    @Override
    public List<Person> findYoungerThen(Date birthDate) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person as p where p.birthdate > :birthdate")
                .setDate("birthdate", birthDate).list();
    }

    @Override
    public List<Person> findYoungerOrEqualThen(Date birthDate) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person as p where p.birthdate >= :birthdate")
                .setDate("birthdate", birthDate).list();
    }

    @Override
    public List<Person> findOlderThen(Date birthDate) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person as p where p.birthdate < :birthdate")
                .setDate("birthdate", birthDate).list();
    }

    @Override
    public List<Person> findOlderOrEqualThen(Date birthDate) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person as p where p.birthdate <= :birthdate")
                .setDate("birthdate", birthDate).list();
    }

    @Override
    public List<Person> findByProfessionName(String professionName) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Person as p where p.profession.name = :name")
                .setString("name", professionName).list();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
