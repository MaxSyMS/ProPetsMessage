package propets.message.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import propets.message.domain.Post;

public interface MessageRepository extends MongoRepository<Post, String> {
	
}
