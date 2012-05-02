package com.summary.voxplore.tts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Locale;
import javax.speech.Central;
import javax.speech.EngineList;
import javax.speech.synthesis.*;

public class CallMyText
{


    private static String noSynthesizerMessage()
    {
        String message = "No synthesizer created.  This may be the result of any\nnumber of problems.  It'" +
"s typically due to a missing\n\"speech.properties\" file that should be at eithe" +
"r of\nthese locations: \n\n"
;
        message = message + "user.home    : " + System.getProperty("user.home") + "\n";
        message = message + "java.home/lib: " + System.getProperty("java.home") + File.separator + "lib\n\n" + "Another cause of this problem might be corrupt or missing\n" + "voice jar files in the freetts lib directory.  This problem\n" + "also sometimes arises when the freetts.jar file is corrupt\n" + "or missing.  Sorry about that.  Please check for these\n" + "various conditions and then try again.\n";
        return message;
    }

    public static void listAllVoices(String modeName)
    {
        System.out.println();
        System.out.println("All " + modeName + " Mode JSAPI Synthesizers and Voices:");
        SynthesizerModeDesc required = new SynthesizerModeDesc(null, modeName, Locale.US, null, null);
        EngineList engineList = Central.availableSynthesizers(required);
        for(int i = 0; i < engineList.size(); i++)
        {
            SynthesizerModeDesc desc = (SynthesizerModeDesc)engineList.get(i);
            System.out.println("    " + desc.getEngineName() + " (mode=" + desc.getModeName() + ", locale=" + desc.getLocale() + "):");
            Voice voices[] = desc.getVoices();
            for(int j = 0; j < voices.length; j++)
            {
                System.out.println("        " + voices[j].getName());
            }

        }

    }

    public static void main(String args[])
    {
        listAllVoices("general");
        String voiceName = args.length <= 0 ? "kevin16" : args[0];
        System.out.println();
        System.out.println("Using voice: " + voiceName);
        try
        {
            SynthesizerModeDesc desc = new SynthesizerModeDesc(null, "general", Locale.US, null, null);
            Synthesizer synthesizer = Central.createSynthesizer(desc);
            if(synthesizer == null)
            {
                System.err.println(noSynthesizerMessage());
                System.exit(1);
            }
            synthesizer.allocate();
            synthesizer.resume();
            desc = (SynthesizerModeDesc)synthesizer.getEngineModeDesc();
            Voice voices[] = desc.getVoices();
            Voice voice = null;
            int i = 0;
            do
            {
                if(i >= voices.length)
                {
                    break;
                }
                if(voices[i].getName().equals(voiceName))
                {
                    voice = voices[i];
                    break;
                }
                i++;
            } while(true);
            if(voice == null)
            {
                System.err.println("Synthesizer does not have a voice named " + voiceName + ".");
                System.exit(1);
            }
           synthesizer.getSynthesizerProperties().setSpeakingRate(150);
           
            synthesizer.getSynthesizerProperties().setVoice(voice);
            synthesizer.speakPlainText(readXMLRequest(), null);
            synthesizer.waitEngineState(0x10000L);
            synthesizer.deallocate();
            System.exit(0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    
    private static String readXMLRequest() {
		BufferedReader br = null;
		String request = "";
		try {
			br = new BufferedReader(new FileReader("request.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("cannot open file ");
			return null;
		}

		// read the contents
		String str = "";

		try {
			while ((str = br.readLine()) != null) {
				request += str;request+=System.getProperty("line.separator");
			}
		} catch (IOException e) {
			
				System.out.println("Exception in reading line "+e);
		
		}		

		return request;
	}
 
}
