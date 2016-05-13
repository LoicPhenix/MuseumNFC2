package webservice.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.smartcardio.CardException;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import NFC.*;
import org.codehaus.jettison.json.JSONString;
import org.codehaus.jettison.json.JSONStringer;


@Path("/")
public class webservice {


    @Path("/readCard")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject readCard() throws JSONException,CardException, SQLException {
        String data = null;
        JSONObject res = new JSONObject();
        LecteurNFC lecteur = new LecteurNFC();
        boolean isTrue = lecteur.openConnection();
        if(isTrue == true){
            data = lecteur.getCardData();
        } else
            data = "-1";

        res.put("uid",data);
        return res;
    }

    @Path("/addOeuvre/{data}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public String addOeuvreDB(@PathParam("data") String data) throws JSONException, SQLException {
        JSONObject datas = new JSONObject(data);
        DataBase dataBase = new DataBase();
        dataBase.prepareToQuery();

        if (datas.getString("uid").length() > 2) {
            return dataBase.addOeuvre(datas.getString("uid"), datas.getString("name"), datas.getString("description"));
        } else {
            return "Taille de carte incorrecte";
        }
    }

    @Path("/listOeuvres")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public JSONObject listOeuvres() throws JSONException, SQLException {
        DataBase dataBase = new DataBase();
        dataBase.prepareToQuery();
        return dataBase.getListOeuvres();
    }

}