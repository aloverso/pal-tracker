package io.pivotal.pal.tracker;

import java.sql.Time;
import java.util.*;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntries = new HashMap<>();
    private Long currentId = 1L;

    @Override
    public TimeEntry create(TimeEntry timeEntry) {
        timeEntry.setId(currentId);
        currentId++;
        timeEntries.put(timeEntry.getId(), timeEntry);
        return timeEntry;
    }

    @Override
    public TimeEntry find(long id) {
        return timeEntries.get(id);
    }

    @Override
    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry toUpdate = timeEntries.get(id);
        if(toUpdate == null) return null;

        timeEntry.setId(id);
        timeEntries.put(id, timeEntry);
        return timeEntry;
    }

    @Override
    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    @Override
    public TimeEntry delete(long id) {
        TimeEntry deleted = timeEntries.get(id);
        timeEntries.remove(id);
        return deleted;
    }
}
