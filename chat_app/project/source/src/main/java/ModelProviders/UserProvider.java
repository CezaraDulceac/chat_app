package ModelProviders;

import Model.Room;
import Model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class UserProvider implements IUserProvider {
    @Override
    public void addUser(String username, String password) {
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
            session.beginTransaction();
            session.save(new User(username, password));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<String> view() {
        List<String> rez = new ArrayList<String>();

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
            List<User> usersHql = session.createQuery("from User", User.class).list();
            for (User user : usersHql) {
                String aux = "";
                aux += user.getId()+ " " +user.getUsername() + " " + user.getPass() + " " + user.getStatus();
                rez.add(aux);
            }
        }
        return rez;
    }

    @Override
    public List<String> getAllUsernames() {
        List<String> rez = new ArrayList<String>();

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
            List<User> usersHql = session.createQuery("from User", User.class).list();
            for (User user : usersHql) {
                String aux = "";
                aux += user.getUsername();
                rez.add(aux);
            }
        }
        return rez;
    }

    @Override
    public void deleteUserById(int id) {
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
            session.beginTransaction();
            List<User> aux = session.createQuery("from User where id = " + id, User.class).list();
            session.delete(aux.get(0));
            session.getTransaction().commit();
        }
    }

    @Override
    public void updateUserById(int id, String username, String password) {
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
            List<User> aux = session.createQuery("from User where id = " + id, User.class).list();
            User x = aux.get(0);
            x.setUsername(username);
            x.setPass(password);
            session.update(x);
            session.getTransaction().commit();
        }
    }

    @Override
    public boolean verifyUser(int id) {
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
            List<User> aux = session.createQuery("from User where id = " + id, User.class).list();
            try {
                aux.get(0);
                return true;
            } catch (Exception ee) {
                return false;
            }
        }
    }

    @Override
    public boolean verifyUserByUsername(String user) {
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
            String q = "from User where username = '" + user + "'";
            List<User> aux = session.createQuery(q, User.class).list();
            try {
                User ii = aux.get(0);
                System.out.println(ii.getUsername());
                return true;
            } catch (Exception ee) {
                return false;
            }
        }
    }

    @Override
    public User findUserById(int id) {
        User user = null;
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
            List<User> aux = session.createQuery("from User where id = " + id, User.class).list();
            user = aux.get(0);
        }
        return user;
    }

    @Override
    public User findUserByUsername(String s) {
        User user = null;
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
            String q = "from User where username = '" + s + "'";
            List<User> aux = session.createQuery(q, User.class).list();
            user = aux.get(0);
        }
        return user;
    }
    @Override
    public User verUserByUsername(String username) {
        User user = null;
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
            String q = "from User where username = '" + username + "'";
            List<User> aux = session.createQuery(q, User.class).list();
            user = aux.get(0);
        }
        return user;
    }

    @Override
    public void setStatusById(int id, String stat){
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
            List<User> aux = session.createQuery("from User where id = " + id, User.class).list();
            User x = aux.get(0);
            x.setStatus(stat);
            session.update(x);
            session.getTransaction().commit();
        }
    }
    @Override
    public String getStatusById(int id){
        SessionFactory sessionFactory = null;
        String stat = null;
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
            List<User> aux = session.createQuery("from User where id = " + id, User.class).list();
            User x = aux.get(0);
            stat = x .getStatus();
            session.update(x);
            session.getTransaction().commit();
        }
        return stat;
    }

    @Override
    public boolean verifyUserAndPass(String username, String pass) {
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
            String q = "from User where username = '" + username + "'";
            List<User> aux = session.createQuery(q, User.class).list();
            User u = aux.get(0);
            if (u.getPass().equals(pass)) return true;
            return false;
        }
    }

    @Override
    public String  getUsernameById(int id) {
       String username = null;
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
            List<User> aux = session.createQuery("from User where id = " + id, User.class).list();
            User u = aux.get(0);
            username = u.getUsername();
        }
        return username;
    }

}


