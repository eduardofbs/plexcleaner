package br.com.eduardofbs.plexcleaner.model;

import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "MediaContainer")
public class MediaContainer {

	private int size;

	private int allowSync;

	private String identifier;

	private String mediaTagPrefix;

	private String mediaTagVersion;

	private String title1;

	private List<Directory> directories;

	private List<Video> videos;

	public int getSize() {
		return size;
	}

	@XmlAttribute(name = "size")
	public void setSize(int size) {
		this.size = size;
	}

	public int getAllowSync() {
		return allowSync;
	}

	@XmlAttribute(name = "allowSync")
	public void setAllowSync(int allowSync) {
		this.allowSync = allowSync;
	}

	public String getIdentifier() {
		return identifier;
	}

	@XmlAttribute(name = "identifier")
	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getMediaTagPrefix() {
		return mediaTagPrefix;
	}

	@XmlAttribute(name = "mediaTagPrefix")
	public void setMediaTagPrefix(String mediaTagPrefix) {
		this.mediaTagPrefix = mediaTagPrefix;
	}

	public String getMediaTagVersion() {
		return mediaTagVersion;
	}

	@XmlAttribute(name = "mediaTagVersion")
	public void setMediaTagVersion(String mediaTagVersion) {
		this.mediaTagVersion = mediaTagVersion;
	}

	public String getTitle1() {
		return title1;
	}

	@XmlAttribute(name = "title1")
	public void setTitle1(String title1) {
		this.title1 = title1;
	}

	public List<Directory> getDirectories() {
		return directories;
	}

	@XmlElement(name = "Directory")
	public void setDirectories(List<Directory> directories) {
		this.directories = directories;
	}

	@XmlElement(name = "Video")
	public List<Video> getVideos() {
		return videos;
	}

	public void setVideos(List<Video> videos) {
		this.videos = videos;
	}

	@Override
	public String toString() {
		return "MediaContainer [size=" + size + ", allowSync=" + allowSync + ", identifier=" + identifier
				+ ", mediaTagPrefix=" + mediaTagPrefix + ", mediaTagVersion=" + mediaTagVersion + ", title1=" + title1
				+ ", directories=" + directories + ", videos=" + videos + "]";
	}

}
