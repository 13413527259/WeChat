package com.weChat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.weChat.bean.token.Token;
import com.weChat.dao.TokenDao;
import com.weChat.service.TokenService;

@Service
public class TokenServiceImpl implements TokenService {

	@Autowired
	private TokenDao dao;

	@Override
	public Token save(Token token) {
		return dao.save(token);
	}

	@Override
	public Token findByIdLimitOne() {
		return dao.findByIdLimitOne();
	}

}
