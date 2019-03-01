package ru.dsoccer1980.service;

import org.springframework.stereotype.Service;
import ru.dsoccer1980.dao.QuestionDao;
import ru.dsoccer1980.model.Question;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao questionDao;

    public QuestionServiceImpl(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionDao.getAllQuestions();
    }


}
