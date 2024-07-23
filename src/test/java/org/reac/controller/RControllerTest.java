package org.reac.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.reac.entity.Users;
import org.reac.model.RDTO;
import org.reac.model.response.RDTOResponse;
import org.reac.service.RService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebFluxTest(RController.class)
public class RControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private RService rService;

    @Test
    public void testYourEndpoint() {
        RDTO mockUser = new RDTO("123", "John Doe");
        Mockito.when(rService.getUserById("123")).thenReturn(Mono.just(mockUser));

        Mono<RDTOResponse> responseMono = webTestClient.get().uri("/getUserById")
                .exchange()
                .expectStatus().isOk()
                .returnResult(RDTOResponse.class)
                .getResponseBody()
                .next();

        StepVerifier.create(responseMono)
                .expectNextMatches(response -> response.getMessage().equals("Success"))
                .expectComplete()
                .verify();
    }

    @Test
    public void testGetUserById() {
        RDTO mockUser = new RDTO("123", "John Doe");
        Mockito.when(rService.getUserById("123")).thenReturn(Mono.just(mockUser));

        webTestClient.get().uri("/getUserById")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(RDTOResponse.class)
                .value(response -> {
                    assert response != null;
                    assert response.getStatusCode() == 200;
                    assert response.getMessage().equals("Success");
                    assert response.getData() != null;
                    assert response.getData().getId().equals("123");
                    assert response.getData().getName().equals("John Doe");
                });
    }

    @Test
    public void testGetUsers() {
        List<Users> mockUsers = Arrays.asList(
                new Users("1", "Alice"),
                new Users("2", "Bob")
        );
        Mockito.when(rService.getAllUsers()).thenReturn(Flux.fromIterable(mockUsers));

        webTestClient.get().uri("/getUsers")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Users.class)
                .value(users -> {
                    // Assert the size and content of the response list
                    assert users != null;
                    assert users.size() == 2;
                    assert users.get(0).getId().equals("1");
                    assert users.get(0).getName().equals("Alice");
                    assert users.get(1).getId().equals("2");
                    assert users.get(1).getName().equals("Bob");
                });
    }

}
