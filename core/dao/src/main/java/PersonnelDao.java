import java.util.*;

public interface PersonnelDao {

	public List<Personnel> findAll();

  public Personnel getPersonnel(Long id);

  public void addContactToPersonnel(Personnel p);

  public void contactUpdate(Personnel p);

  public void removeContact(Personnel p);
}