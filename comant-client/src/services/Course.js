(function() {
  'use strict';

  angular
    .module('comantApp')
    .factory('Course', Course);

  Course.$inject = ['$resource'];

  function Course($resource) {
    return $resource('api/courses/:id', {
      withInstructors: true,
      withListeners: true
    });
  }
})();