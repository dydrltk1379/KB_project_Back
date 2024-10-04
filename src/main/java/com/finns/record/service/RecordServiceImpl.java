package com.finns.record.service;

import com.finns.record.dto.RecordDTO;
import com.finns.record.mapper.RecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    private RecordMapper recordMapper;

    @Override
    public RecordDTO getRecordById(Long id) {
        // 데이터베이스에서 해당 ID의 소비 내역을 조회하여 반환
        return recordMapper.getRecordById(id);
    }

    @Override
    public void updateRecord(Long id, RecordDTO recordDTO) {
        // DTO에 ID를 설정
        recordDTO.setId(id);
        // 데이터베이스에서 소비 내역을 수정
        recordMapper.updateRecord(recordDTO);
    }
}
