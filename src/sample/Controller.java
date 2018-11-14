package sample;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private Label label;

    @FXML
    private Label warningLabel;

    @FXML
    private TableView<Tasks> tableview;

    @FXML
    private TableColumn<Tasks, Integer> id;

    @FXML
    private TableColumn<Tasks, String> coldes;

    @FXML
    private TableColumn<Tasks, String> colper;

    @FXML
    private TableColumn<Tasks, String> coltime;

    @FXML
    private TableColumn<Tasks, String> colnote;

    @FXML
    private TableColumn<Tasks, String> colpro;

    @FXML
    private TableColumn<Tasks, String> colpri;

    @FXML
    private TableColumn<Tasks, String> colbor;

    @FXML
    private TextField fieldDescripiton;

    @FXML
    private TextField fieldPerson;

    @FXML
    private TextField fieldTime;

    @FXML
    private TextField fieldNote;

    @FXML
    private TextField fieldProccess;

    @FXML
    private TextField fieldPriority;

    @FXML
    private TextField fieldBorrowBy;

    @FXML
    private TextField fieldDelete;

    @FXML
    private Button btnload;

    @FXML
    private Button deleteButton;


    private ObservableList<Tasks> data;
    private Database dc;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        tableview.setEditable(true);
        dc=new Database();
        coldes.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<Tasks, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<Tasks, String> t) {
                        ((Tasks) t.getTableView().getItems().get(
                                t.getTablePosition().getRow())
                        ).setDescription(t.getNewValue());
                    }
                }
        );
    }

    @FXML
    private void deleteDataFromDatabase(ActionEvent event) {
        try {
            data = FXCollections.observableArrayList();
            Connection conn = dc.Connect();
            String query = "DELETE FROM tasks WHERE Id = (?)";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, fieldDelete.getText());
            ps.executeUpdate();
        } catch (SQLException ex) {
            System.err.println("Error" + ex);
        }
    }


    @FXML
    private void loadDataFromDatabase(ActionEvent event){
        try {
            Connection conn = dc.Connect();
            data = FXCollections.observableArrayList();
            ResultSet rs = conn.createStatement().executeQuery("Select * FROM tasks");
            while (rs.next()) {
                data.add(new Tasks(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));

            System.out.println(data);
        }
            } catch(SQLException ex){
                System.err.println("Error" + ex);
            }

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            coldes.setCellValueFactory(new PropertyValueFactory<>("description"));
            colper.setCellValueFactory(new PropertyValueFactory<>("person"));
            coltime.setCellValueFactory(new PropertyValueFactory<>("time"));
            colnote.setCellValueFactory(new PropertyValueFactory<>("note"));
            colpro.setCellValueFactory(new PropertyValueFactory<>("proccess"));
            colpri.setCellValueFactory(new PropertyValueFactory<>("priority"));
            colbor.setCellValueFactory(new PropertyValueFactory<>("borrowBy"));

            tableview.setItems(null);
            tableview.setItems(data);

            }
    @FXML
    private void insertDataToDatabase(ActionEvent event){
        try {
            if(fieldDescripiton.getText().length()>1 && fieldPerson.getText().length()>1 && fieldTime.getText().length()>1 && fieldNote.getText().length()>1
                    && fieldProccess.getText().length()>1 && fieldPriority.getText().length()>1 && fieldBorrowBy.getText().length()>1) {
                data = FXCollections.observableArrayList();
                Connection conn = dc.Connect();
                String query = "INSERT INTO tasks(Description, Person, Time, Note, Proccess, Priority, BorrowBy) " +
                        " VALUES (?,?,?,?,?,?,?)";
                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, fieldDescripiton.getText());
                ps.setString(2, fieldPerson.getText());
                ps.setString(3, fieldTime.getText());
                ps.setString(4, fieldNote.getText());
                ps.setString(5, fieldProccess.getText());
                ps.setString(6, fieldPriority.getText());
                ps.setString(7, fieldBorrowBy.getText());
                warningLabel.setVisible(false);
                ps.executeUpdate();
            }else {
                warningLabel.setVisible(true);
            }
        } catch(SQLException ex){
            System.err.println("Error" + ex);
        }
    }
}
