package com.servicios_web.segunda_vida_backend.Controller;
import com.servicios_web.segunda_vida_backend.Model.User;
import com.servicios_web.segunda_vida_backend.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("users")
@CrossOrigin(origins = "http://localhost:3000", methods= {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})

@Tag(name = "Users", description = "Provides methods for managing users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users")
    @ApiResponse(responseCode = "200", description = "Found users", content = {
        @Content(mediaType = "application/json",array = @ArraySchema(schema = @Schema(implementation = User.class)))})
    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @Operation(summary = "Get user by ID", description = "Retrieve a user by their unique ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    @GetMapping("{id_user}")
    public ResponseEntity<User> getByID_Usuario(@PathVariable Integer id_user) {
        User user = userService.getByID_User(id_user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @Operation(summary = "Create a new user")
    @ApiResponse(responseCode = "201", description = "User created successfully", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)) })
    @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
    @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    @PostMapping
    public void createUser (@Parameter(description = "User object containing the details of the new user", required = true)
                                @RequestBody  User user) {
        userService.save(user);
    }

    @Operation(summary = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated user record", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content)
    })
    @PutMapping("{id_user}")
    public ResponseEntity<?> update(@Parameter(description = "User object containing the updated details", required = true)
                                        @RequestBody User user,
                                    @Parameter(description = "ID of the user to be updated", required = true)
                                        @PathVariable Integer id_user) {
        try {
            User auxUser = userService.getByID_User(id_user);
            user.setId_user(auxUser.getId_user());
            userService.save(user);
            return new ResponseEntity<String>("Updated record", HttpStatus.OK);
        }catch (NoSuchElementException e) {
            return new ResponseEntity<String>("The record with the control number provided is not found in the database", HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Delete a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("{id_user}")
    public void delete(@Parameter(description = "ID of the user to be deleted", required = true)
                           @PathVariable Integer id_user) {
        userService.delete(id_user);
    }

    
}
