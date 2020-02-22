package propets.message.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
//M
public class ViewPostDto {
	int currentPage;
	 int itemsOnPage;
	 int itemsTotal;
	 List<PostDto> posts;
	}


