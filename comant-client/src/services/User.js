(function() {
  'use strict';

  angular
    .module('comantApp')
    .factory('User', User);

  User.$inject = ['$resource'];

  function User($resource) {
    return $resource('api/users/:username');
  }
})();
