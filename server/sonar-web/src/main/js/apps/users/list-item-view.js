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
import _ from 'underscore';
import Marionette from 'backbone.marionette';
import UpdateView from './update-view';
import ChangePasswordView from './change-password-view';
import DeactivateView from './deactivate-view';
import GroupsView from './groups-view';
import TokensView from './tokens-view';
import Template from './templates/users-list-item.hbs';

export default Marionette.ItemView.extend({
  tagName: 'tr',
  template: Template,

  events: {
    'click .js-user-more-scm': 'onMoreScmClick',
    'click .js-user-more-groups': 'onMoreGroupsClick',
    'click .js-user-update': 'onUpdateClick',
    'click .js-user-change-password': 'onChangePasswordClick',
    'click .js-user-deactivate': 'onDeactivateClick',
    'click .js-user-groups': 'onGroupsClick',
    'click .js-user-tokens': 'onTokensClick'
  },

  initialize: function () {
    this.scmLimit = 3;
    this.groupsLimit = 3;
  },

  onRender: function () {
    this.$el.attr('data-login', this.model.id);
    this.$('[data-toggle="tooltip"]').tooltip({ container: 'body', placement: 'bottom' });
  },

  onDestroy: function () {
    this.$('[data-toggle="tooltip"]').tooltip('destroy');
  },

  onMoreScmClick: function (e) {
    e.preventDefault();
    this.showMoreScm();
  },

  onMoreGroupsClick: function (e) {
    e.preventDefault();
    this.showMoreGroups();
  },

  onUpdateClick: function (e) {
    e.preventDefault();
    this.updateUser();
  },

  onChangePasswordClick: function (e) {
    e.preventDefault();
    this.changePassword();
  },

  onDeactivateClick: function (e) {
    e.preventDefault();
    this.deactivateUser();
  },

  onGroupsClick: function (e) {
    e.preventDefault();
    this.showGroups();
  },

  onTokensClick: function (e) {
    e.preventDefault();
    this.showTokens();
  },

  showMoreScm: function () {
    this.scmLimit = 10000;
    this.render();
  },

  showMoreGroups: function () {
    this.groupsLimit = 10000;
    this.render();
  },

  updateUser: function () {
    new UpdateView({
      model: this.model,
      collection: this.model.collection
    }).render();
  },

  changePassword: function () {
    new ChangePasswordView({
      model: this.model,
      collection: this.model.collection
    }).render();
  },

  deactivateUser: function () {
    new DeactivateView({ model: this.model }).render();
  },

  showGroups: function () {
    new GroupsView({ model: this.model }).render();
  },

  showTokens: function () {
    new TokensView({ model: this.model }).render();
  },

  serializeData: function () {
    var scmAccounts = this.model.get('scmAccounts'),
        scmAccountsLimit = scmAccounts.length > this.scmLimit ? this.scmLimit - 1 : this.scmLimit,
        groups = this.model.get('groups'),
        groupsLimit = groups.length > this.groupsLimit ? this.groupsLimit - 1 : this.groupsLimit;
    return _.extend(Marionette.ItemView.prototype.serializeData.apply(this, arguments), {
      firstScmAccounts: _.first(scmAccounts, scmAccountsLimit),
      moreScmAccountsCount: scmAccounts.length - scmAccountsLimit,
      firstGroups: _.first(groups, groupsLimit),
      moreGroupsCount: groups.length - groupsLimit
    });
  }
});


