package br.com.eduardofbs.plexcleaner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import br.com.eduardofbs.plexcleaner.model.Directory;
import br.com.eduardofbs.plexcleaner.model.Location;
import br.com.eduardofbs.plexcleaner.model.Media;
import br.com.eduardofbs.plexcleaner.model.Part;
import br.com.eduardofbs.plexcleaner.model.Video;

public class Main {

	public static void main(String[] args) throws IOException, ParserConfigurationException,
			UnsupportedOperationException, SAXException, JAXBException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter your Plex login");
		String login = br.readLine();

		System.out.println("Enter your Plex password");
		String password = br.readLine();

		System.out.println("Enter your Plex Media Server hostname/ip");
		String host = br.readLine();

		System.out.println("Enter your Plex Media Server port");
		String port = br.readLine();

		String authenticationToken = Controller.getAuthorizationToken(login, password);

		List<Directory> libraries = Controller.listLibraries(authenticationToken, host, port);

		System.out.println("Found libraries:");

		for (Directory library : libraries) {
			String title = library.getTitle();

			Location location = library.getLocation();
			String path = location.getPath();

			System.out.println(String.format("Title: %s", title, path));

			String key = library.getKey();
			String type = library.getType();

			List<Video> videos = Controller.listWatched(authenticationToken, host, port, key, type);

			if (videos != null) {

				for (Video video : videos) {
					Media media = video.getMedia();

					Part part = media.getPart();

					String file = part.getFile();

					System.out.println(String.format("Should I erase %s?", file));
					String answer = br.readLine();

					if (answer.equalsIgnoreCase("Y")) {
						System.out.println("Erased!");
					} else {
						System.out.println("Not Erased!");
					}

				}

			}

		}
		
	}

}
