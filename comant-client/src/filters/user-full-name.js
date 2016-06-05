(function() {
  'use strict';

  angular
    .module('comantApp')
    .filter('userFullName', userFullName);

  function userFullName() {
    return function(user) {
      return `${user.firstname} ${user.lastname}`;
    }
  }
})();