package com.hj.placesearchproject.mapper;

import com.hj.placesearchproject.model.SearchLog;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SearchMapper {
    void insertSearchLog(SearchLog searchLog);
    List<SearchLog> findTop10();
}
