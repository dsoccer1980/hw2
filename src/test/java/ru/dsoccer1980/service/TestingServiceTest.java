package ru.dsoccer1980.service;

import org.junit.jupiter.api.Test;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import ru.dsoccer1980.model.Question;


import java.util.Collections;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ComponentScan
@PropertySource("classpath:application.properties")
class TestingServiceTest {

    @Test
    void testAllAnswersRight() {
        QuestionService questionService = mock(QuestionService.class);
        IOService ioService = mock(IOService.class);
        MessageSource messageSource = mock(MessageSource.class);

        List<Question> questions = Collections.singletonList(new Question(1, "question", "answer"));

        when(questionService.getAllQuestions()).thenReturn(questions);
        when(ioService.read()).thenReturn("answer");

        TestingService testService = new TestingServiceImpl(questionService, ioService);
        ((TestingServiceImpl) testService).locale = "ru_RU";
        ((TestingServiceImpl) testService).setMessageSource(messageSource);
        testService.showQuestions();
        assertEquals(1, testService.getResult());
    }

    @Test
    void testOneAnswerWrong() {
        QuestionService questionService = mock(QuestionService.class);
        IOService ioService = mock(IOService.class);
        MessageSource messageSource = mock(MessageSource.class);

        List<Question> questions = Collections.singletonList(new Question(1, "question", "answer"));

        when(questionService.getAllQuestions()).thenReturn(questions);
        when(ioService.read()).thenReturn("wrong");

        TestingService testService = new TestingServiceImpl(questionService, ioService);
        ((TestingServiceImpl) testService).locale = "ru_RU";
        ((TestingServiceImpl) testService).setMessageSource(messageSource);
        testService.showQuestions();
        assertEquals(0, testService.getResult());
    }
}