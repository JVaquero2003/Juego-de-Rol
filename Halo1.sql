DROP DATABASE if exists halo1;

-- Creamos la base de datos
CREATE DATABASE if not exists halo1
default character set latin1;

use halo1;

-- Creamos la tabla de las armas
CREATE TABLE `arma` (
  `id_arma` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `dano` int DEFAULT NULL,
  PRIMARY KEY (`id_arma`)
) ENGINE = InnoDB CHARSET = latin1 AUTO_INCREMENT = 1;

-- Creamos la tabla de los avatares
CREATE TABLE `avatar` (
  `id_avatar` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `vida` int DEFAULT NULL,
  PRIMARY KEY (`id_avatar`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Creamos la tabla de los poderes
CREATE TABLE `poder` (
  `id_poder` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `dano_defensa` int DEFAULT NULL,
  PRIMARY KEY (`id_poder`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Creamos la tabla de las preguntas
CREATE TABLE `pregunta` (
  `id_pregunta` int NOT NULL AUTO_INCREMENT,
  `pregunta` varchar(300) DEFAULT NULL,
  `desencadenante1` varchar(150) DEFAULT NULL,
  `desencadenante2` varchar(150) DEFAULT NULL,
  `respuesta1` int DEFAULT NULL,
  `respuesta2` int DEFAULT NULL,
  PRIMARY KEY (`id_pregunta`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Creamos la tabla de los characteres
CREATE TABLE `personaje` (
  `id_personaje` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  `id_avatar` int DEFAULT NULL,
  `id_arma` int DEFAULT NULL,
  `id_poder` int DEFAULT NULL,
  PRIMARY KEY (`id_personaje`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Relacionamos las claves foraneas (FK) de la tabla de Character (Avatar, Arma, Poder)
alter table `personaje`
add constraint fk_id_avatar	
foreign key (id_avatar) 				
references avatar (id_avatar)
on delete no action on update no action;

alter table `personaje`
add constraint fk_id_arma
foreign key (id_arma) 				
references arma (id_arma)
on delete no action on update no action;

alter table `personaje`
add constraint fk_id_poder
foreign key (id_poder) 				
references poder (id_poder)
on delete no action on update no action;

CREATE TABLE `ranking` (
  `id_ranking` int NOT NULL AUTO_INCREMENT,
  `id_personaje` int DEFAULT NULL,
  `rondas` int DEFAULT NULL,
  PRIMARY KEY (`id_ranking`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Relacionamos la FK con la tabla de Character
alter table ranking
add constraint fk_id_personaje
foreign key (id_personaje) 				
references `personaje` (id_personaje)
on delete no action on update no action;

INSERT INTO `halo1`.`pregunta` (`id_pregunta`, `pregunta`, `desencadenante1`, `desencadenante2`, `respuesta1`, `respuesta2`) 
VALUES ('1', 'Te encuentras una cueva. 1- Entras 2- Pasas de largo', 
		'Decides entrar y te encuentras un cofre con una pocion. Ganas 200 de vida', 
        'Decides seguir el camino y te encuentras a una bruja que te envenena. Pierdes 200 puntos de vida', '200', '-200');
INSERT INTO `halo1`.`pregunta` (`id_pregunta`, `pregunta`, `desencadenante1`, `desencadenante2`, `respuesta1`, `respuesta2`) 
VALUES ('2', 'Te Gusta la pizza. 1- Si 2- No', 
		'Has dicho que si y por eso te corresponde un año de pizza gratis. Ganas 50 puntos de vida', 
        'Has dicho que no la pizzeria se enfada contigo te hecha a patadas y te prohibe la entrada. Pierdes 50 puntos de vida', 
        '50', '-50');
INSERT INTO `halo1`.`pregunta` (`id_pregunta`, `pregunta`, `desencadenante1`, `desencadenante2`, `respuesta1`, `respuesta2`) 
VALUES ('3', 'Encuentras un pueblo a lo lejos. 1- Decides acudir para pedir algo de agua y comida 
2- Decides continuar tu viaje con la posiblidad de morir de deshidratación', 
'Has decidido ir al pueblo y cuando entras te encuentras que son todos indegenas y acabas herido. Pierdes 300 de vida', 
'Has decidido continuar tu camino y pasando unas montañas encuentras un refugio abandonado con reservas de comida 
y agua para los montañeros. Ganas 300 de vida', '-300', '300');


        
INSERT INTO `halo1`.`arma` (`id_arma`, `nombre`, `dano`) VALUES ('1', 'Escudo', '25');

INSERT INTO `halo1`.`avatar` (`id_avatar`, `nombre`, `vida`) VALUES ('1', 'Trol', '400');

INSERT INTO `halo1`.`poder` (`id_poder`, `nombre`, `dano_defensa`) VALUES ('1', 'Pistola de Gravedad', '40');
INSERT INTO `halo1`.`poder` (`id_poder`, `nombre`, `dano_defensa`) VALUES ('2', 'Pocion curativa', '50');
UPDATE `halo1`.`poder` SET `nombre` = 'Pocion del amor' WHERE (`id_poder` = '1');
INSERT INTO `halo1`.`poder` (`id_poder`, `nombre`, `dano_defensa`) VALUES ('3', 'Crece-Huesos', '45');
INSERT INTO `halo1`.`poder` (`id_poder`, `nombre`, `dano_defensa`) VALUES ('4', 'Antiparaliz', '60');

INSERT INTO `halo1`.`personaje` (`id_personaje`, `nombre`, `id_avatar`, `id_arma`, `id_poder`) VALUES ('1', 'Joselu', '1', '1', '1');

INSERT INTO `halo1`.`ranking` (`id_ranking`, `id_personaje`, `rondas`) VALUES ('1', '1', '1');
