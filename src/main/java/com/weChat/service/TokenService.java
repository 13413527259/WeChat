package com.weChat.service;

import com.weChat.bean.token.Token;

public interface TokenService {

	public Token save(Token token);

	public Token findByIdLimitOne();

}
