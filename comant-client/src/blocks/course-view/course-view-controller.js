(function() {
  'use strict';
  
  angular
    .module('comantApp')
    .controller('CourseViewController', CourseViewController);
  
  CourseViewController.$inject = ['Class', 'Course', '$scope', '$stateParams'];
  
  function CourseViewController(Class, Course, $scope, $stateParams) {
    let vm = this;

    vm.course = {};
    vm.classes = [];
    vm.currentUserIsInstructor = false;

    init();

    function init() {
      vm.course = Course.get({
        id: $stateParams.courseId
      }, () => {
        vm.currentUserIsInstructor = vm.course.instructors.some(
          instructor => instructor.username == $scope.currentUser.username
        );
      });

      vm.classes = Class.queryByCourse({
        courseId: $stateParams.courseId,
        detailed: true
      });
    }
  }
})();