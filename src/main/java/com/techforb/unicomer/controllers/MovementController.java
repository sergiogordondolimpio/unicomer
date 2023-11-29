package com.techforb.unicomer.controllers;

import com.techforb.unicomer.entitites.Movement;
import com.techforb.unicomer.entitites.request.MovementRequest;
import com.techforb.unicomer.entitites.response.MovementResponse;
import com.techforb.unicomer.services.MovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movement")
@RequiredArgsConstructor
public class MovementController {

    @Autowired
    private MovementService movementService;

    @GetMapping(value = "/last-movement")
    public ResponseEntity<Movement> getMovement(@RequestParam Integer accountId) {
        return movementService.getMovement(accountId);
    }

    @PostMapping(value = "/movement")
    public ResponseEntity<Object> addMovement(@RequestParam Integer accountId, @RequestBody MovementRequest movementRequest) {
        return movementService.addMovement(accountId, movementRequest);
    }

}
