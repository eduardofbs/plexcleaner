package br.com.eduardofbs.plexcleaner.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Directory {

	private int allowSync;

	private String art;

	private String composite;

	private int filters;

	private int refreshing;

	private String thumb;

	private String key;

	private String type;

	private String title;

	private String agent;

	private String scanner;

	private String language;

	private String uuid;

	private long updatedAt;

	private long createdAt;

	private Location location;

	public int getAllowSync() {
		return allowSync;
	}

	@XmlAttribute(name = "allowSync")
	public void setAllowSync(int allowSync) {
		this.allowSync = allowSync;
	}

	public String getArt() {
		return art;
	}

	@XmlAttribute(name = "art")
	public void setArt(String art) {
		this.art = art;
	}

	public String getComposite() {
		return composite;
	}

	@XmlAttribute(name = "composite")
	public void setComposite(String composite) {
		this.composite = composite;
	}

	public int getFilters() {
		return filters;
	}

	@XmlAttribute(name = "filters")
	public void setFilters(int filters) {
		this.filters = filters;
	}

	public int getRefreshing() {
		return refreshing;
	}

	@XmlAttribute(name = "refreshing")
	public void setRefreshing(int refreshing) {
		this.refreshing = refreshing;
	}

	public String getThumb() {
		return thumb;
	}

	@XmlAttribute(name = "thumb")
	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getKey() {
		return key;
	}

	@XmlAttribute(name = "key")
	public void setKey(String key) {
		this.key = key;
	}

	public String getType() {
		return type;
	}

	@XmlAttribute(name = "type")
	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	@XmlAttribute(name = "title")
	public void setTitle(String title) {
		this.title = title;
	}

	public String getAgent() {
		return agent;
	}

	@XmlAttribute(name = "agent")
	public void setAgent(String agent) {
		this.agent = agent;
	}

	public String getScanner() {
		return scanner;
	}

	@XmlAttribute(name = "scanner")
	public void setScanner(String scanner) {
		this.scanner = scanner;
	}

	public String getLanguage() {
		return language;
	}

	@XmlAttribute(name = "language")
	public void setLanguage(String language) {
		this.language = language;
	}

	public String getUuid() {
		return uuid;
	}

	@XmlAttribute(name = "uuid")
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public long getUpdatedAt() {
		return updatedAt;
	}

	@XmlAttribute(name = "updatedAt")
	public void setUpdatedAt(long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public long getCreatedAt() {
		return createdAt;
	}

	@XmlAttribute(name = "createdAt")
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}

	public Location getLocation() {
		return location;
	}

	@XmlElement(name = "Location")
	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "Directory [allowSync=" + allowSync + ", art=" + art + ", composite=" + composite + ", filters="
				+ filters + ", refreshing=" + refreshing + ", thumb=" + thumb + ", key=" + key + ", type=" + type
				+ ", title=" + title + ", agent=" + agent + ", scanner=" + scanner + ", language=" + language
				+ ", uuid=" + uuid + ", updatedAt=" + updatedAt + ", createdAt=" + createdAt + ", location=" + location
				+ "]";
	}

}
