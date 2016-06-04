package br.com.eduardofbs.plexcleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import br.com.eduardofbs.plexcleaner.model.Directory;
import br.com.eduardofbs.plexcleaner.model.MediaContainer;
import br.com.eduardofbs.plexcleaner.model.Video;

public class Controller {
	
	private static final String DEFAULT_HOST = "127.0.0.1";
	private static final String DEFAULT_PORT = "32400";

	public static String getAuthorizationToken(String login, String password)
			throws ClientProtocolException, IOException {
		String tokenRequestURL = "https://plex.tv/users/sign_in.json";

		HttpClient client = HttpClientBuilder.create().build();

		HttpPost post = new HttpPost(tokenRequestURL);

		post.addHeader("X-Plex-Product", "PlexCleaner");
		post.addHeader("X-Plex-Version", "1");
		post.addHeader("X-Plex-Client-Identifier", "30c37510-3154-4cc5-a6f4-fdd2f6336a6d");

		List<NameValuePair> postParameters = new ArrayList<NameValuePair>();

		BasicNameValuePair loginParam = new BasicNameValuePair("user[login]", login);
		BasicNameValuePair passwordParam = new BasicNameValuePair("user[password]", password);

		postParameters.add(loginParam);
		postParameters.add(passwordParam);

		post.setEntity(new UrlEncodedFormEntity(postParameters));

		HttpResponse response = client.execute(post);

		BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}

		JSONObject responseObject = new JSONObject(result.toString());

		JSONObject user = responseObject.getJSONObject("user");

		String authenticationToken = user.getString("authentication_token");

		System.out.println(String.format("authentication token is %s", authenticationToken));

		return authenticationToken;
	}

	public static List<Directory> listLibraries(String authenticationToken) throws ClientProtocolException, IOException,
			ParserConfigurationException, UnsupportedOperationException, SAXException, JAXBException {
		return listLibraries(authenticationToken, DEFAULT_HOST, DEFAULT_PORT);
	}

	public static List<Directory> listLibraries(String authenticationToken, String host, String port)
			throws ClientProtocolException, IOException, ParserConfigurationException, UnsupportedOperationException,
			SAXException, JAXBException {

		HttpClient client = HttpClientBuilder.create().build();

		HttpGet listLibraries = new HttpGet(String.format("http://%s:%s/library/sections", host, port));

		listLibraries.addHeader("X-Plex-Token", authenticationToken);

		HttpResponse execute = client.execute(listLibraries);

		JAXBContext jaxbContext = JAXBContext.newInstance(MediaContainer.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		MediaContainer mediaContainer = (MediaContainer) unmarshaller.unmarshal(execute.getEntity().getContent());
		
		List<Directory> directories = mediaContainer.getDirectories();
		
		return directories;
		
	}
	
	public static List<Video> listWatched(String authenticationToken, String key, String type) throws ClientProtocolException, IOException, JAXBException {
		
		HttpClient client = HttpClientBuilder.create().build();

		String url = String.format("http://%s:%s/library/sections/%s/all?unwatched=0", DEFAULT_HOST, DEFAULT_PORT, key);
		
		if (type.equalsIgnoreCase("show")) {
			url += "&type=4";
		}
		
		HttpGet listLibraries = new HttpGet(url);

		listLibraries.addHeader("X-Plex-Token", authenticationToken);

		HttpResponse execute = client.execute(listLibraries);

		JAXBContext jaxbContext = JAXBContext.newInstance(MediaContainer.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		MediaContainer mediaContainer = (MediaContainer) unmarshaller.unmarshal(execute.getEntity().getContent());
		
		List<Video> videos = mediaContainer.getVideos();
		
		return videos;
		
	}
	
	public static List<Video> listWatched(String authenticationToken, String host, String port, String key, String type) throws ClientProtocolException, IOException, JAXBException {
		
		HttpClient client = HttpClientBuilder.create().build();

		String url = String.format("http://%s:%s/library/sections/%s/all?unwatched=0", host, port, key);
		
		if (type.equalsIgnoreCase("show")) {
			url += "&type=4";
		}
		
		HttpGet listLibraries = new HttpGet(url);

		listLibraries.addHeader("X-Plex-Token", authenticationToken);

		HttpResponse execute = client.execute(listLibraries);

		JAXBContext jaxbContext = JAXBContext.newInstance(MediaContainer.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		
		MediaContainer mediaContainer = (MediaContainer) unmarshaller.unmarshal(execute.getEntity().getContent());
		
		List<Video> videos = mediaContainer.getVideos();
		
		return videos;
		
	}

	public static void eraseFiles(List<String> filesToErase) throws IOException {
		
		String test = System.getProperty("test", "false");
		
		boolean isTest = Boolean.parseBoolean(test);
		
		if (filesToErase != null && !filesToErase.isEmpty()) {
			
			for (String filePath : filesToErase) {
				
				File file = new File(filePath);
				
				if (!isTest) {
					Files.deleteIfExists(file.toPath());
					System.out.println(String.format("File %s erased", file));
				}
				
			}
			
		}
		
	}

}
