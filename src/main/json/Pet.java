package main.json;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonProperty;

@XmlRootElement
@XmlType(propOrder={"id", "name"})
@SuppressWarnings("unchecked")
public class Pet {

	private Integer id;
	
	private String name;

	@JsonProperty( "photoUrls" )
	private String photoUrls[];
	
	 
	private String status;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@JsonProperty( "category" )
	private Category category;
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	@JsonProperty( "tags" )
	private List<Tag> tags;

	
	public List getTag() {
		return tags;
	}

	public void setTag(List tags) {
		this.tags = tags;
	}
	
	@XmlRootElement
	@XmlType(propOrder={"id", "name"})
 	private class Category {
		private Integer id;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setCategoryName(String name) {
			this.name = name;
		}
		private String name;
		
		
		
		
	}
	
	@XmlRootElement
	@XmlType(propOrder={"id", "name"})
	@SuppressWarnings("unchecked")
	private class Tag {
		private Integer id;
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private String name;
	}
	
	
}
	
	