package br.com.eduardofbs.plexcleaner;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import br.com.eduardofbs.plexcleaner.model.Directory;
import br.com.eduardofbs.plexcleaner.model.Media;
import br.com.eduardofbs.plexcleaner.model.Part;
import br.com.eduardofbs.plexcleaner.model.Video;

public class Main {

	public static void main(String[] args)
			throws IOException, ParserConfigurationException, SAXException, JAXBException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		System.out.println("Enter your Plex login");
		String login = br.readLine();

		System.out.println("Enter your Plex password");
		String password = br.readLine();

		String authenticationToken = Controller.getAuthorizationToken(login, password);

		List<Directory> libraries = Controller.listLibraries(authenticationToken);

		System.out.println("Found libraries:");

		long allLibrariesSize = 0L;

		for (Directory library : libraries) {
			String title = library.getTitle();

			System.out.println(String.format("Title: %s", title));

			String key = library.getKey();
			String type = library.getType();

			List<Video> videos = Controller.listWatched(authenticationToken, key, type);

			if (videos == null)
				continue;

			List<String> filesToErase = new ArrayList<>();

			System.out.println("All these files will be erased");

			long allVideosSize = 0L;

			for (Video video : videos) {
				Media media = video.getMedia();

				Part part = media.getPart();

				String filePath = part.getFile();

				File file = new File(filePath);

				long fileSize = file.length();

				allVideosSize += fileSize;

				String prettyFileSize = Main.readableFileSize(fileSize);

				System.out.println(String.format("%s %s", filePath, prettyFileSize));

				filesToErase.add(filePath);
			}

			String prettyAllSize = Main.readableFileSize(allVideosSize);

			System.out.println(String.format("%s will be erased! Continue? [Y] Yes, [N] No", prettyAllSize));
			String answer = br.readLine();

			if (answer.equalsIgnoreCase("Y")) {
				Controller.eraseFiles(filesToErase);
				allLibrariesSize += allVideosSize;
			} else {
				System.out.println("Not Erased!");
			}

		}

		String prettyAllLibreriesSize = Main.readableFileSize(allLibrariesSize);

		System.out.println(String.format("%s recovered after cleaning the libraries", prettyAllLibreriesSize));

	}

	// as seen on
	// https://stackoverflow.com/questions/3263892/format-file-size-as-mb-gb-etc
	public static String readableFileSize(long size) {
		if (size <= 0)
			return "0";
		final String[] units = new String[] { "B", "kB", "MB", "GB", "TB" };
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return new DecimalFormat("#,##0.#").format(size / Math.pow(1024, digitGroups)) + " " + units[digitGroups];
	}

}
