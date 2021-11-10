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
    private static final boolean DEBUG = false;
    public static String host = "lgg2gx1ha7yp2w0k.cbetxkdyhwsb.us-east-1.rds.amazonaws.com";
    public static String username = "les3q9tv7dqgwvev";
    public static String password = "mruo1942bnwvhx9i";
    public static String version = "1.5.2";
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
