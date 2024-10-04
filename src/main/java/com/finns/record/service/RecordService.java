package com.finns.record.service;

import com.finns.record.dto.RecordDTO;

public interface RecordService {
    RecordDTO getRecordById(Long id);
    void updateRecord(Long id, RecordDTO recordDTO);
}
