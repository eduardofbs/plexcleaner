package br.com.eduardofbs.plexcleaner.model;

import javax.xml.bind.annotation.XmlElement;

public class Video {

	private Media media;

	public Media getMedia() {
		return media;
	}

	@XmlElement(name = "Media")
	public void setMedia(Media media) {
		this.media = media;
	}

	@Override
	public String toString() {
		return "Video [media=" + media + "]";
	}

}
