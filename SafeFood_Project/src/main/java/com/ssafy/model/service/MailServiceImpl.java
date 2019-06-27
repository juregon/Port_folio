package com.ssafy.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.model.dto.Mail;
import com.ssafy.model.repository.MailRepository;

@Service
public class MailServiceImpl implements MailService{
	@Autowired
	private MailRepository maRepo;
	
	public int insert(Mail mail) {
        return maRepo.insert(mail);
    }

	public List<Mail> searchAll(String id) {
		return maRepo.searchAll(id);
	}

	public int mailUpdate(int num) {
		return maRepo.mailUpdate(num);
	}

	public int searchCnt(String id) {
		// TODO Auto-generated method stub
		return maRepo.searchCnt(id);
	}
}
