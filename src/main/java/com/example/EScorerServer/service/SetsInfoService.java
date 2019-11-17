package com.example.EScorerServer.service;

import com.example.EScorerServer.model.SetInfo;
import com.example.EScorerServer.model.Summary;
import com.example.EScorerServer.response.SummaryResponse;

import java.util.List;

public class SetsInfoService {
    public static List<SetInfo> getSetsInfoListFromSummaryResponseAndSummary(SummaryResponse response,
                                                                                  Summary summary) {
        return SetInfo.makeFromBody(response.getSets(), summary);
    }
}
