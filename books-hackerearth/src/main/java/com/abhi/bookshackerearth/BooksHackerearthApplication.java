package com.abhi.bookshackerearth;

import com.abhi.bookshackerearth.entity.Book;
import com.abhi.bookshackerearth.dao.Dao;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;


@SpringBootApplication
public class BooksHackerearthApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksHackerearthApplication.class, args);
	}

	Book[] books = null;
	//the postconstruct runs after dependency injection is complete, but this will hold the server from starting up
	//so postconstruct should be something small and critical as server wont start without it
	@PostConstruct
	void init() {
		System.err.println("This is the post construct method");
	}
//	private void init() throws IOException {
//		//build url. here url is the link where json object is returned
//		URL obj = new URL("https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json");
//		//open connection to this url
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		//determine the type of request. since data is fetched the type is GET
//		con.setRequestMethod("GET");
//		//get the response code from the connection
//		int responseCode = con.getResponseCode();
//		System.out.println("GET Response Code :: " + responseCode);
//		//if response if OK meaning 200, the url is working and returning output so proceed
//		if (responseCode == HttpURLConnection.HTTP_OK) {
//			//we are using buffered reader to get output of the url which is JSON array of objects
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			//stringbuffer to allow less usage of space compared to string
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//			//close the buffered reader
//
//			String body = response.toString();
//			//now we need to map this json object array to individual Entity objects
//			ObjectMapper mapper = new ObjectMapper();
//			//objectmapper is used for mapping response to POJO
//
//			//books is an array and readValue() will store the response string as array of ENTITY POJO objects
//			books = mapper.readValue(body, Book[].class);
//			//now books array has multiple objects of Book type saved as array
//			List<Book> langList = new ArrayList(Arrays.asList(books));
//			//after conversion to list, it is easier to play with as LIST is a COLLECTION
//			Dao dao = new Dao(); //dao object which will handle database related queries
//			//----- We can try using @autowired annotation for dependency injection of Dao object
//			//for each object in LIST collection, we want to save it in the database which is in memory H2 database
//			langList.forEach(object -> dao.saveIt(object));
//		}
//	}

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() throws IOException {
		System.out.println("hello world, I have just started up");

		//build url. here url is the link where json object is returned
		URL obj = new URL("https://s3-ap-southeast-1.amazonaws.com/he-public-data/books8f8fe52.json");
		//open connection to this url
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		//determine the type of request. since data is fetched the type is GET
		con.setRequestMethod("GET");
		//get the response code from the connection
		int responseCode = con.getResponseCode();
		System.out.println("GET Response Code :: " + responseCode);
		//if response if OK meaning 200, the url is working and returning output so proceed
		if (responseCode == HttpURLConnection.HTTP_OK) {
			//we are using buffered reader to get output of the url which is JSON array of objects
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			//stringbuffer to allow less usage of space compared to string
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			//close the buffered reader

			String body = response.toString();
			//now we need to map this json object array to individual Entity objects
			ObjectMapper mapper = new ObjectMapper();
			//objectmapper is used for mapping response to POJO

			//books is an array and readValue() will store the response string as array of ENTITY POJO objects
			books = mapper.readValue(body, Book[].class);
			//now books array has multiple objects of Book type saved as array
			List<Book> langList = new ArrayList(Arrays.asList(books));
			//after conversion to list, it is easier to play with as LIST is a COLLECTION
			Dao dao = new Dao(); //dao object which will handle database related queries
			//----- We can try using @autowired annotation for dependency injection of Dao object
			//for each object in LIST collection, we want to save it in the database which is in memory H2 database
			langList.forEach(object -> dao.saveIt(object));
		}

	}
}
