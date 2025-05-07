package com.sarkhan.dtssystem.service;

import com.sarkhan.dtssystem.dto.request.CompleteRequest;
import com.sarkhan.dtssystem.model.user.User;

public interface AdminService {
    public User completeAdminData(CompleteRequest completeRequest, String token);
}
