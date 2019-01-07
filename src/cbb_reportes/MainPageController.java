/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cbb_reportes;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.mysql.jdbc.Statement;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Permiso;
import modelo.Tipo_Permiso;
import modelo.Usuario;
import mysql.MysqlConnect;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;

import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.scene.control.skin.ComboBoxListViewSkin;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.BorderPane;
import modelo.Cantidad;
import modelo.Clientes;

/**
 * FXML Controller class
 *
 * @author saulmestanza
 */
public class MainPageController implements Initializable {

    private ArrayList<Tipo_Permiso> tps;
    private ArrayList<Permiso> permisos;

    @FXML
    private MenuItem add_permiso;
    @FXML
    private MenuItem edit_permiso;
    @FXML
    private MenuItem edit_permiso_generado;
    @FXML
    private MenuItem menu_liquidar_persona;

    @FXML
    private Pane pane_emision_permiso;
    @FXML
    private TextField emision_search_permiso;
    @FXML
    private ComboBox emision_tipo_permiso;
    @FXML
    private DatePicker emision_fecha;
    @FXML
    private TextField emision_nombre;
    @FXML
    private TextField emision_apellido;
    @FXML
    private TextField emision_cedula;
    @FXML
    private TextField emision_razon_social;
    @FXML
    private TextField emision_direccion;
    @FXML
    private TextField emision_descripcion;
    @FXML
    private ChoiceBox modo_permiso;
    @FXML
    private DatePicker fecha_ocasional;
    @FXML
    private DatePicker fecha_documento;
    @FXML
    private TextField numero_deposito;
    @FXML
    private JFXButton emision_generar;
    @FXML
    private TextField emision_vehiculo;
    @FXML
    private TextField emision_capacidad;
    @FXML
    private TextField emision_placa;
    @FXML
    private CheckBox emision_extintor;
    private String _path_list_;

    @FXML
    private Pane pane_consultar_permiso;
    @FXML
    private TextField consultar_search_permiso;
    @FXML
    private TextField consultar_parametro;
    @FXML
    private Label consultar_total_permiso;
    @FXML
    private ComboBox consultar_tipo_permiso;
    @FXML
    private TableView<Permiso> consultar_tv;
    @FXML
    private TableColumn<Permiso, Integer> consultar_column_id;
    @FXML
    private TableColumn<Permiso, String> consultar_column_permiso;
    @FXML
    private TableColumn<Permiso, String> consultar_column_fecha_emision;
    @FXML
    private TableColumn<Permiso, String> consultar_column_nombre;
    @FXML
    private TableColumn<Permiso, String> consultar_column_cedula;
    @FXML
    private TableColumn<Permiso, String> consultar_column_fecha_caducidad;
    @FXML
    private TableColumn<Permiso, String> consultar_column_visualizar;
    @FXML
    private ComboBox consultar_modo_permiso;

    @FXML
    private Pane pane_generado_editar_permiso;
    @FXML
    private TextField generado_editar_search_permiso;
    @FXML
    private ComboBox generado_editar_tipo_permiso;
    @FXML
    private DatePicker generado_editar_fecha;
    @FXML
    private TextField generado_editar_nombre;
    @FXML
    private TextField generado_editar_apellido;
    @FXML
    private TextField generado_editar_cedula;
    @FXML
    private TextField generado_editar_razon_social;
    @FXML
    private TextField generado_editar_direccion;
    @FXML
    private TextArea generado_editar_descripcion;
    @FXML
    private RadioButton generado_editar_rd_1;
    @FXML
    private RadioButton generado_editar_rd_2;
    @FXML
    private RadioButton generado_editar_modo_permanente;
    @FXML
    private RadioButton generado_editar_modo_ocasional;
    @FXML
    private DatePicker generado_editar_fecha_ocasional;
    @FXML
    private Pane pane_generado_permiso;
    @FXML
    private TextField generado_search_permiso;
    @FXML
    private TextField generado_parametro;
    @FXML
    private ComboBox generado_tipo_permiso;
    @FXML
    private TableView<Permiso> editar_generados_tv;
    @FXML
    private TableColumn<Permiso, Integer> generado_column_id;
    @FXML
    private TableColumn<Permiso, String> generado_column_permiso;
    @FXML
    private TableColumn<Permiso, String> generado_column_fecha_emision;
    @FXML
    private TableColumn<Permiso, String> generado_column_nombre;
    @FXML
    private TableColumn<Permiso, String> generado_column_cedula;
    @FXML
    private TableColumn<Permiso, String> generado_column_editar;
    @FXML
    private TableColumn<Permiso, String> generado_column_eliminar;

    @FXML
    private Pane pane_agregar_permiso;
    @FXML
    private TextField agregar_permiso_nombre;
    @FXML
    private TextField agregar_permiso_precio;
    @FXML
    private CheckBox agregar_permiso_is_active;

    @FXML
    private Pane pane_editar_permiso;
    @FXML
    private Button editar_actualizar;
    @FXML
    private TableView<Tipo_Permiso> editar_tv;
    @FXML
    private TableColumn<Tipo_Permiso, Integer> editar_column_id_permiso;
    @FXML
    private TableColumn<Tipo_Permiso, String> editar_column_nombre_permiso;
    @FXML
    private TableColumn<Tipo_Permiso, String> editar_column_precio_permiso;
    @FXML
    private TableColumn<Tipo_Permiso, Boolean> editar_column_activo_permiso;
    @FXML
    private TextField editar_nombre;
    @FXML
    private TextField editar_precio;
    @FXML
    private CheckBox editar_is_active;
    private int _id_edit_permiso;
    private int _id_edit_row_;

    @FXML
    private Pane pane_detalle_permiso;
    @FXML
    private TextField detalle_search_permiso;
    @FXML
    private ComboBox detalle_tipo_permiso;
    @FXML
    private DatePicker detalle_date_picker_desde;
    @FXML
    private DatePicker detalle_date_picker_hasta;
    @FXML
    private Label detalle_total_permiso;
    @FXML
    private TableView<Permiso> detalle_tv;
    @FXML
    private TableColumn<Permiso, Integer> detalle_column_id;
    @FXML
    private TableColumn<Permiso, String> detalle_column_permiso;
    @FXML
    private TableColumn<Permiso, String> detalle_column_fecha_emision;
    @FXML
    private TableColumn<Permiso, String> detalle_column_nombre;
    @FXML
    private TableColumn<Permiso, String> detalle_column_cedula;
    @FXML
    private TableColumn<Permiso, String> detalle_column_fecha_caducidad;
    @FXML
    private TableColumn<Permiso, String> detalle_column_visualizar;
    @FXML
    private ComboBox detalle_modo_permiso;

    @FXML
    private Pane pane_arqueo_caja;
    @FXML
    private TextField arqueo_search_permiso;
    @FXML
    private ComboBox arqueo_tipo_permiso;
    @FXML
    private DatePicker arqueo_date_picker_desde;
    @FXML
    private DatePicker arqueo_date_picker_hasta;
    @FXML
    private Label arqueo_total_permiso;
    @FXML
    private Label arqueo_total_precio;
    @FXML
    private TableView<Permiso> arqueo_tv;
    @FXML
    private TableColumn<Permiso, Integer> arqueo_column_id;
    @FXML
    private TableColumn<Permiso, String> arqueo_column_permiso;
    @FXML
    private TableColumn<Permiso, String> arqueo_column_fecha_emision;
    @FXML
    private TableColumn<Permiso, String> arqueo_column_nombre;
    @FXML
    private TableColumn<Permiso, String> arqueo_column_cedula;
    @FXML
    private TableColumn<Permiso, String> arqueo_column_valor;
    @FXML
    private TableColumn<Permiso, String> arqueo_column_fecha_caducidad;
    @FXML
    private TableColumn<Permiso, String> arqueo_column_visualizar;
    @FXML
    private ComboBox arqueo_modo_permiso;

    @FXML
    private Menu usuariosMenu;

    @FXML
    private Pane pane_lista_usuarios;
    @FXML
    private TableView<Usuario> usuario_tv;
    @FXML
    private TableColumn<Usuario, String> usuario_column_nombre;
    @FXML
    private TableColumn<Usuario, String> usuario_column_apellido;
    @FXML
    private TableColumn<Usuario, String> usuario_column_usuario;
    @FXML
    private TableColumn<Usuario, String> usuario_column_contrasena;
    @FXML
    private TableColumn<Usuario, Boolean> usuario_column_activo;
    @FXML
    private TableColumn<Usuario, Boolean> usuario_column_is_superuser;
    @FXML
    private JFXTextField usuario_nombre;
    @FXML
    private JFXTextField usuario_apellido;
    @FXML
    private JFXTextField usuario_usuario;
    @FXML
    private JFXPasswordField usuario_contrasena;
    @FXML
    private JFXCheckBox usuario_active;
    @FXML
    private JFXCheckBox usuario_is_superuser;
    private int user_id;
    private ArrayList<Usuario> usuarioList;

    @FXML
    private Pane pane_agregar_usuario;
    @FXML
    private JFXTextField usuario_agregar_nombre;
    @FXML
    private JFXTextField usuario_agregar_apellido;
    @FXML
    private JFXTextField usuario_agregar_usuario;
    @FXML
    private JFXPasswordField usuario_agregar_contrasena;
    @FXML
    private JFXCheckBox usuario_agregar_active;
    @FXML
    private JFXCheckBox usuario_agregar_is_superuser;

    private Usuario usuario;

    @FXML
    private Pane pane_liquidacion;
    @FXML
    private TableView<Clientes> liquidar_tv;
    @FXML
    private TableColumn<Clientes, String> liquidar_nombre;
    @FXML
    private TableColumn<Clientes, String> liquidar_razon_social;
    @FXML
    private TableColumn<Clientes, String> liquidar_ruc;
    @FXML
    private TableColumn<Clientes, String> liquidar_liquidar;
    @FXML
    private TextField liquidar_cedula;

    // VERSION
    @FXML
    private void versionMenuEvent(ActionEvent event) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Cuerpo Bomberos de Balzar");
        alert.setHeaderText(null);
        alert.setContentText("Versión 1.0.0");
        alert.showAndWait();
    }
    
    // LIQUIDAR
    @FXML
    private void liquidarMenuAction(ActionEvent event) {
        setVisiblePane(false, false, false, false, false, false, false, false, false, false, true);
        ArrayList<Clientes> clientesList = new ArrayList<>();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM clientes WHERE is_active = true;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Clientes clientes = new Clientes();
                    clientes.setId(rs.getInt("id"));
                    clientes.setNombre(rs.getString("nombre"));
                    clientes.setApellido(rs.getString("apellido"));
                    clientes.setCedula(rs.getString("cedula"));
                    clientes.setDireccion(rs.getString("direccion"));
                    clientes.setRazon_social(rs.getString("razon_social"));
                    clientesList.add(clientes);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        liquidar_nombre.setCellValueFactory(new PropertyValueFactory<>("FullName"));
        liquidar_razon_social.setCellValueFactory(new PropertyValueFactory<>("razon_social"));
        liquidar_ruc.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        liquidar_liquidar.setCellValueFactory(new PropertyValueFactory<>("liquidar"));

        liquidar_tv.getItems().setAll(clientesList);
        liquidar_tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                try {
                    if (liquidar_tv.getSelectionModel().getSelectedItem() != null) {
                        TableViewSelectionModel selectionModel = liquidar_tv.getSelectionModel();
                        ObservableList selectedCells = selectionModel.getSelectedCells();
                        TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                        Object val = tablePosition.getTableColumn().getCellData(newValue);
                        if (val.equals("Liquidar")) {
                            Clientes cliente = liquidar_tv.getSelectionModel().getSelectedItem();
                            Alert alert = new Alert(AlertType.CONFIRMATION);
                            alert.setTitle("Cuerpo Bomberos de Balzar");
                            alert.setHeaderText(null);
                            alert.setContentText(String.format("Está seguro que desea liquidar el usuario %s?", cliente.getFullName()));
                            Optional<ButtonType> result = alert.showAndWait();
                            if (result.get() == ButtonType.OK) {
                                getLiquidacion(cliente, clientesList);
                            } else {
                                alert.close();
                            }
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void getLiquidacion(Clientes cliente, ArrayList<Clientes> clientesList) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM permisos WHERE permisos.id_clientes = " + cliente.getId() + " ORDER BY id DESC LIMIT 1;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                if (!rs.isBeforeFirst()) {
                    liquidarPersona(cliente, clientesList);
                }
                while (rs.next()) {
                    String split[] = rs.getString("fecha_expiracion").split("-");
                    int fecha_expiracion = Integer.parseInt(split[0]);
                    int year = Calendar.getInstance().get(Calendar.YEAR);
                    if (fecha_expiracion < year) {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Cuerpo Bomberos de Balzar");
                        alert.setHeaderText(null);
                        alert.setContentText(String.format("El cliente %s tiene pendiente de pagos", cliente.getFullName()));
                        alert.showAndWait();
                    } else {
                        liquidarPersona(cliente, clientesList);
                    }
                }
            }
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

    private void liquidarPersona(Clientes cliente, ArrayList<Clientes> clientesList) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String query = "UPDATE clientes SET is_active = false WHERE id = ?";
            PreparedStatement pstmt = null;
            pstmt = mysqlConnect.connect().prepareStatement(query);
            pstmt.setString(1, String.valueOf(cliente.getId()));
            pstmt.executeUpdate();
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Cuerpo Bomberos de Balzar");
            alert.setHeaderText(null);
            alert.setContentText(String.format("El cliente %s ha sido liquidado.", cliente.getFullName()));
            alert.showAndWait();
            clientesList = new ArrayList();
            String sql = "SELECT * FROM clientes WHERE is_active = true;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Clientes clientes = new Clientes();
                    clientes.setId(rs.getInt("id"));
                    clientes.setNombre(rs.getString("nombre"));
                    clientes.setApellido(rs.getString("apellido"));
                    clientes.setCedula(rs.getString("cedula"));
                    clientes.setDireccion(rs.getString("direccion"));
                    clientes.setRazon_social(rs.getString("razon_social"));
                    clientesList.add(clientes);
                }
            }
            rs.close();
            liquidar_tv.refresh();
            liquidar_tv.getItems().clear();
            liquidar_tv.getItems().addAll(clientesList);
            empezarLiquidarPDF(cliente);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

    @FXML
    private void buscarUsuarioEvent(ActionEvent event) {
        String _parametro_ = liquidar_cedula.getText();
        ArrayList<Clientes> clientesList = new ArrayList<>();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM clientes WHERE is_active = true AND cedula LIKE '%" + _parametro_ + "%';";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Clientes clientes = new Clientes();
                    clientes.setId(rs.getInt("id"));
                    clientes.setNombre(rs.getString("nombre"));
                    clientes.setApellido(rs.getString("apellido"));
                    clientes.setCedula(rs.getString("cedula"));
                    clientes.setDireccion(rs.getString("direccion"));
                    clientes.setRazon_social(rs.getString("razon_social"));
                    clientesList.add(clientes);
                }
            }
            rs.close();
            liquidar_tv.refresh();
            liquidar_tv.getItems().clear();
            liquidar_tv.getItems().addAll(clientesList);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

    private void empezarLiquidarPDF(Clientes cliente) {
        File _file_ = null;
        try {
            String _directory_ = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
            String directoryName = String.format("%s/pdfs/%s/", _directory_, cliente.getApellido());
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            /**
             * ************* PERMISO **************
             *
             */

            _file_ = new File(String.format("%sliquidacion_%s.pdf", directoryName, cliente.getApellido()));
            OutputStream file = new FileOutputStream(_file_);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = null;

            document.setPageSize(PageSize.A4);
            document.setMargins(40, 40, 73, 10);
            document.newPage();
            generateLiquidarPDF(document, writer, cliente);
            canvas = writer.getDirectContentUnder();
            image = Image.getInstance(getClass().getClassLoader().getResource("img/permiso_liquidacion.png"));
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);
            /**
             * ************* CIERRE **************
             *
             */
            document.close();
            file.close();
            goToPrintDialog(_file_.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void generateLiquidarPDF(Document document, PdfWriter writer, Clientes cliente) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            DateFormat dateFormat = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            Date date = new Date();
            _p1_.add(String.format("%s", dateFormat.format(date)));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("    %s", cliente.getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", cliente.getDireccion()));
            p1.add(_p1_);
            document.add(p1);

            // COPIA
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(14f);
            _p1_.clear();
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", dateFormat.format(date)));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("    %s", cliente.getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", cliente.getDireccion()));
            p1.add(_p1_);
            document.add(p1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // FICHA INSPECCION
    @FXML
    private void imprimirFichaInspeccion(ActionEvent event) {
        String directory = generateFichaPdf();
        goToPrintDialog(directory);
    }

    public String generateFichaPdf() {
        String _directory_ = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String directoryName = String.format("%s/pdfs/", _directory_);
        String path = String.format("%sficha_inspeccion.pdf", directoryName);
        try {
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File _file_ = new File(path);

            if (_file_.exists()) {
                _file_.delete();
            }

            Document document = new Document();

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(_file_));

            document.setPageSize(PageSize.A4);
            document.setMargins(40, 40, 80, 100);
            document.open();

            PdfContentByte cb = writer.getDirectContent();
            PdfReader reader = new PdfReader(this.getClass().getClassLoader().getResource("pdf/ficha_inspeccion_template.pdf").toString());
            PdfImportedPage page = writer.getImportedPage(reader, 1);

            document.newPage();
            cb.addTemplate(page, 0, 0);

            Font tinyFont = new Font(Font.FontFamily.TIMES_ROMAN, 9, Font.NORMAL);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();
            _p1_.setFont(tinyFont);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Calendar c = Calendar.getInstance();
            _p1_.add(String.format("  %s", sdf.format(c.getTime())));
            p1.setLeading(168);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(Chunk.TABBING);
            p1.add(_p1_);
            document.add(p1);
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    //EMISION PERMISOS
    @FXML
    private void emisionMenuAction(ActionEvent event) {
        setVisiblePane(true, false, false, false, false, false, false, false, false, false, false);
        emision_cedula.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                emision_cedula.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        emision_nombre.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (emision_nombre.getText().length() >= Settings.LENGTH_NAMES) {
                    emision_nombre.setText(emision_nombre.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });

        emision_apellido.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (emision_apellido.getText().length() >= Settings.LENGTH_NAMES) {
                    emision_apellido.setText(emision_apellido.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });

        emision_razon_social.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (emision_razon_social.getText().length() >= Settings.LENGTH_NAMES) {
                    emision_razon_social.setText(emision_razon_social.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });

        emision_direccion.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (emision_direccion.getText().length() >= Settings.LENGTH_NAMES) {
                    emision_direccion.setText(emision_direccion.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });

        emision_descripcion.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (emision_descripcion.getText().length() >= Settings.LENGTH_DESCRIPTION) {
                    emision_descripcion.setText(emision_descripcion.getText().substring(0, Settings.LENGTH_DESCRIPTION));
                }
            }
        });

        emision_cedula.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (emision_cedula.getText().length() >= Settings.LENGTH_NATIONAL_ID) {
                    emision_cedula.setText(emision_cedula.getText().substring(0, Settings.LENGTH_NATIONAL_ID));
                }
            }
        });
        emision_tipo_permiso.getStylesheets().add(
                getClass().getResource(
                        "ComboBoxCSS.css"
                ).toExternalForm()
        );
        emision_search_permiso.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                List<String> _tps_ = emision_tipo_permiso.getItems();
                int _count_ = 0;
                for (String tp : _tps_) {
                    if (tp.toLowerCase().contains(newValue.toLowerCase())) {
                        emision_tipo_permiso.getSelectionModel().select(_count_);

                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) emision_tipo_permiso.getSkin();
                        ((ListView<?>) skin.getPopupContent()).scrollTo(_count_);
                        break;
                    }
                    _count_++;
                }
            } else {
                emision_tipo_permiso.getSelectionModel().select(0);
                ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) emision_tipo_permiso.getSkin();
                ((ListView<?>) skin.getPopupContent()).scrollTo(0);
            }
        });
        tps = new ArrayList<>();
        ObservableList cursors = FXCollections.observableArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM tipo_permiso WHERE is_active = True;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tps.add(tp);
                    cursors.add(tp.getTipo_permiso());
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        emision_tipo_permiso.setItems(cursors);
        emision_tipo_permiso.getSelectionModel().selectFirst();
        emision_tipo_permiso.setTooltip(new Tooltip("Elija un tipo de permiso"));
        emision_tipo_permiso.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {

            }
        });
        emision_fecha.setValue(LocalDate.now());

        ObservableList _permisos_ = FXCollections.observableArrayList();
        _permisos_.add("Transporte");
        _permisos_.add("Ocasional");
        _permisos_.add("Construcción");
        _permisos_.add("Funcionamiento");
        modo_permiso.setItems(_permisos_);
        fecha_ocasional.setDisable(true);
        fecha_ocasional.setValue(null);
        emision_descripcion.setVisible(false);
        emision_vehiculo.setVisible(false);
        emision_placa.setVisible(false);
        emision_capacidad.setVisible(false);
        emision_extintor.setVisible(false);
        modo_permiso.getSelectionModel().selectedItemProperty().addListener((options, oldValue, newValue) -> {
            if (newValue != null) {
                switch (newValue.toString()) {
                    case "Transporte":
                        fecha_ocasional.setDisable(false);
                        fecha_ocasional.setValue(null);
                        emision_descripcion.setVisible(true);
                        emision_descripcion.setPromptText("Para que transporte");
                        emision_vehiculo.setVisible(true);
                        emision_placa.setVisible(true);
                        emision_capacidad.setVisible(true);
                        emision_extintor.setVisible(true);
                        break;
                    case "Ocasional":
                        fecha_ocasional.setDisable(false);
                        fecha_ocasional.setValue(null);
                        emision_descripcion.setVisible(false);
                        emision_vehiculo.setVisible(false);
                        emision_placa.setVisible(false);
                        emision_capacidad.setVisible(false);
                        emision_extintor.setVisible(false);
                        break;
                    case "Construcción":
                        fecha_ocasional.setDisable(true);
                        fecha_ocasional.setValue(null);
                        emision_descripcion.setVisible(true);
                        emision_descripcion.setPromptText("Construcción de");
                        emision_vehiculo.setVisible(false);
                        emision_placa.setVisible(false);
                        emision_capacidad.setVisible(false);
                        emision_extintor.setVisible(false);
                        break;
                    case "Funcionamiento":
                        fecha_ocasional.setDisable(true);
                        fecha_ocasional.setValue(null);
                        emision_descripcion.setVisible(false);
                        emision_vehiculo.setVisible(false);
                        emision_placa.setVisible(false);
                        emision_capacidad.setVisible(false);
                        emision_extintor.setVisible(false);
                        break;
                    default:
                        fecha_ocasional.setDisable(true);
                        fecha_ocasional.setValue(null);
                        emision_descripcion.setVisible(false);
                        emision_vehiculo.setVisible(false);
                        emision_placa.setVisible(false);
                        emision_capacidad.setVisible(false);
                        emision_extintor.setVisible(false);
                }
            }
        });
    }

    @FXML
    private void emisionGenerarPermiso(ActionEvent event) {
        _path_list_ = "";
        if (modo_permiso.getSelectionModel().getSelectedItem() != null) {
            if (emisionIsEmpty()) {
                showDialog("Error", "Debe de llenar todos los datos para poder generar un permiso", AlertType.ERROR);
            } else if (emision_cedula.getText().length() != 10) {
                showDialog("Error", "Ingrese un número de cédula válido", AlertType.ERROR);
            } else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                Calendar c = Calendar.getInstance();
                String expiration_date = "";
                if (fecha_ocasional.getValue() == null) {
                    try {
                        c.setTime(sdf.parse(emision_fecha.getValue().toString()));
                        expiration_date = sdf.format(c.getTime()) + "-12-31";
                    } catch (ParseException ex) {
                        Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    try {
                        sdf = new SimpleDateFormat("yyyy-mm-dd");
                        c.setTime(sdf.parse(fecha_ocasional.getValue().toString()));
                        expiration_date = sdf.format(c.getTime());
                    } catch (ParseException ex) {
                        Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
                MysqlConnect mysqlConnect = new MysqlConnect();
                Clientes cliente = new Clientes();
                String sql = "SELECT * FROM clientes WHERE cedula = '" + emision_cedula.getText() + "';";
                ResultSet rs;
                try {
                    Statement st = (Statement) mysqlConnect.connect().createStatement();
                    rs = st.executeQuery(sql);
                    if (rs.next()) {
                        cliente.setId(rs.getInt("id"));
                        cliente.setNombre(rs.getString("nombre"));
                        cliente.setApellido(rs.getString("apellido"));
                        cliente.setCedula(rs.getString("cedula"));
                        cliente.setRazon_social(rs.getString("razon_social"));
                        cliente.setDireccion(rs.getString("direccion"));
                        cliente.setIs_active(rs.getBoolean("is_active"));
                        cliente.setIs_closed(rs.getBoolean("is_closed"));
                    } else {
                        String query = "INSERT INTO clientes(nombre, apellido, cedula, razon_social, direccion)"
                                + " values (?,?,?,?,?)";
                        PreparedStatement preparedStmt = mysqlConnect.connect().prepareStatement(query);
                        preparedStmt.setString(1, emision_nombre.getText());
                        preparedStmt.setString(2, emision_apellido.getText());
                        preparedStmt.setString(3, emision_cedula.getText());
                        preparedStmt.setString(4, emision_razon_social.getText());
                        preparedStmt.setString(5, emision_direccion.getText());
                        preparedStmt.execute();
                        sql = "SELECT * FROM clientes WHERE cedula = '" + emision_cedula.getText() + "';";
                        try {
                            st = (Statement) mysqlConnect.connect().createStatement();
                            rs = st.executeQuery(sql);
                            while (rs.next()) {
                                cliente.setId(rs.getInt("id"));
                                cliente.setNombre(rs.getString("nombre"));
                                cliente.setApellido(rs.getString("apellido"));
                                cliente.setCedula(rs.getString("cedula"));
                                cliente.setRazon_social(rs.getString("razon_social"));
                                cliente.setDireccion(rs.getString("direccion"));
                                cliente.setIs_active(rs.getBoolean("is_active"));
                                cliente.setIs_closed(rs.getBoolean("is_closed"));
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String query = "INSERT INTO permisos("
                            + "descripcion, fecha_emision, fecha_expiracion, "
                            + "modo_permiso, id_usuario, id_tipo_permiso, "
                            + "id_clientes, numero_deposito, fecha_documento,"
                            + "vehiculo_marca, extintor, capacidad, placa"
                            + ")"
                            + " values ("
                            + "?,?,?,"
                            + "?,?,?,"
                            + "?,?,?,"
                            + "?,?,?,?"
                            + ")";
                    PreparedStatement preparedStmt = mysqlConnect.connect().prepareStatement(query);
                    preparedStmt.setString(1, emision_descripcion.getText());
                    preparedStmt.setString(2, emision_fecha.getValue().toString());
                    preparedStmt.setString(3, expiration_date);
                    preparedStmt.setString(4, modo_permiso.getSelectionModel().getSelectedItem().toString());
                    preparedStmt.setInt(5, usuario.getId());
                    int position = emision_tipo_permiso.getSelectionModel().getSelectedIndex();
                    preparedStmt.setInt(6, tps.get(position).getId());
                    preparedStmt.setInt(7, cliente.getId());
                    preparedStmt.setString(8, numero_deposito.getText());
                    preparedStmt.setString(9, fecha_documento.getValue().toString());
                    preparedStmt.setString(10, emision_vehiculo.getText());
                    preparedStmt.setBoolean(11, emision_extintor.isSelected());
                    preparedStmt.setString(12, emision_capacidad.getText());
                    preparedStmt.setString(13, emision_placa.getText());
                    preparedStmt.execute();
                    emision_nombre.setText("");
                    emision_apellido.setText("");
                    emision_cedula.setText("");
                    emision_razon_social.setText("");
                    emision_direccion.setText("");
                    emision_descripcion.setText("");
                    fecha_documento.getEditor().setText("");
                    numero_deposito.setText("");
                    emision_vehiculo.setText("");
                    emision_capacidad.setText("");
                    emision_placa.setText("");
                    emision_extintor.setSelected(false);
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("Cuerpo Bomberos de Balzar");
                    alert.setHeaderText(null);
                    alert.setContentText("Espere unos momentos....");
                    alert.show();
                    CompletableFuture.supplyAsync(() -> {
                        getPermiso();
                        return null;
                    }).thenRun(() -> {
                        goToPrintDialog(_path_list_);
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                    showDialog("Error", "Ha ocurrido un error", AlertType.ERROR);
                } finally {
                    mysqlConnect.disconnect();
                }
            }
        } else {
            showDialog("Error", "Debe de elegir un tipo de permiso.", AlertType.ERROR);
        }
    }

    private boolean emisionIsEmpty() {
        return emision_nombre.getText().isEmpty() || emision_apellido.getText().isEmpty()
                || emision_cedula.getText().isEmpty()
                || emision_direccion.getText().isEmpty()
                || emision_fecha.getEditor().getText().isEmpty() || fecha_documento.getEditor().getText().isEmpty()
                || numero_deposito.getText().isEmpty();
    }

    private void getPermiso() {
        MysqlConnect mysqlConnect = new MysqlConnect();
        Permiso permiso = new Permiso();
        try {
            String sql = "SELECT permisos.numero_deposito, permisos.fecha_documento, "
                    + "permisos.vehiculo_marca, permisos.extintor, permisos.placa, "
                    + "permisos.capacidad, permisos.id, permisos.descripcion, "
                    + "permisos.fecha_emision, permisos.fecha_expiracion, "
                    + "permisos.ruta_pdf, permisos.modo_permiso, "
                    + "tipo_permiso.id as id_tipo_permiso, tipo_permiso.precio, "
                    + "tipo_permiso.is_active as tipo_permiso_is_active, "
                    + "tipo_permiso.tipo_permiso, clientes.id as id_clientes, "
                    + "clientes.nombre, clientes.apellido, clientes.direccion, "
                    + "clientes.razon_social, "
                    + "clientes.is_active as cliente_is_active, clientes.cedula "
                    + "FROM permisos , tipo_permiso, clientes "
                    + "WHERE permisos.id_tipo_permiso = tipo_permiso.id AND "
                    + "permisos.id_clientes = clientes.id  "
                    + "ORDER BY permisos.ID DESC LIMIT 1;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    permiso.setId(rs.getInt("id"));
                    permiso.setDescripcion(rs.getString("descripcion"));
                    permiso.setFecha_emision(rs.getString("fecha_emision"));
                    permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                    permiso.setRuta_pdf(rs.getString("ruta_pdf"));
                    permiso.setModo_permiso(rs.getString("modo_permiso"));
                    permiso.setNumero_deposito(rs.getString("numero_deposito"));
                    permiso.setFecha_documento(rs.getString("fecha_documento"));
                    permiso.setVehiculo_marca(rs.getString("vehiculo_marca"));
                    permiso.setExtintor(rs.getBoolean("extintor"));
                    permiso.setPlaca(rs.getString("placa"));
                    permiso.setCapacidad(rs.getString("capacidad"));

                    Clientes cliente = new Clientes();
                    cliente.setId(rs.getInt("id_clientes"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCedula(rs.getString("cedula"));
                    cliente.setRazon_social(rs.getString("razon_social"));
                    cliente.setDireccion(rs.getString("direccion"));
                    cliente.setIs_active(rs.getBoolean("cliente_is_active"));

                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id_tipo_permiso"));
                    tp.setIs_active(rs.getBoolean("tipo_permiso_is_active"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));

                    permiso.setCliente(cliente);
                    permiso.setPermiso(tp);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        generatePDF(permiso);
    }

    private void generatePDF(Permiso permiso) {
        File _file_ = null;
        try {
            String _directory_ = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
            String directoryName = String.format("%s/pdfs/%s/", _directory_, permiso.getCliente().getApellido());
            File directory = new File(directoryName);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            /**
             * ************* PERMISO **************
             *
             */

            _file_ = new File(String.format("%spermiso_%s.pdf", directoryName, permiso.getFullCode()));
            OutputStream file = new FileOutputStream(_file_);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image = null;

            document.setPageSize(PageSize.A4);
            document.setMargins(40, 40, 73, 10);
            document.newPage();
            switch (permiso.getModo_permiso()) {
                case "Transporte":
                    generateTransportePDF(document, writer, permiso);
                    break;
                case "Ocasional":
                    generateOcasionalPDF(document, writer, permiso);
                    break;
                case "Construcción":
                    generateConstruccionPDF(document, writer, permiso);
                    break;
                case "Funcionamiento":
                    generateFuncionamientoPDF(document, writer, permiso);
                    break;
                default:
                    generateTransportePDF(document, writer, permiso);
            }

            canvas = writer.getDirectContentUnder();
            switch (permiso.getModo_permiso()) {
                case "Transporte":
                    image = Image.getInstance(getClass().getClassLoader().getResource("img/permiso_transporte.png"));
                    break;
                case "Ocasional":
                    image = Image.getInstance(getClass().getClassLoader().getResource("img/permiso_ocasional.png"));
                    break;
                case "Construcción":
                    image = Image.getInstance(getClass().getClassLoader().getResource("img/permiso_construccion.png"));
                    break;
                case "Funcionamiento":
                    image = Image.getInstance(getClass().getClassLoader().getResource("img/permiso_funcionamiento.png"));
                    break;
                default:
                    image = Image.getInstance(getClass().getClassLoader().getResource("img/permiso_transporte.png"));
            }
            image.scaleAbsolute(PageSize.A4);
            image.setAbsolutePosition(0, 0);
            canvas.addImage(image);

            /**
             * ************* PERMISO COPIA **************
             *
             */
            switch (permiso.getModo_permiso()) {
                case "Transporte":
                    copiagenerateTransportePDF(document, writer, permiso);
                    break;
                case "Ocasional":
                    copiagenerateOcasionalPDF(document, writer, permiso);
                    break;
                case "Construcción":
                    copiagenerateConstruccionPDF(document, writer, permiso);
                    break;
                case "Funcionamiento":
                    copiagenerateFuncionamientoPDF(document, writer, permiso);
                    break;
                default:
                    copiagenerateTransportePDF(document, writer, permiso);
            }
            /**
             * ************* CIERRE **************
             *
             */
            document.close();

            /**
             * ************* GUARDAR EN BD **************
             *
             */
            _path_list_ = _file_.getAbsolutePath();
            file.close();
            System.out.println(_path_list_);
            MysqlConnect mysqlConnect = new MysqlConnect();
            try {
                int id = permiso.getId();
                String query = "UPDATE permisos SET ruta_pdf = ? WHERE id = ?;";
                PreparedStatement preparedStmt = mysqlConnect.connect().prepareStatement(query);
                preparedStmt.setString(1, _path_list_);
                preparedStmt.setInt(2, id);
                preparedStmt.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysqlConnect.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void generateTransportePDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullFechaEmision()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getCedula()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getDescripcion()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCapacidad()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(13f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getVehiculo_marca()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPlaca()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getExtintor() ? "Si" : "No"));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullFechaExpiracion()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copiagenerateTransportePDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(10f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullFechaEmision()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getCedula()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getDescripcion()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCapacidad()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(13f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getVehiculo_marca()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPlaca()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getExtintor() ? "Si" : "No"));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullFechaExpiracion()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateOcasionalPDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getAnoEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format(" %s", permiso.getFullFechaEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("00:00");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(18f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPrecioEnLetras()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPermiso().getTipo_permiso()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getDireccion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFecha_expiracion()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copiagenerateOcasionalPDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(6f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getAnoEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format(" %s", permiso.getFullFechaEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("00:00");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(18f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPrecioEnLetras()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPermiso().getTipo_permiso()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getDireccion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFecha_expiracion()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateConstruccionPDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getAnoEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format(" %s", permiso.getFullFechaEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("00:00");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(18f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPrecioEnLetras()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getDescripcion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getDireccion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFecha_expiracion()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copiagenerateConstruccionPDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(6f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getAnoEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format(" %s", permiso.getFullFechaEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("00:00");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(18f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFullName()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPrecioEnLetras()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getDescripcion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getDireccion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getFecha_expiracion()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateFuncionamientoPDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getAnoEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format(" %s", permiso.getFullFechaEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("00:00");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(18f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getCliente().getCedula());
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getCliente().getFullName());
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getCliente().getRazon_social());
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPermiso().getTipo_permiso()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(10f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getDireccion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("$0.00");

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("     $ %s", permiso.getPermiso().getPrecio()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void copiagenerateFuncionamientoPDF(Document document, PdfWriter writer, Permiso permiso) {
        try {
            Font font = new Font(Font.FontFamily.COURIER, 11, Font.NORMAL);
            Font boldFont = new Font(Font.FontFamily.COURIER, 11, Font.BOLD);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            _p1_.setFont(boldFont);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getFullCode());
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getAnoEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format(" %s", permiso.getFullFechaEmision()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("00:00");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(18f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getCliente().getCedula());
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getCliente().getFullName());
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(12f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(permiso.getCliente().getRazon_social());
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getPermiso().getTipo_permiso()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(10f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("%s", permiso.getCliente().getDireccion()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            p1.setSpacingBefore(8f);
            _p1_.clear();
            _p1_.setFont(font);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("$ %s", permiso.getPermiso().getPrecio()));

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add("$0.00");

            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(Chunk.TABBING);
            _p1_.add(String.format("     $ %s", permiso.getPermiso().getPrecio()));
            p1.add(_p1_);
            document.add(p1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // EDITAR PERMISOS GENERADOS
    @FXML
    private void editarGeneradosMenuAction(ActionEvent event) {
        setVisiblePane(false, false, false, false, false, false, false, false, true, false, false);
        tps = new ArrayList<>();
        permisos = new ArrayList<>();
        generado_parametro.setText("");
        ObservableList cursors = FXCollections.observableArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM tipo_permiso";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tps.add(tp);
                    cursors.add(tp.getTipo_permiso());
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        generado_tipo_permiso.getStylesheets().add(
                getClass().getResource(
                        "ComboBoxCSS.css"
                ).toExternalForm()
        );
        generado_search_permiso.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                List<String> _tps_ = generado_tipo_permiso.getItems();
                int _count_ = 0;
                for (String tp : _tps_) {
                    if (tp.toLowerCase().contains(newValue.toLowerCase())) {
                        generado_tipo_permiso.getSelectionModel().select(_count_);

                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) generado_tipo_permiso.getSkin();
                        ((ListView<?>) skin.getPopupContent()).scrollTo(_count_);
                        break;
                    }
                    _count_++;
                }
            } else {
                generado_tipo_permiso.getSelectionModel().select(0);
                ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) generado_tipo_permiso.getSkin();
                ((ListView<?>) skin.getPopupContent()).scrollTo(0);
            }
        });
        generado_tipo_permiso.setItems(cursors);
        generado_tipo_permiso.setTooltip(new Tooltip("Elija un tipo de permiso"));
        generado_tipo_permiso.getSelectionModel().clearSelection();
        generado_column_id.setCellValueFactory(new PropertyValueFactory<>("fullCode"));
        generado_column_permiso.setCellValueFactory(new PropertyValueFactory<>("permiso"));
        generado_column_fecha_emision.setCellValueFactory(new PropertyValueFactory<>("fecha_emision"));
        generado_column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        generado_column_cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        generado_column_editar.setCellValueFactory(new PropertyValueFactory<>("editar"));
        generado_column_eliminar.setCellValueFactory(new PropertyValueFactory<>("eliminar"));
        editar_generados_tv.getItems().setAll(permisos);
        editar_generados_tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (editar_generados_tv.getSelectionModel().getSelectedItem() != null) {
                    TableViewSelectionModel selectionModel = editar_generados_tv.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    Permiso _permiso_ = editar_generados_tv.getSelectionModel().getSelectedItem();
                    if (val.equals("Editar")) {
                        // setVisiblePane(false, false, false, false, false, false, false, false, false, true, false);
                        // setGeneradoEditarPane(_permiso_);
                    }

                    if (val.equals("Eliminar")) {
                        Alert alert = new Alert(AlertType.CONFIRMATION);
                        alert.setTitle("Cuerpo Bomberos de Balzar");
                        alert.setHeaderText(null);
                        alert.setContentText(String.format("Está seguro que desea eliminar el permiso #%s?", _permiso_.getFullCode()));
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get() == ButtonType.OK) {
                            editar_generados_tv.getSelectionModel().clearSelection();
                            eliminarGenerado(_permiso_, event);
                        } else {
                            alert.close();
                        }
                    }
                }
            }
        });
    }

    private void setGeneradoEditarPane(Permiso _permiso_) {
        generado_editar_cedula.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                generado_editar_cedula.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        generado_editar_cedula.setText(_permiso_.getCliente().getCedula());
        generado_editar_nombre.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (generado_editar_nombre.getText().length() >= Settings.LENGTH_NAMES) {
                    generado_editar_nombre.setText(generado_editar_nombre.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });
        generado_editar_nombre.setText(_permiso_.getCliente().getNombre());
        generado_editar_apellido.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (generado_editar_apellido.getText().length() >= Settings.LENGTH_NAMES) {
                    generado_editar_apellido.setText(generado_editar_apellido.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });
        generado_editar_apellido.setText(_permiso_.getCliente().getApellido());
        generado_editar_razon_social.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (generado_editar_razon_social.getText().length() >= Settings.LENGTH_NAMES) {
                    generado_editar_razon_social.setText(generado_editar_razon_social.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });
        generado_editar_razon_social.setText(_permiso_.getCliente().getRazon_social());
        generado_editar_direccion.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (generado_editar_direccion.getText().length() >= Settings.LENGTH_NAMES) {
                    generado_editar_direccion.setText(generado_editar_direccion.getText().substring(0, Settings.LENGTH_NAMES));
                }
            }
        });
        generado_editar_direccion.setText(_permiso_.getCliente().getDireccion());
        generado_editar_descripcion.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (generado_editar_descripcion.getText().length() >= Settings.LENGTH_DESCRIPTION) {
                    generado_editar_descripcion.setText(generado_editar_descripcion.getText().substring(0, Settings.LENGTH_DESCRIPTION));
                }
            }
        });
        generado_editar_descripcion.setText(_permiso_.getDescripcion());
        generado_editar_cedula.lengthProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
            if (newValue.intValue() > oldValue.intValue()) {
                if (generado_editar_cedula.getText().length() >= Settings.LENGTH_NATIONAL_ID) {
                    generado_editar_cedula.setText(generado_editar_cedula.getText().substring(0, Settings.LENGTH_NATIONAL_ID));
                }
            }
        });
        generado_editar_tipo_permiso.getStylesheets().add(
                getClass().getResource(
                        "ComboBoxCSS.css"
                ).toExternalForm()
        );
        generado_editar_search_permiso.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                List<String> _tps_ = generado_editar_tipo_permiso.getItems();
                int _count_ = 0;
                for (String tp : _tps_) {
                    if (tp.toLowerCase().contains(newValue.toLowerCase())) {
                        generado_editar_tipo_permiso.getSelectionModel().select(_count_);

                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) generado_editar_tipo_permiso.getSkin();
                        ((ListView<?>) skin.getPopupContent()).scrollTo(_count_);
                        break;
                    }
                    _count_++;
                }
            } else {
                generado_editar_tipo_permiso.getSelectionModel().select(0);
                ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) generado_editar_tipo_permiso.getSkin();
                ((ListView<?>) skin.getPopupContent()).scrollTo(0);
            }
        });
        tps = new ArrayList<>();
        ObservableList cursors = FXCollections.observableArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM tipo_permiso WHERE is_active = True;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tps.add(tp);
                    cursors.add(tp.getTipo_permiso());
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        generado_editar_tipo_permiso.setItems(cursors);
        generado_editar_tipo_permiso.getSelectionModel().selectFirst();
        generado_editar_tipo_permiso.setTooltip(new Tooltip("Elija un tipo de permiso"));
        generado_editar_fecha.setValue(LocalDate.now());

        generado_editar_modo_ocasional.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> obs, Boolean wasPreviouslySelected, Boolean isNowSelected) {
                if (isNowSelected) {
                    generado_editar_fecha_ocasional.setDisable(false);
                    generado_editar_fecha_ocasional.setValue(LocalDate.now());
                } else {
                    generado_editar_fecha_ocasional.setDisable(true);
                    generado_editar_fecha_ocasional.setValue(null);
                }
            }
        });
    }

    @FXML
    private void generarEditarPermiso(ActionEvent event) {

    }

    private void eliminarGenerado(Permiso _permiso_, ActionEvent event) {
        try {
            MysqlConnect mysqlConnect = new MysqlConnect();
            String query = "DELETE FROM permisos WHERE id = ?";
            PreparedStatement pstmt = null;
            pstmt = mysqlConnect.connect().prepareStatement(query);
            pstmt.setString(1, String.valueOf(_permiso_.getId()));
            pstmt.executeUpdate();
            permisos = new ArrayList<Permiso>();
            String sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id ORDER BY permisos.id;";
            try {
                ResultSet rs;
                try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        Permiso permiso = new Permiso();
                        permiso.setId(rs.getInt("id"));
                        permiso.setDescripcion(rs.getString("descripcion"));
                        permiso.setFecha_emision(rs.getString("fecha_emision"));
                        permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                        permiso.setRuta_pdf(rs.getString("ruta_pdf"));

                        Clientes cliente = new Clientes();
                        cliente.setId(rs.getInt("id_clientes"));
                        cliente.setNombre(rs.getString("nombre"));
                        cliente.setApellido(rs.getString("apellido"));
                        cliente.setCedula(rs.getString("cedula"));
                        cliente.setRazon_social(rs.getString("razon_social"));
                        cliente.setDireccion(rs.getString("direccion"));

                        Tipo_Permiso tp = new Tipo_Permiso();
                        tp.setId(rs.getInt("id_tipo_permiso"));
                        tp.setIs_active(rs.getBoolean("is_active"));
                        tp.setPrecio(rs.getDouble("precio"));
                        tp.setTipo_permiso(rs.getString("tipo_permiso"));

                        permiso.setCliente(cliente);
                        permiso.setPermiso(tp);

                        permisos.add(permiso);
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysqlConnect.disconnect();
            }
            editar_generados_tv.refresh();
            editar_generados_tv.getItems().clear();
            editar_generados_tv.getItems().addAll(permisos);
        } catch (SQLException | IndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void buscarGenerado(ActionEvent event) {
        String _parametro_ = generado_parametro.getText();
        String _ddl_ = "";
        String sql = "";
        if (!_parametro_.isEmpty() && generado_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id AND (nombre LIKE '%" + _parametro_ + "%' OR cedula LIKE '%" + _parametro_ + "%') ORDER BY permisos.id;";
        } else if (_parametro_.isEmpty() && generado_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
            _ddl_ = generado_tipo_permiso.getSelectionModel().getSelectedItem().toString();
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
        } else if (!_parametro_.isEmpty() && generado_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
            _ddl_ = generado_tipo_permiso.getSelectionModel().getSelectedItem().toString();
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id AND (nombre LIKE '%" + _parametro_ + "%' OR cedula LIKE '%" + _parametro_ + "%') AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
        } else if (_parametro_.isEmpty() && generado_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id ORDER BY permisos.id;";
        }
        permisos = new ArrayList<>();
        MysqlConnect mysqlConnect = new MysqlConnect();
        int _count_permiso_ = 0;
        try {
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Permiso permiso = new Permiso();
                    permiso.setId(rs.getInt("id"));
                    permiso.setDescripcion(rs.getString("descripcion"));
                    permiso.setFecha_emision(rs.getString("fecha_emision"));
                    permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                    permiso.setRuta_pdf(rs.getString("ruta_pdf"));

                    Clientes cliente = new Clientes();
                    cliente.setId(rs.getInt("id_clientes"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCedula(rs.getString("cedula"));
                    cliente.setRazon_social(rs.getString("razon_social"));
                    cliente.setDireccion(rs.getString("direccion"));

                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id_tipo_permiso"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));

                    permiso.setCliente(cliente);
                    permiso.setPermiso(tp);

                    permisos.add(permiso);
                    _count_permiso_++;
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        editar_generados_tv.refresh();
        editar_generados_tv.getItems().clear();
        editar_generados_tv.getItems().addAll(permisos);
        if (_count_permiso_ == 0) {
            showDialog("Error", "No se a encontrado resultados para la búsqueda solicitada", AlertType.CONFIRMATION);
        }
    }

    // VISUALIZAR PERMISOS
    @FXML
    private void consultarMenuAction(ActionEvent event) {
        setVisiblePane(false, true, false, false, false, false, false, false, false, false, false);
        tps = new ArrayList<>();
        permisos = new ArrayList<>();
        consultar_parametro.setText("");
        ObservableList cursors = FXCollections.observableArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM tipo_permiso";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tps.add(tp);
                    cursors.add(tp.getTipo_permiso());
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        consultar_tipo_permiso.getStylesheets().add(
                getClass().getResource(
                        "ComboBoxCSS.css"
                ).toExternalForm()
        );
        consultar_search_permiso.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                List<String> _tps_ = consultar_tipo_permiso.getItems();
                int _count_ = 0;
                for (String tp : _tps_) {
                    if (tp.toLowerCase().contains(newValue.toLowerCase())) {
                        consultar_tipo_permiso.getSelectionModel().select(_count_);

                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) consultar_tipo_permiso.getSkin();
                        ((ListView<?>) skin.getPopupContent()).scrollTo(_count_);
                        break;
                    }
                    _count_++;
                }
            } else {
                consultar_tipo_permiso.getSelectionModel().select(0);
                ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) consultar_tipo_permiso.getSkin();
                ((ListView<?>) skin.getPopupContent()).scrollTo(0);
            }
        });
        consultar_tipo_permiso.setItems(cursors);
        consultar_tipo_permiso.setTooltip(new Tooltip("Elija un tipo de permiso"));
        consultar_tipo_permiso.getSelectionModel().clearSelection();
        int _count_permiso_ = 0;
        // double _precio_permiso_ = 0;
        consultar_total_permiso.setText(String.format("%d", _count_permiso_));
        // s.setText(String.format("$%1$,.2f", _precio_permiso_));
        consultar_column_id.setCellValueFactory(new PropertyValueFactory<>("fullCode"));
        consultar_column_permiso.setCellValueFactory(new PropertyValueFactory<>("permiso"));
        consultar_column_fecha_emision.setCellValueFactory(new PropertyValueFactory<>("fecha_emision"));
        consultar_column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        consultar_column_cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        consultar_column_fecha_caducidad.setCellValueFactory(new PropertyValueFactory<>("fecha_expiracion"));
        consultar_column_visualizar.setCellValueFactory(new PropertyValueFactory<>("ver"));
        consultar_tv.getItems().setAll(permisos);
        consultar_tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (consultar_tv.getSelectionModel().getSelectedItem() != null) {
                    TableViewSelectionModel selectionModel = consultar_tv.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    if (val.equals("Ver")) {
                        Permiso permiso = consultar_tv.getSelectionModel().getSelectedItem();
                        goToPrintDialog(permiso.getRuta_pdf());
                    }
                }
            }
        });
        ObservableList _permisos_ = FXCollections.observableArrayList();
        _permisos_.add("Transporte");
        _permisos_.add("Ocasional");
        _permisos_.add("Construcción");
        _permisos_.add("Funcionamiento");
        consultar_modo_permiso.setItems(_permisos_);
    }

    @FXML
    private void buscarConsultar(ActionEvent event) {
        String _parametro_ = consultar_parametro.getText();
        String _ddl_ = "";
        String sql = "";
        if (!_parametro_.isEmpty() && consultar_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id AND (nombre LIKE '%" + _parametro_ + "%' OR cedula LIKE '%" + _parametro_ + "%') ORDER BY permisos.id;";
        } else if (_parametro_.isEmpty() && consultar_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
            _ddl_ = consultar_tipo_permiso.getSelectionModel().getSelectedItem().toString();
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
        } else if (!_parametro_.isEmpty() && consultar_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
            _ddl_ = consultar_tipo_permiso.getSelectionModel().getSelectedItem().toString();
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id AND (nombre LIKE '%" + _parametro_ + "%' OR cedula LIKE '%" + _parametro_ + "%') AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
        } else if (_parametro_.isEmpty() && consultar_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
            sql = "SELECT `permisos`.`id`, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `permisos`.`id_clientes`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`permisos`, tipo_permiso, clientes WHERE permisos.id_tipo_permiso = tipo_permiso.id AND permisos.id_clientes = clientes.id ORDER BY permisos.id;";
        }
        permisos = new ArrayList<>();
        MysqlConnect mysqlConnect = new MysqlConnect();
        int _count_permiso_ = 0;
        double _precio_permiso_ = 0;
        try {
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Permiso permiso = new Permiso();
                    permiso.setId(rs.getInt("id"));
                    permiso.setDescripcion(rs.getString("descripcion"));
                    permiso.setFecha_emision(rs.getString("fecha_emision"));
                    permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                    permiso.setRuta_pdf(rs.getString("ruta_pdf"));

                    Clientes cliente = new Clientes();
                    cliente.setId(rs.getInt("id_clientes"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCedula(rs.getString("cedula"));
                    cliente.setRazon_social(rs.getString("razon_social"));
                    cliente.setDireccion(rs.getString("direccion"));

                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id_tipo_permiso"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));

                    permiso.setCliente(cliente);
                    permiso.setPermiso(tp);

                    permisos.add(permiso);
                    _count_permiso_++;
                    _precio_permiso_ += permiso.getPermiso().getPrecio();
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        consultar_tv.refresh();
        consultar_tv.getItems().clear();
        consultar_tv.getItems().addAll(permisos);
        consultar_total_permiso.setText(String.format("%d", _count_permiso_));
        if (_count_permiso_ == 0) {
            showDialog("Error", "No se a encontrado resultados para la búsqueda solicitada", AlertType.CONFIRMATION);
        }
    }

    // EDITAR PERMISOS
    @FXML
    private void editarMenuAction(ActionEvent event) {
        tps = new ArrayList<>();
        setVisiblePane(false, false, true, false, false, false, false, false, false, false, false);
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM tipo_permiso;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tps.add(tp);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        editar_precio.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                editar_precio.setText(newValue.replaceAll("[^\\d+(\\.)?]", ""));
            }
        });
        editar_precio.setText("");
        editar_nombre.setText("");
        editar_is_active.setSelected(false);
        editar_column_id_permiso.setCellValueFactory(new PropertyValueFactory<>("id"));
        editar_column_nombre_permiso.setCellValueFactory(new PropertyValueFactory<>("tipo_permiso"));
        editar_column_precio_permiso.setCellValueFactory(new PropertyValueFactory<>("precio"));
        editar_column_activo_permiso.setCellValueFactory(new PropertyValueFactory<>("activado"));
        editar_tv.getItems().setAll(tps);
        editar_tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (editar_tv.getSelectionModel().getSelectedItem() != null) {
                    TableViewSelectionModel selectionModel = editar_tv.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    editar_nombre.setText(tps.get(tablePosition.getRow()).getTipo_permiso());
                    editar_precio.setText(tps.get(tablePosition.getRow()).getPrecio().toString());
                    editar_is_active.setSelected(tps.get(tablePosition.getRow()).isIs_active());
                    _id_edit_permiso = tps.get(tablePosition.getRow()).getId();
                    _id_edit_row_ = tablePosition.getRow();
                }
            }
        });
    }

    @FXML
    private void saveEditPermisos(ActionEvent event) {
        if (editar_nombre.getText().isEmpty() || editar_precio.getText().isEmpty()) {
            showDialog("Error", "Elija un permiso para editar", AlertType.ERROR);
        } else {
            MysqlConnect mysqlConnect = new MysqlConnect();
            try {
                String query = "UPDATE tipo_permiso SET tipo_permiso = ?, precio = ?, is_active = ? WHERE id = ?;";
                PreparedStatement preparedStmt = mysqlConnect.connect().prepareStatement(query);
                preparedStmt.setString(1, editar_nombre.getText());
                preparedStmt.setDouble(2, Double.parseDouble(editar_precio.getText()));
                preparedStmt.setBoolean(3, editar_is_active.isSelected());
                preparedStmt.setInt(4, _id_edit_permiso);
                preparedStmt.executeUpdate();
                Tipo_Permiso tp = new Tipo_Permiso();
                tp.setId(_id_edit_permiso);
                tp.setTipo_permiso(editar_nombre.getText());
                tp.setPrecio(Double.parseDouble(editar_precio.getText()));
                tp.setIs_active(editar_is_active.isSelected());
                tps.remove(_id_edit_row_);
                tps.add(tp);
                editar_nombre.setText("");
                editar_precio.setText("");
                editar_is_active.setSelected(false);
                _id_edit_permiso = _id_edit_row_ = 0;
                editar_tv.refresh();
                editar_tv.getItems().clear();
                editar_tv.getItems().addAll(tps);
                showDialog("Permiso Editado", "Permiso editado exitosamente", AlertType.INFORMATION);
            } catch (Exception e) {
                e.printStackTrace();
                showDialog("Error", "Ha ocurrido un error", AlertType.ERROR);
            } finally {
                mysqlConnect.disconnect();
            }
        }
    }

    // AGREGAR TIPO PERMISO
    @FXML
    private void agregarMenuAction(ActionEvent event) {
        setVisiblePane(false, false, false, false, false, true, false, false, false, false, false);
        agregar_permiso_nombre.setText("");
        agregar_permiso_precio.setText("");
        agregar_permiso_is_active.setSelected(false);
        agregar_permiso_precio.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.matches("\\d*")) {
                agregar_permiso_precio.setText(newValue.replaceAll("[^\\d+(\\.)?]", ""));
            }
        });
    }

    @FXML
    private void saveAgregarPermiso(ActionEvent event) {
        if (agregar_permiso_precio.getText().isEmpty() || agregar_permiso_nombre.getText().isEmpty()) {
            showDialog("Error", "Debe de llenar todos los datos para crear un nuevo tipo de permiso", AlertType.ERROR);
        } else {
            MysqlConnect mysqlConnect = new MysqlConnect();
            try {
                String query = "INSERT INTO tipo_permiso(tipo_permiso, precio, is_active)"
                        + " values (?,?,?)";
                PreparedStatement preparedStmt = mysqlConnect.connect().prepareStatement(query);
                preparedStmt.setString(1, agregar_permiso_nombre.getText());
                preparedStmt.setDouble(2, Double.parseDouble(agregar_permiso_precio.getText()));
                preparedStmt.setBoolean(3, agregar_permiso_is_active.isSelected());
                preparedStmt.execute();
                agregar_permiso_nombre.setText("");
                agregar_permiso_precio.setText("");
                agregar_permiso_is_active.setSelected(false);
                showDialog("Exitos", "Nuevo tipo permiso creado", AlertType.INFORMATION);
            } catch (Exception e) {
                e.printStackTrace();
                showDialog("Error", "Ha ocurrido un error", AlertType.ERROR);
            } finally {
                mysqlConnect.disconnect();
            }
        }
    }

    // DETALLE PERMISOS
    @FXML
    private void detalleMenuAction(ActionEvent event) {
        setVisiblePane(false, false, false, true, false, false, false, false, false, false, false);
        tps = new ArrayList<>();
        permisos = new ArrayList<>();
        ObservableList cursors = FXCollections.observableArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM tipo_permiso";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tps.add(tp);
                    cursors.add(tp.getTipo_permiso());
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        detalle_tipo_permiso.getStylesheets().add(
                getClass().getResource(
                        "ComboBoxCSS.css"
                ).toExternalForm()
        );
        detalle_search_permiso.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                List<String> _tps_ = detalle_tipo_permiso.getItems();
                int _count_ = 0;
                for (String tp : _tps_) {
                    if (tp.toLowerCase().contains(newValue.toLowerCase())) {
                        detalle_tipo_permiso.getSelectionModel().select(_count_);

                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) detalle_tipo_permiso.getSkin();
                        ((ListView<?>) skin.getPopupContent()).scrollTo(_count_);
                        break;
                    }
                    _count_++;
                }
            } else {
                detalle_tipo_permiso.getSelectionModel().select(0);
                ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) detalle_tipo_permiso.getSkin();
                ((ListView<?>) skin.getPopupContent()).scrollTo(0);
            }
        });
        detalle_tipo_permiso.setItems(cursors);
        detalle_tipo_permiso.setTooltip(new Tooltip("Elija un tipo de permiso"));
        detalle_tipo_permiso.getSelectionModel().clearSelection();
        int _count_permiso_ = 0;
        // double _precio_permiso_ = 0;
        detalle_total_permiso.setText(String.format("%d", _count_permiso_));
        // detalle_total_precio.setText(String.format("$%1$,.2f", _precio_permiso_));
        detalle_column_id.setCellValueFactory(new PropertyValueFactory<>("fullCode"));
        detalle_column_permiso.setCellValueFactory(new PropertyValueFactory<>("permiso"));
        detalle_column_fecha_emision.setCellValueFactory(new PropertyValueFactory<>("fecha_emision"));
        detalle_column_nombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        detalle_column_cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        detalle_column_fecha_caducidad.setCellValueFactory(new PropertyValueFactory<>("fecha_expiracion"));
        detalle_column_visualizar.setCellValueFactory(new PropertyValueFactory<>("ver"));
        detalle_tv.getItems().setAll(permisos);
        detalle_tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (detalle_tv.getSelectionModel().getSelectedItem() != null) {
                    TableViewSelectionModel selectionModel = detalle_tv.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    if (val.equals("Ver")) {
                        Permiso permiso = detalle_tv.getSelectionModel().getSelectedItem();
                        goToPrintDialog(permiso.getRuta_pdf());
                    }
                }
            }
        });
        detalle_date_picker_hasta.setValue(null);
        detalle_date_picker_desde.setValue(null);
        detalle_date_picker_hasta.setDisable(true);
        detalle_date_picker_hasta.setEditable(false);

        // detalle_date_picker_desde.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        try {
                            if (item.isBefore(detalle_date_picker_desde.getValue())) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        } catch (NullPointerException e) {
                        }
                    }
                };
            }
        };
        detalle_date_picker_hasta.setDayCellFactory(dayCellFactory);

        final Callback<DatePicker, DateCell> desdeCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        detalle_date_picker_hasta.setDisable(false);
                    }
                };
            }
        };
        detalle_date_picker_desde.setDayCellFactory(desdeCellFactory);
        ObservableList _permisos_ = FXCollections.observableArrayList();
        _permisos_.add("Transporte");
        _permisos_.add("Ocasional");
        _permisos_.add("Construcción");
        _permisos_.add("Funcionamiento");
        detalle_modo_permiso.setItems(_permisos_);
    }

    @FXML
    private void buscarDetalle(ActionEvent event) {
        String _desde_ = "";
        if (detalle_date_picker_desde.getValue() != null) {
            _desde_ = detalle_date_picker_desde.getValue().toString();
        }
        String _hasta_ = "";
        if (detalle_date_picker_hasta.getValue() != null) {
            _hasta_ = detalle_date_picker_hasta.getValue().toString();
        }
        if (!_desde_.isEmpty() && _hasta_.isEmpty()) {
            showDialog("Error", "Debe de elegir una fecha de finalización de búsqueda", AlertType.ERROR);
        } else {
            String _ddl_ = "";
            String sql = "";
            // RANGO TIEMPO
            if (!_desde_.isEmpty() && detalle_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND fecha_emision BETWEEN '" + _desde_ + "' AND '" + _hasta_ + "' ORDER BY permisos.id;";
                // TIPO PERMISO
            } else if (_desde_.isEmpty() && detalle_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
                _ddl_ = detalle_tipo_permiso.getSelectionModel().getSelectedItem().toString();
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
                // AMBOS
            } else if (!_desde_.isEmpty() && detalle_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
                _ddl_ = detalle_tipo_permiso.getSelectionModel().getSelectedItem().toString();
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND fecha_emision BETWEEN '" + _desde_ + "' AND '" + _hasta_ + "' AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
                // NINGUNO
            } else if (_desde_.isEmpty() && detalle_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id ORDER BY permisos.id;";
            }
            permisos = new ArrayList<>();
            MysqlConnect mysqlConnect = new MysqlConnect();
            int _count_permiso_ = 0;
            try {
                ResultSet rs;
                try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        Permiso permiso = new Permiso();
                        permiso.setId(rs.getInt("id"));
                        permiso.setDescripcion(rs.getString("descripcion"));
                        permiso.setFecha_emision(rs.getString("fecha_emision"));
                        permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                        permiso.setRuta_pdf(rs.getString("ruta_pdf"));

                        Clientes cliente = new Clientes();
                        cliente.setId(rs.getInt("clientes_id"));
                        cliente.setNombre(rs.getString("nombre"));
                        cliente.setApellido(rs.getString("apellido"));
                        cliente.setCedula(rs.getString("cedula"));
                        cliente.setRazon_social(rs.getString("razon_social"));
                        cliente.setDireccion(rs.getString("direccion"));

                        Tipo_Permiso tp = new Tipo_Permiso();
                        tp.setId(rs.getInt("id_tipo_permiso"));
                        tp.setIs_active(rs.getBoolean("is_active"));
                        tp.setPrecio(rs.getDouble("precio"));
                        tp.setTipo_permiso(rs.getString("tipo_permiso"));

                        permiso.setCliente(cliente);
                        permiso.setPermiso(tp);

                        permisos.add(permiso);
                        _count_permiso_++;
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysqlConnect.disconnect();
            }
            detalle_tv.refresh();
            detalle_tv.getItems().clear();
            detalle_tv.getItems().addAll(permisos);
            detalle_total_permiso.setText(String.format("%d", _count_permiso_));
            // detalle_total_precio.setText(String.format("$%1$,.2f", _precio_permiso_));
            if (_count_permiso_ == 0) {
                showDialog("Error", "No se a encontrado resultados para la búsqueda solicitada", AlertType.CONFIRMATION);
            }
        }
    }

    // ARQUEO PERMISOS
    @FXML
    private void arqueoMenuAction(ActionEvent event) {
        setVisiblePane(false, false, false, false, true, false, false, false, false, false, false);
        tps = new ArrayList<>();
        permisos = new ArrayList<>();
        ObservableList cursors = FXCollections.observableArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM tipo_permiso";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setIs_active(rs.getBoolean("is_active"));
                    tps.add(tp);
                    cursors.add(tp.getTipo_permiso());
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        arqueo_tipo_permiso.getStylesheets().add(
                getClass().getResource(
                        "ComboBoxCSS.css"
                ).toExternalForm()
        );
        arqueo_search_permiso.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (!newValue.equals("")) {
                List<String> _tps_ = arqueo_tipo_permiso.getItems();
                int _count_ = 0;
                for (String tp : _tps_) {
                    if (tp.toLowerCase().contains(newValue.toLowerCase())) {
                        arqueo_tipo_permiso.getSelectionModel().select(_count_);

                        ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) arqueo_tipo_permiso.getSkin();
                        ((ListView<?>) skin.getPopupContent()).scrollTo(_count_);
                        break;
                    }
                    _count_++;
                }
            } else {
                arqueo_tipo_permiso.getSelectionModel().select(0);
                ComboBoxListViewSkin<?> skin = (ComboBoxListViewSkin<?>) arqueo_tipo_permiso.getSkin();
                ((ListView<?>) skin.getPopupContent()).scrollTo(0);
            }
        });
        arqueo_tipo_permiso.setItems(cursors);
        arqueo_tipo_permiso.setTooltip(new Tooltip("Elija un tipo de permiso"));
        arqueo_tipo_permiso.getSelectionModel().clearSelection();
        int _count_permiso_ = 0;
        double _precio_permiso_ = 0;
        arqueo_total_permiso.setText(String.format("%d", _count_permiso_));
        arqueo_total_precio.setText(String.format("$%1$,.2f", _precio_permiso_));
        arqueo_column_id.setCellValueFactory(new PropertyValueFactory<>("fullCode"));
        arqueo_column_permiso.setCellValueFactory(new PropertyValueFactory<>("permiso"));
        arqueo_column_fecha_emision.setCellValueFactory(new PropertyValueFactory<>("fecha_emision"));
        arqueo_column_nombre.setCellValueFactory(new PropertyValueFactory<>("fullName"));
        arqueo_column_cedula.setCellValueFactory(new PropertyValueFactory<>("cedula"));
        arqueo_column_fecha_caducidad.setCellValueFactory(new PropertyValueFactory<>("fecha_expiracion"));
        arqueo_column_valor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        arqueo_column_visualizar.setCellValueFactory(new PropertyValueFactory<>("ver"));
        arqueo_tv.getItems().setAll(permisos);
        arqueo_tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (arqueo_tv.getSelectionModel().getSelectedItem() != null) {
                    TableViewSelectionModel selectionModel = arqueo_tv.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    if (val.equals("Ver")) {
                        Permiso permiso = arqueo_tv.getSelectionModel().getSelectedItem();
                        goToPrintDialog(permiso.getRuta_pdf());
                    }
                }
            }
        });
        arqueo_date_picker_hasta.setValue(null);
        arqueo_date_picker_desde.setValue(null);
        arqueo_date_picker_hasta.setDisable(true);
        arqueo_date_picker_hasta.setEditable(false);

        // detalle_date_picker_desde.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> dayCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        try {
                            if (item.isBefore(arqueo_date_picker_desde.getValue())) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        } catch (NullPointerException e) {
                        }
                    }
                };
            }
        };
        arqueo_date_picker_hasta.setDayCellFactory(dayCellFactory);

        final Callback<DatePicker, DateCell> desdeCellFactory
                = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        arqueo_date_picker_hasta.setDisable(false);
                    }
                };
            }
        };
        arqueo_date_picker_desde.setDayCellFactory(desdeCellFactory);
        ObservableList _permisos_ = FXCollections.observableArrayList();
        _permisos_.add("Transporte");
        _permisos_.add("Ocasional");
        _permisos_.add("Construcción");
        _permisos_.add("Funcionamiento");
        arqueo_modo_permiso.setItems(_permisos_);
    }

    @FXML
    private void arqueoDescargar(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cuerpo Bomberos de Balzar");
        alert.setHeaderText(null);
        alert.setContentText("Espere unos momentos....");
        alert.show();

        String _directory_ = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String directoryName = String.format("%s/pdfs/", _directory_);
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File _file_ = new File(String.format("%sarqueo_caja.pdf", directoryName));
        try {
            Font boldRedFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.RED);
            Font boldBlackFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 10, Font.NORMAL, BaseColor.BLACK);

            OutputStream file = new FileOutputStream(_file_);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image;
            document.setPageSize(PageSize.A4);
            document.setMargins(80, 80, 80, 100);
            document.open();

            image = Image.getInstance(getClass().getClassLoader().getResource("img/cbc_logo_small_transparency.png"));
            image.setAbsolutePosition(250, 770);
            canvas.addImage(image);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            p1.clear();
            _p1_.clear();
            _p1_.setFont(boldRedFont);
            p1.setAlignment(Element.ALIGN_CENTER);
            _p1_.add("INFORME ARQUEO DE CAJA CUERPO BOMBERO DE BALZAR");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            SimpleDateFormat sdf = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            Calendar c = Calendar.getInstance();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Fecha emisión: %s", sdf.format(c.getTime())));
            p1.add(_p1_);
            document.add(p1);

            PdfPTable table = new PdfPTable(6);
            table.setTotalWidth(450);
            table.setLockedWidth(true);

            PdfPCell cell = new PdfPCell(new Phrase("Código"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cédula"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Emisión"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Permiso"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Valor"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);
            arqueo_tv.getItems().forEach((permiso) -> {
                Phrase p = new Phrase(permiso.getFullCode(), smallFont);
                PdfPCell cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getFullName(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getCliente().getCedula(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getFecha_emision(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getPermiso().getTipo_permiso(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(String.format("$%s", permiso.getPermiso().getPrecio()), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);
            });

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total Permisos: %s", arqueo_total_permiso.getText()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total Precio: %s", arqueo_total_precio.getText()));
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();

            goToPrintDialog(_file_.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void buscarArqueo(ActionEvent event) {
        String _desde_ = "";
        if (arqueo_date_picker_desde.getValue() != null) {
            _desde_ = arqueo_date_picker_desde.getValue().toString();
        }
        String _hasta_ = "";
        if (arqueo_date_picker_hasta.getValue() != null) {
            _hasta_ = arqueo_date_picker_hasta.getValue().toString();
        }
        if (!_desde_.isEmpty() && _hasta_.isEmpty()) {
            showDialog("Error", "Debe de elegir una fecha de finalización de búsqueda", AlertType.ERROR);
        } else {
            String _ddl_ = "";
            String sql = "";
            // RANGO TIEMPO
            if (!_desde_.isEmpty() && arqueo_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND fecha_emision BETWEEN '" + _desde_ + "' AND '" + _hasta_ + "' ORDER BY permisos.id;";
                // TIPO PERMISO
            } else if (_desde_.isEmpty() && arqueo_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
                _ddl_ = arqueo_tipo_permiso.getSelectionModel().getSelectedItem().toString();
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
                // AMBOS
            } else if (!_desde_.isEmpty() && arqueo_tipo_permiso.getSelectionModel().getSelectedItem() != null) {
                _ddl_ = arqueo_tipo_permiso.getSelectionModel().getSelectedItem().toString();
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND fecha_emision BETWEEN '" + _desde_ + "' AND '" + _hasta_ + "' AND tipo_permiso.tipo_permiso LIKE '%" + _ddl_ + "%' ORDER BY permisos.id;";
                // NINGUNO
            } else if (_desde_.isEmpty() && arqueo_tipo_permiso.getSelectionModel().getSelectedItem() == null) {
                sql = "SELECT `permisos`.`id`, `clientes`.`id` as clientes_id, `clientes`.`nombre`, `clientes`.`apellido`, `clientes`.`cedula`, `clientes`.`razon_social`, `clientes`.`direccion`, `permisos`.`descripcion`, `permisos`.`fecha_emision`, `permisos`.`fecha_expiracion`, `permisos`.`ruta_pdf`, `permisos`.`id_tipo_permiso`, `tipo_permiso`.precio, `tipo_permiso`.tipo_permiso, `tipo_permiso`.is_active FROM `cbb_db`.`clientes`, `cbb_db`.`permisos`, tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id ORDER BY permisos.id;";
            }
            permisos = new ArrayList<>();
            MysqlConnect mysqlConnect = new MysqlConnect();
            int _count_permiso_ = 0;
            double _precio_permiso_ = 0;
            try {
                ResultSet rs;
                try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        Permiso permiso = new Permiso();
                        permiso.setId(rs.getInt("id"));
                        permiso.setDescripcion(rs.getString("descripcion"));
                        permiso.setFecha_emision(rs.getString("fecha_emision"));
                        permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                        permiso.setRuta_pdf(rs.getString("ruta_pdf"));

                        Clientes cliente = new Clientes();
                        cliente.setId(rs.getInt("clientes_id"));
                        cliente.setNombre(rs.getString("nombre"));
                        cliente.setApellido(rs.getString("apellido"));
                        cliente.setCedula(rs.getString("cedula"));
                        cliente.setRazon_social(rs.getString("razon_social"));
                        cliente.setDireccion(rs.getString("direccion"));

                        Tipo_Permiso tp = new Tipo_Permiso();
                        tp.setId(rs.getInt("id_tipo_permiso"));
                        tp.setIs_active(rs.getBoolean("is_active"));
                        tp.setPrecio(rs.getDouble("precio"));
                        tp.setTipo_permiso(rs.getString("tipo_permiso"));

                        permiso.setCliente(cliente);
                        permiso.setPermiso(tp);

                        permisos.add(permiso);
                        _count_permiso_++;
                        _precio_permiso_ += permiso.getPermiso().getPrecio();
                    }
                }
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysqlConnect.disconnect();
            }
            arqueo_tv.refresh();
            arqueo_tv.getItems().clear();
            arqueo_tv.getItems().addAll(permisos);
            arqueo_total_permiso.setText(String.format("%d", _count_permiso_));
            arqueo_total_precio.setText(String.format("$%1$,.2f", _precio_permiso_));
            if (_count_permiso_ == 0) {
                showDialog("Error", "No se a encontrado resultados para la búsqueda solicitada", AlertType.CONFIRMATION);
            }
        }
    }

    // REPORTES
    
    @FXML
    private void generarPDFConstruccion(ActionEvent event) {
        generate_Construccion_Funcionamiento("Construcción");
    }
    
    @FXML
    private void generarPDFFuncionamiento(ActionEvent event) {
        generate_Construccion_Funcionamiento("Funcionamiento");
    }
    
    @FXML
    private void generarPDFOcasional(ActionEvent event) {
        generate_Ocasional_Transporte("Ocasional");
    }
    
    @FXML
    private void generarPDFTransporte(ActionEvent event) {
        generate_Ocasional_Transporte("Transporte");
    }
    
    private void generate_Construccion_Funcionamiento(String titulo) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cuerpo Bomberos de Balzar");
        alert.setHeaderText(null);
        alert.setContentText("Espere unos momentos....");
        alert.show();
        double precio = 0;
        ArrayList<Permiso> permisosList = new ArrayList<>();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT permisos.id, permisos.descripcion, permisos.fecha_emision, permisos.fecha_expiracion, permisos.ruta_pdf, permisos.id_usuario, permisos.id_tipo_permiso, permisos.id_clientes, clientes.cedula, clientes.nombre, clientes.apellido, clientes.direccion, clientes.razon_social, clientes.is_active, tipo_permiso, precio, tipo_permiso.is_active as permiso_active FROM permisos , tipo_permiso, clientes WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND permisos.modo_permiso = '"+titulo+"' ORDER BY permisos.ID;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Permiso permiso = new Permiso();
                    permiso.setId(rs.getInt("id"));
                    permiso.setDescripcion(rs.getString("descripcion"));
                    permiso.setFecha_emision(rs.getString("fecha_emision"));
                    permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                    permiso.setRuta_pdf(rs.getString("ruta_pdf"));

                    Clientes cliente = new Clientes();
                    cliente.setId(rs.getInt("id_clientes"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCedula(rs.getString("cedula"));
                    cliente.setRazon_social(rs.getString("razon_social"));
                    cliente.setDireccion(rs.getString("direccion"));

                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id_tipo_permiso"));
                    tp.setIs_active(rs.getBoolean("permiso_active"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));

                    permiso.setCliente(cliente);
                    permiso.setPermiso(tp);

                    precio = precio + tp.getPrecio();
                    permisosList.add(permiso);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        String _directory_ = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String directoryName = String.format("%s/pdfs/", _directory_);
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File _file_ = new File(String.format("%sinforme_permisos_%s.pdf", directoryName, titulo.toLowerCase()));
        try {
            Font boldRedFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.RED);
            Font boldBlackFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
            Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);

            OutputStream file = new FileOutputStream(_file_);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image;
            document.setPageSize(PageSize.A4);
            document.setMargins(80, 80, 80, 100);
            document.open();

            image = Image.getInstance(getClass().getClassLoader().getResource("img/cbc_logo_small_transparency.png"));
            image.setAbsolutePosition(250, 770);
            canvas.addImage(image);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            p1.clear();
            _p1_.clear();
            _p1_.setFont(boldRedFont);
            p1.setAlignment(Element.ALIGN_CENTER);
            _p1_.add(String.format("INFORME PERMISOS %s - CUERPO BOMBERO DE BALZAR", titulo.toUpperCase(Locale.US)));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            SimpleDateFormat sdf = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            Calendar c = Calendar.getInstance();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Fecha emisión: %s", sdf.format(c.getTime())));
            p1.add(_p1_);
            document.add(p1);

            PdfPTable table = new PdfPTable(6);
            table.setTotalWidth(450);
            table.setLockedWidth(true);

            PdfPCell cell = new PdfPCell(new Phrase("Código"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cédula/RUC"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Razón Social"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Permiso"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Emisión"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            for (Permiso permiso : permisosList) {
                Phrase p = new Phrase(permiso.getFullCode(), smallFont);
                PdfPCell cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getCliente().getNombre(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getCliente().getCedula(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getCliente().getRazon_social(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getPermiso().toSimple(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getFecha_emision(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);
            }

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total: %s", permisosList.size()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total USD: %s$", precio));
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();

            goToPrintDialog(_file_.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generate_Ocasional_Transporte(String titulo) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cuerpo Bomberos de Balzar");
        alert.setHeaderText(null);
        alert.setContentText("Espere unos momentos....");
        alert.show();
        double precio = 0;
        ArrayList<Permiso> permisosList = new ArrayList<>();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT permisos.id, permisos.descripcion, permisos.fecha_emision, permisos.fecha_expiracion, permisos.ruta_pdf, permisos.id_usuario, permisos.id_tipo_permiso, permisos.id_clientes, clientes.cedula, clientes.nombre, clientes.apellido, clientes.direccion, clientes.razon_social, clientes.is_active, tipo_permiso, precio, tipo_permiso.is_active as permiso_active FROM permisos , tipo_permiso, clientes WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id AND permisos.modo_permiso = '"+titulo+"' ORDER BY permisos.ID;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Permiso permiso = new Permiso();
                    permiso.setId(rs.getInt("id"));
                    permiso.setDescripcion(rs.getString("descripcion"));
                    permiso.setFecha_emision(rs.getString("fecha_emision"));
                    permiso.setFecha_expiracion(rs.getString("fecha_expiracion"));
                    permiso.setRuta_pdf(rs.getString("ruta_pdf"));

                    Clientes cliente = new Clientes();
                    cliente.setId(rs.getInt("id_clientes"));
                    cliente.setNombre(rs.getString("nombre"));
                    cliente.setApellido(rs.getString("apellido"));
                    cliente.setCedula(rs.getString("cedula"));
                    cliente.setRazon_social(rs.getString("razon_social"));
                    cliente.setDireccion(rs.getString("direccion"));

                    Tipo_Permiso tp = new Tipo_Permiso();
                    tp.setId(rs.getInt("id_tipo_permiso"));
                    tp.setIs_active(rs.getBoolean("permiso_active"));
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setTipo_permiso(rs.getString("tipo_permiso"));

                    permiso.setCliente(cliente);
                    permiso.setPermiso(tp);
                    precio = precio + tp.getPrecio();
                    permisosList.add(permiso);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        String _directory_ = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String directoryName = String.format("%s/pdfs/", _directory_);
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File _file_ = new File(String.format("%sinforme_permisos_%s.pdf", directoryName, titulo.toLowerCase()));
        try {
            Font boldRedFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.RED);
            Font boldBlackFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
            Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);

            OutputStream file = new FileOutputStream(_file_);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image;
            document.setPageSize(PageSize.A4);
            document.setMargins(80, 80, 80, 100);
            document.open();

            image = Image.getInstance(getClass().getClassLoader().getResource("img/cbc_logo_small_transparency.png"));
            image.setAbsolutePosition(250, 770);
            canvas.addImage(image);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            p1.clear();
            _p1_.clear();
            _p1_.setFont(boldRedFont);
            p1.setAlignment(Element.ALIGN_CENTER);
            _p1_.add(String.format("INFORME PERMISOS %s - CUERPO BOMBERO DE BALZAR", titulo.toUpperCase()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            SimpleDateFormat sdf = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            Calendar c = Calendar.getInstance();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Fecha emisión: %s", sdf.format(c.getTime())));
            p1.add(_p1_);
            document.add(p1);

            PdfPTable table = new PdfPTable(7);
            table.setTotalWidth(450);
            table.setLockedWidth(true);

            PdfPCell cell = new PdfPCell(new Phrase("Código"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cédula/RUC"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Razón Social"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Permiso"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Emisión"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Caducidad"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            for (Permiso permiso : permisosList) {
                Phrase p = new Phrase(permiso.getFullCode(), smallFont);
                PdfPCell cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getCliente().getNombre(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getCliente().getCedula(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getCliente().getRazon_social(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getPermiso().toSimple(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getFecha_emision(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(permiso.getFecha_expiracion(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);
            }

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total: %s", permisosList.size()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total USD: %s$", precio));
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();

            goToPrintDialog(_file_.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void generarPDFempresa(ActionEvent event) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cuerpo Bomberos de Balzar");
        alert.setHeaderText(null);
        alert.setContentText("Espere unos momentos....");
        alert.show();
        double precio = 0;
        ArrayList<Cantidad> cantidads = new ArrayList();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT tipo_permiso.tipo_permiso, tipo_permiso.precio, nombre, apellido, cedula, razon_social, fecha_emision, fecha_expiracion, permisos.id FROM cbb_db.permisos, cbb_db.clientes, cbb_db.tipo_permiso WHERE clientes.id = permisos.id_clientes AND permisos.id_tipo_permiso = tipo_permiso.id ORDER BY cedula, razon_social;";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Cantidad tp = new Cantidad();
                    tp.setPrecio(rs.getDouble("precio"));
                    tp.setPermiso(rs.getString("tipo_permiso"));
                    tp.setNombre(String.format("%s %s", rs.getString("nombre"), rs.getString("apellido")));
                    tp.setCedula(rs.getString("cedula"));
                    tp.setRazonsocial(rs.getString("razon_social"));
                    tp.setFechaemision(rs.getString("fecha_emision"));
                    tp.setFechaexpiracion(rs.getString("fecha_expiracion"));
                    tp.setId(rs.getInt("id"));
                    precio = precio + tp.getPrecio();
                    cantidads.add(tp);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        String _directory_ = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory().toString();
        String directoryName = String.format("%s/pdfs/", _directory_);
        File directory = new File(directoryName);
        if (!directory.exists()) {
            directory.mkdirs();
        }
        File _file_ = new File(String.format("%sinforme_usuarios.pdf", directoryName));
        try {
            Font boldRedFont = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.BOLD, BaseColor.RED);
            Font boldBlackFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
            Font normalFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.NORMAL, BaseColor.BLACK);
            Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.NORMAL, BaseColor.BLACK);

            OutputStream file = new FileOutputStream(_file_);
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, file);
            document.open();
            PdfContentByte canvas = writer.getDirectContentUnder();
            Image image;
            document.setPageSize(PageSize.A4);
            document.setMargins(80, 80, 80, 100);
            document.open();

            image = Image.getInstance(getClass().getClassLoader().getResource("img/cbc_logo_small_transparency.png"));
            image.setAbsolutePosition(250, 770);
            canvas.addImage(image);

            Phrase _p1_ = new Phrase();
            Paragraph p1 = new Paragraph();

            p1.clear();
            _p1_.clear();
            _p1_.setFont(boldRedFont);
            p1.setAlignment(Element.ALIGN_CENTER);
            _p1_.add("INFORME USUARIOS CUERPO BOMBERO DE BALZAR");
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            SimpleDateFormat sdf = new SimpleDateFormat("d, MMMM yyyy", new Locale("es", "ES"));
            Calendar c = Calendar.getInstance();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Fecha emisión: %s", sdf.format(c.getTime())));
            p1.add(_p1_);
            document.add(p1);

            PdfPTable table = new PdfPTable(6);
            table.setTotalWidth(450);
            table.setLockedWidth(true);

            PdfPCell cell = new PdfPCell(new Phrase("Código"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Nombre"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Cédula/RUC"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Razón Social"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Permiso"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase("Fecha Emisión"));
            cell.setHorizontalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);
            cantidads.stream().map((cantidad) -> {
                Phrase p = new Phrase(cantidad.getFullCode(), smallFont);
                PdfPCell cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(cantidad.getNombre(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(cantidad.getCedula(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(cantidad.getRazonsocial(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(cantidad.getPermiso(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cel);

                p = new Phrase(cantidad.getFechaemision(), smallFont);
                cel = new PdfPCell(p);
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                // table.addCell(cel);
                return cel;
            }).map((cel) -> {
                cel.setHorizontalAlignment(Element.ALIGN_MIDDLE);
                return cel;
            }).forEachOrdered((cel) -> {
                table.addCell(cel);
            });

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total: %s", cantidads.size()));
            p1.add(_p1_);
            document.add(p1);

            p1.clear();
            _p1_.clear();
            _p1_.setFont(normalFont);
            p1.setAlignment(Element.ALIGN_LEFT);
            _p1_.add(String.format("Total USD: %s$", precio));
            p1.add(_p1_);
            document.add(p1);

            document.add(Chunk.NEWLINE);
            document.add(table);

            document.close();

            goToPrintDialog(_file_.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CERRAR SESION
    @FXML
    private void cerrarSesionMenuAction(ActionEvent event) {
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            Statement st = (Statement) mysqlConnect.connect().createStatement();
            st.execute("DELETE FROM config");
            st.close();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.getIcons().add(new javafx.scene.image.Image(CBB_Reportes.class.getResourceAsStream("/img/cbc_logo.png")));
            stage.setTitle("CBB - Permisos");
            stage.setScene(new Scene(root1));
            stage.show();
            ap_main_page.getScene().getWindow().hide();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            mysqlConnect.disconnect();
        }
    }

    private void setVisiblePane(
            boolean b1, boolean b2,
            boolean b3, boolean b4,
            boolean b5, boolean b6,
            boolean b7, boolean b8,
            boolean b9, boolean b10,
            boolean b11
    ) {
        pane_emision_permiso.setVisible(b1);
        pane_consultar_permiso.setVisible(b2);
        pane_editar_permiso.setVisible(b3);
        pane_detalle_permiso.setVisible(b4);
        pane_arqueo_caja.setVisible(b5);
        pane_agregar_permiso.setVisible(b6);
        pane_lista_usuarios.setVisible(b7);
        pane_agregar_usuario.setVisible(b8);
        pane_generado_permiso.setVisible(b9);
        pane_generado_editar_permiso.setVisible(b10);
        pane_liquidacion.setVisible(b11);
    }

    private void showDialog(String titulo, String text, AlertType alert_type) {
        // Alert alert = new Alert(alert_type);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Cuerpo Bomberos de Balzar");
        // alert.setHeaderText("Cuerpo Bomberos de Balzar");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    private BorderPane ap_main_page;

    /**
     * Initializes the controller class.
     */
    public MainPageController() {
        tps = new ArrayList<>();
        permisos = new ArrayList<>();
        usuario = new Usuario();
        MysqlConnect mysqlConnect = new MysqlConnect();
        String sql = "SELECT * FROM config;";
        try {
            Statement st = (Statement) mysqlConnect.connect().createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                if (rs.getBoolean("is_logged")) {
                    sql = "SELECT * FROM usuarios WHERE id=" + rs.getInt("user_id") + ";";
                    try {
                        st = (Statement) mysqlConnect.connect().createStatement();
                        rs = st.executeQuery(sql);
                        while (rs.next()) {
                            boolean is_active = rs.getBoolean("is_active");
                            if (is_active) {
                                usuario.setId(rs.getInt("id"));
                                usuario.setFirst_name(rs.getString("nombre"));
                                usuario.setLast_name(rs.getString("apellido"));
                                usuario.setUsuario(rs.getString("usuario"));
                                usuario.setIs_active(rs.getBoolean("is_active"));
                                usuario.setIs_superuser(rs.getBoolean("is_superuser"));
                            }
                        }
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
            st.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Locale.setDefault(new Locale("es", "ES"));
        if (usuario.getIs_superuser()) {
            usuariosMenu.setDisable(false);
            add_permiso.setDisable(false);
            edit_permiso.setDisable(false);
            edit_permiso_generado.setDisable(true);
        } else {
            usuariosMenu.setDisable(true);
            add_permiso.setDisable(true);
            edit_permiso.setDisable(true);
            edit_permiso_generado.setDisable(true);
        }
    }

    // LISTA Y EDITAR USUARIOS
    @FXML
    private void listaMenuUsuarios(ActionEvent event) {
        setVisiblePane(false, false, false, false, false, false, true, false, false, false, false);

        usuarioList = new ArrayList<>();
        MysqlConnect mysqlConnect = new MysqlConnect();
        try {
            String sql = "SELECT * FROM usuarios";
            ResultSet rs;
            try (Statement st = (Statement) mysqlConnect.connect().createStatement()) {
                rs = st.executeQuery(sql);
                while (rs.next()) {
                    Usuario usuario = new Usuario();
                    usuario.setId(rs.getInt("id"));
                    usuario.setFirst_name(rs.getString("nombre"));
                    usuario.setLast_name(rs.getString("apellido"));
                    usuario.setUsuario(rs.getString("usuario"));
                    usuario.setPassword(rs.getString("contrasena"));
                    usuario.setIs_active(rs.getBoolean("is_active"));
                    usuario.setIs_superuser(rs.getBoolean("is_superuser"));
                    usuarioList.add(usuario);
                }
            }
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            mysqlConnect.disconnect();
        }
        usuario_column_nombre.setCellValueFactory(new PropertyValueFactory<>("first_name"));
        usuario_column_apellido.setCellValueFactory(new PropertyValueFactory<>("last_name"));
        usuario_column_usuario.setCellValueFactory(new PropertyValueFactory<>("usuario"));
        usuario_column_contrasena.setCellValueFactory(new PropertyValueFactory<>("password"));
        usuario_column_activo.setCellValueFactory(new PropertyValueFactory<>("activado"));
        usuario_column_is_superuser.setCellValueFactory(new PropertyValueFactory<>("admin"));

        usuario_tv.getItems().setAll(usuarioList);
        usuario_tv.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
                if (usuario_tv.getSelectionModel().getSelectedItem() != null) {
                    TableViewSelectionModel selectionModel = usuario_tv.getSelectionModel();
                    ObservableList selectedCells = selectionModel.getSelectedCells();
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    Object val = tablePosition.getTableColumn().getCellData(newValue);
                    Usuario usuario = usuario_tv.getSelectionModel().getSelectedItem();
                    usuario_nombre.setText(usuario.getFirst_name());
                    usuario_apellido.setText(usuario.getLast_name());
                    usuario_usuario.setText(usuario.getUsuario());
                    usuario_contrasena.setText(usuario.getPassword());
                    if (usuario.getIs_active()) {
                        usuario_active.setSelected(true);
                    } else {
                        usuario_active.setSelected(false);
                    }
                    if (usuario.getIs_superuser()) {
                        usuario_is_superuser.setSelected(true);
                    } else {
                        usuario_is_superuser.setSelected(false);
                    }
                    user_id = usuario.getId();
                }
            }
        });
    }

    private boolean user_empty() {
        return usuario_nombre.getText().isEmpty()
                || usuario_apellido.getText().isEmpty()
                || usuario_usuario.getText().isEmpty()
                || usuario_contrasena.getText().isEmpty();
    }

    @FXML
    private void editarUsuarioEvent(ActionEvent event) {
        if (user_empty()) {
            showDialog("Error", "Debe de llenar todos los datos para editar el usuario", AlertType.ERROR);
        } else {
            Usuario usuario = new Usuario();
            usuario.setFirst_name(usuario_nombre.getText());
            usuario.setLast_name(usuario_apellido.getText());
            usuario.setUsuario(usuario_usuario.getText());
            usuario.setPassword(usuario_contrasena.getText());
            usuario.setIs_active(usuario_active.isSelected());
            usuario.setIs_superuser(usuario_is_superuser.isSelected());
            usuario.setId(user_id);
            MysqlConnect mysqlConnect = new MysqlConnect();
            try {
                String query = "UPDATE usuarios SET nombre = ?, apellido = ?, usuario = ?, contrasena = ?, is_active = ?, is_superuser = ? WHERE id = ?;";
                PreparedStatement preparedStmt = mysqlConnect.connect().prepareStatement(query);
                preparedStmt.setString(1, usuario.getFirst_name());
                preparedStmt.setString(2, usuario.getLast_name());
                preparedStmt.setString(3, usuario.getUsuario());
                preparedStmt.setString(4, usuario.getPassword());
                preparedStmt.setBoolean(5, usuario.getIs_active());
                preparedStmt.setBoolean(6, usuario.getIs_superuser());
                preparedStmt.setInt(7, usuario.getId());
                preparedStmt.executeUpdate();

                for (Usuario u : usuarioList) {
                    if (u.getId() == usuario.getId()) {
                        usuarioList.remove(u);
                        break;
                    }
                }
                usuarioList.add(usuario);

                usuario_nombre.setText("");
                usuario_apellido.setText("");
                usuario_usuario.setText("");
                usuario_contrasena.setText("");
                usuario_active.setSelected(false);
                usuario_is_superuser.setSelected(false);

                usuario_tv.refresh();
                usuario_tv.getItems().clear();
                usuario_tv.getItems().addAll(usuarioList);
                showDialog("Usuario Editado", "Usuario editado exitosamente", AlertType.INFORMATION);
            } catch (Exception e) {
                e.printStackTrace();
                showDialog("Error", "Ha ocurrido un error", AlertType.ERROR);
            } finally {
                mysqlConnect.disconnect();
            }
        }
    }

    // AGREGAR USUARIOS
    @FXML
    private void agregarMenuUsuarios(ActionEvent event) {
        setVisiblePane(false, false, false, false, false, false, false, true, false, false, false);
    }

    private boolean user_agregar_empty() {
        return usuario_agregar_nombre.getText().isEmpty()
                || usuario_agregar_apellido.getText().isEmpty()
                || usuario_agregar_usuario.getText().isEmpty()
                || usuario_agregar_contrasena.getText().isEmpty();
    }

    @FXML
    private void agregarUsuarioEvent(ActionEvent event) {
        if (user_agregar_empty()) {
            showDialog("Error", "Debe de llenar todos los datos", AlertType.ERROR);
        } else {
            MysqlConnect mysqlConnect = new MysqlConnect();
            try {
                String query = "INSERT INTO usuarios(nombre, apellido, usuario, contrasena,"
                        + "is_active, is_superuser)"
                        + " values (?,?,?,?,?,?)";
                PreparedStatement preparedStmt = mysqlConnect.connect().prepareStatement(query);

                preparedStmt.setString(1, usuario_agregar_nombre.getText());
                preparedStmt.setString(2, usuario_agregar_apellido.getText());
                preparedStmt.setString(3, usuario_agregar_usuario.getText());
                preparedStmt.setString(4, usuario_agregar_contrasena.getText());
                preparedStmt.setBoolean(5, usuario_agregar_active.isSelected());
                preparedStmt.setBoolean(6, usuario_agregar_is_superuser.isSelected());
                preparedStmt.execute();

                usuario_agregar_nombre.setText("");
                usuario_agregar_apellido.setText("");
                usuario_agregar_usuario.setText("");
                usuario_agregar_contrasena.setText("");
                usuario_agregar_active.setSelected(false);
                usuario_agregar_is_superuser.setSelected(false);
                showDialog("Exitos", "Nuevo usuario creado", AlertType.INFORMATION);
            } catch (Exception e) {
                e.printStackTrace();
                showDialog("Error", "Ha ocurrido un error", AlertType.ERROR);
            } finally {
                mysqlConnect.disconnect();
            }
        }
    }

    private void goToPrintDialog(String path) {
        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = null;
                if (path.contains("::")) {
                    String split[] = path.split("::");
                    myFile = new File(split[1]);
                } else {
                    myFile = new File(path);
                }
                Desktop.getDesktop().open(myFile);
            } catch (IOException | IllegalArgumentException | NullPointerException ex) {
                showDialog("Error", "No se ha encontrado el archivo seleccionado", AlertType.ERROR);
            }
        }
    }

}
