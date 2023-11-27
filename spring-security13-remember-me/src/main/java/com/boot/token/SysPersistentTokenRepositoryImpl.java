package com.boot.token;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.boot.entity.SysToken;
import com.boot.mapper.SysTokenMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysPersistentTokenRepositoryImpl implements PersistentTokenRepository {

    @Autowired
    SysTokenMapper tokenMapper;

    @Override
    public void createNewToken(PersistentRememberMeToken token) {
        SysToken sysToken = new SysToken();
        sysToken.setUsername(token.getUsername());
        sysToken.setSeries(token.getSeries());
        sysToken.setTokenValue(token.getTokenValue());
        sysToken.setDate(token.getDate());

        tokenMapper.insert(sysToken);

    }

    @Override
    public void updateToken(String series, String tokenValue, Date lastUsed) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.set("token",tokenValue);
        updateWrapper.set("date",lastUsed);

        updateWrapper.eq("series",series);

        tokenMapper.update(null,updateWrapper);

    }

    @Override
    public PersistentRememberMeToken getTokenForSeries(String seriesId) {

        SysToken sysToken = tokenMapper.selectOne(new QueryWrapper<SysToken>().eq("series", seriesId));

        if(sysToken == null){
            return null;
        }

        PersistentRememberMeToken token =
                new PersistentRememberMeToken(sysToken.getUsername(),sysToken.getSeries(),sysToken.getTokenValue(),sysToken.getDate());

        return token;
    }

    @Override
    public void removeUserTokens(String username) {
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq("username",username);
        tokenMapper.delete(updateWrapper);
    }
}
