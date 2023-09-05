package service;

import dao.HistoryDAO;
import model.History;

import java.util.List;

public class HistoryService {
    HistoryDAO historyDAO = new HistoryDAO();
    public int countSongHistory(int idSong){
        return historyDAO.countSongHistory(idSong);
    }
    public void createViewHistory(History history){
        historyDAO.createViewHistory(history);
    }

    public List<History> findAll() {
        return historyDAO.findAll();
    }
}
