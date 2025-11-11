package app.ui;

import app.service.ApiClient;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class EquipoController {

    @FXML private TableView<Map<String, Object>> tblEquipos;
    @FXML private TableColumn<Map<String, Object>, String> colEquipoId;
    @FXML private TableColumn<Map<String, Object>, String> colEquipoNombre;
    @FXML private TableColumn<Map<String, Object>, String> colEquipoEstadio;
    @FXML private TableColumn<Map<String, Object>, String> colEquipoAforo;
    @FXML private TableColumn<Map<String, Object>, String> colEquipoFundacion;
    @FXML private TableColumn<Map<String, Object>, String> colEquipoDepartamento;

    @FXML private TextField txtEquipoNombre;
    @FXML private TextField txtEquipoEstadio;
    @FXML private TextField txtEquipoAforo;
    @FXML private TextField txtEquipoFundacion;
    @FXML private ComboBox<Map<String, Object>> cmbEquipoDepartamento;

    @FXML private TableView<Map<String, Object>> tblJugadores;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorId;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorNombre;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorApellidos;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorCorreo;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorMunicipio;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorFecha;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorPosicion;
    @FXML private TableColumn<Map<String, Object>, String> colJugadorEquipo;

    @FXML private TextField txtJugadorNombre1;
    @FXML private TextField txtJugadorNombre2;
    @FXML private TextField txtJugadorApellido1;
    @FXML private TextField txtJugadorApellido2;
    @FXML private TextField txtJugadorCorreo;
    @FXML private DatePicker dateJugadorNacimiento;
    @FXML private ComboBox<String> cmbJugadorPosicion;
    @FXML private ComboBox<Map<String, Object>> cmbJugadorDepartamento;
    @FXML private ComboBox<Map<String, Object>> cmbJugadorMunicipio;
    @FXML private ComboBox<Map<String, Object>> cmbJugadorEquipo;

    @FXML private ComboBox<Map<String, Object>> cmbJugadorSeleccionCorreo;
    @FXML private TextField txtCorreoJugadorNuevo;
    @FXML private TableView<Map<String, Object>> tblCorreosJugador;
    @FXML private TableColumn<Map<String, Object>, String> colCorreoJugadorId;
    @FXML private TableColumn<Map<String, Object>, String> colCorreoJugadorCorreo;

    @FXML private TableView<Map<String, Object>> tblPartidos;
    @FXML private TableColumn<Map<String, Object>, String> colPartidoId;
    @FXML private TableColumn<Map<String, Object>, String> colPartidoFecha;
    @FXML private TableColumn<Map<String, Object>, String> colPartidoGolesCasa;
    @FXML private TableColumn<Map<String, Object>, String> colPartidoGolesFuera;
    @FXML private TableColumn<Map<String, Object>, String> colPartidoEquipoCasa;
    @FXML private TableColumn<Map<String, Object>, String> colPartidoEquipoFuera;

    @FXML private DatePicker datePartidoFecha;
    @FXML private ComboBox<Map<String, Object>> cmbPartidoEquipoCasa;
    @FXML private ComboBox<Map<String, Object>> cmbPartidoEquipoFuera;
    @FXML private TextField txtPartidoGolesCasa;
    @FXML private TextField txtPartidoGolesFuera;

    @FXML private TableView<Map<String, Object>> tblGoles;
    @FXML private TableColumn<Map<String, Object>, String> colGolId;
    @FXML private TableColumn<Map<String, Object>, String> colGolPartido;
    @FXML private TableColumn<Map<String, Object>, String> colGolJugador;
    @FXML private TableColumn<Map<String, Object>, String> colGolMinuto;
    @FXML private TableColumn<Map<String, Object>, String> colGolDescripcion;

    @FXML private ComboBox<Map<String, Object>> cmbGolPartido;
    @FXML private ComboBox<Map<String, Object>> cmbGolJugador;
    @FXML private TextField txtGolMinuto;
    @FXML private TextField txtGolDescripcion;

    @FXML private TableView<Map<String, Object>> tblPresidentes;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteDpi;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteNombres;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteApellidos;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteCorreo;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteMunicipio;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteFecha;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteAnio;
    @FXML private TableColumn<Map<String, Object>, String> colPresidenteEquipo;

    @FXML private TableView<Map<String, Object>> tblDepartamentos;
    @FXML private TableColumn<Map<String, Object>, String> colDepartamentoId;
    @FXML private TableColumn<Map<String, Object>, String> colDepartamentoNombre;
    @FXML private TextField txtDepartamentoNombre;

    @FXML private TableView<Map<String, Object>> tblMunicipios;
    @FXML private TableColumn<Map<String, Object>, String> colMunicipioId;
    @FXML private TableColumn<Map<String, Object>, String> colMunicipioNombre;
    @FXML private TableColumn<Map<String, Object>, String> colMunicipioDepartamento;
    @FXML private TextField txtMunicipioNombre;
    @FXML private ComboBox<Map<String, Object>> cmbMunicipioDepartamento;

    @FXML private TextField txtPresidenteDpi;
    @FXML private TextField txtPresidenteNombre1;
    @FXML private TextField txtPresidenteNombre2;
    @FXML private TextField txtPresidenteNombre3;
    @FXML private TextField txtPresidenteApellido1;
    @FXML private TextField txtPresidenteApellido2;
    @FXML private TextField txtPresidenteCorreo;
    @FXML private DatePicker datePresidenteNacimiento;
    @FXML private TextField txtPresidenteAnio;
    @FXML private ComboBox<Map<String, Object>> cmbPresidenteDepartamento;
    @FXML private ComboBox<Map<String, Object>> cmbPresidenteMunicipio;
    @FXML private ComboBox<Map<String, Object>> cmbPresidenteEquipo;

    @FXML private ComboBox<Map<String, Object>> cmbPresidenteSeleccionCorreo;
    @FXML private TextField txtCorreoPresidenteNuevo;
    @FXML private TableView<Map<String, Object>> tblCorreosPresidente;
    @FXML private TableColumn<Map<String, Object>, String> colCorreoPresidenteId;
    @FXML private TableColumn<Map<String, Object>, String> colCorreoPresidenteCorreo;

    private final ApiClient api = new ApiClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_LOCAL_DATE;

    private final ObservableList<Map<String, Object>> equipos = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> jugadores = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> partidos = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> goles = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> presidentes = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> correosJugador = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> correosPresidente = FXCollections.observableArrayList();

    private final ObservableList<Map<String, Object>> departamentos = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> municipiosCatalogo = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> municipiosJugador = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> municipiosPresidente = FXCollections.observableArrayList();

    private final ObservableList<Map<String, Object>> equipoComboItems = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> jugadorComboItems = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> partidoComboItems = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> presidenteComboItems = FXCollections.observableArrayList();

    private final Map<Long, List<Map<String, Object>>> municipiosPorDepartamento = new HashMap<>();

    private final Map<Long, Map<String, Object>> departmentById = new HashMap<>();
    private final Map<Long, Map<String, Object>> teamById = new HashMap<>();
    private final Map<Long, Map<String, Object>> playerById = new HashMap<>();
    private final Map<Long, Map<String, Object>> matchById = new HashMap<>();
    private final Map<String, Map<String, Object>> presidentByDpi = new HashMap<>();

    private Long selectedTeamId;
    private Long selectedPlayerId;
    private Long selectedPlayerEmailId;
    private Long selectedMatchId;
    private Long selectedGoalId;
    private Long selectedGoalMatchId;
    private String selectedPresidentDpi;
    private Long selectedPresidentEmailId;
    private Long selectedDepartamentoId;
    private Long selectedMunicipioId;

    @FXML
    public void initialize() {
        setupTables();
        setupComboBoxes();

        cmbJugadorSeleccionCorreo.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> loadPlayerEmailsFromSelection());
        cmbPresidenteSeleccionCorreo.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> loadPresidentEmailsFromSelection());

        cmbJugadorDepartamento.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldDept, newDept) -> {
                    Long deptId = newDept != null ? asLong(newDept.get("id")) : null;
                    updateMunicipalitiesForDepartment(deptId, municipiosJugador, cmbJugadorMunicipio, null);
                });

        cmbPresidenteDepartamento.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldDept, newDept) -> {
                    Long deptId = newDept != null ? asLong(newDept.get("id")) : null;
                    updateMunicipalitiesForDepartment(deptId, municipiosPresidente, cmbPresidenteMunicipio, null);
                });

        tblEquipos.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populateEquipoForm(newValue));
        tblJugadores.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populateJugadorForm(newValue));
        tblCorreosJugador.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populateJugadorCorreoForm(newValue));
        tblPartidos.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populatePartidoForm(newValue));
        tblGoles.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populateGolForm(newValue));
        tblPresidentes.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populatePresidenteForm(newValue));
        tblCorreosPresidente.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populatePresidenteCorreoForm(newValue));
        tblDepartamentos.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populateDepartamentoForm(newValue));
        tblMunicipios.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> populateMunicipioForm(newValue));

        onRefresh();
    }

    private void setupTables() {
        bindColumn(colEquipoId, "id");
        bindColumn(colEquipoNombre, "name");
        bindColumn(colEquipoEstadio, "stadium");
        bindColumn(colEquipoAforo, "capacity");
        bindColumn(colEquipoFundacion, "foundationYear");
        bindColumn(colEquipoDepartamento, "departmentName");
        tblEquipos.setItems(equipos);

        bindColumn(colJugadorId, "id");
        bindColumn(colJugadorNombre, "fullName");
        bindColumn(colJugadorApellidos, "lastNames");
        bindColumn(colJugadorCorreo, "email");
        bindColumn(colJugadorMunicipio, "municipalityName");
        bindColumn(colJugadorFecha, "birthDate");
        bindColumn(colJugadorPosicion, "position");
        bindColumn(colJugadorEquipo, "teamName");
        tblJugadores.setItems(jugadores);

        bindColumn(colCorreoJugadorId, "id");
        bindColumn(colCorreoJugadorCorreo, "email");
        tblCorreosJugador.setItems(correosJugador);

        bindColumn(colPartidoId, "id");
        bindColumn(colPartidoFecha, "date");
        bindColumn(colPartidoGolesCasa, "homeGoals");
        bindColumn(colPartidoGolesFuera, "awayGoals");
        bindColumn(colPartidoEquipoCasa, "homeTeamName");
        bindColumn(colPartidoEquipoFuera, "awayTeamName");
        tblPartidos.setItems(partidos);

        bindColumn(colGolId, "id");
        bindColumn(colGolPartido, "matchLabel");
        bindColumn(colGolJugador, "playerName");
        bindColumn(colGolMinuto, "minute");
        bindColumn(colGolDescripcion, "description");
        tblGoles.setItems(goles);

        bindColumn(colPresidenteDpi, "dpi");
        bindColumn(colPresidenteNombres, "names");
        bindColumn(colPresidenteApellidos, "lastNames");
        bindColumn(colPresidenteCorreo, "email");
        bindColumn(colPresidenteMunicipio, "municipalityName");
        bindColumn(colPresidenteFecha, "birthDate");
        bindColumn(colPresidenteAnio, "electionYear");
        bindColumn(colPresidenteEquipo, "teamName");
        tblPresidentes.setItems(presidentes);

        bindColumn(colCorreoPresidenteId, "id");
        bindColumn(colCorreoPresidenteCorreo, "email");
        tblCorreosPresidente.setItems(correosPresidente);

        bindColumn(colDepartamentoId, "id");
        bindColumn(colDepartamentoNombre, "name");
        tblDepartamentos.setItems(departamentos);

        bindColumn(colMunicipioId, "id");
        bindColumn(colMunicipioNombre, "name");
        bindColumn(colMunicipioDepartamento, "departmentName");
        tblMunicipios.setItems(municipiosCatalogo);
    }

    private void bindColumn(TableColumn<Map<String, Object>, String> column, String key) {
        column.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(stringValue(data.getValue().get(key))));
    }

    private void setupComboBoxes() {
        configureComboBox(cmbEquipoDepartamento, "name");
        configureComboBox(cmbJugadorDepartamento, "name");
        configureComboBox(cmbJugadorMunicipio, "name");
        configureComboBox(cmbPresidenteDepartamento, "name");
        configureComboBox(cmbPresidenteMunicipio, "name");
        configureComboBox(cmbMunicipioDepartamento, "name");
        configureComboBox(cmbJugadorEquipo, "name");
        configureComboBox(cmbPartidoEquipoCasa, "name");
        configureComboBox(cmbPartidoEquipoFuera, "name");
        configureComboBox(cmbPresidenteEquipo, "name");

        configureComboBox(cmbGolPartido, "label");
        configureComboBox(cmbGolJugador, "fullName");
        configureComboBox(cmbJugadorSeleccionCorreo, "fullName");
        configureComboBox(cmbPresidenteSeleccionCorreo, "names");

        cmbEquipoDepartamento.setItems(departamentos);
        cmbJugadorDepartamento.setItems(departamentos);
        cmbPresidenteDepartamento.setItems(departamentos);
        cmbMunicipioDepartamento.setItems(departamentos);

        cmbJugadorMunicipio.setItems(municipiosJugador);
        cmbPresidenteMunicipio.setItems(municipiosPresidente);

        cmbJugadorEquipo.setItems(equipoComboItems);
        cmbPartidoEquipoCasa.setItems(equipoComboItems);
        cmbPartidoEquipoFuera.setItems(equipoComboItems);
        cmbPresidenteEquipo.setItems(equipoComboItems);

        cmbGolPartido.setItems(partidoComboItems);
        cmbGolJugador.setItems(jugadorComboItems);
        cmbJugadorSeleccionCorreo.setItems(jugadorComboItems);
        cmbPresidenteSeleccionCorreo.setItems(presidenteComboItems);
    }

    private void configureComboBox(ComboBox<Map<String, Object>> combo, String labelKey) {
        combo.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Map<String, Object> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : stringValue(item.get(labelKey)));
            }
        });
        combo.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Map<String, Object> item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty || item == null ? "" : stringValue(item.get(labelKey)));
            }
        });
        combo.setConverter(new StringConverter<>() {
            @Override
            public String toString(Map<String, Object> object) {
                return object == null ? "" : stringValue(object.get(labelKey));
            }

            @Override
            public Map<String, Object> fromString(String string) {
                return combo.getItems().stream()
                        .filter(item -> Objects.equals(stringValue(item.get(labelKey)), string))
                        .findFirst()
                        .orElse(null);
            }
        });
    }

    @FXML
    public void onRefresh() {
        try {
            loadCatalogs();
            loadTeams();
            loadPlayers();
            loadMatches();
            loadGoals();
            loadPresidents();
            loadPlayerEmailsFromSelection();
            loadPresidentEmailsFromSelection();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void loadCatalogs() throws Exception {
        String departmentsJson = api.getDepartments();
        List<Map<String, Object>> departmentList = mapper.readValue(departmentsJson, new TypeReference<>() {});

        departmentById.clear();
        municipiosPorDepartamento.clear();

        List<Map<String, Object>> departmentCopies = new ArrayList<>();
        for (Map<String, Object> department : departmentList) {
            Map<String, Object> deptCopy = copyMap(department);
            deptCopy.remove("municipalities");
            String deptName = stringValue(deptCopy.get("name"));
            deptCopy.put("name", deptName);
            Long deptId = asLong(deptCopy.get("id"));
            departmentCopies.add(deptCopy);
            departmentById.put(deptId, deptCopy);
        }
        departamentos.setAll(departmentCopies);

        String municipalitiesJson = api.getMunicipalities();
        List<Map<String, Object>> municipalityList = mapper.readValue(municipalitiesJson, new TypeReference<>() {});

        List<Map<String, Object>> allMunicipalities = new ArrayList<>();
        for (Map<String, Object> municipality : municipalityList) {
            Map<String, Object> muniCopy = copyMap(municipality);
            Map<String, Object> deptMap = castMap(muniCopy.get("department"));
            Long deptId = null;
            String deptName = "";
            if (deptMap != null) {
                deptId = asLong(deptMap.get("id"));
                deptName = stringValue(deptMap.get("name"));
            } else {
                deptId = asLong(muniCopy.get("departmentId"));
                if (deptId != null) {
                    Map<String, Object> dept = departmentById.get(deptId);
                    if (dept != null) {
                        deptName = stringValue(dept.get("name"));
                    }
                }
            }
            muniCopy.put("departmentId", deptId);
            muniCopy.remove("department");

            if (deptId != null) {
                municipiosPorDepartamento
                        .computeIfAbsent(deptId, ignored -> new ArrayList<>())
                        .add(copyMap(muniCopy));
            }

            Map<String, Object> tableEntry = copyMap(muniCopy);
            tableEntry.put("departmentName", deptName);
            allMunicipalities.add(tableEntry);
        }

        municipiosCatalogo.setAll(allMunicipalities.stream()
                .sorted(Comparator
                        .comparing((Map<String, Object> m) -> stringValue(m.get("departmentName")))
                        .thenComparing(m -> stringValue(m.get("name"))))
                .toList());

        municipiosJugador.clear();
        municipiosPresidente.clear();
        clearDepartamentoForm();
        clearMunicipioForm();
        cmbEquipoDepartamento.getSelectionModel().clearSelection();
        cmbJugadorDepartamento.getSelectionModel().clearSelection();
        cmbJugadorMunicipio.getSelectionModel().clearSelection();
        cmbPresidenteDepartamento.getSelectionModel().clearSelection();
        cmbPresidenteMunicipio.getSelectionModel().clearSelection();
    }

    private void loadTeams() throws Exception {
        String json = api.getTeams();
        List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
        equipos.setAll(list);
        equipoComboItems.setAll(list);

        teamById.clear();
        for (Map<String, Object> team : list) {
            Long id = asLong(team.get("id"));
            teamById.put(id, team);
            team.put("name", stringValue(team.get("name")));
            team.put("stadium", stringValue(team.get("stadium")));
            team.put("capacity", numberToString(team.get("capacity")));
            team.put("foundationYear", numberToString(team.get("foundationYear")));

            Map<String, Object> department = castMap(team.get("department"));
            if (department != null) {
                team.put("departmentName", stringValue(department.get("name")));
                team.put("departmentId", asLong(department.get("id")));
            } else {
                team.put("departmentName", "");
                team.put("departmentId", null);
            }
        }

        cmbJugadorEquipo.getSelectionModel().clearSelection();
        cmbPartidoEquipoCasa.getSelectionModel().clearSelection();
        cmbPartidoEquipoFuera.getSelectionModel().clearSelection();
        cmbPresidenteEquipo.getSelectionModel().clearSelection();
        tblEquipos.getSelectionModel().clearSelection();
        clearEquipoForm();
    }

    private void loadPlayers() throws Exception {
        String json = api.getPlayers();
        List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
        jugadores.setAll(list);
        jugadorComboItems.setAll(list);

        playerById.clear();
        for (Map<String, Object> player : list) {
            Long id = asLong(player.get("id"));
            playerById.put(id, player);

            String nombres = joinStrings(
                    stringValue(player.get("firstName")),
                    stringValue(player.get("secondName"))
            );
            String apellidos = joinStrings(
                    stringValue(player.get("lastName1")),
                    stringValue(player.get("lastName2"))
            );
            player.put("fullName", nombres);
            player.put("lastNames", apellidos);
            player.put("email", stringValue(player.get("email")));
            player.put("birthDate", formatDate(player.get("birthDate")));
            player.put("position", stringValue(player.get("position")));

            Map<String, Object> municipality = castMap(player.get("municipality"));
            if (municipality != null) {
                player.put("municipalityName", stringValue(municipality.get("name")));
                player.put("municipalityId", asLong(municipality.get("id")));
                Object departmentObj = municipality.get("department");
                Long deptId = null;
                if (departmentObj instanceof Map<?, ?> deptMap) {
                    deptId = asLong(((Map<?, ?>) departmentObj).get("id"));
                } else {
                    deptId = asLong(municipality.get("departmentId"));
                }
                player.put("departmentId", deptId);
            } else {
                player.put("municipalityName", "");
                player.put("municipalityId", null);
                player.put("departmentId", null);
            }

            Long teamId = asLong(player.get("teamId"));
            String teamName = Optional.ofNullable(teamById.get(teamId))
                    .map(t -> stringValue(t.get("name")))
                    .orElse("");
            player.put("teamName", teamName);
        }

        tblJugadores.getSelectionModel().clearSelection();
        clearJugadorForm();
    }

    private void loadMatches() throws Exception {
        String json = api.getMatches();
        List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
        partidos.setAll(list);
        partidoComboItems.setAll(list);

        matchById.clear();
        for (Map<String, Object> match : list) {
            Long id = asLong(match.get("id"));
            matchById.put(id, match);

            match.put("date", formatDate(match.get("date")));
            match.put("homeGoals", numberToString(match.get("homeGoals")));
            match.put("awayGoals", numberToString(match.get("awayGoals")));

            Long homeId = asLong(match.get("homeTeamId"));
            Long awayId = asLong(match.get("awayTeamId"));
            String homeName = Optional.ofNullable(teamById.get(homeId)).map(t -> stringValue(t.get("name"))).orElse("");
            String awayName = Optional.ofNullable(teamById.get(awayId)).map(t -> stringValue(t.get("name"))).orElse("");
            match.put("homeTeamName", homeName);
            match.put("awayTeamName", awayName);
            match.put("label", String.format("#%s - %s vs %s", match.get("id"), homeName, awayName));
        }

        tblPartidos.getSelectionModel().clearSelection();
        clearPartidoForm();
    }

    private void loadGoals() throws Exception {
        String json = api.getGoals();
        List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
        goles.setAll(list);

        for (Map<String, Object> goal : list) {
            goal.put("minute", numberToString(goal.get("minute")));
            goal.put("description", stringValue(goal.get("description")));

            Long playerId = asLong(goal.get("playerId"));
            String playerName = Optional.ofNullable(playerById.get(playerId))
                    .map(p -> stringValue(p.get("fullName")) + " " + stringValue(p.get("lastNames")).trim())
                    .orElse("");
            goal.put("playerName", playerName.trim());

            Map<String, Object> match = castMap(goal.get("match"));
            Long matchId = match != null ? asLong(match.get("id")) : null;
            Map<String, Object> matchInfo = matchId != null ? matchById.get(matchId) : null;
            String label = matchInfo != null ? stringValue(matchInfo.get("label"))
                    : (matchId != null ? "Partido #" + matchId : "");
            goal.put("matchLabel", label);
        }

        tblGoles.getSelectionModel().clearSelection();
        clearGolForm();
    }

    private void loadPresidents() throws Exception {
        String json = api.getPresidents();
        List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
        presidentes.setAll(list);
        presidenteComboItems.setAll(list);

        presidentByDpi.clear();
        for (Map<String, Object> president : list) {
            String dpi = stringValue(president.get("dpi"));
            presidentByDpi.put(dpi, president);

            String names = joinStrings(
                    stringValue(president.get("firstName")),
                    stringValue(president.get("secondName")),
                    stringValue(president.get("thirdName"))
            );
            String lastNames = joinStrings(
                    stringValue(president.get("lastName1")),
                    stringValue(president.get("lastName2"))
            );
            president.put("names", names);
            president.put("lastNames", lastNames);
            president.put("email", stringValue(president.get("email")));
            president.put("birthDate", formatDate(president.get("birthDate")));
            president.put("electionYear", numberToString(president.get("electionYear")));

            Map<String, Object> municipality = castMap(president.get("municipality"));
            if (municipality != null) {
                president.put("municipalityName", stringValue(municipality.get("name")));
                president.put("municipalityId", asLong(municipality.get("id")));
                Map<String, Object> deptMap = castMap(municipality.get("department"));
                Long deptId = deptMap != null ? asLong(deptMap.get("id")) : asLong(municipality.get("departmentId"));
                president.put("departmentId", deptId);
            } else {
                president.put("municipalityName", "");
                president.put("municipalityId", null);
                president.put("departmentId", null);
            }

            Map<String, Object> team = castMap(president.get("team"));
            String teamName = team != null ? stringValue(team.get("name")) : "";
            president.put("teamName", teamName);
        }

        cmbPresidenteEquipo.getSelectionModel().clearSelection();
        cmbPresidenteSeleccionCorreo.getSelectionModel().clearSelection();
        tblPresidentes.getSelectionModel().clearSelection();
        clearPresidenteForm();
    }

    private void loadPlayerEmailsFromSelection() {
        Map<String, Object> player = cmbJugadorSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (player == null) {
            correosJugador.clear();
            clearCorreoJugadorForm();
            return;
        }
        try {
            Long playerId = asLong(player.get("id"));
            String json = api.getPlayerEmails(playerId);
            List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
            correosJugador.setAll(list);
            tblCorreosJugador.getSelectionModel().clearSelection();
            clearCorreoJugadorForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void loadPresidentEmailsFromSelection() {
        Map<String, Object> president = cmbPresidenteSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (president == null) {
            correosPresidente.clear();
            clearCorreoPresidenteForm();
            return;
        }
        try {
            String dpi = stringValue(president.get("dpi"));
            String json = api.getPresidentEmails(dpi);
            List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
            correosPresidente.setAll(list);
            tblCorreosPresidente.getSelectionModel().clearSelection();
            clearCorreoPresidenteForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void populateEquipoForm(Map<String, Object> team) {
        if (team == null) {
            clearEquipoForm();
            return;
        }
        selectedTeamId = asLong(team.get("id"));
        txtEquipoNombre.setText(stringValue(team.get("name")));
        txtEquipoEstadio.setText(stringValue(team.get("stadium")));
        txtEquipoAforo.setText(numberToString(team.get("capacity")));
        txtEquipoFundacion.setText(numberToString(team.get("foundationYear")));
        selectComboItemById(cmbEquipoDepartamento, asLong(team.get("departmentId")));
    }

    private void clearEquipoForm() {
        selectedTeamId = null;
        txtEquipoNombre.clear();
        txtEquipoEstadio.clear();
        txtEquipoAforo.clear();
        txtEquipoFundacion.clear();
        cmbEquipoDepartamento.getSelectionModel().clearSelection();
        if (tblEquipos != null) {
            tblEquipos.getSelectionModel().clearSelection();
        }
    }

    private void populateJugadorForm(Map<String, Object> player) {
        if (player == null) {
            clearJugadorForm();
            return;
        }
        selectedPlayerId = asLong(player.get("id"));
        txtJugadorNombre1.setText(stringValue(player.get("firstName")));
        txtJugadorNombre2.setText(stringValue(player.get("secondName")));
        txtJugadorApellido1.setText(stringValue(player.get("lastName1")));
        txtJugadorApellido2.setText(stringValue(player.get("lastName2")));
        txtJugadorCorreo.setText(stringValue(player.get("email")));
        dateJugadorNacimiento.setValue(parseDate(stringValue(player.get("birthDate"))));
        cmbJugadorPosicion.getSelectionModel().select(stringValue(player.get("position")));

        Long departmentId = asLong(player.get("departmentId"));
        Long municipalityId = asLong(player.get("municipalityId"));
        selectComboItemById(cmbJugadorDepartamento, departmentId);
        updateMunicipalitiesForDepartment(departmentId, municipiosJugador, cmbJugadorMunicipio, municipalityId);
        selectComboItemById(cmbJugadorEquipo, asLong(player.get("teamId")));
        selectComboItemById(cmbJugadorSeleccionCorreo, selectedPlayerId);
    }

    private void clearJugadorForm() {
        selectedPlayerId = null;
        txtJugadorNombre1.clear();
        txtJugadorNombre2.clear();
        txtJugadorApellido1.clear();
        txtJugadorApellido2.clear();
        txtJugadorCorreo.clear();
        dateJugadorNacimiento.setValue(null);
        cmbJugadorPosicion.getSelectionModel().clearSelection();
        cmbJugadorDepartamento.getSelectionModel().clearSelection();
        municipiosJugador.clear();
        cmbJugadorMunicipio.getSelectionModel().clearSelection();
        cmbJugadorEquipo.getSelectionModel().clearSelection();
        if (tblJugadores != null) {
            tblJugadores.getSelectionModel().clearSelection();
        }
    }

    private void populateJugadorCorreoForm(Map<String, Object> correo) {
        if (correo == null) {
            clearCorreoJugadorForm();
            return;
        }
        selectedPlayerEmailId = asLong(correo.get("id"));
        txtCorreoJugadorNuevo.setText(stringValue(correo.get("email")));
    }

    private void clearCorreoJugadorForm() {
        selectedPlayerEmailId = null;
        txtCorreoJugadorNuevo.clear();
        if (tblCorreosJugador != null) {
            tblCorreosJugador.getSelectionModel().clearSelection();
        }
    }

    private void populatePartidoForm(Map<String, Object> match) {
        if (match == null) {
            clearPartidoForm();
            return;
        }
        selectedMatchId = asLong(match.get("id"));
        datePartidoFecha.setValue(parseDate(stringValue(match.get("date"))));
        txtPartidoGolesCasa.setText(numberToString(match.get("homeGoals")));
        txtPartidoGolesFuera.setText(numberToString(match.get("awayGoals")));
        selectComboItemById(cmbPartidoEquipoCasa, asLong(match.get("homeTeamId")));
        selectComboItemById(cmbPartidoEquipoFuera, asLong(match.get("awayTeamId")));
    }

    private void clearPartidoForm() {
        selectedMatchId = null;
        datePartidoFecha.setValue(null);
        txtPartidoGolesCasa.clear();
        txtPartidoGolesFuera.clear();
        cmbPartidoEquipoCasa.getSelectionModel().clearSelection();
        cmbPartidoEquipoFuera.getSelectionModel().clearSelection();
        if (tblPartidos != null) {
            tblPartidos.getSelectionModel().clearSelection();
        }
    }

    private void populateGolForm(Map<String, Object> goal) {
        if (goal == null) {
            clearGolForm();
            return;
        }
        selectedGoalId = asLong(goal.get("id"));
        Map<String, Object> matchMap = castMap(goal.get("match"));
        selectedGoalMatchId = matchMap != null ? asLong(matchMap.get("id")) : null;
        selectComboItemById(cmbGolPartido, selectedGoalMatchId);
        selectComboItemById(cmbGolJugador, asLong(goal.get("playerId")));
        txtGolMinuto.setText(numberToString(goal.get("minute")));
        txtGolDescripcion.setText(stringValue(goal.get("description")));
    }

    private void clearGolForm() {
        selectedGoalId = null;
        selectedGoalMatchId = null;
        cmbGolPartido.getSelectionModel().clearSelection();
        cmbGolJugador.getSelectionModel().clearSelection();
        txtGolMinuto.clear();
        txtGolDescripcion.clear();
        if (tblGoles != null) {
            tblGoles.getSelectionModel().clearSelection();
        }
    }

    private void populatePresidenteForm(Map<String, Object> president) {
        if (president == null) {
            clearPresidenteForm();
            return;
        }
        selectedPresidentDpi = stringValue(president.get("dpi"));
        txtPresidenteDpi.setText(selectedPresidentDpi);
        txtPresidenteDpi.setDisable(true);
        txtPresidenteNombre1.setText(stringValue(president.get("firstName")));
        txtPresidenteNombre2.setText(stringValue(president.get("secondName")));
        txtPresidenteNombre3.setText(stringValue(president.get("thirdName")));
        txtPresidenteApellido1.setText(stringValue(president.get("lastName1")));
        txtPresidenteApellido2.setText(stringValue(president.get("lastName2")));
        txtPresidenteCorreo.setText(stringValue(president.get("email")));
        txtPresidenteAnio.setText(numberToString(president.get("electionYear")));
        datePresidenteNacimiento.setValue(parseDate(stringValue(president.get("birthDate"))));

        Long departmentId = asLong(president.get("departmentId"));
        Long municipalityId = asLong(president.get("municipalityId"));
        selectComboItemById(cmbPresidenteDepartamento, departmentId);
        updateMunicipalitiesForDepartment(departmentId, municipiosPresidente, cmbPresidenteMunicipio, municipalityId);

        Map<String, Object> team = castMap(president.get("team"));
        selectComboItemById(cmbPresidenteEquipo, team != null ? asLong(team.get("id")) : null);
        if (selectedPresidentDpi != null) {
            cmbPresidenteSeleccionCorreo.getItems().stream()
                    .filter(item -> Objects.equals(stringValue(item.get("dpi")), selectedPresidentDpi))
                    .findFirst()
                    .ifPresent(item -> cmbPresidenteSeleccionCorreo.getSelectionModel().select(item));
        }
    }

    private void clearPresidenteForm() {
        selectedPresidentDpi = null;
        txtPresidenteDpi.setDisable(false);
        txtPresidenteDpi.clear();
        txtPresidenteNombre1.clear();
        txtPresidenteNombre2.clear();
        txtPresidenteNombre3.clear();
        txtPresidenteApellido1.clear();
        txtPresidenteApellido2.clear();
        txtPresidenteCorreo.clear();
        txtPresidenteAnio.clear();
        datePresidenteNacimiento.setValue(null);
        cmbPresidenteDepartamento.getSelectionModel().clearSelection();
        municipiosPresidente.clear();
        cmbPresidenteMunicipio.getSelectionModel().clearSelection();
        cmbPresidenteEquipo.getSelectionModel().clearSelection();
        if (tblPresidentes != null) {
            tblPresidentes.getSelectionModel().clearSelection();
        }
    }

    private void populatePresidenteCorreoForm(Map<String, Object> correo) {
        if (correo == null) {
            clearCorreoPresidenteForm();
            return;
        }
        selectedPresidentEmailId = asLong(correo.get("id"));
        txtCorreoPresidenteNuevo.setText(stringValue(correo.get("email")));
    }

    private void clearCorreoPresidenteForm() {
        selectedPresidentEmailId = null;
        txtCorreoPresidenteNuevo.clear();
        if (tblCorreosPresidente != null) {
            tblCorreosPresidente.getSelectionModel().clearSelection();
        }
    }

    private void populateDepartamentoForm(Map<String, Object> department) {
        if (department == null) {
            clearDepartamentoForm();
            return;
        }
        selectedDepartamentoId = asLong(department.get("id"));
        txtDepartamentoNombre.setText(stringValue(department.get("name")));
    }

    private void clearDepartamentoForm() {
        selectedDepartamentoId = null;
        txtDepartamentoNombre.clear();
        if (tblDepartamentos != null) {
            tblDepartamentos.getSelectionModel().clearSelection();
        }
    }

    private void populateMunicipioForm(Map<String, Object> municipality) {
        if (municipality == null) {
            clearMunicipioForm();
            return;
        }
        selectedMunicipioId = asLong(municipality.get("id"));
        txtMunicipioNombre.setText(stringValue(municipality.get("name")));
        selectComboItemById(cmbMunicipioDepartamento, asLong(municipality.get("departmentId")));
    }

    private void clearMunicipioForm() {
        selectedMunicipioId = null;
        txtMunicipioNombre.clear();
        if (cmbMunicipioDepartamento != null) {
            cmbMunicipioDepartamento.getSelectionModel().clearSelection();
        }
        if (tblMunicipios != null) {
            tblMunicipios.getSelectionModel().clearSelection();
        }
    }

    private void selectComboItemById(ComboBox<Map<String, Object>> combo, Long id) {
        if (combo == null) {
            return;
        }
        if (id == null) {
            combo.getSelectionModel().clearSelection();
            return;
        }
        combo.getItems().stream()
                .filter(item -> Objects.equals(asLong(item.get("id")), id))
                .findFirst()
                .ifPresent(combo.getSelectionModel()::select);
    }

    private LocalDate parseDate(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        try {
            return LocalDate.parse(value);
        } catch (Exception e) {
            return null;
        }
    }


    @FXML
    public void onCrearEquipo() {
        if (isBlank(txtEquipoNombre) || isBlank(txtEquipoEstadio) || isBlank(txtEquipoAforo) ||
                isBlank(txtEquipoFundacion) || cmbEquipoDepartamento.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos del equipo.");
            return;
        }
        try {
            int capacity = Integer.parseInt(txtEquipoAforo.getText().trim());
            int foundationYear = Integer.parseInt(txtEquipoFundacion.getText().trim());

            Map<String, Object> payload = new HashMap<>();
            payload.put("name", txtEquipoNombre.getText().trim());
            payload.put("stadium", txtEquipoEstadio.getText().trim());
            payload.put("capacity", capacity);
            payload.put("foundationYear", foundationYear);
            Map<String, Object> department = cmbEquipoDepartamento.getSelectionModel().getSelectedItem();
            payload.put("departmentId", asLong(department.get("id")));

            api.createTeam(payload);
            onRefresh();
            showInfo("Equipo creado correctamente.");
            clearEquipoForm();
        } catch (NumberFormatException nfe) {
            showError("Aforo y año de fundación deben ser números.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarEquipo() {
        if (selectedTeamId == null) {
            showError("Selecciona un equipo para actualizar.");
            return;
        }
        if (isBlank(txtEquipoNombre) || isBlank(txtEquipoEstadio) || isBlank(txtEquipoAforo) ||
                isBlank(txtEquipoFundacion) || cmbEquipoDepartamento.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos del equipo.");
            return;
        }
        try {
            int capacity = Integer.parseInt(txtEquipoAforo.getText().trim());
            int foundationYear = Integer.parseInt(txtEquipoFundacion.getText().trim());

            Map<String, Object> payload = new HashMap<>();
            payload.put("name", txtEquipoNombre.getText().trim());
            payload.put("stadium", txtEquipoEstadio.getText().trim());
            payload.put("capacity", capacity);
            payload.put("foundationYear", foundationYear);
            Map<String, Object> department = cmbEquipoDepartamento.getSelectionModel().getSelectedItem();
            payload.put("departmentId", asLong(department.get("id")));

            api.updateTeam(selectedTeamId, payload);
            onRefresh();
            showInfo("Equipo actualizado correctamente.");
        } catch (NumberFormatException nfe) {
            showError("Aforo y año de fundación deben ser números.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarEquipo() {
        if (selectedTeamId == null) {
            showError("Selecciona un equipo para eliminar.");
            return;
        }
        try {
            api.deleteTeam(selectedTeamId);
            onRefresh();
            showInfo("Equipo eliminado.");
            clearEquipoForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearDepartamento() {
        if (isBlank(txtDepartamentoNombre)) {
            showError("Ingresa el nombre del departamento.");
            return;
        }
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("name", txtDepartamentoNombre.getText().trim());
            api.createDepartment(payload);
            onRefresh();
            showInfo("Departamento creado correctamente.");
            clearDepartamentoForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarDepartamento() {
        if (selectedDepartamentoId == null) {
            showError("Selecciona un departamento para actualizar.");
            return;
        }
        if (isBlank(txtDepartamentoNombre)) {
            showError("Ingresa el nombre del departamento.");
            return;
        }
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("name", txtDepartamentoNombre.getText().trim());
            api.updateDepartment(selectedDepartamentoId, payload);
            onRefresh();
            showInfo("Departamento actualizado correctamente.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarDepartamento() {
        if (selectedDepartamentoId == null) {
            showError("Selecciona un departamento para eliminar.");
            return;
        }
        try {
            api.deleteDepartment(selectedDepartamentoId);
            onRefresh();
            showInfo("Departamento eliminado.");
            clearDepartamentoForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearMunicipio() {
        if (isBlank(txtMunicipioNombre) || cmbMunicipioDepartamento.getSelectionModel().getSelectedItem() == null) {
            showError("Ingresa el nombre y selecciona el departamento del municipio.");
            return;
        }
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("name", txtMunicipioNombre.getText().trim());
            Map<String, Object> department = cmbMunicipioDepartamento.getSelectionModel().getSelectedItem();
            payload.put("departmentId", asLong(department.get("id")));
            api.createMunicipality(payload);
            onRefresh();
            showInfo("Municipio creado correctamente.");
            clearMunicipioForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarMunicipio() {
        if (selectedMunicipioId == null) {
            showError("Selecciona un municipio para actualizar.");
            return;
        }
        if (isBlank(txtMunicipioNombre) || cmbMunicipioDepartamento.getSelectionModel().getSelectedItem() == null) {
            showError("Ingresa el nombre y selecciona el departamento del municipio.");
            return;
        }
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("name", txtMunicipioNombre.getText().trim());
            Map<String, Object> department = cmbMunicipioDepartamento.getSelectionModel().getSelectedItem();
            payload.put("departmentId", asLong(department.get("id")));
            api.updateMunicipality(selectedMunicipioId, payload);
            onRefresh();
            showInfo("Municipio actualizado correctamente.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarMunicipio() {
        if (selectedMunicipioId == null) {
            showError("Selecciona un municipio para eliminar.");
            return;
        }
        try {
            api.deleteMunicipality(selectedMunicipioId);
            onRefresh();
            showInfo("Municipio eliminado.");
            clearMunicipioForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearJugador() {
        if (isBlank(txtJugadorNombre1) || isBlank(txtJugadorApellido1) || isBlank(txtJugadorApellido2) ||
                isBlank(txtJugadorCorreo) ||
                dateJugadorNacimiento.getValue() == null ||
                cmbJugadorPosicion.getSelectionModel().getSelectedItem() == null ||
                cmbJugadorEquipo.getSelectionModel().getSelectedItem() == null ||
                cmbJugadorDepartamento.getSelectionModel().getSelectedItem() == null ||
                cmbJugadorMunicipio.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos obligatorios del jugador.");
            return;
        }

        Map<String, Object> team = cmbJugadorEquipo.getSelectionModel().getSelectedItem();
        Map<String, Object> municipality = cmbJugadorMunicipio.getSelectionModel().getSelectedItem();
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("firstName", txtJugadorNombre1.getText().trim());
            payload.put("secondName", txtJugadorNombre2.getText().trim());
            payload.put("lastName1", txtJugadorApellido1.getText().trim());
            payload.put("lastName2", txtJugadorApellido2.getText().trim());
            payload.put("email", txtJugadorCorreo.getText().trim());
            payload.put("birthDate", dateJugadorNacimiento.getValue().format(dateFormatter));
            payload.put("position", cmbJugadorPosicion.getSelectionModel().getSelectedItem());
            payload.put("teamId", asLong(team.get("id")));
            payload.put("municipalityId", asLong(municipality.get("id")));

            api.createPlayer(payload);
            onRefresh();
            showInfo("Jugador registrado correctamente.");
            clearJugadorForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarJugador() {
        if (selectedPlayerId == null) {
            showError("Selecciona un jugador para actualizar.");
            return;
        }
        if (isBlank(txtJugadorNombre1) || isBlank(txtJugadorApellido1) || isBlank(txtJugadorApellido2) ||
                isBlank(txtJugadorCorreo) ||
                dateJugadorNacimiento.getValue() == null ||
                cmbJugadorPosicion.getSelectionModel().getSelectedItem() == null ||
                cmbJugadorEquipo.getSelectionModel().getSelectedItem() == null ||
                cmbJugadorDepartamento.getSelectionModel().getSelectedItem() == null ||
                cmbJugadorMunicipio.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos obligatorios del jugador.");
            return;
        }

        Map<String, Object> team = cmbJugadorEquipo.getSelectionModel().getSelectedItem();
        Map<String, Object> municipality = cmbJugadorMunicipio.getSelectionModel().getSelectedItem();
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("firstName", txtJugadorNombre1.getText().trim());
            payload.put("secondName", txtJugadorNombre2.getText().trim());
            payload.put("lastName1", txtJugadorApellido1.getText().trim());
            payload.put("lastName2", txtJugadorApellido2.getText().trim());
            payload.put("email", txtJugadorCorreo.getText().trim());
            payload.put("birthDate", dateJugadorNacimiento.getValue().format(dateFormatter));
            payload.put("position", cmbJugadorPosicion.getSelectionModel().getSelectedItem());
            payload.put("teamId", asLong(team.get("id")));
            payload.put("municipalityId", asLong(municipality.get("id")));

            api.updatePlayer(selectedPlayerId, payload);
            onRefresh();
            showInfo("Jugador actualizado correctamente.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarJugador() {
        if (selectedPlayerId == null) {
            showError("Selecciona un jugador para eliminar.");
            return;
        }
        try {
            api.deletePlayer(selectedPlayerId);
            onRefresh();
            showInfo("Jugador eliminado.");
            selectedPlayerId = null;
            clearJugadorForm();
            cmbJugadorSeleccionCorreo.getSelectionModel().clearSelection();
            correosJugador.clear();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onAgregarCorreoJugador() {
        Map<String, Object> player = cmbJugadorSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (player == null) {
            showError("Selecciona un jugador.");
            return;
        }
        if (isBlank(txtCorreoJugadorNuevo)) {
            showError("Ingresa el correo a registrar.");
            return;
        }
        try {
            Long playerId = asLong(player.get("id"));
            Map<String, Object> payload = Map.of("email", txtCorreoJugadorNuevo.getText().trim());
            api.createPlayerEmail(playerId, payload);
            txtCorreoJugadorNuevo.clear();
            loadPlayerEmailsFromSelection();
            showInfo("Correo agregado al jugador.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarCorreoJugador() {
        Map<String, Object> player = cmbJugadorSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (player == null || selectedPlayerEmailId == null) {
            showError("Selecciona un jugador y un correo para actualizar.");
            return;
        }
        if (isBlank(txtCorreoJugadorNuevo)) {
            showError("Ingresa el correo a actualizar.");
            return;
        }
        try {
            Long playerId = asLong(player.get("id"));
            Map<String, Object> payload = Map.of("email", txtCorreoJugadorNuevo.getText().trim());
            api.updatePlayerEmail(playerId, selectedPlayerEmailId, payload);
            loadPlayerEmailsFromSelection();
            showInfo("Correo actualizado.");
            clearCorreoJugadorForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarCorreoJugador() {
        Map<String, Object> player = cmbJugadorSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (player == null || selectedPlayerEmailId == null) {
            showError("Selecciona un jugador y un correo para eliminar.");
            return;
        }
        try {
            Long playerId = asLong(player.get("id"));
            api.deletePlayerEmail(playerId, selectedPlayerEmailId);
            loadPlayerEmailsFromSelection();
            showInfo("Correo eliminado.");
            clearCorreoJugadorForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearPartido() {
        if (datePartidoFecha.getValue() == null ||
                cmbPartidoEquipoCasa.getSelectionModel().getSelectedItem() == null ||
                cmbPartidoEquipoFuera.getSelectionModel().getSelectedItem() == null ||
                isBlank(txtPartidoGolesCasa) ||
                isBlank(txtPartidoGolesFuera)) {
            showError("Completa todos los campos del partido.");
            return;
        }

        Map<String, Object> homeTeam = cmbPartidoEquipoCasa.getSelectionModel().getSelectedItem();
        Map<String, Object> awayTeam = cmbPartidoEquipoFuera.getSelectionModel().getSelectedItem();
        if (Objects.equals(asLong(homeTeam.get("id")), asLong(awayTeam.get("id")))) {
            showError("Los equipos casa y fuera deben ser distintos.");
            return;
        }

        try {
            int goalsHome = Integer.parseInt(txtPartidoGolesCasa.getText().trim());
            int goalsAway = Integer.parseInt(txtPartidoGolesFuera.getText().trim());

            Map<String, Object> payload = new HashMap<>();
            payload.put("date", datePartidoFecha.getValue().format(dateFormatter));
            payload.put("homeTeamId", asLong(homeTeam.get("id")));
            payload.put("awayTeamId", asLong(awayTeam.get("id")));
            payload.put("homeGoals", goalsHome);
            payload.put("awayGoals", goalsAway);

            api.createMatch(payload);
            onRefresh();
            showInfo("Partido registrado.");
            clearPartidoForm();
        } catch (NumberFormatException nfe) {
            showError("Los goles deben ser valores numéricos.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarPartido() {
        if (selectedMatchId == null) {
            showError("Selecciona un partido para actualizar.");
            return;
        }
        if (datePartidoFecha.getValue() == null ||
                cmbPartidoEquipoCasa.getSelectionModel().getSelectedItem() == null ||
                cmbPartidoEquipoFuera.getSelectionModel().getSelectedItem() == null ||
                isBlank(txtPartidoGolesCasa) ||
                isBlank(txtPartidoGolesFuera)) {
            showError("Completa todos los campos del partido.");
            return;
        }

        Map<String, Object> homeTeam = cmbPartidoEquipoCasa.getSelectionModel().getSelectedItem();
        Map<String, Object> awayTeam = cmbPartidoEquipoFuera.getSelectionModel().getSelectedItem();
        if (Objects.equals(asLong(homeTeam.get("id")), asLong(awayTeam.get("id")))) {
            showError("Los equipos casa y fuera deben ser distintos.");
            return;
        }

        try {
            int goalsHome = Integer.parseInt(txtPartidoGolesCasa.getText().trim());
            int goalsAway = Integer.parseInt(txtPartidoGolesFuera.getText().trim());

            Map<String, Object> payload = new HashMap<>();
            payload.put("date", datePartidoFecha.getValue().format(dateFormatter));
            payload.put("homeTeamId", asLong(homeTeam.get("id")));
            payload.put("awayTeamId", asLong(awayTeam.get("id")));
            payload.put("homeGoals", goalsHome);
            payload.put("awayGoals", goalsAway);

            api.updateMatch(selectedMatchId, payload);
            onRefresh();
            showInfo("Partido actualizado correctamente.");
        } catch (NumberFormatException nfe) {
            showError("Los goles deben ser valores numéricos.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarPartido() {
        if (selectedMatchId == null) {
            showError("Selecciona un partido para eliminar.");
            return;
        }
        try {
            api.deleteMatch(selectedMatchId);
            onRefresh();
            showInfo("Partido eliminado.");
            clearPartidoForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearGol() {
        Map<String, Object> match = cmbGolPartido.getSelectionModel().getSelectedItem();
        Map<String, Object> player = cmbGolJugador.getSelectionModel().getSelectedItem();
        if (match == null || player == null || isBlank(txtGolMinuto)) {
            showError("Selecciona partido, jugador y minuto del gol.");
            return;
        }

        try {
            int minute = Integer.parseInt(txtGolMinuto.getText().trim());
            Map<String, Object> payload = new HashMap<>();
            payload.put("playerId", asLong(player.get("id")));
            payload.put("minute", minute);
            payload.put("description", txtGolDescripcion.getText().trim());

            Long matchId = asLong(match.get("id"));
            api.createGoal(matchId, payload);
            showInfo("Gol registrado.");
            loadGoals();
            clearGolForm();
        } catch (NumberFormatException nfe) {
            showError("El minuto debe ser numérico.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarGol() {
        if (selectedGoalId == null) {
            showError("Selecciona un gol para actualizar.");
            return;
        }
        Map<String, Object> match = cmbGolPartido.getSelectionModel().getSelectedItem();
        Map<String, Object> player = cmbGolJugador.getSelectionModel().getSelectedItem();
        if (match == null || player == null || isBlank(txtGolMinuto)) {
            showError("Selecciona partido, jugador y minuto del gol.");
            return;
        }
        try {
            int minute = Integer.parseInt(txtGolMinuto.getText().trim());
            Map<String, Object> payload = new HashMap<>();
            payload.put("playerId", asLong(player.get("id")));
            payload.put("minute", minute);
            payload.put("description", txtGolDescripcion.getText().trim());

            Long matchId = asLong(match.get("id"));
            api.updateGoal(matchId, selectedGoalId, payload);
            loadGoals();
            showInfo("Gol actualizado.");
            clearGolForm();
        } catch (NumberFormatException nfe) {
            showError("El minuto debe ser numérico.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarGol() {
        if (selectedGoalId == null) {
            showError("Selecciona un gol para eliminar.");
            return;
        }
        Long matchId = selectedGoalMatchId;
        if (matchId == null) {
            Map<String, Object> match = cmbGolPartido.getSelectionModel().getSelectedItem();
            matchId = match != null ? asLong(match.get("id")) : null;
        }
        if (matchId == null) {
            showError("No se pudo determinar el partido del gol seleccionado.");
            return;
        }
        try {
            api.deleteGoal(matchId, selectedGoalId);
            loadGoals();
            showInfo("Gol eliminado.");
            clearGolForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearPresidente() {
        if (isBlank(txtPresidenteDpi) || isBlank(txtPresidenteNombre1) ||
                isBlank(txtPresidenteApellido1) || isBlank(txtPresidenteApellido2) ||
                isBlank(txtPresidenteCorreo) ||
                datePresidenteNacimiento.getValue() == null || isBlank(txtPresidenteAnio) ||
                cmbPresidenteEquipo.getSelectionModel().getSelectedItem() == null ||
                cmbPresidenteDepartamento.getSelectionModel().getSelectedItem() == null ||
                cmbPresidenteMunicipio.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos obligatorios del presidente.");
            return;
        }

        try {
            int electionYear = Integer.parseInt(txtPresidenteAnio.getText().trim());
            Map<String, Object> team = cmbPresidenteEquipo.getSelectionModel().getSelectedItem();
            Map<String, Object> municipality = cmbPresidenteMunicipio.getSelectionModel().getSelectedItem();

            Map<String, Object> payload = new HashMap<>();
            payload.put("dpi", txtPresidenteDpi.getText().trim());
            payload.put("firstName", txtPresidenteNombre1.getText().trim());
            payload.put("secondName", txtPresidenteNombre2.getText().trim());
            payload.put("thirdName", txtPresidenteNombre3.getText().trim());
            payload.put("lastName1", txtPresidenteApellido1.getText().trim());
            payload.put("lastName2", txtPresidenteApellido2.getText().trim());
            payload.put("email", txtPresidenteCorreo.getText().trim());
            payload.put("birthDate", datePresidenteNacimiento.getValue().format(dateFormatter));
            payload.put("electionYear", electionYear);
            payload.put("team", Map.of("id", asLong(team.get("id"))));
            payload.put("municipalityId", asLong(municipality.get("id")));

            api.createPresident(payload);
            onRefresh();
            showInfo("Presidente creado correctamente.");
            clearPresidenteForm();
        } catch (NumberFormatException nfe) {
            showError("El año elegido debe ser numérico.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarPresidente() {
        if (selectedPresidentDpi == null) {
            showError("Selecciona un presidente para actualizar.");
            return;
        }
        String dpiIngresado = txtPresidenteDpi.getText().trim();
        if (!Objects.equals(selectedPresidentDpi, dpiIngresado)) {
            showError("El DPI no se puede modificar.");
            return;
        }
        if (isBlank(txtPresidenteNombre1) ||
                isBlank(txtPresidenteApellido1) || isBlank(txtPresidenteApellido2) ||
                isBlank(txtPresidenteCorreo) ||
                datePresidenteNacimiento.getValue() == null || isBlank(txtPresidenteAnio) ||
                cmbPresidenteEquipo.getSelectionModel().getSelectedItem() == null ||
                cmbPresidenteDepartamento.getSelectionModel().getSelectedItem() == null ||
                cmbPresidenteMunicipio.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos obligatorios del presidente.");
            return;
        }
        try {
            int electionYear = Integer.parseInt(txtPresidenteAnio.getText().trim());
            Map<String, Object> team = cmbPresidenteEquipo.getSelectionModel().getSelectedItem();
            Map<String, Object> municipality = cmbPresidenteMunicipio.getSelectionModel().getSelectedItem();

            Map<String, Object> payload = new HashMap<>();
            payload.put("firstName", txtPresidenteNombre1.getText().trim());
            payload.put("secondName", txtPresidenteNombre2.getText().trim());
            payload.put("thirdName", txtPresidenteNombre3.getText().trim());
            payload.put("lastName1", txtPresidenteApellido1.getText().trim());
            payload.put("lastName2", txtPresidenteApellido2.getText().trim());
            payload.put("email", txtPresidenteCorreo.getText().trim());
            payload.put("birthDate", datePresidenteNacimiento.getValue().format(dateFormatter));
            payload.put("electionYear", electionYear);
            payload.put("team", Map.of("id", asLong(team.get("id"))));
            payload.put("municipalityId", asLong(municipality.get("id")));

            api.updatePresident(selectedPresidentDpi, payload);
            onRefresh();
            showInfo("Presidente actualizado correctamente.");
        } catch (NumberFormatException nfe) {
            showError("El año elegido debe ser numérico.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarPresidente() {
        if (selectedPresidentDpi == null) {
            showError("Selecciona un presidente para eliminar.");
            return;
        }
        try {
            api.deletePresident(selectedPresidentDpi);
            onRefresh();
            showInfo("Presidente eliminado.");
            clearPresidenteForm();
            cmbPresidenteSeleccionCorreo.getSelectionModel().clearSelection();
            correosPresidente.clear();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onAgregarCorreoPresidente() {
        Map<String, Object> president = cmbPresidenteSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (president == null) {
            showError("Selecciona un presidente.");
            return;
        }
        if (isBlank(txtCorreoPresidenteNuevo)) {
            showError("Ingresa el correo para registrar.");
            return;
        }
        try {
            String dpi = stringValue(president.get("dpi"));
            Map<String, Object> payload = Map.of("email", txtCorreoPresidenteNuevo.getText().trim());
            api.createPresidentEmail(dpi, payload);
            txtCorreoPresidenteNuevo.clear();
            loadPresidentEmailsFromSelection();
            showInfo("Correo agregado al presidente.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onActualizarCorreoPresidente() {
        Map<String, Object> president = cmbPresidenteSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (president == null || selectedPresidentEmailId == null) {
            showError("Selecciona un presidente y un correo para actualizar.");
            return;
        }
        if (isBlank(txtCorreoPresidenteNuevo)) {
            showError("Ingresa el correo para actualizar.");
            return;
        }
        try {
            String dpi = stringValue(president.get("dpi"));
            Map<String, Object> payload = Map.of("email", txtCorreoPresidenteNuevo.getText().trim());
            api.updatePresidentEmail(dpi, selectedPresidentEmailId, payload);
            loadPresidentEmailsFromSelection();
            showInfo("Correo de presidente actualizado.");
            clearCorreoPresidenteForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onEliminarCorreoPresidente() {
        Map<String, Object> president = cmbPresidenteSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (president == null || selectedPresidentEmailId == null) {
            showError("Selecciona un presidente y un correo para eliminar.");
            return;
        }
        try {
            String dpi = stringValue(president.get("dpi"));
            api.deletePresidentEmail(dpi, selectedPresidentEmailId);
            loadPresidentEmailsFromSelection();
            showInfo("Correo de presidente eliminado.");
            clearCorreoPresidenteForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private boolean isBlank(TextField field) {
        return field.getText() == null || field.getText().trim().isEmpty();
    }

    private String stringValue(Object value) {
        return value == null ? "" : value.toString();
    }

    private Long asLong(Object value) {
        if (value == null) return null;
        if (value instanceof Number number) {
            return number.longValue();
        }
        try {
            return Long.parseLong(value.toString());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private Map<String, Object> castMap(Object value) {
        if (value instanceof Map<?, ?> map) {
            Map<String, Object> result = new HashMap<>();
            map.forEach((k, v) -> result.put(String.valueOf(k), v));
            return result;
        }
        return null;
    }

    private String formatDate(Object value) {
        if (value == null) return "";
        if (value instanceof LocalDate localDate) {
            return localDate.format(dateFormatter);
        }
        String text = value.toString();
        if (text.isBlank()) return "";
        try {
            LocalDate parsed = LocalDate.parse(text);
            return parsed.format(dateFormatter);
        } catch (Exception e) {
            return text;
        }
    }

    private String numberToString(Object value) {
        if (value == null) return "";
        if (value instanceof Number number) {
            if (number.doubleValue() == number.longValue()) {
                return String.valueOf(number.longValue());
            }
            return number.toString();
        }
        return value.toString();
    }

    private String joinStrings(String... values) {
        return Arrays.stream(values)
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.joining(" "));
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.setHeaderText("Error");
        alert.showAndWait();
    }

    private void showInfo(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.setHeaderText("Operación exitosa");
        alert.showAndWait();
    }

    private void updateMunicipalitiesForDepartment(Long departmentId,
                                                   ObservableList<Map<String, Object>> targetList,
                                                   ComboBox<Map<String, Object>> municipalityCombo,
                                                   Long selectedMunicipalityId) {
        List<Map<String, Object>> source = municipiosPorDepartamento.getOrDefault(departmentId, List.of());
        List<Map<String, Object>> copies = new ArrayList<>(source.size());
        for (Map<String, Object> item : source) {
            copies.add(copyMap(item));
        }
        targetList.setAll(copies);
        if (selectedMunicipalityId != null) {
            selectComboItemById(municipalityCombo, selectedMunicipalityId);
        } else {
            municipalityCombo.getSelectionModel().clearSelection();
        }
    }

    private Map<String, Object> copyMap(Object value) {
        Map<String, Object> copy = new HashMap<>();
        if (value instanceof Map<?, ?> map) {
            map.forEach((k, v) -> copy.put(String.valueOf(k), v));
        }
        return copy;
    }
}
