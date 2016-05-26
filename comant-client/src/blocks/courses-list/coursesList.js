(function() {
  'use strict';

  angular
    .module('comantApp')
    .directive('coursesList', coursesList);

  function coursesList() {
    CoursesListController.$inject = ['$scope', 'filterFilter'];

    return {
      templateUrl: 'blocks/courses-list/courses-list.html',
      restrict: 'E',
      scope: {
        'listName': '@name',
        'from': '=',
        'query': '='
      },
      controller: CoursesListController
    };

    function CoursesListController($scope, filterFilter) {
      $scope.listIsEmpty = function() {
        return filterFilter($scope.from, { name: $scope.query }).length === 0;
      }
    }
  }
})();
