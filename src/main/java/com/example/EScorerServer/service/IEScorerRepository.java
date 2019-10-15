package com.example.EScorerServer.service;

import com.example.EScorerServer.model.RefereeClass;
import com.example.EScorerServer.model.User;

import java.util.List;

public interface IEScorerRepository {
    interface RefereeClassRep{
        void save(RefereeClass refereeClass);
        List<RefereeClass> loadAll();
    }

    interface UserRep{
        void save(User user);
        User getUserById(String userId);
    }
}
