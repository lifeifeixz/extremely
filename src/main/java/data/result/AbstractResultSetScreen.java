/*
 * Copyright (c) 2018, 2018, Travel and/or its affiliates. All rights reserved.
 * TRAVEL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
package data.result;

import data.Table;
import data.protocol.Select;

import java.util.List;
import java.util.Map;

/**
 * @author flysLi
 * @ClassName AbstractResultSetScreen
 * @Decription TODO
 * @Date 2019/1/9 14:16
 * @Version 1.0
 */
public abstract class AbstractResultSetScreen implements ResultSetScreen {

    ResultSetScreen screen;

    Table table;

    Select select;

    List<Map<String, Object>> resultSet;
}
