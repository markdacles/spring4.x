import java.time.*;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;

public class PersonnelDaoImpl implements PersonnelDao{

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
    }

    public Session getCurrentSession() {
      return sessionFactory.getCurrentSession();
    }

  	public List<Personnel> findAll() {
    return (List<Personnel>) getCurrentSession().createQuery("from Personnel").setCacheable(true).list();
  	}

    public Personnel getPersonnel(Long id) {
      return (Personnel) getCurrentSession().get(Personnel.class, id);
    }

    public void addContactToPersonnel(Personnel p) {
      getCurrentSession().update(p);
    }

    public void contactUpdate(Personnel p) {
      getCurrentSession().update(p);
    }

    public void removeContact(Personnel p) {
      getCurrentSession().update(p);
    }
}