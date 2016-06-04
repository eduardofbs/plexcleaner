package br.com.eduardofbs.plexcleaner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

		String authenticationToken = Controller.getAuthorizationToken(login, password);

		List<Directory> libraries = Controller.listLibraries(authenticationToken);

		System.out.println("Found libraries:");

		for (Directory library : libraries) {
			String title = library.getTitle();

			Location location = library.getLocation();
			String path = location.getPath();

			System.out.println(String.format("Title: %s", title, path));

			String key = library.getKey();
			String type = library.getType();

			List<Video> videos = Controller.listWatched(authenticationToken, key, type);

			if (videos != null) {
				
				List<String> filesToErase = new ArrayList<String>();

				System.out.println(String.format("All these files will be erased"));
				
				for (Video video : videos) {
					Media media = video.getMedia();

					Part part = media.getPart();

					String file = part.getFile();
					filesToErase.add(file);
				}
				
				System.out.println(String.format("Continue?"));
				String answer = br.readLine();

				if (answer.equalsIgnoreCase("Y")) {
					Controller.eraseFiles(filesToErase);
				} else {
					System.out.println("Not Erased!");
				}

			}

		}
		
	}

}
