package com.jy.modules.drools.domain;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
//@Entity
//@Data
//@JsonIgnoreProperties(value = {"title","url"})
public class LearnResource {

    private Long id;
    private String author;
    private String title;
    private String url;

    public LearnResource() {
    }

    public LearnResource(String author, String title, String url) {
        this.author = author;
        this.title = title;
        this.url = url;
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
    
    
}
