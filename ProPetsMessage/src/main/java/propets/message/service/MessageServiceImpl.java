package propets.message.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import propets.message.convertor.MessageConvertor;
import propets.message.dao.MessageRepository;
import propets.message.domain.Post;
import propets.message.dto.NewPostDto;
import propets.message.dto.PostDto;
import propets.message.dto.ViewPostDto;
import propets.message.exseptions.PostNotFoundException;

@Service
//M
public class MessageServiceImpl implements MessageService {
	@Autowired
	MessageRepository messageRepository;
	@Autowired
	MessageConvertor convertor;

	@Override
	public PostDto createPost(NewPostDto newPostDto, String ownerId) {
		Post post = Post.builder().ownerId(ownerId).postDate(LocalDateTime.now()).text(newPostDto.getText())
				.blackList(new HashSet<String>()).images(checkImages(newPostDto.getImages())).complpain(false).build();
		post = messageRepository.save(post);
		return convertor.convertToPostDto(post);
	}

	Set<String> checkImages(Set<String> images) {
		if (images != null) {
			return images;
		} else {
			return new HashSet<String>();
		}
	}

	@Override
	public PostDto updatePost(NewPostDto newPostDto, String id) {
		Post post = messageRepository.findById(id).orElseThrow(PostNotFoundException::new);
		if (newPostDto.getText() != null) {
			post.setText(newPostDto.getText());
		}
		if (newPostDto.getImages() != null) {
			newPostDto.getImages().forEach(post::addImages);
			;
		}
		messageRepository.save(post);
		return convertor.convertToPostDto(post);
	}

	@Override
	public PostDto deletePost(String id) {
		Post post = messageRepository.findById(id).orElseThrow(PostNotFoundException::new);
		messageRepository.delete(post);
		return convertor.convertToPostDto(post);
	}

	@Override
	public PostDto getPostById(String id) {
		Post post = messageRepository.findById(id).orElseThrow(PostNotFoundException::new);
		return convertor.convertToPostDto(post);
	}

	@Override
	public ViewPostDto viewPosts(int itemsOnPage, int currentPage, String user) {
		Pageable paging = PageRequest.of(currentPage, itemsOnPage);
		/*
		 * INFO The findAll(Pageable pageable) method by default returns a Page<T>
		 * object. However, we can choose to return either a Page<T>, a Slice<T> or a
		 * List<T> from any of our custom methods returning a paginated data. A Page<T>
		 * instance, in addition to having the list of Products, also knows about the
		 * total number of available pages. It triggers an additional count query to
		 * achieve it. To avoid such an overhead cost, we can instead return a Slice<T>
		 * or a List<T>. A Slice only knows about whether the next slice is available or
		 * not. https://www.baeldung.com/spring-data-jpa-pagination-sorting
		 */
		Slice<Post> allPostsPageable = messageRepository.findAll(paging);

		List<Post> allPostsWithoutCheck = allPostsPageable.getContent();
		// FIXME
//		tut nuzhno delat' proverku na blacklist, i proveryat komu pokazivat' 
//		posti(naverno metod dolzhen prinimat usera
		List<Post> checkedList = checkBlackList(allPostsWithoutCheck, user);

		List<PostDto> list = checkedList.stream().map(post -> convertor.convertToPostDto(post))
				.collect(Collectors.toList());
		return convertor.convertToViewPostDto(list, paging);

	}

	@Override
	public void complainOnPost(String id) {
		Post post = messageRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.setComplpain(true);
		messageRepository.save(post);
		sendComplainedPostToAdministrator(post);

	}

	@Override
	public void hidePost(String id, String user) {
		Post post = messageRepository.findById(id).orElseThrow(PostNotFoundException::new);
		post.addToBlackList(user);
		messageRepository.save(post);
	}

	private List<Post> checkBlackList(List<Post> allPostsWithoutCheck, String user) {
		List<Post> checkList = new ArrayList<>(allPostsWithoutCheck);
		for (Post p : checkList) {
			if (p.getBlackList().contains(user)) {
				checkList.remove(p);
			}
		}
		return checkList;
	}

	private void sendComplainedPostToAdministrator(Post post) {
		// TODO

	}

}
