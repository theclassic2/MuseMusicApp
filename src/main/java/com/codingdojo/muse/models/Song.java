package com.codingdojo.muse.models;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="songs")
public class Song {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Song name is required!")
    @Size(min=3, message="Song Name must be at least 3 characters.")
    private String name;
	
	@NotEmpty(message="Artist is required!")
    @Size(min=3, message="Artist must be at least 3 characters.")
    private String artist;
	
	@NotNull
    private String album;
	
	@Min(value=1600 ,message="The year must be at least 1600.")
	@Max(value=2025, message="The year cannot be in the future.")
	@NotNull(message="Please select the year the song was released.")
	private Integer releaseYear;
	
	@NotEmpty(message="Genre is required.")
    private String genre;
	
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creator_id")
    private User creator;
   
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    		name = "usersLikedSongs",
    		joinColumns = @JoinColumn(name = "song_id"),
    		inverseJoinColumns = @JoinColumn(name= "user_id")
    )
    private List<User> likedUsers;
    
    public Song() {}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public Integer getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(Integer releaseYear) {
		this.releaseYear = releaseYear;
	}
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public User getCreator() {
		return creator;
	}
	public void setCreator(User creator) {
		this.creator = creator;
	}
	public List<User> getLikedUsers() {
		return likedUsers;
	}
	public void setLikedUsers(List<User> likedUsers) {
		this.likedUsers = likedUsers;
	}
}
