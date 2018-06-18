import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactServiceImpl implements ContactService {

    private static PersonnelDaoImpl personnelDaoImpl;
    private static Logger logger = LoggerFactory.getLogger(ContactServiceImpl.class);

    public void setPersonnelDaoImpl(PersonnelDaoImpl personnelDaoImpl){
      this.personnelDaoImpl = personnelDaoImpl;
    }

    public void addContactToPersonnel(Personnel p) {
        logger.info("ContactService addContactToPersonnel method");
        personnelDaoImpl.addContactToPersonnel(p);
    }

    public void updateContact(Personnel p) {
        logger.info("ContactService updateContact method");
        personnelDaoImpl.contactUpdate(p);
    }

    public void removeContact(Long id, Long cid) {
        logger.info("ContactService removeContact method");
        Personnel p = personnelDaoImpl.getPersonnel(id);
        for(Contact c : p.getContact()) {
            if(c.getContactId() == cid) {
                p.getContact().remove(c);
                break;
            }
        }
        personnelDaoImpl.removeContact(p);
    }

    public boolean doesContactExist(Long id) {
        List<Personnel> personnelList =  personnelDaoImpl.findAll();
        for(Personnel p : personnelList) {
            for(Contact c : p.getContact()) {
                if(c.getContactId() == id) {
                    return true;
                }
            }
        }
        return false;
    }
}