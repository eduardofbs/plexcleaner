package br.com.eduardofbs.plexcleaner.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Part {

	private int id;
	private String key;
	private long duration;
	private String file;
	private long size;
	private String container;
	private String indexes;
	private String videoProfile;

	public int getId() {
		return id;
	}

	@XmlAttribute(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	@XmlAttribute(name = "key")
	public void setKey(String key) {
		this.key = key;
	}

	public long getDuration() {
		return duration;
	}

	@XmlAttribute(name = "duration")
	public void setDuration(long duration) {
		this.duration = duration;
	}

	public String getFile() {
		return file;
	}

	@XmlAttribute(name = "file")
	public void setFile(String file) {
		this.file = file;
	}

	public long getSize() {
		return size;
	}

	@XmlAttribute(name = "size")
	public void setSize(long size) {
		this.size = size;
	}

	public String getContainer() {
		return container;
	}

	@XmlAttribute(name = "container")
	public void setContainer(String container) {
		this.container = container;
	}

	public String getIndexes() {
		return indexes;
	}

	@XmlAttribute(name = "indexes")
	public void setIndexes(String indexes) {
		this.indexes = indexes;
	}

	public String getVideoProfile() {
		return videoProfile;
	}

	@XmlAttribute(name = "videoProfile")
	public void setVideoProfile(String videoProfile) {
		this.videoProfile = videoProfile;
	}

	@Override
	public String toString() {
		return "Part [id=" + id + ", key=" + key + ", duration=" + duration + ", file=" + file + ", size=" + size
				+ ", container=" + container + ", indexes=" + indexes + ", videoProfile=" + videoProfile + "]";
	}

}
