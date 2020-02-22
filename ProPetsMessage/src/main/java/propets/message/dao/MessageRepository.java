package propets.message.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import propets.message.domain.Post;

public interface MessageRepository extends MongoRepository<Post, String> {

	Page<Post> findByIdIn(List<String> ids, Pageable paging);
	
}
