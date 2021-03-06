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
package org.sonar.batch.cache;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.sonar.batch.bootstrap.BatchWsClient;
import org.sonar.batch.cache.WSLoader.LoadStrategy;
import org.sonar.home.cache.PersistentCache;

import static org.assertj.core.api.Assertions.assertThat;

public class StrategyWSLoaderProviderTest {
  @Mock
  private PersistentCache cache;

  @Mock
  private BatchWsClient client;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testStrategy() {
    StrategyWSLoaderProvider provider = new StrategyWSLoaderProvider(LoadStrategy.CACHE_FIRST);
    WSLoader wsLoader = provider.provide(cache, client);

    assertThat(wsLoader.getDefaultStrategy()).isEqualTo(LoadStrategy.CACHE_FIRST);
  }

  @Test
  public void testSingleton() {
    StrategyWSLoaderProvider provider = new StrategyWSLoaderProvider(LoadStrategy.CACHE_FIRST);
    WSLoader wsLoader = provider.provide(cache, client);

    assertThat(provider.provide(null, null)).isEqualTo(wsLoader);
  }
}
