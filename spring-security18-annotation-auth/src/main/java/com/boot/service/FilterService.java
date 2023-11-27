package com.boot.service;

import com.boot.vo.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class FilterService {

    /**
     * 当@PreFilter标注的方法内拥有多个集合类型的参数时，
     * 可以通过@PreFilter的filterTarget属性来指定当前是针对哪个参数进行过滤的。
     */
    @PreFilter(filterTarget = "ids", value = "filterObject%2==0")
    public List<Integer> doFilter(List<Integer> ids, List<UserEntity> users) {
        log.warn("ids=" + ids.toString());
        log.warn("users=" + users.toString());
        return ids;
    }

}

