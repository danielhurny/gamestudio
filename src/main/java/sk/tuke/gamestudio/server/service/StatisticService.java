package sk.tuke.gamestudio.server.service;

import sk.tuke.gamestudio.server.entity.Statistic;

public interface StatisticService {

	Statistic getStatistic(String game) throws Exception;

}