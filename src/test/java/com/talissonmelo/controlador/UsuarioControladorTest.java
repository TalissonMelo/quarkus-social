package com.talissonmelo.controlador;

import com.talissonmelo.modelo.dto.UsuarioDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UsuarioControladorTest {

    @Test
    @Order(1)
    @DisplayName("Deve cadastrar usuário com sucesso")
    public void deveCriarUsuario() {

        var usuario = new UsuarioDto();
        usuario.setNome("Davi Taylor");
        usuario.setIdade(1);

        var response = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .extract()
                .response();

        assertEquals(201, response.statusCode());
        assertNotNull(response.jsonPath().getString("id"));
    }

    @Test
    @Order(2)
    @DisplayName("Deve dar erro ao cadastar usuário")
    public void deveDarErroCriarUsuario() {

        var usuario = new UsuarioDto();
        usuario.setNome(null);
        usuario.setIdade(null);

        var response = given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post("/usuarios")
                .then()
                .extract()
                .response();

        assertEquals(400, response.statusCode());
    }

    @Test
    @Order(3)
    @DisplayName("Deve listar usuários")
    public void listar() {
        given()
                .contentType(ContentType.JSON)
                .when()
                .get("/usuarios")
                .then()
                .statusCode(200)
                .body("size()", Matchers.is(1));
    }
}