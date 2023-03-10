package com.example.wordGame;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SpringBootApplication
public class WordGameApplication {
	public static String randomWord;

	public static void main(String[] args) {
		SpringApplication.run(WordGameApplication.class, args);


		try {
			randomWord= getRandomWord();
			while(randomWord.length()!=5)
			{
				randomWord = getRandomWord();
			}
			System.out.println(randomWord);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			throw new RuntimeException(e);
		}
	}
	private static String getRandomWord() throws IOException, JSONException {
		String api_key = "169c08ab92msh0b7fe475c8aa506p19466fjsn1bbfbe410e1a";
		String host = "wordsapiv1.p.rapidapi.com";

		URL url = new URL("https://wordsapiv1.p.rapidapi.com/words/?random=true&lettersMin=5&lettersMax=5&hasDetails=definitions,partsOfSpeech,synonyms,antonyms,examples");
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

		urlConnection.setRequestProperty("X-RapidAPI-Key", api_key);
		urlConnection.setRequestProperty("X-RapidAPI-Host", host);

		BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		//my reader
		String inputLine;

		StringBuilder response = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		JSONObject obj = new JSONObject(response.toString());

		System.out.println(obj);
		String word = obj.getString("word");
		String[] words = word.split(" ");
		return word;
	}

}
