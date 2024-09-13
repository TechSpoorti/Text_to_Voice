package com.text_to_speech.controller;



import javax.speech.Central;


import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

import org.springframework.stereotype.Controller;

@Controller
public class TextToSpeechController {

    private static final String VOICES_KEY = "freetts.voices";
    private static final String VOICES_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String CENTRAL_DATA = "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral";

    public void convertTextToSpeech(String text) {
        Synthesizer synthesizer = null;
        try {
            // Set system properties
            System.setProperty(VOICES_KEY, VOICES_VALUE);
            Central.registerEngineCentral(CENTRAL_DATA);
            
            // Create and allocate the synthesizer
            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
            synthesizer = Central.createSynthesizer(desc);

            if (synthesizer == null) {
                System.err.println("Synthesizer could not be created.");
                return;
            }

            synthesizer.allocate();
            synthesizer.resume();

            // Speak the provided text
            synthesizer.speakPlainText(text, null);

            // Wait until the synthesizer has finished speaking
            synthesizer.waitEngineState(Synthesizer.QUEUE_EMPTY);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (synthesizer != null) {
                try {
                    synthesizer.deallocate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

