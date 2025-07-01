package com.codingdojo.muse.controllers;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.codingdojo.muse.models.Song;
import com.codingdojo.muse.services.SongService;
import com.codingdojo.muse.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class SongController {
	@Autowired
	private SongService songServ;
	
	@Autowired
	private UserService userServ;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/songs")
	public String dashboard(Model viewModel) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		viewModel.addAttribute("currentUser", userServ.readUserById(userID));
		return "dashboard.jsp";
	}
	
	@GetMapping("/songs/curated")
	public String addedSongs(Model viewModel) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		viewModel.addAttribute("currentUser", userServ.readUserById(userID));
		return "curatedMusic.jsp";
	}
	
	@GetMapping("/songs/new")
	public String newSong(Model viewModel,@ModelAttribute("newSong") Song newSong) {
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Alternative");
		genres.add("Country");
		genres.add("Classical");
		genres.add("Electronic");
		genres.add("HipHop");
		genres.add("Jazz");
		genres.add("Latin");
		genres.add("Pop");
		genres.add("Rock");
		genres.add("Rnb");
		genres.add("Soundtracks");
		viewModel.addAttribute("genres", genres);
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		return "addSong.jsp";
	}
	
	@GetMapping("/songs/discover")
	public String viewAllSongs(Model viewModel) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		viewModel.addAttribute("allSongs", songServ.readAllSongs());
		return "songDiscovery.jsp";
	}
	
	
	@PostMapping("/songs/new")
	public String createSong(Model viewModel,@Valid @ModelAttribute("newSong") Song newSong,
			BindingResult result) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			ArrayList<String> genres = new ArrayList<String>();
			genres.add("Alternative");
			genres.add("Country");
			genres.add("Classical");
			genres.add("Electronic");
			genres.add("HipHop");
			genres.add("Jazz");
			genres.add("Latin");
			genres.add("Pop");
			genres.add("Rock");
			genres.add("Rnb");
			genres.add("Soundtracks");
			viewModel.addAttribute("genres", genres);
			return "addSong.jsp";
		}
		newSong.setCreator(userServ.readUserById(userID));
		Song newEntry = songServ.createSong(newSong);
		return "redirect:/songs/"+newEntry.getId();
	}
	
	@GetMapping("/songs/{id}")
	public String viewSong(@PathVariable Long id, Model viewModel) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		viewModel.addAttribute("currentSong", songServ.readOneSong(id));
		viewModel.addAttribute("currentUser", userServ.readUserById(userID));
		return "songDetails.jsp";
	}
	
	@GetMapping("/songs/{id}/profile")
	public String viewProfile(@PathVariable Long id, Model viewModel) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		viewModel.addAttribute("userProfile", userServ.readUserById(id));
		return "musicProfile.jsp";
	}
	
	@GetMapping("/songs/{id}/edit") 
	public String editSong(@PathVariable Long id, Model viewModel) {
		ArrayList<String> genres = new ArrayList<String>();
		genres.add("Alternative");
		genres.add("Country");
		genres.add("Classical");
		genres.add("Electronic");
		genres.add("HipHop");
		genres.add("Jazz");
		genres.add("Latin");
		genres.add("Pop");
		genres.add("Rock");
		genres.add("Rnb");
		genres.add("Soundtracks");
		viewModel.addAttribute("genres", genres);
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		Song currentSong = songServ.readOneSong(id);
		if (!userID.equals(currentSong.getCreator().getId())) {
			return "redirect:/";
		}
		viewModel.addAttribute("currentSong", songServ.readOneSong(id));
		return "editSong.jsp";
	}
	
	@PutMapping("/songs/{id}/edit") 
	public String updateSong(Model viewModel, @PathVariable Long id, @Valid @ModelAttribute("currentSong") Song currentSong,
			 BindingResult result) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		Song userSong = songServ.readOneSong(id);
		if (!userID.equals(userSong.getCreator().getId())) {
			return "redirect:/";
		}
		if (result.hasErrors()) {
			ArrayList<String> genres = new ArrayList<String>();
			genres.add("Alternative");
			genres.add("Country");
			genres.add("Classical");
			genres.add("Electronic");
			genres.add("HipHop");
			genres.add("Jazz");
			genres.add("Latin");
			genres.add("Pop");
			genres.add("Rock");
			genres.add("Rnb");
			genres.add("Soundtracks");
			viewModel.addAttribute("genres", genres);
			return "editSong.jsp";
		}
		currentSong.setLikedUsers(userSong.getLikedUsers());
		currentSong.setCreator(userServ.readUserById(userID));
		Song newEntry = songServ.updateSong(currentSong);
		return "redirect:/songs/"+newEntry.getId();
	}
	
	@DeleteMapping("/songs/{id}/delete")
	public String deleteSong(@PathVariable Long id) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		Song currentSong = songServ.readOneSong(id);
		if (!userID.equals(currentSong.getCreator().getId())) {
			return "redirect:/";
		}
		songServ.deleteSong(id);
		return "redirect:/songs";
	}
	
	@PostMapping("/songs/{id}/likeSong")
	public String likeSong(@PathVariable Long id) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		songServ.addUserLike(userID, id);
		return "redirect:/songs/"+id;
	}
	
	@DeleteMapping("/songs/{id}/unlikeSong")
	public String unlikeSong(@PathVariable Long id) {
		Long userID = (Long) session.getAttribute("userID");
		if (userID == null) {
			return "redirect:/";
		}
		songServ.removeUserLike(userID, id);
		return "redirect:/songs/"+id;
	}
}
