package ru.rrk.stats;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import ru.rrk.classifiers.DataType;
import ru.rrk.stats.strategy.StatStrategy;

import java.util.HashMap;


@Getter
@RequiredArgsConstructor
public class StatsRepository {
    private final StatFactory statFactory;
    private final HashMap<DataType, StatStrategy> stats = new HashMap<>();

    /**
     * Метод проверяет существование Stats, который обеспечивает соответствующий тип данных, если данного Stats в мапе нет,
     * тогда создает его.
     * @param dataType тип данных, который должен быть обеспечен Writer.
     * @param form форма статистики, которую ввел юзер.
     * @return найденный или только что созданный Writer.
     */
    public StatStrategy getOrCreateStats(DataType dataType, StatForm form) {
        if (!stats.containsKey(dataType)) putNewFullStatsInMap(dataType, form);
        return stats.get(dataType);
    }

    /**
     * Метод кладет в мапу Stats с заданными данными.
     * @param dataType тип данных, который будет обеспечивать данный экземпляр Stats.
     * @param form форма статистики, которую ввел юзер.
     */
    private void putNewFullStatsInMap(DataType dataType, StatForm form) {
        if (form.equals(StatForm.FULL)) {
            stats.put(dataType, statFactory.create(dataType));
        } else {
            stats.put(dataType, statFactory.create());
        }
    }
}
