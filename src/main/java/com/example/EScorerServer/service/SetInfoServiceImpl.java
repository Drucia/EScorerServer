package com.example.EScorerServer.service;

import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.repository.SetInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SetInfoServiceImpl implements SetInfoService {
    private final SetInfoRepository setInfoRepository;

    @Override
    public List<SetInfo> saveAll(List<SetInfo> sets) {
        return setInfoRepository.saveAll(sets);
    }
}
