package com.company.dao;

import com.company.models.statistics.AwardCountByMentor;
import com.company.models.statistics.QuestCountByMentor;

import java.util.List;

public interface StatisticsDao {
    List<QuestCountByMentor> getQuestCountByMentor();
    List<AwardCountByMentor> getAwardCountByMentor();
}
