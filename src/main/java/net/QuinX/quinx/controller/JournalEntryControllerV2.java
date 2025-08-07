package net.QuinX.quinx.controller;

import net.QuinX.quinx.entity.JournalEntry;
import net.QuinX.quinx.entity.User;
import net.QuinX.quinx.services.JournalEntryService;
import net.QuinX.quinx.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping
    public List<JournalEntry> getAll() {
        return journalEntryService.getAll(); // ✅ Already defined
    }

    @PostMapping("/{userName}")
    public ResponseEntity<JournalEntry> createEntry(
            @RequestBody JournalEntry myEntry,
            @PathVariable String userName) {

        try {
            User user = userService.findByUsername(userName);
            if (user == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            journalEntryService.saveEntry(myEntry, userName); // ✅ date set inside service
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

        } catch (Exception e) {
            e.printStackTrace(); // Consider logging instead
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUsername(userName);
        if (user == null || user.getJournalEntries() == null) {
            return new ResponseEntity<>("User not found or has no journal entries", HttpStatus.NOT_FOUND);
        }

        List<JournalEntry> all = user.getJournalEntries();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }



    @DeleteMapping("/id/{myId}")
    public ResponseEntity<String> deleteJournalEntryById(@PathVariable String myId) {
        try {
            journalEntryService.deleteById(myId);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Not Found", HttpStatus.NOT_FOUND);
        }
    }
}
