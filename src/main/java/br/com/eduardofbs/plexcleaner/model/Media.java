package br.com.eduardofbs.plexcleaner.model;

import javax.xml.bind.annotation.XmlElement;

public class Media {

	private Part part;

	public Part getPart() {
		return part;
	}

	@XmlElement(name = "Part")
	public void setPart(Part part) {
		this.part = part;
	}

	@Override
	public String toString() {
		return "Media [part=" + part + "]";
	}

}
