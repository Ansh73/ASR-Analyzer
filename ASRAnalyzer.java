package com.kevin.maven.quickstart;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

public class ASRAnalyzer {

	Thread thread = new Thread(new Runnable() {
        public void run() {
            String path = "C:\\Users\\kevin\\Desktop\\ASR_Audio";
            boolean flag = false;
            File[] audioFiles = new File(path).listFiles();
            int size;
            byte[] data;
            if (audioFiles != null) {
                for (File audioFile : audioFiles) {
                    size = (int) audioFile.length();
                    data = new byte[size];
                    try {
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(audioFile));
                        DataInputStream dis = new DataInputStream(bis);
                        dis.readFully(data);
                        dis.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    SpeechClient speech = null;
                    try {
                        speech = SpeechClient.create();
                        if(speech!=null)
                        	flag=true;
                        else
                        	flag = false;
                    } catch (IOException e) {
                    	System.out.println("The speech flag is "+flag);
                        e.printStackTrace();
                    }
                    // Configure request with local raw PCM audio
                    RecognitionConfig config = RecognitionConfig.newBuilder()
                            .setEncoding(RecognitionConfig.AudioEncoding.LINEAR16)
                            .setLanguageCode("en-US")
                            .setSampleRateHertz(16000)
                            .build();

                    //data = Files.readAllBytes(path);
                    ByteString audioBytes = ByteString.copyFrom(data);
                    RecognitionAudio audio = RecognitionAudio.newBuilder()
                            .setContent(audioBytes)
                            .build();

                    // Use blocking call to get audio transcript
                    RecognizeResponse response = speech.recognize(config, audio);
                    List<SpeechRecognitionResult> results = response.getResultsList();

                    for (SpeechRecognitionResult result : results) {
                        // There can be several alternative transcripts for a given chunk of speech. Just use the
                        // first (most likely) one here.
                        SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                        System.out.printf("Transcription: %s%n", alternative.getTranscript());
                    }
                    try {
                        speech.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    });
public void startProcessing()
{
    thread.start();
}
}
