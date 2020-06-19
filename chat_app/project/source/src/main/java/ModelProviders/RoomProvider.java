package ModelProviders;

import Model.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class RoomProvider implements IRoomProvider{
    @Override
    public void addRoom(int id1, int id2, String date){
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
            session.save(new Room(id1,id2, date));
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
            List<Room> usersHql = session.createQuery("from Room", Room.class).list();
            for (Room room : usersHql){
                String aux = "";
                String date = fixStrings(room.getCreationDate());
                aux += room.getId() + " " + room.getUser1() + " " + room.getUser2() + " " + date + " " + room.getStar();
                rez.add(aux);
            }
        }
        return rez;
    }
    @Override
    public List<String> gerRoomdsID() {
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
            List<Room> usersHql = session.createQuery("from Room", Room.class).list();
            for (Room room : usersHql){
                String aux = "";
                aux += room.getId() + " " + room.getUser1() + " " + room.getUser2();
                rez.add(aux);
            }
        }
        return rez;
    }
    @Override
    public void deleteRoomById(int id){
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
            List<Room> aux = session.createQuery("from Room where id = " + id, Room.class).list();
            session.delete(aux.get(0));
            session.getTransaction().commit();
        }
    }
    @Override
    public Room findRoomById(int id){
        Room room = null;
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
            List<Room> aux = session.createQuery("from Room where id = " + id, Room.class).list();
            room = aux.get(0);
        }
        return room;
    }
    @Override
    public int findRoomIdByUsernames(int user1, int user2){
        Room room = null;
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
            String a = "from Room where (user1 = " + user1 + " and user2 = " + user2 + ") or (user1 = " + user2 + " and user2 = " + user1 + ")";
            List<Room> aux = session.createQuery(a, Room.class).list();
            room = aux.get(0);
        }
        if(room.getId() > 0)
            return room.getId();
        return -1;
    }

    @Override
    public void updateRoomById(int id,int user1, int user2){
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
            List<Room> aux = session.createQuery("from Room where id = " + id, Room.class).list();
            Room x = aux.get(0);
            x.setUser1(user1);
            x.setUser2(user2);
            session.update(x);
            session.getTransaction().commit();
        }
    }
    @Override
    public boolean verifyRoom(int id){
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
            List<Room> aux = session.createQuery("from Room where id = " + id, Room.class).list();
            try{
                aux.get(0);
                return true;
            }catch (Exception ee){
                return false;
            }
        }
    }
    @Override   //ok
    public boolean verifyRoomByUsers(int user1, int user2){
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
            String a = "from Room where (user1 = " + user1 + " and user2 = " + user2 + ") or (user1 = " + user2 + " and user2 = " + user1 + ")";
            List<Room> aux = session.createQuery(a, Room.class).list();
            try{
                aux.get(0);
                return true;
            }catch (Exception ee){
                return false;
            }
        }
    }

    @Override
    public List<String> getAllRoomsUser2() {
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
            List<Room> usersHql = session.createQuery("from Room", Room.class).list();
            for (Room room : usersHql) {
                String aux = "";
                aux += room.getUser2();
                rez.add(aux);
            }
        }
        return rez;
    }

    @Override
    public List<String> getAllRoomsByUser(int id){
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
            List<Room> usersHql = session.createQuery("from Room where user1 = " + id + " or user2 = " + id, Room.class).list();
            for (Room room : usersHql) {
                String aux = "";
                if(room.getUser1() == id){
                    aux += room.getUser2();
                    rez.add(aux);
                }else if(room.getUser2() == id){
                    aux += room.getUser1();
                    rez.add(aux);
                }
            }
        }
        return rez;
    }

    @Override
    public void setRoomStar(int idR, int star){
        Room room = null;
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
            System.err.println("Error in hibernate: ");//NOPMD//NOPMD
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try(Session session = sessionFactory.openSession()) {
            List<Room> aux = session.createQuery("from Room where id = " + idR, Room.class).list();
            session.beginTransaction();
            room = aux.get(0);
            room.setStar(star);
            session.getTransaction().commit();
        }
    }
    @Override
    public List<String> getAllRoomsByUserStar(int id, int star){
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
            String query = "from Room where (user1 = " + id + " or user2 = " + id + ") and star = " + star;
            List<Room> usersHql = session.createQuery(query, Room.class).list();
            for (Room room : usersHql) {
                String aux = "";
                if(room.getUser1() == id){
                    aux += room.getUser2();
                    rez.add(aux);
                }else if(room.getUser2() == id){
                    aux += room.getUser1();
                    rez.add(aux);
                }
            }
        }
        return rez;
    }

    @Override
    public void setRoomBlock(int idR, int block){
        Room room = null;
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
            System.err.println("Error in hibernate: ");//NOPMD//NOPMD
            e.printStackTrace();
        }
        assert sessionFactory != null;
        try(Session session = sessionFactory.openSession()) {
            List<Room> aux = session.createQuery("from Room where id = " + idR, Room.class).list();
            session.beginTransaction();
            room = aux.get(0);
            room.setBlock(0);
            session.getTransaction().commit();
        }
    }

    @Override
    public List<String> getAllRoomsByUserBlockAndNoStar(int id, int block, int star){  //0 is blocked
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
            String query = "from Room where (user1 = " + id + " or user2 = " + id + ") and block = " + block + " and star = " + star;
            List<Room> usersHql = session.createQuery(query, Room.class).list();
            for (Room room : usersHql) {
                String aux = "";
                if(room.getUser1() == id){
                    aux += room.getUser2();
                    rez.add(aux);
                }else if(room.getUser2() == id){
                    aux += room.getUser1();
                    rez.add(aux);
                }
            }
        }
        return rez;
    }


    public String fixStrings(String s) {
        String sNew = s.replace(" ", "_");
        return sNew;
    }
}
