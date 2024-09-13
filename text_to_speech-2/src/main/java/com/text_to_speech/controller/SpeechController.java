package com.text_to_speech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SpeechController {
	   private final TextToSpeechController textToSpeechController = new TextToSpeechController();

	    @GetMapping("/")
	    public ModelAndView showForm() {
	        return new ModelAndView("index");
	    }

	    @PostMapping("/synthesize")
	    public ModelAndView convertTextToSpeech(@RequestParam("text") String text) {
	        textToSpeechController.convertTextToSpeech(text);
	        return new ModelAndView("index");
	    }
}
