package com.deepakshankar.cartracker.service.impl;

import com.deepakshankar.cartracker.dao.ReadingDao;
import com.deepakshankar.cartracker.entity.Reading;
import com.deepakshankar.cartracker.exceptions.BadRequestException;
import com.deepakshankar.cartracker.service.ReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadingServiceImpl implements ReadingService {

    @Autowired
    private ReadingDao dao;

    @Override
    public void create(final Reading reading) {
        if(reading!=null){
            dao.save(reading);
        }
        else{
            throw new BadRequestException("Reading cannot be empty!");
        }
    }
}
