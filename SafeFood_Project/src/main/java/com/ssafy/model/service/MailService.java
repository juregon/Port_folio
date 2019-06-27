package com.ssafy.model.service;

import java.util.List;

import com.ssafy.model.dto.Mail;

public interface MailService {
	public int insert(Mail mail); // send mail
	public List<Mail> searchAll(String id); // get list
	public int searchCnt(String id);
	public int mailUpdate(int num);
}
