package propets.message.dto;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
//M
public class PostDto {
	@Id
	String id;
	String ownerId;
	LocalDateTime postDate;
	String text;
	Set<String> images;

}
