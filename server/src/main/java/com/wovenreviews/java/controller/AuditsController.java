package com.wovenreviews.java.controller;

import com.wovenreviews.java.dto.AuditsRequest;
import com.wovenreviews.java.dto.AuditsResponse;
import com.wovenreviews.java.model.Audit;
import com.wovenreviews.java.repo.AuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/audits")
public class AuditsController {

    @Autowired
    private AuditRepository auditRepository;

    @PostMapping
    public List<AuditsResponse> getByFilter(@RequestBody AuditsRequest auditsRequest) {
        return auditRepository.findByMessageLikeIgnoreCase("%" + auditsRequest.getQ().getFilters().getMessage() + "%")
                .stream()
                .map(auditModel -> new AuditsResponse(auditModel.getId(), auditModel.getUserId(),
                        auditModel.getEvent(), auditModel.getStatus(),
                        auditModel.getMessage(), auditModel.getCreatedAt()))
                .collect(Collectors.toList());
    }
}
