package br.recomende.model.recommending;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.xml.sax.SAXException;

import br.recomende.infra.http.GetFacade;
import br.recomende.infra.user.User;
import br.recomende.infra.util.SpringScope;
import br.recomende.model.document.BlogPostDocument;
import br.recomende.model.document.Document;
import br.recomende.model.repository.BlogToRecommendRepository;
import br.recomende.model.repository.UserRepository;


@Component
@Scope(SpringScope.APPLICATION)
public class BlogPostRecommender {
	
	private RecommedationSender recommedationSender;
	private GetFacade getFacade;
	private BlogCommentExtractor blogCommentExtractor;
	private BlogToRecommendRepository blogToRecommendRepository;
	private UserRepository userRepository;
	private BlogPostExtractor blogPostExtractor;
	
	@Autowired
	public BlogPostRecommender(RecommedationSender recommedationSender, GetFacade getFacade, 
			BlogCommentExtractor blogCommentExtractor, BlogToRecommendRepository blogToRecommendRepository,
			UserRepository userRepository, BlogPostExtractor blogPostExtractor) {
		this.recommedationSender = recommedationSender;
		this.getFacade = getFacade;
		this.blogCommentExtractor = blogCommentExtractor;
		this.blogToRecommendRepository = blogToRecommendRepository;
		this.userRepository = userRepository;
		this.blogPostExtractor = blogPostExtractor;
	}
	
	//@Scheduled(fixedDelay=5000)
	//@Scheduled(cron="0 0 2 * * *")
	public void recommend() throws IOException, SAXException, AddressException, MessagingException {
		Collection<BlogToRecommend> blogs = this.blogToRecommendRepository.list();
		for (BlogToRecommend blog : blogs) {
			List<BlogPostDocument> posts = this.blogPostExtractor.extractPosts(blog.getUrl());
			List<BlogComment> comments = this.blogCommentExtractor.extractComments(blog.getUrl());
			for (BlogPostDocument post : posts) {
				List<String> users = new ArrayList<String>();
				for (BlogComment comment : comments) {
					if (comment.getBlogPostPermalink().startsWith(post.getUrl())) {
						users.add(comment.getCreator());
					}
				}
				Collection<User> usersAbsent = this.userRepository.getNotInListByCitName(users);
				if (usersAbsent.size() > 0) {
					this.sendRecommendations(usersAbsent, blog.getToNoCommentUsers(), post.getText(), 3);
				} else {
					this.sendRecommendations(this.userRepository.list(), blog.getToFinalizedTopic(), post.getText(), 1);
				}
			}
		}
	}
	
	private void sendRecommendations(Collection<User> users, Class<? extends Document> documentClass, String text, Integer quantity) throws AddressException, MessagingException, IOException {
		for (User user : users) {
			Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("q", text);
			parameters.put("t", documentClass.getCanonicalName());
			parameters.put("u", user.getUsername());
			parameters.put("n", quantity);
			InputStream stream = this.getFacade.get("http://localhost:8080/recomende/search", parameters);
			this.recommedationSender.send(user.getEmail(), this.convertStreamToString(stream));
		}
	}

	public String convertStreamToString(InputStream is) throws IOException {
		if (is != null) {
			StringBuilder sb = new StringBuilder();
			String line;

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				while ((line = reader.readLine()) != null) {
					sb.append(line).append("\n");
				}
			} finally {
				is.close();
			}
			return sb.toString();
		}
		return null;
	}

	
}
