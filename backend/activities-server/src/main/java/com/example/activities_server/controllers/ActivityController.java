package com.example.activities_server.controllers;

import com.example.activities_server.dto.ActivityDTO;
import com.example.activities_server.dto.LoadMoneyRequest;
import com.example.activities_server.dto.TransferRequest;
import com.example.activities_server.exceptions.ResourceNotFoundException;
import com.example.activities_server.services.ActivityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping("/{userId}/transferences")
    public ResponseEntity<List<ActivityDTO>> getLatestActivitiesByUserId(@PathVariable Long userId) {
        try {
            List<ActivityDTO> activities = activityService.getLatestActivitiesByUserId(userId);
            return ResponseEntity.ok(activities);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{userId}/activity")
    public ResponseEntity<List<ActivityDTO>> getAllActivitiesByUserId(@PathVariable Long userId) {
        try {
            List<ActivityDTO> activities = activityService.getAllActivitiesByUserId(userId);
            return ResponseEntity.ok(activities);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/user/{userId}/activity/{transactionId}")
    public ResponseEntity<ActivityDTO> getActivityDetail(@PathVariable Long userId, @PathVariable Long transactionId) {
        try {
            ActivityDTO activity = activityService.getActivityByUserIdAndTransactionId(userId, transactionId);
            return ResponseEntity.ok(activity);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/accounts/{userId}/transferences")
    public ResponseEntity<ActivityDTO> loadMoneyByCard(@PathVariable Long userId, @RequestBody LoadMoneyRequest loadMoneyRequest) {
        try {
            ActivityDTO activity = activityService.loadMoneyByCard(userId, loadMoneyRequest);
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        } catch (IllegalArgumentException | ResourceNotFoundException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/accounts/{userId}/transfer")
    public ResponseEntity<ActivityDTO> makeTransfer(@PathVariable Long userId, @RequestBody TransferRequest transferRequest) {
        try {
            ActivityDTO activity = activityService.makeTransfer(userId, transferRequest);
            return new ResponseEntity<>(activity, HttpStatus.CREATED);
        } catch (ResourceNotFoundException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
