(function() {
  'use strict';
  
  angular
    .module('comantApp')
    .factory('Class', Class);
  
  Class.$inject = ['$resource'];
  
  function Class($resource) {
    return $resource('api/classes/:id', {}, {
      queryByCourse: {
        method: 'GET',
        url: 'api/courses/:courseId/classes',
        isArray: true
      }
    });
  }
})();