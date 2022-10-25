package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(BootStrapData.class);
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) {
        logger.debug("Started in Bootstrap Mode...");
        Publisher publisher = new Publisher("SFG Publications", "Rajiv Chowk", "New Delhi", "Delhi", "110041");
        Author eric = new Author("Eric", "Evans");
        Book domDriven = new Book("Domain Driven Design", "123123");

        eric.getBooks().add(domDriven);
        domDriven.getAuthors().add(eric);
        publisher.getBooks().add(domDriven);

        authorRepository.save(eric);
        bookRepository.save(domDriven);
        publisherRepository.save(publisher);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "123125");

        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        publisher.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(publisher);

        logger.info("Number of books: " + bookRepository.count());
        logger.info("Publisher's Number of books: " + publisher.getBooks().size());
        logger.debug("Ending Bootstrap mode...");
    }
}
