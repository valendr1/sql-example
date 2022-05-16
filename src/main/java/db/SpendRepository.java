package db;

import entity.SpendEntity;

import java.util.List;

public interface SpendRepository {
    List<SpendEntity> getAll();
}
