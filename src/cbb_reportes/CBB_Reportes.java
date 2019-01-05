/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbb_reportes;

import javafx.geometry.Rectangle2D;
import com.mysql.jdbc.Statement;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.StandardOpenOption;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;
import mysql.MysqlConnect;

/**
 *
 * @author saulmestanza
 */
public class CBB_Reportes extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        MysqlConnect mysqlConnect = new MysqlConnect();
        String sql = "SELECT * FROM config;";
        try {
            Statement st = (Statement) mysqlConnect.connect().createStatement();
            ResultSet rs = st.executeQuery(sql);
            if (!rs.isBeforeFirst() ) {
                goToStage(stage, "FXMLDocument.fxml");
            }
            while (rs.next()){
                if(rs.getBoolean("is_logged")){
                    sql = "SELECT * FROM usuarios WHERE id="+rs.getInt("user_id")+";";
                    try {
                        st = (Statement) mysqlConnect.connect().createStatement();
                        rs = st.executeQuery(sql);
                        while (rs.next()){
                            boolean is_active = rs.getBoolean("is_active");
                            if(is_active){
                                try{
                                    goToStage(stage, "MainPage.fxml");
                                }catch(Exception e){
                                    goToStage(stage, "FXMLDocument.fxml");
                                    e.printStackTrace();
                                }
                            }else{
                                 goToStage(stage, "FXMLDocument.fxml");
                            }
                        }
                    }catch(Exception e1){
                        e1.printStackTrace();
                    }
                }else{
                    goToStage(stage, "FXMLDocument.fxml");
                }
            }
            st.close();
            rs.close();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            mysqlConnect.disconnect();
        }
    }

    private void goToStage(Stage stage, String fxml){
        Parent root;
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
        try {
            
            String userHome = System.getProperty("user.home");
            File file = new File(userHome, "my.lock");
            FileChannel fc = FileChannel.open(file.toPath(),
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE
            );
            FileLock lock = fc.tryLock();
            if (lock == null) {
                System.out.println("another instance is running");
            }else{
                root = FXMLLoader.load(getClass().getResource(fxml));
                Scene scene = new Scene(root);
                stage.getIcons().add(new Image(CBB_Reportes.class.getResourceAsStream("/img/cbc_logo.png")));
                stage.setScene(scene);
                stage.setResizable(false);
                stage.setTitle("CBB - Permisos");
                stage.show();
            }
        } catch (IOException ex) {
            Logger.getLogger(CBB_Reportes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
