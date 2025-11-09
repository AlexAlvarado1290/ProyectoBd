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
    @FXML private TextField txtEquipoDepartamento;

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
    @FXML private TextField txtJugadorMunicipio;
    @FXML private DatePicker dateJugadorNacimiento;
    @FXML private ComboBox<String> cmbJugadorPosicion;
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

    @FXML private TextField txtPresidenteDpi;
    @FXML private TextField txtPresidenteNombre1;
    @FXML private TextField txtPresidenteNombre2;
    @FXML private TextField txtPresidenteNombre3;
    @FXML private TextField txtPresidenteApellido1;
    @FXML private TextField txtPresidenteApellido2;
    @FXML private TextField txtPresidenteCorreo;
    @FXML private TextField txtPresidenteMunicipio;
    @FXML private DatePicker datePresidenteNacimiento;
    @FXML private TextField txtPresidenteAnio;
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

    private final ObservableList<Map<String, Object>> equipoComboItems = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> jugadorComboItems = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> partidoComboItems = FXCollections.observableArrayList();
    private final ObservableList<Map<String, Object>> presidenteComboItems = FXCollections.observableArrayList();

    private final Map<Long, Map<String, Object>> teamById = new HashMap<>();
    private final Map<Long, Map<String, Object>> playerById = new HashMap<>();
    private final Map<Long, Map<String, Object>> matchById = new HashMap<>();
    private final Map<String, Map<String, Object>> presidentByDpi = new HashMap<>();

    @FXML
    public void initialize() {
        setupTables();
        setupComboBoxes();

        cmbJugadorSeleccionCorreo.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> loadPlayerEmailsFromSelection());
        cmbPresidenteSeleccionCorreo.getSelectionModel().selectedItemProperty()
                .addListener((obs, oldValue, newValue) -> loadPresidentEmailsFromSelection());

        onRefresh();
    }

    private void setupTables() {
        bindColumn(colEquipoId, "id");
        bindColumn(colEquipoNombre, "name");
        bindColumn(colEquipoEstadio, "stadium");
        bindColumn(colEquipoAforo, "capacity");
        bindColumn(colEquipoFundacion, "foundationYear");
        bindColumn(colEquipoDepartamento, "department");
        tblEquipos.setItems(equipos);

        bindColumn(colJugadorId, "id");
        bindColumn(colJugadorNombre, "fullName");
        bindColumn(colJugadorApellidos, "lastNames");
        bindColumn(colJugadorCorreo, "email");
        bindColumn(colJugadorMunicipio, "municipality");
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
        bindColumn(colPresidenteMunicipio, "municipality");
        bindColumn(colPresidenteFecha, "birthDate");
        bindColumn(colPresidenteAnio, "electionYear");
        bindColumn(colPresidenteEquipo, "teamName");
        tblPresidentes.setItems(presidentes);

        bindColumn(colCorreoPresidenteId, "id");
        bindColumn(colCorreoPresidenteCorreo, "email");
        tblCorreosPresidente.setItems(correosPresidente);
    }

    private void bindColumn(TableColumn<Map<String, Object>, String> column, String key) {
        column.setCellValueFactory(data ->
                new ReadOnlyStringWrapper(stringValue(data.getValue().get(key))));
    }

    private void setupComboBoxes() {
        configureComboBox(cmbJugadorEquipo, "name");
        configureComboBox(cmbPartidoEquipoCasa, "name");
        configureComboBox(cmbPartidoEquipoFuera, "name");
        configureComboBox(cmbPresidenteEquipo, "name");

        configureComboBox(cmbGolPartido, "label");
        configureComboBox(cmbGolJugador, "fullName");
        configureComboBox(cmbJugadorSeleccionCorreo, "fullName");
        configureComboBox(cmbPresidenteSeleccionCorreo, "names");

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
            team.put("department", stringValue(team.get("department")));
        }

        cmbJugadorEquipo.getSelectionModel().clearSelection();
        cmbPartidoEquipoCasa.getSelectionModel().clearSelection();
        cmbPartidoEquipoFuera.getSelectionModel().clearSelection();
        cmbPresidenteEquipo.getSelectionModel().clearSelection();
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
            player.put("municipality", stringValue(player.get("municipality")));
            player.put("birthDate", formatDate(player.get("birthDate")));
            player.put("position", stringValue(player.get("position")));

            Long teamId = asLong(player.get("teamId"));
            String teamName = Optional.ofNullable(teamById.get(teamId))
                    .map(t -> stringValue(t.get("name")))
                    .orElse("");
            player.put("teamName", teamName);
        }

        cmbJugadorPosicion.getSelectionModel().clearSelection();
        cmbJugadorEquipo.getSelectionModel().clearSelection();
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

        cmbPartidoEquipoCasa.getSelectionModel().clearSelection();
        cmbPartidoEquipoFuera.getSelectionModel().clearSelection();
        datePartidoFecha.setValue(null);
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

        cmbGolPartido.getSelectionModel().clearSelection();
        cmbGolJugador.getSelectionModel().clearSelection();
        txtGolMinuto.clear();
        txtGolDescripcion.clear();
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
            president.put("municipality", stringValue(president.get("municipality")));
            president.put("birthDate", formatDate(president.get("birthDate")));
            president.put("electionYear", numberToString(president.get("electionYear")));

            Map<String, Object> team = castMap(president.get("team"));
            String teamName = team != null ? stringValue(team.get("name")) : "";
            president.put("teamName", teamName);
        }

        cmbPresidenteEquipo.getSelectionModel().clearSelection();
        cmbPresidenteSeleccionCorreo.getSelectionModel().clearSelection();
        txtPresidenteDpi.clear();
        txtPresidenteNombre1.clear();
        txtPresidenteNombre2.clear();
        txtPresidenteNombre3.clear();
        txtPresidenteApellido1.clear();
        txtPresidenteApellido2.clear();
        txtPresidenteCorreo.clear();
        txtPresidenteMunicipio.clear();
        datePresidenteNacimiento.setValue(null);
        txtPresidenteAnio.clear();
    }

    private void loadPlayerEmailsFromSelection() {
        Map<String, Object> player = cmbJugadorSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (player == null) {
            correosJugador.clear();
            return;
        }
        try {
            Long playerId = asLong(player.get("id"));
            String json = api.getPlayerEmails(playerId);
            List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
            correosJugador.setAll(list);
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void loadPresidentEmailsFromSelection() {
        Map<String, Object> president = cmbPresidenteSeleccionCorreo.getSelectionModel().getSelectedItem();
        if (president == null) {
            correosPresidente.clear();
            return;
        }
        try {
            String dpi = stringValue(president.get("dpi"));
            String json = api.getPresidentEmails(dpi);
            List<Map<String, Object>> list = mapper.readValue(json, new TypeReference<>() {});
            correosPresidente.setAll(list);
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearEquipo() {
        if (isBlank(txtEquipoNombre) || isBlank(txtEquipoEstadio) || isBlank(txtEquipoAforo) ||
                isBlank(txtEquipoFundacion) || isBlank(txtEquipoDepartamento)) {
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
            payload.put("department", txtEquipoDepartamento.getText().trim());

            api.createTeam(payload);
            onRefresh();
            showInfo("Equipo creado correctamente.");
            txtEquipoNombre.clear();
            txtEquipoEstadio.clear();
            txtEquipoAforo.clear();
            txtEquipoFundacion.clear();
            txtEquipoDepartamento.clear();
        } catch (NumberFormatException nfe) {
            showError("Aforo y año de fundación deben ser números.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearJugador() {
        if (isBlank(txtJugadorNombre1) || isBlank(txtJugadorApellido1) || isBlank(txtJugadorApellido2) ||
                isBlank(txtJugadorCorreo) || isBlank(txtJugadorMunicipio) ||
                dateJugadorNacimiento.getValue() == null ||
                cmbJugadorPosicion.getSelectionModel().getSelectedItem() == null ||
                cmbJugadorEquipo.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos obligatorios del jugador.");
            return;
        }

        Map<String, Object> team = cmbJugadorEquipo.getSelectionModel().getSelectedItem();
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("firstName", txtJugadorNombre1.getText().trim());
            payload.put("secondName", txtJugadorNombre2.getText().trim());
            payload.put("lastName1", txtJugadorApellido1.getText().trim());
            payload.put("lastName2", txtJugadorApellido2.getText().trim());
            payload.put("email", txtJugadorCorreo.getText().trim());
            payload.put("municipality", txtJugadorMunicipio.getText().trim());
            payload.put("birthDate", dateJugadorNacimiento.getValue().format(dateFormatter));
            payload.put("position", cmbJugadorPosicion.getSelectionModel().getSelectedItem());
            payload.put("teamId", asLong(team.get("id")));

            api.createPlayer(payload);
            onRefresh();
            showInfo("Jugador registrado correctamente.");
            clearJugadorForm();
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    private void clearJugadorForm() {
        txtJugadorNombre1.clear();
        txtJugadorNombre2.clear();
        txtJugadorApellido1.clear();
        txtJugadorApellido2.clear();
        txtJugadorCorreo.clear();
        txtJugadorMunicipio.clear();
        dateJugadorNacimiento.setValue(null);
        cmbJugadorPosicion.getSelectionModel().clearSelection();
        cmbJugadorEquipo.getSelectionModel().clearSelection();
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
            txtPartidoGolesCasa.clear();
            txtPartidoGolesFuera.clear();
            datePartidoFecha.setValue(null);
            cmbPartidoEquipoCasa.getSelectionModel().clearSelection();
            cmbPartidoEquipoFuera.getSelectionModel().clearSelection();
        } catch (NumberFormatException nfe) {
            showError("Los goles deben ser valores numéricos.");
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
            txtGolMinuto.clear();
            txtGolDescripcion.clear();
            cmbGolPartido.getSelectionModel().clearSelection();
            cmbGolJugador.getSelectionModel().clearSelection();
        } catch (NumberFormatException nfe) {
            showError("El minuto debe ser numérico.");
        } catch (Exception e) {
            showError(e.getMessage());
        }
    }

    @FXML
    public void onCrearPresidente() {
        if (isBlank(txtPresidenteDpi) || isBlank(txtPresidenteNombre1) ||
                isBlank(txtPresidenteApellido1) || isBlank(txtPresidenteApellido2) ||
                isBlank(txtPresidenteCorreo) || isBlank(txtPresidenteMunicipio) ||
                datePresidenteNacimiento.getValue() == null || isBlank(txtPresidenteAnio) ||
                cmbPresidenteEquipo.getSelectionModel().getSelectedItem() == null) {
            showError("Completa todos los campos obligatorios del presidente.");
            return;
        }

        try {
            int electionYear = Integer.parseInt(txtPresidenteAnio.getText().trim());
            Map<String, Object> team = cmbPresidenteEquipo.getSelectionModel().getSelectedItem();

            Map<String, Object> payload = new HashMap<>();
            payload.put("dpi", txtPresidenteDpi.getText().trim());
            payload.put("firstName", txtPresidenteNombre1.getText().trim());
            payload.put("secondName", txtPresidenteNombre2.getText().trim());
            payload.put("thirdName", txtPresidenteNombre3.getText().trim());
            payload.put("lastName1", txtPresidenteApellido1.getText().trim());
            payload.put("lastName2", txtPresidenteApellido2.getText().trim());
            payload.put("email", txtPresidenteCorreo.getText().trim());
            payload.put("municipality", txtPresidenteMunicipio.getText().trim());
            payload.put("birthDate", datePresidenteNacimiento.getValue().format(dateFormatter));
            payload.put("electionYear", electionYear);
            payload.put("team", Map.of("id", asLong(team.get("id"))));

            api.createPresident(payload);
            onRefresh();
            showInfo("Presidente creado correctamente.");
        } catch (NumberFormatException nfe) {
            showError("El año elegido debe ser numérico.");
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
}
