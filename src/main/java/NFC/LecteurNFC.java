/**
 * Created by Phenix on 25/04/2016.
 */
package NFC;

import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONString;
import org.codehaus.jettison.json.JSONArray;
import javax.smartcardio.*;

import java.sql.SQLException;
import java.util.List;

public class LecteurNFC {
    private static Card card = null;
    private static CardChannel channel = null;
    private static CardTerminal terminal;

    private static String uid;


    public boolean openConnection() throws CardException, SQLException {
        TerminalFactory factory = TerminalFactory.getDefault();
        CardTerminals cardterminals = factory.terminals();
        try {
            List<CardTerminal> terminals = cardterminals.list();
            System.out.println("\nTerminals: " + terminals);
            terminal = cardterminals.getTerminal(terminals.get(0).getName());
            readCard();
            return true;
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println("Veuillez brancher le module NFC");
            return false;
        }
    }

    private void readCard() throws CardException, SQLException {
        uid = null;
        while (true) {
            System.out.println("Presenter carte !");
            if(terminal.waitForCardPresent(10000) && terminal.isCardPresent()) {
                try {
                    System.out.println("Card detected!");

                    card = terminal.connect("*");
                    System.out.println("Card: " + card);

                    channel = card.getBasicChannel();
                    System.out.println("Channel: " + channel);

                    //Traitement de l'ATR de la carte
                    ATR atr = card.getATR();
                    byte[] baAtr = atr.getBytes();
                    System.out.print("ATR = 0x");
                    for (int i = 0; i < baAtr.length; i++)
                        System.out.printf("%02X ", baAtr[i]);
                    System.out.println();

                    //Traitement de l'UID de la carte
                    byte[] cmdApduGetCardUid = new byte[]{(byte) 0xFF, (byte) 0xCA, (byte) 0x00, (byte) 0x00, (byte) 0x00};
                    ResponseAPDU respApdu = channel.transmit(new CommandAPDU(cmdApduGetCardUid));
                    if (respApdu.getSW1() == 0x90 && respApdu.getSW2() == 0x00) {
                        byte[] baCardUid = respApdu.getData();
                        uid = "0x";
                        for (int i = 0; i < baCardUid.length; i++) {
                            uid += String.format("%02x", baCardUid[i]);
                        }
                        System.out.println("Card UID = " + uid);
                        return;
                    }

                } catch (CardException e) {
                    System.err.println(e);
                }
                try {
                    card.disconnect(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                while (!terminal.waitForCardAbsent(10000)) {
                    System.out.println("retirer carte");
                }
            }
        }
    }

    public String getCardData() throws CardException {
        return uid;
    }

}