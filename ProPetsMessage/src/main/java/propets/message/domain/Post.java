package propets.message.domain;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Document(collection = "posts")
@EqualsAndHashCode(of = {"id"})
//M
public class Post {

	@Id
	String id;
	String ownerId;
	LocalDateTime postDate;
	@Setter
	String text;
	Set<String> images;
	Set<String> blackList;
	@Setter
	boolean complpain;

	
	public boolean addImages(String image) {
		return images.add(image);
	}
	public boolean addToBlackList(String user) {
		return blackList.add(user);
	}
	
	
}
