package net.QuinX.quinx.services;

import net.QuinX.quinx.entity.JournalEntry;
import net.QuinX.quinx.entity.User;
import net.QuinX.quinx.repository.JournalEntryRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;



    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUsername(userName);
        if (user == null) {

            throw new IllegalArgumentException("User not found: " + userName);
        }

        // ✅ First, save the JournalEntry itself
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalEntryRepository.save(journalEntry);

        // ✅ Then, reference it in the User object
        if (user.getJournalEntries() == null) {
            user.setJournalEntries(new ArrayList<>());
        }
        user.getJournalEntries().add(saved);

        // ✅ Finally, save the user
        userService.saveEntry(user);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public void deleteById(String myId) {
        journalEntryRepository.deleteById(myId);
    }


}

