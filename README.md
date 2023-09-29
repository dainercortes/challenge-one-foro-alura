
# API Foro Alura

![API Foro Alura](https://socialify.git.ci/dainercortes/challenge-one-foro-alura/image?font=Jost&issues=1&language=1&logo=https%3A%2F%2Fcdn.worldvectorlogo.com%2Flogos%2Fspring-3.svg&name=1&owner=1&pattern=Solid&pulls=1&stargazers=1&theme=Auto)


<p align="center" > 
  <img src="https://img.shields.io/badge/Java%20JDK-v.17-green"/>
  <img src="https://img.shields.io/badge/Spiring%20Boot-v.3-yellow"/>
  <img src="https://img.shields.io/badge/MySQL-v.8.0.33-blue"/>
</p>

## Tabla de Contenido

- [Descripción del proyecto](#descripcion-del-proyecto)
- [Estado del proyecto](#estado-del-proyecto)
- [Funcionalidades](#funcionalidades)
    - [Autenticacion](#autenticacion)
		- [Login](#login)
    - [Usuario](#usuario)
		- [Registrar](#registrar)
		- [Actualizar](#actualizar)
		- [Eliminar](#eliminar)
	- [Cursos](#cursos)
		- [Registrar](#registrar)
		- [Actualizar](#actualizar)
		- [Eliminar](#eliminar)
	- [Topico](#topico)
		- [Registrar](#registrar)
		- [Listar](#listar)
		- [Listar con respuestas](#listar-con-respuestas)
		- [Actualizar](#actualizar)
		- [Eliminar](#eliminar)
	- [Respuesta](#respuesta)
		- [Registrar](#registrar)
		- [Actualizar](#actualizar)
		- [Eliminar](#eliminar)
- [Acceso al proyecto](#acceso-al-proyecto)
- [Tecnologías utilizadas](#tecnologias-utilizadas)
- [Dependencias utilizadas](#dependencias-utilizadas)
- [Documentacion](#docmentacion)
- [Pruebas a la API](#pruebas-a-la-api)
- [Autor](#autor)
- [Licencia](#licencia)


## Descripcion del proyecto

El foro alura es un lugar donde todos los alumnos de la plataforma alura
pueden colocar sus preguntas sobre determinados cursos, este mágico lugar está lleno de mucho aprendizaje y de colaboración entre alumnos, profesores y moderadores.

## Estado del proyecto

   ![Badge en Desarollo](https://img.shields.io/badge/ESTADO-FINALIZADO-green)


## Funcionalidades

### Autenticacion

#### Login

```bash
[POST] https://localhost:8080/login
```

```json
{
  "username": "string",
  "password": "string"
}
```

### Usuario

#### Registrar

```bash
[POST] https://localhost:8080/users
```

```json
{
  "name": "string",
  "email": "string",
  "username": "string",
  "password": "string"
}
```

#### Actualizar

Los unicos que pueden actualizar los datos de un usuario son el dueño de la cuenta y el adminstrador del sistema.

```bash
[PUT] https://localhost:8080/users
```

```json
{
  "id": 0,
  "name": "string",
  "email": "string",
  "username": "string",
  "password": "string"
}
```

#### Eliminar

Los unicos que pueden eliminar un usuario son el dueño de la cuenta y el adminstrador del sistema.

```bash
[DELETE] https://localhost:8080/users/{id}
```

### Curso

El unico que tiene permitido registrar, actualizar y eliminar los cursos es el administrador del sistema.


#### Registrar

```bash
[POST] https://localhost:8080/courses
```

```json
{
  "name": "string",
  "category": "string"
}
```

#### Actualizar

```bash
[PUT] https://localhost:8080/courses
```

```json
{
  "id": 0,
  "name": "string",
  "category": "string"
}
```

#### Eliminar

```bash
[DELETE] https://localhost:8080/courses/{id}
```

### Topico

#### Registrar

```bash
[POST] https://localhost:8080/topics
```

```json
{
  "title": "string",
  "message": "string",
  "idAuthor": 0,
  "idCourse": 0
}
```

#### Listar

Retorna el siguiente formato

```bash
[GET] https://localhost:8080/topics
```

```json
{
      "id": 0,
      "title": "string",
      "message": "string",
      "creatioDate": "LocalDateTime",
      "status": "string",
      "author": "string",
      "course": "string"
    },
	{
      "id": 0,
      "title": "string",
      "message": "string",
      "creationDate": "LocalDateTime",
      "status": "string",
      "author": "string",
      "course": "string"
    }
```

#### Listar con respuestas

Retorna el siguiente formato

```bash
[GET] https://localhost:8080/topics{id}
```

```json
{
  "id": 0,
  "title": "string",
  "message": "string",
  "creationDate": "LocalDateTime",
  "status": "string",
  "author": "string",
  "course": "string",
  "response": [
    {
      "id": 0,
      "message": "string",
      "creationDate": "LocalDateTime",
      "author": "string",
      "solution": false
    },
	{
      "id": 0,
      "message": "string",
      "creationDate": "LocalDateTime",
      "author": "string",
      "solution": false
    }
  ]
}

```

#### Actualizar

Los unicos que pueden actualizar la información de un topico son el creador y el administrador del sistema.

```bash
[PUT] https://localhost:8080/topics
```

```json
{
  "id": 0,
  "title": "string",
  "message": "string",
  "status": "string",
  "idCourse": 0
}
```

#### Eliminar

Los unicos que pueden eliminar un topico son el creador y el administrador del sistema.

```bash
[DELETE] https://localhost:8080/topics/{id}
```

### Respuesta

#### Registrar

```bash
[POST] https://localhost:8080/responses
```

```json
{
  "message": "string",
  "idTopic": 0,
  "idAuthor": 0
}
```

#### Actualizar

Los unicos que pueden actualizar la información de una respuesta son el creador y el administrador del sistema.

```bash
[PUT] https://localhost:8080/responses
```

```json
{
  "id": 0,
  "message": "string"
}
```

#### Eliminar

Los unicos que pueden eliminar una respuesta son el creador y el administrador del sistema.

```bash
[DELETE] https://localhost:8080/responses/{id}
```

## Acceso al proyecto

1. Descargue la versión 17 de [Java JDK.](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)

2. Descargue el IDE [IntelliJ IDEA Community.](https://www.jetbrains.com/idea/download/)

3. Descargue [MySQL.](https://dev.mysql.com/downloads/installer/)

4. Descargue [MySQL Workbench.](https://dev.mysql.com/downloads/workbench/)

5. Clone el repositoria mediante el siguiente enlace: https://github.com/dainercortes/challenge-one-foro-alura.git

6. Abra MySQL Workbench y ejecute la siguiente linea de codigo para crear la base de datos:

```bash
CREATE DATABASE foro_alura;
```

7. Abra el proyecto en el IDE IntelliJ IDEA Community.

8. Dirijase a la carpeta de **resources** y en **application.properties ** modifique los campos **spring.datasource.username** y **spring.datasource.password** reemplazandolos por el *usuario* y la *contraseña* que asigno al instalar MySQL en su PC.

9. Compile y ejecute la aplicación, listo 🙃.

## Tecnologias utilizadas

* Java 17

* Spring Boot 3

* MySQL

## Dependencias utilizadas

*  `Spring Web`

* `Spring Boot DevTools`

* `Lombok`

* `Spring Data JPA`

*  `Flyway Migration`

* `MySQL Driver`

* `JPA`
  
* `Hibernate`

* `Validation`

* `Spring Security`

*  `JSON Wen Token`

* `Spring Doc`

## Documentacion

1. Puedes acceder a la documentación de la API gracias a Swagger, solo escribe esta dirección en tu navegador cuando el proyecto se este ejecutando: 

```bash
http://localhost:8080/swagger-ui/index.html
```

## Pruebas a la API

1. Puede testear la API desde el programa Insomnia, aquí les dejo un archivo ya configurado para que lo descarguen y luego lo importen en el programa:

*   Clic para descargar el [**Archivo**](https://drive.google.com/file/d/1iUEOA3Hg_wCwGSarpO9KmBjxc01yZLRx/view?usp=sharing)

## Autor

|  [<img src="https://avatars.githubusercontent.com/u/95858653?v=4" width=115><br><sub>Dainer Cortés</sub>](https://github.com/dainercortes) |
| :---: |

## Licencia

> Foro Alura está licenciado bajo la [licencia MIT](https://github.com/dainercortes/challenge-one-foro-alura/blob/main/LICENSE)
