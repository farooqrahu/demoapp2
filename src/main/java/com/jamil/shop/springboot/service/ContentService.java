package com.jamil.shop.springboot.service;

import com.jamil.shop.springboot.DAO.ContentDao;
import com.jamil.shop.springboot.model.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentService {

    @Autowired
    private ContentDao contentDao;

    public void saveContent(Content content) {
        contentDao.save(content);
    }


}
