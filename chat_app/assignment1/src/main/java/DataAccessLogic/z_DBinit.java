package DataAccessLogic;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class z_DBinit {

    public z_DBinit()
    {
        Connection connection = null;
        try
        {
            // create a database connection
            connection = DriverManager.getConnection("jdbc:sqlite:bank.db");

            Statement statement = connection.createStatement();
            statement.setQueryTimeout(30);  // set timeout to 30 sec.

            statement.executeUpdate("drop table if exists employee");
            statement.executeUpdate("drop table if exists client");
            statement.executeUpdate("drop table if exists account");
            statement.executeUpdate("drop table if exists activity");
            statement.executeUpdate("drop table if exists utility");

            String sqlEmployee = "CREATE TABLE IF NOT EXISTS employee (\n"
                    + "    employeeId integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "    name text NOT NULL,\n"
                    + "    cnp text NOT NULL UNIQUE,\n"
                    + "    salary integer NOT NULL\n"
                    + ");";
            String sqlClient = "CREATE TABLE IF NOT EXISTS client (\n"
                    + "    idNumber integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "    name text NOT NULL,\n"
                    + "    cnp text NOT NULL UNIQUE,\n"
                    + "    address text,\n"
                    + "    phone text\n"
                    + ");";
            String sqlAccount = "CREATE TABLE IF NOT EXISTS account (\n"
                    + "    idNumber integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "    type text NOT NULL,\n"
                    + "    money integer,\n"
                    + "    date integer\n"
                    + ");";
            String sqlActivity = "CREATE TABLE IF NOT EXISTS activity (\n"
                    + "    id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "    employeeId integer NOT NULL,\n"
                    + "    description text,\n"
                    + "    date integer\n"
                    + ");";
            String sqlUtility = "CREATE TABLE IF NOT EXISTS utility (\n"
                    + "    id integer PRIMARY KEY AUTOINCREMENT,\n"
                    + "    clientId integer NOT NULL,\n"
                    + "    description text,\n"
                    + "    price integer\n"
                    + ");";

            statement.executeUpdate(sqlEmployee);
            statement.executeUpdate(sqlClient);
            statement.executeUpdate(sqlAccount);
            statement.executeUpdate(sqlActivity);
            statement.executeUpdate(sqlUtility);

            statement.executeUpdate("insert into client values(NULL,'Rafael_Burks', '186554785648', '197_San_Carlos_Ave_Rock_Hill_SC_29730', '2025550139')");
            statement.executeUpdate("insert into client values(NULL,'Ruby_Rose_Gordon', '286385685648', '425_Old_Brickell_St_Rocky_Mount_NC_27804', '2025550195')");
            statement.executeUpdate("insert into client values(NULL,'Menna_Wilson', '286554785688', '47_Second_Drive_Goodlettsville', '2025550173')");
            statement.executeUpdate("insert into client values(NULL,'Izaan_Gomez', '190565435648', '626_North_Sunbeam_Ave_LA_70301', '2025550181')");
            statement.executeUpdate("insert into client values(NULL,'Carlo_Davenport', '191667885648', '27_Courtland_Drive_CA_95301', '2025550127')");

            statement.executeUpdate("insert into account values(NULL,'personal', 2500, '326')"); //26 martie
            statement.executeUpdate("insert into account values(NULL,'personal', 8000, '201')"); // 1 februarie
            statement.executeUpdate("insert into account values(NULL,'professional', 10000, '710')"); // 10 iulie
            statement.executeUpdate("insert into account values(NULL,'personal', 150, '1201')"); // 1 decembrie
            statement.executeUpdate("insert into account values(NULL,'professional', 4500, '916')"); //6 seprembrie

            statement.executeUpdate("insert into employee values(NULL,'Moesha_Luna','298546325874',2400)");
            statement.executeUpdate("insert into employee values(NULL,'Huey_Cline','178546325874',2000)");
            statement.executeUpdate("insert into employee values(NULL,'Codey_Maldonado','188587965489',2400)");
            statement.executeUpdate("insert into employee values(NULL,'Koby_Hogan','177587458965',3000)");
            statement.executeUpdate("insert into employee values(NULL,'Leon_Guthrie','195254698547',2500)");

            statement.executeUpdate("insert into activity values(NULL,4, 'Money_Transfer', 404)");
            statement.executeUpdate("insert into activity values(NULL,1, 'Update_Account', 507)");
            statement.executeUpdate("insert into activity values(NULL,4, 'Add_Client', 118)");
            statement.executeUpdate("insert into activity values(NULL,3, 'Create_Account', 1201)");
            statement.executeUpdate("insert into activity values(NULL,2, 'Money_Transfer', 808)");

            statement.executeUpdate("insert into utility values(NULL,1, 'Money_Transfer', 50)");


            ResultSet rs = statement.executeQuery("select * from activity");
            while(rs.next())
            {
                // read the result set
                //System.out.println("Name = " + rs.getInt("id"));
                //System.out.println("id = " + rs.getInt("employeeId"));
               // System.out.println("CNP = " + rs.getString("cnp"));
                //System.out.println("salary = " + rs.getInt("salary"));
                //System.out.println("\n");
            }
        }
        catch(SQLException e)
        {
            // if the error message is "out of memory",
            // it probably means no database file is found
            System.err.println(e.getMessage());
        }
        finally
        {
            try
            {
                if(connection != null)
                    connection.close();
            }
            catch(SQLException e)
            {
                // connection close failed.
                System.err.println(e.getMessage());
            }
        }
    }
}
