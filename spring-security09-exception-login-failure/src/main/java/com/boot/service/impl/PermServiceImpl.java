package com.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.boot.entity.Perm;
import com.boot.mapper.PermMapper;
import com.boot.service.PermService;
import org.springframework.stereotype.Service;

@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Perm> implements PermService {

}
