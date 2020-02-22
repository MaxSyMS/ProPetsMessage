package propets.message.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import propets.message.dto.NewPostDto;
import propets.message.dto.PostDto;
import propets.message.dto.ViewPostDto;
import propets.message.service.MessageService;

@RestController
@RequestMapping("/{lang}/message/v1")
//M
public class MessageController {

	@Autowired
	MessageService messageService;

	@PostMapping("/{ownerId}")
	public PostDto createPost(@RequestBody NewPostDto newPostDto, @PathVariable String ownerId) {
		return messageService.createPost(newPostDto, ownerId);
	}

	@PutMapping("/{id}")
	public PostDto updatePost(@RequestBody NewPostDto newPostDto, @PathVariable String id) {
		return messageService.updatePost(newPostDto, id);
	}

	@DeleteMapping("/{id}")
	public PostDto deletePost(@PathVariable String id) {
		return messageService.deletePost(id);
	}

	@GetMapping("/{id}")
	public PostDto getPostById(@PathVariable String id) {
		return messageService.getPostById(id);
	}

	@GetMapping("/view/{user}")
	public ViewPostDto viewPosts(@PathVariable String user, @RequestParam int itemsOnPage, @RequestParam int currentPage) {
		return messageService.viewPosts(itemsOnPage, currentPage, user);
	}
	
	@PutMapping("/complain/{id}")
	public void complainOnPost(@PathVariable String id) {
		messageService.complainOnPost(id);
	}
	
	@PutMapping("/hide/{id}/{user}")
	public void hidePost(@PathVariable String id, @PathVariable String user) {
		messageService.hidePost(id, user);
	}
	

}
