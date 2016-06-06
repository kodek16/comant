(function() {
  'use strict';

  angular
    .module('comantApp')
    .factory('Lecture', Lecture);

  Lecture.$inject = ['$resource'];

  function Lecture($resource) {
    return $resource('api/lectures/:id');
  }
})();