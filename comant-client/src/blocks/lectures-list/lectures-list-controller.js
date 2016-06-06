(function() {
  'use strict';

  angular
    .module('comantApp')
    .controller('LecturesListController', LecturesListController);

  LecturesListController.$inject = ['Lecture', 'userRoles', '$scope', '$state'];

  function LecturesListController(Lecture, userRoles, $scope, $state) {
    let vm = this;

    vm.lectures = [];
    vm.goToLecture = goToLecture;
    vm.canAddLectures = userRoles.isAtLeast($scope.currentUser, 'teacher');

    init();

    function init() {
      vm.lectures = Lecture.query();
    }

    function goToLecture(lecture) {
      $state.go('lecture', {
        lectureId: lecture.id
      });
    }
  }
})();