package net.QuinX.quinx.repository;

import net.QuinX.quinx.entity.JournalEntry;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface JournalEntryRepository extends MongoRepository<JournalEntry, String> {
    // You can add custom queries here if needed
}
