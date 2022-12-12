package com.example.pmdm_08_retrofit.modelos;

public class Photo{
	private int albumId;
	private int id;
	private String title;
	private String url;
	private String thumbnailUrl;

	public void setAlbumId(int albumId){
		this.albumId = albumId;
	}

	public int getAlbumId(){
		return albumId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setThumbnailUrl(String thumbnailUrl){
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	@Override
 	public String toString(){
		return 
			"Photo{" + 
			"albumId = '" + albumId + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",thumbnailUrl = '" + thumbnailUrl + '\'' + 
			"}";
		}
}
