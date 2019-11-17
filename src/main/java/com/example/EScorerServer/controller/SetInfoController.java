package com.example.EScorerServer.controller;

import com.example.EScorerServer.errors.SummaryNotFoundException;
import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.repository.SetInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SetInfoController {
    @Autowired
    private SetInfoRepository repository;

    public List<SetInfo> saveSetsForSummary(Summary summary, List<SetInfo> setInfos) {
        boolean isFine = setInfos.stream().allMatch(setInfo -> setInfo.getSummary().getId() == summary.getId());

        if (isFine)
            return repository.saveAll(setInfos);
        else
            throw new SummaryNotFoundException(summary.getId());
    }
}
