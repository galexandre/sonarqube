/*
 * SonarQube
 * Copyright (C) 2009-2016 SonarSource SA
 * mailto:contact AT sonarsource DOT com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package org.sonar.db.issue;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @since 3.6
 */
public interface ActionPlanMapper {

  void insert(ActionPlanDto actionPlanDto);

  void update(ActionPlanDto actionPlanDto);

  void delete(@Param("key") String key);

  List<ActionPlanDto> findByKeys(@Param("keys") List<String> keys);

  ActionPlanDto findByKey(@Param("key") String key);

  List<ActionPlanDto> findOpenByProjectId(@Param("projectId") Long projectId);

  List<ActionPlanDto> findByNameAndProjectId(@Param("name") String name, @Param("projectId") Long projectId);
}
