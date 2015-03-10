package goit.iavorskyi.learningUnit;

import goit.iavorskyi.dao.ArticleDAO;

public class Article {
	
	private String author = null;
	private String header = null;
	private String linkToText = null;
	private String text = null;
	
	public String getLinkToText() {
		return linkToText;
	}
	public void setLinkToText(String linkToText) {
		this.linkToText = linkToText;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getHeader() {
		return header;
	}
	public void setHeader(String header) {
		this.header = header;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void save(String author, String header, String text) {
		ArticleDAO articleDAO = new ArticleDAO();
		articleDAO.save(author, header, text);
	}
}
