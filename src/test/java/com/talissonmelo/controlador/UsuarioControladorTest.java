package com.talissonmelo.controlador;

import com.talissonmelo.modelo.dto.UsuarioDto;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
class UsuarioControladorTest {

    @Test
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
}