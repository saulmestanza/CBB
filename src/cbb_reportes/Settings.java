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
    public static String version = "1.0.2";
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
}
