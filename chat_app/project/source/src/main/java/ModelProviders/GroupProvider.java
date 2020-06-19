package ModelProviders;

import Model.Grup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class GroupProvider implements IGroupProvider {
    @Override
    public void addGroup(int ownerId, int userId, String name) {
        SessionFactory sessionFactory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {

            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(new Grup(ownerId, userId, name));
            session.getTransaction().commit();
        }
    }

//    //@Override
//    public List<String> view() {
//        List<String> rez = new ArrayList<String>();
//
//        SessionFactory sessionFactory;
//        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
//                .configure() // configures settings from hibernate.cfg.xml
//                .build();
//        try {
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//        } catch (Exception e) {
//            StandardServiceRegistryBuilder.destroy(registry);
//            System.err.println("Error in hibernate: ");
//            e.printStackTrace();
//            return null;
//        }
//        try (Session session = sessionFactory.openSession()) {
//            List<Grup> usersHql = session.createQuery("from Grup", Grup.class).list();
//            for (Grup grup : usersHql) {
//                String aux = "";
//                aux += grup.getOwnerId() + " " + grup.getUserId();
//                rez.add(aux);
//            }
//        }
//        return rez;
//    }

    @Override   //ok
    public boolean verifyGroupByName(String name) {
        SessionFactory sessionFactory = null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            String q = "from Grup where name = '" + name + "'";
            List<Grup> aux = session.createQuery(q, Grup.class).list();
            try{
                aux.get(0);
                return true;
            }catch (Exception ee){
                return false;
            }
        }
    }
    @Override
    public List<Grup> getAllGroupsByUser(int id) {
        SessionFactory sessionFactory;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
            return null;
        }
        try (Session session = sessionFactory.openSession()) {
            String a = "from Grup where (ownerId = " + id +  " or userId = " + id + ") and allowed = 1";
            List<Grup> rez = session.createQuery(a, Grup.class).list();
            return rez;
        } catch (Exception e) {
            e.getStackTrace();
        }
        return null;
    }

    @Override
    public Grup findGroupByName(String s) {
        Grup g = null;
        SessionFactory sessionFactory = null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            String q = "from Grup where name = '" + s + "'";
            List<Grup> aux = session.createQuery(q, Grup.class).list();
            g = aux.get(0);
        }
        return g;
    }
    @Override
    public Grup findGroupByNameAndUser(String name, int user){
        Grup room = null;
        SessionFactory sessionFactory = null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {

            StandardServiceRegistryBuilder.destroy( registry );
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try(Session session = sessionFactory.openSession()) {
            String a = "from Grup where name = '" + name + "'";
            List<Grup> aux = session.createQuery(a, Grup.class).list();
            room = aux.get(0);
        }
        return room;
    }

    @Override   //ok
    public boolean verifuUserInRoomByIds(int groupId, int userId) {
        SessionFactory sessionFactory = null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            String q = "from Grup where id = " + groupId + " and userId = " + userId;
            List<Grup> aux = session.createQuery(q, Grup.class).list();
            try{
                aux.get(0);
                return true;
            }catch (Exception ee){
                return false;
            }
        }
    }

    @Override
    public void denyUser(int owner,int userId){
        SessionFactory sessionFactory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Grup> aux = session.createQuery("from Grup where ownerId = " + owner + " and userId = " + userId, Grup.class).list();
            try{
                Grup x = aux.get(0);
                x.setAllowed(0);
                session.update(x);
                session.getTransaction().commit();
            }catch(Exception e){
                System.out.println("nu e aici");
            }

        }
    }



}
