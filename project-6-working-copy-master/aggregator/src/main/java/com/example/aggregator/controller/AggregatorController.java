package com.example.aggregator.controller;

import com.example.aggregator.model.Entry;
import com.example.aggregator.service.AggregatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AggregatorController {

    private static final Logger logger = LoggerFactory.getLogger(AggregatorController.class.getName());

    private AggregatorService service;

    public AggregatorController(AggregatorService service) {
        this.service = service;
    }

    @GetMapping("/getDefinitionFor/{word}")
    public Entry getDefinitionFor(@PathVariable String word) {
        return service.getDefinitionFor(word);
    }

    @GetMapping("/getAllPalindromes")
    public List<Entry> getAllPalindromes() {
        return service.getAllPalindromes();
    }

    @GetMapping("/getWordsThatContainSuccessiveLettersAndStartsWith/{chars}")
    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(@PathVariable String chars) {

        StopWatch sw = new StopWatch();
        sw.start();
        List<Entry> result = service.getWordsThatContainSuccessiveLettersAndStartsWith(chars);
        sw.stop();

        long nanoSeconds = sw.getLastTaskTimeNanos();

        String message = new StringBuilder().append("Retrieved entries for words starting with [")
                                            .append(chars)
                                            .append("] and containing consecutive double letters,")
                                            .append(" containing ")
                                            .append(result.size())
                                            .append(" entries in ")
                                            .append(nanoSeconds / 1000000.0)
                                            .append("ms")
                                            .toString();
        logger.info(message);

        return result;
    }

    @GetMapping("/getWordsThatStartWithAndContain/{startsWith}/{contains}")
    public List<Entry> getWordsThatContainSuccessiveLettersAndStartsWith(@PathVariable String startsWith, @PathVariable String contains) {

        StopWatch sw = new StopWatch();
        sw.start();
        List<Entry> result = service.getWordsThatStartWithAndContain(startsWith, contains);
        sw.stop();

        long nanoSeconds = sw.getLastTaskTimeNanos();

        String message = new StringBuilder().append("Retrieved entries for words starting with [")
                                            .append(startsWith)
                                            .append("] and containing [")
                                            .append(contains)
                                            .append("], containing ")
                                            .append(result.size())
                                            .append(" entries in ")
                                            .append(nanoSeconds / 1000000.0)
                                            .append("ms")
                                            .toString();
        logger.info(message);

        return result;
    }
}
