package ru.job4j.dreamjob.store;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.stereotype.Repository;
import ru.job4j.dreamjob.model.Candidate;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CandidateDBStore {
    private final BasicDataSource pool;

    public CandidateDBStore(BasicDataSource pool) {
        this.pool = pool;
    }

    public List<Candidate> findAll() {
        List<Candidate> candidates = new ArrayList<>();
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate")
        ) {
            try (ResultSet it = ps.executeQuery()) {
                while (it.next()) {
                    candidates.add(new Candidate(it.getInt("id"), it.getString("name"),
                            it.getString("description"), it.getObject("created", LocalDate.class),
                            it.getBytes("photo")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidates;
    }

    public Candidate add(Candidate candidate) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("INSERT INTO candidate(name,description,created,photo) VALUES (?,?,?,?)",
                     PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(candidate.getPhoto());
            ps.setString(1, candidate.getName());
            ps.setString(2, candidate.getDescription());
            ps.setObject(3, candidate.getCreated());
            ps.setBinaryStream(4, byteArrayInputStream, byteArrayInputStream.available());
            ps.execute();
            try (ResultSet id = ps.getGeneratedKeys()) {
                if (id.next()) {
                    candidate.setId(id.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return candidate;
    }

    public boolean update(Candidate candidate) {
        boolean result = false;
        try (Connection cn = pool.getConnection();
             PreparedStatement statement =
                     cn.prepareStatement("UPDATE candidate set name = ?, description = ?, created = ?, photo = ? where id = ?")) {
            InputStream byteArrayInputStream = new ByteArrayInputStream(candidate.getPhoto());
            statement.setString(1, candidate.getName());
            statement.setString(2, candidate.getDescription());
            statement.setObject(3, candidate.getCreated());
            statement.setBinaryStream(4, byteArrayInputStream, byteArrayInputStream.available());
            statement.setInt(5, candidate.getId());
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Candidate findById(int id) {
        try (Connection cn = pool.getConnection();
             PreparedStatement ps = cn.prepareStatement("SELECT * FROM candidate WHERE id = ?")
        ) {
            ps.setInt(1, id);
            try (ResultSet it = ps.executeQuery()) {
                if (it.next()) {
                    return new Candidate(it.getInt("id"), it.getString("name"),
                            it.getString("description"), it.getObject("created", LocalDate.class),
                            it.getBytes("photo"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
