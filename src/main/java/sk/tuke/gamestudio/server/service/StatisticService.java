package sk.tuke.gamestudio.server.service;

import sk.tuke.gamestudio.server.dto.Statistic;

public interface StatisticService {

	Statistic getStatistic(String game) throws Exception;

}