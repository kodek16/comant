(function() {
  'use strict';
  
  angular
    .module('comantApp')
    .directive('courseCard', courseCard);
  
  function courseCard() {
    return {
      templateUrl: 'blocks/courses-list/course-card/course-card.html',
      restrict: 'E',
      scope: {
        'course': '='
      },
      controller: 'CourseCardController',
      controllerAs: 'courseCard'
    };
  }
})();