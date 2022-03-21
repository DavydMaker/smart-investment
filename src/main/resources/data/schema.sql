-- Iniciaizando Tabela de Empresas --
DROP TABLE IF EXISTS `company`;

CREATE TABLE `company` (
	`id` INT(11) UNSIGNED NOT NULL AUTO_INCREMENT,
	`name` VARCHAR(50) NOT NULL,
	`status` TINYINT(1) UNSIGNED NOT NULL DEFAULT '1',
	`excluded` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',

	CONSTRAINT PK_COMPANY PRIMARY KEY (id)
);

-- Inicializando Tabela de Ações --
DROP TABLE IF EXISTS `action`;

CREATE TABLE `action` (
	`company_id` INT(11) UNSIGNED NOT NULL,
	`ticker` VARCHAR(10) NOT NULL,
	`price` NUMBER(20,2) NOT NULL,

	CONSTRAINT PK_ACTION PRIMARY KEY (ticker),
	CONSTRAINT FKR_COMPANY_ID FOREIGN KEY (company_id) REFERENCES company (id)
);