(function() {
  'use strict';
  
  angular
    .module('comantApp')
    .directive('classInfo', classInfo);
  
  function classInfo() {
    return {
      templateUrl: 'blocks/class-info/class-info.html',
      restrict: 'E',
      scope: {
        'class': '=',
        'isInstructor': '='
      },
      controller: 'ClassInfoController',
      controllerAs: 'classInfo'
    };
  }
})();