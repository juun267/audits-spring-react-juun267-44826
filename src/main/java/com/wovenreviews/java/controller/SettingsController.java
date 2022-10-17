package com.wovenreviews.java.controller;

import com.wovenreviews.java.dto.SettingsRequest;
import com.wovenreviews.java.dto.SettingsResponse;
import com.wovenreviews.java.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/settings")
public class SettingsController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<SettingsResponse>> getUserConfig(@AuthenticationPrincipal String userEmail) {
        return userRepository.findByEmail(userEmail)
                .map(user -> ResponseEntity.ok(
                        Collections.singletonList(new SettingsResponse(user.getDailyEmailUpdates()))))
                .orElse(ResponseEntity.of(Optional.empty()));

    }

    @PostMapping("/daily_email_updates")
    public void updateEmailSetting(@AuthenticationPrincipal String userEmail, @RequestBody SettingsRequest request) {
        userRepository.setEmailUpdates(userEmail, request.getValue());
    }
}
