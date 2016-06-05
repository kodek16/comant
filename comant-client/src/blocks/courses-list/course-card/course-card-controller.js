(function() {
  'use strict';

  angular
    .module('comantApp')
    .controller('CourseCardController', CourseCardController);

  CourseCardController.$inject = ['$state'];

  function CourseCardController($state) {
    let vm = this;

    vm.goToCourse = goToCourse;

    function goToCourse(course) {
      $state.go('course.classes', {
        courseId: course.id
      });
    }
  }
})();