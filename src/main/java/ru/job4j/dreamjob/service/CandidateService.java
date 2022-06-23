package ru.job4j.dreamjob.service;

import net.jcip.annotations.ThreadSafe;
import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.Candidate;
import ru.job4j.dreamjob.store.CandidateDBStore;
import java.util.Collection;

@Service
@ThreadSafe
public class CandidateService {

    CandidateDBStore candidateDBStore;

    public CandidateService(CandidateDBStore candidateDBStore) {
        this.candidateDBStore = candidateDBStore;
    }

    public Collection<Candidate> findAll() {
        return candidateDBStore.findAll();
    }

    public void add(Candidate candidate) {
        candidateDBStore.add(candidate);
    }

    public Candidate findById(int id) {
        return candidateDBStore.findById(id);
    }

    public boolean update(Candidate candidate) {
        return candidateDBStore.update(candidate);
    }

}
