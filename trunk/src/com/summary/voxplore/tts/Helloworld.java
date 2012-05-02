package com.summary.voxplore.tts;
import javax.speech.*;
import javax.speech.recognition.*;
import java.io.FileReader;
import java.util.Locale;

public class Helloworld extends ResultAdapter {
	static Recognizer rec;

	// Receives RESULT_ACCEPTED event: print it, clean up, exit
	public void resultAccepted(ResultEvent e) {
		Result r = (Result)(e.getSource());
		ResultToken tokens[] = r.getBestTokens();

		for (int i = 0; i < tokens.length; i++)
			System.out.print(tokens[i].getSpokenText() + " ");
		System.out.println();

		// Deallocate the recognizer and exit
		try {
			rec.deallocate();
		} catch (EngineException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (EngineStateError e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.exit(0);
	}

	public static void main(String args[]) {
		try {
			
			System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
			Central.registerEngineCentral("com.cloudgarden.speech.CGEngineCentral");
			System.out.println(System.getProperty("java.library.path"));
			System.setProperty("mbrola.base", "C:/Documents and Settings/ssharma/My Documents/Downloads/freetts-1.2.2-bin(2)/freetts-1.2/lib");
			// Create a recognizer that supports English.
			  RecognizerModeDesc desc = new RecognizerModeDesc(Locale.US,Boolean.TRUE);
			rec = Central.createRecognizer(
							new EngineModeDesc(Locale.US));
			EngineList list = Central.availableRecognizers(desc);
			System.out.println(list);
			
			// Start up the recognizer
			rec.allocate();
			RuleGrammar gram=null;
			// Load the grammar from a file, and enable it
			try{
			FileReader reader = new FileReader("example/mygram.gram");
			gram = rec.loadJSGF(reader);
			}catch(Exception e)
			{
				System.out.println("::::::::::::::::::::");
				e.printStackTrace();
			}
			gram.setEnabled(true);
	
			// Add the listener to get results
			rec.addResultListener(new Helloworld());
	
			// Commit the grammar
			rec.commitChanges();
	
			// Request focus and start listening
			rec.requestFocus();
			rec.resume();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}