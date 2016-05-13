package NFC;
/**
 * Created by Phenix on 12/05/2016.
 */

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;


public class DataBase {

    Statement stmt = null;
    Connection con = null;

    public DataBase() {
    }

    public void prepareToQuery() throws SQLException {
        Connection c = null;
        try {
            Class.forName("org.sqlite.JDBC");
            this.con = DriverManager.getConnection("jdbc:sqlite:F:/OneDrive/LP SIL IDSE/RFID (Mme Lesas)/Projet/MuseumNFC2/data/MuseNFC.db");
            this.stmt = con.createStatement();
            System.out.println("Database ready");
        } catch ( Exception e ) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public JSONObject getListOeuvres() throws SQLException, JSONException {
        ResultSet allCat = this.stmt.executeQuery("SELECT * FROM spots_nfc");
        JSONArray listCat = new JSONArray();
        JSONObject resList = new JSONObject();

        while (allCat.next()) {
            JSONObject obj = new JSONObject();
            obj.put("uid", allCat.getString("uid"));
            obj.put("name", allCat.getString("name"));
            obj.put("description", allCat.getString("description"));
            listCat.put(obj);
        }
        System.out.println("Database");
        System.out.println(listCat);
        resList.put("oeuvres",listCat);
        return resList;
    }

    public String addOeuvre(String uid, String param1, String param2) throws SQLException {

        this.stmt.executeUpdate("INSERT INTO spots_nfc (uid,name,description) VALUES ('" + uid + "','" + param1 + "','" + param2 + "')");
        return "Ajout ID Ok !";
    }

    public String deleteOeuvre(String uid) throws SQLException {

        this.stmt.executeUpdate("DELETE FROM spots_nfc WHERE uid = '" + uid + "'");
        return "Delete ID Ok !";
    }
}
