package org.reac.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.reac.entity.Users;
import org.reac.model.RDTO;
import org.reac.model.response.RDTOResponse;
import org.reac.service.RService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class RController {

    private final RService rService;

    public RController(RService rService) {
        this.rService = rService;
    }

    @Operation(summary = "Get all users")
    @ResponseStatus(HttpStatus.OK)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation", content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = RDTOResponse.class)))})
    })
    @GetMapping("/getUserById")
    public Mono<RDTOResponse> getUserById() {
        Mono<RDTO> user = rService.getUserById("1234");
        return user.map(u -> {
            RDTOResponse rdtoResponse = new RDTOResponse(u, 200);
            rdtoResponse.setMessage("Success");
            return rdtoResponse;
        }).defaultIfEmpty(new RDTOResponse(new RDTO(), 404));
    }

    @GetMapping("/getUsers")
    public Flux<Users> getUsers() {
        return rService.getAllUsers();
    }
}
