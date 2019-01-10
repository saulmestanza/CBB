/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbb_reportes;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import mysql.MysqlConnect;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Statement;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author saulmestanza
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    JFXTextField txt_usuario;

    @FXML
    JFXPasswordField txt_contrasena;

    @FXML
    ProgressIndicator login_progress;

    @FXML
    JFXButton login_button;

    @FXML
    private void onMouseEnteredLoggin(MouseEvent event) {
        // DropShadow shadow = new DropShadow();
        //btn_loggin.setEffect(shadow);
    }

    @FXML
    private void onMouseExitLoggin(MouseEvent event) {
        // btn_loggin.setEffect(null);
    }

    @FXML
    private void onLoggin(ActionEvent event) {
        if (txt_usuario.getText().equals("") || txt_contrasena.getText().equals("")) {
            showDialog("Error en el formulario", "Debe de ingresar usuario y contraseña.");
        } else {
            login_button.setVisible(false);
            login_progress.setVisible(true);
            MysqlConnect mysqlConnect = new MysqlConnect();
            String sql = "SELECT * FROM usuarios WHERE usuario='" + txt_usuario.getText() + "';";
            try {
                Statement st = (Statement) mysqlConnect.connect().createStatement();
                ResultSet rs = st.executeQuery(sql);
                if (!rs.isBeforeFirst()) {
                    login_button.setVisible(true);
                    login_progress.setVisible(false);
                    showDialog("Error en el formulario", "Usuario no existe");
                } else {
                    while (rs.next()) {
                        boolean is_active = rs.getBoolean("is_active");
                        if (is_active) {
                            if (!rs.getString("contrasena").equals(txt_contrasena.getText())) {
                                login_button.setVisible(true);
                                login_progress.setVisible(false);
                                showDialog("Error en el formulario", "Contraseña invalida");
                            } else {
                                int _id_usuario = rs.getInt("id");
                                Settings.setUser_id(_id_usuario);
                                st = (Statement) mysqlConnect.connect().createStatement();
                                st.execute("DELETE FROM config");
                                st.executeUpdate("INSERT INTO config (is_logged, user_id) VALUES (1, " + _id_usuario + ")");
                                try {
                                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                                    Parent root1 = (Parent) fxmlLoader.load();
                                    Stage stage = new Stage();
                                    stage.getIcons().add(new Image(CBB_Reportes.class.getResourceAsStream("/img/cbc_logo.png")));
                                    stage.setTitle("CBB - Permisos");
                                    stage.setScene(new Scene(root1));
                                    stage.setResizable(false);
                                    stage.show();
                                    ((Node) (event.getSource())).getScene().getWindow().hide();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        } else {
                            login_button.setVisible(true);
                            login_progress.setVisible(false);
                            showDialog("Error en el formulario", "Usuario inactivo");
                            break;
                        }
                    }
                }
                st.close();
                rs.close();
            } catch (SQLException e) {
                login_button.setVisible(true);
                login_progress.setVisible(false);
                showDialog("Error en el formulario", "Ha ocurrido un error, intente nuevamente");
                e.printStackTrace();
            } finally {
                mysqlConnect.disconnect();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void showDialog(String titulo, String text) {
        // Alert alert = new Alert(alert_type);
        Alert alert = new Alert(AlertType.INFORMATION);
        // alert.setTitle(titulo);
        alert.setTitle("Cuerpo Bomberos de Balzar");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
