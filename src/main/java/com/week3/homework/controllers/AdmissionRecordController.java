package com.week3.homework.controllers;

import com.week3.homework.dto.AdmissionRecordDTO;
import com.week3.homework.services.AdmissionRecordService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/admissionRecord")
public class AdmissionRecordController {

    private final AdmissionRecordService admissionRecordService;

    public AdmissionRecordController(AdmissionRecordService admissionRecordService) {
        this.admissionRecordService = admissionRecordService;
    }

    @GetMapping(path = "/{admissionRecordId}")
    public AdmissionRecordDTO getAdmissionRecordById(@PathVariable Long admissionRecordId) {
        return admissionRecordService.getAdmissionRecordById(admissionRecordId);
    }

    @PostMapping
    public AdmissionRecordDTO createNewAdmissionRecord(@RequestBody AdmissionRecordDTO newAdmissionRecord) {
        return admissionRecordService.createNewAdmissionRecord(newAdmissionRecord);
    }
}
