package com.company.dao;

import com.company.model.statistics.AwardCountByMentor;
import com.company.model.statistics.QuestCountByMentor;

import java.util.List;

public interface StatisticsDao {
    List<QuestCountByMentor> getQuestCountByMentor();
    List<AwardCountByMentor> getAwardCountByMentor();
}
