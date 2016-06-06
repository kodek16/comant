(function() {
  'use strict';

  angular
    .module('comantApp')
    .factory('userRoles', userRoles);

  function userRoles() {
    let roles = {
      'student': 1,
      'teacher': 2,
      'admin': 3
    };

    return {
      isAtLeast
    };

    function isAtLeast(user, role) {
      return roles[user.role] >= roles[role];
    }
  }
})();