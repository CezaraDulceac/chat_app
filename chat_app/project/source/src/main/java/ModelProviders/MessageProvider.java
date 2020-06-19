package ModelProviders;

import Model.Message;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class MessageProvider implements IMessageProvider{
    @Override
    public void addMess(int id1, int roomId,int groupId, String mess, String date){
        SessionFactory sessionFactory = null;
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure()
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
            session.beginTransaction();
            session.save(new Message(id1,roomId,groupId, mess, date));
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
            List<Message> usersHql = session.createQuery("from Message ", Message.class).list();
            for (Message mess : usersHql){
                String aux = "";
                aux += mess.getUser() + " " + mess.getMessage() + " " + mess.getCreationDate()+ " ";
                rez.add(aux);
            }
        }
        return rez;
    }
    @Override
    public void deleteMessageById(int id){
        SessionFactory sessionFactory = null;
        // A SessionFactory is set up once for an application!
        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure() // configures settings from hibernate.cfg.xml
                .build();
        try {
            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
        }
        catch (Exception e) {
            // The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
            // so destroy it manually.
            StandardServiceRegistryBuilder.destroy( registry );
            System.err.println("Error in hibernate: ");
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try(Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<Message> aux = session.createQuery("from Message where id = " + id, Message.class).list();
            session.delete(aux.get(0));
            session.getTransaction().commit();
        }
    }

    @Override
    public List<String> getAllMessages() {
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
            List<Message> usersHql = session.createQuery("from Message", Message.class).list();
            for (Message m : usersHql) {
                String aux = "";
                aux += m.getMessage();
                rez.add(aux);
            }
        }
        return rez;
    }
    @Override
    public List<String> getAllMessagesByRoom(int id){
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
            List<Message> usersHql = session.createQuery("from Message where roomId = " + id, Message.class).list();
            for (Message mess : usersHql) {
                String aux = "";

                aux += mess.getUser() + " (" + mess.getCreationDate() + "): " + mess.getMessage();
                rez.add(aux);

            }
        }
        return rez;
    }

    @Override
    public List<String> getAllMessagesByGroup(int id){
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
            List<Message> usersHql = session.createQuery("from Message where groupId = " + id, Message.class).list();
            for (Message mess : usersHql) {
                String aux = "";

                aux += mess.getUser() + " (" + mess.getCreationDate() + "): " + mess.getMessage();
                rez.add(aux);

            }
        }
        return rez;
    }
}
