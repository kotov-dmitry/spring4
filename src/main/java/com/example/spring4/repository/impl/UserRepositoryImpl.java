package com.example.spring4.repository.impl;

import com.example.spring4.domain.entity.User;
import com.example.spring4.repository.UserRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.UUID;

import static java.util.UUID.randomUUID;

/**
 * @author dkotov
 * @since 30.11.2021
 */
@Repository
public class UserRepositoryImpl implements UserRepository {

    private final ObjectMapper objectMapper;
    private final File file;
    private final Path path;

    @SneakyThrows
    public UserRepositoryImpl(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        path = Path.of("/Users/dkotov/IdeaProjects/spring4/database.json");
        if (!Files.exists(path)) {
            Files.createFile(path);
            Files.writeString(path, "{}");
        }
        this.file = new File(path.toUri());
    }

    public User get(UUID id) {
        return findAll().get(id);
    }

    @SneakyThrows
    public User create(User user) {
        Map<UUID, User> content = findAll();
        UUID id = randomUUID();
        user.setId(id);
        content.put(id, user);
        Files.writeString(path, objectMapper.writeValueAsString(content));
        return findAll().get(id);
    }

    @SneakyThrows
    public User update(User user) {
        Map<UUID, User> content = findAll();
        UUID id = user.getId();
        content.put(id, user);
        Files.writeString(path, objectMapper.writeValueAsString(content));
        return findAll().get(id);
    }

    @SneakyThrows
    public void delete(UUID id) {
        Map<UUID, User> content = findAll();
        content.remove(id);
        Files.writeString(path, objectMapper.writeValueAsString(content));
    }

    @SneakyThrows
    private Map<UUID, User> findAll() {
        return objectMapper.readValue(file, new TypeReference<>() {
        });
    }
}
