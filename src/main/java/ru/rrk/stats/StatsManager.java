package ru.rrk.stats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rrk.classifiers.DataType;
import ru.rrk.stats.strategy.StatStrategy;

import java.util.HashMap;
import java.util.HashSet;

@Getter
@RequiredArgsConstructor
public class StatsManager {
    private final StatFactory statFactory;
    private final HashMap<DataType, StatStrategy> stats = new HashMap<>();

    public StatStrategy getOrCreateStats(DataType dataType, StatForm form) {
        if (!stats.containsKey(dataType)) putNewFullStatsInMap(dataType, form);
        return stats.get(dataType);
    }

    public HashSet<StatStrategy> getAllStats() {
        return new HashSet<>(stats.values());
    }

    private void putNewFullStatsInMap(DataType dataType, StatForm form) {
        if (form.equals(StatForm.FULL)) {
            stats.put(dataType, statFactory.create(dataType));
        } else {
            stats.put(dataType, statFactory.create());
        }
    }
}
