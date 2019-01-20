/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbb_reportes;

/**
 *
 * @author saulmestanza
 */
public class Settings {
    private static boolean DEBUG = false;
    public static String host = DEBUG ? "localhost" : "167.99.11.161";
    public static String username = DEBUG ? "cbc_user" : "cbb_user";
    public static String password = DEBUG ? "cbc_password" : "g/u}5?n~sA2x<fR>*(5~es(v]P}MC<fg";
    public static String version = "1.1.1";
    public static int user_id = 1;
    public static int LENGTH_NATIONAL_ID = 15;
    public static int LENGTH_NAMES = 250;
    public static int LENGTH_DESCRIPTION = 250;

    public static int getUser_id() {
        return user_id;
    }

    public static void setUser_id(int user_id) {
        Settings.user_id = user_id;
    }
    
    public static String getFullCode(int id) {
        if (id < 10) {
            return String.format("0000%d", id);
        } else if (id >= 10 && id < 100) {
            return String.format("000%d", id);
        } else if (id >= 100 && id < 1000) {
            return String.format("00%d", id);
        } else if (id >= 1000 && id < 10000) {
            return String.format("00%d", id);
        } else {
            return String.format("%d", id);
        }
    }
}
