package com.example.activities_server.services;

import com.example.activities_server.clients.AccountClient;
import com.example.activities_server.clients.CardClient;
import com.example.activities_server.dto.*;
import com.example.activities_server.entities.Activity;
import com.example.activities_server.exceptions.BadRequestException;
import com.example.activities_server.exceptions.ResourceNotFoundException;
import com.example.activities_server.repositories.ActivityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ActivityService {

    private static final String TRANSFER_TYPE = "Transfer";
    private static final String LOAD_MONEY_TYPE = "LOAD MONEY FROM CARD";

    private final ActivityRepository activityRepository;
    private final CardClient cardClient;
    private final AccountClient accountClient;

    public ActivityService(ActivityRepository activityRepository, CardClient cardClient, AccountClient accountClient) {
        this.activityRepository = activityRepository;
        this.cardClient = cardClient;
        this.accountClient = accountClient;
    }

    public List<ActivityDTO> getLatestActivitiesByUserId(Long userId) {
        return activityRepository.findTop5ByUserIdOrderByDateDesc(userId).stream()
                .map(ActivityDTO::new)
                .collect(Collectors.toList());
    }

    public List<ActivityDTO> getAllActivitiesByUserId(Long userId) {
        return activityRepository.findAllByUserIdOrderByDateDesc(userId).stream()
                .map(ActivityDTO::new)
                .collect(Collectors.toList());
    }

    public ActivityDTO getActivityByUserIdAndTransactionId(Long userId, Long transactionId) {
        Activity activity = activityRepository.findByUserIdAndId(userId, transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction with ID " + transactionId + " not found for user " + userId));
        return new ActivityDTO(activity);
    }

    public ActivityDTO loadMoneyByCard(Long userId, LoadMoneyRequest loadMoneyRequest) {
        double amountToLoad = loadMoneyRequest.getAmount();

        if (amountToLoad <= 0) {
            throw new BadRequestException("The amount to load must be greater than zero.");
        }

        CardDTO card = cardClient.getCardByLastFourNumbers(userId, loadMoneyRequest.getCardNumber());
        if (card == null) {
            throw new ResourceNotFoundException("Card not found for userId " + userId + " with card number " + loadMoneyRequest.getCardNumber());
        }

        AccountDTO updatedAccount = accountClient.updateAccountBalance(userId, amountToLoad);

        Activity activity = new Activity();
        activity.setUserId(userId);
        activity.setAmount(amountToLoad);
        activity.setOrigin(updatedAccount.getAlias());
        activity.setDestination(updatedAccount.getAlias());
        activity.setCardId(card.getId());
        activity.setType(LOAD_MONEY_TYPE);
        activity.setDate(LocalDateTime.now().toString());
        activity.setDetail("Ingresaste desde tarjeta terminada en " + loadMoneyRequest.getCardNumber());

        return new ActivityDTO(activityRepository.save(activity));
    }

    public List<ActivityDTO> getLast5TransfersByUserAlias(Long userId) {
        String alias = accountClient.getAccountByUserId(userId).getAlias();

        return activityRepository.findTop5ByOriginOrderByDateDesc(alias).stream()
                .map(ActivityDTO::new)
                .collect(Collectors.toList());
    }

    public ActivityDTO makeTransfer(Long userId, TransferRequest transferRequest) {
        AccountDTO originAccount = accountClient.getAccountByUserId(userId);
        if (originAccount == null) {
            throw new ResourceNotFoundException("Cuenta no encontrada para userId " + userId);
        }

        AccountSearchRequest searchRequest = new AccountSearchRequest();
        String destination = transferRequest.getDestination();

        if (destination.matches("\\d+")) {
            searchRequest.setCvu(destination);
        } else {
            searchRequest.setAlias(destination);
        }

        ResponseEntity<AccountDTO> responseEntity = accountClient.getAccountByCvuOrAlias(searchRequest);
        AccountDTO destinationAccount = responseEntity.getBody();

        if (destinationAccount == null) {
            throw new ResourceNotFoundException("Cuenta no encontrada para el alias o CVU proporcionado.");
        }

        if (originAccount.getBalance() < transferRequest.getAmount()) {
            throw new BadRequestException("Fondos insuficientes");
        }

        AccountDTO updatedOriginAccount = accountClient.updateAccountBalance(userId, transferRequest.getAmount());
        AccountDTO updatedDestinationAccount = accountClient.updateAccountBalance(
                Long.valueOf(destinationAccount.getUserId()), Math.abs(transferRequest.getAmount()));

        Activity originActivity = new Activity();
        originActivity.setUserId(userId);
        originActivity.setAmount(transferRequest.getAmount());
        originActivity.setOrigin(updatedOriginAccount.getAlias());
        originActivity.setDestination(updatedDestinationAccount.getAlias());
        originActivity.setType(TRANSFER_TYPE);
        originActivity.setDate(LocalDateTime.now().toString());
        originActivity.setDetail("Transferencia a " + transferRequest.getDestination());

        activityRepository.save(originActivity);

        Activity destinationActivity = new Activity();
        destinationActivity.setUserId(Long.valueOf(destinationAccount.getUserId()));
        destinationActivity.setAmount(Math.abs(transferRequest.getAmount()));
        destinationActivity.setOrigin(updatedOriginAccount.getAlias());
        destinationActivity.setDestination(updatedDestinationAccount.getAlias());
        destinationActivity.setType(TRANSFER_TYPE);
        destinationActivity.setDate(LocalDateTime.now().toString());
        destinationActivity.setDetail("Recibiste transferencia desde " + originAccount.getAlias());

        activityRepository.save(destinationActivity);

        return new ActivityDTO(originActivity);
    }
}
