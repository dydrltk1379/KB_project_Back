package com.finns.record.controller;

import com.finns.record.dto.RecordDTO;
import com.finns.record.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/record")
public class RecordController {

    @Autowired
    private RecordService recordService;

    // Get record details by ID
    @GetMapping("/{id}")
    public RecordDTO getRecord(@PathVariable Long id) {
        return recordService.getRecordById(id);
    }

    // Update record
    @PutMapping("/{id}")
    public void updateRecord(@PathVariable Long id, @RequestBody RecordDTO recordDTO) {
        recordService.updateRecord(id, recordDTO);
    }
}
