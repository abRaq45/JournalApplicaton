package net.QuinX.quinx.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Document(collection = "user")
@Getter
@Setter
public class User {

    @Id
    private String id;

    private String username;
    private String password;

    @DBRef
    private List<JournalEntry> journalEntries = new ArrayList<>();

    private Set<String> roles = new HashSet<>();

    public User() {}

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
