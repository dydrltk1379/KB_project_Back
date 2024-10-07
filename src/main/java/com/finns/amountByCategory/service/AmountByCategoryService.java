package com.finns.amountByCategory.service;

import com.finns.amountByCategory.dto.AmountByCategory;
import com.finns.amountByCategory.mapper.AmountByCategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
@Transactional(readOnly = true)
public class AmountByCategoryService {

    private final AmountByCategoryMapper mapper;

    public List<AmountByCategory> getAmountsForCategoryByUser(Long userNo) {
        return Optional.ofNullable(mapper.selectAllByUser(userNo))
                .orElseThrow(NoSuchElementException::new);
    }

}
