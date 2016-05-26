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
        'state': 'ongoing',
        'ofUser': $scope.currentUser.username
      });
      vm.pastCourses = Course.query({
        'state': 'past',
        'ofUser': $scope.currentUser.username
      });
      vm.newCourses = Course.query({
        'state': 'new'
      });
    }

    function shouldShow(state) {
      return $state.is('courses')|| $state.is('courses.' + state);
    }
  }
})();