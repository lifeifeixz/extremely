package data.result;

import java.util.List;
import java.util.Map;

/**
 * 结果集筛选规范
 * <p>
 * <p>
 *
 * @author flysLi
 * @date 2019/1/9
 */
public interface ResultSetScreen {

    /**
     * 筛选结果集
     *
     * @param resultSet
     * @return
     */
    List<Map<String, Object>> screen(List<Map<String, Object>> resultSet);
}
