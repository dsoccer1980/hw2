package ru.dsoccer1980.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Locale;

@Service
public class RunTesting {

    private TestingService testingService;
    private IOService ioService;
    @Autowired
    private MessageSource messageSource;
    @Value("${locale.prop}")
    private String locale;

    public RunTesting(TestingService testingService, IOService ioService) {
        this.testingService = testingService;
        this.ioService = ioService;
    }

    public void run() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        ioService.write(messageSource.getMessage(
                "write.name",
                null,
                new Locale(locale)));
        String name = bufferedReader.readLine();

        testingService.showQuestions();

        ioService.write(messageSource.getMessage(
                "write.result",
                new String[]{name, String.valueOf(testingService.getResult())},
                new Locale(locale)));

    }
}
