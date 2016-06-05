(function() {
  'use strict';
  
  angular
    .module('comantApp')
    .controller('CoursesOverviewController', CoursesOverviewController);

  CoursesOverviewController.$inject = ['Course', '$scope', '$state'];

  function CoursesOverviewController(Course, $scope, $state) {
    let vm = this;
    
    vm.ongoingCourses = [];
    vm.pastCourses = [];
    vm.newCourses = [];

    vm.shouldShow = shouldShow;

    init();

    function init() {
      vm.ongoingCourses = Course.query({
        state: 'ongoing',
        user: $scope.currentUser.username,
        detailed: 'true'
      });
      vm.pastCourses = Course.query({
        state: 'past',
        user: $scope.currentUser.username,
        detailed: 'true'
      });
      vm.newCourses = Course.query({
        state: 'new',
        detailed: 'true'
      });
    }

    function shouldShow(state) {
      return $state.is('courses')|| $state.is('courses.' + state);
    }
  }
})();