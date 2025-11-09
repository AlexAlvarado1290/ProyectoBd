CREATE TABLE team (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    stadium VARCHAR(100) NOT NULL,
    capacity INTEGER NOT NULL,
    foundation_year INTEGER NOT NULL,
    department VARCHAR(50) NOT NULL
);

CREATE TABLE president (
    dpi VARCHAR(15) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    second_name VARCHAR(50),
    third_name VARCHAR(50),
    last_name1 VARCHAR(50) NOT NULL,
    last_name2 VARCHAR(50),
    birth_date DATE NOT NULL,
    email VARCHAR(100),
    municipality VARCHAR(50) NOT NULL,
    election_year INTEGER NOT NULL,
    team_id BIGINT UNIQUE,
    CONSTRAINT fk_president_team FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE president_email (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    president_dpi VARCHAR(15) NOT NULL,
    CONSTRAINT fk_president_email FOREIGN KEY (president_dpi) REFERENCES president (dpi)
);

CREATE TABLE player (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    second_name VARCHAR(50),
    last_name1 VARCHAR(50) NOT NULL,
    last_name2 VARCHAR(50),
    email VARCHAR(100),
    municipality VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    position VARCHAR(20) NOT NULL CHECK (position IN ('Portero','Defensa','Delantero','Centrocampista')),
    team_id BIGINT NOT NULL,
    CONSTRAINT fk_player_team FOREIGN KEY (team_id) REFERENCES team (id)
);

CREATE TABLE player_email (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(100) NOT NULL,
    player_id BIGINT NOT NULL,
    CONSTRAINT fk_player_email FOREIGN KEY (player_id) REFERENCES player (id)
);

CREATE TABLE matches (
    id BIGSERIAL PRIMARY KEY,
    date DATE NOT NULL,
    home_goals INTEGER DEFAULT 0,
    away_goals INTEGER DEFAULT 0,
    home_team_id BIGINT NOT NULL,
    away_team_id BIGINT NOT NULL,
    CONSTRAINT fk_matches_home_team FOREIGN KEY (home_team_id) REFERENCES team (id),
    CONSTRAINT fk_matches_away_team FOREIGN KEY (away_team_id) REFERENCES team (id)
);

CREATE TABLE goal (
    id BIGSERIAL PRIMARY KEY,
    minute INTEGER NOT NULL,
    description VARCHAR(255),
    player_id BIGINT NOT NULL,
    match_id BIGINT NOT NULL,
    CONSTRAINT fk_goal_player FOREIGN KEY (player_id) REFERENCES player (id),
    CONSTRAINT fk_goal_match FOREIGN KEY (match_id) REFERENCES matches (id)
);

-- Equipos
INSERT INTO team (name, stadium, capacity, foundation_year, department)
VALUES
    ('Real Mixco', 'Estadio Santo Domingo', 15000, 1970, 'Guatemala'),
    ('Xelajú MC', 'Estadio Mario Camposeco', 11000, 1942, 'Quetzaltenango'),
    ('Cobán Imperial', 'Estadio Verapaz', 12000, 1936, 'Alta Verapaz');

-- Presidentes
INSERT INTO president (dpi, first_name, second_name, third_name, last_name1, last_name2, birth_date, email, municipality, election_year, team_id)
VALUES
    ('1234567890101', 'Carlos', 'Enrique', NULL, 'Ramírez', 'López', '1975-06-15', 'carlos.ramirez@realmixco.gt', 'Mixco', 2020, 1),
    ('9876543210101', 'Luis', 'Fernando', NULL, 'Gómez', 'Martínez', '1980-03-22', 'luis.gomez@coban.gt', 'Cobán', 2021, 3);

-- Correos presidentes
INSERT INTO president_email (email, president_dpi)
VALUES
    ('presidente.mixco@liga.gt', '1234567890101'),
    ('contacto.mixco@liga.gt', '1234567890101'),
    ('presidente.coban@liga.gt', '9876543210101');

-- Jugadores
INSERT INTO player (first_name, second_name, last_name1, last_name2, email, municipality, birth_date, position, team_id)
VALUES
    ('José', 'Manuel', 'Pérez', 'García', 'jose.perez@realmixco.gt', 'Mixco', '1995-04-10', 'Delantero', 1),
    ('Mario', NULL, 'López', 'Hernández', 'mario.lopez@realmixco.gt', 'Mixco', '1998-07-22', 'Defensa', 1),
    ('Carlos', 'Andrés', 'Ruiz', 'Mendoza', 'carlos.ruiz@xelaju.gt', 'Quetzaltenango', '1992-01-15', 'Portero', 2),
    ('Luis', 'Alberto', 'Santos', 'Ramírez', 'luis.santos@xelaju.gt', 'Quetzaltenango', '1996-09-30', 'Centrocampista', 2),
    ('Erick', NULL, 'Gómez', 'Paz', 'erick.gomez@coban.gt', 'Cobán', '1994-11-05', 'Delantero', 3),
    ('Jorge', 'Iván', 'Castillo', 'Morales', 'jorge.castillo@coban.gt', 'Cobán', '1997-02-18', 'Defensa', 3);

-- Correos jugadores
INSERT INTO player_email (email, player_id)
VALUES
    ('jperez10@liga.gt', 1),
    ('mlopez5@liga.gt', 2),
    ('cruiz1@liga.gt', 3),
    ('lsantos8@liga.gt', 4),
    ('egomez9@liga.gt', 5),
    ('jcastillo4@liga.gt', 6);

-- Partidos
INSERT INTO matches (date, home_goals, away_goals, home_team_id, away_team_id)
VALUES
    ('2025-10-01', 2, 1, 1, 2),
    ('2025-10-08', 3, 2, 3, 1);

-- Goles
INSERT INTO goal (minute, description, player_id, match_id)
VALUES
    (15, 'Gol de cabeza tras tiro de esquina', 5, 1),
    (45, 'Gol de penal', 1, 1),
    (60, 'Gol de tiro libre', 3, 2),
    (10, 'Gol de jugada colectiva', 4, 2),
    (55, 'Gol de rebote', 5, 2),
    (70, 'Gol de contraataque', 2, 1),
    (85, 'Gol de volea', 2, 2);

