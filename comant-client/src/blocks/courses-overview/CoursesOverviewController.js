(function() {
  'use strict';
  
  angular
    .module('comantApp')
    .controller('CoursesOverviewController', CoursesOverviewController);

  CoursesOverviewController.$inject = ['Course', '$http'];

  function CoursesOverviewController(Course, $http) {
    let vm = this;
    
    vm.courses = [];

    init();

    function init() {
      vm.courses = Course.query();
    }
  }
})();