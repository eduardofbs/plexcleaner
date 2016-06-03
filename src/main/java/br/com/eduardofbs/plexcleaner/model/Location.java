package br.com.eduardofbs.plexcleaner.model;

import javax.xml.bind.annotation.XmlAttribute;

public class Location {

	private int id;

	private String path;

	public int getId() {
		return id;
	}

	@XmlAttribute(name = "id")
	public void setId(int id) {
		this.id = id;
	}

	public String getPath() {
		return path;
	}

	@XmlAttribute(name = "path")
	public void setPath(String path) {
		this.path = path;
	}

	@Override
	public String toString() {
		return "Location [id=" + id + ", path=" + path + "]";
	}

}
