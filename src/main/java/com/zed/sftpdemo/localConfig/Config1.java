package com.zed.sftpdemo.localConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.integration.core.GenericSelector;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.FileWritingMessageHandler;
import org.springframework.integration.file.support.FileExistsMode;
import org.springframework.messaging.MessageHandler;

import java.io.File;

/**
 * @author Zeluo
 * @date 2019/9/18 17:45
 */
//@Configuration
public class Config1 {
    private static String INPUT_DIR = "D:\\Test Folder\\INPUT_FOLDER";
    private static String OUTPUT_DIR = "D:\\Test Folder\\OUTPUT_FOLDER";
    private static String SUFFIX = ".txt";
    @Bean
    public IntegrationFlow upcaseFlow() {
        return IntegrationFlows.from(sourceDir(), configurer -> configurer.poller(Pollers.fixedDelay(10000)))
                .filter(source -> ((File)source).getName().endsWith(SUFFIX))
                .handle(targetDir())
                .transform(str -> str+"1")
                .get();
    }
    @Bean
    public MessageSource<File> sourceDir() {
        FileReadingMessageSource messageSource = new FileReadingMessageSource();
        messageSource.setDirectory(new File(INPUT_DIR));
        return messageSource;
    }
    @Bean
    public GenericSelector<File> onlyJpgs() {
        return new GenericSelector<File>() {
            @Override
            public boolean accept(File file) {
                return file.getName().endsWith(SUFFIX);
            }
        };
    }

    @Bean
    public MessageHandler targetDir() {
        FileWritingMessageHandler handler = new FileWritingMessageHandler(new File(OUTPUT_DIR));
        handler.setFileExistsMode(FileExistsMode.REPLACE);
        handler.setExpectReply(false);
        return handler;
    }

}
