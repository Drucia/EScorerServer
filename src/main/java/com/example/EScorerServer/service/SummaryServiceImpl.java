package com.example.EScorerServer.service;

import com.example.EScorerServer.repository.SummaryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SummaryServiceImpl implements SummaryService {
    @Autowired
    private SummaryRepository summaryRepository;


}
