package com.cg.billing.daoservices;

import java.util.List;

import com.cg.billing.beans.PostpaidAccount;

public interface PostpaidAccountDAO {
	PostpaidAccount save(PostpaidAccount posAccount);
	boolean update(PostpaidAccount posAccount);
	PostpaidAccount findOne(long mobileNo);
	List<PostpaidAccount> findAll();
	boolean deleteOne(PostpaidAccount posAccount);
	boolean deleteAll();
	
}
