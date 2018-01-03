package com.weChat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.weChat.bean.token.Token;

@Repository
public interface TokenDao extends JpaRepository<Token, Integer> {

	/**
	 * 查询id最大的一个也就是最新的一个token。 <br>
	 * 注意：需要加上 nativeQuery=true，即SQL语句查询
	 * 
	 * @return
	 */
	@Query(value = "select id,access_token,expires_in,create_time from token order by id desc limit 1", nativeQuery = true)
	public Token findByIdLimitOne();

}
