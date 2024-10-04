package com.finns.record.mapper;

import com.finns.record.dto.RecordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
    public interface RecordMapper {


    @Select("SELECT * FROM record_feed WHERE record_no = #{id}")
    RecordDTO getRecordById(Long id);


    @Update("UPDATE record_feed SET category = #{category}, public_status = #{publicStatus}, memo = #{memo} WHERE record_no = #{recordNo}")
    void updateRecord(RecordDTO recordDTO);


}