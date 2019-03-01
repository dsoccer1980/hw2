package ru.dsoccer1980.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.dsoccer1980.model.Question;

import java.util.List;
import java.util.Locale;

@Service
public class TestingServiceImpl implements TestingService {

    private final QuestionService questionService;
    private final IOService ioService;
    private int rightAnswersCount = 0;

    private MessageSource messageSource;
    @Value("${locale.prop}")
    protected String locale;

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public TestingServiceImpl(QuestionService questionService, IOService ioService) {
        this.questionService = questionService;
        this.ioService = ioService;
    }

    @Override
    public void showQuestions() {
        List<Question> questions = questionService.getAllQuestions();

        for (Question question : questions) {
            ioService.write(question.getQuestion());
            ioService.write(messageSource.getMessage(
                    "write.answer",
                    null,
                    new Locale(locale)));
            String userAnswer = ioService.read();
            String rightAnswer = question.getAnswer();
            if (userAnswer.equals(rightAnswer)) {
                this.rightAnswersCount++;
            }
        }
    }

    @Override
    public int getResult() {
        return rightAnswersCount;
    }

}
