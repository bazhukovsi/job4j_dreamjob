package ru.job4j.dreamjob.store;

import org.junit.jupiter.api.Test;
import ru.job4j.dreamjob.Main;
import ru.job4j.dreamjob.model.Candidate;
import java.time.LocalDate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CandidateDBStoreTest {
    @Test
    public void whenCreateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = new Candidate(0, "Java Job", "Java Developer",
                LocalDate.now(), new byte[]{});
        store.add(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenUpdateCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = store.findById(1);
        candidate.setName("Sergey Bazhukov");
        candidate.setDescription("SPBGTU");
        candidate.setCreated(LocalDate.now());
        candidate.setPhoto(new byte[]{});
        store.update(candidate);
        Candidate candidateInDb = store.findById(candidate.getId());
        assertThat(candidateInDb.getName(), is(candidate.getName()));
    }

    @Test
    public void whenFindbyIdCandidate() {
        CandidateDBStore store = new CandidateDBStore(new Main().loadPool());
        Candidate candidate = store.findById(1);
        assertThat("Sergey Bazhukov", is(candidate.getName()));
    }
}