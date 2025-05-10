package com.example.activities_server.services;

import com.example.activities_server.dto.*;

import java.util.List;

public interface ActivityService {

    List<ActivityDTO> getLatestActivitiesByUserId(Long userId);

    List<ActivityDTO> getAllActivitiesByUserId(Long userId);

    ActivityDTO getActivityByUserIdAndTransactionId(Long userId, Long transactionId);

    ActivityDTO loadMoneyByCard(Long userId, LoadMoneyRequest loadMoneyRequest);

    List<ActivityDTO> getLast5TransfersByUserAlias(Long userId);

    ActivityDTO makeTransfer(Long userId, TransferRequest transferRequest);
}
