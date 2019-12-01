package com.example.EScorerServer.service;

import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.repository.SetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SetInfoServiceImpl implements SetInfoService {
    @Autowired
    private SetInfoRepository setInfoRepository;

    @Override
    public List<SetInfo> saveAll(List<SetInfo> sets) {
        return setInfoRepository.saveAll(sets);
    }

    @Override
    public boolean deleteAllWhereSummaryId(int summaryId) {
        return setInfoRepository.deleteAllBySummaryId(summaryId);
    }
}
