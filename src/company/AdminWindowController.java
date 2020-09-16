package company;

import company.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class AdminWindowController implements Initializable {

    private static final String sqlLoadData = "SELECT * FROM " + dbConnection.TABLE_GPN;

    private static final String sqlLoadSubProjectData = "SELECT * FROM " + dbConnection.TABLE_SUB_PROJECT;

    private static final String sqlLoadPhaseData = "SELECT * FROM " + dbConnection.TABLE_PHASE;

    private static final String sqlAddGPN = "INSERT INTO " + dbConnection.TABLE_GPN
                                                    + "(" + dbConnection.COLUMN_GLOBAL_PROJECT_NUMBER +","
                                                    + dbConnection.COLUMN_GLOBAL_PROJECT_NAME + ","
                                                    + dbConnection.COLUMN_GLOBAL_PROJECT_STATUS + ") VALUES (?,?,?)";

    private static final String sqlAddPhase = "INSERT INTO " +dbConnection.TABLE_PHASE
                                                    + "(" + dbConnection.COLUMN_PHASE_GLOBAL_PROJECT_NUMBER + ","
                                                    + dbConnection.COLUMN_PHASE_NUMBER + ","
                                                    + dbConnection.COLUMN_PHASE_NAME + ","
                                                    + dbConnection.COLUMN_PHASE_STAGE + ","
                                                    + dbConnection.COLUMN_PHASE_STATUS + ") VALUES (?,?,?,?,?)";

    private static final String sqlAddSubProject = "INSERT INTO " +dbConnection.TABLE_SUB_PROJECT
                                                    + "(" + dbConnection.COLUMN_SUB_PROJECT_PHASE_NUMBER + ","
                                                    + dbConnection.COLUMN_SUB_PROJECT_PROJECT_NUMBER + ","
                                                    + dbConnection.COLUMN_SUB_PROJECT_PROJECT_NAME + ","
                                                    + dbConnection.COLUMN_SUB_PROJECT_PROJECT_MANAGER + ","
                                                    + dbConnection.COLUMN_SUB_PROJECT_STATUS + ") VALUES (?,?,?,?,?)";

    private static final String sqlloadDataCombobox = "SELECT " + dbConnection.COLUMN_GLOBAL_PROJECT_NUMBER + " FROM "
                                                      + dbConnection.TABLE_GPN + " ORDER BY " + dbConnection.COLUMN_GLOBAL_PROJECT_NUMBER
                                                      + " ASC";

    private static final String searchGlobalProject = "SELECT * FROM " + dbConnection.TABLE_GPN
            + " WHERE (" +dbConnection.COLUMN_GLOBAL_PROJECT_NUMBER + " LIKE ?" + " OR " + dbConnection.COLUMN_GLOBAL_PROJECT_NAME
            + " LIKE ?" + " OR " + dbConnection.COLUMN_GLOBAL_PROJECT_STATUS + " LIKE ?)";

    private static final String sqlLoadProjectManager = "SELECT * FROM " + dbConnection.TABLE_PROJECT_MANAGERS;

    //tableViews
    /////////////////////////////////////////////////////////////////////
    @FXML
    private TableView<GlobalProjectNumber> globalProjectNumbersTableView;
    @FXML
    private TableView<Phase> phasesTableView;
    @FXML
    private TableView<SubProject> subProjectsTableView;
    @FXML
    private TableView<Task> tasksTableView;
    /////////////////////////////////////////////////////////////////////


    //tableColumns for globalProjectNumbersTableView
    /////////////////////////////////////////////////////////////////////
    @FXML
    private TableColumn<GlobalProjectNumber, String> id;
    @FXML
    private TableColumn<GlobalProjectNumber, String> globalProjectName;
    @FXML
    private TableColumn<GlobalProjectNumber, String> status;
    /////////////////////////////////////////////////////////////////////


    //tableColumns for phasesTableView
    /////////////////////////////////////////////////////////////////////
    @FXML
    private TableColumn<Phase,String> gpn;
    @FXML
    private TableColumn<Phase,String> phaseNumber;
    @FXML
    private TableColumn<Phase,String> phaseName;
    @FXML
    private TableColumn<Phase,String> phaseStage;
    @FXML
    private TableColumn<Phase,String> phaseStatus;
    /////////////////////////////////////////////////////////////////////


    //tableColumn for subProjectsTableView
    /////////////////////////////////////////////////////////////////////
    @FXML
    private TableColumn<SubProject,String> subProjectPhaseNumber;
    @FXML
    private TableColumn<SubProject,String> subProjectNumber;
    @FXML
    private TableColumn<SubProject,String> subProjectName;
    @FXML
    private TableColumn<SubProject,String> projectManager;
    @FXML
    private TableColumn<SubProject,String> subProjectStatus;
    /////////////////////////////////////////////////////////////////////


    //Global Project Manager Tab
    /////////////////////////////////////////////////////////////////////
    @FXML
    private TextField txtFieldGlobalProjectNumber;
    @FXML
    private TextField txtFieldGlobalProjectName;
    @FXML
    private ToggleGroup projectStatus;
    @FXML
    private RadioButton gpnRadioOpened;
    @FXML
    private RadioButton gpnRadioClosed;
    @FXML
    private Button addGlobalProjectButton;
    @FXML
    private Button clearFormGPNButton;
    @FXML
    private TextField txtFieldsearchGlobalProject;
    @FXML
    private Button loadDataButton;
    /////////////////////////////////////////////////////////////////////


    //Phase Number Number
    /////////////////////////////////////////////////////////////////////
    @FXML
    private ComboBox<GlobalProjectNumber> gpnSelector;
    @FXML
    private TextField txtFieldPhaseNumber;
    @FXML
    private TextField txtFieldPhaseName;
    @FXML
    private ComboBox<PhaseStatus> phaseStagesPicker;
    @FXML
    private ToggleGroup phaseStatusToggleGroup;
    @FXML
    private RadioButton phaseRadioOpened;
    @FXML
    private RadioButton phaseRadioClosed;
    @FXML
    private Button addPhaseButton;
    @FXML
    private Button clearFormPhaseButton;
    @FXML
    private TextField txtFieldsearchPhase;
    @FXML
    private Button loadPhaseDataButton;
    /////////////////////////////////////////////////////////////////////


    //Project Manager Tab
    /////////////////////////////////////////////////////////////////////
    @FXML
    private ComboBox<Phase> phaseSelector;
    @FXML
    private TextField txtFieldProjectNumber;
    @FXML
    private TextField txtFieldProjectName;
    @FXML
    private ComboBox<String> projectManagerPicker;
    @FXML
    private ToggleGroup subProjectStatusRadioButton;
    @FXML
    private RadioButton projectStatusOpened;
    @FXML
    private RadioButton projectStatusClosed;
    @FXML
    private Button addSubProjectButton;
    @FXML
    private Button clearFormSubProjectButton;
    @FXML
    private TextField txtFieldsearchSubProject;
    @FXML
    private Button loadSubProjectDataButton;
    /////////////////////////////////////////////////////////////////////

    //User Tab
    /////////////////////////////////////////////////////////////////////
    @FXML
    private ComboBox<SubProject> subProjectSelector;
    @FXML
    private TextField txtFieldTaskNumber;
    @FXML
    private TextField txtFieldTaskName;
    @FXML
    private ComboBox<Worker> workerPicker;
    @FXML
    private ToggleGroup tasksStatusRadioButton;
    @FXML
    private RadioButton taskStatusOpened;
    @FXML
    private RadioButton taskStatusClosed;
    @FXML
    private Button addTaslButton;
    @FXML
    private Button clearFormTaskButton;
    @FXML
    private TextField txtFieldSearchTask;
    @FXML
    private Button loadTaskDataButton;

//    @FXML
//    private TabPane menuPane;
//
//    @FXML
//    private Pane portfolioManager;
//
//    @FXML
//    private Pane globalProjectManager;

    private dbConnection dc;

    private ObservableList<GlobalProjectNumber> globalProjectNumbersTable;

    private ObservableList<SubProject> subProjectsTable;

    private ObservableList<PhaseStatus> phasesStatuses;

    private ObservableList<GlobalProjectNumber> sortedProjectNumbersTable;

    private ObservableList<Phase> phasesTable;

    private ObservableList<Phase> sortedPhasesTable;

    private ObservableList<String> projectManagersTable;

    private ObservableList<Task> tasksTable;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.dc = new dbConnection();
        phasesStatuses = FXCollections.observableArrayList();
        phasesStatuses.addAll(PhaseStatus.values());
        phaseStagesPicker.setItems(phasesStatuses);
        try {
            loadGlobalProject();
            loadPhaseData();
            loadSubProject();
            loadProjectManagers();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @FXML
    private void loadProjectManagers() throws SQLException {
        Connection connection = null;
        ResultSet rs = null;
        try {
            projectManagersTable = FXCollections.observableArrayList();
            connection = dbConnection.getConnecion();
            rs = connection.createStatement().executeQuery(sqlLoadProjectManager);
            while (rs.next()) {
                this.projectManagersTable.add(rs.getString(dbConnection.COLUMN_PROJECT_MANAGERS_PROJECT_MANAGER));
            }
            this.projectManagerPicker.setItems(projectManagersTable);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            rs.close();
            connection.close();
        }
    }

    @FXML
    public void searchTask() {

    }

    @FXML
    public void loadGlobalProjectNumberData(ActionEvent event) throws SQLException {
       loadGlobalProject();
    }

    @FXML
    void loadSubProjectData(ActionEvent event) throws SQLException {
       loadSubProject();
    }

    public void loadSubProject() throws SQLException {
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnecion();
            subProjectsTable = FXCollections.observableArrayList();
            rs = connection.createStatement().executeQuery(sqlLoadSubProjectData);
            while (rs.next()) {
                this.subProjectsTable.add(new SubProject(rs.getString(dbConnection.COLUMN_SUB_PROJECT_PHASE_NUMBER),
                        rs.getString(dbConnection.COLUMN_SUB_PROJECT_PROJECT_NUMBER),rs.getString(dbConnection.COLUMN_SUB_PROJECT_PROJECT_NAME),
                        rs.getString(dbConnection.COLUMN_SUB_PROJECT_PROJECT_MANAGER), Status.valueOf(rs.getString(dbConnection.COLUMN_SUB_PROJECT_STATUS))));

                this.subProjectPhaseNumber.setCellValueFactory(new PropertyValueFactory<SubProject, String>("phaseID"));
                this.subProjectNumber.setCellValueFactory(new PropertyValueFactory<SubProject, String>("subProjectNumber"));
                this.subProjectName.setCellValueFactory(new PropertyValueFactory<SubProject, String>("subProjectName"));
                this.projectManager.setCellValueFactory(new PropertyValueFactory<SubProject, String>("projectManager"));
                this.subProjectStatus.setCellValueFactory(new PropertyValueFactory<SubProject, String>("subProjectStatus"));
            }
            this.subProjectsTableView.setItems(subProjectsTable);
            addPhaseToSubProjectTabCombobox();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            rs.close();
            connection.close();
        }
    }

    public void loadGlobalProject() throws SQLException {

        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnecion();
            globalProjectNumbersTable = FXCollections.observableArrayList();
            rs = connection.createStatement().executeQuery(sqlLoadData);
            while (rs.next()) {
                this.globalProjectNumbersTable.add(new GlobalProjectNumber(rs.getString(dbConnection.COLUMN_GLOBAL_PROJECT_NUMBER),
                                                    rs.getString(dbConnection.COLUMN_GLOBAL_PROJECT_NAME),Status.valueOf(rs.getString(dbConnection.COLUMN_GLOBAL_PROJECT_STATUS))));

                this.id.setCellValueFactory(new PropertyValueFactory<GlobalProjectNumber, String>("id"));
                this.globalProjectName.setCellValueFactory(new PropertyValueFactory<GlobalProjectNumber, String>("globalProjectName"));
                this.status.setCellValueFactory(new PropertyValueFactory<GlobalProjectNumber, String>("status"));
            }
            this.globalProjectNumbersTableView.setItems(globalProjectNumbersTable);
            addGlobalProjectToPhaseTabCombobox();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            rs.close();
            connection.close();
        }
    }

    public void loadTasks() throws SQLException {

        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnecion();
            tasksTable = FXCollections.observableArrayList();
            rs = connection.createStatement().executeQuery(sqlLoadData);
            while (rs.next()) {
                this.globalProjectNumbersTable.add(new GlobalProjectNumber(rs.getString(dbConnection.COLUMN_GLOBAL_PROJECT_NUMBER),
                        rs.getString(dbConnection.COLUMN_GLOBAL_PROJECT_NAME),Status.valueOf(rs.getString(dbConnection.COLUMN_GLOBAL_PROJECT_STATUS))));

                this.id.setCellValueFactory(new PropertyValueFactory<GlobalProjectNumber, String>("id"));
                this.globalProjectName.setCellValueFactory(new PropertyValueFactory<GlobalProjectNumber, String>("globalProjectName"));
                this.status.setCellValueFactory(new PropertyValueFactory<GlobalProjectNumber, String>("status"));
            }
            this.globalProjectNumbersTableView.setItems(globalProjectNumbersTable);
            addGlobalProjectToPhaseTabCombobox();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            rs.close();
            connection.close();
        }
    }

    public void addGlobalProjectToPhaseTabCombobox() {
        List<GlobalProjectNumber> list = new ArrayList<GlobalProjectNumber>(globalProjectNumbersTable);
        list.sort(new Comparator<GlobalProjectNumber>() {
            @Override
            public int compare(GlobalProjectNumber o1, GlobalProjectNumber o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        for (GlobalProjectNumber id : list) {
            System.out.println(id.toString());
        }
        sortedProjectNumbersTable = FXCollections.observableArrayList(list);
        gpnSelector.setItems(sortedProjectNumbersTable);
    }

    public void addPhaseToSubProjectTabCombobox() {
        List<Phase> list = new ArrayList<Phase>(phasesTable);
        list.sort(new Comparator<Phase>() {
            @Override
            public int compare(Phase o1, Phase o2) {
                return o1.getId().compareTo(o2.getId());
            }
        });
        for (Phase id : list) {
            System.out.println(id.toString());
        }
        sortedPhasesTable = FXCollections.observableArrayList(list);
        phaseSelector.setItems(sortedPhasesTable);
    }

    @FXML
    public void addGPN(MouseEvent event) throws SQLException {
        Connection connection = null;
        try {
            connection = dbConnection.getConnecion();
            PreparedStatement pr = connection.prepareStatement(sqlAddGPN);
            pr.setString(1 ,this.txtFieldGlobalProjectNumber.getText());
            pr.setString(2, this.txtFieldGlobalProjectName.getText());
            if (this.projectStatus.getSelectedToggle().equals(gpnRadioClosed)) {
                pr.setString(3, this.gpnRadioClosed.getText());
            } else {
                pr.setString(3, this.gpnRadioOpened.getText());
            }
            pr.execute();
            this.globalProjectNumbersTableView.refresh();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
        }
    }

    @FXML
    public void addPhase(MouseEvent event) throws SQLException {
        Connection connection = null;
        try {
            connection = dbConnection.getConnecion();
            PreparedStatement pr = connection.prepareStatement(sqlAddPhase);
            pr.setString(1,this.gpnSelector.getValue().getId());
            pr.setString(2 ,this.txtFieldPhaseNumber.getText());
            pr.setString(3, this.txtFieldPhaseName.getText());
            pr.setString(4,this.phaseStagesPicker.getValue().name());
            if (this.phaseStatusToggleGroup.getSelectedToggle().equals(phaseRadioClosed)) {
                pr.setString(5, this.phaseRadioClosed.getText());
            } else {
                pr.setString(5, this.phaseRadioOpened.getText());
            }
            pr.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
        }
    }

    @FXML
    void addSubProject(MouseEvent event) throws SQLException {
        Connection connection = null;
        try {
            connection = dbConnection.getConnecion();
            PreparedStatement pr = connection.prepareStatement(sqlAddSubProject);
            pr.setString(1,this.phaseSelector.getValue().getId());
            pr.setString(2 ,this.txtFieldProjectNumber.getText());
            pr.setString(3, this.txtFieldProjectName.getText());
            pr.setString(4,this.projectManagerPicker.getValue());
            if (this.subProjectStatusRadioButton.getSelectedToggle().equals(projectStatusClosed)) {
                pr.setString(5, this.projectStatusClosed.getText());
            } else {
                pr.setString(5, this.projectStatusOpened.getText());
            }
            pr.execute();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            connection.close();
        }
    }

//    public void fillCombobox() throws SQLException {
//        Connection connection = null;
//        try {
//            connection = dbConnection.getConnecion();
//            ResultSet rs = connection.createStatement().executeQuery(loadDataCombobox);
//
//            while (rs.next()) {
//                this.gpnNumbersList.add(rs.getString(dbConnection.COLUMN_GLOBAL_PROJECT_NUMBER));
//            }
//        } catch (SQLException ex) {
//            System.out.println(ex.getMessage());
//        } finally {
//            connection.close();
//        }
//    }

    @FXML
    public void clearGlobalProjectInput(MouseEvent event) {
        txtFieldGlobalProjectNumber.clear();
        txtFieldGlobalProjectName.clear();
        txtFieldsearchGlobalProject.clear();
        projectStatus.selectToggle(gpnRadioOpened);
    }

    @FXML
    public void clearPhaseInput(MouseEvent event) {
        gpnSelector.setValue(null);
        txtFieldPhaseNumber.clear();
        txtFieldPhaseName.clear();
        phaseStagesPicker.setValue(null);
        txtFieldsearchGlobalProject.clear();
        phaseStatusToggleGroup.selectToggle(phaseRadioOpened);
        txtFieldsearchPhase.clear();
    }


    @FXML
    void clearSubProjectInput(MouseEvent event) {
        phaseSelector.setValue(null);
        txtFieldProjectNumber.clear();
        txtFieldProjectName.clear();
        projectManagerPicker.setValue(null);
        subProjectStatusRadioButton.selectToggle(projectStatusOpened);
        txtFieldsearchSubProject.clear();
    }

    @FXML
    public void searchGlobalProject(KeyEvent event) throws SQLException {
        FilteredList<GlobalProjectNumber> filterData = new FilteredList<>(globalProjectNumbersTable, gpn -> true);
        txtFieldsearchGlobalProject.textProperty().addListener((obsevable, oldValue, newValue) -> {
            filterData.setPredicate(gpn -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String typedText = newValue.toLowerCase();
                if (gpn.getId().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (gpn.getGlobalProjectName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (gpn.getStatus().toString().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }

                return false;
            });
            SortedList<GlobalProjectNumber> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(globalProjectNumbersTableView.comparatorProperty());
            globalProjectNumbersTableView.setItems(sortedList);
        });
    }

    @FXML
    public void searchPhase(KeyEvent event) throws SQLException {
        FilteredList<Phase> filterData = new FilteredList<>(phasesTable, phase -> true);
        txtFieldsearchPhase.textProperty().addListener((obsevable, oldValue, newValue) -> {
            filterData.setPredicate(phase -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String typedText = newValue.toLowerCase();

                if (phase.getGpn().toLowerCase().indexOf(typedText) != -1 ) {

                    return true;
                }
                if (phase.getId().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (phase.getPhaseName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (phase.getPhaseStage().toString().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }

                if (phase.getPhaseStatus().toString().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }

                return false;
            });
            SortedList<Phase> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(phasesTableView.comparatorProperty());
            phasesTableView.setItems(sortedList);
        });
    }

    @FXML
    public void searchSubProject(KeyEvent event) throws SQLException {
        FilteredList<SubProject> filterData = new FilteredList<>(subProjectsTable, subProject -> true);
        txtFieldsearchSubProject.textProperty().addListener((obsevable, oldValue, newValue) -> {
            filterData.setPredicate(subProject -> {

                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String typedText = newValue.toLowerCase();

                if (subProject.getPhaseID().toLowerCase().indexOf(typedText) != -1 ) {

                    return true;
                }
                if (subProject.getSubProjectNumber().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (subProject.getSubProjectName().toLowerCase().indexOf(typedText) != -1) {

                    return true;
                }
                if (subProject.getProjectManager().toString().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }

                if (subProject.getSubProjectStatus().toString().toLowerCase().indexOf(typedText) != -1) {
                    return true;
                }

                return false;
            });
            SortedList<SubProject> sortedList = new SortedList<>(filterData);
            sortedList.comparatorProperty().bind(subProjectsTableView.comparatorProperty());
            subProjectsTableView.setItems(sortedList);
        });
    }

    @FXML
    void loadPhaseData() throws SQLException {
        Connection connection = null;
        ResultSet rs = null;
        try {
            connection = dbConnection.getConnecion();
            rs = connection.createStatement().executeQuery(sqlLoadPhaseData);
            phasesTable = FXCollections.observableArrayList();
            while (rs.next()) {

                this.phasesTable.add(new Phase(rs.getString(dbConnection.COLUMN_PHASE_GLOBAL_PROJECT_NUMBER), rs.getString(dbConnection.COLUMN_PHASE_NUMBER),
                        rs.getString(dbConnection.COLUMN_PHASE_NAME),PhaseStatus.valueOf(rs.getString(dbConnection.COLUMN_PHASE_STAGE)),Status.valueOf(rs.getString(dbConnection.COLUMN_PHASE_STATUS))));

                this.gpn.setCellValueFactory(new PropertyValueFactory<Phase, String>("gpn"));
                this.phaseNumber.setCellValueFactory(new PropertyValueFactory<Phase, String>("id"));
                this.phaseName.setCellValueFactory(new PropertyValueFactory<Phase, String>("phaseName"));
                this.phaseStage.setCellValueFactory(new PropertyValueFactory<Phase,String>("phaseStage"));
                this.phaseStatus.setCellValueFactory(new PropertyValueFactory<Phase,String>("phaseStatus"));
            }
            this.phasesTableView.setItems(phasesTable);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } finally {
            rs.close();
            connection.close();
        }
    }
}