/**
 * Created by Phenix on 25/04/2016.
 */

package NFC;

import javax.smartcardio.*;
import java.sql.SQLException;
import java.util.List;

class MuseumNFC {
    private static Card card = null;
    private static CardChannel channel = null;
    private static CardTerminal terminal;

    public static void main(String [] args) throws CardException, SQLException{

        //Connection BDD
        DataBase DB_NFC = new DataBase();
        DB_NFC.prepareToQuery();


    }


}
