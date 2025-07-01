package com.codingdojo.muse.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codingdojo.muse.models.Song;
import com.codingdojo.muse.models.User;
import com.codingdojo.muse.repositories.SongRepository;
import com.codingdojo.muse.repositories.UserRepository;

@Service
public class SongService {
	@Autowired
	private SongRepository songRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	public Song createSong(Song songData) {
		return songRepo.save(songData);
	}
	
	public List<Song> readAllSongs() {
		return songRepo.findAll();
	}
	
	public Song readOneSong(Long id) {
		Optional<Song> potentialSong =songRepo.findById(id);
		return potentialSong.isPresent() ? potentialSong.get() : null;
	}
	
	public Song updateSong(Song songData) {
		return songRepo.save(songData);
	}
	
	public void deleteSong(Long id) {
		songRepo.deleteById(id);
	}
	
	public void addUserLike(Long userId, Long songId) {
		Song currentSong = this.readOneSong(songId);
		User currentUser = userRepo.findById(userId).get();
		currentSong.getLikedUsers().add(currentUser);
		songRepo.save(currentSong);
	}
	
	public void removeUserLike(Long userId, Long songId) {
		Song currentSong = this.readOneSong(songId);
		User currentUser = userRepo.findById(userId).get();
		currentSong.getLikedUsers().remove(currentUser);
		songRepo.save(currentSong);
	}
	
	public List<Song> getSongsByGenre(String genre) {
		if (genre == null || genre.isEmpty() || genre.equals("all")) {
			return songRepo.findAll();
		}
	    return songRepo.findByGenre(genre);
	}
	
    public List<String> getAllGenres() {
        return songRepo.findAll()
            .stream()
            .map(Song::getGenre)
            .distinct()
            .collect(Collectors.toList());
    }
}
