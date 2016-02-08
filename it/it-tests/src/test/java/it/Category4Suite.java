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
package it;

import com.sonar.orchestrator.Orchestrator;
import it.analysisExclusion.FileExclusionsTest;
import it.analysisExclusion.IssueExclusionsTest;
import it.component.ComponentsWsTest;
import it.component.ProjectSearchTest;
import it.dbCleaner.PurgeTest;
import it.duplication.CrossProjectDuplicationsOnRemoveFileTest;
import it.duplication.CrossProjectDuplicationsTest;
import it.duplication.DuplicationsTest;
import it.highlighting.HighlightingTest;
import it.serverSystem.HttpsTest;
import it.serverSystem.RestartTest;
import it.serverSystem.ServerSystemRestartingOrchestrator;
import it.serverSystem.ServerSystemTest;
import it.updateCenter.UpdateCenterTest;
import it.user.FavouriteTest;
import it.user.ForceAuthenticationTest;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static util.ItUtils.xooPlugin;

@RunWith(Suite.class)
@Suite.SuiteClasses({
  // server system
  RestartTest.class,
  HttpsTest.class,
  ServerSystemTest.class,
  ServerSystemRestartingOrchestrator.class,
  // user
  ForceAuthenticationTest.class,
  FavouriteTest.class,
  // component search
  ProjectSearchTest.class,
  ComponentsWsTest.class,
  // update center
  UpdateCenterTest.class,
  // analysis exclusion
  FileExclusionsTest.class,
  IssueExclusionsTest.class,
  // duplication
  CrossProjectDuplicationsTest.class,
  CrossProjectDuplicationsOnRemoveFileTest.class,
  DuplicationsTest.class,
  // db cleaner
  PurgeTest.class,
  // highlighting
  HighlightingTest.class
})
public class Category4Suite {

  @ClassRule
  public static final Orchestrator ORCHESTRATOR = Orchestrator.builderEnv()
    .addPlugin(xooPlugin())
    .build();
}
