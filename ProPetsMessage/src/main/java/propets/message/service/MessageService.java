package propets.message.service;


import propets.message.dto.NewPostDto;
import propets.message.dto.PostDto;
import propets.message.dto.ViewPostDto;
//M
public interface MessageService {
	
	PostDto createPost(NewPostDto newPostDto, String ownerId);

	PostDto updatePost(NewPostDto newPostDto, String id);

	PostDto deletePost(String id);

	PostDto getPostById(String id);

	ViewPostDto viewPosts(int items_on_page, int current_page, String user);

	void complainOnPost(String id);

	void hidePost(String id, String user);

}
