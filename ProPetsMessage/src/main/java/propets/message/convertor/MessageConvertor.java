package propets.message.convertor;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import propets.message.domain.Post;
import propets.message.dto.PostDto;
import propets.message.dto.ViewPostDto;

@Component
//M
public class MessageConvertor {

	public PostDto convertToPostDto(Post post) {
		return PostDto.builder()
				.id(post.getId())
				.ownerId(post.getOwnerId())
				.postDate(post.getPostDate())
				.text(post.getText())
				.images(post.getImages())
				.build();
	}

	public ViewPostDto convertToViewPostDto(List<PostDto> list, Pageable paging) {
		return ViewPostDto.builder()
				.posts(list)
				.currentPage(paging.getPageNumber())
				.itemsOnPage(paging.getPageSize())
				.itemsTotal(list.size())
				.build();
	}

	public ViewPostDto convertPostToViewPostDto(List<Post> favoritesList, Pageable paging) {
		List<PostDto> posts = favoritesList.stream().map(post->convertToPostDto(post)).collect(Collectors.toList());
		return convertToViewPostDto(posts, paging);
	}

}
